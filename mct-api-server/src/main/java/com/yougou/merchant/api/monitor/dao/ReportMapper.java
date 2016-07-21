/**
 * 
 */
package com.yougou.merchant.api.monitor.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.merchant.api.monitor.vo.AnalyzeAppkey;
import com.yougou.merchant.api.monitor.vo.AnalyzeAppkeyDay;
import com.yougou.merchant.api.monitor.vo.AnalyzeDetail;
import com.yougou.merchant.api.monitor.vo.AnalyzeInterface;
import com.yougou.merchant.api.monitor.vo.AnalyzeInterfaceDay;
import com.yougou.merchant.api.monitor.vo.AnalyzeIp;
import com.yougou.merchant.api.monitor.vo.MonitorInvalidIp;

/**
 * API监控报表映射
 * 
 * @author huang.tao
 *
 */
public interface ReportMapper {
	
	/**
	 * 增加统计明细信息
	 * 
	 * @param detail
	 */
	void insertAnalyzeDetail(AnalyzeDetail detail);
	
	/**
	 * 增加按AppKey统计汇总（天维度）
	 * 
	 * @param appkeyDay
	 */
	void insertAnalyzeAppkeyDay(AnalyzeAppkeyDay appkeyDay);
	
	/**
	 * 增加按AppKey统计汇总（小时维度）
	 * 
	 * @param appkeyObj
	 */
	void insertAnalyzeAppkey(AnalyzeAppkey appkeyObj);
	
	/**
	 * 增加按API接口统计汇总（天维度）
	 * 
	 * @param interfaceDay
	 */
	@Deprecated
	void insertAnalyzeInterfaceDay(AnalyzeInterfaceDay interfaceDay);
	
	/**
	 * 增加按API接口统计汇总(小时维度)
	 * 
	 * @param interfaced
	 */
	void insertAnalyzeInterface(AnalyzeInterface interfaced);
	
	/**
	 * 增加appkey ip连接数
	 * 
	 * @param ipObj
	 */
	void insertAnalyzeIp(AnalyzeIp ipObj);
	
	/**
	 * 查询时间范围内的ip列表
	 * 
	 * @param appKey
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Map<String, String>> queryAnalyzeIps(@Param("appKey")String appKey, @Param("startTime")String startTime, @Param("endTime")String endTime);
	
	/**
	 * 记录无效Ip请求（按天进行统计）
	 * 
	 * @param invalidIp
	 */
	void insertMonitorInvalidIp(MonitorInvalidIp invalidIp);
	
	List<MonitorInvalidIp> queryMonitorInvalidIps(@Param("ip")String ip, @Param("doubtType")Integer doubtType, @Param("startTime")String startTime, @Param("endTime")String endTime, RowBounds row);
	Integer queryMonitorInvalidIpCount(@Param("ip")String ip, @Param("doubtType")Integer doubtType, @Param("startTime")String startTime, @Param("endTime")String endTime);
	
	/**
	 * API概况
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<AnalyzeInterfaceDay> queryApiIntegerGeneral(@Param("startTime")String startTime, @Param("endTime")String endTime);
	
	/**
	 * AppKey概况
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<AnalyzeAppkeyDay> queryAppKeyGeneral(@Param("startTime")String startTime, @Param("endTime")String endTime);
	
	/**
	 * 查询某个ApiId下各个AppKey的调用情况
	 * 
	 * @param apiId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<AnalyzeDetail> queryAnalyzeDetailByApi(@Param("apiId")String apiId, @Param("appKeys")List<String> appKeys, @Param("startTime")String startTime, @Param("endTime")String endTime);
	
	/**
	 * 查询某个AppKey下各个接口的调用情况
	 * 
	 * @param appKey
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<AnalyzeDetail> queryAnalyzeDetailByAppKey(@Param("appKey")String appKey, @Param("apiId")String apiId, @Param("startTime")String startTime, @Param("endTime")String endTime);
	
	/**
	 * 趋势分析
	 * 
	 * @param startTime
	 * @param endTime
	 * @param sign 1：按天统计  0：按小时统计
	 * @return
	 */
	List<AnalyzeInterface> queryTrendAnalysis(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("sign")Integer sign);
	
	/**
	 * API接口趋势分析
	 * 
	 * @param apiId
	 * @param startTime
	 * @param endTime
	 * @param sign 1:按天统计  0:按小时统计
	 * @return
	 */
	List<AnalyzeInterface> queryTrendAnalysisByApi(@Param("apiId")String apiId, @Param("appKeys")List<String> appKeys, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("sign")Integer sign);
	
	/**
	 * AppKey调用趋势分析
	 * 
	 * @param appKey
	 * @param startTime
	 * @param endTime
	 * @param sign 1:按天统计  0:按小时统计
	 * @return
	 */
	List<AnalyzeInterface> queryTrendAnalysisByAppKey(@Param("appKey")String appKey, @Param("apiId")String apiId, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("sign")Integer sign);
	
	
	
	/******************************************************************/
	/**********************   定时汇总           ********************************/
	/******************************************************************/
	
	List<AnalyzeAppkey> summary2AppKeyForHoursJob(@Param("startTime")String startTime, @Param("endTime")String endTime);
	
	List<AnalyzeInterface> summary2ApiForHoursJob(@Param("startTime")String startTime, @Param("endTime")String endTime);
	
	List<AnalyzeAppkeyDay> summary2AppKeyForDaysJob(@Param("startTime")String startTime, @Param("endTime")String endTime);
	
	/******************************************************************/
	/*******  当天数据需从tbl_merchant_api_analyze_detail表获取      ************/
	/******************************************************************/
	
	List<AnalyzeInterfaceDay> queryApiIntegerGeneralForToday();
	
	List<AnalyzeAppkeyDay> queryAppKeyGeneralForToday();
	
	List<AnalyzeInterface> queryTrendAnalysisForToday(@Param("sign")Integer sign);
}
