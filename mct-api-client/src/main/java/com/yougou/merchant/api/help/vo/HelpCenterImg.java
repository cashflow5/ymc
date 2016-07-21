package com.yougou.merchant.api.help.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家帮助中心图片实体
 * 
 * @author huang.tao
 *
 */
public class HelpCenterImg implements Serializable {

	private static final long serialVersionUID = 4223630121266688780L;

	private String id;
	
	/** 图片名称 */
	private String picName;
	
	private Date created;

	private Date updated;
	
	public HelpCenterImg() {}

	public HelpCenterImg(String picName, Date created,
			Date updated) {
		this.picName = picName;
		this.created = created;
		this.updated = updated;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}


}
