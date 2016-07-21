/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;

/**
 * 单接口调用频率预警明细
 * 
 * @author huang.tao
 *
 */
public class MonitorRateWarnDetail implements Serializable {

	private static final long serialVersionUID = 3168637812694710298L;
	
	private String id;
	private String appKey;
	private String apiId;
	/** 统计时间段 yyyy-MM-dd HH:mm:ss */
	private String timeQuantum;
	/** API频率上限 */
	private Integer warmRateCallCount;
	/** API实际频率峰值 */
	private Integer rateCallCount;
	
	/** api接口方法名 */
	private String apiMethod;
	/** 实际日调用次数/日调用次数上限比例 (rateCallCount/warmRateCallCount) */
	private Double ratio;
	/** 预警时间段 03:00~04:00 */
	private String hourQuantum;
	
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
	public Integer getWarmRateCallCount() {
		return warmRateCallCount;
	}
	public void setWarmRateCallCount(Integer warmRateCallCount) {
		this.warmRateCallCount = warmRateCallCount;
	}
	public Integer getRateCallCount() {
		return rateCallCount;
	}
	public void setRateCallCount(Integer rateCallCount) {
		this.rateCallCount = rateCallCount;
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
	public String getHourQuantum() {
		return hourQuantum;
	}
	public void setHourQuantum(String hourQuantum) {
		this.hourQuantum = hourQuantum;
	}

}
