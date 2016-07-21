/**
 * 
 */
package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同
 * 
 * @author huang.tao
 *
 */
public class ContractVo implements Serializable {

	private static final long serialVersionUID = 8701829412025847564L;
	
	private String id;
	
	private String supplierId;
	
	private String contractNo;
	
	private Integer isValid;
	
	private String supplier;
	
	private String supplierCode;
	
	private Integer clearingForm;
	
	private Date effectiveDate;
	
	private Date failureDate;
	
	private String updateTime;
	
	private String updateUser;
	
	private String attachment;

	//操作人(附加字段)
	private String operationtor;
	
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

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public Integer getClearingForm() {
		return clearingForm;
	}

	public void setClearingForm(Integer clearingForm) {
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
	
	public String getOperationtor() {
		return operationtor;
	}

	public void setOperationtor(String operationtor) {
		this.operationtor = operationtor;
	}
	
	
}
