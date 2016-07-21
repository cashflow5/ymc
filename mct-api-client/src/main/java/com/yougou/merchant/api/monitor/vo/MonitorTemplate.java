/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author huang.tao
 *
 */
public class MonitorTemplate implements Serializable {

	private static final long serialVersionUID = 878224614175005252L;
	
	private String id;
	private String templateNo;
	//模板类型  1：普通模板 2：自定义模板
	private Integer referenceType;
	//是否为默认模板 1：默认  0：不是默认
	private Integer isDefault;
	
	private String templateName;
	private String templateDesc;
	private Date createTime;
	private Date updateTime;
	
	//模板对应的规则明细
	private List<MonitorTemplateDetail> templateDetails;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTemplateNo() {
		return templateNo;
	}
	public void setTemplateNo(String templateNo) {
		this.templateNo = templateNo;
	}
	public Integer getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(Integer referenceType) {
		this.referenceType = referenceType;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTemplateDesc() {
		return templateDesc;
	}
	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
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
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	public List<MonitorTemplateDetail> getTemplateDetails() {
		return templateDetails;
	}
	public void setTemplateDetails(List<MonitorTemplateDetail> templateDetails) {
		this.templateDetails = templateDetails;
	}
	
	
}
