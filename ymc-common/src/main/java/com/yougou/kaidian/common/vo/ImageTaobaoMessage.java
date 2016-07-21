/**
 * 
 */
package com.yougou.kaidian.common.vo;

import java.io.Serializable;

/**
 * 商家中心和切图应用交互的jms消息对象
 * 
 * @author li.m1
 *
 */
public class ImageTaobaoMessage implements Serializable {

	private static final long serialVersionUID = -8050847421220712141L;
	/**
	 * 消息ID
	 */
	private String id;
	
	/** 淘宝商品id */
	private String numIid;

	/** 商家编码 */
	private String merchantCode;
	
	/** 图片角度图*/
	private String[] imgArry;
	
	/** 商品描述图 */
	private String proDesc;
	
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

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public String[] getImgArry() {
		return imgArry;
	}

	public void setImgArry(String[] imgArry) {
		this.imgArry = imgArry;
	}

	public String getNumIid() {
		return numIid;
	}

	public void setNumIid(String numIid) {
		this.numIid = numIid;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer("{merchantCode(商家编码):")
				.append(this.merchantCode).append("####proDesc(商品描述字符串):")
				.append(this.proDesc).append("####id(消息ID):").append(this.id)
				.append("####imgFileId(描述图ID列表):");
		if (null == imgArry)
			return strBuf.append("}").toString();
		
		for (String picid : imgArry) {
			strBuf.append(picid + ",");
		}
		return strBuf.append("}").toString();
	}
}
