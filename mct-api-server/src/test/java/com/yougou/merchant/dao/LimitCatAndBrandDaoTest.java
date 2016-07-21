package com.yougou.merchant.dao;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.supplier.dao.LimitCatAndBrandMapper;
import com.yougou.merchant.api.supplier.vo.BrandCatRelation;
import com.yougou.merchant.api.supplier.vo.BrandVo;
import com.yougou.merchant.api.supplier.vo.CatVo;

/**
 * 
 * @ClassName: SupplierTest
 * @Description: 供应商Test
 * @author zhuang.rb
 * @date 2012-9-7 下午6:03:41
 * 
 */
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class LimitCatAndBrandDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private LimitCatAndBrandMapper limitMapper;

	@Test
	public void queryLimitBrandBysupplyId() {
		List<String> supplyIds = new ArrayList<String>();
		supplyIds.add("8a809ec83f98fab1013f993f140d001a");
		supplyIds.add("8a809ec83ecf1e12013ecf6bc33a0007");
		
		List<BrandVo> list = limitMapper.queryLimitBrandBysupplyId(supplyIds);
		assertTrue(list != null);
	}
	
	@Test
	public void insertBrandVo() {
		BrandVo vo = new BrandVo();
		vo.setId("111");
		vo.setBrandNo("xxx");
		vo.setSupplyId("sdgdfgdg");
		
		limitMapper.insertBrandVo(vo);
	}
	
	@Test
	public void deleteBrandById() {
		List<String> list = new ArrayList<String>();
		list.add("110");
		list.add("111");
		limitMapper.deleteBrandById(list);
	}
	
	@Test
	public void insertCatVo() {
		CatVo vo = new CatVo();
		vo.setId("12334");
		vo.setStructName("4656767");
		vo.setSupplyId("sdfdfdfg");
		vo.setCatNo("sdfg");
		
		limitMapper.insertCatVo(vo);
	}
	
	@Test
	public void deleteCatById() {
		List<String> list = new ArrayList<String>();
		list.add("12333");
		list.add("12334");
		
		limitMapper.deleteCatById(list);
	}
	
	@Test
	public void insertBrandCatRelation() {
		BrandCatRelation vo = new BrandCatRelation();
		vo.setId("12333");
		vo.setBrandId("xsdfdf");
		vo.setCatId("3454656");
		
		limitMapper.insertBrandCatRelation(vo);
	}
	
	@Test
	public void deleteBrandCatRelationById() {
		List<String> list = new ArrayList<String>();
		list.add("12333");
		list.add("12334");
		
		limitMapper.deleteBrandCatRelationById(list);
	}
	
	@Test
	public void queryBrandCatRelationBysupplyId() {
		List<CatVo> list = limitMapper.queryBrandCatRelationBysupplyId("8a809ec83fcc7243013fccd077ad0010");
		
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void getBrandNosByMerchantCode() {
		List<Map<String, String>> list = limitMapper.getBrandNosByMerchantCode("SP20130821678648");
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void getCategoryNosByMerchantCode() {
		List<Map<String, String>> list = limitMapper.getCategoryNosByMerchantCode("SP20130821678648");
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void getCategoryBySupplyId() {
		List<CatVo> list = limitMapper.getCategoryBySupplyId("8a8a8a173b97e026013b98163c3e05fe");
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void getMerchantByCatNo() {
		List<Map<String, String>> list = limitMapper.getMerchantByCatNo("CE");
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
}
