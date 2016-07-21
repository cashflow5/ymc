/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * API接口监控配置对象
 * 
 * @author huang.tao
 *
 */
public class MonitorConfig implements Serializable{

	private static final long serialVersionUID = -457511337163863341L;
	
	private String id;
	//配置名称
	private String configName;
	//配置key
	private String configKey;
	//配置value
	private String configValue;
	//备注
	private String remark;
	//删除标示 1：有效 0：删除
	private String deleteFlag;
	private Date createTime;
	private Date updateTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public String getConfigKey() {
		return configKey;
	}
	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
