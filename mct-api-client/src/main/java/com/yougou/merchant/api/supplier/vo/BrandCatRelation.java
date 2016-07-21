/**
 * 
 */
package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;

/**
 * 品牌分类授权关系
 * 
 * @author huang.tao
 *
 */
public class BrandCatRelation implements Serializable {

	private static final long serialVersionUID = -2009168555217756428L;
	
	private String id;
	
	/**
	 * tbl_sp_limit_brand.id
	 */
	private String brandId;
	
	/**
	 * tbl_sp_limit_cat.id
	 */
	private String catId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}
	
	
}
