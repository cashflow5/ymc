package com.yougou.merchant.api.supplier.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.merchant.api.common.MerchantLogTools;
import com.yougou.merchant.api.common.UUIDGenerator;
import com.yougou.merchant.api.supplier.dao.LimitCatAndBrandMapper;
import com.yougou.merchant.api.supplier.dao.MerchantMapper;
import com.yougou.merchant.api.supplier.service.IBrandCatApi;
import com.yougou.merchant.api.supplier.vo.BrandCatRelation;
import com.yougou.merchant.api.supplier.vo.BrandVo;
import com.yougou.merchant.api.supplier.vo.CatVo;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog.OperationType;
import com.yougou.merchant.api.supplier.vo.SupplierVo;

@Service(value="brandCatApi")
public class BrandCatApi implements IBrandCatApi {

	@Resource
	private LimitCatAndBrandMapper brandCatMapper;
	@Resource
	private MerchantMapper merchantMapper;
	
	@Override
	public List<BrandVo> queryLimitBrandBysupplyId(List<String> supplyIds) {
		return this.brandCatMapper.queryLimitBrandBysupplyId(supplyIds);
	}

	@Override
	@Transactional
	public void updateLimitBrandCatObj(SupplierVo vo) throws Exception {
		//add 账户信息
		if (null != vo.getUser()) {
			merchantMapper.insertMerchantUser(vo.getUser());
			
			//添加账户日志
			MerchantOperationLog _userlog = new MerchantOperationLog();
			_userlog.setId(UUIDGenerator.getUUID());
			_userlog.setMerchantCode(vo.getSupplierCode());
			_userlog.setOperator(vo.getCreator());
			_userlog.setOperated(new Date());
			_userlog.setOperationType(OperationType.ACCOUNT);
			_userlog.setOperationNotes(MerchantLogTools.buildMerchantAccountOperationNotes(null, vo.getUser()));
			merchantMapper.insertMerchantLog(_userlog);
		}
		
		//删除品牌授权关系后(重新添加|保证在一个事物里面)
		this.delelteLimitBandAndCat(vo.getId());
		
		//add 品牌分类授权
		if (CollectionUtils.isNotEmpty(vo.getBrandVos())) {
			for (BrandVo brandVo : vo.getBrandVos()) {
				brandCatMapper.insertBrandVo(brandVo);
			}
		}
		if (CollectionUtils.isNotEmpty(vo.getCatVos())) {
			for (CatVo catVo : vo.getCatVos()) {
				brandCatMapper.insertCatVo(catVo);
			}
		}
		if (CollectionUtils.isNotEmpty(vo.getBrandcatRelations())) {
			for (BrandCatRelation relation : vo.getBrandcatRelations()) {
				brandCatMapper.insertBrandCatRelation(relation);
			}
		}
		
		//add operation Log
		//TODO
		
	}

	@Override
	@Transactional
	public void updateLimitBrandCatNoUser(SupplierVo vo) throws Exception {
		//删除品牌授权关系后(重新添加|保证在一个事物里面)
		this.delelteLimitBandAndCat(vo.getId());
		
		//add 品牌分类授权
		if (CollectionUtils.isNotEmpty(vo.getBrandVos())) {
			for (BrandVo brandVo : vo.getBrandVos()) {
				brandCatMapper.insertBrandVo(brandVo);
			}
		}
		if (CollectionUtils.isNotEmpty(vo.getCatVos())) {
			for (CatVo catVo : vo.getCatVos()) {
				brandCatMapper.insertCatVo(catVo);
			}
		}
		if (CollectionUtils.isNotEmpty(vo.getBrandcatRelations())) {
			for (BrandCatRelation relation : vo.getBrandcatRelations()) {
				brandCatMapper.insertBrandCatRelation(relation);
			}
		}
		
		//TODO
	}
	
	@Override
	public void insertLimitBrand(BrandVo vo) throws Exception {
		brandCatMapper.insertBrandVo(vo);
	}

	@Override
	public void insertLimitCat(CatVo vo) throws Exception {
		brandCatMapper.insertCatVo(vo);
	}

	@Override
	public void insertLimitBrandAndCat(BrandCatRelation vo) throws Exception {
		brandCatMapper.insertBrandCatRelation(vo);
	}

	@Override
	@Transactional
	public void deleteLimitBandAndCatBySupplyId(String supplyId)
			throws Exception {
		this.delelteLimitBandAndCat(supplyId);
	}

	private void delelteLimitBandAndCat(String supplyId) {
		//删除品牌授权关系后(重新添加|保证在一个事物里面)
		List<CatVo> _temps = brandCatMapper.queryBrandCatRelationBysupplyId(supplyId);
		if (CollectionUtils.isNotEmpty(_temps)) {
			List<String> relationIds = new ArrayList<String>();
			for (CatVo _catvo : _temps) {
				relationIds.add(_catvo.getRelationId());
			}
			brandCatMapper.deleteBrandCatRelationById(relationIds);
		}
				
		brandCatMapper.deleteBrandBySupplyId(supplyId);
		brandCatMapper.deleteCatBySupplyId(supplyId);
	}
}
