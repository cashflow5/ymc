package com.yougou.merchant.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.monitor.dao.WarnMapper;
import com.yougou.merchant.api.monitor.vo.MonitorAppkeyWarnDetail;
import com.yougou.merchant.api.monitor.vo.MonitorDayWarnDetail;
import com.yougou.merchant.api.monitor.vo.MonitorEarlyWarnQueryVo;
import com.yougou.merchant.api.monitor.vo.MonitorEarlyWarning;
import com.yougou.merchant.api.monitor.vo.MonitorRateWarnDetail;
import com.yougou.merchant.api.monitor.vo.MonitorSuccRateWarnDetail;

/**
 * 
 * @ClassName: WarnDaoTest
 * @author huang.tao
 * @date 2013-12-16 下午6:03:41
 * 
 */
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class WarnDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private WarnMapper warnMapper;

	@Test
	public void insertMonitorEarlyWarning() {
		MonitorEarlyWarning vo = new MonitorEarlyWarning();
		vo.setId("112");
		vo.setAppKey("6242ebb2_1407b95bc46__7fb7");
		vo.setTimeQuantum("2014-02-26");
		vo.setWarmAppkeyCallCount(1);
		vo.setWarmDayCallCount(1);
		vo.setWarmRateCount(1);
		vo.setWarmSuccessCount(1);
		
		warnMapper.insertMonitorEarlyWarning(vo);
	}
	
	@Test
	public void insertMonitorAppkeyWarnDetail() {
		MonitorAppkeyWarnDetail vo = new MonitorAppkeyWarnDetail();
		vo.setId("110");
		vo.setAppKey("6242ebb2_1407b95bc46__7fb7");
		vo.setTimeQuantum("2014-02-26");
		vo.setAppkeyCallCount(100000);
		vo.setWarmAppkeyCallCount(80000);
		
		warnMapper.insertMonitorAppkeyWarnDetail(vo);
	}
	
	@Test
	public void insertMonitorDayWarnDetail() {
		MonitorDayWarnDetail vo = new MonitorDayWarnDetail();
		vo.setId("110");
		vo.setAppKey("6242ebb2_1407b95bc46__7fb7");
		vo.setApiId("ee5010b0c4f511e19c4f5cf3fc0c2d70");
		vo.setDayCallCount(10000);
		vo.setTimeQuantum("2014-02-26");
		vo.setWarmDayCallCount(8000);
		
		warnMapper.insertMonitorDayWarnDetail(vo);
	}
	
	@Test
	public void insertMonitorSuccRateWarnDetail() {
		MonitorSuccRateWarnDetail vo = new MonitorSuccRateWarnDetail();
		vo.setId("110");
		vo.setAppKey("6242ebb2_1407b95bc46__7fb7");
		vo.setApiId("ee5010b0c4f511e19c4f5cf3fc0c2d70");
		vo.setTimeQuantum("2014-02-26");
		vo.setFailCallCount(20000);
		vo.setSuccessCallCount(30000);
		
		warnMapper.insertMonitorSuccRateWarnDetail(vo);
	}
	
	@Test
	public void insertMonitorRateWarnDetail() {
		MonitorRateWarnDetail vo = new MonitorRateWarnDetail();
		vo.setId("110");
		vo.setAppKey("6242ebb2_1407b95bc46__7fb7");
		vo.setApiId("ee5010b0c4f511e19c4f5cf3fc0c2d70");
		vo.setTimeQuantum("2014-02-26");
		vo.setRateCallCount(12000);
		vo.setWarmRateCallCount(10000);
		
		warnMapper.insertMonitorRateWarnDetail(vo);
	}
	
	@Test
	public void queryMonitorEarlyWarning() {
		MonitorEarlyWarnQueryVo vo = new MonitorEarlyWarnQueryVo();
		vo.setAppKey("6242ebb2_1407b95bc46__7fb7");
		vo.setStartTime("2014-02-26");
		vo.setEndTime("2014-02-26");
		
		List<MonitorEarlyWarning> list = warnMapper.queryMonitorEarlyWarning(vo, new RowBounds());
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void queryMonitorEarlyWarningCount() {
		MonitorEarlyWarnQueryVo vo = new MonitorEarlyWarnQueryVo();
		vo.setAppKey("6242ebb2_1407b95bc46__7fb7");
		vo.setStartTime("2014-02-26");
		vo.setEndTime("2014-02-26");
		Integer count = warnMapper.queryMonitorEarlyWarningCount(vo);
		assertTrue(count > 0);
	}
	
	@Test
	public void queryAppKeyEarlyWarningDetail() {
		MonitorEarlyWarnQueryVo vo = new MonitorEarlyWarnQueryVo();
		vo.setAppKey("6242ebb2_1407b95bc46__7fb7");
		vo.setStartTime("2014-02-26");
		vo.setEndTime("2014-02-26");
		List<MonitorAppkeyWarnDetail> list = warnMapper.queryAppKeyEarlyWarningDetail(vo);
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void queryApiEarlyWarningDetail() {
		MonitorEarlyWarnQueryVo vo = new MonitorEarlyWarnQueryVo();
		vo.setAppKey("6242ebb2_1407b95bc46__7fb7");
		vo.setStartTime("2014-02-26");
		vo.setEndTime("2014-02-26");
		List<MonitorDayWarnDetail> list = warnMapper.queryApiEarlyWarningDetail(vo);
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void querySuccRateWarnDetail() {
		MonitorEarlyWarnQueryVo vo = new MonitorEarlyWarnQueryVo();
		vo.setAppKey("6242ebb2_1407b95bc46__7fb7");
		vo.setStartTime("2014-02-26");
		vo.setEndTime("2014-02-26");
		List<MonitorSuccRateWarnDetail> list = warnMapper.querySuccRateWarnDetail(vo);
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void queryRateWarnDetail() {
		MonitorEarlyWarnQueryVo vo = new MonitorEarlyWarnQueryVo();
		vo.setAppKey("6242ebb2_1407b95bc46__7fb7");
		vo.setStartTime("2014-02-26");
		vo.setEndTime("2014-02-26");
		List<MonitorRateWarnDetail> list = warnMapper.queryRateWarnDetail(vo);
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
	
	@Test
	public void queryApiFrequencyBynow() {
		MonitorEarlyWarnQueryVo vo = new MonitorEarlyWarnQueryVo();
		vo.setStartTime("2014-02-01");
		vo.setEndTime("2014-02-26");
		List<MonitorRateWarnDetail> list = warnMapper.queryApiFrequencyBynow(vo);
		assertTrue(CollectionUtils.isNotEmpty(list));
	}
}
