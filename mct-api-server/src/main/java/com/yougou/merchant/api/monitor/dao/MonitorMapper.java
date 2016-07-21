/**
 * 
 */
package com.yougou.merchant.api.monitor.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.merchant.api.monitor.vo.MonitorAppKeyTemplate;
import com.yougou.merchant.api.monitor.vo.MonitorConfig;
import com.yougou.merchant.api.monitor.vo.MonitorIpBlackList;
import com.yougou.merchant.api.monitor.vo.MonitorLock;
import com.yougou.merchant.api.monitor.vo.MonitorTemplate;
import com.yougou.merchant.api.monitor.vo.MonitorTemplateDetail;


/**
 * 
 * 监控相关数据持久层映射
 * 
 * @author huang.tao
 *
 */
public interface MonitorMapper {
	/**
	 * 监控配置
	 * 
	 * @param config
	 */
	void insertMonitorConfig(MonitorConfig config);
	void updateMonitorConfig(MonitorConfig config);
	MonitorConfig getMonitorConfigById(String id);
	MonitorConfig getMonitorConfigByKey(String key);
	List<MonitorConfig> queryMonitorConfigList();
	
	/**
	 * 监控模板
	 */
	void insertTemplate(MonitorTemplate template);
	void updateTemplate(MonitorTemplate template);
	List<MonitorTemplate> queryMonitorTemplate(MonitorTemplate template, RowBounds row);
	Integer queryMonitorTemplateCount(MonitorTemplate template);
	List<MonitorTemplate> queryMonitorTemplateByNoORName(MonitorTemplate template, RowBounds row);
	Integer queryMonitorTemplateByNoORNameCount(MonitorTemplate template);

	void insertTemplateDetail(MonitorTemplateDetail detail);
	void updateTemplateDetail(MonitorTemplateDetail detail);
	
	void insertAppKeyTemplate(MonitorAppKeyTemplate appKeyTemplate);
	void updateAppKeyTemplate(MonitorAppKeyTemplate appKeyTemplate);

    Integer queryAppKeyTemplateByAppKey(String appkeyId);
    String queryAppKeyTemplateByAppKeyId(String appkeyId);
    List<String> queryDefaultTemplate();
	void updateAppKeyTemplateByAppKey(MonitorAppKeyTemplate appKeyTemplate);
	    
	List<MonitorTemplateDetail> queryTemplateDetailList(String templateNo);
	List<MonitorTemplateDetail> queryTemplateDetailPage(String templateNo, RowBounds row);
	Integer queryTemplateDetailCount(String templateNo);
	Integer deleteMonitorTemplateByTemplateNo(String templateNo);
	Integer deleteMonitorTemplateDetailByTemplateNo(String templateNo);
	Integer deleteMonitorTemplateDetailById(String id);
	List<MonitorTemplate> queryTemplateList();
	/**
	 * 加入黑名单
	 * 
	 * @param black
	 */
	void insertIpBlackList(MonitorIpBlackList black);
	void updateIpBlackList(MonitorIpBlackList black);
	
	/**
	 * 查询IP黑名单
	 * 
	 * @param ip
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<MonitorIpBlackList> queryMonitorIpBlackList(@Param("ip")String ip, @Param("startTime")String startTime, @Param("endTime")String endTime, RowBounds row);
	Integer queryMonitorIpBlackListCount(@Param("ip")String ip, @Param("startTime")String startTime, @Param("endTime")String endTime);
	
	/**
	 * 锁定的API
	 */
	void insertMonitorLock(MonitorLock lock);
	void updateMonitorLock(MonitorLock lock);
	List<MonitorLock> queryMonitorLockByObj(MonitorLock lock, RowBounds row);
	Integer queryMonitorLockCount(MonitorLock lock);
	String queryAppKeyIdByAppKey(String appKey);
	List<MonitorAppKeyTemplate> queryAppKeyTemplate();
}
