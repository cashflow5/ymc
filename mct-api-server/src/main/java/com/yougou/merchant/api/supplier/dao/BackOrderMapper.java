package com.yougou.merchant.api.supplier.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yougou.merchant.api.supplier.vo.BackOrderDetailVo;
import com.yougou.merchant.api.supplier.vo.BackOrderVo;

/**
 * 招商系统将退回单信息保存至数据库，持久化映射接口
 * @author zhang.f1
 *
 */
public interface BackOrderMapper {
	
	/**
	 * 保存退回单信息（退回单主表）
	 * @param backOrderVo
	 */
	void insertBackOrderInfo(BackOrderVo backOrderVo);
	
	/**
	 * 保存退回单详情
	 * @param detailList
	 */
	void batchInsertBackOrderDetailInfo(@Param("detailList")List<BackOrderDetailVo> detailList);
	
	/**
	 * 根据退回单ID查询记录是否已存在
	 * @param id
	 * @return
	 */
	int queryBackOrderCountById(String id);
}
