package com.yougou.merchant.api;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.yop.api.IOpenApiService;


@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class OpenApiTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private IOpenApiService openApiService;


	@Test
	public void testBeanInjection() throws Exception {
		String str = "http://localhost:8080/mms/api.sc?sign=1d5abb9ae480932de265f31625651384&sign_method=MD5&timestamp=2014-3-31 17:1:58&category=8a8094dc3bacb28f013bb1ef70a00001&submit=POST&app_key=403727c3_13bd534c154__7fee&method=yougou.chain.order.source&format=XML";
		System.out.println(openApiService.apiAuth(str));
	}
}
