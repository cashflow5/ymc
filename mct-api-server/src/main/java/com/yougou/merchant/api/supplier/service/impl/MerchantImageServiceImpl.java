/**
 * 
 */
package com.yougou.merchant.api.supplier.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.merchant.api.supplier.dao.MerchantImageJmsMapper;
import com.yougou.merchant.api.supplier.service.IMerchantImageService;
import com.yougou.merchant.api.supplier.vo.ImageJmsVo;
import com.yougou.merchant.api.supplier.vo.TaobaoImage;

/**
 * @author huang.tao
 *
 */
@Service(value="merchantImageService")
public class MerchantImageServiceImpl implements IMerchantImageService {
	
	@Resource
	private MerchantImageJmsMapper imageJmsMapper;
	
	@Override
	public void addImageJms(ImageJmsVo message) {
		imageJmsMapper.addImageJms(message);
	}

	@Override
	public List<ImageJmsVo> queryImageJmsList(ImageJmsVo message) {
		return imageJmsMapper.queryImageJmsList(message);
	}

	@Override
	public List<ImageJmsVo> queryImageJmsList(ImageJmsVo message,
			Integer pageNo, Integer pageSize) {
		int page = pageNo == 0 ? 1 : pageNo;
		int offset = (page - 1 )* pageSize;
		return imageJmsMapper.queryImageJmsList(message, new RowBounds(offset, pageSize));
	}
	
	@Override
	public Integer queryImageJmsCount(ImageJmsVo message) {
		return imageJmsMapper.queryImageJmsCount(message);
	}
	
	@Override
	public void updateImageJmsStatus(ImageJmsVo message) {
		imageJmsMapper.updateImageJmsStatus(message);
	}

	@Override
	public List<ImageJmsVo> queryImageJmsListByIds(String[] ids)
			throws Exception {
		return imageJmsMapper.queryImageJmsListByIds(ids);
	}

	@Override
	public List<ImageJmsVo> getUntreated() throws Exception {
		return imageJmsMapper.queryImageJmsUntreated();
	}

	@Override
	public void updateImageJmsStatusInvalid(String[] ids) throws Exception {
		imageJmsMapper.updateImageJmsStatusInvalid(ids);
	}

	@Override
	public void delMessage() throws Exception {
		imageJmsMapper.delMessage();
	}

	@Override
	public void updateTaobaoItemImgURL(Long numIid,String oldImgUrl,String newImgUrl,String thumbnailURL) {
		imageJmsMapper.updateTaobaoItemImgURL(numIid,oldImgUrl,newImgUrl,thumbnailURL);
	}
	
	@Override
	public void updateTaobaoItemImgAngle(Long numIid,String anglePic) {
		imageJmsMapper.updateTaobaoItemImgAngle(numIid,anglePic);
	}

	@Override
	public void updateTaobaoImgDesc(Long numIid,String desc,String defaultPic) {
		imageJmsMapper.updateTaobaoItemSrcDesc(numIid, desc);
		imageJmsMapper.updateTaobaoItemImgDesc(numIid, desc,defaultPic);
	}
	
	@Override
	public void updateTaobaoItemImgURLBatch(List<TaobaoImage> taobaoImages) {
		imageJmsMapper.updateTaobaoItemImgURLBatch(taobaoImages);
	}
}
