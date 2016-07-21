package com.yougou.merchant.api.supplier.dao;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.yougou.merchant.api.supplier.vo.Commodity;
import com.yougou.merchant.api.supplier.vo.CommodityQueryVo;


public interface MerchantCommodityMapper {

	/**
	 * 查询商品（精确三级分类）
	 * @param commodityQueryVo
	 * @param rowBounds
	 * @return
	 */
	public List<Commodity> queryCommodityList(CommodityQueryVo commodityQueryVo, RowBounds rowBounds);
	
	public int queryCommodityListCount(CommodityQueryVo commodityQueryVo);
	
	/**
	 * 查询商品（模糊分类）
	 * @param commodityQueryVo
	 * @param rowBounds
	 * @return
	 */
	public List<Commodity> queryCommodityListByLike(CommodityQueryVo commodityQueryVo, RowBounds rowBounds);
	
	public int queryCommodityListCountByLike(CommodityQueryVo commodityQueryVo);
}
