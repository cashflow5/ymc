package com.yougou.dto.output;

import java.util.Date;

public class CancelOrModifyOrderForMerchantVo extends OutputDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3072921050562459999L;
	
	private String orderSubNo;
	private String merchantCode;
	private String changeType;
	private Date interceptDate;
	private String content;
	
	/**
	 * @return the merchantCode
	 */
	public String getMerchantCode() {
		return merchantCode;
	}
	/**
	 * @param merchantCode the merchantCode to set
	 */
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	/**
	 * @return the changeType
	 */
	public String getChangeType() {
		return changeType;
	}
	/**
	 * @param changeType the changeType to set
	 */
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	/**
	 * @return the interceptDate
	 */
	public Date getInterceptDate() {
		return interceptDate;
	}
	/**
	 * @param interceptDate the interceptDate to set
	 */
	public void setInterceptDate(Date interceptDate) {
		this.interceptDate = interceptDate;
	}
	
	/**
	 * @return the orderSubNo
	 */
	public String getOrderSubNo() {
		return orderSubNo;
	}
	/**
	 * @param orderSubNo the orderSubNo to set
	 */
	public void setOrderSubNo(String orderSubNo) {
		this.orderSubNo = orderSubNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CancelOrModifyOrderForMerchantVo [orderSubNo=" + orderSubNo
				+ ", merchantCode=" + merchantCode + ", changeType="
				+ changeType + ", interceptDate=" + interceptDate + "]";
	}
	
}

