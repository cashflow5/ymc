package com.yougou.merchant.dao;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.help.dao.HelpMapper;
import com.yougou.merchant.api.help.vo.HelpCenterContent;
import com.yougou.merchant.api.help.vo.HelpCenterImg;
import com.yougou.merchant.api.help.vo.HelpCenterLog;
import com.yougou.merchant.api.help.vo.HelpCenterMenu;

/**
 * 
 * @ClassName: ContactsDaoTest
 * @author huang.tao
 * @date 2013-12-16 下午6:03:41
 * 
 */
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class HelpDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private HelpMapper helpMapper;

	@Test
	public void insertHelpLog() {
		HelpCenterLog vo = new HelpCenterLog();
		vo.setId("110");
		vo.setContent("sdgdfg");
		vo.setMenuId("sdgdfg");
		vo.setOperator("dgdfg");
		vo.setUpdateTime(new Date());
		
		helpMapper.insertHelpLog(vo);
	}
	
	@Test
	public void insertHelpContent() {
		HelpCenterContent vo = new HelpCenterContent();
		vo.setId("110");
		vo.setContent("sdgdfg");
		vo.setMenuId("sdgdfg");
		
		helpMapper.insertHelpContent(vo);
	}
	
	@Test
	public void insertHelpImg() {
		HelpCenterImg vo = new HelpCenterImg();
		vo.setId("110");
		vo.setCreated(new Date());
		vo.setPicName("dgdg");
		vo.setUpdated(new Date());
		
		helpMapper.insertHelpImg(vo);
	}
	
	@Test
	public void insertHelpMenu() {
		HelpCenterMenu vo = new HelpCenterMenu();
		vo.setId("110");
		vo.setIsLeaf(1);
		vo.setLevel(2);
		vo.setMenuName("sdgdg");
		vo.setOrderNo(1);
		vo.setParentId("-1");
		vo.setSubId("1");
		
		helpMapper.insertHelpMenu(vo);
	}
	
	@Test
	public void updateHelpContent() {
		HelpCenterContent vo = new HelpCenterContent();
		vo.setId("110");
		vo.setContent("_sdgdfg");
		vo.setMenuId("_sdgdfg");
		
		helpMapper.updateHelpContent(vo);
	}
	
	@Test
	public void updateHelpMenu() {
		HelpCenterMenu vo = new HelpCenterMenu();
		vo.setId("110");
		vo.setIsLeaf(10);
		vo.setLevel(20);
		vo.setMenuName("_sdgdg");
		vo.setOrderNo(10);
		vo.setParentId("-10");
		vo.setSubId("11");
		
		helpMapper.updateHelpMenu(vo);
	}
	
	@Test
	public void getContentByMenuId() {
		HelpCenterContent _menu = helpMapper.getContentByMenuId("8a8094e83fcb82f5013fcb85e6270019");
		assertTrue(_menu != null);
	}
}
