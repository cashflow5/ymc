package com.yougou.merchant.api.active.vo;

import java.io.Serializable;
/**
 * 审批商家报名vo
 * @author zhang.wj
 *
 */
public class MerchanteEnrollxamineVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//是否成功
	private boolean isSuccess;	
	//错误原因
	private String  errorMsg;
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
