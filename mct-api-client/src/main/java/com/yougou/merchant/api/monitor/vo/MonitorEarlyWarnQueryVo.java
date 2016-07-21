package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;

/**
 * Api监控预警查询vo
 * 
 * @author huang.tao
 *
 */
public class MonitorEarlyWarnQueryVo implements Serializable {
	
	private static final long serialVersionUID = -6729987240848350182L;
	
	//appKey 对应到数据库的appkey
	private String appKey;
	
	private String startTime;
	
	private String endTime;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
