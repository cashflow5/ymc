package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;
import java.util.List;
/**
 * 银行和开票银行VO
 * @author zhang.wj
 *
 */
public class MerchantBankInfoVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String supplierCode;  //商家编码
	private String supplier;      //商家名称
	private String posSourceNo;   //Pos供应商编码
	private Integer isValid;      //状态
	private String setofbooksCode;//成本帐套编码
	private String setofbooksName;//成本帐套名称
	private String supplierType  ;//供应商类型
	private String balanceTraderCode;//结算商家编码
	private String balanceTraderName;//结算商家名称
	

	private List<String> bankList;    //开户行
	private List<String> accountList; //银行账号
	private String contact;           //联系人/开户人
	
	private String id;
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
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getPosSourceNo() {
		return posSourceNo;
	}
	public void setPosSourceNo(String posSourceNo) {
		this.posSourceNo = posSourceNo;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public String getSetofbooksCode() {
		return setofbooksCode;
	}
	public void setSetofbooksCode(String setofbooksCode) {
		this.setofbooksCode = setofbooksCode;
	}
	public String getSetofbooksName() {
		return setofbooksName;
	}
	public void setSetofbooksName(String setofbooksName) {
		this.setofbooksName = setofbooksName;
	}
	public String getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	public String getBalanceTraderCode() {
		return balanceTraderCode;
	}
	public void setBalanceTraderCode(String balanceTraderCode) {
		this.balanceTraderCode = balanceTraderCode;
	}
	public String getBalanceTraderName() {
		return balanceTraderName;
	}
	public void setBalanceTraderName(String balanceTraderName) {
		this.balanceTraderName = balanceTraderName;
	}
	public List<String> getBankList() {
		return bankList;
	}
	public void setBankList(List<String> bankList) {
		this.bankList = bankList;
	}
	public List<String> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<String> accountList) {
		this.accountList = accountList;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	

	
	
	

}
