package com.yougou.merchant.api.active.vo;

import java.io.Serializable;

/**
 * 促销接口查询VO类
 * @author zhang.wj
 *
 */
public class QueryActivityVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//商家名称
	private  String merchantName;
	//活动id
	private  String activeId; 
	//商品编码
	private  String commodityNo;
	//活动名称
	private  String activeName;
	//商家编码
	private String merchantCode;
	
	
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getActiveId() {
		return activeId;
	}
	public void setActiveId(String activeId) {
		this.activeId = activeId;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public String getActiveName() {
		return activeName;
	}
	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}
	
}
