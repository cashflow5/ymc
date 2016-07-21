package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 招商系统 商家用户信息表
 * 
 * @author huang.tao
 *
 */
public class MerchantUser implements Serializable {
	
	private static final long serialVersionUID = 8144913010062635943L;

	private String id;
	
	private String merchantUserId;//供页面保存id使用
	  
	private String merchantCode;//商家编号
	
	private String merchantName;
	
	private String userName;//商家正是姓名
	
	private String loginName;//商家登录名称
	
	private String password;//密码
	
	private String mobileCode;//手机号码
	
	private String creater;//创建人

	private String createTime;//创建时间

	private Integer status;//状态  默认1 启用  0 表示锁定
	
	private String remark;//备注

	private Integer isAdministrator;//是否为管理员 0 不是  1 是
	
	private Integer deleteFlag;//删除标志 0表示已删除  1表示未删除
	
	private Integer isYougouAdmin;//是否优购管理员 0 不是  1 是
	
	private String email;//邮箱
	
	private Integer emailstatus;//邮箱是否激活 0未激活 1激活
	
	//1低  2中  3高
	private String strength;	//密码强度
 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileCode() {
		return mobileCode;
	}

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsAdministrator() {
		return isAdministrator;
	}

	public void setIsAdministrator(Integer isAdministrator) {
		this.isAdministrator = isAdministrator;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getIsYougouAdmin() {
		return isYougouAdmin;
	}

	public void setIsYougouAdmin(Integer isYougouAdmin) {
		this.isYougouAdmin = isYougouAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmailstatus() {
		return emailstatus;
	}

	public void setEmailstatus(Integer emailstatus) {
		this.emailstatus = emailstatus;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getMerchantUserId() {
		return merchantUserId;
	}

	public void setMerchantUserId(String merchantUserId) {
		this.merchantUserId = merchantUserId;
	}
 

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
