/**
 * 
 */
package com.yougou.yop.api;

import java.util.Map;

import com.yougou.dto.input.ReturnQualityQueryInputDto;
import com.yougou.dto.output.ReturnQualityQueryOutputDto;

/**
 * 售后质检相关服务（为公共API提供服务）
 * 
 * @author huang.tao
 *
 */
public interface IMerchantApiAsmService {
	/**
	 * 退换货质检查询
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	ReturnQualityQueryOutputDto queryReturnQualityList(ReturnQualityQueryInputDto dto) throws Exception;
	
	Object addQualityReturn(Map<String, Object> parameterMap) throws Exception;
	
	Object addQualityRejection(Map<String, Object> parameterMap) throws Exception;
	
	Object getReturnWarehouse(String order_sub_no) throws Exception;
}
