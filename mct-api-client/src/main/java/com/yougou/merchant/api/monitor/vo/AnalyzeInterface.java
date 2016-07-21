/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 按API接口统计汇总（小时维度）
 * 
 * @author huang.tao
 *
 */
public class AnalyzeInterface implements Serializable {

	private static final long serialVersionUID = -7263390204645180758L;
	
	private String id;
	//API id
	private String apiId;
	
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
	//最高频率
	private Integer maxFrequency;
	
	//平均执行时间
	private Double avgExTime;
	//接口最大appkey并发数
	private Integer maxAppkeyNums;
	
	//平均频率
	private Integer avgFrequency;
	//成功率
	private Double successRate;
		
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


}
