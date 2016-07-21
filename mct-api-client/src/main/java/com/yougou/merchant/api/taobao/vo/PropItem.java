package com.yougou.merchant.api.taobao.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yougou.merchant.api.taobao.enums.PropResult;

/**
 * 淘宝优购分类属性
 * @author luo.hl
 *
 */
public class PropItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 优购属性编码
	 */
	private String ygPropNo;
	/**
	 * 淘宝属性编码
	 */
	private String tbPropNo;
	/**
	 * 淘宝属性名称
	 */
	private String tbPropName;
	
	private PropResult propResult;
	
	/**
	 * 属性值结合
	 */
	private List<PropValue> propVals = new ArrayList<PropValue>();
	public String getYgPropNo() {
		return ygPropNo;
	}
	public void setYgPropNo(String ygPropNo) {
		this.ygPropNo = ygPropNo;
	}
	public String getTbPropNo() {
		return tbPropNo;
	}
	public void setTbPropNo(String tbPropNo) {
		this.tbPropNo = tbPropNo;
	}
	public String getTbPropName() {
		return tbPropName;
	}
	public void setTbPropName(String tbPropName) {
		this.tbPropName = tbPropName;
	}
	public List<PropValue> getPropVals() {
		return propVals;
	}
	public void setPropVals(List<PropValue> propVals) {
		this.propVals = propVals;
	}
	public PropResult getPropResult() {
		return propResult;
	}
	public void setPropResult(PropResult propResult) {
		this.propResult = propResult;
	}
	
}
