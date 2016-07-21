package com.yougou.merchant.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.dto.output.QueryOrderDetailOutputDto;
import com.yougou.dto.output.QueryOrderOutputDto;
import com.yougou.merchant.api.active.vo.QueryActivityVo;
import com.yougou.merchant.api.supplier.service.IMerchantsApi;
import com.yougou.merchant.api.supplier.vo.MerchantBankInfoVo;
import com.yougou.yop.api.IMerchantApiOrderService;

@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class MerchantOrderTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Resource
	private IMerchantApiOrderService merchantApiOrderService;
	
	@Resource
	private IMerchantsApi iMerchantsApi;
	

    @Test
    public void queryCanceledOrder()  {
    	System.out.println(";;;;;;;;;;;;");
		MerchantBankInfoVo  obj=iMerchantsApi.queryMerchantBankInfo("SP20150914872914");
		System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
    }
  
}
