 
package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class MerchantSupplierExpand implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private String id;
    
    private String supplierExpandId;//供页面保存id使用

    private String merchantCode;

    private Integer safeStockQuantity;

    private String ygContactUserId;

    private String contractNo;

    private String uptateTime;

    private String remark;

    private Integer markRemainingDays;

    private Integer contractRemainingDays;

    private Integer businessRemainingDays;

    private Integer institutionalRemainingDays;

    private Integer deleteFlag;

    private String businessRange;

    private String businessValidityStart;

    private String businessValidityEnd;

    private String registerProvince;

    private String registerCity;

    private String registerArea;

    private String registerDetails;

    private String institutionalValidityStart;

    private String institutionalValidityEnd;

    private String bankLocalProvince;

    private String bankLocalCity;

    private String bankLocalArea;

    private String supplierId;
    
    private String isOld;// 新旧商家标志 
    
    private Integer maxCouponAmount;// 卡券最高分摊金额
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
    }

    public Integer getSafeStockQuantity() {
        return safeStockQuantity;
    }

    public void setSafeStockQuantity(Integer safeStockQuantity) {
        this.safeStockQuantity = safeStockQuantity;
    }

    public String getYgContactUserId() {
        return ygContactUserId;
    }

    public void setYgContactUserId(String ygContactUserId) {
        this.ygContactUserId = ygContactUserId == null ? null : ygContactUserId.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getUptateTime() {
        return uptateTime;
    }

    public void setUptateTime(String uptateTime) {
        this.uptateTime = uptateTime == null ? null : uptateTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Integer getBusinessRemainingDays() {
        return businessRemainingDays;
    }

    public void setBusinessRemainingDays(Integer businessRemainingDays) {
        this.businessRemainingDays = businessRemainingDays;
    }

    public Integer getInstitutionalRemainingDays() {
        return institutionalRemainingDays;
    }

    public void setInstitutionalRemainingDays(Integer institutionalRemainingDays) {
        this.institutionalRemainingDays = institutionalRemainingDays;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getBusinessRange() {
        return businessRange;
    }

    public void setBusinessRange(String businessRange) {
        this.businessRange = businessRange == null ? null : businessRange.trim();
    }

    public String getBusinessValidityStart() {
        return businessValidityStart;
    }

    public void setBusinessValidityStart(String businessValidityStart) {
        this.businessValidityStart = businessValidityStart == null ? null : businessValidityStart.trim();
    }

    public String getBusinessValidityEnd() {
        return businessValidityEnd;
    }

    public void setBusinessValidityEnd(String businessValidityEnd) {
        this.businessValidityEnd = businessValidityEnd == null ? null : businessValidityEnd.trim();
    }

    public String getRegisterProvince() {
        return registerProvince;
    }

    public void setRegisterProvince(String registerProvince) {
        this.registerProvince = registerProvince == null ? null : registerProvince.trim();
    }

    public String getRegisterCity() {
        return registerCity;
    }

    public void setRegisterCity(String registerCity) {
        this.registerCity = registerCity == null ? null : registerCity.trim();
    }

    public String getRegisterArea() {
        return registerArea;
    }

    public void setRegisterArea(String registerArea) {
        this.registerArea = registerArea == null ? null : registerArea.trim();
    }

    public String getRegisterDetails() {
        return registerDetails;
    }

    public void setRegisterDetails(String registerDetails) {
        this.registerDetails = registerDetails == null ? null : registerDetails.trim();
    }

    public String getInstitutionalValidityStart() {
        return institutionalValidityStart;
    }

    public void setInstitutionalValidityStart(String institutionalValidityStart) {
        this.institutionalValidityStart = institutionalValidityStart == null ? null : institutionalValidityStart.trim();
    }

    public String getInstitutionalValidityEnd() {
        return institutionalValidityEnd;
    }

    public void setInstitutionalValidityEnd(String institutionalValidityEnd) {
        this.institutionalValidityEnd = institutionalValidityEnd == null ? null : institutionalValidityEnd.trim();
    }

    public String getBankLocalProvince() {
        return bankLocalProvince;
    }

    public void setBankLocalProvince(String bankLocalProvince) {
        this.bankLocalProvince = bankLocalProvince == null ? null : bankLocalProvince.trim();
    }

    public String getBankLocalCity() {
        return bankLocalCity;
    }

    public void setBankLocalCity(String bankLocalCity) {
        this.bankLocalCity = bankLocalCity == null ? null : bankLocalCity.trim();
    }

    public String getBankLocalArea() {
        return bankLocalArea;
    }

    public void setBankLocalArea(String bankLocalArea) {
        this.bankLocalArea = bankLocalArea == null ? null : bankLocalArea.trim();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getSupplierExpandId() {
		return supplierExpandId;
	}

	public void setSupplierExpandId(String supplierExpandId) {
		this.supplierExpandId = supplierExpandId;
	}

	public String getIsOld() {
		return isOld;
	}

	public void setIsOld(String isOld) {
		this.isOld = isOld;
	}

	public Integer getMaxCouponAmount() {
		return maxCouponAmount;
	}

	public void setMaxCouponAmount(Integer maxCouponAmount) {
		this.maxCouponAmount = maxCouponAmount;
	}

	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}