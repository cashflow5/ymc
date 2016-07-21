package com.yougou.merchant.api.supplier.vo;

/**
 * 商家退换货地址（仓库）
 * @author zhuang.rb
 *
 */
public class RejectedAddressVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String supplierName;//商家名称
	private String supplierCode;//商家编码
	private String consigneeName;//收货人姓名
	private String consigneePhone;//收货人手机
	private String consigneeTell;//收货人电话
	private String warehousePostcode;//收货人邮编
	private String warehouseArea;//收货人省市区
	private String warehouseAdress;//收货人详细地址
	private String createrTime;
	private String createrPerson;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneePhone() {
		return consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	public String getConsigneeTell() {
		return consigneeTell;
	}

	public void setConsigneeTell(String consigneeTell) {
		this.consigneeTell = consigneeTell;
	}

	public String getWarehousePostcode() {
		return warehousePostcode;
	}

	public void setWarehousePostcode(String warehousePostcode) {
		this.warehousePostcode = warehousePostcode;
	}

	public String getWarehouseArea() {
		return warehouseArea;
	}

	public void setWarehouseArea(String warehouseArea) {
		this.warehouseArea = warehouseArea;
	}

	public String getWarehouseAdress() {
		return warehouseAdress;
	}

	public void setWarehouseAdress(String warehouseAdress) {
		this.warehouseAdress = warehouseAdress;
	}

	public String getCreaterTime() {
		return createrTime;
	}

	public void setCreaterTime(String createrTime) {
		this.createrTime = createrTime;
	}

	public String getCreaterPerson() {
		return createrPerson;
	}

	public void setCreaterPerson(String createrPerson) {
		this.createrPerson = createrPerson;
	}

}
