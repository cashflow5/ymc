package com.yougou.merchant.api.pic.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.merchant.api.pic.service.vo.MerchantPicture;
import com.yougou.merchant.api.pic.service.vo.MerchantPictureCatalog;
import com.yougou.merchant.api.pic.service.vo.MerchantPictureVO;

public interface MerchantPictureMapper {
	
	public List<MerchantPicture> queryMerchantPicList(MerchantPictureVO pictureVO, RowBounds rowBounds);
	public int queryMerchantPicListCount(MerchantPictureVO pictureVO);
	public List<MerchantPictureCatalog> queryShopPicCatalogList(Map<String, String> praMap);
	public List<MerchantPictureCatalog> queryCommodityPicCatalogList(String merchantCode);
	public void insertPicCatalog(MerchantPictureCatalog catalog);
	public void updatePicCatalog(MerchantPictureCatalog catalog);
	public List<MerchantPictureCatalog> selectCatalogByParentId(@Param("id") String id);
	public void delPicCatalog(String id);
	public void insertPic(MerchantPicture picture);
	public void updatePic(MerchantPicture picture);
	public void delPic(String id);
	public void delPicByCatalogId(String catalogId);
	public void delPicByCatalogIdBatch(@Param("list")List<MerchantPictureCatalog> list);
	public MerchantPicture queryPicById(String id);
	public MerchantPictureCatalog getPicCatalog(String id);
	public void movePics(Map<String,Object> parameters);
}
