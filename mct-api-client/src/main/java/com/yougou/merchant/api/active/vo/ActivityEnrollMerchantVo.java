package com.yougou.merchant.api.active.vo;

import java.io.Serializable;
/**
 * 查询活动报名商家列表Vo
 * @author zhang.wj
 *
 */
public class ActivityEnrollMerchantVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String merchantName;
	
	private String merchantCode;
	
	private String status;
	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	
	
	


	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
