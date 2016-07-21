package com.yougou.merchant.api.pic.service.vo;

import java.io.Serializable;
import java.util.Date;

public class MerchantPicture implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;// 优购图片ID
	private String picName;// 商家图片名称
	private String thumbnaiPicName;// 商家图片缩略图名称
	private String picPath;// 商家图片路径
	private Long picSize;// 图片大小(字节)
	private Date created;//图片上传时间
	private String merchantCode;// 商家编码
	private String catalogId;// 目录id
	private Integer picType;//图片类型
	private String sourcePicName; //图片原名称
	private Integer width;//图片宽度
	private Integer height;//图片高度
	private String shopId;//店铺ID
	
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
	public String getThumbnaiPicName() {
		return thumbnaiPicName;
	}
	public void setThumbnaiPicName(String thumbnaiPicName) {
		this.thumbnaiPicName = thumbnaiPicName;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public Long getPicSize() {
		return picSize;
	}
	public void setPicSize(Long picSize) {
		this.picSize = picSize;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
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
	public Integer getPicType() {
		return picType;
	}
	public void setPicType(Integer picType) {
		this.picType = picType;
	}
	public String getSourcePicName() {
		return sourcePicName;
	}
	public void setSourcePicName(String sourcePicName) {
		this.sourcePicName = sourcePicName;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
}
