package com.yougou.merchant.dao;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.supplier.dao.ContactsMapper;
import com.yougou.merchant.api.supplier.vo.ContactsVo;
import com.yougou.merchant.api.supplier.vo.ContractVo;

/**
 * 
 * @ClassName: ContactsDaoTest
 * @author huang.tao
 * @date 2013-12-16 下午6:03:41
 * 
 */
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class ContactsDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private ContactsMapper contactsMapper;

	@Test
	public void queryContactsByVo() {
		ContactsVo vo = new ContactsVo();
		vo.setSupplierCode("SP20110626925");
		//vo.setSupplier("锐捷实业有限公司");
		//vo.setMobilePhone("135387");
		//vo.setEmail("shisi33");
		List<ContactsVo> list = contactsMapper.queryContactsByVo(vo, new RowBounds());
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void queryContactsCountByVo() {
		ContactsVo vo = new ContactsVo();
		vo.setSupplierCode("SP20110626925");
		
		Integer count = contactsMapper.queryContactsCountByVo(vo);
		assertTrue(count > 0);
	}
	
	@Test
	public void queryContractListByVo() throws ParseException {
		ContractVo vo = new ContractVo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = sdf.parse("2013-01-01 00:00:00");
		Date _d = sdf.parse("2013-07-31 00:00:00");
		vo.setEffectiveDate(d);
		vo.setFailureDate(_d);
		//vo.setSupplierCode("SP20110626925");
		//vo.setSupplier("锐捷实业有限公司");
		//vo.setMobilePhone("135387");
		//vo.setEmail("shisi33");
		List<ContractVo> list = contactsMapper.queryContractListByVo(vo, new RowBounds());
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void queryContractCountByVo() {
		ContractVo vo = new ContractVo();
		vo.setSupplierCode("SP20110626925");
		
		Integer count = contactsMapper.queryContractCountByVo(vo);
		assertTrue(count > 0);
	}
	
	@Test
	public void getContactById() {
		ContactsVo vo = contactsMapper.getContactById("2c94812830ca5d100130ca7cefda0006");
		assertTrue(vo != null);
	}
	
	@Test
	public void insertContact() {
		ContactsVo vo = new ContactsVo();
		vo.setId("110");
		vo.setSupplyId("456879890");
		vo.setAddress("Testertr");
		vo.setContact("huangtao");
		vo.setEmail("taohuangasd@163.com");
		vo.setFax("34546");
		vo.setMobilePhone("12334");
		vo.setTelePhone("1234356");
		vo.setType(1);
		
		contactsMapper.insertContact(vo);
	}
	
	@Test
	public void updateContact() {
		ContactsVo vo = new ContactsVo();
		vo.setId("110");
		vo.setSupplyId("_456879890");
		vo.setAddress("_Testertr");
		vo.setContact("_huangtao");
		vo.setEmail("_taohuangasd@163.com");
		vo.setFax("_34546");
		vo.setMobilePhone("_12334");
		vo.setTelePhone("_1234356");
		vo.setType(2);
		
		try {
			contactsMapper.updateContact(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertContract() {
		ContractVo vo = new ContractVo();
		vo.setId("110");
		vo.setAttachment("3445656");
		vo.setClearingForm(1);
		vo.setContractNo("345456");
		vo.setEffectiveDate(new Date());
		vo.setFailureDate(new Date());
		vo.setSupplierId("3454657");
		vo.setUpdateUser("hh");
		vo.setUpdateTime("xxx");
		
		contactsMapper.insertContract(vo);
	}
	
	@Test
	public void updateContract() {
		ContractVo vo = new ContractVo();
		vo.setId("110");
		vo.setAttachment("_3445656");
		vo.setClearingForm(2);
		vo.setContractNo("_345456");
		vo.setEffectiveDate(new Date());
		vo.setFailureDate(new Date());
		vo.setSupplierId("_3454657");
		vo.setUpdateUser("_hh");
		vo.setUpdateTime("_xxx");
		
		contactsMapper.updateContract(vo);
	}
	
	@Test
	public void getContractById() {
		ContractVo vo = contactsMapper.getContractById("8a8094553821bae3013821c48db00002");
		assertTrue(vo != null);
	}
}
