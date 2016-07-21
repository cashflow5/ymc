/**
 * 
 */
package com.yougou.kaidian.common.constant;

/**
 * 商家中心错误码
 * 
 * @author huang.tao
 *
 */
public class ErrorConstant {
	
	public static final String MODULE_SYSTEM = "01";
	
	public static final String MODULE_COMMODITY = "02";
	
	/** 未设置商家编码 */
	public static final String E_0001 = "0001";
	/** 未设置仓库编码 */
	public static final String E_0002 = "0002";
	
	public static String getErrorCode(String module, String code) {
		return module + "_" + code;
	}
}
