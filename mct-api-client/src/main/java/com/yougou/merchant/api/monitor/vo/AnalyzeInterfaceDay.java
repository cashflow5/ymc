/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 按API接口统计汇总（天维度）
 * 
 * @author huang.tao
 *
 */
public class AnalyzeInterfaceDay implements Serializable {

	private static final long serialVersionUID = 1160104358182870482L;
	
	private String id;
	private String apiId;
	private Date createTime;
	//统计时间段
	private String timeQuantum;
	private Integer callCount;
	private Integer validCallCount;
	private Integer sucessCallCount;
	private Integer failCallCount;
	//最高频率
	private Integer maxFrequency;
	//平均执行时间
	private Double avgExTime;
	//接口最大appkey并发数
	private Integer maxAppkeyNums;
	
	private String apiName;
	//平均频率
	private Integer avgFrequency;
	//成功率
	private Double successRate;
	
	//临时字段（排序字段）
	private Integer rankingCall;
	private Integer rankingFrequency;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
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
	public Integer getMaxAppkeyNums() {
		return maxAppkeyNums;
	}
	public void setMaxAppkeyNums(Integer maxAppkeyNums) {
		this.maxAppkeyNums = maxAppkeyNums;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public Integer getAvgFrequency() {
		return avgFrequency;
	}
	public void setAvgFrequency(Integer avgFrequency) {
		this.avgFrequency = avgFrequency;
	}
	public Double getSuccessRate() {
		return successRate;
	}
	public void setSuccessRate(Double successRate) {
		this.successRate = successRate;
	}
	public Integer getRankingCall() {
		return rankingCall;
	}
	public void setRankingCall(Integer rankingCall) {
		this.rankingCall = rankingCall;
	}
	public Integer getRankingFrequency() {
		return rankingFrequency;
	}
	public void setRankingFrequency(Integer rankingFrequency) {
		this.rankingFrequency = rankingFrequency;
	}
	
	
}
