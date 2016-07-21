/**
 * 
 */
package com.yougou.yop.api;

import java.util.Date;
import java.util.Map;

/**
 * 招商订单  service
 * 
 * @author huang.tao
 *
 */
public interface IMerchantApiOrderService {
	/**
	 * 查询订单列表信息 yougou.order.query
	 * 
	 * @param parameterMap
	 * @return Object
	 * @throws Exception
	 */
	Object queryOrder(Map<String, Object> parameterMap) throws Exception;
	
	/**
	 * 查询增量订单列表信息 yougou.order.increment.query
	 * 
	 * @return Object
	 * @throws Exception
	 */
	Object queryIncrementOrder(Map<String, Object> parameterMap) throws Exception; 
	
	/**
	 * 查询订单详情信息 yougou.order.get
	 * 
	 * @param parameterMap
	 * @return Object
	 * @throws Exception
	 */
	Object getOrder(Map<String, Object> parameterMap) throws Exception;
	
	/**
	 * 查询客服取消订单 yougou.order.canceled.query
	 * 
	 * @param parameterMap
	 * @return Object
	 * @throws Exception
	 */
	Object queryCanceledOrder(Map<String, Object> parameterMap) throws Exception;
	
	/**
	 * 客服取消订单，商家成功拦截后更新订音状态为终止发货 yougou.order.nondelivery.update
	 * 
	 * @param parameterMap
	 * @return Object
	 * @throws Exception
	 */
	Object updateNondeliveryOrder(Map<String, Object> parameterMap) throws Exception;
	
	/**
	 * 更新缺货异常、物流超区异常订单 yougou.order.stockout.update
	 * 
	 * @param parameterMap
	 * @return Object
	 * @throws Exception
	 */
	Object updateStockoutOrder(Map<String, Object> parameterMap) throws Exception;

    /**
     * 商家订单发货以后更新订单状态为已完成 yougou.order.completed.update
     */
    Boolean orderOutStoreForMerchant(String merchantCode, String orderSubNo, String logisticsCompanyCode, String expressCode, Date outStoreDate) throws Exception;
    
    /**
     * 商家订单发货以后更新订单状态 (支持一单多包裹的情况)--第一个包裹发货之后，该订单的状态就改为已发货 。
     */
    Boolean orderOutStoreForMerchant(String merchantCode, String orderSubNo, String productInfo, String logisticsCode, String expressCode, Date outStoreDate) throws Exception;
    
    /**
     * added by zhangfeng 2016-03-03 适应商家中心首尔直发订单需求，新增包裹重量参数（原有接口openapi 有用到，所以新增接口）
     * 商家订单发货以后更新订单状态为已完成 yougou.order.completed.update
     * params{merchantCode:商家编码，orderSubNo:子订单号，logisticsCompanyCode:物流公司编码，expressCode:运单号，outStoreDate:发货日期，weight:包裹重量}
     * @param params
     * @return
     * @throws Exception
     */
    Boolean newOrderOutStoreForMerchant(Map<String,Object> params) throws Exception;

	/**
	 * 商家同步订单后回调更新订单同步状态为已同步 yougou.order.synchronized.update
	 * 
	 * @param parameterMap
	 * @return Object
	 * @throws Exception
	 */
	Object updateSynchronizedOrder(Map<String, Object> parameterMap) throws Exception;
	
	/**
	 * 查询客服申请拦截订单的申请拦截信息 yougou.order.intercept.query
	 * 
	 * @param parameterMap
	 * @return Object
	 * @throws Exception
	 */
	Object queryInterceptOrder(Map<String, Object> parameterMap) throws Exception;
	
	/**
	 * 更新同意/不同意客服申请拦截订单信息 yougou.order.intercept.update
	 * 
	 * @param parameterMap
	 * @return Object
	 * @throws Exception
	 */
	Object updateInterceptOrder(Map<String, Object> parameterMap) throws Exception;
}
