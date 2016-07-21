/**
 * 
 */
package com.yougou.merchant.api.supplier.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.supplier.dao.LimitCatAndBrandMapper;
import com.yougou.merchant.api.supplier.dao.MerchantCommodityMapper;
import com.yougou.merchant.api.supplier.service.ISupplierCommodityService;
import com.yougou.merchant.api.supplier.vo.CatVo;
import com.yougou.merchant.api.supplier.vo.Commodity;
import com.yougou.merchant.api.supplier.vo.CommodityQueryVo;

/**
 * 
 * 
 * @author huang.tao
 *
 */
@Service(value="supplierCommodityService")
public class SupplierCommodityService implements ISupplierCommodityService {
	
	@Resource
	private LimitCatAndBrandMapper catbrandMapper;
	@Resource
	private MerchantCommodityMapper commodityMapper;
	
	/* (non-Javadoc)
	 * @see com.yougou.merchant.api.supplier.service.ISupplierCommodityService#getBrandNosByMerchantCode(java.lang.String)
	 */
	@Override
	public List<String> getBrandNosByMerchantCode(String merchantCode) {
		if (StringUtils.isBlank(merchantCode)) return null;
		
		List<String> brands = new ArrayList<String>();
		List<Map<String, String>> brandMaps = catbrandMapper.getBrandNosByMerchantCode(merchantCode);
		if (CollectionUtils.isNotEmpty(brandMaps)) {
			for (Map<String, String> _map : brandMaps) {
				brands.add(MapUtils.getString(_map, "brand_no"));
			}
		}
		return brands;
	}

	/* (non-Javadoc)
	 * @see com.yougou.merchant.api.supplier.service.ISupplierCommodityService#getCategoryNosByMerchantCode(java.lang.String)
	 */
	@Override
	public List<String> getCategoryNosByMerchantCode(String merchantCode) {
		if (StringUtils.isBlank(merchantCode)) return null;
		
		List<String> cats = new ArrayList<String>();
		List<Map<String, String>> catMaps = catbrandMapper.getCategoryNosByMerchantCode(merchantCode);
		if (CollectionUtils.isNotEmpty(catMaps)) {
			for (Map<String, String> _map : catMaps) {
				cats.add(MapUtils.getString(_map, "cat_no"));
			}
		}
		return cats;
	}

	/* (non-Javadoc)
	 * @see com.yougou.merchant.api.supplier.service.ISupplierCommodityService#getCategoryBySupplyId(java.lang.String)
	 */
	@Override
	public List<CatVo> getCategoryBySupplyId(String supplyId) {
		return catbrandMapper.getCategoryBySupplyId(supplyId);
	}

	/* (non-Javadoc)
	 * @see com.yougou.merchant.api.supplier.service.ISupplierCommodityService#getMerchantByCatNo(java.lang.String)
	 */
	@Override
	public List<Map<String, String>> getMerchantByCatNo(String catNo) {
		if (StringUtils.isBlank(catNo)) return null;
		
		return catbrandMapper.getMerchantByCatNo(catNo);
	}
	
	@Override
	public PageFinder<Commodity> getCommodityList(CommodityQueryVo commodityQueryVo, Query query,boolean isLike) throws Exception{
		if(commodityQueryVo==null||commodityQueryVo.getMerchantCode()==null){
			throw new RuntimeException("CommodityQueryVo不允许null或者MerchantCode不允许null");
		}
		RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
		List<Commodity> lstCommodity=null;
		int count=0;
		if(isLike){
			lstCommodity=commodityMapper.queryCommodityListByLike(commodityQueryVo, rowBounds);
			count=commodityMapper.queryCommodityListCountByLike(commodityQueryVo);
		}else{
			lstCommodity=commodityMapper.queryCommodityList(commodityQueryVo, rowBounds);
			count=commodityMapper.queryCommodityListCount(commodityQueryVo);
		}
		PageFinder<Commodity> pageFinder = new PageFinder<Commodity>(query.getPage(), query.getPageSize(), count, lstCommodity);
		return pageFinder;
	}
}
