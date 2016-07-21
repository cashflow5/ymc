/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 无效IP监控
 * 
 * @author huang.tao
 *
 */
public class MonitorInvalidIp implements Serializable {

	private static final long serialVersionUID = -8867265605216680125L;
	
	private String id;
	//统计时间段
	private String timeQuantum;
	//可疑类型:0:AppKey不存在,1未开启
	private Integer doubtType;
	private String ip;
	//无效请求次数
	private Integer invalidCount;
	//最后请求时间
	private Date lastCallTime;
	
	//临时字段（标示ip是否已经被加入黑名单了） 0:已经加入黑名单  1：未加入黑名单
	private Integer isBlackList;
	
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
	public Integer getDoubtType() {
		return doubtType;
	}
	public void setDoubtType(Integer doubtType) {
		this.doubtType = doubtType;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getInvalidCount() {
		return invalidCount;
	}
	public void setInvalidCount(Integer invalidCount) {
		this.invalidCount = invalidCount;
	}
	public Date getLastCallTime() {
		return lastCallTime;
	}
	public void setLastCallTime(Date lastCallTime) {
		this.lastCallTime = lastCallTime;
	}
	public Integer getIsBlackList() {
		return isBlackList;
	}
	public void setIsBlackList(Integer isBlackList) {
		this.isBlackList = isBlackList;
	}
	
}
