package com.yougou.merchant.api;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.supplier.service.IMerchantsApi;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog;
import com.yougou.merchant.api.supplier.vo.MerchantRejectedAddressVo;
import com.yougou.merchant.api.supplier.vo.MerchantUser;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MerchantsTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Resource
    private IMerchantsApi merchantService;

    @Test
    public void queryMerchantUserList() {
        MerchantUser user = new MerchantUser();
        user.setMerchantCode("SP");

        PageFinder<MerchantUser> pageFinder = merchantService.queryMerchantUserList(user, new Query());

        assertTrue(CollectionUtils.isNotEmpty(pageFinder.getData()));
    }

    @Test
    public void getMerchantRejectedAddressList() {
        MerchantRejectedAddressVo vo = new MerchantRejectedAddressVo();
        PageFinder<MerchantRejectedAddressVo> pageFinder = merchantService.getMerchantRejectedAddressList(vo, new Query());
        assertTrue(CollectionUtils.isNotEmpty(pageFinder.getData()));
    }

    @Test
    public void queryMerchantOperLogList() {
        PageFinder<MerchantOperationLog> pageFinder = merchantService.queryMerchantOperLogList("SP20130726545722", new Query());
        assertTrue(CollectionUtils.isNotEmpty(pageFinder.getData()));
    }
    
    @Test
	public void getShipmentDayHour() throws Exception {
    	
    	//-1 表于开始时间不能大于结束时间
    	Double hour1 = merchantService.getShipmentDayHour("2014-01-09 01:00:00", "2014-01-08 09:31:00");
    	assertTrue(hour1 == -1);
    	
    	//-2时间段不能大于30天
    	Double hour2 = merchantService.getShipmentDayHour("2013-01-09 01:00:00", "2014-01-08 09:31:00");
    	assertTrue(hour2 == -2);
    	
    	//时间段都是货日
		Double hour3 = merchantService.getShipmentDayHour("2014-01-07 01:00:00", "2014-01-08 09:31:00");
		System.out.println(hour3);
		assertTrue(hour3 == 32.52);
		
		//时间段都非货日
		Double hour4 = merchantService.getShipmentDayHour("2014-01-04 01:00:00", "2014-01-05 09:31:00");
		assertTrue(hour4 == 0);
		

    	//时间段有非货日和发货日
		Double hour6 = merchantService.getShipmentDayHour("2014-01-04 01:00:00", "2014-01-06 09:31:00");
		assertTrue(hour6 == 32.52);
		
		//时间段有非货日和发货日
		Double hour7 = merchantService.getShipmentDayHour("2014-01-04 01:00:00", "2014-01-07 09:31:00");
		assertTrue(hour7 == 56.52);
		
	
	}
    
    @Test
	public void getShipmentCountdownHour() throws Exception {
    	SimpleDateFormat dateTimeSf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	double x=merchantService.getShipmentCountdownHour(dateTimeSf.parse("2014-04-18 15:16:59"),dateTimeSf.parse("2014-04-20 15:16:59"));
    	Double hour1 = merchantService.getShipmentDayHour("2014-04-18 15:16:59", "2014-04-20 15:16:59");
    	System.out.println(x);
    	System.out.println(hour1);
    }
}
