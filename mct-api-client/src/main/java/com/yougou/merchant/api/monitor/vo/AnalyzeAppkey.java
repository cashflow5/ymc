/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 按AppKey统计汇总（小时维度）
 * 
 * @author huang.tao
 *
 */
public class AnalyzeAppkey implements Serializable {

	private static final long serialVersionUID = 2877393367426607283L;
	
	private String id;
	//appKe
	private String appKey;
	//创建时间
	private Date createTime;
	//统计时间段
	private String timeQuantum;
	//调用次数
	private Integer callCount;
	//有效调用次数
	private Integer validCallCount;
	//成功调用次数
	private Integer sucessCallCount;
	//失败调用次数
	private Integer failCallCount;
	
	//最高频率（次/时）
	private Integer maxFrequency;
	//平均执行时间（ms）
	private Double avgExTime;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTimeQuantum() {
		return timeQuantum;
	}
	public void setTimeQuantum(String timeQuantum) {
		this.timeQuantum = timeQuantum;
	}
	public Integer getCallCount() {
		return callCount;
	}
	public void setCallCount(Integer callCount) {
		this.callCount = callCount;
	}
	public Integer getValidCallCount() {
		return validCallCount;
	}
	public void setValidCallCount(Integer validCallCount) {
		this.validCallCount = validCallCount;
	}
	public Integer getSucessCallCount() {
		return sucessCallCount;
	}
	public void setSucessCallCount(Integer sucessCallCount) {
		this.sucessCallCount = sucessCallCount;
	}
	public Integer getFailCallCount() {
		return failCallCount;
	}
	public void setFailCallCount(Integer failCallCount) {
		this.failCallCount = failCallCount;
	}
	public Integer getMaxFrequency() {
		return maxFrequency;
	}
	public void setMaxFrequency(Integer maxFrequency) {
		this.maxFrequency = maxFrequency;
	}
	public Double getAvgExTime() {
		return avgExTime;
	}
	public void setAvgExTime(Double avgExTime) {
		this.avgExTime = avgExTime;
	}
	

}
