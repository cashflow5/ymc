package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 品牌扣点
 * @author li.j1
 *
 */
public class MerchantBrandDeducRate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String deductId;
	private String supplierCode;
	private String supplierName;
	private String brandName;
	private String brandCode;
	private String contractCode;
	private String contractStatus;
	private Date contractStartTime;
	private Date contractEndTime;
	private int deductRate;
	private String settlementType;
	// added by zhangfeng 2016.5.10  配合财务招商结算，新增合同更新时间返回 
	private Date contractUpdateTime;
	
	public String getDeductId() {
		return deductId;
	}
	public void setDeductId(String deductId) {
		this.deductId = deductId;
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
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public Date getContractStartTime() {
		return contractStartTime;
	}
	public void setContractStartTime(Date contractStartTime) {
		this.contractStartTime = contractStartTime;
	}
	public Date getContractEndTime() {
		return contractEndTime;
	}
	public void setContractEndTime(Date contractEndTime) {
		this.contractEndTime = contractEndTime;
	}
	public int getDeductRate() {
		return deductRate;
	}
	public void setDeductRate(int deductRate) {
		this.deductRate = deductRate;
	}
	public String getSettlementType() {
		return settlementType;
	}
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}
	
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Date getContractUpdateTime() {
		return contractUpdateTime;
	}
	public void setContractUpdateTime(Date contractUpdateTime) {
		this.contractUpdateTime = contractUpdateTime;
	}
}
