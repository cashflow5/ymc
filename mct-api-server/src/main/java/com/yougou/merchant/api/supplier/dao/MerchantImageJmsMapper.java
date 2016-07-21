package com.yougou.merchant.api.supplier.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.merchant.api.supplier.vo.ImageJmsVo;
import com.yougou.merchant.api.supplier.vo.TaobaoImage;

/**
 * 招商图片处理jms消息
 * 
 * @author huang.tao
 *
 */
public interface MerchantImageJmsMapper {
	
	void addImageJms(ImageJmsVo message);
	
	/**
	 * 查询jms消息列表
	 * 
	 * @param message
	 * @return
	 */
	List<ImageJmsVo> queryImageJmsList(ImageJmsVo message);
	
	List<ImageJmsVo> queryImageJmsList(ImageJmsVo message, RowBounds rowBounds);
	
	Integer queryImageJmsCount(ImageJmsVo message);
	
	/**
	 * 修改jms消息的状态
	 * 
	 * @param message
	 */
	void updateImageJmsStatus(ImageJmsVo message);
	
	List<ImageJmsVo> queryImageJmsListByIds(String[] ids);
	List<ImageJmsVo> queryImageJmsUntreated();
	void updateImageJmsStatusInvalid(String[] ids);
	void delMessage();

	void updateTaobaoItemImgURL(@Param(value="numIid")Long numIid,@Param(value="oldImgUrl")String oldImgUrl,@Param(value="newImgUrl")String newImgUrl,@Param(value="thumbnailURL")String thumbnailURL);
	
	void updateTaobaoItemImgDesc(@Param(value="numIid")Long numIid,@Param(value="desc")String desc,@Param(value="defaultPic")String defaultPic);
	
	void updateTaobaoItemImgAngle(@Param(value="numIid")Long numIid,@Param(value="anglePic")String anglePic);
	
	void updateTaobaoItemSrcDesc(@Param(value="numIid")Long numIid,@Param(value="desc")String desc);
	
	void updateTaobaoItemImgURLBatch(List<TaobaoImage> params);
}
