package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;
import java.util.List;


public class CommodityQueryVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2056562741747811343L;
	private String commodityName;//商品名称
	private String commodityNo;//商品编码
	private String minCreateDate;//创建开始时间
	private String maxCreateDate;//创建结束时间
	private String styleNo;//款号
	private String styleColorNo;//款色编码
	private String merchantCode;//供应商列表
	private String brandNo;//品牌编码
	private List<String> category;//分类精确到三级分类

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName; 
	}

	public String getCommodityNo() {
		return commodityNo;
	}

	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}

	public String getMaxCreateDate() {
		return maxCreateDate;
	}

	public void setMaxCreateDate(String maxCreateDate) {
		this.maxCreateDate = maxCreateDate;
	}

	public String getMinCreateDate() {
		return minCreateDate;
	}

	public void setMinCreateDate(String minCreateDate) {
		this.minCreateDate = minCreateDate;
	}

	public String getStyleColorNo() {
		return styleColorNo;
	}

	public void setStyleColorNo(String styleColorNo) {
		this.styleColorNo = styleColorNo;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}

	public String getStyleNo() {
		return styleNo;
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}
}
