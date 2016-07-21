package com.yougou.merchant.api.supplier.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yougou.merchant.api.supplier.vo.BrandCatRelation;
import com.yougou.merchant.api.supplier.vo.BrandVo;
import com.yougou.merchant.api.supplier.vo.CatVo;


/**
 * 招商授权分类和品牌
 * 
 * @author huang.tao
 *
 */
public interface LimitCatAndBrandMapper {
	/**
	 * 通过商家ids获取授权品牌列表
	 * 
	 * @param supplyIds
	 * @return
	 */
	List<BrandVo> queryLimitBrandBysupplyId(List<String> supplyIds);
	
	void insertBrandVo(BrandVo vo);
	
	void deleteBrandById(List<String> ids);
	
	void deleteBrandBySupplyId(String supplyId);
	
	/**
	 * tbl_sp_limit_cat
	 * 
	 * @param vo
	 */
	void insertCatVo(CatVo vo);
	
	void deleteCatById(List<String> ids);
	
	void deleteCatBySupplyId(String supplyId);
	
	/**
	 * 品牌分类授权关系
	 * 
	 * @param vo
	 */
	void insertBrandCatRelation(BrandCatRelation vo);
	
	void deleteBrandCatRelationById(List<String> ids);
	
	/**
	 * 通过供应商Id获取品牌分类授权关系
	 * 
	 * @param supplyId
	 * @return
	 */
	List<CatVo> queryBrandCatRelationBysupplyId(String supplyId);
	
	List<Map<String, String>> getBrandNosByMerchantCode(@Param("merchantCode") String merchantCode);
	
	List<Map<String, String>> getCategoryNosByMerchantCode(@Param("merchantCode") String merchantCode);
	
	List<CatVo> getCategoryBySupplyId(String supplyId);
	
	List<Map<String, String>> getMerchantByCatNo(String catNo);
}
