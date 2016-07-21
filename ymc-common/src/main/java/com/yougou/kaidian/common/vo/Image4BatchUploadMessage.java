package com.yougou.kaidian.common.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家中心和切图应用交互的jms消息对象(批量上传图片的单个消息体)
 * @author li.m1
 *
 */
public class Image4BatchUploadMessage implements Serializable {

	private static final long serialVersionUID = -8050847421220712141L;
	
	private String id;
	
	/** 商家编码 */
	private String merchantCode;
	
	/** 商品编码 */
	private String commodityNo;
	
	/** 图片类型  b|l */
	private String picType;
	
	/** 图片名称*/
	private String picName;
	
	/** 图片路径*/
	private String picUrl;
	
	/** 多切三张小图的序号(s、c、u图) */
	private Integer seqNo = 1;
	
	/** 品牌英文名称/年份/商品编码 */
	private String urlFragment;
	
	/** FTP预处理目录(相对地址) */
	private String ftpRelativePath;
	
	/** 0:未处理  1:已处理 */
	private Integer status = 0; 
	
	
	private Date createTime;
	
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

	public String getFtpRelativePath() {
		return ftpRelativePath;
	}

	public void setFtpRelativePath(String ftpRelativePath) {
		this.ftpRelativePath = ftpRelativePath;
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

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer("{merchantCode(商家编码):")
				.append(this.merchantCode).append("####commodityNo(商品编号):")
				.append(this.commodityNo)
				.append("####urlFragment(图片服务器访问相对地址头部):")
				.append(this.urlFragment).append("####seqNo(s、c、u图序号):")
				.append(this.seqNo).append("####picName(图片名称):")
				.append(this.picName).append("####picUrl(URL):").append(this.picUrl)
				.append("####ftpRelativePath(ftp上传地址):").append(this.ftpRelativePath);
		return strBuf.append("}").toString();
	}
}
