/**
 * 
 */
package com.yougou.yop.api.service;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.dto.common.Payment;
import com.yougou.dto.common.OrderEnum.DeliveryDate;
import com.yougou.dto.input.ReturnQualityQueryInputDto;
import com.yougou.dto.output.QueryOrderDetailOutputDto;
import com.yougou.dto.output.RejectionQualityOutputDto;
import com.yougou.dto.output.ReturnQA;
import com.yougou.dto.output.ReturnQADetail;
import com.yougou.dto.output.ReturnQualityOutputDto;
import com.yougou.dto.output.ReturnQualityQueryOutputDto;
import com.yougou.dto.output.QueryOrderDetailOutputDto.ItemDetail;
import com.yougou.dto.output.ReturnWarehouseOutputDto;
import com.yougou.merchant.api.asm.dao.AfterSaleMapper;
import com.yougou.merchant.api.common.Constant;
import com.yougou.merchant.api.common.UUIDGenerator;
import com.yougou.merchant.api.supplier.service.ISupplierService;
import com.yougou.merchant.api.supplier.service.impl.MerchantsApi;
import com.yougou.merchant.api.supplier.vo.SupplierVo;
import com.yougou.ordercenter.api.asm.IAsmApi;
import com.yougou.ordercenter.api.asm.IOrderForSaleApi;
import com.yougou.ordercenter.api.order.IOrderForMerchantApi;
import com.yougou.ordercenter.common.DateUtil;
import com.yougou.ordercenter.model.asm.SaleApplyBill;
import com.yougou.ordercenter.model.asm.SaleCancelGoodsInfo;
import com.yougou.ordercenter.model.order.OrderDetail4sub;
import com.yougou.ordercenter.model.order.OrderSub;
import com.yougou.ordercenter.vo.wms.WarehouseInfoVo;
import com.yougou.pc.api.ICommodityBaseApiService;
import com.yougou.pc.api.ICommodityMerchantApiService;
import com.yougou.pc.model.commodityinfo.Commodity;
import com.yougou.pc.model.product.Product;
import com.yougou.tools.cache.redis.CacheAble;
import com.yougou.wms.wpi.common.exception.WPIBussinessException;
import com.yougou.wms.wpi.expresssoc.domain.ExpressSocDomain;
import com.yougou.wms.wpi.expresssoc.service.IExpressSocDomainService;
import com.yougou.wms.wpi.rejectioninspection.domain.RejectionInspectionDetailDomain;
import com.yougou.wms.wpi.rejectioninspection.domain.RejectionInspectionDomain;
import com.yougou.wms.wpi.rejectioninspection.service.IRejectionInspectionDomainService;
import com.yougou.wms.wpi.returnqaproduct.domain.vo.ReturnQaProductDetailDomainVo;
import com.yougou.wms.wpi.returnqaproduct.domain.vo.ReturnQaProductDomainVo;
import com.yougou.wms.wpi.returnqaproduct.domain.vo.ReturnQaProductDomainVo.ReturnExceptionType;
import com.yougou.wms.wpi.returnqaproduct.domain.vo.ReturnQaProductDomainVo.ReturnStatus;
import com.yougou.wms.wpi.returnqaproduct.service.IReturnQaProductDomainService;
import com.yougou.wms.wpi.warehouse.domain.WarehouseDomain;
import com.yougou.wms.wpi.warehouse.service.IWarehouseCacheService;
import com.yougou.yop.api.IMerchantApiAsmService;
import com.yougou.yop.api.vo.RejectionQualityInputDto;
import com.yougou.yop.api.vo.ReturnQualityInputDto;

/**
 * @author huang.tao
 *
 */
@Service(value="afterSaleApi")
public class MerchantApiAsmService implements IMerchantApiAsmService {
	
	private final static Logger logger = Logger.getLogger(MerchantsApi.class);
	
	@Resource
	private AfterSaleMapper afterSaleMapper;
	@Autowired
	private IWarehouseCacheService warehouseCacheService;
	@Resource
	private ICommodityBaseApiService commodityBaseApiService;
	@Resource
	private IRejectionInspectionDomainService rejectionInspectionDomainService;
	@Resource
	private IAsmApi asmApi;
	@Resource
	private IOrderForMerchantApi orderForMerchantApi;
	@Resource
	private IReturnQaProductDomainService returnQaApi;
	@Resource
	private ICommodityMerchantApiService commodityMerchantApiService;
	@Resource
	private IExpressSocDomainService expressSocService;
	@Resource
	private ISupplierService supplierService;
	@Resource
	private IOrderForSaleApi orderForSaleApi;
	
	final static String QUALITY_RETURN_PREFIX = "saveQualityReturn ";
	
	final static String QUALITY_REJECTION_PREFIX = "saveQualityRejection ";
	
	/* (non-Javadoc)
	 * @see com.yougou.merchant.api.asm.service.IAfterSaleApi#queryReturnQualityList(com.yougou.dto.input.ReturnQualityQueryInputDto)
	 */
	@Override
	public ReturnQualityQueryOutputDto queryReturnQualityList(
			ReturnQualityQueryInputDto dto) throws Exception {
		List<ReturnQA> data = afterSaleMapper.queryReturnQualityList(dto, new RowBounds(dto.getOffset(), dto.getPage_size()));
		int count = afterSaleMapper.queryReturnQualityCount(dto);
		if (CollectionUtils.isNotEmpty(data)) {
			for (ReturnQA qualityObj : data) {
				//填充仓库名称
				if (StringUtils.isNotBlank(qualityObj.getWarehouse_name())) {
					qualityObj.setWarehouse_name(this.getWarehouseNameByCode(qualityObj.getWarehouse_name()));
				}
				
				List<ReturnQADetail> details = afterSaleMapper.queryReturnQualityDetailList(qualityObj.getReturn_id());
				if (CollectionUtils.isNotEmpty(details)) {
					for (ReturnQADetail _detail : details) {
						//订单货品
						Product product = commodityBaseApiService.getProductByNo(_detail.getProd_no(), true);
						if (null != product) {
							boolean isCommodity = (null == product.getCommodity());
							_detail.setProd_name(isCommodity ? "" : product.getCommodity().getCommodityName());
							_detail.setSupplier_code(isCommodity ? "" : product.getCommodity().getSupplierCode());
							_detail.setSale_price(isCommodity ? null : product.getCommodity().getSellPrice());
							_detail.setCommodity_specification_str(isCommodity ? product.getSizeName() : product.getCommodity().getColorName() + "," + product.getSizeName());
						}
						
						//质检货品
						Product qa_product = commodityBaseApiService.getProductByNo(_detail.getQa_prod_no(), true);
						if (null != qa_product) {
							boolean isCommodity = (null == qa_product.getCommodity());
							_detail.setQa_prod_name(isCommodity ? "" : qa_product.getCommodity().getCommodityName());
							_detail.setQa_supplier_code(isCommodity ? "" : qa_product.getCommodity().getSupplierCode());
							_detail.setQa_commodity_specification_str(isCommodity ? qa_product.getSizeName() : qa_product.getCommodity().getColorName() + "," + qa_product.getSizeName());
						}
					}
					
					qualityObj.setReturn_details(details);
				}
			}
		}
		
		ReturnQualityQueryOutputDto output = new ReturnQualityQueryOutputDto(dto.getPage_index(), dto.getPage_size(), count);
		output.setItems(data);
		return output;
	}
	
	/**
	 * 通过仓库编码获取名称
	 * 
	 * @param warehouseCode
	 * @return
	 */
	private String getWarehouseNameByCode(String warehouseCode) {
		WarehouseDomain domain = null;
		try {
			domain = warehouseCacheService.getWarehouse(warehouseCode);
		} catch (Exception e) {
			logger.error("通过仓库编码获取仓库名称异常.", e);
		}
		return null == domain ? "未知("+warehouseCode+")" : domain.getWarehouseName();
	}

	@Override
	@CacheAble(key = "'MERCHANT_RETURN_QUALITY_'+#parameterMap['merchant_code']+'_'+#parameterMap['orderNo']+'_'+#parameterMap['expressNo']+'_'+#parameterMap['expressCode']+'_'+#parameterMap['expressfee']+'_'+#parameterMap['qaDate']+'_'+#parameterMap['thirdPartyCode']", expiration = 60)
	public Object addQualityReturn(Map<String, Object> parameterMap)
			throws Exception {
		 ConvertUtils.register(new Converter() {
			@SuppressWarnings("rawtypes") 
			@Override
			public Object convert(Class arg0, Object arg1) {
		        if(arg1 == null)  
		        {  
		          return null;  
		        }  
 
		        if(arg1 instanceof String){
			        String str = (String)arg1;  
			        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			        try{  
			          return sd.parse(str);  
			        }  
			        catch(ParseException e)  
			        {  
			          throw new RuntimeException("日期格式转化异常!");  
			        } 
		        }else{
		        	return arg1;
		        }
		      } 
		},java.util.Date.class); 
		ReturnQualityInputDto dto = new ReturnQualityInputDto();
		BeanUtils.populate(dto, parameterMap);
		dto.setCashOnDelivery(MapUtils.getString(parameterMap, "cashOnDelivery"));
		dto.setExpressfee(MapUtils.getDouble(parameterMap, "expressfee"));
		dto.handleParams();
		
		String[] thirdPartyCodes = dto.getThirdPartyCode().split(",");
		String orderNo = null;
		List<Product> products = new ArrayList<Product>();
		OrderSub order = this.getOrderForOrderCenter(dto);
		if (null == order || StringUtils.isBlank(orderNo = order.getOrderSubNo())) {
			logger.info(MessageFormat.format("错误[501],接口[return.quality.add],错误信息[订单号{0}不存在],商家[{1}]",dto.getOrderNo(),dto.getMerchant_code()));
			return new Exception("订单号不存在.");
		} else if (!orderForMerchantApi.checkOrderSubIsCompleted(dto.getMerchant_code(), orderNo, null, null)) {
			logger.info(MessageFormat.format("错误[501],接口[return.quality.add],错误信息[订单号{0}没有发货信息],商家[{1}]",dto.getOrderNo(),dto.getMerchant_code()));
			return new Exception("订单还没有发货信息");
		}
		
		Date now=new Date(System.currentTimeMillis()+3600000);
		//加入时间的校验
		if(dto.getQaDate().getTime()<=order.getShipTime().getTime()||dto.getQaDate().getTime()>=now.getTime()){
			String errStr=MessageFormat.format("商家:{0} 订单: {1} 质检时间 {2} 不能<=发货时间 {3}和>=当前时间 {4}.",dto.getMerchant_code(),order.getOrderSubNo(),DateUtil.format(dto.getQaDate(),"yyyy-MM-dd HH:mm:ss"),DateUtil.format(order.getShipTime(),"yyyy-MM-dd HH:mm:ss"),DateUtil.format(now,"yyyy-MM-dd HH:mm:ss"));
			logger.error(errStr);
			return new Exception(errStr);
		}
		
		
		for (String thirdPartyCode : thirdPartyCodes) {
			Product tempProducts = null;
			if (null==(tempProducts = commodityMerchantApiService.getProductByThirdPartyCodeAndMerchantCode(thirdPartyCode.trim(), dto.getMerchant_code()))) {
				logger.info(MessageFormat.format("错误[501],接口[return.quality.add],错误信息[商家货品条码[{0}]的货品信息],商家[{1}]",thirdPartyCode,dto.getMerchant_code()));
				return new Exception(MessageFormat.format("找不到商家货品条码[{0}]的货品信息.", thirdPartyCode));
			}
			products.add(tempProducts);
		}
		if (this.calculateOrderProductCount(order) < this.calculateQualityCount(dto.getQuantity())) {
			logger.info(MessageFormat.format("错误[501],接口[return.quality.add],错误信息[订单{0}含有的货品数量{1}与质检货品数量{2}不匹配],商家[{3}]",dto.getOrderNo(), this.calculateOrderProductCount(order), this.calculateQualityCount(dto.getQuantity()), dto.getMerchant_code()));
			return new Exception("质检货品数量和订单货品数量不匹配.");
		}
		List<SaleApplyBill> saleApplyBills = asmApi.querySaleApplyBillListByOrderSubNo(orderNo);
		if (this.isExceptionReceiving(saleApplyBills, order, products)) {
			return new Exception("异常质检，您扫描的商品不属于该包裹.");
		}
		
		try {
			//通过货品条码校验货品是否已质检
			String strCheckInfo=checkInsideCodeInfo(thirdPartyCodes, dto, orderNo);
			if(StringUtils.isNotBlank(strCheckInfo)){
				logger.error(strCheckInfo);
				return new RuntimeException(strCheckInfo);
			}
			if (returnQaApi.confrimReturnQaProductByOrderSubNoDomain(orderNo)) {
				
				this.insertReturnQuality(dto, saleApplyBills, order, products);
				
				ReturnQualityOutputDto returnVo = new ReturnQualityOutputDto();
				returnVo.setResult(Boolean.toString(true));
				
				return returnVo;
			}
		} catch (WPIBussinessException e) {
			logger.error(QUALITY_RETURN_PREFIX + " -> exception: " + e.getReturnMessage());
			if ("ZL13424001".equals(e.getReturnCode())) {
				throw new RuntimeException("订单号已完成质检,不能再次质检.");
			} else if ("ZL13424002".equals(e.getReturnCode())) {
				throw new RuntimeException("订单号已做过拒收质检,不能做退换货质检登记.");
			} else {
				throw new RuntimeException(e.getReturnMessage());
			}
		}
		throw new RuntimeException("退换货质检登记失败.");
	}
	/**
	 * 通过货品条码校验货品是否已质检
	 * @return 
	 */
	private String checkInsideCodeInfo(String[] insideCodes,ReturnQualityInputDto dto,String orderNo)throws Exception{
		//** 质检数量
		String quantity=dto.getQuantity();
		//传入质检数量有误，返回不进行质检
		if(StringUtils.isNotBlank(quantity)){
			String [] quantitys=quantity.split(",");
			if(quantitys.length!=insideCodes.length){
				logger.info(quantitys.length+"quantitys");
				logger.info(insideCodes.length+"insideCodes");
				logger.warn("传入质检数量有误，返回不进行质检");
				return "传入质检数量有误，返回不进行质检";
			}
		}
		//获取要质检每个商品的数量
		Map<String, Integer> insideCodeCount = new HashMap<String, Integer>();
		for (String insideCode : insideCodes) {
			if (null != insideCodeCount.get(insideCode)) {	
				insideCodeCount.put(insideCode, insideCodeCount.get(insideCode) + 1);
			} else {
				insideCodeCount.put(insideCode, 1);
			}
		}
		
		//根据子订单号和商家编码获取订单详情信息
		OrderSub orderSub = orderForMerchantApi.findOrderSubByOrderSubNoAndMerchantCode(orderNo, dto.getMerchant_code());
		
		//已经质检过的商品
		//List<String>   checkingInsideCodes=new ArrayList<String>();
		
		// 获取已经质检过的货品条码以及数量
		Map<String, Integer> hasQA = getHasQAProductDomainByOrderNo(orderSub.getOrderSubNo());
		
		// 获取订单内所有货品条码以及数量
		Map<String, Integer> orderCommodityMap = new HashMap<String, Integer>();
		for (OrderDetail4sub orderDetail4sub : orderSub.getOrderDetail4subs()) {
			if(orderCommodityMap.containsKey(orderDetail4sub.getLevelCode())){
				Integer commodityNum=(orderCommodityMap.get(orderDetail4sub.getLevelCode()))+ orderDetail4sub.getCommodityNum();
				orderCommodityMap.put(orderDetail4sub.getLevelCode(),commodityNum);
			}else{
				orderCommodityMap.put(orderDetail4sub.getLevelCode(), orderDetail4sub.getCommodityNum());
			}
		}
		
		// 判断订单下面商品是否已经质检
		Iterator<String> it = orderCommodityMap.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			if(insideCodeCount.containsKey(key)){
				if(hasQA.containsKey(key)==false  &&  orderCommodityMap.get(key)<insideCodeCount.get(key) ){
					logger.warn("货品条码【"+key+"】可以质检数量为:"+orderCommodityMap.get(key)+"传入质检数量为 :"+ insideCodeCount.get(key)+"已经质检或超过需质检数量");
					return "货品条码【"+key+"】可以质检数量为:"+orderCommodityMap.get(key)+"传入质检数量为 :"+ insideCodeCount.get(key)+"已经质检或超过需质检数量";
				}else if (hasQA.containsKey(key) && ( orderCommodityMap.get(key)-hasQA.get(key)<insideCodeCount.get(key))) {
					logger.warn("货品条码【"+key+"】可以质检数量为:"+(orderCommodityMap.get(key)-hasQA.get(key))+"传入质检数量为 :"+ insideCodeCount.get(key)+"已经质检或超过需质检数量");
					return "货品条码【"+key+"】可以质检数量为:"+(orderCommodityMap.get(key)-hasQA.get(key))+"传入质检数量为 :"+ insideCodeCount.get(key)+"已经质检或超过需质检数量";
				}
			}
			
		}
		return "";
		
	}
	/**
     * 获取已经质检过的货品条码以及数量
     * 
     * @param orderNo
     * @return
     */
	private Map<String, Integer> getHasQAProductDomainByOrderNo(String orderNo) {
		// 获取已经质检过的货品条码以及数量
		Map<String, Integer> hasQA = new HashMap<String, Integer>();
		for (ReturnQaProductDomainVo domainVo : returnQaApi.getReturnQaProductDomainByOrderNo(orderNo)) {
			if ("已确认".equals(domainVo.getReturnStatus().getLabel())) {
				for (ReturnQaProductDetailDomainVo domain : domainVo.getDetailsList()) {
					if (null != hasQA.get(domain.getInsideCode())) {	
						hasQA.put(domain.getInsideCode(), hasQA.get(domain.getInsideCode()) + 1);
					} else {
						hasQA.put(domain.getInsideCode(), 1);
					}
				}
			}
		}
		return hasQA;
	}
	@Override
	@CacheAble(key = "'MERCHANT_REJECTION_QUALITY_'+#parameterMap['merchant_code']+'_'+#parameterMap['orderNo']+'_'+#parameterMap['expressCode']+'_'+#parameterMap['qaDate']+'_'+#parameterMap['thirdPartyCode']", expiration = 60)
	public Object addQualityRejection(Map<String, Object> parameterMap)
			throws Exception {
		 ConvertUtils.register(new Converter() {
			@SuppressWarnings("rawtypes") 
			@Override
			public Object convert(Class arg0, Object arg1) {
		        if(arg1 == null)  
		        {  
		          return null;  
		        }  

		        if(arg1 instanceof String){
			        String str = (String)arg1;  
			        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			        try{  
			          return sd.parse(str);  
			        }  
			        catch(ParseException e)  
			        {  
			          throw new RuntimeException("日期格式转化异常!");  
			        } 
		        }else{
		        	return arg1;
		        } 
		      } 
		},java.util.Date.class); 
		RejectionQualityInputDto dto = new RejectionQualityInputDto();
		BeanUtils.populate(dto, parameterMap);
		dto.setExpressfee(MapUtils.getDouble(parameterMap, "expressfee"));
		dto.handleParams();
		
		List<Product> products = new ArrayList<Product>();
		List<OrderSub> orderSubs = null;
		//放开orderNo,通过orderNo做拒收质检
		if (StringUtils.isNotBlank(dto.getOrderNo())) {
			OrderSub orderSub = orderForMerchantApi.findOrderSubByOrderSubNoAndMerchantCode(dto.getOrderNo(), dto.getMerchant_code());
			if (orderSub == null) return new Exception("输入的订单号无效,不能进去拒收质检登记.");
			
			dto.setExpressCode(orderSub.getExpressOrderId());
		}
		if (StringUtils.isBlank(dto.getExpressCode())) {
			return new Exception("请输入一个正确的快递单号或者订单号进行拒收质检.");
		}
		if (!orderForMerchantApi.checkIsSendByMerchantCodeAndExpressOrderId(dto.getMerchant_code(), dto.getExpressCode())) {
			return new Exception("订单还没有发货信息,不能进去拒收质检登记.");
		} else if (CollectionUtils.isEmpty(orderSubs = orderForMerchantApi.findOrderSubByMerchantCodeAndExpressOrderId(dto.getMerchant_code(), dto.getExpressCode()))) {
			return new Exception(MessageFormat.format("找不到收货快递单号[{0}]的订单信息.", dto.getExpressCode()));
		} /*else if (orderSubs.size() > 1) { return new BusinessServiceException("收货快递单号关联多张订单信息."); }*/
		if (this.calculateOrderProductCount(orderSubs) < this.calculateQualityCount(dto.getQuantity())) {
			logger.info(MessageFormat.format("错误[501],接口[rejection.quality.add],错误信息[快递单号{0}含有的货品数量{1}与质检货品数量{2}不匹配],商家[{3}]",dto.getExpressCode(), this.calculateOrderProductCount(orderSubs), this.calculateQualityCount(dto.getQuantity()), dto.getMerchant_code()));
			return new Exception("质检货品数量和包裹货品数量不匹配.");
		}
		
		Date now=new Date(System.currentTimeMillis()+3600000);
		for(OrderSub order:orderSubs){
			//加入时间的校验
			if(dto.getQaDate().getTime()<=order.getShipTime().getTime()||dto.getQaDate().getTime()>=now.getTime()){
				String errStr=MessageFormat.format("商家:{0} 订单 :{1} 拒收质检时间{2} 不能<=发货时间 {3}和>=当前时间 {4}.",dto.getMerchant_code(),order.getOrderSubNo(),DateUtil.format(dto.getQaDate(),"yyyy-MM-dd HH:mm:ss"),DateUtil.format(order.getShipTime(),"yyyy-MM-dd HH:mm:ss"),DateUtil.format(now,"yyyy-MM-dd HH:mm:ss"));
				logger.error(errStr);
				return new Exception(errStr);
			}
		}
		
		String[] thirdPartyCodes = dto.getThirdPartyCode().split(",");//货品条码
		for (String thirdPartyCode : thirdPartyCodes) {
			Product tempProducts = null;
			if (null==(tempProducts = commodityMerchantApiService.getProductByThirdPartyCodeAndMerchantCode(thirdPartyCode.trim(), dto.getMerchant_code()))) {
				return new Exception(MessageFormat.format("找不到商家货品条码[{0}]的货品信息.", thirdPartyCode));
			}
			products.add(tempProducts);
		}
		
		try {
			if (rejectionInspectionDomainService.queryRejectionInspectionByExpressCode(dto.getExpressCode())) {
				RejectionInspectionDomain domain = rejectionInspectionDomainService.getRejectionInspectionByExpressCode(dto.getExpressCode());
				if (null != domain && !domain.getId().equals(dto.getRejectionId())) {
					dto.setRejectionId(domain.getId());
				}
				
				//OrderSub orderSub = orderSubs.get(0);
				this.insertRejectionQuality(dto, orderSubs, products);
				
				RejectionQualityOutputDto returnVo = new RejectionQualityOutputDto();
				returnVo.setRejectionId(dto.getRejectionId());
				
				return returnVo;
			}
		} catch (WPIBussinessException e) {
			logger.error(QUALITY_REJECTION_PREFIX + " exception : " + e.getReturnMessage());
			throw new RuntimeException(e.getReturnMessage());
		}
		
		throw new RuntimeException("拒收质检登记失败.");
	}
	
	//加上分布式业务锁
	public void insertRejectionQuality(RejectionQualityInputDto dto, List<OrderSub> orders, List<Product> products) throws Exception {
		boolean isInsertDomain = true;
		if (StringUtils.isNotBlank(dto.getRejectionId()))  isInsertDomain = false;
		
		RejectionInspectionDomain domain = new RejectionInspectionDomain();		
		domain.setId(isInsertDomain ? UUIDGenerator.getUUID() : dto.getRejectionId());
		dto.setRejectionId(domain.getId());
		domain.setRejectionType(dto.getRejectionType());
		domain.setCreator(this.getSupplierNameByCode(dto.getMerchant_code()));
		domain.setCreateDate(dto.getQaDate());
		//绑定商家仓库
		Map<String, String> warehouse = supplierService.getWarehouseByMerchantCode(dto.getMerchant_code());
		//String _tempWarehouseCode = afterSaleService.getMerchantWarehouseBySupplierCode(dto.getMerchant_code());
		String warehouseCode = MapUtils.isEmpty(warehouse) ? orders.get(0).getWarehouseCode() : warehouse.get(Constant.WAREHOUSECODE);
		domain.setWarehouseId(warehouseCode);
		domain.setExpressno(dto.getExpressCode());
		domain.setExpressfee(dto.getExpressfee());
		domain.setExpressCode(dto.getExpressCode());
		domain.setSTATUS(NumberUtils.INTEGER_ZERO);
		domain.setPaytype("NO".equals(dto.getCashOnDelivery()) ? NumberUtils.INTEGER_ZERO : NumberUtils.BYTE_MINUS_ONE); //支付方式 （是否到付）
		domain.setOrderSourceNo(orders.get(0).getOrderSourceNo());
		
		List<RejectionInspectionDetailDomain> detailVos = this.buildRejectionQulityDetailVo(dto, orders, products, domain);
		domain.setRejectionQcRegisterdetailDomainVo(detailVos);
		
		if (isInsertDomain) {
			if (!rejectionInspectionDomainService.addRejectionInspection(domain)) {
				throw new RuntimeException("拒收质检登记失败.");
			}
		} else {
			if (!rejectionInspectionDomainService.addRejectionInspectionDetail(detailVos)) {
				throw new RuntimeException("拒收质检明细登记失败.");
			}
		}
		rejectionInspectionDomainService.confirmRejectionInspection(dto.getRejectionId(), dto.getMerchant_code());
	}
	//==========================================================================
	
	/**
	 * 校验订单号是否存在
	 * 
	 * @param orderNo
	 * @param merchantCode
	 * @return
	 */
	private OrderSub getOrderForOrderCenter(ReturnQualityInputDto dto) {
		// 从订单中心获取订单信息
		OrderSub order = orderForMerchantApi.findOrderSubByOrderSubNoAndMerchantCode(dto.getOrderNo(), dto.getMerchant_code());
		if (null != order) {
			return order;
		}

		List<OrderSub> originals = orderForMerchantApi.findOrderSubByMerchantCodeAndOutOrderId(dto.getMerchant_code(), dto.getOrderNo());
		if (CollectionUtils.isNotEmpty(originals)) {
			order = originals.get(0);
			return order;
		}

		return null;
	}
	
	private void insertReturnQuality(ReturnQualityInputDto dto, List<SaleApplyBill> saleApplyBills, OrderSub order, List<Product> products) throws Exception {
		List<ReturnQaProductDomainVo> domainVos = this.buildReturnQulityDetailVo(dto, saleApplyBills, order, products);
		if (CollectionUtils.isNotEmpty(domainVos)) {
			for (ReturnQaProductDomainVo domainVo : domainVos) {
				try {
					returnQaApi.addReturnQaProductDomain(domainVo);
					
					//auto confirm
					returnQaApi.confrimReturnQaProductDomain(domainVo.getId(), dto.getMerchant_code());
				} catch (WPIBussinessException e) {
					logger.error(QUALITY_RETURN_PREFIX + " -> exception: " + e.getReturnMessage());
					throw new RuntimeException("退换货质检登记失败.");
				}
			}
		} 
	}
	
	/**
	 * <p>构造退换货明细对象</p>
	 * @param dto
	 * @param saleApplyBills
	 * @param order
	 * @param product
	 * @param domainVo
	 * @return
	 * @throws Exception
	 */
	private List<ReturnQaProductDomainVo> buildReturnQulityDetailVo(ReturnQualityInputDto dto, List<SaleApplyBill> saleApplyBills, OrderSub order,
			List<Product> products)
			throws Exception {
		List<ReturnQaProductDomainVo> domainVos = new ArrayList<ReturnQaProductDomainVo>();
		//待处理申请单,货品个数
		Map<String, Integer> handleApplys = this.handleApplyNoing(saleApplyBills);
		
		String[] thirdPartyCodes = dto.getThirdPartyCode().split(",");
		String[] storageTypes = dto.getStorageType().split(",");
		String[] qaDescriptions = StringUtils.isNotBlank(dto.getQaDescription()) ? dto.getQaDescription().split(",") : null;
		String[] questionTypes = dto.getQuestionType().split(",");
		String[] quantitys = dto.getQuantity().split(",");
		
		List<ReturnQaProductDetailDomainVo> detailVos = new ArrayList<ReturnQaProductDetailDomainVo>();
		for (int i = 0; i < thirdPartyCodes.length; i++) {
			ReturnQaProductDetailDomainVo detailVo = this.buildReturnQulityCommonDetailVo(thirdPartyCodes[i].trim(), order, products, handleApplys);
			
			detailVo.setQuantity(NumberUtils.INTEGER_ONE);
			detailVo.setQaQuantity(NumberUtils.INTEGER_ONE);
			detailVo.setQuestionType(questionTypes[i]);
			detailVo.setQaDescription(qaDescriptions == null ? "" : qaDescriptions[i]);
			detailVo.setStorageType(storageTypes[i]);
			detailVo.setId(UUIDGenerator.getUUID());
			//detailVo.setReturnQaProductId(domainVo.getId());
			//TODO 待质检不通过的需求上线之后修正回来
			detailVo.setIsPassed("1");
			
			detailVos.add(detailVo);
			
			//质检货品个数大于1时要进行拆分
			int qty = Integer.valueOf(StringUtils.trimToNull(quantitys[i]));
			if (qty > 1) {
				for (int j = 1; j < qty; j++) {
					ReturnQaProductDetailDomainVo _detailVo = new ReturnQaProductDetailDomainVo();
					//BeanUtils.copyProperties(_detailVo, detailVo);
					this.copyProperties(_detailVo, detailVo);
					
					_detailVo.setId(UUIDGenerator.getUUID());
					detailVos.add(_detailVo);
				}
			}
		}
		
		if (CollectionUtils.isNotEmpty(detailVos)) {
			ReturnQaProductDomainVo domainVo = null;
			for (ReturnQaProductDetailDomainVo _detail : detailVos) {
				domainVo = new ReturnQaProductDomainVo();
				domainVo.setId(UUIDGenerator.getUUID());
				BeanUtils.copyProperties(domainVo, dto);
				domainVo.setQaDate(dto.getQaDate());
				domainVo.setQaPerson(dto.getMerchant_code());
				domainVo.setCashOnDelivery(dto.getCashOnDelivery());
				domainVo.setReturnStatus(ReturnStatus.WAIT_CONFIRM);
				domainVo.setIsException(ReturnExceptionType.NO);
				//物流信息
				ExpressSocDomain express = this.getExpressCompanyByNo(dto.getExpressNo());
				domainVo.setExpressId(null == express ? null : express.getId());
				domainVo.setExpressName(null == express ? null : express.getExpressName());
				domainVo.setExpressCharges(dto.getExpressfee());
				//绑定商家仓库
				Map<String, String> warehouse = supplierService.getWarehouseByMerchantCode(dto.getMerchant_code());
				//String _tempWarehouseCode = afterSaleService.getMerchantWarehouseBySupplierCode(dto.getMerchant_code());
				String warehouseCode = MapUtils.isEmpty(warehouse) ? order.getWarehouseCode() : warehouse.get(Constant.WAREHOUSECODE); 
				domainVo.setWarehouseCode(warehouseCode);
				
				_detail.setReturnQaProductId(domainVo.getId());
				//填充质检明细
				List<ReturnQaProductDetailDomainVo> _details = new ArrayList<ReturnQaProductDetailDomainVo>();
				_details.add(_detail);
				domainVo.setDetailsList(_details);
				
				domainVos.add(domainVo);
			}
		}
		
		return domainVos;
	}
	
	/**
	 * <p>填充退换货质检明细中的公共属性</p>
	 * 
	 * @param saleApplyBills
	 * @param order
	 * @param product
	 * @param domainVo
	 * @param handleApplys Map<申请单,待处理个数>
	 * @return
	 * @throws Exception
	 */
	private ReturnQaProductDetailDomainVo buildReturnQulityCommonDetailVo(String thirdPartyCode, OrderSub order,
			List<Product> products, Map<String, Integer> handleApplys)
			throws Exception {
		Product product = null;
		for (Product p : products) {
			if (thirdPartyCode.equals(p.getInsideCode())) {
				product = p; break;
			}
		}
		
		ReturnQaProductDetailDomainVo detailVo = new ReturnQaProductDetailDomainVo();
		
		//=========================质检货品信息===================================
		detailVo.setQaThirdPartyCode(product.getCommodity() != null ? product.getCommodity().getSupplierCode() : null);
		detailVo.setQaInsideCode(product.getInsideCode());
		detailVo.setQaProductId(product.getId());
		detailVo.setQaProductNo(product.getProductNo());
		detailVo.setQaGoodsName(product.getCommodity() != null ? product.getCommodity().getCommodityName() : null);
		//收货信息
		detailVo.setConsignee(order.getOrderConsigneeInfo().getUserName());
		detailVo.setConsigneeAddress(order.getOrderConsigneeInfo().getConsigneeAddress());
		detailVo.setConsigneePhone(order.getOrderConsigneeInfo().getMobilePhone());
		detailVo.setConsigneeTel(order.getOrderConsigneeInfo().getConstactPhone());
		detailVo.setConsigneePostcode(order.getOrderConsigneeInfo().getZipCode());
		detailVo.setMember(order.getOrderBuyInfo().getMemberName());
		detailVo.setQuestionDescription("");
		detailVo.setQuestionCause("");
		detailVo.setQuestionClassification("");
		
		detailVo.setOrderSubNo(order.getOrderSubNo());
		
		//=========================销售货品信息(申请单不为空,则填充申请单的商品信息)===================================
		if (CollectionUtils.isNotEmpty(order.getOrderDetail4subs())) {
			for (OrderDetail4sub order4Sub : order.getOrderDetail4subs()) {
				if (product.getProductNo().equals(order4Sub.getProdNo())) {
					detailVo.setProductId(order4Sub.getId());
					detailVo.setProductNo(order4Sub.getProdNo());
					detailVo.setGoodsName(order4Sub.getProdName());
					detailVo.setInsideCode(product.getInsideCode());
					detailVo.setThirdPartyCode(detailVo.getQaThirdPartyCode());
					break;
				}
			}
		}
		
		List<SaleApplyBill> saleApplyBills = asmApi.querySaleApplyBillListByOrderSubNo(order.getOrderSubNo());
		SaleApplyBill saleApplyBill = null;
		if (CollectionUtils.isNotEmpty(saleApplyBills)) {
			for (SaleApplyBill apply : saleApplyBills) {
				if (!"SALE_COMFIRM".equals(apply.getStatus()) 
						&& !"PART_SALE_QC".equals(apply.getStatus())
						&& !handleApplys.containsKey(apply.getApplyNo()))
					continue;
				
				List<SaleCancelGoodsInfo> saleCancelGoodsInfo = null;
				if (CollectionUtils.isNotEmpty(saleCancelGoodsInfo = apply.getGoodInfos())) {
					SaleCancelGoodsInfo goodsInfo = saleCancelGoodsInfo.get(0);
					if (product.getProductNo().equals(goodsInfo.getProdCode())) {
						Product p = commodityBaseApiService.getProductByNo(goodsInfo.getProdCode(), true);
						detailVo.setProductNo(goodsInfo.getProdCode());
						detailVo.setProductId(p == null ? null : p.getId());
						detailVo.setGoodsName(p == null ? null : p.getCommodity().getCommodityName());
						detailVo.setInsideCode(p == null ? null : p.getInsideCode());
						detailVo.setThirdPartyCode(p == null ? null : p.getCommodity().getSupplierCode());
						saleApplyBill = apply;
						break;
					}
				}
			}
			//强制关联申请单号
			if (saleApplyBill == null && order.getOrderDetail4subs().size() == 1) {
				for (SaleApplyBill apply : saleApplyBills) {
					if ("SALE_COMFIRM".equals(apply.getStatus()) || "PART_SALE_QC".equals(apply.getStatus())) {
						saleApplyBill = apply;
						break;
					}
				}
			}
		}
		
		//申请单不为空, 下面信息从申请单填充
		if (null != saleApplyBill) {
			//售后类型 domainVo.setReturnType(saleApplyBill.getSaleType()); 使用该字段转化
			detailVo.setOutShopName(saleApplyBill.getSaleType());
			detailVo.setApplyDate(saleApplyBill.getCreateTime());
			detailVo.setApplyNo(saleApplyBill.getApplyNo());
			if ("TRADE_GOODS".equals(saleApplyBill.getSaleType())) {//换货时,从申请单中补充收货信息
				detailVo.setConsignee(saleApplyBill.getCustomer());
				detailVo.setConsigneeAddress(saleApplyBill.getAddress());
				detailVo.setConsigneePhone(saleApplyBill.getMobilePhone());
				detailVo.setConsigneeTel(saleApplyBill.getContactPhone());
				detailVo.setConsigneePostcode(saleApplyBill.getZipCode());
			} 
			detailVo.setQuestionDescription(saleApplyBill.getRemark());
			detailVo.setMember(saleApplyBill.getLoginName());
			detailVo.setQuestionCause("");
			detailVo.setQuestionClassification("");
			
			//判断申请单是否已处理完成
			Integer commodityNum = handleApplys.get(saleApplyBill.getApplyNo());
			handleApplys.put(saleApplyBill.getApplyNo(), --commodityNum);
			if (commodityNum == 0) handleApplys.remove(saleApplyBill.getApplyNo());
		} 
		
		return detailVo;
	}
	
	/**
	 * <p>构造拒收质检明细对象</p>
	 * 
	 * @param dto
	 * @param order
	 * @param product
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	private List<RejectionInspectionDetailDomain> buildRejectionQulityDetailVo(RejectionQualityInputDto dto, List<OrderSub> orders, List<Product> products,
			RejectionInspectionDomain domain) throws Exception {
		String[] thirdPartyCodes = dto.getThirdPartyCode().split(",");
		String[] quantitys = dto.getQuantity().split(",");
		String[] questionTypes = dto.getQuestionType().split(",");
		String[] storageTypes = dto.getStorageType().split(",");
		String[] qaDescriptions = StringUtils.isBlank(dto.getQaDescription()) ? null : dto.getQaDescription().split(",");
		
		//订单货品个数
		Map<String, Integer> handleOrderMap = this.handleOrderProductNoing(orders);
		
		List<RejectionInspectionDetailDomain> detailVos = new ArrayList<RejectionInspectionDetailDomain>();
		RejectionInspectionDetailDomain detailVo = null;
		for (int i = 0; i < thirdPartyCodes.length; i++) {
			detailVo = new RejectionInspectionDetailDomain();
			Product product = null;
			for (Product temp : products) {
				if (thirdPartyCodes[i].trim().equals(temp.getInsideCode())) {
					product = temp;
					break;
				}
			}
			
			detailVo.setId(UUIDGenerator.getUUID());
			detailVo.setRejectionId(domain.getId());
			detailVo.setSupplierCode(thirdPartyCodes[i].trim());
			detailVo.setCommodityId(product.getId());
			detailVo.setCommodityCode(product.getProductNo());
			detailVo.setGoodsName(product.getCommodity().getCommodityName());
			detailVo.setSpecification(product.getSizeName());
			detailVo.setInvitemno(product.getCommodity().getSupplierCode());
			detailVo.setQuantity(NumberUtils.INTEGER_ONE);
			detailVo.setDescr(null == qaDescriptions ? null : qaDescriptions[i]);
			// detailVo.setProblemDue(vo.getProblemDue());
			// detailVo.setProblemReason(vo.getProblemReason());
			detailVo.setProblemType(this.getQuestionType(questionTypes[i].trim()));
			detailVo.setStorageType(storageTypes[i].trim());
			// detailVo.setUnits(vo.getUnits());
			//关联子订单号
			detailVo.setOrderSubNo(this.getNotRelateOrderNo(orders, product.getProductNo(), handleOrderMap));
			detailVos.add(detailVo);
			
			//质检货品个数大于1时要进行拆分
			int qty = Integer.valueOf(StringUtils.trimToNull(quantitys[i]));
			if (qty > 1) {
				RejectionInspectionDetailDomain _detailVo = null;
				for (int j = 1; j < qty; j++) {
					_detailVo = new RejectionInspectionDetailDomain();
					BeanUtils.copyProperties(_detailVo, detailVo);
					
					_detailVo.setId(UUIDGenerator.getUUID());
					_detailVo.setOrderSubNo(this.getNotRelateOrderNo(orders, product.getProductNo(), handleOrderMap));
					detailVos.add(_detailVo);
				}
			}
		}
		
		return detailVos;
	}
	
	/**
	 * <p>通过发货快递公司no获取其Id值</p>
	 * 
	 * @param expressNo 发货快递公司No
	 * @return
	 * @throws Exception
	 */
	private ExpressSocDomain getExpressCompanyByNo(String expressNo) throws Exception {
		ExpressSocDomain express = null;
		try {
			express = expressSocService.getExpressSocDomainByExpressNo(expressNo);
		} catch (WPIBussinessException e) {
			logger.error(QUALITY_RETURN_PREFIX + " getExpressCompanyByNo -> exception: " + e.getReturnMessage());
			throw new RuntimeException("系统内部异常, 请稍后再试.");
		}
		if (null == express) {
			throw new RuntimeException("没有找到[expressNo|"+expressNo+"]相关的值,请你核对后再输入.");
		}
		
		return express;
	}
	
	private String getSupplierNameByCode(String merchant_code) throws Exception {
		SupplierVo supplier = supplierService.getSupplierByMerchantCode(merchant_code);
		return null == supplier ? merchant_code : supplier.getSupplier();
	}
	
	private String getQuestionType(String questionType) {
		if ("BAD".equals(questionType)) {
			return "质量问题";
		}
		return "非质量问题";
	}
	
	/**
	 * <p>判断是否异常收货</p>
	 * @param saleApplyBills
	 * @param order
	 * @param thirdPartyCodes
	 * @return
	 */
	private boolean isExceptionReceiving(List<SaleApplyBill> saleApplyBills, OrderSub order, List<Product> products) {
		boolean _isExce = true;
		for (Product product : products) {
			if (StringUtils.isBlank(product.getProductNo())) continue;
			
			for (OrderDetail4sub detail4sub : order.getOrderDetail4subs()) {
				if (product.getProductNo().equals(detail4sub.getProdNo())) {
					_isExce = false;
					break;
				}
			}
			
			for (SaleApplyBill apply : saleApplyBills) {
				if (!"SALE_COMFIRM".equals(apply.getStatus()) && !"PART_SALE_QC".equals(apply.getStatus())) continue;
				
				List<SaleCancelGoodsInfo> goodsInfos = apply.getGoodInfos();
				if (CollectionUtils.isNotEmpty(goodsInfos)) {
					for (SaleCancelGoodsInfo goodsInfo : goodsInfos) {
						if (product.getProductNo().equals(goodsInfo.getProdCode())) {
							_isExce = false;
							break;
						}
					}
				}
			}
			if (_isExce) return true;
		}
		return false;
	}
	
	/**
	 * 申请单处理状态
	 * Map<申请单号, 待处理货品个数>
	 * 
	 * @param saleApplyBills
	 * @return 
	 */
	private Map<String, Integer> handleApplyNoing(List<SaleApplyBill> saleApplyBills) {
		Map<String, Integer> handleApplyMap = new HashMap<String, Integer>();
		if (CollectionUtils.isNotEmpty(saleApplyBills)) {
			for (SaleApplyBill apply : saleApplyBills) {
				if (!"SALE_COMFIRM".equals(apply.getStatus()) && !"PART_SALE_QC".equals(apply.getStatus())) continue;
				
				List<SaleCancelGoodsInfo> goodsInfos = apply.getGoodInfos();
				if (CollectionUtils.isNotEmpty(goodsInfos)) {
					SaleCancelGoodsInfo goodsInfo = goodsInfos.get(0);
					handleApplyMap.put(apply.getApplyNo(), goodsInfo.getCommodityNum());
				}
			}
		}
		
		return handleApplyMap;
	}
	
	/**
	 * 多包裹订单货品关联
	 * 
	 * @param orders
	 * @return [OT123456789_1;99814743003, 2]
	 */
	private Map<String, Integer> handleOrderProductNoing(List<OrderSub> orders) {
		Map<String, Integer> handleOrderMap = new HashMap<String, Integer>();
		if (CollectionUtils.isNotEmpty(orders)) {
			for (OrderSub _order : orders) {
				List<OrderDetail4sub> _details = _order.getOrderDetail4subs();
				if (CollectionUtils.isEmpty(_details)) continue;
				
				for (OrderDetail4sub _detail : _details) {
					handleOrderMap.put(_order.getOrderSubNo() + ";" + _detail.getProdNo(), _detail.getCommodityNum());
				}
			}
		}
		
		return handleOrderMap;
	}
	
	/**
	 * 拒收质检时，通过货品编码获取未关联的订单号
	 * 
	 * @param orders
	 * @param productNo
	 * @param handleOrderMap
	 * @return
	 */
	private String getNotRelateOrderNo(List<OrderSub> orders, String productNo, Map<String, Integer> handleOrderMap) {
		String orderSubNo = null;
		
		for (OrderSub _order : orders) {
			String key_temp = _order.getOrderSubNo() + ";" + productNo;
			if (!handleOrderMap.containsKey(key_temp))
				continue;
			orderSubNo = _order.getOrderSubNo();
			Integer commodityNum = handleOrderMap.get(key_temp);
			handleOrderMap.put(key_temp, --commodityNum);
			if (commodityNum == 0) handleOrderMap.remove(key_temp);
			break;
		}
		
		return StringUtils.isNotBlank(orderSubNo) ? orderSubNo : orders.get(0).getOrderSubNo();
	}
	
	/**
	 * 计算订单包含的货品数量
	 * 
	 * @param order
	 * @return
	 */
	private int calculateOrderProductCount(OrderSub order) {
		List<OrderSub> _orders = new ArrayList<OrderSub>();
		_orders.add(order);
		return this.calculateOrderProductCount(_orders);
	}
	
	/**
	 * 计算订单集合包含的货品数量
	 * 
	 * @param orders
	 * @return
	 */
	private int calculateOrderProductCount(List<OrderSub> orders) {
		if (CollectionUtils.isEmpty(orders)) return 0;
		
		int count = 0;
		for (OrderSub orderSub : orders) {
			List<OrderDetail4sub> details = orderSub.getOrderDetail4subs();
			for (OrderDetail4sub _detail4sub : details) {
				count += _detail4sub.getCommodityNum();
			}
		}
		
		return count;
	}
	
	/**
	 * 计算质检货品数量
	 * 
	 * @param quantity
	 * @return
	 */
	private int calculateQualityCount(String quantity) {
		if (StringUtils.isBlank(quantity)) return 0;
		
		int count = 0;
		String[] qtys = quantity.split(",");
		for (String qty : qtys) {
			count += Integer.valueOf(qty);
		}
		
		return count;
	}
	
	private void copyProperties(ReturnQaProductDetailDomainVo dest, ReturnQaProductDetailDomainVo orig) {
		if (orig == null) return;
		
		if (dest == null) dest = new ReturnQaProductDetailDomainVo();
		
		dest.setApplyDate(orig.getApplyDate());
		dest.setApplyDetailId(orig.getApplyDetailId());
		dest.setApplyNo(orig.getApplyNo());
		dest.setConsignee(orig.getConsignee());
		dest.setConsigneeAddress(orig.getConsigneeAddress());
		dest.setConsigneePhone(orig.getConsigneePhone());
		dest.setConsigneePostcode(orig.getConsigneePostcode());
		dest.setConsigneeTel(orig.getConsigneeTel());
		dest.setDefectiveType(orig.getDefectiveType());
		dest.setGoodsName(orig.getGoodsName());
		dest.setId(orig.getId());
		dest.setInsideCode(orig.getInsideCode());
		dest.setIsPassed(orig.getIsPassed());
		dest.setMember(orig.getMember());
		dest.setMobilePhone(orig.getMobilePhone());
		dest.setOrderCode(orig.getOrderCode());
		dest.setOrderSourceNo(orig.getOrderSourceNo());
		dest.setOrderSubNo(orig.getOrderSubNo());
		dest.setOutOrderId(orig.getOutOrderId());
		dest.setOutShopName(orig.getOutShopName());
		dest.setProductId(orig.getProductId());
		dest.setProductNo(orig.getProductNo());
		dest.setQaApplyDate(orig.getQaApplyDate());
		dest.setQaDescription(orig.getQaDescription());
		dest.setQaGoodsName(orig.getQaGoodsName());
		dest.setQaInsideCode(orig.getQaInsideCode());
		dest.setQaProductId(orig.getQaProductId());
		dest.setQaProductNo(orig.getQaProductNo());
		dest.setQaQuantity(orig.getQaQuantity());
		dest.setQaThirdPartyCode(orig.getQaThirdPartyCode());
		dest.setQuantity(orig.getQuantity());
		dest.setQuestionCause(orig.getQuestionCause());
		dest.setQuestionClassification(orig.getQuestionClassification());
		dest.setQuestionDescription(orig.getQuestionDescription());
		dest.setQuestionType(orig.getQuestionType());
		dest.setReturnQaProductId(orig.getReturnQaProductId());
		dest.setStorageType(orig.getStorageType());
		dest.setThirdPartyCode(orig.getThirdPartyCode());
		dest.setWarehouseCode(orig.getWarehouseCode());
	}

	@Override
	public Object getReturnWarehouse(String order_sub_no) throws Exception {
		// TODO Auto-generated method stub
		WarehouseInfoVo vo = orderForSaleApi.getReturnWarehouseInfo(order_sub_no);
		return this.convert(vo);
	}
	
	/**
	 * 转化招商API退货地址信息对象
	 * 
	 * @param dto
	 * @return
	 */
	private ReturnWarehouseOutputDto convert(WarehouseInfoVo warehouseInfoVo) {
		if (null == warehouseInfoVo) return null;
		
		ReturnWarehouseOutputDto output = new ReturnWarehouseOutputDto();
		output.setMobilePhone(warehouseInfoVo.getMobilePhone());
		output.setReceiver(warehouseInfoVo.getReceiver());
		output.setTelPhone(warehouseInfoVo.getTelPhone());
		output.setWarehouseAddress(warehouseInfoVo.getWarehouseAddress());
		output.setWarehouseName(warehouseInfoVo.getWarehouseName());
		output.setZipCode(warehouseInfoVo.getZipCode());
		
		return output;
	}
}
