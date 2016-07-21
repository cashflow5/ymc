/**
 * 
 */
package com.yougou.yop.api;

import java.util.List;
import java.util.Map;

import com.yougou.dto.output.QueryExpressCompanyOuputDto;
import com.yougou.dto.output.QueryLogisticsCompanyOuputDto;

/**
 * 招商物流 相关服务 （为公共API提供服务）
 * 
 * @author huang.tao
 *
 */
public interface IMerchantApiLogisticsService {
	
	List<QueryLogisticsCompanyOuputDto> getLogisticscompany() throws Exception;
	
	List<QueryExpressCompanyOuputDto> getExpresscompany() throws Exception;
	
	/**
	 * 获取唯品会JIT入库编号
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	List<String> getStorageNo(Map<String, Object> parameterMap) throws Exception;
	
	
	/**
	 * 上传唯品会JIT出仓单
	 * 
	 * @param jitDelivery
	 * @return
	 */
	Boolean importDeliveryDetail(Map<String, Object> parameterMap);
}
