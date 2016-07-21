package com.yougou.merchant.api;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.pic.service.IPictureService;
import com.yougou.merchant.api.pic.service.vo.MerchantPicture;
import com.yougou.merchant.api.pic.service.vo.MerchantPictureCatalog;
import com.yougou.merchant.api.pic.service.vo.MerchantPictureVO;


@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MerchantPicTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Resource
    private IPictureService merchantPictureService;
    
	@Test
	public void catalogTest() {

		String id="100103";
		MerchantPictureCatalog pictureCatalog = new MerchantPictureCatalog();
		pictureCatalog.setCatalogName("测试目录100101");
		pictureCatalog.setCreateTime(new Date());
		pictureCatalog.setId(id);
		pictureCatalog.setMerchantCode("SP20130821678648");
		pictureCatalog.setParentId("0");
		pictureCatalog.setParentName("xxx");
		pictureCatalog.setShopId(null);
		try {
			pictureCatalog.setParentId("10010");
			merchantPictureService.insertPicCatalog(pictureCatalog);
			
			
			//merchantPictureService.updatePicCatalog(pictureCatalog);
			merchantPictureService.delPicCatalog("10010");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void picTest() {

		String id=System.currentTimeMillis()+"";
		MerchantPicture picture=new MerchantPicture();
		picture.setCatalogId("123");
		picture.setCreated(new Date());
		picture.setId(id);
		picture.setMerchantCode("SP20130821678648");
		picture.setPicName("测试图片");
		picture.setPicPath("/pic/ceshi");
		picture.setPicSize(50L);
		picture.setThumbnaiPicName("");
		try {
			merchantPictureService.insertPic(picture);
			
			picture.setPicName("测试图片1");
			picture.setPicPath("/pic/ceshi1");
			picture.setPicSize(51L);
			picture.setCatalogId("1234");
			picture.setThumbnaiPicName("测试图片2");
			merchantPictureService.updatePic(picture);
			//merchantPictureService.delPic(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void catalogListTest(){
		try {
			MerchantPictureCatalog c=merchantPictureService.getPicCatalog("1df4f8ab532143cfa91162c7603bdb74");
			
			List<MerchantPictureCatalog> list=merchantPictureService.queryCommodityPicCatalogList("SP20130821678648");
			System.out.println(list.size());
			if(list!=null){
				for(MerchantPictureCatalog catalog:list){
					System.out.println(catalog.getId());
					System.out.println(catalog.getMerchantCode());
					System.out.println(catalog.getParentId());
					System.out.println(catalog.getParentName());
					System.out.println(catalog.getShopId());
					System.out.println(catalog.getCreateTime());
					System.out.println("-----------------------------------------------");
				}
			}
			List<MerchantPictureCatalog> list2=merchantPictureService.queryShopPicCatalogList("SP20130821678648","123");
			if(list2!=null){
				for(MerchantPictureCatalog catalog:list2){
					System.out.println(catalog.getId());
					System.out.println(catalog.getMerchantCode());
					System.out.println(catalog.getParentId());
					System.out.println(catalog.getParentName());
					System.out.println(catalog.getShopId());
					System.out.println(catalog.getCreateTime());
					System.out.println("============================================");
				}
			}
			System.out.println(list2.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getPicTest(){
		try {
			MerchantPicture pic=merchantPictureService.getPic("008a75620f23489faaff15530553f4a7");
			if(pic!=null){
				System.out.println(pic.getCatalogId());
				System.out.println(pic.getId());
				System.out.println(pic.getMerchantCode());
				System.out.println(pic.getPicName());
				System.out.println(pic.getPicPath());
				System.out.println(pic.getThumbnaiPicName());
				System.out.println(pic.getCreated());
				System.out.println(pic.getPicSize());
				System.out.println(pic.getWidth());
				System.out.println(pic.getHeight());
				System.out.println(pic.getPicType());
				System.out.println(pic.getSourcePicName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void picListTest(){
		MerchantPictureVO merchantPictureVO=new MerchantPictureVO();
		merchantPictureVO.setCatalogId("d97781410c73428d82d79977f589a84e");
		//merchantPictureVO.setCreatedEnd(new Date());
		//merchantPictureVO.setCreatedStart(new Date(System.currentTimeMillis()-1000*60*60*24*3));
		merchantPictureVO.setMerchantCode("SP20130821678648");
		//merchantPictureVO.setSrcPicName("ln.jpg");
		merchantPictureVO.setShopId(null);
		try {
/*			PageFinder<MerchantPicture> picList=merchantPictureService.queryCommodityPicListByPage(merchantPictureVO, new Query());
			if(picList!=null){
				for(MerchantPicture pic:picList.getData()){
					System.out.println(pic.getCatalogId());
					System.out.println(pic.getId());
					System.out.println(pic.getMerchantCode());
					System.out.println(pic.getPicName());
					System.out.println(pic.getPicPath());
					System.out.println(pic.getThumbnaiPicName());
					System.out.println(pic.getCreated());
					System.out.println(pic.getPicSize());
					System.out.println(pic.getWidth());
					System.out.println(pic.getHeight());
					System.out.println(pic.getPicType());
					System.out.println(pic.getSourcePicName());
					System.out.println("============================================");
				}
			}*/
			merchantPictureVO.setShopId("d97781410c73428d82d79977f589a84e");
			PageFinder<MerchantPicture> picList2=merchantPictureService.getPicListByPage(merchantPictureVO, new Query());
			if(picList2!=null){
				for(MerchantPicture pic:picList2.getData()){
					System.out.println(pic.getCatalogId());
					System.out.println(pic.getId());
					System.out.println(pic.getMerchantCode());
					System.out.println(pic.getPicName());
					System.out.println(pic.getPicPath());
					System.out.println(pic.getThumbnaiPicName());
					System.out.println(pic.getCreated());
					System.out.println(pic.getPicSize());
					System.out.println(pic.getPicType());
					System.out.println("====================333========================");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void movePic(){
		MerchantPicture m;
		try {
			merchantPictureService.movePics(new String[]{"000c6a8ede394734943aac2101ce5d07","000773dca0414321a564757547b4b1f9"}, "123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
