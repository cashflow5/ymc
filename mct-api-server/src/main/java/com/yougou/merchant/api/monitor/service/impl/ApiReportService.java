/**
 * 
 */
package com.yougou.merchant.api.monitor.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.monitor.dao.ReportMapper;
import com.yougou.merchant.api.monitor.service.IApiReportService;
import com.yougou.merchant.api.monitor.vo.AnalyzeAppkey;
import com.yougou.merchant.api.monitor.vo.AnalyzeAppkeyDay;
import com.yougou.merchant.api.monitor.vo.AnalyzeDetail;
import com.yougou.merchant.api.monitor.vo.AnalyzeInterface;
import com.yougou.merchant.api.monitor.vo.AnalyzeInterfaceDay;
import com.yougou.merchant.api.monitor.vo.AnalyzeIp;
import com.yougou.merchant.api.monitor.vo.MonitorInvalidIp;

/**
 * @author huang.tao
 *
 */
@Service(value="apiReportService")
public class ApiReportService implements IApiReportService {

	@Resource
	private ReportMapper reportMapper;
	
	@Override
	@Transactional
	public boolean insertAnalyzeDetailBatch(List<AnalyzeDetail> details)
			throws Exception {
		if (CollectionUtils.isEmpty(details)) return true;
		
		for (AnalyzeDetail detail : details) {
			reportMapper.insertAnalyzeDetail(detail);
		}
		
		return true;
	}

	@Override
	@Transactional
	public boolean insertAnalyzeAppkeyBatch(List<AnalyzeAppkey> appkeys)
			throws Exception {
		if (CollectionUtils.isEmpty(appkeys)) return true;
		
		for (AnalyzeAppkey detail : appkeys) {
			reportMapper.insertAnalyzeAppkey(detail);
		}
		
		return true;
	}

	@Override
	@Transactional
	public boolean insertAnalyzeAppkeyDayBatch(List<AnalyzeAppkeyDay> appkeyDays)
			throws Exception {
		if (CollectionUtils.isEmpty(appkeyDays)) return true;
		
		for (AnalyzeAppkeyDay appkeyDay : appkeyDays) {
			reportMapper.insertAnalyzeAppkeyDay(appkeyDay);
		}
		
		return true;
	}

	@Override
	@Transactional
	public boolean insertAnalyzeInterfaceBatch(List<AnalyzeInterface> interfaces)
			throws Exception {
		if (CollectionUtils.isEmpty(interfaces)) return true;
		
		for (AnalyzeInterface _interface : interfaces) {
			reportMapper.insertAnalyzeInterface(_interface);
		}
		
		return true;
	}
	
	@Override
	@Transactional
	public boolean insertAnalyzeInterfaceDayBatch(
			List<AnalyzeInterfaceDay> interfaceDays) throws Exception {
		if (CollectionUtils.isEmpty(interfaceDays)) return true;
		
		for (AnalyzeInterfaceDay interfaceDay : interfaceDays) {
			reportMapper.insertAnalyzeInterfaceDay(interfaceDay);
		}
		
		return true;
	}
	
	@Override
	@Transactional
	public boolean insertAnalyzeIpBatch(List<AnalyzeIp> ipObjs) throws Exception {
		if (CollectionUtils.isEmpty(ipObjs)) return true;
		
		for (AnalyzeIp analyzeIp : ipObjs) {
			reportMapper.insertAnalyzeIp(analyzeIp);
		}
		
		return true;
	}

	@Override
	public List<String> queryAnalyzeIps(String appKey, String startTime,
			String endTime) throws Exception {
		List<Map<String, String>> list = reportMapper.queryAnalyzeIps(appKey, startTime, endTime);
		List<String> ips = null;
		if (CollectionUtils.isNotEmpty(list)) {
			ips = new ArrayList<String>();
			for (Map<String, String> map : list) {
				ips.add(map.get("ip"));
			}
		}
		return ips;
	}
	
	@Override
	public void insertMonitorInvalidIp(MonitorInvalidIp invalidIp)
			throws Exception {
		reportMapper.insertMonitorInvalidIp(invalidIp);
	}

	@Override
	public PageFinder<MonitorInvalidIp> queryMonitorInvalidIps(String ip,
			Integer doubtType, String startTime, String endTime, Query query) {
		List<MonitorInvalidIp> list = reportMapper.queryMonitorInvalidIps(ip, doubtType, startTime, endTime, new RowBounds(query.getOffset(), query.getPageSize()));
		Integer count = reportMapper.queryMonitorInvalidIpCount(ip, doubtType, startTime, endTime);
		PageFinder<MonitorInvalidIp> pageFinder = new PageFinder<MonitorInvalidIp>(query.getPage(), query.getPageSize(), count, list);
		return pageFinder;
	}

	@Override
	public List<AnalyzeInterfaceDay> queryApiIntegerGeneral(String startTime,
			String endTime) throws Exception {
		return reportMapper.queryApiIntegerGeneral(startTime, endTime);
	}

	@Override
	public List<AnalyzeAppkeyDay> queryAppKeyGeneral(String startTime,
			String endTime) throws Exception {
		return reportMapper.queryAppKeyGeneral(startTime, endTime);
	}

	@Override
	public List<AnalyzeDetail> queryAnalyzeDetailByApi(String apiId, List<String> appKeys, 
			String startTime, String endTime) throws Exception {
		return reportMapper.queryAnalyzeDetailByApi(apiId, appKeys, startTime, endTime);
	}

	@Override
	public List<AnalyzeDetail> queryAnalyzeDetailByAppKey(String appKey, String apiId,
			String startTime, String endTime) throws Exception {
		return reportMapper.queryAnalyzeDetailByAppKey(appKey, apiId, startTime, endTime);
	}

	@Override
	public List<AnalyzeInterface> queryTrendAnalysis(String startTime, String endTime, Integer sign) throws Exception {
		return reportMapper.queryTrendAnalysis(startTime, endTime, sign);
	}

	@Override
	public List<AnalyzeInterface> queryTrendAnalysisByApi(String apiId, List<String> appKeys, String startTime, String endTime, Integer sign) throws Exception {
		return reportMapper.queryTrendAnalysisByApi(apiId, appKeys, startTime, endTime, sign);
	}

	@Override
	public List<AnalyzeInterface> queryTrendAnalysisByAppKey(String appKey, String apiId, 
			String startTime, String endTime, Integer sign) throws Exception {
		return reportMapper.queryTrendAnalysisByAppKey(appKey, apiId, startTime, endTime, sign);
	}

	@Override
	public List<AnalyzeAppkey> summary2AppKeyForHoursJob(String startTime,
			String endTime) throws Exception {
		return reportMapper.summary2AppKeyForHoursJob(startTime, endTime);
	}

	@Override
	public List<AnalyzeInterface> summary2ApiForHoursJob(String startTime,
			String endTime) throws Exception {
		return reportMapper.summary2ApiForHoursJob(startTime, endTime);
	}

	@Override
	public List<AnalyzeAppkeyDay> summary2AppKeyForDaysJob(String startTime,
			String endTime) throws Exception {
		return reportMapper.summary2AppKeyForDaysJob(startTime, endTime);
	}

	@Override
	public List<AnalyzeInterfaceDay> queryApiIntegerGeneralForToday()
			throws Exception {
		return reportMapper.queryApiIntegerGeneralForToday();
	}

	@Override
	public List<AnalyzeAppkeyDay> queryAppKeyGeneralForToday() throws Exception {
		return reportMapper.queryAppKeyGeneralForToday();
	}

	@Override
	public List<AnalyzeInterface> queryTrendAnalysisForToday(Integer sign)
			throws Exception {
		return reportMapper.queryTrendAnalysisForToday(sign);
	}



}
