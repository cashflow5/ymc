package com.yougou.dto.output;
/**
 * 
 * @author 郑乔乔
 * 
 */
public class ReturnWarehouseOutputDto extends OutputDto {

	private static final long serialVersionUID = -3547416916611124183L;
	
	/** 退回的仓库名称 */
	private String warehouseName;	
	/** 退回的仓库地址 */
	private String warehouseAddress;
	/** 退回的邮政编码 */
	private String zipCode;
	/** 接收人 */
	private String receiver;
	/** 手机 */
	private String mobilePhone;
	/** 电话 */
	private String telPhone;

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	
}
