package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;

public class BrandCategoryVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String brandNo;//品牌编码
	private String rootCategory;//一级分类
	private String secondCategory;//二级分类
	private String threeCategory;//三级分类
	
	public String getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}
	public String getRootCategory() {
		return rootCategory;
	}
	public void setRootCategory(String rootCategory) {
		this.rootCategory = rootCategory;
	}
	public String getSecondCategory() {
		return secondCategory;
	}
	public void setSecondCategory(String secondCategory) {
		this.secondCategory = secondCategory;
	}
	public String getThreeCategory() {
		return threeCategory;
	}
	public void setThreeCategory(String threeCategory) {
		this.threeCategory = threeCategory;
	}
}
