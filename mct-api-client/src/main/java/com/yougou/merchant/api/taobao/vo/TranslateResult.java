package com.yougou.merchant.api.taobao.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TranslateResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 淘宝三级分类编码  比如：50015368
	 */
	private String tbCategoryCode;
	
	/**
	 * 淘宝三级分类名称  比如： 服饰配件
	 */
	private String tbCategoryName;
	
	/**
	 * 淘宝分类全称 比如：户外/登山/野营/旅行用品 > 服饰配件
	 */
	private String tbCategoryFullName;
	
	/**
	 * 优购淘宝属性对应结合
	 */
	private List<PropItem>ygProps = new ArrayList<PropItem>();
	
	/**
	 * 是否错误  ture 为错误  false 为没有错误
	 */
	private boolean isError;  
	
	/**
	 * 错误信息
	 */
	private String errorMessage;

	public String getTbCategoryCode() {
		return tbCategoryCode;
	}

	public void setTbCategoryCode(String tbCategoryCode) {
		this.tbCategoryCode = tbCategoryCode;
	}

	public String getTbCategoryName() {
		return tbCategoryName;
	}

	public void setTbCategoryName(String tbCategoryName) {
		this.tbCategoryName = tbCategoryName;
	}

	public String getTbCategoryFullName() {
		return tbCategoryFullName;
	}

	public void setTbCategoryFullName(String tbCategoryFullName) {
		this.tbCategoryFullName = tbCategoryFullName;
	}

	public List<PropItem> getYgProps() {
		return ygProps;
	}

	public void setYgProps(List<PropItem> ygProps) {
		this.ygProps = ygProps;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
