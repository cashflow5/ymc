package com.yougou.merchant.api.order.service;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yougou.component.area.api.IAreaApi;
import com.yougou.component.area.model.Area;
import com.yougou.merchant.api.order.dao.MerchantOrderMapper;
import com.yougou.ordercenter.common.PageFinder;
import com.yougou.ordercenter.common.Query;
import com.yougou.ordercenter.constant.OrderConstant;
import com.yougou.ordercenter.constant.Payment;
import com.yougou.ordercenter.exception.ParameterException;
import com.yougou.ordercenter.exception.SystemException;
import com.yougou.ordercenter.model.order.OrderBuyInfo;
import com.yougou.ordercenter.model.order.OrderConsigneeInfo;
import com.yougou.ordercenter.model.order.OrderDetail4sub;
import com.yougou.ordercenter.model.order.OrderSub;
import com.yougou.ordercenter.model.order.OrderSubExpand;
import com.yougou.ordercenter.vo.merchant.input.QueryOrderInputDto;
import com.yougou.ordercenter.vo.merchant.output.CancelOrModifyOrderForMerchantVo;
import com.yougou.ordercenter.vo.merchant.output.QueryOrderCanceledOutputDto;
import com.yougou.ordercenter.vo.merchant.output.QueryOrderDetailOutputDto;
import com.yougou.ordercenter.vo.merchant.output.QueryOrderOutputDto;
import com.yougou.ordercenter.vo.order.OrderEnum.DeliveryDate;
import com.yougou.wms.wpi.warehouse.service.IWarehouseCacheService;

@Service
public class OrderSubService{

	private final Logger logger = Logger.getLogger(OrderSubService.class);
	@Resource
	private IAreaApi areaApi;
	@Autowired
	private IWarehouseCacheService warehouseCacheService;
	
	@Resource
	private MerchantOrderMapper merchantOrderDao;

	public List<QueryOrderCanceledOutputDto> queryMerchantOrderCanceledList(QueryOrderInputDto dto) throws SystemException {
		if (dto == null) {
			logger.error("查询取消订单参数为空！");
			throw new SystemException("缺少必要参数！");
		}
		List<QueryOrderCanceledOutputDto> results = new ArrayList<QueryOrderCanceledOutputDto>();
		try {
			//List<Map<String, Object>> orderList = orderSubDao.selectMerchantCancleSaleOrder(dto);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("merchantCode", dto.getMerchant_code());
			params.put("isException", OrderConstant.NO_EXCEPTION);
			params.put("baseStatus1", OrderConstant.BASE_SPECIAL_CANCEL);
			params.put("baseStatus2", OrderConstant.BASE_UPDATE);
			params.put("delFlag", OrderConstant.DEL_FLAG_FALSE);
			params.put("startTime", dto.getStart_created());
			params.put("endTime", dto.getEnd_created());
			
			List<Map<String, Object>> orderList = merchantOrderDao.selectMerchantCancleSaleOrder(params);
			for(Map<String, Object> map : orderList){
				QueryOrderCanceledOutputDto canceledOutputDto = new QueryOrderCanceledOutputDto();
				try {
					canceledOutputDto.setModify_time(this.getdate1(String.valueOf(map.get("modity_date"))));
				} catch (Exception e) {
					logger.error("日期类型转换出错，orderSubNo:"+map.get("order_sub_no"));
				}
				canceledOutputDto.setOrder_sub_no(String.valueOf(map.get("order_sub_no")));
				results.add(canceledOutputDto);
			}
		} finally {
		}

		return results;
	}

	public List<QueryOrderDetailOutputDto> getOrderDetail(QueryOrderInputDto dto)
			throws SystemException {
		if (StringUtils.isEmpty(dto.getOrder_sub_no())
				|| StringUtils.isEmpty(dto.getMerchant_code())) {
			logger.error("查询订单参数为空！");
			throw new SystemException("缺少必要参数！");
		}
		
		if(StringUtils.isNotEmpty(dto.getStart_modified())){
			try {
				dto.setStart_modified(String.valueOf(this.getdate1(dto.getStart_modified()).getTime()));
			} catch (Exception e) {
				logger.info("时间戳转换出错");
			}
		}
		
		if(StringUtils.isNotEmpty(dto.getEnd_modified())){
			try {
				dto.setEnd_modified(String.valueOf(this.getdate1(dto.getEnd_modified()).getTime()));
			} catch (Exception e) {
				logger.info("时间戳转换出错");
			}
		}
		
		/**
		 * 查询出符合条件的订单数据
		 */
		//List<OrderSub> orderList = orderSubDao.selectMerchantOrderList(dto);
		Map<String, String> map = this.convertBean(dto);
		List<OrderSub> orderList = merchantOrderDao.selectMerchantOrderList(map);
		
		/**
		 * 对符合条件的订单数据进行封住
		 */
		for(OrderSub orderSub : orderList){
			String orderSubId = orderSub.getId();
			//OrderSubExpand orderSubExpand = orderSubExpandDao.obtainOrderSubExpandByOrderSubId(orderSubId);
			//OrderConsigneeInfo orderConsigneeInfo = orderConsigneeInfoDao.findByOrderConsigneeInfoId(orderSub.getConsigneeId());
			//OrderBuyInfo orderBuyInfo = orderBuyInfoDao.findByOrderBuyInfoId(orderSub.getBuyId());
			OrderSubExpand orderSubExpand = merchantOrderDao.findOrderSubExpandByOrderSubId(orderSubId);
			OrderConsigneeInfo orderConsigneeInfo = this.findByOrderConsigneeInfoId(orderSub.getConsigneeId());
			OrderBuyInfo orderBuyInfo = merchantOrderDao.findByOrderBuyInfoId(orderSub.getBuyId());
			
			orderSub.setOrderBuyInfo(orderBuyInfo);
			orderSub.setOrderSubExpand(orderSubExpand);
			orderSub.setOrderConsigneeInfo(orderConsigneeInfo);
		}
		
		/**
		 * 定义返回信息
		 */
		List<QueryOrderDetailOutputDto> items = new ArrayList<QueryOrderDetailOutputDto>();
		for(OrderSub orderSub : orderList){
			QueryOrderDetailOutputDto item = new QueryOrderDetailOutputDto();
			item.setId(orderSub.getId());
			item.setOrder_sub_no(orderSub.getOrderSubNo());
			item.setOrder_status(item.getOrder_status(orderSub.getBaseStatus()));
			item.setProduct_total_quantity(orderSub.getProductTotalQuantity());
			item.setOriginal_order_no(orderSub.getOriginalOrderNo());
			item.setMessage(orderSub.getMessage());
			item.setPayment(Payment.valueOf(orderSub.getPayment()));
			item.setLogistics_code(orderSub.getLogisticsCode());
			item.setLogistics_name(orderSub.getLogisticsName());
			item.setOrder_pay_total_amont(orderSub.getOrderPayTotalAmont());
			item.setActual_postage(orderSub.getActualPostage());
			item.setDiscount_amount(orderSub.getDiscountAmount());
			item.setCoupon_pref_amount(orderSub.getCouponPrefAmount());
			item.setCoupon_pref_amount5(orderSub.getCouponPrefAmount5());
			item.setCreate_time(orderSub.getCreateTime());
			item.setModify_time(orderSub.getModityDate());
			item.setOnline_pay_time(orderSub.getOnlinePayTime());
			item.setShip_time(orderSub.getShipTime());
			item.setProvince(orderSub.getOrderConsigneeInfo().getProvince());
			item.setCity(orderSub.getOrderConsigneeInfo().getCity());
			item.setArea(orderSub.getOrderConsigneeInfo().getArea());
			item.setConsignee_address(orderSub.getOrderConsigneeInfo().getConsigneeAddress());
			item.setConsignee_name(orderSub.getOrderConsigneeInfo().getUserName());
			item.setConstact_phone(orderSub.getOrderConsigneeInfo().getConstactPhone());
			item.setEmail(orderSub.getOrderConsigneeInfo().getEmail());
			item.setMobile_phone(orderSub.getOrderConsigneeInfo().getMobilePhone());
			item.setZipcode(orderSub.getOrderConsigneeInfo().getZipCode());
			item.setBuyer_name(orderSub.getOrderBuyInfo().getRealName());
			item.setMember_name(orderSub.getOrderBuyInfo().getMemberName());
			item.setDelivery_date_temp(DeliveryDate.valueOf(orderSub.getOrderConsigneeInfo().getDeliveryDate()));
			item.setOrder_source_no(orderSub.getOrderSourceNo());
			item.setBuy_reduction_pref_amount(orderSub.getBuyReductionPrefAmount());//li.j1 2015-08-27 add
			List<OrderDetail4sub> orderDetails = orderSub.getOrderDetail4subs();
			List<QueryOrderDetailOutputDto.ItemDetail> itemDetails = new ArrayList<QueryOrderDetailOutputDto.ItemDetail>();
			for(OrderDetail4sub orderDetail : orderDetails){
				QueryOrderDetailOutputDto.ItemDetail itemDetail = new QueryOrderDetailOutputDto.ItemDetail();
				itemDetail.setProd_no(orderDetail.getProdNo());
				itemDetail.setProd_name(orderDetail.getProdName());
				itemDetail.setProd_unit_price(orderDetail.getProdUnitPrice());
				itemDetail.setProd_discount_amount(orderDetail.getProdDiscountAmount());
				itemDetail.setProd_total_amt(orderDetail.getProdTotalAmt());
				itemDetail.setLevel_code(orderDetail.getLevelCode());
				itemDetail.setCommodity_no(orderDetail.getCommodityNo());
				itemDetail.setCommodity_image(orderDetail.getCommodityImage());
				itemDetail.setCommodity_num(orderDetail.getCommodityNum());
				itemDetail.setCommodity_weight(orderDetail.getCommodityWeight());
				itemDetail.setCommodity_specification_str(orderDetail.getCommoditySpecificationStr());
				itemDetail.setCommodity_type(orderDetail.getCommodityType());
				itemDetails.add(itemDetail);
			}
			item.setItems(itemDetails);
			
			Area province = this.selectByNo(orderSub.getOrderConsigneeInfo().getProvince());
			Area city = this.selectByNo(orderSub.getOrderConsigneeInfo().getCity());
			Area area = this.selectByNo(orderSub.getOrderConsigneeInfo().getArea());
			
			if(province != null){
				item.setProvince_name(province.getName());
			}
			if(city != null){
				item.setCity_name(city.getName());
			}
			if(area != null){
				item.setArea_name(area.getName());
			}
			items.add(item);
		}
		
		return items;
	}

	public QueryOrderOutputDto getMerchantOrderList(QueryOrderInputDto dto)
			throws SystemException {
		if (dto == null) {
			logger.error("查询订单参数为空！");
			throw new SystemException("缺少必要参数！");
		}
		String merchant_code = dto.getMerchant_code();
		String merchant_warehouse_code = "";
		if (null != merchant_code && (merchant_code = merchant_code.trim()).length() > 0) {
			try {
				//Map<String, String>KEY是仓库编码，VALUE是仓库名称
				Map<String, String> warehouseDomain = warehouseCacheService.getWarehouseByMerchantCode(merchant_code);
				Set<String> wareHourseCodeSet = warehouseDomain.keySet();
				if (wareHourseCodeSet != null && !wareHourseCodeSet.isEmpty()) {
					merchant_warehouse_code = wareHourseCodeSet.iterator().next();
//					for (String wareHourseCode : wareHourseCodeSet) {
//						merchant_warehouse_code = wareHourseCode;
//						break;
//					}
				}
			} catch (Exception ex) {
				logger.error(ex);
				ex.printStackTrace();
			}
		}
		if (StringUtils.isEmpty(merchant_warehouse_code)) {
			logger.error("获取商家仓库为空，商家编码为:"+merchant_code);
			throw new SystemException("该商家没有配置对应的仓库！merchantCode="+merchant_code);
		} else {
			dto.setMerchant_warehouse_code(merchant_warehouse_code);
		}
		
		
		if(StringUtils.isNotEmpty(dto.getStart_modified())){
			try {
				dto.setStart_modified(String.valueOf(this.getdate1(dto.getStart_modified()).getTime()));
			} catch (Exception e) {
				logger.error("时间戳转换出错", e);
			}
		}
		
		if(StringUtils.isNotEmpty(dto.getEnd_modified())){
			try {
				dto.setEnd_modified(String.valueOf(this.getdate1(dto.getEnd_modified()).getTime()));
			} catch (Exception e) {
				logger.error("时间戳转换出错", e);
			}
		}
		
		/**
		 * 查询出符合条件的订单数据
		 */
		//PageFinder<OrderSub> pageOrderList = orderSubDao.queryMerchantOrderList(dto);
		PageFinder<OrderSub> pageOrderList = this.queryMerchantOrderList(dto);//有待完成
		
		QueryOrderOutputDto outputDto = null;
		if(pageOrderList == null || (pageOrderList != null && pageOrderList.getData() == null) || (pageOrderList != null && pageOrderList.getData() != null && pageOrderList.getData().size() == 0)){
			outputDto = new QueryOrderOutputDto(dto.getPage_index(), dto.getPage_size(), 0);
			return outputDto;
		}
		
		outputDto = new QueryOrderOutputDto(dto.getPage_index(), dto.getPage_size(), pageOrderList.getRowCount());
		
		List<String> orderSubIds = new ArrayList<String>();
		List<String> orderConsigneeIds = new ArrayList<String>();
		List<String> orderBuyIds = new ArrayList<String>();
		for(OrderSub orderSub : pageOrderList.getData()){
			orderSubIds.add(orderSub.getId());
			orderConsigneeIds.add(orderSub.getConsigneeId());
			orderBuyIds.add(orderSub.getBuyId());
		}
		
		if(orderConsigneeIds.size() == 0 || orderBuyIds.size() == 0){
			outputDto = new QueryOrderOutputDto(dto.getPage_index(), dto.getPage_size(), 0);
			return outputDto;
		}
		
		//List<OrderConsigneeInfo> orderConsigneeInfos = orderConsigneeInfoDao.findByOrderConsigneeInfoIds(orderConsigneeIds);
		List<OrderConsigneeInfo> orderConsigneeInfos = this.findByOrderConsigneeInfoIds(orderConsigneeIds);
		
		if(orderConsigneeInfos == null || (orderConsigneeInfos != null && orderConsigneeInfos.size() == 0)){
			outputDto = new QueryOrderOutputDto(dto.getPage_index(), dto.getPage_size(), 0);
			return outputDto;
		}
		
		Map<String, OrderConsigneeInfo> orderConsigneeInfoMap = new HashMap<String, OrderConsigneeInfo>();
		for(OrderConsigneeInfo consigneeInfo : orderConsigneeInfos){
			orderConsigneeInfoMap.put(consigneeInfo.getId(), consigneeInfo);
		}
		
		//List<OrderBuyInfo> orderBuyInfos = orderBuyInfoDao.obtainOrderBuyInfoByIds(orderBuyIds);
		List<OrderBuyInfo> orderBuyInfos = this.obtainOrderBuyInfoByIds(orderBuyIds);
		
		if(orderBuyInfos == null || (orderBuyInfos != null && orderBuyInfos.size() == 0)){
			outputDto = new QueryOrderOutputDto(dto.getPage_index(), dto.getPage_size(), 0);
			return outputDto;
		}
		
		Map<String, OrderBuyInfo> orderBuyInfoMap = new HashMap<String, OrderBuyInfo>();
		for(OrderBuyInfo orderBuyInfo : orderBuyInfos){
			orderBuyInfoMap.put(orderBuyInfo.getId(), orderBuyInfo);
		}
		//List<OrderSubExpand> orderSubExpands = orderSubExpandDao.obtainOrderSubExpandByOrderSubIds(orderSubIds);
		List<OrderSubExpand> orderSubExpands = this.obtainOrderSubExpandByOrderSubIds(orderSubIds);
		Map<String, OrderSubExpand> orderSubExpandMap = new HashMap<String, OrderSubExpand>();
		for(OrderSubExpand orderSubExpand : orderSubExpands){
			orderSubExpandMap.put(orderSubExpand.getOrderSubId(), orderSubExpand);
		}
		/**
		 * 对符合条件的订单数据进行封装
		 */
		for(OrderSub orderSub :pageOrderList.getData()){
			orderSub.setOrderBuyInfo(orderBuyInfoMap.get(orderSub.getBuyId()));
			orderSub.setOrderSubExpand(orderSubExpandMap.get(orderSub.getId()));
			orderSub.setOrderConsigneeInfo(orderConsigneeInfoMap.get(orderSub.getConsigneeId()));
		}
		/**
		 * 定义返回信息
		 */
		List<QueryOrderOutputDto.Item> items = new ArrayList<QueryOrderOutputDto.Item>();
		for(OrderSub orderSub : pageOrderList.getData()){
			QueryOrderOutputDto.Item item = new QueryOrderOutputDto.Item();
			item.setId(orderSub.getId());
			item.setOrder_sub_no(orderSub.getOrderSubNo());
			item.setWarehouse_code(orderSub.getWarehouseCode());
			item.setOriginal_order_no(orderSub.getOriginalOrderNo());
			item.setOrder_status(item.getOrder_status(orderSub.getBaseStatus(), orderSub.getIsException()));
			item.setProduct_total_quantity(orderSub.getProductTotalQuantity());
			item.setMessage(orderSub.getMessage());
			item.setPayment(Payment.valueOf(orderSub.getPayment()));
			item.setLogistics_code(orderSub.getLogisticsCode());
			item.setLogistics_name(orderSub.getLogisticsName());
			item.setOrder_pay_total_amont(orderSub.getOrderPayTotalAmont());
			item.setActual_postage(orderSub.getActualPostage());
			item.setDiscount_amount(orderSub.getDiscountAmount());
			item.setCoupon_pref_amount(orderSub.getCouponPrefAmount());
			item.setCoupon_pref_amount5(orderSub.getCouponPrefAmount5());
			item.setCreate_time(orderSub.getCreateTime());
			item.setModify_time(orderSub.getModityDate());
			item.setOnline_pay_time(orderSub.getOnlinePayTime());
			item.setShip_time(orderSub.getShipTime());
			
			item.setOrderSourceNo( orderSub.getOrderSourceNo() );//Add on 20160607
			item.setPackage_no(orderSub.getPackageNo()); //唯品会JIT 拣货单号 add by zf 2016-07-11
			item.setOut_order_id(orderSub.getOutOrderId()); //唯品会JIT PO单号 add by zf 2016-07-11
			if(orderSub.getOrderConsigneeInfo() != null){
				item.setProvince(orderSub.getOrderConsigneeInfo().getProvince());
				item.setCity(orderSub.getOrderConsigneeInfo().getCity());
				item.setArea(orderSub.getOrderConsigneeInfo().getArea());
				item.setConsignee_address(orderSub.getOrderConsigneeInfo().getConsigneeAddress());
				item.setConsignee_name(orderSub.getOrderConsigneeInfo().getUserName());
				item.setConstact_phone(orderSub.getOrderConsigneeInfo().getConstactPhone());
				item.setEmail(orderSub.getOrderConsigneeInfo().getEmail());
				item.setMobile_phone(orderSub.getOrderConsigneeInfo().getMobilePhone());
				item.setZipcode(orderSub.getOrderConsigneeInfo().getZipCode());
				item.setDelivery_date_temp(DeliveryDate.valueOf(orderSub.getOrderConsigneeInfo().getDeliveryDate()));
			}else{
				logger.error("订单号：" + orderSub.getOrderSubNo() + ", OrderConsigneeInfo为空");
			}
			
			if(orderSub.getOrderBuyInfo() != null){
				item.setBuyer_name(orderSub.getOrderBuyInfo().getRealName());
				item.setMember_name(orderSub.getOrderBuyInfo().getMemberName());
			}else{
				logger.error("订单号：" + orderSub.getOrderSubNo() + ", orderBuyInfo为空");
			}
			
			item.setBuy_reduction_pref_amount(orderSub.getBuyReductionPrefAmount());//li.j1 2015-08-27 add
			List<OrderDetail4sub> orderDetails = orderSub.getOrderDetail4subs();
			List<QueryOrderDetailOutputDto.ItemDetail> itemDetails = new ArrayList<QueryOrderDetailOutputDto.ItemDetail>();
			for(OrderDetail4sub orderDetail : orderDetails){
				QueryOrderDetailOutputDto.ItemDetail itemDetail = new QueryOrderDetailOutputDto.ItemDetail();
				itemDetail.setProd_no(orderDetail.getProdNo());
				itemDetail.setProd_name(orderDetail.getProdName());
				itemDetail.setProd_unit_price(orderDetail.getProdUnitPrice());
				itemDetail.setProd_discount_amount(orderDetail.getProdDiscountAmount());
				itemDetail.setProd_total_amt(orderDetail.getProdTotalAmt());
				itemDetail.setLevel_code(orderDetail.getLevelCode());
				itemDetail.setCommodity_no(orderDetail.getCommodityNo());
				itemDetail.setCommodity_image(orderDetail.getCommodityImage());
				itemDetail.setCommodity_num(orderDetail.getCommodityNum());
				itemDetail.setCommodity_weight(orderDetail.getCommodityWeight());
				itemDetail.setCommodity_specification_str(orderDetail.getCommoditySpecificationStr());
				//added by yanglu at 2013-10-22
				itemDetail.setCommodity_type(orderDetail.getCommodityType());
				//added by zheng.qq at 2016-06-23
				itemDetail.setActivePrefAmount(orderDetail.getActivePrefAmount());
				itemDetail.setBuyReductionPrefAmount(orderDetail.getBuyReductionPrefAmount());
				itemDetail.setCouponPrefAmount(orderDetail.getCouponPrefAmount());
				itemDetail.setMemberPrefAmount(orderDetail.getMemberPrefAmount());
				itemDetail.setPaymentPrefAmount(orderDetail.getPaymentPrefAmount());
				itemDetail.setPostageCost(orderDetail.getPostageCost());
				itemDetail.setShouldPostage(orderDetail.getShouldPostage());
				itemDetails.add(itemDetail);
			}
			item.setItem_details(itemDetails);
			
			Area province = this.selectByNo(orderSub.getOrderConsigneeInfo().getProvince());
			Area city = this.selectByNo(orderSub.getOrderConsigneeInfo().getCity());
			Area area = this.selectByNo(orderSub.getOrderConsigneeInfo().getArea());
			
			if(province != null){
				item.setProvince_name(province.getName());
			}
			if(city != null){
				item.setCity_name(city.getName());
			}
			if(area != null){
				item.setArea_name(area.getName());
			}
			items.add(item);
		}
		
		outputDto.setItems(items);
		return outputDto;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------
	private Area selectByNo(String no) {
		Area area = null;
		try {
			if(StringUtils.isNotBlank(no)){
				area = areaApi.getAreaByNo(no.trim());
				return area;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
    private java.util.Date getdate1(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);
    }
    
    private Map convertBean(Object bean) {
		try {
			Class type = bean.getClass();
			Map returnMap = new HashMap();
			BeanInfo beanInfo = Introspector.getBeanInfo(type);

			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					if (result != null) {
						returnMap.put(propertyName, result);
					} else {
						returnMap.put(propertyName, "");
					}
				}
			}
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
	private OrderConsigneeInfo findByOrderConsigneeInfoId(String id) {
		OrderConsigneeInfo orderConsigneeInfo = merchantOrderDao.findByOrderConsigneeInfoId(id);
		if(orderConsigneeInfo != null){
			orderConsigneeInfo.setProvinceName(this.selectByNo(orderConsigneeInfo.getProvince()) != null ? this.selectByNo(orderConsigneeInfo.getProvince()).getName() : "");
			orderConsigneeInfo.setCityName(this.selectByNo(orderConsigneeInfo.getCity()) != null ? this.selectByNo(orderConsigneeInfo.getCity()).getName() : "");
			if(StringUtils.isEmpty(orderConsigneeInfo.getAreaName())){
				orderConsigneeInfo.setAreaName(this.selectByNo(orderConsigneeInfo.getArea()) != null ? this.selectByNo(orderConsigneeInfo.getArea()).getName() : "");
			}
		}
		return orderConsigneeInfo;
	}
	
	private List<OrderConsigneeInfo> findByOrderConsigneeInfoIds(List<String> ids) {
		List<OrderConsigneeInfo> list = null;
		if(ids != null && ids.size() > 0){
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			map.put("orderConsigneeInfoIdList", ids);
			list = merchantOrderDao.getOrderConsigneeInfoByIds(map);
		}
		List<OrderConsigneeInfo> newList  = new ArrayList<OrderConsigneeInfo>();
		if(list != null){
			for(OrderConsigneeInfo info: list){
				info.setProvinceName(this.selectByNo(info.getProvince()) != null ? this.selectByNo(info.getProvince()).getName() : "");
				info.setCityName(this.selectByNo(info.getCity()) != null ? this.selectByNo(info.getCity()).getName() : "");
				if(StringUtils.isEmpty(info.getAreaName())){
					info.setAreaName(this.selectByNo(info.getArea()) != null ? this.selectByNo(info.getArea()).getName() : "");
				}
				newList.add(info);
			}
		}
		return newList;
	}
	
	private List<OrderBuyInfo> obtainOrderBuyInfoByIds(List<String> orderBuyInfoIdList) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("orderBuyInfoIdList", orderBuyInfoIdList);
		if(orderBuyInfoIdList != null && orderBuyInfoIdList.size() > 0){
			return merchantOrderDao.getOrderBuyInfoByIds(map);
		}
		return null;
	}
	
	private List<OrderSubExpand> obtainOrderSubExpandByOrderSubIds(List<String> orderSubIdList) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("orderSubIdList", orderSubIdList);
		return merchantOrderDao.getOrderSubExpandByOrderSubIds(map);
	}
	
	private PageFinder<OrderSub> queryMerchantOrderList(QueryOrderInputDto dto){
		Map<String, String> map = this.convertBean(dto);
		Query query = new Query();
		query.setPage(dto.getPage_index());
		query.setPageSize(dto.getPage_size());
		
		List<OrderSub> datas = (List<OrderSub>) merchantOrderDao.queryMerchantOrderList(map,new RowBounds(query.getOffset(), query.getPageSize()));
		int count = merchantOrderDao.queryMerchantOrderCount(map);
		PageFinder<OrderSub> pageFinder = null;
		if(count>0) {
			pageFinder = new PageFinder<OrderSub>(query.getPage(),query.getPageSize(), count, datas);
		}
		return pageFinder;
	}
	
}