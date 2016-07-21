/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 监控模板与AppKey的关系对象
 * 
 * @author huang.tao
 *
 */
public class MonitorAppKeyTemplate implements Serializable {

	private static final long serialVersionUID = 9004099841868426013L;
	
	private String id;
	//appKey  
	private String appkeyId;
	//模板编码
	private String templateNo;
	
	private Date createTime;
	private Date updateTime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppkeyId() {
		return appkeyId;
	}

	public void setAppkeyId(String appkeyId) {
		this.appkeyId = appkeyId;
	}

	public String getTemplateNo() {
		return templateNo;
	}

	public void setTemplateNo(String templateNo) {
		this.templateNo = templateNo;
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
