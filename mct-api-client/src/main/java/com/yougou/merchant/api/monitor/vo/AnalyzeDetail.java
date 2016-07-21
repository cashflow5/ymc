/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 统计明细表（小时维度）
 * 
 * @author huang.tao
 *
 */
public class AnalyzeDetail implements Serializable {

	private static final long serialVersionUID = -2048951164018919388L;
	
	private String id;
	
	//appKey
	private String appKey;
	
	//接口Id
	private String apiId;
	private Date createTime;
	//统计时间段 yyyy-MM-dd hh24:00:00
	private String timeQuantum;
	//调用次数
	private Integer callCount;
	//有效调用次数
	private Integer validCallCount;
	//成功调用次数
	private Integer sucessCallCount;
	//失败调用次数
	private Integer failCallCount;
	//最高频率
	private Integer maxFrequency;
	//平均执行时间
	private Double avgExTime;
	
	//平均频率
	private Integer avgFrequency;
	//成功率
	private Double successRate;
	private String appKeyHolder;
	private String apiName;
	
	//临时字段（排序字段）
	private Integer rankingCall;
	private Integer rankingFrequency;
	
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
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
	public String getAppKeyHolder() {
		return appKeyHolder;
	}
	public void setAppKeyHolder(String appKeyHolder) {
		this.appKeyHolder = appKeyHolder;
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
