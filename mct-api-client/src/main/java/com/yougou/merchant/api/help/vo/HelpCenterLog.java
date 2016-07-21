package com.yougou.merchant.api.help.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家帮助中心内容实体
 * 
 * @author huang.tao
 *
 */
public class HelpCenterLog implements Serializable {

	private static final long serialVersionUID = -3066049418499259560L;

	private String id;
	
	/** 菜单ID */
	private String menuId;
	
	/** 操作内容 */
	private String content;
	
	private Date updateTime;
	
	private String operator;
	
	public HelpCenterLog() {}

	public HelpCenterLog(String menuId, String content,
			Date updateTime, String operator) {
		this.menuId = menuId;
		this.content = content;
		this.updateTime = updateTime;
		this.operator = operator;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}


	
}
