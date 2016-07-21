package com.yougou.yop.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import com.yougou.dto.output.QueryBrandOutputDto;
import com.yougou.dto.output.QueryCatOutputDto;
import com.yougou.merchant.api.supplier.service.ISupplierCommodityService;
import com.yougou.pc.api.ICommodityBaseApiService;
import com.yougou.pc.model.brand.Brand;
import com.yougou.pc.model.category.Category;
import com.yougou.yop.api.IMerchantApiCategoryService;

@Service(value="merchantApiCategoryService")
public class MerchantApiCategoryService implements IMerchantApiCategoryService {
	
	@Resource
	private ICommodityBaseApiService commodityBaseApiService;
	@Resource
	private ISupplierCommodityService supplierCommodityService;
	
	@Override
	public Object queryBrand(Map<String, Object> parameterMap) throws Exception {
		String merchantCode = MapUtils.getString(parameterMap, "merchant_code");
		List<QueryBrandOutputDto> dtos = new ArrayList<QueryBrandOutputDto>();
		List<String> brands = supplierCommodityService.getBrandNosByMerchantCode(merchantCode);
		//List<Brand> brands = commodityMerchantApiService.getBrandByMerchantCode(merchantCode);
		if (CollectionUtils.isNotEmpty(brands)) {
			for (String brandNo : brands) {
				QueryBrandOutputDto dto = new QueryBrandOutputDto();
				Brand b = commodityBaseApiService.getBrandByNo(brandNo);
				dto.setBrand_name(b.getBrandName());
				dto.setBrand_no(b.getBrandNo());
				dtos.add(dto);
			}
		}
		
		return dtos;
	}

	@Override
	public Object queryCat(Map<String, Object> parameterMap) throws Exception {
		String merchantCode = MapUtils.getString(parameterMap, "merchant_code");
		List<QueryCatOutputDto> dtos = new ArrayList<QueryCatOutputDto>();
		//List<Category> categories = commodityMerchantApiService.getCategoryByMerchantCode(merchantCode);
		List<String> categories = supplierCommodityService.getCategoryNosByMerchantCode(merchantCode);
		if (CollectionUtils.isNotEmpty(categories)) {
			for (String catNo : categories) {
				QueryCatOutputDto dto = new QueryCatOutputDto();
				Category category = commodityBaseApiService.getCategoryByNo(catNo);
				dto.setCat_id(category.getId());
				dto.setCat_no(category.getCatNo());
				dto.setCat_name(category.getCatName());
				dto.setStruct_name(category.getStructName());
				dtos.add(dto);
			}
		}
		
		return dtos;
	}

}
