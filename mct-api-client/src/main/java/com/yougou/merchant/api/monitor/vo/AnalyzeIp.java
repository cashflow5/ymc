/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;

/**
 * IP连接数-按天存储
 * 
 * @author huang.tao
 *
 */
public class AnalyzeIp implements Serializable {

	private static final long serialVersionUID = 2725038774033014132L;
	
	private String id;
	//统计时间段
	private String timeQuantum;
	
	//appKey
	private String appKey;
	
	//Ip地址
	private String ip;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTimeQuantum() {
		return timeQuantum;
	}
	public void setTimeQuantum(String timeQuantum) {
		this.timeQuantum = timeQuantum;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
