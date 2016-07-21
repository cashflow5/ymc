package com.yougou.yop.api.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yougou.api.exception.YOPRuntimeException;
import com.yougou.dto.input.QueryInventoryInputDto;
import com.yougou.dto.input.UpdateInventoryInputDto;
import com.yougou.dto.output.QueryInventoryOutputDto;
import com.yougou.dto.output.QueryInventoryOutputDto.Item;
import com.yougou.dto.output.UpdateInventoryOutputDto;
import com.yougou.pc.api.ICommodityBaseApiService;
import com.yougou.pc.model.product.ProductCommodity;
import com.yougou.wms.wpi.inventory.service.IInventoryDomainService;
import com.yougou.wms.wpi.warehouse.service.IWarehouseCacheService;
import com.yougou.yop.api.IMerchantApiInventoryService;

@Service(value="merchantApiInventoryService")
public class MerchantApiInventoryService implements IMerchantApiInventoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantApiInventoryService.class);
	
	@Resource
	private IWarehouseCacheService warehouseCacheService;
	@Resource
	private IInventoryDomainService inventoryDomainService;
	@Resource
	private ICommodityBaseApiService commodityBaseApiService;
	
	@Override
	public Object updateMerchantInventory(UpdateInventoryInputDto dto) throws Exception {
		UpdateInventoryOutputDto resultDto = null;
		Map<String, Object> mapQueryParam = new HashMap<String, Object>();
		mapQueryParam.put("merchantCode", dto.getMerchant_code());
		mapQueryParam.put("thirdPartyCode", dto.getThird_party_code());
		List<ProductCommodity> lstProductCommodity = commodityBaseApiService.getProductCommodities(mapQueryParam, false);
		ProductCommodity productCommodity = null;
		if (lstProductCommodity == null || lstProductCommodity.isEmpty()) {
			return new YOPRuntimeException("500", "您发布的商品无对应货品" + dto.getMerchant_code());
		} else {
			productCommodity = lstProductCommodity.get(0);
		}
			
		// 校验商家是否绑定虚拟仓库
		Map<String, ?> temporaryMap = warehouseCacheService.getWarehouseByMerchantCode(dto.getMerchant_code());
		if (MapUtils.isEmpty(temporaryMap)) {
			logger.info("商家未绑定虚拟仓库." + dto.getMerchant_code());
			return new YOPRuntimeException("500", "商家未绑定虚拟仓库.");
		}
		String warehouseCode = temporaryMap.keySet().iterator().next();
		Integer updateType = NumberUtils.INTEGER_ONE.equals(dto.getUpdate_type()) ? dto.getUpdate_type() : NumberUtils.INTEGER_ZERO;
		// 校验库存是否为负数
		if (updateType == 0 && dto.getQuantity() < 0) {
			logger.info("库存数量必须为0或者正整数。" + dto.getMerchant_code());
			return new YOPRuntimeException("502", "库存数量必须为0或者正整数。");
		} else if (updateType == 1) {
			Map<Integer, Date> mapResult = inventoryDomainService.querySalesInventroyForMerchant(productCommodity.getProductNo(), warehouseCode);
			int saleCount = !MapUtils.isEmpty(mapResult) ? mapResult.keySet().iterator().next() : 0;
			if (dto.getQuantity() + saleCount < 0) {
				logger.info("增量更新失败，现有库存:" + saleCount + "加上增量库存:" + dto.getQuantity() + "之后的实际库存必须是0或者正整数。" + dto.getMerchant_code());
				return new YOPRuntimeException("502", "增量更新失败，现有库存:" + saleCount + "加上增量库存:" + dto.getQuantity() + "之后的实际库存必须是0或者正整数。");
			}
		}
		
		temporaryMap = inventoryDomainService.updateInventoryForMerchant(productCommodity.getProductNo(), warehouseCode, dto.getQuantity(), updateType);
		
		resultDto = new UpdateInventoryOutputDto();
		resultDto.setThird_party_code(dto.getThird_party_code());
		resultDto.setModified((Date) temporaryMap.values().iterator().next());
		return resultDto;
	}

	@Override
	public QueryInventoryOutputDto queryMerchantInventory(QueryInventoryInputDto dto) throws Exception {
		// 校验商家是否绑定虚拟仓库
		Map<String, String> temporaryMap = warehouseCacheService.getWarehouseByMerchantCode(dto.getMerchant_code());
		if (MapUtils.isEmpty(temporaryMap)) {
			logger.error("商家未绑定虚拟仓库.merchantCode:" + dto.getMerchant_code());
			throw new RuntimeException("商家未绑定虚拟仓库.merchantCode:"+dto.getMerchant_code());
		}
		String warehouseCode = temporaryMap.keySet().iterator().next();
		QueryInventoryOutputDto outputDto = new QueryInventoryOutputDto(dto.getPage_index(), dto.getPage_size(), 0);
		Map<String, Object> mapQueryParam = new HashMap<String, Object>();
		mapQueryParam.put("merchantCode", dto.getMerchant_code());
		if (StringUtils.isNotBlank(dto.getBrand_no())) {
			mapQueryParam.put("brandNo", dto.getBrand_no());
		}
		if (StringUtils.isNotBlank(dto.getCat_no())) {
			mapQueryParam.put("cateNo", dto.getCat_no());
		}
		if (StringUtils.isNotBlank(dto.getYears())) {
			mapQueryParam.put("year", dto.getYears());
		}
		if (StringUtils.isNotBlank(dto.getStyle_no())) {
			mapQueryParam.put("styleNo", dto.getStyle_no());
		}
		if (StringUtils.isNotBlank(dto.getCommodity_status()) && !dto.getCommodity_status().equals("-1")) {
			mapQueryParam.put("commodityStatusList", dto.getCommodity_status().split(","));
		}
		if (StringUtils.isNotBlank(dto.getThird_party_code())) {
			mapQueryParam.put("thirdPartyCode", dto.getThird_party_code());
		}
		if (StringUtils.isNotBlank(dto.getProduct_no())) {
			mapQueryParam.put("productNo", dto.getProduct_no());
		}
		mapQueryParam.put("orderBy", "pro.product_no");
		List<ProductCommodity> lstProductCommodity = commodityBaseApiService.getProductCommodities(mapQueryParam, false, dto.getPage_index(), dto.getPage_size());
		if(lstProductCommodity != null && !lstProductCommodity.isEmpty()) {
			outputDto.setTotal_count(commodityBaseApiService.getProductCommoditiesCount(mapQueryParam));
			List<Item> lstItem = new ArrayList<Item>(lstProductCommodity.size());
			Item item = null;
			int saleCount = 0;
			for(ProductCommodity productCommodity : lstProductCommodity){
				item = new Item();
				Map<Integer, Date> mapResult = inventoryDomainService.querySalesInventroyForMerchant(productCommodity.getProductNo(), warehouseCode);
				saleCount = !MapUtils.isEmpty(mapResult)?mapResult.keySet().iterator().next():0;
				item.setCommodity_image(productCommodity.getDefaultPic());
				item.setCommodity_items_page(commodityBaseApiService.getCommodityPageUrlWithExtension(productCommodity.getCommodityNo()));
				item.setCommodity_name(productCommodity.getCommodityName());
				item.setCommodity_no(productCommodity.getCommodityNo());
				item.setCommodity_status(productCommodity.getCommodityStatus());
				item.setModified(mapResult.get(saleCount));
				item.setProduct_no(productCommodity.getProductNo());
				item.setPublic_price(BigDecimal.valueOf(productCommodity.getPublicPrice()));
				item.setSale_price(BigDecimal.valueOf(Double.valueOf(productCommodity.getSalePrice())));
				item.setStyle_no(productCommodity.getStyleNo());
				item.setSupplier_code(productCommodity.getSupplierCode());
				item.setThird_party_code(productCommodity.getThirdPartyCode());
				item.setTotal_stock_quantity(saleCount);
				lstItem.add(item);
			}
			if (lstProductCommodity != null && !lstProductCommodity.isEmpty()) {
				outputDto.setItems(lstItem);
			}
		}
		return outputDto;
	}

}
