package com.yougou.merchant.api;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.yougou.merchant.api.supplier.service.IContractNewApi;

@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ContractTest {
	@Resource
	@Qualifier(value="contractNewApi")
	private IContractNewApi contractNewApi;
	
	@Test
	public void testGetSettlementType(){
		List<String> ms = new ArrayList<String>();
		ms.add("SP20150707883342");
		Assert.assertTrue(contractNewApi.getSettlementType(ms).size()>0);
	}
	
}
