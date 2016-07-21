package com.yougou.dto.input;

import com.yougou.dto.BaseDto;

/**
 * 
 * @author 杨梦清
 * 
 */
public class InputDto extends BaseDto {

	private static final long serialVersionUID = 7978719981956480110L;

	/** 商家编码 **/
	private String merchant_code;

	public String getMerchant_code() {
		return merchant_code;
	}

	public void setMerchant_code(String merchant_code) {
		this.merchant_code = merchant_code;
	}
}
