package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;


/**
 * 商家收货退货地址设置
 * @author wang.m
 * @DATE 2012-05-11
 *
 */
public class MerchantRejectedAddressVo implements Serializable {
	
	private static final long serialVersionUID = 8283287794130814835L;
	
	private String id;
	private String rejectedAddressId;//供页面保存id使用
	private String supplierName;//商家名称
	private String supplierCode;//商家编号
	private String consigneeName;//收货人姓名
	private String consigneePhone;//收货人手机
	private String consigneeTell;//收货人电话
	private String warehousePostcode;//收货仓库邮编
	private String warehouseArea;//收货仓库地区
	private String warehouseAdress;//收货仓库地址
	private String createrTime;//创建时间
	private String createrPerson;//创建人
	
	private String afterSaleProvince;//将收货仓库地区分割成三部分 供前台展示
	private String afterSaleCity;//将收货仓库地区分割成三部分 供前台展示
	private String afterSaleArea;//将收货仓库地区分割成三部分 供前台展示

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
	
	

	public String getWarehouseArea() {
		return warehouseArea;
	}

	public String getWarehousePostcode() {
		return warehousePostcode;
	}

	public void setWarehousePostcode(String warehousePostcode) {
		this.warehousePostcode = warehousePostcode;
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

	public MerchantRejectedAddressVo(String id, String supplierName,
			String supplierCode, String consigneeName, String consigneePhone,
			String consigneeTell, String warehousePostcode, String warehouseArea,
			String warehouseAdress, String createrTime, String createrPerson) {
		super();
		this.id = id;
		this.supplierName = supplierName;
		this.supplierCode = supplierCode;
		this.consigneeName = consigneeName;
		this.consigneePhone = consigneePhone;
		this.consigneeTell = consigneeTell;
		this.warehousePostcode = warehousePostcode;
		this.warehouseArea = warehouseArea;
		this.warehouseAdress = warehouseAdress;
		this.createrTime = createrTime;
		this.createrPerson = createrPerson;
	}

	public MerchantRejectedAddressVo() {
		super();
	}

	public String getRejectedAddressId() {
		return rejectedAddressId;
	}

	public void setRejectedAddressId(String rejectedAddressId) {
		this.rejectedAddressId = rejectedAddressId;
	}

	public String getAfterSaleProvince() {
		return afterSaleProvince;
	}

	public String getAfterSaleCity() {
		return afterSaleCity;
	}

	public String getAfterSaleArea() {
		return afterSaleArea;
	}

	public void setAfterSaleProvince(String afterSaleProvince) {
		this.afterSaleProvince = afterSaleProvince;
	}

	public void setAfterSaleCity(String afterSaleCity) {
		this.afterSaleCity = afterSaleCity;
	}

	public void setAfterSaleArea(String afterSaleArea) {
		this.afterSaleArea = afterSaleArea;
	}
	
	public void initAddressForForm(){
		String pst = this.warehouseArea;
		if (StringUtils.isNotBlank(pst)) {
			String[] units = pst.trim().split("-");
			if (null != units && units.length > 0) {
				String province = units[0];
				this.afterSaleProvince = province;
				if( units.length>1 ){
					this.afterSaleCity = units[1];
				}
				if( units.length>2 ){
					this.afterSaleArea = units[2];
				}
			}
		}
	}
}
