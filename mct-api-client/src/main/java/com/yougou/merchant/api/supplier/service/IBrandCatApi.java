/**
 * 
 */
package com.yougou.merchant.api.supplier.service;

import java.util.List;

import com.yougou.merchant.api.supplier.vo.BrandCatRelation;
import com.yougou.merchant.api.supplier.vo.BrandVo;
import com.yougou.merchant.api.supplier.vo.CatVo;
import com.yougou.merchant.api.supplier.vo.SupplierVo;


/**
 * 招商品牌分类授权接口
 * 
 * @author huang.tao
 *
 */
public interface IBrandCatApi {
	
	/**
	 * 通过商家ids获取授权品牌列表
	 * 
	 * @param supplyIds
	 * @return
	 */
	public List<BrandVo> queryLimitBrandBysupplyId(List<String> supplyIds);
	
	/**
	 * 更新商家品牌分类授权(包括商家账户信息)
	 * 在一个事物里.<br />
	 *   <b>1.先删除商家品类授权</b><br />
	 *   <b>2.然后再添加</b><br />
	 * 1)supplierVo.setUser(MerchantUser user)<br />
	 * 2)supplierVo.setBrandVos(List<BrandVo> brandVos)<br />
	 * 3)supplierVo.setCatVos(List<CatVo> catVos) <br />
	 * 4)supplierVo.setBrandcatRelations(List<BrandCatRelation> brandcatRelations)<br /> 
	 * 
	 * @param vo
	 */
	void updateLimitBrandCatObj(SupplierVo vo) throws Exception;
	
	/**
	 * 只更新商家品类授权（不包含商家账户信息）<br />
	 * 在一个事物里.<br />
	 *   <b>1.先删除商家品类授权</b><br />
	 *   <b>2.然后再添加</b><br />
	 * 1)supplierVo.setBrandVos(List<BrandVo> brandVos)<br />
	 * 2)supplierVo.setCatVos(List<CatVo> catVos) <br />
	 * 3)supplierVo.setBrandcatRelations(List<BrandCatRelation> brandcatRelations)<br /> 
	 * @param Vo
	 * @throws Exceptin
	 */
	void updateLimitBrandCatNoUser(SupplierVo vo) throws Exception;
	
	/**
	 * 添加招商品牌授权
	 * 
	 * @param vo
	 */
	void insertLimitBrand(BrandVo vo) throws Exception;
	
	/**
	 * 添加招商分类授权 <br/>
	 * 只需如下字段：<br/>
	 * <b>id</b> <br/>
	 * <b>supplyId</b> <br/>
	 * <b>catNo</b> <br/>
	 * <b>structName</b> <br/>
	 * =========================
	 * @param vo
	 * @throws Exception
	 */
	void insertLimitCat(CatVo vo) throws Exception;
	
	/**
	 * 添加品牌与分类的授权关系
	 * 
	 * @param vo
	 * @throws Exception
	 */
	void insertLimitBrandAndCat(BrandCatRelation vo) throws Exception;
	
	/**
	 * 通过商家id删除品类授权（包括品类授权关系）
	 * 
	 * @param supplyId
	 * @throws Exception
	 */
	void deleteLimitBandAndCatBySupplyId(String supplyId) throws Exception;
}
