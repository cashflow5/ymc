/**
 * 
 */
package com.yougou.merchant.api.supplier.service;

import java.util.List;
import java.util.Map;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.supplier.vo.CatVo;
import com.yougou.merchant.api.supplier.vo.Commodity;
import com.yougou.merchant.api.supplier.vo.CommodityQueryVo;

/**
 * 商品系统调用接口
 * 
 * @author huang.tao
 *
 */
public interface ISupplierCommodityService {
	
	/**
	 * <p>通过招商&nbsp;<b>商家编码: </b>merchantCode <br />获取商家授权品牌 brandNo</p>
	 * 
	 * @param merchantCode
	 * @return
	 */
	List<String> getBrandNosByMerchantCode(String merchantCode);
	
	/**
	 * <p>通过招商&nbsp;<b>商家编码: </b>merchantCode <br />获取商家授权分类 catNo</p>
	 * 
	 * @param merchantCode
	 * @return
	 */
	List<String> getCategoryNosByMerchantCode(String merchantCode);
	
	/**
	 * 根据商家id获取分类关联信息
	 * 
	 * @param supplyId 商家ID
	 * @return
	 */
	List<CatVo> getCategoryBySupplyId(String supplyId);

	/**
	 * 通过分类获取商家 
	 * Map.key&ltmerchantCode,supplierName&gt
	 * 
	 * @param catNo
	 * @return
	 */
	List<Map<String, String>> getMerchantByCatNo(String catNo);
	
	/**
	 * 根据查询条件获取商品信息(like:true模糊查询  like:false三级分类精确查询)
	 * @param commodityQueryVo
	 * @param rowBounds
	 * @return
	 */
	PageFinder<Commodity> getCommodityList(CommodityQueryVo commodityQueryVo, Query query,boolean isLike) throws Exception;
	
}
