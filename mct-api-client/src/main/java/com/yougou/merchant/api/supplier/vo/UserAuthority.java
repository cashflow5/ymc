package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户资源中间表(tbl_merchant_user_authority)
 * 
 * @author huang.tao
 *
 */
public class UserAuthority implements Serializable {
	
	private static final long serialVersionUID = -7196130201081232657L;
	
	private String id;//主键ID
	
	private String userId;//用户Id
	
	private String authorityId;//权限ID
	
	private Date createDate;//创建时间
	
	private String remark;//备注

	private String authrityName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAuthrityName() {
		return authrityName;
	}

	public void setAuthrityName(String authrityName) {
		this.authrityName = authrityName;
	}
	
	
}
