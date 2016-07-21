/**
 * 
 */
package com.yougou.merchant.api.monitor.service;

import java.util.List;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.monitor.vo.MonitorAppKeyTemplate;
import com.yougou.merchant.api.monitor.vo.MonitorAppkeyWarnDetail;
import com.yougou.merchant.api.monitor.vo.MonitorConfig;
import com.yougou.merchant.api.monitor.vo.MonitorDayWarnDetail;
import com.yougou.merchant.api.monitor.vo.MonitorEarlyWarnQueryVo;
import com.yougou.merchant.api.monitor.vo.MonitorEarlyWarning;
import com.yougou.merchant.api.monitor.vo.MonitorIpBlackList;
import com.yougou.merchant.api.monitor.vo.MonitorLock;
import com.yougou.merchant.api.monitor.vo.MonitorRateWarnDetail;
import com.yougou.merchant.api.monitor.vo.MonitorSuccRateWarnDetail;
import com.yougou.merchant.api.monitor.vo.MonitorTemplate;
import com.yougou.merchant.api.monitor.vo.MonitorTemplateDetail;

/**
 * API监控服务
 * 
 * @author huang.tao
 *
 */
public interface IApiMonitorService {
	//API监控参数配置项
	List<MonitorConfig> queryMonitorConfigList() throws Exception;
	boolean updateMonitorConfig(List<MonitorConfig> configs) throws Exception;
	
	/**
	 * 保存监控模板 <br/> 
	 * <b>
	 * 	id为空则是新增、否则为update
	 * <b>
	 * @param template
	 * @return
	 * @throws Exception
	 */
	boolean saveMonitorTemplate(MonitorTemplate template) throws Exception;
	
	   /**
     * 保存自定义的监控模板 <br/> 
     * <b>
     *  id不为空，更新还是插入取决于数据库是否已经有该条数据了
     * <b>
     * @param template
     * @return
     * @throws Exception
     */
    boolean saveAppKeyTemplate(MonitorTemplate template) throws Exception;
	
	   /**
     * 删除监控模板 <br/> 
     * @param template
     * @return
     * @throws Exception
     */
    boolean deleteMonitorTemplateByTemplateNo(String templateNo) throws Exception;
    //删除监控模板明细
    boolean deleteMonitorTemplateDetailByTemplateNo(String templateNo) throws Exception;
    boolean deleteMonitorTemplateDetailById(String id) throws Exception;
	//通过编码获取监控template
	MonitorTemplate getMonitorTemplateByNo(String templateNo) throws Exception;
	/**
	 * 查询模板列表
	 * 
	 * @param template
	 * @param query
	 * @return
	 * @throws Exception
	 */
	PageFinder<MonitorTemplate> queryMonitorTemplateList(MonitorTemplate template, Query query) throws Exception;
	
	   /**
     * 查询模板详细列表
     * 
     * @param templateNo
     * @param query
     * @return
     * @throws Exception
     */
    PageFinder<MonitorTemplateDetail> queryTemplateDetailList(String templateNo, Query query) throws Exception;
	
	/**
	 * API锁定
	 * @param lock
	 * @throws Exception
	 */
	void insertMonitorLock(MonitorLock lock) throws Exception;
	boolean updateMonitorLock(List<MonitorLock> locks) throws Exception;
	PageFinder<MonitorLock> queryMonitorLockByObj(MonitorLock lock, Query query) throws Exception;
	
	/**
	 * IP黑名单
	 * 
	 * @param ips
	 * @return
	 * @throws Exception
	 */
	boolean insertIpBlackLists(List<MonitorIpBlackList> ips) throws Exception;
	void updateIpBlackList(MonitorIpBlackList ipBlack) throws Exception; 
	PageFinder<MonitorIpBlackList> queryMonitorIpBlacklist(String ip, String startTime, String endTime, Query query) throws Exception;

    /**
     * 查询模板列表
     * 
     * @return
     * @throws Exception
     */
    List<MonitorTemplate> queryTemplateList() throws Exception;
    
    /**
     * 保存APPKEY与模板之间的关系
     * 
     * @param template
     * @return
     * @throws Exception
     */
    boolean updateMonitorAppKeyTemplateByAppkeyId(MonitorAppKeyTemplate template) throws Exception;
    
    //===============================Api监控预警======================
    
    /**
     * Api监控预警
     */
	boolean insertMonitorEarlyWarning(List<MonitorEarlyWarning> warnVos) throws Exception;
	
	/**
	 * 查询预警列表
	 * @param appKey
	 * @param startTime
	 * @param endTime
	 * @param row 
	 * @return
	 */
	PageFinder<MonitorEarlyWarning> queryMonitorEarlyWarning(MonitorEarlyWarnQueryVo queryVo, Query query) throws Exception;
	/**
	 * 查询AppKey日调用次数预警明细列表
	 * @param queryVo
	 * @return
	 */
	List<MonitorAppkeyWarnDetail> queryAppKeyEarlyWarningDetail(MonitorEarlyWarnQueryVo queryVo) throws Exception;
	/**
	 * 查询Api日调用次数预警明细列表
	 * @param queryVo
	 * @return
	 */
	List<MonitorDayWarnDetail> queryApiEarlyWarningDetail(MonitorEarlyWarnQueryVo queryVo) throws Exception;
	/**
	 * 查询单接口调用成功率预警明细列表
	 * @param queryVo
	 * @return
	 */
	List<MonitorSuccRateWarnDetail> querySuccRateWarnDetail(MonitorEarlyWarnQueryVo queryVo) throws Exception;
	
	/**
	 * 查询单接口调用频率预警明细列表
	 * @param queryVo
	 * @return
	 */
	List<MonitorRateWarnDetail> queryRateWarnDetail(MonitorEarlyWarnQueryVo queryVo) throws Exception;
	
	/**
	 * 查询指定时间段内 所以appKey 单接口的实际频率
	 * @param 
	 * @return
	 */
	List<MonitorRateWarnDetail> queryApiFrequencyBynow(MonitorEarlyWarnQueryVo queryVo) throws Exception;
		
	/**
     * 按照appkeyId查询该appkey对应的模板
     * @param MonitorTemplate
     * @return
     */
	List<MonitorTemplateDetail> getMonitorTemplateListByAppKeyId(String appkeyId);
	
    /**
     * 按照appkey查询appkeyId
     * 
     * @param appKey
     * @return
     */
	String queryAppKeyIdByAppKey(String appKey);
	
	/**
     * 查询所有已配置的appkeyId和templateNo关系的数据
     * @return List
     */
	List<MonitorAppKeyTemplate> queryAppKeyTemplate();
	
	/**
     * 查询默认的模板信息
     * @return List
     */
	List<String> queryDefaultTemplate();
	
	/**
     * 根据模板号，查询该模板下的api接口配置明细
     * @return List
     */
	List<MonitorTemplateDetail> queryTemplateDetailList(String templateNo);
}
