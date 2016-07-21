/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;

/**
 * 单接口调用成功率预警明细
 * 
 * @author huang.tao
 *
 */
public class MonitorSuccRateWarnDetail implements Serializable {

	private static final long serialVersionUID = -450416943358869304L;
	
	private String id;
	private String appKey;
	private String apiId;
	/** 统计时间段 yyyy-MM-dd */
	private String timeQuantum;
	/** 成功次数 */
	private Integer successCallCount;
	/** 失败次数 */
	private Integer failCallCount;
	
	/** api接口方法名 */
	private String apiMethod;
	/** 实际日调用次数/日调用次数上限比例 (successCallCount/successCallCount+failCallCount) */
	private Double ratio;
	
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
	public String getTimeQuantum() {
		return timeQuantum;
	}
	public void setTimeQuantum(String timeQuantum) {
		this.timeQuantum = timeQuantum;
	}
	public Integer getSuccessCallCount() {
		return successCallCount;
	}
	public void setSuccessCallCount(Integer successCallCount) {
		this.successCallCount = successCallCount;
	}
	public Integer getFailCallCount() {
		return failCallCount;
	}
	public void setFailCallCount(Integer failCallCount) {
		this.failCallCount = failCallCount;
	}
	public String getApiMethod() {
		return apiMethod;
	}
	public void setApiMethod(String apiMethod) {
		this.apiMethod = apiMethod;
	}
	public Double getRatio() {
		return ratio;
	}
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
	
	
}
