/**
 * 
 */
package com.yougou.yop.api;

import java.util.Map;

/**
 * 招商品类授权相关服务（为公共API提供服务）
 * 
 * @author huang.tao
 *
 */
public interface IMerchantApiCategoryService {
	
	Object queryBrand(Map<String, Object> parameterMap) throws Exception;
	
	Object queryCat(Map<String, Object> parameterMap) throws Exception;
}
