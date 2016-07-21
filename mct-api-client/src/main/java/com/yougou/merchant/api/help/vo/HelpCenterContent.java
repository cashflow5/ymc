package com.yougou.merchant.api.help.vo;

import java.io.Serializable;


/**
 * 商家帮助中心内容实体
 * 
 * @author huang.tao
 *
 */
public class HelpCenterContent implements Serializable {

	private static final long serialVersionUID = 237061146051351385L;

	private String id;
	
	/** 菜单ID */
	private String menuId;
	
	/** 帮助内容 */
	private String content;

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
	
	
}
