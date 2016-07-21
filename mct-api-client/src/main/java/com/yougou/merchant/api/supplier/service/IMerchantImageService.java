/**
 * 
 */
package com.yougou.merchant.api.supplier.service;

import java.util.List;

import com.yougou.merchant.api.supplier.vo.ImageJmsVo;
import com.yougou.merchant.api.supplier.vo.TaobaoImage;

/**
 * 招商图片处理相关服务(供商家中心使用)
 * 
 * @author huang.tao
 *
 */
public interface IMerchantImageService {
	
	void addImageJms(ImageJmsVo message);
	
	/**
	 * 查询jms消息列表
	 * 
	 * @param message
	 * @return
	 */
	List<ImageJmsVo> queryImageJmsList(ImageJmsVo message);
	
	List<ImageJmsVo> queryImageJmsList(ImageJmsVo message, Integer pageNo, Integer pageSize);
	
	Integer queryImageJmsCount(ImageJmsVo message);
	
	/**
	 * 修改jms消息的状态
	 * 
	 * @param message
	 */
	void updateImageJmsStatus(ImageJmsVo message);
	
	List<ImageJmsVo> queryImageJmsListByIds(String[] ids) throws Exception;
	List<ImageJmsVo> getUntreated() throws Exception;
	/**
	 * 批量置为作废
	 * @param ids
	 * @throws Exception
	 */
	void updateImageJmsStatusInvalid(String[] ids) throws Exception;
	/**
	 * 删除7天一起的消息
	 * @throws Exception
	 */
	void delMessage() throws Exception;
	
	/**
	 * 更新淘宝图片转换为优购的地址
	 * @param imgId
	 * @param imgUrl
	 */
	void updateTaobaoItemImgURL(Long numIid,String oldImgUrl,String newImgUrl,String thumbnailURL);
	
	/**
	 * 更新淘宝图片转换为优购的描述字符串
	 * @param numIid
	 * @param desc
	 */
	void updateTaobaoImgDesc(Long numIid,String desc,String defaultPic);
	
	/**
	 * 更新淘宝图片角度图
	 * @param numIid
	 * @param desc
	 */
	void updateTaobaoItemImgAngle(Long numIid,String anglePic);
	
	/**
	 * 批量更新淘宝图片转换为优购的地址
	 * @param taobaoImages
	 */
	void updateTaobaoItemImgURLBatch(List<TaobaoImage> taobaoImages);
}
