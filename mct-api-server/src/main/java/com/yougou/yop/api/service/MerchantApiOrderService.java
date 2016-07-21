/**
 * 
 */
package com.yougou.yop.api.service;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.yougou.api.exception.YOPRuntimeException;
import com.yougou.dto.common.OrderEnum.DeliveryDate;
import com.yougou.dto.common.Payment;
import com.yougou.dto.input.OrderExceptionInputDto;
import com.yougou.dto.output.CancelOrModifyOrderForMerchantVo;
import com.yougou.dto.output.QueryOrderCanceledOutputDto;
import com.yougou.dto.output.QueryOrderDetailOutputDto;
import com.yougou.dto.output.QueryOrderDetailOutputDto.ItemDetail;
import com.yougou.dto.output.QueryOrderOutputDto;
import com.yougou.dto.output.UpdateOrderStatusOutputDto;
import com.yougou.merchant.api.order.service.OrderSubService;
import com.yougou.merchant.api.supplier.service.ISupplierService;
import com.yougou.merchant.api.supplier.vo.SupplierVo;
import com.yougou.ordercenter.api.order.IOrderApi;
import com.yougou.ordercenter.api.order.IOrderForMerchantApi;
import com.yougou.ordercenter.common.DateUtil;
import com.yougou.ordercenter.common.OrderPropertiesEnum;
import com.yougou.ordercenter.constant.OrderConstant;
import com.yougou.ordercenter.model.order.OrderDeliveryInfo;
import com.yougou.ordercenter.model.order.OrderDetail4sub;
import com.yougou.ordercenter.model.order.OrderDetailDeliveryInfo;
import com.yougou.ordercenter.model.order.OrderProperties;
import com.yougou.ordercenter.model.order.OrderSub;
import com.yougou.ordercenter.vo.merchant.input.MerchantOrderOutStoreVo;
import com.yougou.ordercenter.vo.merchant.input.QueryOrderInputDto;
import com.yougou.ordercenter.vo.order.OrderExceptionVo;
import com.yougou.outside.api.domain.DeliveryInfo;
import com.yougou.pc.api.ICommodityBaseApiService;
import com.yougou.pc.model.commodityinfo.Commodity;
import com.yougou.wms.wpi.logisticscompany.service.ILogisticsCompanyDomainService;
import com.yougou.yop.api.IMerchantApiOrderService;

/**
 * @author huang.tao
 *
 */
@Service(value="merchantOrderApi")
public class MerchantApiOrderService implements IMerchantApiOrderService {

	@Resource
	private IOrderForMerchantApi orderForMerchantApi;
	
	@Resource
	private ISupplierService supplierApi;
	
	@Resource
	private IOrderApi orderApi;
	
	@Resource 
	private OrderSubService orderSubService;
	
    @Resource
    private ICommodityBaseApiService commodityBaseApi;
    
    @Resource
	private ILogisticsCompanyDomainService logisticsCompanyService;
    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    private static final String API_CACHE_ORDER_OUTSTORE = "api_cache_order_outstore_";
    private static final Log logger = LogFactory.getLog(MerchantApiOrderService.class);
	
	@Override
	public Object queryOrder(Map<String, Object> parameterMap) throws Exception {
		QueryOrderOutputDto result = null;
		//Object obj = orderForMerchantApi.queryOrder(parameterMap);
		QueryOrderInputDto dto = new QueryOrderInputDto();
		BeanUtils.populate(dto, parameterMap);
		com.yougou.ordercenter.vo.merchant.output.QueryOrderOutputDto outputDto = orderSubService.getMerchantOrderList(dto);
		if (outputDto != null) {
			result = this.convert(outputDto);
		}
		return result;
	}

	@Override
	public Object queryIncrementOrder(Map<String, Object> parameterMap) throws Exception {
		QueryOrderOutputDto result = null;
		//Object obj = orderForMerchantApi.queryOrder(parameterMap);
		QueryOrderInputDto dto = new QueryOrderInputDto();
		BeanUtils.populate(dto, parameterMap);
		com.yougou.ordercenter.vo.merchant.output.QueryOrderOutputDto outputDto = null;
		try {
			outputDto = orderSubService.getMerchantOrderList(dto);
		} catch (Exception e) {
			logger.error("orderSubService.getMerchantOrderList", e);
			throw e;
		}
		if (outputDto != null) {
			try {
				result = this.convert(outputDto);
			} catch (Exception e) {
				logger.error("convert(outputDto)", e);
				throw e;
			}
		}
		return result;
	}

	@Override
	public Object getOrder(Map<String, Object> parameterMap) throws Exception {
		com.yougou.ordercenter.vo.merchant.input.QueryOrderInputDto _dto = new com.yougou.ordercenter.vo.merchant.input.QueryOrderInputDto();
		BeanUtils.populate(_dto, parameterMap);
		List<com.yougou.ordercenter.vo.merchant.output.QueryOrderDetailOutputDto> list = orderSubService.getOrderDetail(_dto);
		
		com.yougou.ordercenter.vo.merchant.output.QueryOrderDetailOutputDto dto = CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
		return this.convert(dto);
	}

	@Override
	public Object queryCanceledOrder(Map<String, Object> parameterMap)
			throws Exception {
		com.yougou.ordercenter.vo.merchant.input.QueryOrderInputDto _dto = new com.yougou.ordercenter.vo.merchant.input.QueryOrderInputDto();
		BeanUtils.populate(_dto, parameterMap);
		
		List<com.yougou.ordercenter.vo.merchant.output.QueryOrderCanceledOutputDto> _list = orderSubService.queryMerchantOrderCanceledList(_dto);
		List<QueryOrderCanceledOutputDto> list = new ArrayList<QueryOrderCanceledOutputDto>();
		if (CollectionUtils.isNotEmpty(_list)) {
			QueryOrderCanceledOutputDto dto = null;
			for (com.yougou.ordercenter.vo.merchant.output.QueryOrderCanceledOutputDto queryOrderCanceledOutputDto : _list) {
				dto = new QueryOrderCanceledOutputDto();
				dto.setModify_time(queryOrderCanceledOutputDto.getModify_time());
				dto.setOrder_sub_no(queryOrderCanceledOutputDto.getOrder_sub_no());
				list.add(dto);
			}
		}
		
		return list;
	}

	@Override
	public Object updateNondeliveryOrder(Map<String, Object> parameterMap)
			throws Exception {
		String merchantCode = MapUtils.getString(parameterMap, "merchant_code");
		String orderSubNos = MapUtils.getString(parameterMap, "order_sub_nos");
		List<String> orderSubNoList = Arrays.asList(StringUtils.split(orderSubNos, ","));
		List<String> failure = orderForMerchantApi.orderNoDeliveryForMerchant(orderSubNoList, merchantCode);
		return CollectionUtils.isNotEmpty(failure) ? Boolean.FALSE : Boolean.TRUE;
	}

	@Override
	public Object updateStockoutOrder(Map<String, Object> parameterMap)
			throws Exception {
		String merchantCode = MapUtils.getString(parameterMap, "merchant_code");
		SupplierVo supplier = supplierApi.getSupplierByMerchantCode(merchantCode);
		OrderExceptionInputDto dto = new OrderExceptionInputDto();
		BeanUtils.populate(dto, parameterMap);
		OrderSub orderSub = orderApi.getOrderSubByOrderSubNo(dto.getOrder_sub_no(), 1);
		Map<String, String> failure=new HashMap<String, String>();
		if(orderSub!=null){
			if(orderSub.getBaseStatus()==3){
				//已挂起
				throw new RuntimeException("该订单状态为已挂起，不允许置为缺货，请重新获取订单状态!");
			}else if(orderSub.getBaseStatus()==4){
				//已完成
				throw new RuntimeException("该订单状态为已完成，不允许置为缺货，请重新获取订单状态!");
			}else if(orderSub.getBaseStatus()==5){
				//已作废
				throw new RuntimeException("该订单状态为已作废，不允许置为缺货，请重新获取订单状态!");
			}else if(orderSub.getBaseStatus()==6){
				//客服修改
				throw new RuntimeException("该订单状态为客服修改状态，不允许置为缺货，请重新获取订单状态!");
			}else{
				dto.setIs_exception(OrderConstant.EXCEPTION_WAREHOUSE_ERROR);
				List<OrderExceptionVo> list = new ArrayList<OrderExceptionVo>();
				OrderExceptionVo exceptionVo = new OrderExceptionVo();
				exceptionVo.setOperateUser(null == supplier ? merchantCode : supplier.getSupplier());
				exceptionVo.setOrderCode(dto.getOrder_sub_no());
				exceptionVo.setRemark(dto.getRemark());
				exceptionVo.setStatus(dto.getIs_exception());
				List<OrderDetail4sub> orderDetail4subList=orderSub.getOrderDetail4subs();
				List<String> orderDetail4subIDs=new ArrayList<String>();
				//api暂时只支持整单缺货
				for(OrderDetail4sub orderDetail4sub:orderDetail4subList){
					orderDetail4subIDs.add(orderDetail4sub.getId());
				}
				exceptionVo.setOrderDetailIds(orderDetail4subIDs);
				list.add(exceptionVo);
				failure = orderForMerchantApi.orderExceptionForMerchant(list, dto.getMerchant_code());
			}
		}else{
			throw new RuntimeException("商家"+merchantCode+"的订单编号:"+dto.getOrder_sub_no()+"在系统中不存在,请检查!");
		}
		return MapUtils.isNotEmpty(failure) ? Boolean.FALSE : Boolean.TRUE;
	}
	
    @Override
    public Boolean orderOutStoreForMerchant(String merchantCode, String orderSubNo, String logisticsCompanyCode, String expressCode, Date outStoreDate) throws Exception {
    	Boolean success = false;
        Boolean runflag = false;
        String checkResult = "";
        Boolean creatflag = false;
        orderSubNo=orderSubNo.trim();
        
        //商家发货时间用当前操作的时间，参见：http://112.95.151.185:88/redmine/issues/16709
        outStoreDate = new Date();
        try {
            if (redisTemplate.hasKey(API_CACHE_ORDER_OUTSTORE + merchantCode)) {
                creatflag = true;
            }
            if (!redisTemplate.opsForHash().hasKey(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo)) {
                redisTemplate.opsForHash().put(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo, "0");
                if (!creatflag) {
                    // 第一次建key的时候设置生存期当天的23:59:59
                    redisTemplate.opsForHash().put(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo, "0");
                    String temp = DateUtil.format(new Date(), "yyyy-MM-dd");
                    
                    redisTemplate.expireAt(API_CACHE_ORDER_OUTSTORE + merchantCode, format1.parse(temp + " 23:59:59"));
                }
                
                // 从订单中心获取订单信息
                OrderSub order = orderForMerchantApi.findOrderSubByOrderSubNoAndMerchantCode(orderSubNo, merchantCode);
        		if (null == order) {
        			List<OrderSub> originals = orderForMerchantApi.findOrderSubByMerchantCodeAndOutOrderId(merchantCode, orderSubNo);
            		if (CollectionUtils.isNotEmpty(originals)) {
            			order = originals.get(0);
            		}
        		}
        		Date now=new Date(System.currentTimeMillis()+3600000);
        		//加入时间的校验
        		if(outStoreDate.getTime()<=order.getVerifyTime().getTime()||outStoreDate.getTime()>=now.getTime()){
        			 runflag = true;
     				String errStr=MessageFormat.format("商家：{0} 订单 :{1} 发货时间{2} 不能<=下单审核时间 {3}和>=当前时间 {4}.",merchantCode,orderSubNo,DateUtil.format(outStoreDate,"yyyy-MM-dd HH:mm:ss"),DateUtil.format(order.getVerifyTime(),"yyyy-MM-dd HH:mm:ss"),DateUtil.format(now,"yyyy-MM-dd HH:mm:ss"));
    				logger.error(errStr);
        			 throw new YOPRuntimeException("20140902",errStr);
        		}

                // 对订单发货状态做校验
                runflag = true;
                checkResult = orderForMerchantApi.checkOrderIsCanSend(orderSubNo);
                if ("true".equals(checkResult)) {
                    logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "出库中，订单号：" + orderSubNo + "快递单号：" + expressCode.trim() + "物流公司编码：" + logisticsCompanyCode);
                    success = orderForMerchantApi.orderOutStoreForMerchant(orderSubNo, logisticsCompanyCode.trim(), expressCode.trim(), outStoreDate);
                    if (success) {
                        redisTemplate.opsForHash().put(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo, "1");
                        logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "发货成功！");
                        return true;
                    } else {
                        redisTemplate.opsForHash().delete(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo);
                        logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "发货失败！");
                        return false;
                    }
                } else if ("该订单已被置为【已完成】状态，请不要做发货处理".equals(checkResult)) {
                    redisTemplate.opsForHash().put(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo, "1");
                    logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "已经发过货，请勿重复发货！");
                } else {
                    redisTemplate.opsForHash().delete(API_CACHE_ORDER_OUTSTORE + merchantCode,orderSubNo);
                    logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "状态校验未通过，订单校验状态接口返回：" + checkResult);
                }
            } else if ("1".equals(redisTemplate.opsForHash().get(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo).toString())) {
                runflag = true;
                logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "已经发过货，请勿重复发货！");
                return true;
            } else {
                runflag = true;
                logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "正在发货，请勿重复发货！");
                return false;
            }
        } catch (RedisConnectionFailureException e) {
            logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "在发货时遇到redis连接错误", e);
            throw new RuntimeException("商家：" + merchantCode + "的订单：" + orderSubNo + "在发货时遇到redis连接错误,请稍后再试");
        } catch (YOPRuntimeException e) {
            redisTemplate.opsForHash().delete(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "在发货时异常,订单校验状态接口返回：" + checkResult, e);
            if (!"true".equals(checkResult) || !success) {
                redisTemplate.opsForHash().delete(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo);
                throw new RuntimeException("商家：" + merchantCode + "的订单：" + orderSubNo + "在发货时异常！");
            }
        } finally {
            if (!runflag) {
                checkResult = orderForMerchantApi.checkOrderIsCanSend(orderSubNo);
                logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "在尚未发货就发生异常,订单校验状态接口返回：" + checkResult + ",现强制走发货流程。");
                if (!"true".equals(checkResult)) {
                    throw new RuntimeException(checkResult);
                }
                success = orderForMerchantApi.orderOutStoreForMerchant(orderSubNo, logisticsCompanyCode.trim(), expressCode.trim(), outStoreDate);
            }
        }
        return success;
    }
    
    
    public Boolean orderOutStoreForMerchant(String orderSubNo, String logisticsCompanyCode, String expressCode, Date outStoreDate) throws Exception {
        // 对订单发货状态做校验
        String checkResult = orderForMerchantApi.checkOrderIsCanSend(orderSubNo.trim());
        if (!"true".equals(checkResult))
            throw new Exception(checkResult);

        return orderForMerchantApi.orderOutStoreForMerchant(orderSubNo.trim(), logisticsCompanyCode.trim(), expressCode.trim(), outStoreDate);
    }

	@Override
	public Object updateSynchronizedOrder(Map<String, Object> parameterMap)
			throws Exception {
		String merchantCode = MapUtils.getString(parameterMap, "merchant_code");
		String[] orderSubNos = StringUtils.split(MapUtils.getString(parameterMap, "order_sub_nos"), ",");
		List<com.yougou.ordercenter.vo.merchant.output.UpdateOrderStatusOutputDto> _list = orderForMerchantApi.updateOrderToPicking(Arrays.asList(orderSubNos), merchantCode);
		
		List<UpdateOrderStatusOutputDto> list = new ArrayList<UpdateOrderStatusOutputDto>();
		if (CollectionUtils.isNotEmpty(_list)) {
			UpdateOrderStatusOutputDto dto = null;
			for (com.yougou.ordercenter.vo.merchant.output.UpdateOrderStatusOutputDto _dto : _list) {
				dto = new UpdateOrderStatusOutputDto();
				dto.setMessage(_dto.getMessage());
				dto.setOrderSubNo(_dto.getOrderSubNo());
				dto.setSuccess(_dto.isSuccess());
				
				list.add(dto);
			}
		}
		
		return list;
	}

	@Override
	public Object queryInterceptOrder(Map<String, Object> parameterMap)
			throws Exception {
		com.yougou.ordercenter.vo.merchant.output.CancelOrModifyOrderForMerchantVo _cancelOrder=orderForMerchantApi.getModifiedOrCanceledOrder(MapUtils.getString(parameterMap, "order_sub_no"));
		CancelOrModifyOrderForMerchantVo vo = null;
		if (null != _cancelOrder) {
			vo = new CancelOrModifyOrderForMerchantVo();
			vo.setChangeType(_cancelOrder.getChangeType());
			vo.setContent(_cancelOrder.getContent());
			vo.setInterceptDate(_cancelOrder.getInterceptDate());
			vo.setMerchantCode(_cancelOrder.getMerchantCode());
			vo.setOrderSubNo(_cancelOrder.getOrderSubNo());
		}
		
		return vo;
	}

	@Override
	public Object updateInterceptOrder(Map<String, Object> parameterMap)
			throws Exception {
		String return_type= MapUtils.getString(parameterMap, "return_type");
		String order_sub_no = MapUtils.getString(parameterMap, "order_sub_no");
		if("1".equals(return_type)){
			return orderForMerchantApi.orderNoDelivery(Arrays.asList(order_sub_no)).size()==0?true:false;
		}else if("2".equals(return_type)){
			return orderForMerchantApi.interceptNotOkCallBack(Arrays.asList(order_sub_no));
		}
		return false;
	}

	/**
	 * 转化招商订单API输出对象
	 * 
	 * @param dto 订单dto
	 * @return 
	 */
	private QueryOrderOutputDto convert(com.yougou.ordercenter.vo.merchant.output.QueryOrderOutputDto dto) {
		QueryOrderOutputDto result = new QueryOrderOutputDto(dto.getPage_index(), dto.getPage_size(), dto.getTotal_count());
			
		List<com.yougou.dto.output.QueryOrderOutputDto.Item> items = new ArrayList<com.yougou.dto.output.QueryOrderOutputDto.Item>();
		com.yougou.dto.output.QueryOrderOutputDto.Item item = null;
        Commodity c = null;
		if (CollectionUtils.isNotEmpty(dto.getItems())) {
			for (com.yougou.ordercenter.vo.merchant.output.QueryOrderOutputDto.Item _item : dto.getItems()) {
				item = new com.yougou.dto.output.QueryOrderOutputDto.Item();
				item.setActual_postage(_item.getActual_postage());
				item.setArea(_item.getArea());
				item.setArea_name(_item.getArea_name());
				item.setBuyer_name(_item.getBuyer_name());
				item.setCity(_item.getCity());
				item.setCity_name(_item.getCity_name());
				item.setConsignee_address(_item.getConsignee_address());
				item.setConsignee_name(_item.getConsignee_name());
				item.setConstact_phone(_item.getConstact_phone());
				item.setCoupon_pref_amount(_item.getCoupon_pref_amount());
				item.setCoupon_pref_amount5(_item.getCoupon_pref_amount5());
				item.setCreate_time(_item.getCreate_time());
				item.setDelivery_date(_item.getDelivery_date());
				item.setDelivery_date_temp(DeliveryDate.getDeliveryDateEnum(_item.getDelivery_date_temp().getName()));
				item.setDiscount_amount(_item.getDiscount_amount());
				item.setEmail(_item.getEmail());
				item.setId(_item.getId());
				item.setLogistics_code(_item.getLogistics_code());
				item.setLogistics_name(_item.getLogistics_name());
				item.setMember_name(_item.getMember_name());
				item.setMessage(_item.getMessage());
				item.setMobile_phone(_item.getMobile_phone());
				item.setModify_time(_item.getModify_time());
				item.setOnline_pay_time(_item.getOnline_pay_time());
				item.setOrder_pay_total_amont(_item.getOrder_pay_total_amont());
				item.setOrder_status(_item.getOrder_status());
				item.setOrder_sub_no(_item.getOrder_sub_no());
				item.setOriginal_order_no(_item.getOriginal_order_no());
				item.setOut_order_id(_item.getOut_order_id());
				item.setPayment(Payment.getPaymentEnum(_item.getPayment().getName()));
				item.setProduct_total_quantity(_item.getProduct_total_quantity());
				item.setProvince(_item.getProvince());
				item.setProvince_name(_item.getProvince_name());
				item.setShip_time(_item.getShip_time());
				item.setWarehouse_code(_item.getWarehouse_code());
				item.setZipcode(_item.getZipcode());
				item.setBuy_reduction_pref_amount(_item.getBuy_reduction_pref_amount());//li.j1 2015-08-27 add
				item.setOrder_source_no( _item.getOrderSourceNo() );//luo.q 2016-06-12 add
				//=========唯品会JIT新增字段start zf 2016-7-9 ========
				item.setPackage_no(_item.getPackage_no());// 拣货单号				
				List<OrderProperties> orderProps=orderForMerchantApi.getOrderPropertiesList(_item.getOrder_sub_no(), null);
				if(orderProps != null && orderProps.size() >0){
					for(OrderProperties orderPropertie: orderProps){
						String key = orderPropertie.getKey();
						String value = orderPropertie.getValue();
						if(StringUtils.equals(key, OrderPropertiesEnum.WB_JIT_WAREHOUSE_CODE.toString())){
							item.setJit_warehouse(value); // JIT仓库编码
						}else if(StringUtils.equals(key, OrderPropertiesEnum.WB_JIT_ESTIMATE_ARRIVETIME.toString())){
							item.setArrive_time(com.yougou.tools.common.utils.DateUtil.parseDate(value, "yyyy-MM-dd HH:mm:ss")); // JIT要求到货时间
						}
					}
				}
				//=========唯品会JIT新增字段end========
				
				List<ItemDetail> item_details = new ArrayList<ItemDetail>();
				List<com.yougou.ordercenter.vo.merchant.output.QueryOrderDetailOutputDto.ItemDetail> _itemDetails = _item.getItem_details();
				if (CollectionUtils.isNotEmpty(_itemDetails)) {
					ItemDetail detail = null;
					for (com.yougou.ordercenter.vo.merchant.output.QueryOrderDetailOutputDto.ItemDetail _itemDetail : _itemDetails) {
						detail = new ItemDetail();
						detail.setCommodity_image(_itemDetail.getCommodity_image());
						detail.setCommodity_no(_itemDetail.getCommodity_no());
						detail.setCommodity_num(_itemDetail.getCommodity_num());
						detail.setCommodity_specification_str(_itemDetail.getCommodity_specification_str());
						detail.setCommodity_type(_itemDetail.getCommodity_type());
						detail.setCommodity_weight(_itemDetail.getCommodity_weight());
						detail.setLevel_code(_itemDetail.getLevel_code());
						detail.setProd_discount_amount(_itemDetail.getProd_discount_amount());
						detail.setProd_name(_itemDetail.getProd_name());
						detail.setProd_no(_itemDetail.getProd_no());
						detail.setProd_total_amt(_itemDetail.getProd_total_amt());
						detail.setProd_unit_price(_itemDetail.getProd_unit_price());
						detail.setSupplier_code(_itemDetail.getSupplier_code());
		                c = commodityBaseApi.getCommodityByNo(_itemDetail.getCommodity_no(), false, false, false);
		                // 款色编码
		                detail.setSupplier_code(null == c ? "" : c.getSupplierCode());
		                detail.setStyle_no(null == c ? "" : c.getStyleNo());
		                detail.setActive_pref_amount(_itemDetail.getActivePrefAmount());
		                detail.setBuy_reduction_pref_amount(_itemDetail.getBuyReductionPrefAmount());
		                detail.setMember_pref_amount(_itemDetail.getMemberPrefAmount());
		                detail.setPayment_pref_amount(_itemDetail.getPaymentPrefAmount());
		                detail.setPostage_cost(_itemDetail.getPostageCost());
		                detail.setShould_postage(_itemDetail.getShouldPostage());
		                detail.setCoupon_pref_amount(_itemDetail.getCouponPrefAmount());
						item_details.add(detail);					
					}
				}
				//货品明细
				item.setItem_details(item_details);
				items.add(item);
			}
		}
		result.setItems(items);
		
		return result;
	}
	
	/**
	 * 转化招商API订单对象
	 * 
	 * @param dto
	 * @return
	 */
	private QueryOrderDetailOutputDto convert(com.yougou.ordercenter.vo.merchant.output.QueryOrderDetailOutputDto dto) {
		if (null == dto) return null;
		
		QueryOrderDetailOutputDto orderoutput = new QueryOrderDetailOutputDto();
		orderoutput.setActual_postage(dto.getActual_postage());
		orderoutput.setArea(dto.getArea());
		orderoutput.setArea_name(dto.getArea_name());
		orderoutput.setBuyer_name(dto.getBuyer_name());
		orderoutput.setCity(dto.getCity());
		orderoutput.setCity_name(dto.getCity_name());
		orderoutput.setConsignee_address(dto.getConsignee_address());
		orderoutput.setConsignee_name(dto.getConsignee_name());
		orderoutput.setConstact_phone(dto.getConstact_phone());
		orderoutput.setCoupon_pref_amount(dto.getCoupon_pref_amount());
		orderoutput.setCoupon_pref_amount5(dto.getCoupon_pref_amount5());
		orderoutput.setCreate_time(dto.getCreate_time());
		orderoutput.setDelivery_date(dto.getDelivery_date());
		orderoutput.setDelivery_date_temp(DeliveryDate.getDeliveryDateEnum(dto.getDelivery_date_temp().getName()));
		orderoutput.setDiscount_amount(dto.getDiscount_amount());
		orderoutput.setEmail(dto.getEmail());
		orderoutput.setId(dto.getId());
		orderoutput.setLogistics_code(dto.getLogistics_code());
		orderoutput.setLogistics_name(dto.getLogistics_name());
		orderoutput.setMember_name(dto.getMember_name());
		orderoutput.setMessage(dto.getMessage());
		orderoutput.setMobile_phone(dto.getMobile_phone());
		orderoutput.setModify_time(dto.getModify_time());
		orderoutput.setOnline_pay_time(dto.getOnline_pay_time());
		orderoutput.setOrder_pay_total_amont(dto.getOrder_pay_total_amont());
		orderoutput.setOrder_source_id(dto.getOrder_source_id());
		orderoutput.setOrder_source_no(dto.getOrder_source_no());
		orderoutput.setOrder_status(dto.getOrder_status());
		orderoutput.setOrder_sub_no(dto.getOrder_sub_no());
		orderoutput.setOriginal_order_no(dto.getOriginal_order_no());
		orderoutput.setOut_order_id(dto.getOut_order_id());
		orderoutput.setOut_shop_id(dto.getOut_shop_id());
		orderoutput.setPayment(Payment.getPaymentEnum(dto.getPayment().getName()));
		orderoutput.setProduct_total_quantity(dto.getProduct_total_quantity());
		orderoutput.setProvince(dto.getProvince());
		orderoutput.setProvince_name(dto.getProvince_name());
		orderoutput.setShip_time(dto.getShip_time());
		orderoutput.setZipcode(dto.getZipcode());
		orderoutput.setBuy_reduction_pref_amount(dto.getBuy_reduction_pref_amount());
        Commodity c = null;
		List<ItemDetail> item_details = new ArrayList<ItemDetail>();
		List<com.yougou.ordercenter.vo.merchant.output.QueryOrderDetailOutputDto.ItemDetail> _itemDetails = dto.getItems();
		if (CollectionUtils.isNotEmpty(_itemDetails)) {
			ItemDetail detail = null;
			for (com.yougou.ordercenter.vo.merchant.output.QueryOrderDetailOutputDto.ItemDetail _itemDetail : _itemDetails) {
				detail = new ItemDetail();
				detail.setCommodity_image(_itemDetail.getCommodity_image());
				detail.setCommodity_no(_itemDetail.getCommodity_no());
				detail.setCommodity_num(_itemDetail.getCommodity_num());
				detail.setCommodity_specification_str(_itemDetail.getCommodity_specification_str());
				detail.setCommodity_type(_itemDetail.getCommodity_type());
				detail.setCommodity_weight(_itemDetail.getCommodity_weight());
				detail.setLevel_code(_itemDetail.getLevel_code());
				detail.setProd_discount_amount(_itemDetail.getProd_discount_amount());
				detail.setProd_name(_itemDetail.getProd_name());
				detail.setProd_no(_itemDetail.getProd_no());
				detail.setProd_total_amt(_itemDetail.getProd_total_amt());
				detail.setProd_unit_price(_itemDetail.getProd_unit_price());
				detail.setSupplier_code(_itemDetail.getSupplier_code());
                c = commodityBaseApi.getCommodityByNo(_itemDetail.getCommodity_no(), false, false, false);
                // 款色编码
                detail.setSupplier_code(null == c ? "" : c.getSupplierCode());
                detail.setStyle_no(null == c ? "" : c.getStyleNo());
				item_details.add(detail);
			}
		}
		//货品明细
		orderoutput.setItems(item_details);
		
		return orderoutput;
	}

	@Override
	public Boolean newOrderOutStoreForMerchant(Map<String, Object> params)
			throws Exception {
		Boolean success = false;
        Boolean runflag = false;
        String checkResult = "";
        Boolean creatflag = false;
        
        String orderSubNo = (String) params.get("orderSubNo");
        String logisticsCompanyCode = (String) params.get("logisticsCompanyCode");
        String expressCode = (String) params.get("expressCode");
        Date outStoreDate = (Date) params.get("outStoreDate");
        String merchantCode = (String) params.get("merchantCode");
        logger.info("newOrderOutStoreForMerchant 方法params="+params);
        //商家发货时间用当前操作的时间，参见：http://112.95.151.185:88/redmine/issues/16709
         if(outStoreDate == null)
        	 outStoreDate = new Date();

        try {
            if (redisTemplate.hasKey(API_CACHE_ORDER_OUTSTORE + merchantCode)) {
                creatflag = true;
            }
            if (!redisTemplate.opsForHash().hasKey(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo)) {
                redisTemplate.opsForHash().put(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo, "0");
                if (!creatflag) {
                    // 第一次建key的时候设置生存期当天的23:59:59
                    redisTemplate.opsForHash().put(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo, "0");
                    String temp = DateUtil.format(new Date(), "yyyy-MM-dd");
                    
                    redisTemplate.expireAt(API_CACHE_ORDER_OUTSTORE + merchantCode, format1.parse(temp + " 23:59:59"));
                }
                
                // 从订单中心获取订单信息
                OrderSub order = orderForMerchantApi.findOrderSubByOrderSubNoAndMerchantCode(orderSubNo, merchantCode);
        		if (null == order) {
        			List<OrderSub> originals = orderForMerchantApi.findOrderSubByMerchantCodeAndOutOrderId(merchantCode, orderSubNo);
            		if (CollectionUtils.isNotEmpty(originals)) {
            			order = originals.get(0);
            		}
        		}
        		logger.info("newOrderOutStoreForMerchant 方法order="+order);
        		Date now=new Date(System.currentTimeMillis()+3600000);
        		//加入时间的校验
        		if(outStoreDate.getTime()<=order.getVerifyTime().getTime()||outStoreDate.getTime()>=now.getTime()){
        			 runflag = true;
     				String errStr=MessageFormat.format("商家：{0} 订单 :{1} 发货时间{2} 不能<=下单审核时间 {3}和>=当前时间 {4}.",merchantCode,orderSubNo,DateUtil.format(outStoreDate,"yyyy-MM-dd HH:mm:ss"),DateUtil.format(order.getVerifyTime(),"yyyy-MM-dd HH:mm:ss"),DateUtil.format(now,"yyyy-MM-dd HH:mm:ss"));
    				logger.error(errStr);
        			 throw new YOPRuntimeException("20140902",errStr);
        		}

                // 对订单发货状态做校验
                runflag = true;
                checkResult = orderForMerchantApi.checkOrderIsCanSend(orderSubNo);
                logger.info("newOrderOutStoreForMerchant 方法checkResult="+checkResult);
                if ("true".equals(checkResult)) {
                    logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "出库中，订单号：" + orderSubNo + "快递单号：" + expressCode.trim() + "物流公司编码：" + logisticsCompanyCode);
                   // success = orderForMerchantApi.orderOutStoreForMerchant(orderSubNo, logisticsCompanyCode.trim(), expressCode.trim(), outStoreDate); 
                    success = orderForMerchantApi.newOrderOutStoreForMerchant(params);
                    logger.info("newOrderOutStoreForMerchant 方法success="+success);
                    if (success) {
                        redisTemplate.opsForHash().put(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo, "1");
                        logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "发货成功！");
                        return true;
                    } else {
                        redisTemplate.opsForHash().delete(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo);
                        logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "发货失败！");
                        return false;
                    }
                } else if ("该订单已被置为【已完成】状态，请不要做发货处理".equals(checkResult)) {
                    redisTemplate.opsForHash().put(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo, "1");
                    logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "已经发过货，请勿重复发货！");
                } else {
                    redisTemplate.opsForHash().delete(API_CACHE_ORDER_OUTSTORE + merchantCode,orderSubNo);
                    logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "状态校验未通过，订单校验状态接口返回：" + checkResult);
                }
            } else if ("1".equals(redisTemplate.opsForHash().get(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo).toString())) {
                runflag = true;
                logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "已经发过货，请勿重复发货！");
                return true;
            } else {
                runflag = true;
                logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "正在发货，请勿重复发货！");
                return false;
            }
        } catch (RedisConnectionFailureException e) {
            logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "在发货时遇到redis连接错误", e);
            throw new RuntimeException("商家：" + merchantCode + "的订单：" + orderSubNo + "在发货时遇到redis连接错误,请稍后再试");
        } catch (YOPRuntimeException e) {
            redisTemplate.opsForHash().delete(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "在发货时异常,订单校验状态接口返回：" + checkResult, e);
            if (!"true".equals(checkResult) || !success) {
                redisTemplate.opsForHash().delete(API_CACHE_ORDER_OUTSTORE + merchantCode, orderSubNo);
                throw new RuntimeException("商家：" + merchantCode + "的订单：" + orderSubNo + "在发货时异常！");
            }
        } finally {
            if (!runflag) {
                checkResult = orderForMerchantApi.checkOrderIsCanSend(orderSubNo);
                logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "在尚未发货就发生异常,订单校验状态接口返回：" + checkResult + ",现强制走发货流程。");
                if (!"true".equals(checkResult)) {
                    throw new RuntimeException(checkResult);
                }
                //success = orderForMerchantApi.orderOutStoreForMerchant(orderSubNo, logisticsCompanyCode.trim(), expressCode.trim(), outStoreDate);
                success = orderForMerchantApi.newOrderOutStoreForMerchant(params);
            }
        }
        return success;
	}
	@Override
	public Boolean orderOutStoreForMerchant(String merchantCode, String orderSubNo, String productInfo, String logisticsCode, String expressCode, Date outStoreDate) throws Exception {
		List<MerchantOrderOutStoreVo> merchantOrderOutStoreVoList = new ArrayList<MerchantOrderOutStoreVo>();
		Boolean success = false;
        Boolean runflag = false;
        String checkResult = "";
        Boolean creatflag = false;
        orderSubNo=orderSubNo.trim();
        //商家发货时间用当前操作的时间，参见：http://112.95.151.185:88/redmine/issues/16709
        outStoreDate = new Date();

        String[] prodList = productInfo.split("\\|");
		if(prodList.length<=0){
			logger.error("订单发货的货品信息错误,请检查！");
			throw new YOPRuntimeException("10000","订单发货的货品信息错误,请检查！");
		}

        try {
            if (redisTemplate.hasKey(API_CACHE_ORDER_OUTSTORE + merchantCode)) {
                creatflag = true;
            }
            if (!redisTemplate.opsForHash().hasKey(API_CACHE_ORDER_OUTSTORE + merchantCode, expressCode)) {
                redisTemplate.opsForHash().put(API_CACHE_ORDER_OUTSTORE + merchantCode, expressCode, "0");
                if (!creatflag) {
                    // 第一次建key的时候设置生存期当天的23:59:59
                    String temp = DateUtil.format(new Date(), "yyyy-MM-dd");
                    redisTemplate.expireAt(API_CACHE_ORDER_OUTSTORE + merchantCode, format1.parse(temp + " 23:59:59"));
                }
                
                // 从订单中心获取订单信息
                OrderSub order = orderForMerchantApi.findOrderSubByOrderSubNoAndMerchantCode(orderSubNo, merchantCode);
        		if (null == order) {
        			List<OrderSub> originals = orderForMerchantApi.findOrderSubByMerchantCodeAndOutOrderId(merchantCode, orderSubNo);
            		if (CollectionUtils.isNotEmpty(originals)) {
            			order = originals.get(0);
            		}
        		}
        		Date now=new Date(System.currentTimeMillis()+3600000);
        		//加入时间的校验
        		if(outStoreDate.getTime()<=order.getVerifyTime().getTime()||outStoreDate.getTime()>=now.getTime()){
        			 runflag = true;
        			 String errStr=MessageFormat.format("商家：{0} 订单 :{1} 快递单号:{2} 发货时间{3} 不能<=下单审核时间 {4}和>=当前时间 {5}.", merchantCode, orderSubNo, expressCode, DateUtil.format(outStoreDate,"yyyy-MM-dd HH:mm:ss"), DateUtil.format(order.getVerifyTime(),"yyyy-MM-dd HH:mm:ss"), DateUtil.format(now,"yyyy-MM-dd HH:mm:ss"));
    				 logger.error(errStr);
        			 throw new YOPRuntimeException("20140902",errStr);
        		}

                // 对订单发货状态做校验
                runflag = true;
                checkResult = orderForMerchantApi.checkOrderIsCanSend(orderSubNo);
                if ("true".equals(checkResult)) {
                    logger.error("商家：" + merchantCode + "的订单：" + orderSubNo + "出库中，订单号：" + orderSubNo + "快递单号：" + expressCode.trim() + "物流公司编码：" + logisticsCode);
                    
                    for(String prod : prodList){
                    	String[] arr = prod.split(":");
                    	if(arr.length!=6){
                    		logger.error("订单发货的货品信息的格式不正确,请检查！");
                			throw new YOPRuntimeException("10000","订单发货的货品信息的格式不正确,请检查！");
                    	}
                    	MerchantOrderOutStoreVo merchantOrderOutStoreVo = new MerchantOrderOutStoreVo();
                    	merchantOrderOutStoreVo.setMerchantCode(merchantCode);
                        merchantOrderOutStoreVo.setOrderSubNo(orderSubNo);
                        merchantOrderOutStoreVo.setLevelCode(arr[0]);                       
                        try{
                        	 merchantOrderOutStoreVo.setCommodityNum(Integer.parseInt(arr[1]));
                        }catch(Exception e){
                        	logger.error("订单发货的货品信息中的商品数量的格式不正确,请检查！");
                			throw new YOPRuntimeException("10000","订单发货的货品信息中的商品数量的格式不正确,请检查！");
                        }
                        //=======唯品会JIT新增字段start zf 2016-7-9 =====
                        merchantOrderOutStoreVo.setMerchantCargoCode(arr[2]); //唯品会JIT百丽集团线下发货货管单位编码
                        merchantOrderOutStoreVo.setMerchantSettleCode(arr[3]); //唯品会JIT百丽集团线下发货结算公司编码
                        merchantOrderOutStoreVo.setJitWarehouseCode(arr[4]); //唯品会JIT百丽集团线下发货省级仓库编码
                        merchantOrderOutStoreVo.setJitStrorageNo(arr[5]); //唯品会JIT百丽集团线下发货唯品会入库单号
                       //=======唯品会JIT新增字段end zf 2016-7-9 =====
                        merchantOrderOutStoreVo.setLogisticsCode(logisticsCode);
                        merchantOrderOutStoreVo.setExpressCode(expressCode);
                		merchantOrderOutStoreVo.setDeliveryTime(outStoreDate);
                		merchantOrderOutStoreVoList.add(merchantOrderOutStoreVo);
                    }
                    if(merchantOrderOutStoreVoList.size()>0){
                    	success = orderForMerchantApi.outStoreForMerchantBatch(merchantOrderOutStoreVoList);
                    }
                    if (success) {
                        redisTemplate.opsForHash().put(API_CACHE_ORDER_OUTSTORE + merchantCode, expressCode, "1");
                        logger.error("商家：" + merchantCode + " 订单：" + orderSubNo + " 快递单号：" + expressCode + "发货成功！");
                        return true;
                    } else {
                        redisTemplate.opsForHash().delete(API_CACHE_ORDER_OUTSTORE + merchantCode, expressCode);
                        logger.error("商家：" + merchantCode + " 订单：" + orderSubNo + " 快递单号：" + expressCode + "发货失败！");
                        return false;
                    }
                } else {
                    redisTemplate.opsForHash().delete(API_CACHE_ORDER_OUTSTORE + merchantCode, expressCode);
                    logger.error("商家：" + merchantCode + " 订单：" + orderSubNo + " 快递单号：" + expressCode + "状态校验未通过，订单校验状态接口返回：" + checkResult);
                }
            } else if ("1".equals(redisTemplate.opsForHash().get(API_CACHE_ORDER_OUTSTORE + merchantCode, expressCode).toString())) {
                runflag = true;
                logger.error("商家：" + merchantCode + " 订单：" + orderSubNo + " 快递单号：" + expressCode + "已经发过货，请勿重复发货！");
                return true;
            } else {
                runflag = true;
                logger.error("商家：" + merchantCode + " 订单：" + orderSubNo + " 快递单号：" + expressCode + "正在发货，请勿重复发货！");
                return false;
            }
        } catch (RedisConnectionFailureException e) {
            logger.error("商家：" + merchantCode + " 订单：" + orderSubNo + " 快递单号：" + expressCode + "在发货时遇到redis连接错误", e);
            throw new RuntimeException("商家：" + merchantCode + " 订单：" + orderSubNo + " 快递单号：" + expressCode + "在发货时遇到redis连接错误,请稍后再试");
        } catch (YOPRuntimeException e) {
            redisTemplate.opsForHash().delete(API_CACHE_ORDER_OUTSTORE + merchantCode, expressCode);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("商家：" + merchantCode + " 订单：" + orderSubNo + " 快递单号：" + expressCode + "在发货时异常,订单校验状态接口返回：" + checkResult, e);
            if (!"true".equals(checkResult) || !success) {
                redisTemplate.opsForHash().delete(API_CACHE_ORDER_OUTSTORE + merchantCode, expressCode);
                throw new RuntimeException("商家：" + merchantCode + " 订单：" + orderSubNo + " 快递单号：" + expressCode + "在发货时异常！");
            }
        } finally {
            if (!runflag) {
                checkResult = orderForMerchantApi.checkOrderIsCanSend(orderSubNo);
                logger.error("商家：" + merchantCode + " 订单：" + orderSubNo + " 快递单号：" + expressCode + "在尚未发货就发生异常,订单校验状态接口返回：" + checkResult + ",现强制走发货流程。");
                if (!"true".equals(checkResult)) {
                    throw new RuntimeException(checkResult);
                }
                if(merchantOrderOutStoreVoList.size()>0){
                	success = orderForMerchantApi.outStoreForMerchantBatch(merchantOrderOutStoreVoList);
                }
            }
        }
        return success;
    }

}
