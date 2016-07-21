/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * API监控黑名单
 * 
 * @author huang.tao
 *
 */
public class MonitorIpBlackList implements Serializable {

	private static final long serialVersionUID = -1144588333134469320L;
	
	private String id;
	private String ip;
	private String reason;
	private String operator;
	//是否有效，1:有效 0:作废
	private Integer deleteFag;
	private Date createTime;
	private Date updateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Integer getDeleteFag() {
		return deleteFag;
	}
	public void setDeleteFag(Integer deleteFag) {
		this.deleteFag = deleteFag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


}
