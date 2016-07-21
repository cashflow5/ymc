package com.yougou.merchant.api.pic.service;

import java.util.List;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.pic.service.vo.MerchantPicture;
import com.yougou.merchant.api.pic.service.vo.MerchantPictureCatalog;
import com.yougou.merchant.api.pic.service.vo.MerchantPictureVO;

/**
 * 招商图片操作api
 * @author li.m1
 *
 */
public interface IPictureService {

	/**
	 * 获取图片列表
	 */
	public PageFinder<MerchantPicture> getPicListByPage(MerchantPictureVO merchantPictureVO,Query query) throws Exception;
	
	/**
	 * 查询店铺装修目录列表
	 */
	public List<MerchantPictureCatalog> queryShopPicCatalogList(String merchantCode,String shopId) throws Exception;
	
	/**
	 * 查询商品目录列表
	 */
	public List<MerchantPictureCatalog> queryCommodityPicCatalogList(String merchantCode) throws Exception;
	
	/**
	 * 获取图片目录
	 */
	public MerchantPictureCatalog getPicCatalog(String id) throws Exception;
	/**
	 * 新增图片目录
	 */
	public void insertPicCatalog(MerchantPictureCatalog pictureCatalog) throws Exception;
	
	/**
	 * 更新图片目录
	 */
	public void updatePicCatalog(MerchantPictureCatalog pictureCatalog) throws Exception;
	
	/**
	 * 删除图片目录
	 */
	public void delPicCatalog(String catalogId) throws Exception;
	
	/**
	 * 插入图片
	 */
	public void insertPic(MerchantPicture picture) throws Exception;
	
	/**
	 * 跟新图片
	 */
	public void updatePic(MerchantPicture picture) throws Exception;
	
	/**
	 * 删除图片
	 */
	public void delPic(String picId) throws Exception;
	/**
	 * 获取图片
	 */
	public MerchantPicture getPic(String picId) throws Exception;
	
	/**
	 * 移动图片
	 */
	public void movePics(String[] picId,String catalogId) throws Exception;
}
