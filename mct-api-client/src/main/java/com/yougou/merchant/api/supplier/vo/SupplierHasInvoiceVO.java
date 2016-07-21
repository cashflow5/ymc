package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;

public class SupplierHasInvoiceVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String supplierCode;// 供应商编码
	private String supplierName;// 供应商名称
	private int isInvoice;// 是否开发票,0 否
	private int isAddValueInvoice;// 是否支持增值发票,0 否
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public int getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(int isInvoice) {
		this.isInvoice = isInvoice;
	}
	public int getIsAddValueInvoice() {
		return isAddValueInvoice;
	}
	public void setIsAddValueInvoice(int isAddValueInvoice) {
		this.isAddValueInvoice = isAddValueInvoice;
	}
}
