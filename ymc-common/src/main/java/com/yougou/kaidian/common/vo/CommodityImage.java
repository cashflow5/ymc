package com.yougou.kaidian.common.vo;

import java.io.Serializable;
/**
 * 商品图片实体类
 * @author li.m1
 *
 */
public class CommodityImage implements Serializable{

	private static final long serialVersionUID = 7054667250288788954L;
	
	private int index;
	private String picName;
	private String picUrl;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
