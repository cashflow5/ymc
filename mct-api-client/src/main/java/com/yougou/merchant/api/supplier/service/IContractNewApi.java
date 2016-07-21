package com.yougou.merchant.api.supplier.service;

import java.util.List;
import java.util.Map;

/**
 * 合同相关接口（提供外部系统调用）
 * @author le.sm
 *
 */
public interface IContractNewApi {
	/**
	 * 根据商家编码集合获取商家合同中的结算方式 ；如果查询不到某商家的结算方式，返回结果不包括该商家的记录
	 * @author li.n1 
	 * @param merchantCodes 商家编码集合
	 * @return Map<商家编码，结算方式(1=底价结算;2=扣点结算;3=配折结算;4=促销结算)>
	 * @since JDK 1.6 
	 * @date 2015-11-24 下午2:23:42
	 */
	Map<String,Integer> getSettlementType(List<String> merchantCodes); 
	
}
