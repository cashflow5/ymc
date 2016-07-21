package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 首尔直发订单退回单详情
 * @author zhang.f1
 *
 */
public class BackOrderDetailVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "BackOrderDetailVo [id=" + id + ", mainId=" + mainId
				+ ", commodityName=" + commodityName + ", specKey=" + specKey
				+ ", orderSubNo=" + orderSubNo + ", insideCode=" + insideCode
				+ ", supplierCode=" + supplierCode + ", productNo=" + productNo
				+ ", type=" + type + ", planBackCount=" + planBackCount
				+ ", alreadyBackCount=" + alreadyBackCount  + ", commodityPic=" + commodityPic 
				+", updateTime=" + updateTime + "]";
	}
	private String id; //退回单据明细ID
	private String mainId; //退回单据ID
	private String commodityName; //商品名称
	private String specKey;  //规格
	private String orderSubNo; //子订单号
	private String insideCode; //货品条码
	private String supplierCode; //款色编码
	private String productNo; //货品编码
	private String type; //商品类型(正品-SDARD_GOODS,残品-UNSDARD_GOODS)
	private Integer planBackCount; //计划退回数量
	private Integer alreadyBackCount; //已退回数量
	private String updateTime; //修改时间
	private String commodityPic;//商品图片链接（小图）
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMainId() {
		return mainId;
	}
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getSpecKey() {
		return specKey;
	}
	public void setSpecKey(String specKey) {
		this.specKey = specKey;
	}
	public String getOrderSubNo() {
		return orderSubNo;
	}
	public void setOrderSubNo(String orderSubNo) {
		this.orderSubNo = orderSubNo;
	}
	public String getInsideCode() {
		return insideCode;
	}
	public void setInsideCode(String insideCode) {
		this.insideCode = insideCode;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getPlanBackCount() {
		return planBackCount;
	}
	public void setPlanBackCount(Integer planBackCount) {
		this.planBackCount = planBackCount;
	}
	public Integer getAlreadyBackCount() {
		return alreadyBackCount;
	}
	public void setAlreadyBackCount(Integer alreadyBackCount) {
		this.alreadyBackCount = alreadyBackCount;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCommodityPic() {
		return commodityPic;
	}
	public void setCommodityPic(String commodityPic) {
		this.commodityPic = commodityPic;
	}
	
	
}
