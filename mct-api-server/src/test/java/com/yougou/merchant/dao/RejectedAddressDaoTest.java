package com.yougou.merchant.dao;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.supplier.dao.RejectedAddressMapper;
import com.yougou.merchant.api.supplier.vo.ExpressTemplate;

/**
 * 
 * @ClassName: ContactsDaoTest
 * @author huang.tao
 * @date 2013-12-16 下午6:03:41
 * 
 */
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class RejectedAddressDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private RejectedAddressMapper regjectedAddressMapper;

	@Test
	public void insertExpressTemplate() {
		ExpressTemplate vo = new ExpressTemplate();
		vo.setId("110");
		vo.setExpressName("4656");
		vo.setLogisticsId("sdgdfg");
		vo.setBackGroundImage("345646");
		vo.setCommodityNum("grfg");
		vo.setConsigneeAdress("dfgf");
		vo.setConsigneeDay("fghfgh");
		vo.setConsigneeEmail("fgh");
		vo.setConsigneeMonth("hghjhj");
		vo.setConsigneeName("hjjh");
		vo.setConsigneeOneArea("jk");
		vo.setConsigneePhone("jklkl");
		vo.setConsigneeTell("kl");
		vo.setConsigneeThreeArea("l");
		vo.setConsigneeTwoArea("fg");
		vo.setConsigneeYear("ghjhj");
		vo.setFontSize("ghj");
		vo.setHeigth(20.0d);
		vo.setIsBold(1);
		vo.setMoney("hjk");
		vo.setNumber("jkl");
		vo.setOrderSourceId("345456");
		vo.setOrderSubNo("sgdfgd");
		vo.setRemark("kl");
		vo.setShipmentsAdress("vb");
		vo.setShipmentsEmail("vb");
		vo.setShipmentsName("cv");
		vo.setShipmentsOneArea("dfg");
		vo.setShipmentsPhone("dfg");
		vo.setShipmentsTell("dfg");
		vo.setShipmentsThreeArea("vbn");
		vo.setShipmentsTwoArea("vb");
		vo.setTbody("cvbvb");
		vo.setWidth(30.0);
		
		regjectedAddressMapper.insertExpressTemplate(vo);
	}
	
	@Test
	public void updateExpressTemplate() {
		ExpressTemplate vo = new ExpressTemplate();
		vo.setId("110");
		vo.setExpressName("_4656");
		vo.setLogisticsId("sdgdfg");
		vo.setBackGroundImage("_345646");
		vo.setCommodityNum("_grfg");
		vo.setConsigneeAdress("_dfgf");
		vo.setConsigneeDay("_fghfgh");
		vo.setConsigneeEmail("_fgh");
		vo.setConsigneeMonth("_hghjhj");
		vo.setConsigneeName("_hjjh");
		vo.setConsigneeOneArea("_jk");
		vo.setConsigneePhone("_jklkl");
		vo.setConsigneeTell("_kl");
		vo.setConsigneeThreeArea("_l");
		vo.setConsigneeTwoArea("_fg");
		vo.setConsigneeYear("_ghjhj");
		vo.setFontSize("_ghj");
		vo.setHeigth(20.0d);
		vo.setIsBold(1);
		vo.setMoney("_hjk");
		vo.setNumber("_jkl");
		vo.setOrderSourceId("_345456");
		vo.setOrderSubNo("_sgdfgd");
		vo.setRemark("_kl");
		vo.setShipmentsAdress("_vb");
		vo.setShipmentsEmail("_vb");
		vo.setShipmentsName("_cv");
		vo.setShipmentsOneArea("_dfg");
		vo.setShipmentsPhone("_dfg");
		vo.setShipmentsTell("_dfg");
		vo.setShipmentsThreeArea("_vbn");
		vo.setShipmentsTwoArea("_vb");
		vo.setTbody("_cvbvb");
		vo.setWidth(30.0);
		
		regjectedAddressMapper.updateExpressTemplate(vo);
	}

	@Test
	public void getExpressTemplateById() {
		ExpressTemplate template = regjectedAddressMapper.getExpressTemplateById("sdgdfg");
		
		assertTrue(template != null);
	}
	
	
}
