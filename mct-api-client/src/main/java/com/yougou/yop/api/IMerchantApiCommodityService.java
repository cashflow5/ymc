/**
 * 
 */
package com.yougou.yop.api;

import java.util.Map;

import com.yougou.dto.input.ReturnQualityQueryInputDto;
import com.yougou.dto.output.ReturnQualityQueryOutputDto;

/**
 * 商家商品服务接口
 * 
 * @author zheng.qq
 *
 */
public interface IMerchantApiCommodityService {
	/**
	 * 查询列表信息 yougou.commodity.query
	 * 
	 * @param parameterMap
	 * @return Object
	 * @throws Exception
	 */
	 Object queryCommodity(Map<String, Object> parameterMap) throws Exception;
}
