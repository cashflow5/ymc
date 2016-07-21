package com.yougou.merchant.dao;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.common.UUIDGenerator;
import com.yougou.merchant.api.help.dao.FeebackMapper;
import com.yougou.merchant.api.help.vo.Feeback;
import com.yougou.merchant.api.help.vo.FeebackReply;
import com.yougou.merchant.api.help.vo.FeebackVo;

/**
 * 
 * @ClassName: ContactsDaoTest
 * @author huang.tao
 * @date 2013-12-16 下午6:03:41
 * 
 */
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class FeebackDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private FeebackMapper feebackMapper;

	@Test
	public void insertFeeback() {
		Feeback vo = new Feeback();
		vo.setId("110");
		vo.setContent("sdgdfg");
		vo.setUpdateTime(new Date());
		vo.setCreateTime(new Date());
		vo.setEmail("taohuanga@163.com");
		vo.setFirstCate("dgf");
		vo.setSecondCate("sdgdfg");
		vo.setMerchantCode("SPdgdgfg");
		vo.setMerchantName("stetret");
		
		feebackMapper.insertFeeback(vo);
	}
	
	@Test
	public void insertFeebackReply() {
		FeebackReply vo = new FeebackReply();
		vo.setId(UUIDGenerator.getUUID());
		vo.setCreateTime(new Date());
		vo.setIsDeleted("1");
		vo.setReplyContent("sdgdfg");
		vo.setReplyPerson("huang.tao");
		vo.setUpdateTime(new Date());
		vo.setFeebackId("110");
		
		feebackMapper.insertFeebackReply(vo);
	}
	
	@Test
	public void queryFeebackListByVo() {
		FeebackVo vo = new FeebackVo();
		List<Feeback> list = feebackMapper.queryFeebackListByVo(vo, new RowBounds());
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void queryFeebackCountByVo() {
		FeebackVo vo = new FeebackVo();
		Integer count = feebackMapper.queryFeebackCountByVo(vo);
		assertTrue(count > 0);
	}
	
	@Test
	public void getFeebackById() {
		Feeback bo = feebackMapper.getFeebackById("110");
		
		assertTrue(bo != null);	
	}
	
	@Test
	public void updateFeeback() {
		Feeback vo = new Feeback();
		vo.setId("110");
		vo.setContent("_sdgdfg");
		vo.setUpdateTime(new Date());
		vo.setCreateTime(new Date());
		vo.setEmail("_taohuanga@163.com");
		vo.setFirstCate("_dgf");
		vo.setSecondCate("_sdgdfg");
		vo.setMerchantCode("_SPdgdgfg");
		vo.setMerchantName("_stetret");
		vo.setPhone("_dgdfg");
		
		feebackMapper.updateFeeback(vo);
	}
	
	@Test
	public void getFeebackReplyById() {
		List<FeebackReply> list = feebackMapper.getFeebackReplyById("110");
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void updateFeebackReply() {
		FeebackReply vo = new FeebackReply();
		vo.setId("38bcae5619dd4c378a84989484e1a244");
		vo.setCreateTime(new Date());
		vo.setIsDeleted("2");
		vo.setReplyContent("_sdgdfg");
		vo.setReplyPerson("_huang.tao");
		vo.setUpdateTime(new Date());
		vo.setFeebackId("110");
		
		feebackMapper.updateFeebackReply(vo);
	}
}

