package com.yougou.kaidian.common.commodity.pojo;

/**
 * 货品实体类
 * 
 * @author zhuangruibo
 * @create 2012-3-30 上午10:07:32
 * @history
 */
public class Product {
	private String id;
	private String commodityName;
	private int isAudit;
	private String merchantCode;
	private String productNo;
	private String thirdPartyCode;
	private String insideCode;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public int getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(int isAudit) {
		this.isAudit = isAudit;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getThirdPartyCode() {
		return thirdPartyCode;
	}

	public void setThirdPartyCode(String thirdPartyCode) {
		this.thirdPartyCode = thirdPartyCode;
	}

	public String getInsideCode() {
		return insideCode;
	}

	public void setInsideCode(String insideCode) {
		this.insideCode = insideCode;
	}

}
