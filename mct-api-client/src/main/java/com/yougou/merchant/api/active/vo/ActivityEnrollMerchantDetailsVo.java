package com.yougou.merchant.api.active.vo;

import java.io.Serializable;
/**
 * 查询活动报名商家商品明细Vo
 * @author zhang.wj
 *
 */
public class ActivityEnrollMerchantDetailsVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//商品编码
	private String commodityNo;
	//活动价
	private Integer activePrice;
	
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public Integer getActivePrice() {
		return activePrice;
	}
	public void setActivePrice(Integer activePrice) {
		this.activePrice = activePrice;
	}
}
