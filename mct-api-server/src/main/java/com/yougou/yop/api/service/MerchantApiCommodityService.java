/**
 * 
 */
package com.yougou.yop.api.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yougou.dto.input.QueryCommodityInputDto;
import com.yougou.dto.output.QueryCommodityOutputDto;
import com.yougou.dto.output.QueryCommodityOutputDto.Product;
import com.yougou.merchant.api.supplier.service.ISupplierCommodityService;
import com.yougou.pc.api.ICommodityClientApiService;
import com.yougou.pc.api.ICommodityMerchantApiService;
import com.yougou.pc.model.commodityinfo.Commodity;
import com.yougou.pc.model.commodityinfo.CommodityDto;
import com.yougou.pc.model.commodityinfo.CommoditySearch;
import com.yougou.yop.api.IMerchantApiCommodityService;


/**
 * @author zheng.qq
 *
 */
@Service(value="merchantApiCommodityService")
public class MerchantApiCommodityService implements IMerchantApiCommodityService {
	private final Logger logger = Logger.getLogger(MerchantApiCommodityService.class);
	@Resource
	ISupplierCommodityService supplierCommodityService;
	@Resource
	private ICommodityMerchantApiService commodityMerchantApiService;
	
	@Override
	public Object queryCommodity(Map<String, Object> parameterMap)
			throws Exception {
		// TODO Auto-generated method stub
		QueryCommodityInputDto dto = new QueryCommodityInputDto();
		BeanUtils.populate(dto, parameterMap);
		
		if(StringUtils.isEmpty(dto.getMerchant_code())){
			logger.error("商家编码不能为空！");
			throw new Exception("商家编码不能为空！");
		}
		if(dto.getPage_size()==null || dto.getPage_size()>100 || dto.getPage_size()<=0){
			logger.error("分页大小在(0-100]之间!");
			throw new Exception("分页大小在(0-100]之间!");
		}		
		if(dto.getPage_index()==null || dto.getPage_index()<0){
			logger.error("页码必须是>0！");
			throw new Exception("页码必须是>0！");
		}
		//转化page_index,以满足商品接口。
		dto.setPage_index(dto.getPage_index()/dto.getPage_size() + 1);
		logger.warn("商品下载接口入参："+ToStringBuilder.reflectionToString(dto));
		//商品状态调用货品接口入参
		Integer[] status = null;
		String commodityStatus = (String) parameterMap.get("status");
		if(StringUtils.isNotBlank(commodityStatus)
				&& StringUtils.isNumeric(commodityStatus)
				&& dto.getStatus() >0){
			status = new Integer[]{dto.getStatus()};
		}else{
			status =  new Integer[]{1,2,3,4,5,6};
		}
		logger.warn("商品下载接口商品状态入参："+ToStringBuilder.reflectionToString(status));
		//System.out.println("商品状态："+dto.getStatus());
		CommodityDto commodityList = commodityMerchantApiService.getCommodityByMerchantWithStatusTime(dto.getMerchant_code(),dto.getCommodity_no(),
				status,dto.getStart_modified(), dto.getEnd_modified(), 
				false, true, dto.getPage_size(), dto.getPage_index());
		logger.warn("商品下载接口调用货品接口返回数据："+ToStringBuilder.reflectionToString(commodityList));
		QueryCommodityOutputDto outDto = this.convert(commodityList);
		outDto.setPage_index(dto.getPage_index());
		outDto.setPage_size(dto.getPage_size());
		outDto.setTotal_count(commodityList.getCount());	
		return outDto;
	}
	
	/**
	 * 转化招商商品API输出对象
	 * 
	 * @param dto 商品dto
	 * @return 
	 */
	private QueryCommodityOutputDto convert(CommodityDto dto) {
		QueryCommodityOutputDto result = new QueryCommodityOutputDto();
		List<QueryCommodityOutputDto.Commodity> items = new ArrayList<QueryCommodityOutputDto.Commodity>();
		QueryCommodityOutputDto.Commodity item = null;
        Commodity c = null;
		if (CollectionUtils.isNotEmpty(dto.getCommodityList())) {
			for (Commodity _item : dto.getCommodityList()) {
				item = new QueryCommodityOutputDto.Commodity();
				item.setAliasName(_item.getAliasName());
				item.setBrandEnglishName(_item.getBrandEnglishName());
				item.setBrandName(_item.getBrandName());
				item.setBrandNo(_item.getBrandNo());
				item.setCatName(_item.getCatName());
				item.setCatNo(_item.getCatNo());
				item.setCatStructName(_item.getCatStructName());
				item.setColorName(_item.getColorName());
				item.setColorNo(_item.getColorNo());
				item.setCommodityDesc(_item.getCommodityDesc());
				item.setCommodityName(_item.getCommodityName());
				item.setCommodityNo(_item.getCommodityNo());
				item.setCostPrice(_item.getCostPrice());
				item.setCreateDate(_item.getCreateDate());
				item.setDefalutPic(_item.getDefalutPic());
				item.setDownDate(_item.getDownDate());
				item.setFirstSellDate(_item.getFirstSellDate());
				item.setMarkPrice(_item.getMarkPrice());
				item.setMerchantCode(_item.getMerchantCode());
				item.setPicSmall(_item.getPicSmall());
				item.setSellDate(_item.getSellDate());
				item.setSellPrice(_item.getSellPrice());
				item.setStatus(_item.getStatus());
				item.setStyleNo(_item.getStyleNo());
				item.setSupplierCode(_item.getSupplierCode());
				item.setUpdateDate(_item.getUpdateDate());
				item.setYears(_item.getYears());

				List<Product> productList = new ArrayList<Product>();
				List<com.yougou.pc.model.product.Product> products = _item.getProducts();
				if (CollectionUtils.isNotEmpty(products)) {
					Product product = null;
					for (com.yougou.pc.model.product.Product _product : products) {
						product = new Product();
						product.setHeight(_product.getHeight());
						product.setInsideCode(_product.getInsideCode());
						product.setLength(_product.getLength());
						product.setProductNo(_product.getProductNo());
						product.setQuantity(_product.getQuantity());
						product.setSizeName(_product.getSizeName());
						product.setSizeNo(_product.getSizeNo());
						product.setTaobaoReserved(_product.getTaobaoReserved());
						product.setThirdPartyInsideCode(_product.getThirdPartyInsideCode());
						product.setWeight(_product.getWeight());
						product.setWidth(_product.getWidth());
						product.setYougouReserved(_product.getYougouReserved());
						productList.add(product);
					}
				}
				//货品明细
				item.setProducts(productList);
				items.add(item);
			}
		}
		result.setItems(items);
		
		return result;
	}

}
