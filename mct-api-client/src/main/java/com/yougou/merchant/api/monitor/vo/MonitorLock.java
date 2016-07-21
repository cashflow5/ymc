/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 锁定
 * 
 * @author huang.tao
 *
 */
public class MonitorLock implements Serializable {

	private static final long serialVersionUID = 5228088579448587000L;
	
	private String id;
	private String appKey;
	private String apiId;
	//锁定的类型  0：频率锁定  1：流量锁定
	private Integer lockType;
	//锁定时间
	private Date lockTime;
	//锁定时长
	private Integer lockHour;
	//锁定状态  1:已锁定 0:已解除
	private Integer lockStatus;
	private Date updateTime;
	//预计解锁时间
	private Date unlockTime;
	//解锁修改人
	private String updateUser;
	
	/*
	 * 临时字段 
	 */
	//appKey持有者
	private String appKeyHolder;
	//锁定对象（api名称）
	private String apiName;
	//商家编码
	private String merchantCode;
	//接口查询条件
	private String startTime; 
	private String endTime;
	//已锁时长 (eg. 13小时10分)
	private String lockTimeStr; 
	//剩余锁定时长(eg. )
	private String remainLockTimeStr;
	
	/** 锁定剩余时长 */
	private Integer remain;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public Integer getLockType() {
		return lockType;
	}
	public void setLockType(Integer lockType) {
		this.lockType = lockType;
	}
	public Date getLockTime() {
		return lockTime;
	}
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	public Integer getLockHour() {
		return lockHour;
	}
	public void setLockHour(Integer lockHour) {
		this.lockHour = lockHour;
	}
	public Integer getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getUnlockTime() {
		return unlockTime;
	}
	public void setUnlockTime(Date unlockTime) {
		this.unlockTime = unlockTime;
	}
	public String getAppKeyHolder() {
		return appKeyHolder;
	}
	public void setAppKeyHolder(String appKeyHolder) {
		this.appKeyHolder = appKeyHolder;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getRemain() {
		return remain;
	}
	public void setRemain(Integer remain) {
		this.remain = remain;
	}
	public String getLockTimeStr() {
		return lockTimeStr;
	}
	public void setLockTimeStr(String lockTimeStr) {
		this.lockTimeStr = lockTimeStr;
	}
	public String getRemainLockTimeStr() {
		return remainLockTimeStr;
	}
	public void setRemainLockTimeStr(String remainLockTimeStr) {
		this.remainLockTimeStr = remainLockTimeStr;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}
