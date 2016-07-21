package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;
 
/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION : 对外返回结果定义
 * @AUTHOR : le.sm@yougou.com
 * @DATE :2015-7-1
 *       </p>
 **************************************************************** 
 */
public class YmcResult implements Serializable {

	private static final long serialVersionUID = 33434L;
	//运行成功
	public static final String OK_CODE = "0";
	
	//运行时出错异常代码
	public static final String ERROR_CODE = "-1";
	
	//业务异常代码
	public static final String BUSINESS_ERROR_CODE = "-2";

	private String code;
	
	private String message;
	
	private Object data;
	
	public YmcResult(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	/**
	 * 初始化时候设置运行成功
	 */
	public YmcResult() {
		super();
		this.code = OK_CODE;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		if(data== null||"null".equals(data)){
			
			return  code +" :" +message ;
		}else{
			return  code +" :" +message +": "+data.toString();
		}
	}
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	 
}
