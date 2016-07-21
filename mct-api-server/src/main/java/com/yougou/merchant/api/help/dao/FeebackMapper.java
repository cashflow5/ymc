/**
 * 
 */
package com.yougou.merchant.api.help.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.merchant.api.help.vo.Feeback;
import com.yougou.merchant.api.help.vo.FeebackReply;
import com.yougou.merchant.api.help.vo.FeebackVo;

/**
 * @author huang.tao
 *
 */
public interface FeebackMapper {
	void insertFeeback(Feeback vo);
	void insertFeebackReply(FeebackReply vo);
	
	List<Feeback> queryFeebackListByVo(FeebackVo vo, RowBounds row);
	Integer queryFeebackCountByVo(FeebackVo vo);
	
	Feeback getFeebackById(String id);
	
	void updateFeeback(Feeback vo);
	
	/**
	 * 得到该意见反馈的回复列表
	 * @param feebackid
	 * @return
	 */
	List<FeebackReply> getFeebackReplyById(String feebackid);
	
	void updateFeebackReply(FeebackReply vo);
}
