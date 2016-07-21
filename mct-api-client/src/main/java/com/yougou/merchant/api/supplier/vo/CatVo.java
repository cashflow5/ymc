/**
 * 
 */
package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;

/**
 * @author huang.tao
 *
 */
public class CatVo implements Serializable {

	private static final long serialVersionUID = -2126529878992523174L;
	
	private String id;
	
	private String supplyId;
	
	private String catNo;
	
	private String structName;
	
	//==========================
	//tbl_sp_limit_brand.id
	private String brandId;
	//品牌No
	private String brandNo;
	//品牌分类关系Id
	private String relationId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
	}

	public String getCatNo() {
		return catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}

	public String getStructName() {
		return structName;
	}

	public void setStructName(String structName) {
		this.structName = structName;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	
}
