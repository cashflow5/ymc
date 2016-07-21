/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;

/**
 * AppKey日调用次数预警明细
 * 
 * @author huang.tao
 *
 */
public class MonitorAppkeyWarnDetail implements Serializable {
	
	private static final long serialVersionUID = 1874210375464900167L;
	
	private String id;
	private String appKey;
	/** 统计时间段yyyy-MM-dd */
	private String timeQuantum;
	/** appkey日调用次数预警（次数） */
	private Integer warmAppkeyCallCount;
	/** appkey实际日调用次数 */
	private Integer appkeyCallCount;
	
	/** 实际日调用次数/日调用次数上限比例 (appkeyCallCount/warmAppkeyCallCount) */
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
	public String getTimeQuantum() {
		return timeQuantum;
	}
	public void setTimeQuantum(String timeQuantum) {
		this.timeQuantum = timeQuantum;
	}
	public Integer getWarmAppkeyCallCount() {
		return warmAppkeyCallCount;
	}
	public void setWarmAppkeyCallCount(Integer warmAppkeyCallCount) {
		this.warmAppkeyCallCount = warmAppkeyCallCount;
	}
	public Integer getAppkeyCallCount() {
		return appkeyCallCount;
	}
	public void setAppkeyCallCount(Integer appkeyCallCount) {
		this.appkeyCallCount = appkeyCallCount;
	}
	public Double getRatio() {
		return ratio;
	}
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
	
	
}
