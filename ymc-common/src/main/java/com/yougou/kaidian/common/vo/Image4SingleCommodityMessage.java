package com.yougou.kaidian.common.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家中心和切图应用交互的jms消息对象
 * @author li.m1
 *
 */

public class Image4SingleCommodityMessage implements Serializable {

	private static final long serialVersionUID = 7879882180410298350L;

	private String id;
	
	/** 商家编码 */
	private String merchantCode;
	
	/** 商品编码 */
	private String commodityNo;
	
	/**商品角度图数组*/
	private CommodityImage[] commodityImages;
	
	/** 商品描述图 */
	private String proDesc;
	
	/** 多切三张小图的序号(s、c、u图) */
	private Integer seqNo = 1;
	
	/** 品牌英文名称/年份/商品编码 (构建新地址用)*/
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

	public CommodityImage[] getCommodityImages() {
		return commodityImages;
	}

	public void setCommodityImages(CommodityImage[] commodityImages) {
		this.commodityImages = commodityImages;
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

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer("{merchantCode(商家编码):")
				.append(this.merchantCode).append("####commodityNo(商品编号):")
				.append(this.commodityNo)
				.append("####urlFragment(图片服务器相对地址头部):")
				.append(this.urlFragment).append("####seqNo(s、c、u图序号):")
				.append(this.seqNo).append("####proDesc(商品描述字符串):")
				.append(this.proDesc).append("####id(消息ID):").append(this.id)
				.append("####commodityImages(角度图列表):");
		if (null == commodityImages)
			return strBuf.append("}").toString();
		
		for (CommodityImage picid : commodityImages) {
			strBuf.append("(name)"+picid.getPicName() + ",(index)"+picid.getIndex()+ ",(url)"+picid.getPicUrl());
		}
		return strBuf.append("}").toString();
	}
}
