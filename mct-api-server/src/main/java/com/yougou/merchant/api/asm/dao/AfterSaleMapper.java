/**
 * 
 */
package com.yougou.merchant.api.asm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.dto.input.ReturnQualityQueryInputDto;
import com.yougou.dto.output.ReturnQA;
import com.yougou.dto.output.ReturnQADetail;


/**
 *  
 * @author huang.tao
 *
 */
public interface AfterSaleMapper {
	
	List<ReturnQA> queryReturnQualityList(ReturnQualityQueryInputDto dto, RowBounds row);
	
	Integer queryReturnQualityCount(ReturnQualityQueryInputDto dto);
	
	List<ReturnQADetail> queryReturnQualityDetailList(String returnId);
}
