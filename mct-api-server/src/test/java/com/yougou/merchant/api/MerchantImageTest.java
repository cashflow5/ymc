package com.yougou.merchant.api;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.supplier.service.IMerchantImageService;
import com.yougou.merchant.api.supplier.vo.ImageJmsVo;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MerchantImageTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private IMerchantImageService imageService;

	@Test
	public void addImageJms() {
		ImageJmsVo message = new ImageJmsVo();
		message.setMerchantCode("SP20110831511483");
		message.setCommodityNo("99900021");
		message.setPicType("l");
		message.setSeqNo(1);
		message.setId("2c94811d321e2edc01321ebc2989040e");
		message.setCreateTime(new Date());
		message.setImageId("2343455456,5465767868678,5466778989");
		message.setStatus(0);
		message.setUrlFragment("/staccato/2012/99900021/");
		imageService.addImageJms(message);
	}
	@Test
	public void queryImageJmsList() {
		ImageJmsVo message = new ImageJmsVo();
		message.setId("2c94811d321e2edc01321ebc2989040e");
		imageService.queryImageJmsList(message);
	}
	@Test
	public void updateImageJmsStatus() {
		ImageJmsVo message = new ImageJmsVo();
		message.setId("2c94811d321e2edc01321ebc2989040e");
		imageService.updateImageJmsStatus(message);
	}
	
	@Test
	public void queryImageJmsCount() {
		Integer count = imageService.queryImageJmsCount(null);
		System.out.println(count);
		assertTrue(count > 0);
	}
	
	@Test
	public void queryImageJmsListByPage() {
		List<ImageJmsVo> list = imageService.queryImageJmsList(null, 1, 30);
		System.out.println(list.size());
	}
	
	@Test
	public void queryImageJmsListByIds() {
		List<ImageJmsVo> list;
		try {
			list = imageService.queryImageJmsListByIds(new String[]{"2c94811d321e2edc01321ebc2989040e","6cd7ce3605014aa59b8989a3de4a2906"});
			for(ImageJmsVo vo:list){
				System.out.println(vo.getCreateTime());
			}
			System.out.println(list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryImageJmsUntreated() {
		List<ImageJmsVo> list;
		try {
			list = imageService.getUntreated();
			for(ImageJmsVo vo:list){
				System.out.println(vo.getCreateTime());
			}
			System.out.println(list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void updateImageJmsStatusInvalid() {
		try {
			imageService.updateImageJmsStatusInvalid(new String[]{"3b69093356c746f69ce6d6cd55fee982"});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void delMessage() {
		try {
			imageService.delMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTaobaoItemImgURL() {
		try {
			imageService
					.updateTaobaoItemImgURL(
							39004976901L,
							"http://img02.taobaocdn.com/bao/uploaded/i2/320083279/T26u3QXK8aXXXXXXXX_!!320083279.jpg",
							"http://img02.taobaocdn.com/bao/uploaded/i2/320083279/T26u3QXK8aXXXXXXXX_!!320083279.jpg",
							"http://img02.taobaocdn.com/bao/uploaded/i2/320083279/T26u3QXK8aXXXXXXXX_!!320083279.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTaobaoItemImgDesc() {
		try {
			imageService.updateTaobaoImgDesc(123L, "12652990527","http://img02.taobaocdn.com/bao/uploaded/i2/320083279/T26u3QXK8aXXXXXXXX_!!320083279.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
