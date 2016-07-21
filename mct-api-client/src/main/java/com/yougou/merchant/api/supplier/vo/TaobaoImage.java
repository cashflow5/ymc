package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;

public class TaobaoImage implements Serializable{
	
	/**
	 * 生成的序列号
	 */
	private static final long serialVersionUID = 5950798725264101551L;
	
	
	/**
	 * 全参数构造方法
	 * @param numIid
	 * @param oldImageURL
	 * @param newImageURL
	 * @param thumbnailImageURL
	 */
	public TaobaoImage(Long numIid, String oldImageURL, String newImageURL,
			String thumbnailImageURL) {
		super();
		this.numIid = numIid;
		this.oldImageURL = oldImageURL;
		this.newImageURL = newImageURL;
		this.thumbnailImageURL = thumbnailImageURL;
	}

	/** 淘宝商品id */
	private Long numIid;
	
	/** 原图片URL */
	private String oldImageURL;
	
	/** 新图片URL */
	private String newImageURL;
	
	/** 缩略图URL */
	private String thumbnailImageURL;

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	public String getOldImageURL() {
		return oldImageURL;
	}

	public void setOldImageURL(String oldImageURL) {
		this.oldImageURL = oldImageURL;
	}

	public String getNewImageURL() {
		return newImageURL;
	}

	public void setNewImageURL(String newImageURL) {
		this.newImageURL = newImageURL;
	}

	public String getThumbnailImageURL() {
		return thumbnailImageURL;
	}

	public void setThumbnailImageURL(String thumbnailImageURL) {
		this.thumbnailImageURL = thumbnailImageURL;
	}

	@Override
	public String toString() {
		return "TaobaoImage [numIid=" + numIid + ", oldImageURL=" + oldImageURL
				+ ", newImageURL=" + newImageURL + ", thumbnailImageURL="
				+ thumbnailImageURL + "]";
	}
}
