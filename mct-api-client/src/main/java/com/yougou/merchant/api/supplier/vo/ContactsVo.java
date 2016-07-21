/**
 * 
 */
package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;

/**
 * @author huang.tao
 *
 */
public class ContactsVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String supplyId;
	//供应商名称
	private String supplier;
	//商家编码
	private String supplierCode;
	//内容
	private String contact;
	//类型
	private Integer type;
	//电话
	private String telePhone;
	//手机
	private String mobilePhone;
	//传真
	private String fax;
	//邮箱
	private String email;
	//详细地址
	private String address;
	
	//操作人(附加字段)
	private String operationtor;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOperationtor() {
		return operationtor;
	}

	public void setOperationtor(String operationtor) {
		this.operationtor = operationtor;
	}
	
	
}
