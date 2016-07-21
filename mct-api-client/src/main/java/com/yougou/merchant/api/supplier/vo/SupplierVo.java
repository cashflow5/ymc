package com.yougou.merchant.api.supplier.vo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 供应商信息
 * @author luo.q
 *
 */
public class SupplierVo implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	//合同列表
	private List<SupplierContract> supplierContractList ;

	//操作日志
	private List<MerchantOperationLog> logList;
	
	private String id;
	private String supplierCode;// 供应商编码
	private String supplier;// 供应商名称
	private String englishName;// 供应商英文名
	private String simpleName;// 供应商简写
	private String contact;// 开户人
	private String telePhone;// 电话
	private String email;// 电子邮件
	private String fax;// 传真
	private String address;// 地址
	private String url;// 网址
	private String remark;// 备注
	private Integer isValid = 1;// 状态 1启用 2新建 -1 停用
	private String supplierType;// 供应商类型
	private String bank;// 开户行
	private String subBank;// 支行
	private String dutyCode;// 税号
	private String creator;// 创建人
	private String account;// 账户
	private String payType;// 支付方式
	private Integer conTime;// 合作期限
	private String loginAccount;// 登录名
	private String loginPassword;// 登录密码
	private Integer isConfig;// 是否用做供应商商品管理 1适用 0 或 null不适用
	private Date updateDate; // 修改时间
	private String updateUser; // 修改人
	private Double taxRate; // 税率
	private Double couponsAllocationProportion; // 优惠券分摊比例
	private String businessLicense;// 营业执照号
	private String businessLocal;// 营业执照所在地
	private String businessValidity;// 营业执照有效期
	private String tallageNo;// 纳税人识别号（税务登记证号）
	private String institutional;// 组织机构代码
	private String taxpayer;// 一般纳税人
	private String bankLocal;// 开户行银行所在地
	private String inventoryCode;// 仓库名称
	private Integer isInputYougouWarehouse;// 是否入优购仓库0:不入优购仓库，商家发货；1：入优购仓库，优购发货；2:不入优购仓库，优购发货
	private String setOfBooksCode;// 成本帐套编号
	private String setOfBooksName;// 成本账套名称
	private String balanceTraderCode;// 结算商家编码
	private String balanceTraderName;// 结算商家名字balance_trader_name
	private String posSourceNo;// pos供应商编码
	private Integer deleteFlag;// 删除标志 0表示已删除 1表示未删除
	private Integer shipmentType;// 按发货预结算 1：按发货预结算；0：不按发货预结算；
	private Long updateTimestamp;
	private String tradeCurrency;//使用外币支付账户(枚举类型)
	private Integer isUseYougouWms;//是否使用优购WMS 默认否0 是1 
	private Integer isHongKong;//是否是香港直发供应商1：不是，2：是
	private Integer isInvoice;//是否是开发票1：是，0：否
	private Integer isAddValueInvoice;//是否增长税发票1：是，0：否
    private String creatorname;
    private String invoiceName;
    private String invoiceAddress;
    private String  invoicePhone;
    private String isDirect;
    private String taxplayerType;
    private String updateUsername;
    private String supplierTypeCode;
    private String factoryNo;
    
    /***************** newly added start from here  ******/
    
    private Integer markRemainingDays;
   
    private Integer contractRemainingDays;
   
    private Integer businessRemainingDays;
	
    private Integer institutionalRemainingDays;
    
    private Integer expireNum;//资质过期数目
    
    private Integer approachExpireNum;//资质即将过期数目
    
    private String isNewContract;
    
    private String isNeedRenew;
    
    private String renewContractStatus;
    
    /***************** newly added ends **********/
	
	//临时变量
	private String brandNo;
	
	//账户信息
	private MerchantUser user;
	
	//品牌和分类授权
	private List<BrandVo> brandVos;
	private List<CatVo> catVos;
	private List<BrandCatRelation> brandcatRelations;
	
	// 发货类型
	private String deliveryType;
	//结算银行名
	private String clearBank;
	//结算银行账号
	private String clearAccount;
	  
	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getBrandNo() {
		return brandNo;
	}

	public List<MerchantOperationLog> getLogList() {
		return logList;
	}

	public void setLogList(List<MerchantOperationLog> logList) {
		this.logList = logList;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getBusinessLocal() {
		return businessLocal;
	}

	public void setBusinessLocal(String businessLocal) {
		this.businessLocal = businessLocal;
	}


	public String getTallageNo() {
		return tallageNo;
	}

	public void setTallageNo(String tallageNo) {
		this.tallageNo = tallageNo;
	}

	public String getInstitutional() {
		return institutional;
	}

	public void setInstitutional(String institutional) {
		this.institutional = institutional;
	}

	public String getTaxpayer() {
		return taxpayer;
	}

	public void setTaxpayer(String taxpayer) {
		this.taxpayer = taxpayer;
	}

	public String getBankLocal() {
		return bankLocal;
	}

	public void setBankLocal(String bankLocal) {
		this.bankLocal = bankLocal;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSupplierCode() {
		return this.supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getEnglishName() {
		return this.englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getSimpleName() {
		return this.simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTelePhone() {
		return this.telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getSupplierType() {
		return this.supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getSubBank() {
		return this.subBank;
	}

	public void setSubBank(String subBank) {
		this.subBank = subBank;
	}

	public String getDutyCode() {
		return this.dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPayType() {
		return this.payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getConTime() {
		return this.conTime;
	}

	public void setConTime(Integer conTime) {
		this.conTime = conTime;
	}

	public String getLoginAccount() {
		return this.loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public Integer getIsConfig() {
		return isConfig;
	}

	public void setIsConfig(Integer isConfig) {
		this.isConfig = isConfig;
	}

	public Long getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Long updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public Double getCouponsAllocationProportion() {
		return couponsAllocationProportion;
	}

	public void setCouponsAllocationProportion(
			Double couponsAllocationProportion) {
		this.couponsAllocationProportion = couponsAllocationProportion;
	}

	public Integer getIsInputYougouWarehouse() {
		return isInputYougouWarehouse;
	}

	public void setIsInputYougouWarehouse(Integer isInputYougouWarehouse) {
		this.isInputYougouWarehouse = isInputYougouWarehouse;
	}

	public String getSetOfBooksCode() {
		return setOfBooksCode;
	}

	public void setSetOfBooksCode(String setOfBooksCode) {
		this.setOfBooksCode = setOfBooksCode;
	}

	public String getSetOfBooksName() {
		return setOfBooksName;
	}

	public void setSetOfBooksName(String setOfBooksName) {
		this.setOfBooksName = setOfBooksName;
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

	public String getPosSourceNo() {
		return posSourceNo;
	}

	public void setPosSourceNo(String posSourceNo) {
		this.posSourceNo = posSourceNo;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(Integer shipmentType) {
		this.shipmentType = shipmentType;
	}

	public String getTradeCurrency() {
		return tradeCurrency;
	}

	public void setTradeCurrency(String tradeCurrency) {
		this.tradeCurrency = tradeCurrency;
	}

	public Integer getIsUseYougouWms() {
		return isUseYougouWms;
	}

	public void setIsUseYougouWms(Integer isUseYougouWms) {
		this.isUseYougouWms = isUseYougouWms;
	}

	public Integer getIsHongKong() {
		return isHongKong;
	}

	public void setIsHongKong(Integer isHongKong) {
		this.isHongKong = isHongKong;
	}

	public List<BrandVo> getBrandVos() {
		return brandVos;
	}

	public void setBrandVos(List<BrandVo> brandVos) {
		this.brandVos = brandVos;
	}

	public List<CatVo> getCatVos() {
		return catVos;
	}

	public void setCatVos(List<CatVo> catVos) {
		this.catVos = catVos;
	}

	public List<BrandCatRelation> getBrandcatRelations() {
		return brandcatRelations;
	}

	public void setBrandcatRelations(List<BrandCatRelation> brandcatRelations) {
		this.brandcatRelations = brandcatRelations;
	}

	public MerchantUser getUser() {
		return user;
	}

	public void setUser(MerchantUser user) {
		this.user = user;
	}
	public Integer getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}

	public Integer getIsAddValueInvoice() {
		return isAddValueInvoice;
	}

	public void setIsAddValueInvoice(Integer isAddValueInvoice) {
		this.isAddValueInvoice = isAddValueInvoice;
	}

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public String getInvoiceAddress() {
		return invoiceAddress;
	}

	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	public String getInvoicePhone() {
		return invoicePhone;
	}

	public void setInvoicePhone(String invoicePhone) {
		this.invoicePhone = invoicePhone;
	}

	public String getIsDirect() {
		return isDirect;
	}

	public void setIsDirect(String isDirect) {
		this.isDirect = isDirect;
	}

	public String getTaxplayerType() {
		return taxplayerType;
	}

	public void setTaxplayerType(String taxplayerType) {
		this.taxplayerType = taxplayerType;
	}

	public String getUpdateUsername() {
		return updateUsername;
	}

	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}

	public String getSupplierTypeCode() {
		return supplierTypeCode;
	}

	public void setSupplierTypeCode(String supplierTypeCode) {
		this.supplierTypeCode = supplierTypeCode;
	}

	public String getFactoryNo() {
		return factoryNo;
	}

	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}


	public String getBusinessValidity() {
		return businessValidity;
	}

	public void setBusinessValidity(String businessValidity) {
		this.businessValidity = businessValidity;
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

	public Integer getExpireNum() {
		return expireNum;
	}

	public void setExpireNum(Integer expireNum) {
		this.expireNum = expireNum;
	}

	public Integer getApproachExpireNum() {
		return approachExpireNum;
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

	public String getRenewContractStatus() {
		return renewContractStatus;
	}

	public void setRenewContractStatus(String renewContractStatus) {
		this.renewContractStatus = renewContractStatus;
	}

	/**
	 * 合同列表
	 * @return
	 */
	public List<SupplierContract> getSupplierContractList() {
		return supplierContractList;
	}

	public void setSupplierContractList(List<SupplierContract> supplierContractList) {
		this.supplierContractList = supplierContractList;
	}

	public void setApproachExpireNum(Integer approachExpireNum) {
		this.approachExpireNum = approachExpireNum;

	}

	public String getClearBank() {
		return clearBank;
	}

	public void setClearBank(String clearBank) {
		this.clearBank = clearBank;
	}

	public String getClearAccount() {
		return clearAccount;
	}

	public void setClearAccount(String clearAccount) {
		this.clearAccount = clearAccount;
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
