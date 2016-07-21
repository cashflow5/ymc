package com.yougou.merchant.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.common.UUIDGenerator;
import com.yougou.merchant.api.monitor.dao.ReportMapper;
import com.yougou.merchant.api.monitor.vo.AnalyzeAppkey;
import com.yougou.merchant.api.monitor.vo.AnalyzeAppkeyDay;
import com.yougou.merchant.api.monitor.vo.AnalyzeDetail;
import com.yougou.merchant.api.monitor.vo.AnalyzeInterface;
import com.yougou.merchant.api.monitor.vo.AnalyzeInterfaceDay;
import com.yougou.merchant.api.monitor.vo.AnalyzeIp;
import com.yougou.merchant.api.monitor.vo.MonitorInvalidIp;

/**
 * 
 * @ClassName: ContactsDaoTest
 * @author huang.tao
 * @date 2013-12-16 下午6:03:41
 * 
 */
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ReportDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private ReportMapper reportMapper;
	
	@Test
	public void insertAnalyzeDetail() {
		AnalyzeDetail vo = new AnalyzeDetail();
		vo.setId(UUIDGenerator.getUUID());
		vo.setApiId("ee5012a1c4f511e19c4f5cf3fc0c2d70");
		vo.setAppKey("308c6608_13de3f418e5__7fef");
		vo.setCallCount(10000);
		vo.setFailCallCount(20);
		vo.setMaxFrequency(200);
		vo.setSucessCallCount(9980);
		vo.setTimeQuantum("2013-01-14 14:00:00");
		vo.setValidCallCount(10000);
		vo.setCreateTime(new Date());
		
		
		reportMapper.insertAnalyzeDetail(vo);
	}
	
	@Test
	public void insertAnalyzeAppkeyDay() {
		AnalyzeAppkeyDay vo = new AnalyzeAppkeyDay();
		vo.setId(UUIDGenerator.getUUID());
		vo.setAppKey("308c6608_13de3f418e5__7fef");
		vo.setCallCount(2000);
		vo.setCreateTime(new Date());
		vo.setFailCallCount(110);
		vo.setMaxFrequency(1000);
		vo.setOrderMoney(5000.0);
		vo.setOrderQty(102);
		vo.setSucessCallCount(1890);
		vo.setTimeQuantum("2013-01-14 00:00:00");
		vo.setValidCallCount(2000);
		
		reportMapper.insertAnalyzeAppkeyDay(vo);
	}
	
	@Test
	public void insertAnalyzeAppkey() {
		AnalyzeAppkey vo = new AnalyzeAppkey();
		vo.setId(UUIDGenerator.getUUID());
		vo.setAppKey("308c6608_13de3f418e5__7fef");
		vo.setCallCount(2000);
		vo.setCreateTime(new Date());
		vo.setFailCallCount(110);
		vo.setMaxFrequency(1000);
		vo.setSucessCallCount(1890);
		vo.setTimeQuantum("2013-01-14 01:00:00");
		vo.setValidCallCount(2000);
		vo.setAvgExTime(20.1);
		
		reportMapper.insertAnalyzeAppkey(vo);
	}
	
	@Test
	public void insertAnalyzeInterfaceDay() {
		AnalyzeInterfaceDay vo = new AnalyzeInterfaceDay();
		vo.setId(UUIDGenerator.getUUID());
		vo.setApiId("ee5012a1c4f511e19c4f5cf3fc0c2d70");
		vo.setAvgExTime(0.24);
		vo.setCallCount(9000);
		vo.setCreateTime(new Date());
		vo.setFailCallCount(120);
		vo.setMaxAppkeyNums(200);
		vo.setMaxFrequency(400);
		vo.setSucessCallCount(8880);
		vo.setTimeQuantum("2013-01-14 00:00:00");
		vo.setValidCallCount(9000);
		
		reportMapper.insertAnalyzeInterfaceDay(vo);
	}

	@Test
	public void insertAnalyzeInterface() {
		AnalyzeInterface vo = new AnalyzeInterface();
		vo.setId(UUIDGenerator.getUUID());
		vo.setApiId("ee5012a1c4f511e19c4f5cf3fc0c2d70");
		vo.setAvgExTime(0.24);
		vo.setCallCount(9000);
		vo.setCreateTime(new Date());
		vo.setFailCallCount(120);
		vo.setMaxAppkeyNums(200);
		vo.setMaxFrequency(400);
		vo.setSucessCallCount(8880);
		vo.setTimeQuantum("2013-01-14 00:00:00");
		vo.setValidCallCount(9000);
		
		reportMapper.insertAnalyzeInterface(vo);
	}
	
	@Test
	public void insertAnalyzeIp() {
		AnalyzeIp vo = new AnalyzeIp();
		vo.setId(UUIDGenerator.getUUID());
		vo.setAppKey("308c6608_13de3f418e5__7fef");
		vo.setIp("10.0.30.164");
		vo.setTimeQuantum("2013-01-14 00:00:00");
		
		reportMapper.insertAnalyzeIp(vo);
	}
	
	@Test
	public void queryAnalyzeIps() {
		List<Map<String, String>> list = reportMapper.queryAnalyzeIps("308c6608_13de3f418e5__7fef", "2013-01-14 00:00:00", "2013-01-14 00:00:00");
		
		Assert.assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void insertMonitorInvalidIp() {
		MonitorInvalidIp vo = new MonitorInvalidIp();
		vo.setId(UUIDGenerator.getUUID());
		vo.setInvalidCount(20);
		vo.setLastCallTime(new Date());
		vo.setIp("10.0.30.164");
		vo.setTimeQuantum("2013-01-14 00:00:00");
		vo.setDoubtType(1);
		
		reportMapper.insertMonitorInvalidIp(vo);
	}
	
	@Test
	public void queryMonitorInvalidIps() {
		List<MonitorInvalidIp> list = reportMapper.queryMonitorInvalidIps(null, null, "", "", new RowBounds());
		
		Assert.assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void queryMonitorInvalidIpCount() {
		Integer count = reportMapper.queryMonitorInvalidIpCount(null, null, "", "");
		
		Assert.assertTrue(count > 0);
	}
	
	@Test
	public void queryApiIntegerGeneral() {
		List<AnalyzeInterfaceDay> list = reportMapper.queryApiIntegerGeneral("2014-01-13", "2014-01-20");
		
		Assert.assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void queryAnalyzeDetailByApi() {
		List<String> list = new ArrayList<String>();
		list.add("_34870bef_13bcff5977d__7fef");
		list.add("403727c3_13bd534c154__7fee");
		List<AnalyzeDetail> _list = reportMapper.queryAnalyzeDetailByApi("8a8094f83bcfb8c9013bcfcfb48d0001", list, "2014-01-21", "2014-01-21");
		
		Assert.assertTrue(CollectionUtils.isNotEmpty(_list));
	}
	
	@Test
	public void queryApiIntegerGeneralForToday() {
		List<AnalyzeInterfaceDay> list = reportMapper.queryApiIntegerGeneralForToday();
		
		Assert.assertTrue(CollectionUtils.isNotEmpty(list));
	}
	@Test
	public void queryAppKeyGeneralForToday() {
		List<AnalyzeAppkeyDay> list = reportMapper.queryAppKeyGeneralForToday();
		
		Assert.assertTrue(CollectionUtils.isNotEmpty(list));
	}
	@Test
	public void queryTrendAnalysisForToday() {
		List<AnalyzeInterface> list = reportMapper.queryTrendAnalysisForToday(1);
		
		Assert.assertTrue(CollectionUtils.isNotEmpty(list));
		
	}
}
