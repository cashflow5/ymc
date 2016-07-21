/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huang.tao
 *
 */
public class MonitorTemplateDetail implements Serializable {

	private static final long serialVersionUID = -235975555326266247L;
	
	private String id;
	//模板编号
	private String templateNo;
	//apiId
	private String apiId;
	//频率上限
	private Integer frequency;
	//频率单位  1：次/时  2：次/分钟  3：次/秒
	private Integer frequencyUnit;
	//日调用次数上限
	private Integer callNum;
	//是否开启频率限制
	private Integer isFrequency;
	//是否开启日调用次数限制
	private Integer isCallNum;
	
	private Date createTime;
	private Date updateTime;
	
	/**
	 * 模板关联字段
	 */
	private String apiCode;
	private String apiName;
	private String categoryName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTemplateNo() {
		return templateNo;
	}
	public void setTemplateNo(String templateNo) {
		this.templateNo = templateNo;
	}
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	public Integer getFrequencyUnit() {
		return frequencyUnit;
	}
	public void setFrequencyUnit(Integer frequencyUnit) {
		this.frequencyUnit = frequencyUnit;
	}
	public Integer getCallNum() {
		return callNum;
	}
	public void setCallNum(Integer callNum) {
		this.callNum = callNum;
	}
	public Integer getIsFrequency() {
		return isFrequency;
	}
	public void setIsFrequency(Integer isFrequency) {
		this.isFrequency = isFrequency;
	}
	public Integer getIsCallNum() {
		return isCallNum;
	}
	public void setIsCallNum(Integer isCallNum) {
		this.isCallNum = isCallNum;
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
	public String getApiCode() {
		return apiCode;
	}
	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
