/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;

/**
 * Api日调用次数预警明细
 * 
 * @author huang.tao
 *
 */
public class MonitorDayWarnDetail implements Serializable {

	private static final long serialVersionUID = 4624892101889481808L;
	
	private String id;
	private String appKey;
	private String apiId;
	/** 统计时间段yyyy-MM-dd */
	private String timeQuantum;
	/** API日调用次数预警（次数） */
	private Integer warmDayCallCount;
	/** API实际日调用次数 */
	private Integer dayCallCount;
	
	/** api接口方法名 */
	private String apiMethod;
	/** 实际日调用次数/日调用次数上限比例 (dayCallCount/warmDayCallCount) */
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
	public Integer getWarmDayCallCount() {
		return warmDayCallCount;
	}
	public void setWarmDayCallCount(Integer warmDayCallCount) {
		this.warmDayCallCount = warmDayCallCount;
	}
	public Integer getDayCallCount() {
		return dayCallCount;
	}
	public void setDayCallCount(Integer dayCallCount) {
		this.dayCallCount = dayCallCount;
	}
	public Double getRatio() {
		return ratio;
	}
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
	public String getApiMethod() {
		return apiMethod;
	}
	public void setApiMethod(String apiMethod) {
		this.apiMethod = apiMethod;
	}

}
