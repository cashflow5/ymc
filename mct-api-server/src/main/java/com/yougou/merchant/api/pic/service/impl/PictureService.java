package com.yougou.merchant.api.pic.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.pic.service.IPictureService;
import com.yougou.merchant.api.pic.service.vo.MerchantPicture;
import com.yougou.merchant.api.pic.service.vo.MerchantPictureCatalog;
import com.yougou.merchant.api.pic.service.vo.MerchantPictureVO;
import com.yougou.merchant.api.pic.dao.MerchantPictureMapper;


@Service(value="merchantPictureService")
public class PictureService implements IPictureService {

	@Resource
	private MerchantPictureMapper merchantPictureMapper;
	
	@Override
	public PageFinder<MerchantPicture> getPicListByPage(
			MerchantPictureVO merchantPictureVO, Query query) throws Exception {
		if(merchantPictureVO==null){
			throw new Exception("传入参数merchantPictureVO为null,请检查!");
		}
		//处理一下开始时间
		if(merchantPictureVO.getCreatedStart()!=null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(merchantPictureVO.getCreatedStart());
			calendar.set(Calendar.HOUR_OF_DAY, 00);  
			calendar.set(Calendar.MINUTE, 00);  
			calendar.set(Calendar.SECOND, 00);  
			merchantPictureVO.setCreatedStart(calendar.getTime());
		}
		//处理一下结束时间
		if(merchantPictureVO.getCreatedEnd()!=null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(merchantPictureVO.getCreatedEnd());
			calendar.set(Calendar.HOUR_OF_DAY, 23);  
			calendar.set(Calendar.MINUTE, 59);  
			calendar.set(Calendar.SECOND, 59);  
			merchantPictureVO.setCreatedEnd(calendar.getTime());
		}
        Integer count = merchantPictureMapper.queryMerchantPicListCount(merchantPictureVO);
        List<MerchantPicture> list = merchantPictureMapper.queryMerchantPicList(merchantPictureVO, new RowBounds(query.getOffset(), query.getPageSize()));
        PageFinder<MerchantPicture> pageFinder = new PageFinder<MerchantPicture>(query.getPage(), query.getPageSize(), count, list);
        return pageFinder;
	}

	@Override
	public List<MerchantPictureCatalog> queryShopPicCatalogList(
			String merchantCode, String shopId) throws Exception {
		Map<String,String> praMap = new HashMap<String,String>();
		praMap.put("shopId", shopId);
		praMap.put("merchantCode", merchantCode);
		return merchantPictureMapper.queryShopPicCatalogList(praMap);
	}

	@Override
	public List<MerchantPictureCatalog> queryCommodityPicCatalogList(
			String merchantCode) throws Exception {
		return merchantPictureMapper.queryCommodityPicCatalogList(merchantCode);
	}

	@Override
	public void insertPicCatalog(MerchantPictureCatalog pictureCatalog)
			throws Exception {
		merchantPictureMapper.insertPicCatalog(pictureCatalog);
	}

	@Override
	public void updatePicCatalog(MerchantPictureCatalog pictureCatalog)
			throws Exception {
		merchantPictureMapper.updatePicCatalog(pictureCatalog);
	}

	@Transactional
	@Override
	public void delPicCatalog(String catalogId) throws Exception {
		//查询目录下的子目录
		List<MerchantPictureCatalog> childCatalogs = merchantPictureMapper.selectCatalogByParentId(catalogId);
		MerchantPictureCatalog catalog = new MerchantPictureCatalog();
		catalog.setId(catalogId);
		childCatalogs.add(catalog);
		//批量删除图片
		merchantPictureMapper.delPicByCatalogIdBatch(childCatalogs);
		//merchantPictureMapper.delPicByCatalogId(catalogId);
		//删除目录以及子目录
		merchantPictureMapper.delPicCatalog(catalogId);
	}

	@Override
	public void insertPic(MerchantPicture picture) throws Exception {
		merchantPictureMapper.insertPic(picture);
	}

	@Override
	public void updatePic(MerchantPicture picture) throws Exception {
		merchantPictureMapper.updatePic(picture);
	}

	@Override
	public void delPic(String picId) throws Exception {
		merchantPictureMapper.delPic(picId);
	}

	@Override
	public MerchantPicture getPic(String picId) throws Exception {
		return merchantPictureMapper.queryPicById(picId);
	}

	@Override
	public MerchantPictureCatalog getPicCatalog(String id) throws Exception {
		return merchantPictureMapper.getPicCatalog(id);
	}

	@Override
	public void movePics(String[] picId, String catalogId) throws Exception {
		if(picId==null||picId.length==0){
			throw new Exception("传入参数picId为空,请检查!");
		}
		Map<String,Object> praMap = new HashMap<String,Object>();
		praMap.put("picId", picId);
		praMap.put("catalogId", catalogId);
		merchantPictureMapper.movePics(praMap);
	}
}
