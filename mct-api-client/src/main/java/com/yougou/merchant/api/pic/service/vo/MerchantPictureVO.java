package com.yougou.merchant.api.pic.service.vo;

import java.io.Serializable;
import java.util.Date;

public class MerchantPictureVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String picName;// 商家图片名称
	private String srcPicName;// 商家图片名称
	private Date createdStart;//图片上传时间
	private Date createdEnd;//图片上传时间
	private String merchantCode;// 商家编码
	private String catalogId;// 目录id
	private String shopId;//店铺id
	private Integer picType;//图片类型
	private String orderBy; //2:默认按时间正序排列  1名称排序
	
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public Date getCreatedStart() {
		return createdStart;
	}
	public void setCreatedStart(Date createdStart) {
		this.createdStart = createdStart;
	}
	public Date getCreatedEnd() {
		return createdEnd;
	}
	public void setCreatedEnd(Date createdEnd) {
		this.createdEnd = createdEnd;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getSrcPicName() {
		return srcPicName;
	}
	public void setSrcPicName(String srcPicName) {
		this.srcPicName = srcPicName;
	}
	public Integer getPicType() {
		return picType;
	}
	public void setPicType(Integer picType) {
		this.picType = picType;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
