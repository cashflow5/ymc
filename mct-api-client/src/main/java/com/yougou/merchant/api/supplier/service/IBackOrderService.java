package com.yougou.merchant.api.supplier.service;

import com.yougou.merchant.api.supplier.vo.BackOrderVo;

/**
 * 招商商家中心新增首尔直发退回订单， 确认收货功能
 * 此接口提供给WMS 将质检审核通过的退回单信息发送招商系统保存，以便招商系统页面功能展示
 * @author zhang.f1
 *
 */
public interface IBackOrderService {
	/**
	 * WMS系统将质检通过，审核通过的首尔直发退回单（含详情列表）调用此接口将数据保存至招商系统
	 * @param backOrderVo
	 * @return true:数据保存成功，false 数据保存失败
	 */
	boolean addBackOrderFromWms(BackOrderVo backOrderVo)throws Exception;
}
