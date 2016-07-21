package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家菜单资源列表(tbl_merchant_authority)
 * 
 * @author huang.tao
 *
 */
public class MerchantMenu implements Serializable {

	private static final long serialVersionUID = 71555537982119002L;
	
	private String id;
	
	private String authrityName;//菜单名称
	
	private String authrityURL;//菜单url
	
	private Integer authrityModule;//所属模块  0 根模块 1 商品模块  2 订单模3 库存 4结算 4 设置
	
	private Integer sortNo;//排序号
	
	private Date createTime;//添加时间
	
	private String remark;//备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthrityName() {
		return authrityName;
	}

	public void setAuthrityName(String authrityName) {
		this.authrityName = authrityName;
	}

	public String getAuthrityURL() {
		return authrityURL;
	}

	public void setAuthrityURL(String authrityURL) {
		this.authrityURL = authrityURL;
	}

	public Integer getAuthrityModule() {
		return authrityModule;
	}

	public void setAuthrityModule(Integer authrityModule) {
		this.authrityModule = authrityModule;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
