package com.yougou.merchant.api.taobao.vo;

import java.io.Serializable;

import com.yougou.merchant.api.taobao.enums.PropResult;

/**
 * 优购淘宝分类属性值
 * @author luo.hl
 *
 */
public class PropValue implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 优购属性值编码
	 */
	private String ygPropValueNo;
	/**
	 * 淘宝属性值编码
	 */
	private String tbPropValueNo;
	/**
	 * 淘宝属性值名称
	 */
	private String tbPropValueName;
	
	private PropResult propResult;
	
	public String getYgPropValueNo() {
		return ygPropValueNo;
	}
	public void setYgPropValueNo(String ygPropValueNo) {
		this.ygPropValueNo = ygPropValueNo;
	}
	public String getTbPropValueNo() {
		return tbPropValueNo;
	}
	public void setTbPropValueNo(String tbPropValueNo) {
		this.tbPropValueNo = tbPropValueNo;
	}
	public String getTbPropValueName() {
		return tbPropValueName;
	}
	public void setTbPropValueName(String tbPropValueName) {
		this.tbPropValueName = tbPropValueName;
	}
	public PropResult getPropResult() {
		return propResult;
	}
	public void setPropResult(PropResult propResult) {
		this.propResult = propResult;
	}
	
}
