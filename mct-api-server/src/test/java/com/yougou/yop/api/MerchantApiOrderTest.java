package com.yougou.yop.api;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class MerchantApiOrderTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Resource
    private IMerchantApiOrderService iMerchantApiOrderService;

    @Test
    public void queryCanceledOrder() throws Exception {
    	Map<String, Object> parameterMap = new HashMap<String, Object>();
    	parameterMap.put("merchant_code", "SP20130821678648");
    	
		Object output = iMerchantApiOrderService.queryCanceledOrder(parameterMap);
		assertTrue(output != null);
    }

    @Test
    public void queryInterceptOrder() throws Exception {
    	Map<String, Object> parameterMap = new HashMap<String, Object>();
    	parameterMap.put("order_sub_no", "C740226160007_1");
    	
		Object output = iMerchantApiOrderService.queryInterceptOrder(parameterMap);
		assertTrue(output != null);
    }
}
