package com.yougou.merchant.api.help.vo;

import java.io.Serializable;

/**
 * 商家帮助中心菜单实体
 * 
 * @author huang.tao
 *
 */
public class HelpCenterMenu implements Serializable {

	private static final long serialVersionUID = -7037543509432470172L;

	private String id;
	
	/** 菜单名称 */
	private String menuName;
	
	/** 父节点id */
	private String parentId;
	
	/** 节点id */
	private String subId;
	
	/** 菜单级别，从1开始级联 */
	private Integer level;
	
	/** 是否为叶子节点 1:叶子节点 0：非叶子节点 */
	private Integer isLeaf;
	
	/** 排序号 */
	private Integer orderNo;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if (!(obj instanceof HelpCenterMenu)) return false;
		final HelpCenterMenu tempMenu=(HelpCenterMenu)obj;
		if (!this.getMenuName().equals(tempMenu.getMenuName())) return false;
		if (!this.getParentId().equals(tempMenu.getParentId())) return false;
		if (!this.getSubId().equals(tempMenu.getSubId())) return false;
		if (!this.getLevel().equals(tempMenu.getLevel())) return false;
		if (!this.getIsLeaf().equals(tempMenu.getIsLeaf())) return false;
		if (!this.getOrderNo().equals(tempMenu.getOrderNo())) return false;
		return true;
	}
	
	
}
