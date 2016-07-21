/*
 * com.yougou.merchant.api.supplier.vo.SupplierContract
 * 
 *  Tue Jun 23 13:25:28 CST 2015
 * 
 * Copyright (C) 2013 YouGou Information Technology Co.,Ltd 
 * All Rights Reserved. 
 * 
 * The software for the YouGou technology development, without the 
 * company's written consent, and any other individuals and 
 * organizations shall not be used, Copying, Modify or distribute 
 * the software.
 * 
 */
package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 商家合同表
 * 
 * @author le.sm
 * @date 2015/07/06
 */
public class SupplierContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2721622742672068263L;

	private String id;

	private String supplierId;

	private String contractNo;

	private Short clearingForm;

	private Date effectiveDate;

	private Date failureDate;

	private String updateTime;

	private String updateUser;

	private String attachment;

	private String declarant;

	private Date createTime;

	private String bindStatus;

	private String createUser;

	private String supplier;

	private Integer markRemainingDays;

	private Integer contractRemainingDays;

	private String isNeedRenew;

	private String isUseERP;

	private String isNeedDeposit;

	private String isNeedUseFee;

	private BigDecimal deposit;

	private BigDecimal useFee;

	private String remark;

	private String bankOwner;

	private String bankAccount;

	private String bankProvince;

	private String bankCity;

	private String bankArea;

	private String bankName;

	private String parentContractId;

	private String status;

	private BigDecimal preDeposit;

	private BigDecimal preUsefee;

	private String isTransferDeposit;

	private String renewFlag;
	
	private String depositStatus;
	
	private String useFeeStatus;
	
	private String isOld;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Short getClearingForm() {
		return clearingForm;
	}

	public void setClearingForm(Short clearingForm) {
		this.clearingForm = clearingForm;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getFailureDate() {
		return failureDate;
	}

	public void setFailureDate(Date failureDate) {
		this.failureDate = failureDate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getDeclarant() {
		return declarant;
	}

	public void setDeclarant(String declarant) {
		this.declarant = declarant;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBindStatus() {
		return bindStatus;
	}

	public void setBindStatus(String bindStatus) {
		this.bindStatus = bindStatus;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Integer getMarkRemainingDays() {
		return markRemainingDays;
	}

	public void setMarkRemainingDays(Integer markRemainingDays) {
		this.markRemainingDays = markRemainingDays;
	}

	public Integer getContractRemainingDays() {
		return contractRemainingDays;
	}

	public void setContractRemainingDays(Integer contractRemainingDays) {
		this.contractRemainingDays = contractRemainingDays;
	}

	public String getIsNeedRenew() {
		return isNeedRenew;
	}

	public void setIsNeedRenew(String isNeedRenew) {
		this.isNeedRenew = isNeedRenew;
	}

	public String getIsUseERP() {
		return isUseERP;
	}

	public void setIsUseERP(String isUseERP) {
		this.isUseERP = isUseERP;
	}

	public String getIsNeedDeposit() {
		return isNeedDeposit;
	}

	public void setIsNeedDeposit(String isNeedDeposit) {
		this.isNeedDeposit = isNeedDeposit;
	}

	public String getIsNeedUseFee() {
		return isNeedUseFee;
	}

	public void setIsNeedUseFee(String isNeedUseFee) {
		this.isNeedUseFee = isNeedUseFee;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public BigDecimal getUseFee() {
		return useFee;
	}

	public void setUseFee(BigDecimal useFee) {
		this.useFee = useFee;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBankOwner() {
		return bankOwner;
	}

	public void setBankOwner(String bankOwner) {
		this.bankOwner = bankOwner;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankProvince() {
		return bankProvince;
	}

	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public String getBankArea() {
		return bankArea;
	}

	public void setBankArea(String bankArea) {
		this.bankArea = bankArea;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getParentContractId() {
		return parentContractId;
	}

	public void setParentContractId(String parentContractId) {
		this.parentContractId = parentContractId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getPreDeposit() {
		return preDeposit;
	}

	public void setPreDeposit(BigDecimal preDeposit) {
		this.preDeposit = preDeposit;
	}

	public BigDecimal getPreUsefee() {
		return preUsefee;
	}

	public void setPreUsefee(BigDecimal preUsefee) {
		this.preUsefee = preUsefee;
	}

	public String getIsTransferDeposit() {
		return isTransferDeposit;
	}

	public void setIsTransferDeposit(String isTransferDeposit) {
		this.isTransferDeposit = isTransferDeposit;
	}

	public String getRenewFlag() {
		return renewFlag;
	}

	public void setRenewFlag(String renewFlag) {
		this.renewFlag = renewFlag;
	}

	public String getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(String depositStatus) {
		this.depositStatus = depositStatus;
	}

	public String getUseFeeStatus() {
		return useFeeStatus;
	}

	public void setUseFeeStatus(String useFeeStatus) {
		this.useFeeStatus = useFeeStatus;
	}
	
	public String getIsOld() {
		return isOld;
	}

	public void setIsOld(String isOld) {
		this.isOld = isOld;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}