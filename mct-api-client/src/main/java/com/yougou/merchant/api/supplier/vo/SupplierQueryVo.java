/**
 * 
 */
package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author huang.tao
 *
 */
public class SupplierQueryVo implements Serializable {

	private static final long serialVersionUID = 4364162456713940938L;
	
	private String supplier;
	
	private String supplierCode;
	
	private Integer isValid;
	
	private Integer isInputYougouWarehouse;
	
	private List<String> brandNos;
	
	private String supplierType;//供应商类型
	
	private String supplierYgContacts;//品牌负责人
	
	private String orderBy ;//按哪列排序
	
	private String sequence;//排序的次序
	
	private String listKind;// 标识为哪个列表页面而查询  见MerchantConstant
	
	private String flagForReminds;// 查询用：只查资质提醒的数据用的字段  默认true
	
	private String isNewContract; //是新合同还是续签合同
	
	private String isNeedRenew;  //是否需要续签
	
	private String updateUser; //更新人

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

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getIsInputYougouWarehouse() {
		return isInputYougouWarehouse;
	}

	public void setIsInputYougouWarehouse(Integer isInputYougouWarehouse) {
		this.isInputYougouWarehouse = isInputYougouWarehouse;
	}

	public List<String> getBrandNos() {
		return brandNos;
	}

	public void setBrandNos(List<String> brandNos) {
		this.brandNos = brandNos;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public String getSupplierYgContacts() {
		return supplierYgContacts;
	}

	public void setSupplierYgContacts(String supplierYgContacts) {
		this.supplierYgContacts = supplierYgContacts;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getListKind() {
		return listKind;
	}

	public void setListKind(String listKind) {
		this.listKind = listKind;
	}

	public String getFlagForReminds() {
		return flagForReminds;
	}

	public void setFlagForReminds(String flagForReminds) {
		this.flagForReminds = flagForReminds;
	}

	public String getIsNewContract() {
		return isNewContract;
	}

	public void setIsNewContract(String isNewContract) {
		this.isNewContract = isNewContract;
	}

	public String getIsNeedRenew() {
		return isNeedRenew;
	}

	public void setIsNeedRenew(String isNeedRenew) {
		this.isNeedRenew = isNeedRenew;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


}
