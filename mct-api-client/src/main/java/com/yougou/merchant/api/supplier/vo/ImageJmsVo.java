/**
 * 
 */
package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huang.tao
 *
 */
public class ImageJmsVo implements Serializable {

	private static final long serialVersionUID = -813440221384674376L;

	private String id;
	
	/** 商家编码 */
	private String merchantCode;
	
	/** 商品编码 */
	private String commodityNo;
	
	/** 图片类型  b|l */
	private String picType;
	
	/** 如果picType=l、有效(对应7张角度图的id)*/
	private String imageId;
	
	/** 商品描述图 */
	private String proDesc;
	
	/** 多切三张小图的序号(s、c、u图) */
	private Integer seqNo;
	
	/** 品牌英文名称/年份/商品编码 */
	private String urlFragment;
	
	/** 0:未处理  1:已处理 2:已作废*/
	private Integer status;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String startTime;
	private String endTime;
	
	// 切图执行时间(单位：s)
	private Long execTime; 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getCommodityNo() {
		return commodityNo;
	}

	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}

	public String getPicType() {
		return picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getUrlFragment() {
		return urlFragment;
	}

	public void setUrlFragment(String urlFragment) {
		this.urlFragment = urlFragment;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getExecTime() {
		return execTime;
	}

	public void setExecTime(Long execTime) {
		this.execTime = execTime;
	} 

}
