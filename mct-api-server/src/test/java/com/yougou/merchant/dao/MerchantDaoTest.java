package com.yougou.merchant.dao;

import static org.junit.Assert.assertTrue;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.common.UUIDGenerator;
import com.yougou.merchant.api.supplier.dao.MerchantMapper;
import com.yougou.merchant.api.supplier.vo.MerchantMenu;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog;
import com.yougou.merchant.api.supplier.vo.UserAuthority;

/**
 * 
 * @ClassName: ContactsDaoTest
 * @author huang.tao
 * @date 2013-12-16 下午6:03:41
 * 
 */
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class MerchantDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private MerchantMapper merchantMapper;

	@Test
	public void insertYmcMenuResource() {
		MerchantMenu vo = new MerchantMenu();
		vo.setId("110");
		vo.setAuthrityModule(7);
		vo.setAuthrityName("haungtao_test");
		vo.setAuthrityURL("/sfgdgdf/dfg");
		vo.setCreateTime(new Date());
		vo.setRemark("xxxx");
		vo.setSortNo(1);
		
		merchantMapper.insertYmcMenuResource(vo);
	}
	
	@Test
	public void updateYmcMenuResource() {
		MerchantMenu vo = new MerchantMenu();
		vo.setId("110");
		vo.setAuthrityModule(6);
		vo.setAuthrityName("_haungtao_test");
		vo.setAuthrityURL("_/sfgdgdf/dfg");
		vo.setCreateTime(new Date());
		vo.setRemark("_xxxx");
		vo.setSortNo(2);
		
		merchantMapper.updateYmcMenuResource(vo);
	}

	@Test
	public void queryMerchantMenuList() {
		List<MerchantMenu> list = merchantMapper.queryMerchantMenuList("haungtao", new RowBounds());
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void queryMerchantMenuCount() {
		Integer count = merchantMapper.queryMerchantMenuCount("haungtao");
		assertTrue(count > 0);
	}
	
	@Test
	public void getMerchantMenuById() {
		MerchantMenu vo = merchantMapper.getMerchantMenuById("110");
		assertTrue(vo != null);
	}
	
	@Test
	public void deleteYmcMenuResourceById() {
		merchantMapper.deleteYmcMenuResourceById("110");
	}
	
	@Test
	public void insertUserAuthority() {
		UserAuthority user = new UserAuthority();
		user.setId("xxcc");
		user.setAuthorityId("8a809455372fb49c01372fba4d650002");
		user.setCreateDate(new Date());
		user.setRemark("xxx");
		user.setUserId("110");
		
		merchantMapper.insertUserAuthority(user);
	}
	
	@Test
	public void deleteUserAuthorityByUserId() {
		merchantMapper.deleteUserAuthorityByUserId("110");
	}
	
	@Test
	public void queryUserAuthoritysByUserId() {
		List<UserAuthority> list = merchantMapper.queryUserAuthoritysByUserId("110");
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void queryYougouAdminMerchantList() {
		List<Map<String, Object>> list = merchantMapper.queryYougouAdminMerchantList("8a8094ea3c48302f013c4836e8ab0003", null, null, null, new RowBounds());
		
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void queryMerchantNotHadList() {
		List<Map<String, Object>> list = merchantMapper.queryMerchantNotHadList("8a8094ea3c48302f013c4836e8ab0003", "SP201210317679", null, null, new RowBounds());
		
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void queryYougouAdminMerchantCount() {
		Integer count = merchantMapper.queryYougouAdminMerchantCount("8a8094ea3c48302f013c4836e8ab0003", null, null, null);
		
		assertTrue(count > 0);
	}
	
	@Test
	public void queryMerchantNotHadCount() {
		Integer count = merchantMapper.queryMerchantNotHadCount("8a8094ea3c48302f013c4836e8ab0003", "SP201210317679", null, null);
		
		assertTrue(count > 0);
	}
	
	@Test
	public void insertYougouAdminMerchant() {
		merchantMapper.insertYougouAdminMerchant("110", "sdgdfg", "SPweregdfg");
	}
	
	@Test
	public void deleteYougouAdminMerchant() {
		merchantMapper.deleteYougouAdminMerchant("110");
	}
	
	@Test
	public void updateMerchantUser() {
		com.yougou.merchant.api.supplier.vo.MerchantUser vo = new com.yougou.merchant.api.supplier.vo.MerchantUser();
		vo.setId("f184c96f94ae4e7cbea4a1637127b170");
		vo.setEmail("taohuanga@163.com");
		vo.setEmailstatus(NumberUtils.INTEGER_ZERO); //设置未激活
		vo.setDeleteFlag(NumberUtils.INTEGER_ONE);
		merchantMapper.updateMerchantUser(vo);
		
	}
	
	@Test
	public void insertMerchantLog() {
		MerchantOperationLog log = new MerchantOperationLog();
		log.setId(UUIDGenerator.getUUID());
		log.setMerchantCode("sdgdfg");
		log.setOperator("xxx");
		log.setOperated(new Date());
		log.setOperationType(MerchantOperationLog.OperationType.ACCOUNT);
		log.setOperationNotes("sdgdfgfg");
		
		merchantMapper.insertMerchantLog(log);
	}
}
