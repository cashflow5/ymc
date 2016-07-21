/**
 * 
 */
package com.yougou.merchant.api.monitor.service;

import java.util.List;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.monitor.vo.AnalyzeAppkey;
import com.yougou.merchant.api.monitor.vo.AnalyzeAppkeyDay;
import com.yougou.merchant.api.monitor.vo.AnalyzeDetail;
import com.yougou.merchant.api.monitor.vo.AnalyzeInterface;
import com.yougou.merchant.api.monitor.vo.AnalyzeInterfaceDay;
import com.yougou.merchant.api.monitor.vo.AnalyzeIp;
import com.yougou.merchant.api.monitor.vo.MonitorInvalidIp;

/**
 * API报表服务
 * 
 * @author huang.tao
 *
 */
public interface IApiReportService {
	
	/**
	 * 增加统计明细信息
	 * 
	 * @param detail
	 */
	boolean insertAnalyzeDetailBatch(List<AnalyzeDetail> details) throws Exception;
	
	/**
	 * 增加按AppKey统计汇总（小时维度）
	 * 
	 * @return
	 * @throws Exception
	 */
	boolean insertAnalyzeAppkeyBatch(List<AnalyzeAppkey> appkeys) throws Exception;
	
	/**
	 * 增加按AppKey统计汇总（天维度）
	 * 
	 * @param appkeyDay
	 */
	boolean insertAnalyzeAppkeyDayBatch(List<AnalyzeAppkeyDay> appkeyDays) throws Exception;
	
	/**
	 * 增加按API接口统计汇总（小时维度）
	 * 
	 * @return
	 * @throws Exception
	 */
	boolean insertAnalyzeInterfaceBatch(List<AnalyzeInterface> interfaces) throws Exception;
	
	/**
	 * 增加按API接口统计汇总（天维度）
	 * 
	 * @param interfaceDay
	 */
	@Deprecated
	boolean insertAnalyzeInterfaceDayBatch(List<AnalyzeInterfaceDay> interfaceDays) throws Exception;
	
	/**
	 * appKey Ip连接数
	 */
	boolean insertAnalyzeIpBatch(List<AnalyzeIp> ipObjs) throws Exception;
	/**
	 * 获取一段时间内的IP列表
	 * 
	 * @param appKey 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	List<String> queryAnalyzeIps(String appKey, String startTime, String endTime) throws Exception;
	
	
	/**
	 * 无效IP请求
	 * 
	 * @param invalidIp
	 * @throws Exception
	 */
	void insertMonitorInvalidIp(MonitorInvalidIp invalidIp) throws Exception;
	PageFinder<MonitorInvalidIp> queryMonitorInvalidIps(String ip, Integer doubtType, String startTime, String endTime, Query query) throws Exception;
	
	/**
	 * API概况
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	List<AnalyzeInterfaceDay> queryApiIntegerGeneral(String startTime, String endTime) throws Exception;
	
	/**
	 * AppKey概况
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<AnalyzeAppkeyDay> queryAppKeyGeneral(String startTime, String endTime) throws Exception;
	
	/**
	 * 查询某个ApiId下各个AppKey的调用情况
	 * 
	 * @param apiId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<AnalyzeDetail> queryAnalyzeDetailByApi(String apiId, List<String> appKeys, String startTime, String endTime) throws Exception;
	
	/**
	 * 查询某个AppKey下各个接口的调用情况
	 * 
	 * @param appKey
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<AnalyzeDetail> queryAnalyzeDetailByAppKey(String appKey, String apiId, String startTime, String endTime) throws Exception;
	
	/**
	 * 趋势分析
	 * 
	 * @param startTime
	 * @param endTime
	 * @param sign 1:按天统计  0:按小时统计
	 * @return
	 * @throws Exception
	 */
	List<AnalyzeInterface> queryTrendAnalysis(String startTime, String endTime, Integer sign) throws Exception;
	
	/**
	 * API接口趋势分析
	 * 
	 * @param apiId
	 * @param startTime
	 * @param endTime
	 * @param sign 1:按天统计  0:按小时统计
	 * @return
	 * @throws Exception
	 */
	List<AnalyzeInterface> queryTrendAnalysisByApi(String apiId, List<String> appKeys, String startTime, String endTime, Integer sign) throws Exception;
	
	/**
	 * AppKey调用趋势分析
	 * 
	 * @param appKey
	 * @param startTime
	 * @param endTime
	 * @param sign 1:按天统计  0:按小时统计
	 * @return
	 * @throws Exception
	 */
	List<AnalyzeInterface> queryTrendAnalysisByAppKey(String appKey, String apiId, String startTime, String endTime, Integer sign) throws Exception;
	
	//定时job
	List<AnalyzeAppkey> summary2AppKeyForHoursJob(String startTime, String endTime) throws Exception;
	
	List<AnalyzeInterface> summary2ApiForHoursJob(String startTime, String endTime) throws Exception;
	
	List<AnalyzeAppkeyDay> summary2AppKeyForDaysJob(String startTime, String endTime) throws Exception;
	
	//当天数据概况
	List<AnalyzeInterfaceDay> queryApiIntegerGeneralForToday() throws Exception;
	List<AnalyzeAppkeyDay> queryAppKeyGeneralForToday() throws Exception;
	List<AnalyzeInterface> queryTrendAnalysisForToday(Integer sign) throws Exception;
}
