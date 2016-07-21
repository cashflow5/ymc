/**
 * 
 */
package com.yougou.merchant.api.monitor.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.merchant.api.monitor.vo.MonitorAppkeyWarnDetail;
import com.yougou.merchant.api.monitor.vo.MonitorDayWarnDetail;
import com.yougou.merchant.api.monitor.vo.MonitorEarlyWarnQueryVo;
import com.yougou.merchant.api.monitor.vo.MonitorEarlyWarning;
import com.yougou.merchant.api.monitor.vo.MonitorRateWarnDetail;
import com.yougou.merchant.api.monitor.vo.MonitorSuccRateWarnDetail;

/**
 * 
 * 监控预警 相关数据持久层操作
 * 
 * @author huang.tao
 *
 */
public interface WarnMapper {
	/**
	 * 保存Api监控预警信息
	 * @param vo
	 */
	void insertMonitorEarlyWarning(MonitorEarlyWarning vo);
	/**
	 * 保存AppKey日调用次数预警明细
	 * @param vo
	 */
	void insertMonitorAppkeyWarnDetail(MonitorAppkeyWarnDetail vo);
	/**
	 * 保存Api日调用次数预警明细
	 * @param vo
	 */
	void insertMonitorDayWarnDetail(MonitorDayWarnDetail vo);
	/**
	 * 保存单接口调用成功率预警明细
	 * @param vo
	 */
	void insertMonitorSuccRateWarnDetail(MonitorSuccRateWarnDetail vo);
	/**
	 * 单接口调用频率预警明细
	 * @param vo
	 */
	void insertMonitorRateWarnDetail(MonitorRateWarnDetail vo);
	
	/**
	 * 查询预警列表
	 * @param appKey
	 * @param startTime
	 * @param endTime
	 * @param row 
	 * @return
	 */
	List<MonitorEarlyWarning> queryMonitorEarlyWarning(MonitorEarlyWarnQueryVo queryVo, RowBounds row);
	Integer queryMonitorEarlyWarningCount(MonitorEarlyWarnQueryVo queryVo);
	/**
	 * 查询AppKey日调用次数预警明细列表
	 * @param queryVo
	 * @return
	 */
	List<MonitorAppkeyWarnDetail> queryAppKeyEarlyWarningDetail(MonitorEarlyWarnQueryVo queryVo);
	/**
	 * 查询Api日调用次数预警明细列表
	 * @param queryVo
	 * @return
	 */
	List<MonitorDayWarnDetail> queryApiEarlyWarningDetail(MonitorEarlyWarnQueryVo queryVo);
	/**
	 * 查询单接口调用成功率预警明细列表
	 * @param queryVo
	 * @return
	 */
	List<MonitorSuccRateWarnDetail> querySuccRateWarnDetail(MonitorEarlyWarnQueryVo queryVo);
	
	/**
	 * 查询单接口调用频率预警明细列表
	 * @param queryVo
	 * @return
	 */
	List<MonitorRateWarnDetail> queryRateWarnDetail(MonitorEarlyWarnQueryVo queryVo);
	
	/**
	 * 查询指定时间段内 所以appKey 单接口的实际频率
	 * @param 
	 * @return
	 */
	List<MonitorRateWarnDetail> queryApiFrequencyBynow(MonitorEarlyWarnQueryVo queryVo);
}
