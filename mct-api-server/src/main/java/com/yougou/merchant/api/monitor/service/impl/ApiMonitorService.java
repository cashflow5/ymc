package com.yougou.merchant.api.monitor.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.common.UUIDGenerator;
import com.yougou.merchant.api.monitor.dao.MonitorMapper;
import com.yougou.merchant.api.monitor.dao.WarnMapper;
import com.yougou.merchant.api.monitor.service.IApiMonitorService;
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

@Service(value = "apiMonitorService")
public class ApiMonitorService implements IApiMonitorService {

    @Resource
    private MonitorMapper monitorMapper;
    @Resource
    private WarnMapper warnMapper;

    @Override
    public List<MonitorConfig> queryMonitorConfigList() throws Exception {
        return monitorMapper.queryMonitorConfigList();
    }

    @Override
    @Transactional
    public boolean updateMonitorConfig(List<MonitorConfig> configs) throws Exception {
        if (CollectionUtils.isEmpty(configs))
            return true;

        for (MonitorConfig monitorConfig : configs) {
            // 需要通过Id进行删除
            MonitorConfig _temp = monitorMapper.getMonitorConfigByKey(monitorConfig.getConfigKey());
            if (null == _temp) {
                monitorConfig.setId(UUIDGenerator.getUUID());
                monitorConfig.setCreateTime(new Date());
                monitorMapper.insertMonitorConfig(monitorConfig);
                continue;
            }
            monitorConfig.setId(_temp.getId());
            monitorConfig.setUpdateTime(new Date());

            monitorMapper.updateMonitorConfig(monitorConfig);
        }

        return true;
    }

    @Override
    public MonitorTemplate getMonitorTemplateByNo(String templateNo) throws Exception {
        if (StringUtils.isBlank(templateNo))
            return null;

        MonitorTemplate _template = new MonitorTemplate();
        _template.setTemplateNo(templateNo);
        List<MonitorTemplate> _list = monitorMapper.queryMonitorTemplate(_template, new RowBounds());
        if (CollectionUtils.isNotEmpty(_list)) {
            MonitorTemplate obj = _list.get(0);
            List<MonitorTemplateDetail> details = monitorMapper.queryTemplateDetailList(templateNo);
            obj.setTemplateDetails(details);

            return obj;
        }

        return null;
    }

    @Override
    @Transactional
    public boolean saveMonitorTemplate(MonitorTemplate template) throws Exception {
        if (null == template)
            return false;

        if (StringUtils.isNotBlank(template.getId())) {
            monitorMapper.updateTemplate(template);
        } else {
            template.setId(UUIDGenerator.getUUID());
            template.setUpdateTime(new Date());
            monitorMapper.insertTemplate(template);
        }

        List<MonitorTemplateDetail> details = template.getTemplateDetails();
        if (CollectionUtils.isNotEmpty(details)) {
            for (MonitorTemplateDetail detail : details) {
                if (StringUtils.isNotBlank(detail.getId())) {
                    monitorMapper.updateTemplateDetail(detail);
                } else {
                    detail.setId(UUIDGenerator.getUUID());
                    detail.setUpdateTime(new Date());
                    monitorMapper.insertTemplateDetail(detail);
                }
            }
        }

        return true;
    }

    @Override
    @Transactional
    public boolean saveAppKeyTemplate(MonitorTemplate template) throws Exception {
        if (null == template)
            return false;
        Integer count = monitorMapper.queryMonitorTemplateCount(template);
        if (count > 0) {
            monitorMapper.updateTemplate(template);
        } else {
            template.setId(template.getId());
            template.setUpdateTime(new Date());
            monitorMapper.insertTemplate(template);
        }

        List<MonitorTemplateDetail> details = template.getTemplateDetails();
        if (CollectionUtils.isNotEmpty(details)) {
            for (MonitorTemplateDetail detail : details) {
                if (StringUtils.isNotBlank(detail.getId())) {
                    monitorMapper.updateTemplateDetail(detail);
                } else {
                    detail.setId(UUIDGenerator.getUUID());
                    detail.setUpdateTime(new Date());
                    monitorMapper.insertTemplateDetail(detail);
                }
            }
        }

        return true;
    }

    public boolean deleteMonitorTemplateByTemplateNo(String templateNo) throws Exception {
        if (StringUtils.isEmpty(templateNo))
            return false;
        return monitorMapper.deleteMonitorTemplateByTemplateNo(templateNo) > 0;
    }

    public boolean deleteMonitorTemplateDetailByTemplateNo(String templateNo) throws Exception {
        if (StringUtils.isEmpty(templateNo))
            return false;
        return monitorMapper.deleteMonitorTemplateDetailByTemplateNo(templateNo) > 0;
    }

    public boolean deleteMonitorTemplateDetailById(String id) throws Exception {
        if (StringUtils.isEmpty(id))
            return false;
        return monitorMapper.deleteMonitorTemplateDetailById(id) > 0;
    }

    @Override
    public PageFinder<MonitorTemplate> queryMonitorTemplateList(MonitorTemplate template, Query query) throws Exception {
        // 根据模板名称和编号模糊查询
        template.setTemplateNo("%" + template.getTemplateName() + "%");
        template.setTemplateName("%" + template.getTemplateName() + "%");
        List<MonitorTemplate> list = monitorMapper.queryMonitorTemplateByNoORName(template, new RowBounds(query.getOffset(), query.getPageSize()));
        Integer count = monitorMapper.queryMonitorTemplateByNoORNameCount(template);
        PageFinder<MonitorTemplate> pageFinder = new PageFinder<MonitorTemplate>(query.getPage(), query.getPageSize(), count, list);
        return pageFinder;
    }

    @Override
    public PageFinder<MonitorTemplateDetail> queryTemplateDetailList(String templateNo, Query query) throws Exception {
        List<MonitorTemplateDetail> list = monitorMapper.queryTemplateDetailPage(templateNo, new RowBounds(query.getOffset(), query.getPageSize()));
        Integer count = monitorMapper.queryTemplateDetailCount(templateNo);
        PageFinder<MonitorTemplateDetail> pageFinder = new PageFinder<MonitorTemplateDetail>(query.getPage(), query.getPageSize(), count, list);
        return pageFinder;
    }

    @Override
    public void insertMonitorLock(MonitorLock lock) throws Exception {
        monitorMapper.insertMonitorLock(lock);
    }

    @Override
    public boolean updateMonitorLock(List<MonitorLock> locks) throws Exception {
        if (CollectionUtils.isEmpty(locks))
            return true;

        for (MonitorLock monitorLock : locks) {
            monitorMapper.updateMonitorLock(monitorLock);
        }

        return true;
    }

    @Override
    public PageFinder<MonitorLock> queryMonitorLockByObj(MonitorLock lock, Query query) throws Exception {
        List<MonitorLock> list = monitorMapper.queryMonitorLockByObj(lock, new RowBounds(query.getOffset(), query.getPageSize()));
        Integer count = monitorMapper.queryMonitorLockCount(lock);
        PageFinder<MonitorLock> pageFinder = new PageFinder<MonitorLock>(query.getPage(), query.getPageSize(), count, list);
        return pageFinder;
    }

    @Override
    @Transactional
    public boolean insertIpBlackLists(List<MonitorIpBlackList> ips) throws Exception {
        if (CollectionUtils.isEmpty(ips))
            return true;

        for (MonitorIpBlackList monitorIpBlackList : ips) {
            monitorMapper.insertIpBlackList(monitorIpBlackList);
        }
        return true;
    }

    @Override
    public void updateIpBlackList(MonitorIpBlackList ipBlack) throws Exception {
        monitorMapper.updateIpBlackList(ipBlack);
    }

    @Override
    public PageFinder<MonitorIpBlackList> queryMonitorIpBlacklist(String ip, String startTime, String endTime, Query query) throws Exception {
        List<MonitorIpBlackList> list = monitorMapper.queryMonitorIpBlackList(ip, startTime, endTime, new RowBounds(query.getOffset(), query.getPageSize()));
        Integer count = monitorMapper.queryMonitorIpBlackListCount(ip, startTime, endTime);
        PageFinder<MonitorIpBlackList> pageFinder = new PageFinder<MonitorIpBlackList>(query.getPage(), query.getPageSize(), count, list);
        return pageFinder;
    }

    public List<MonitorTemplate> queryTemplateList() {
        return monitorMapper.queryTemplateList();
    }

    public boolean updateMonitorAppKeyTemplateByAppkeyId(MonitorAppKeyTemplate template) throws Exception {
        Integer count = monitorMapper.queryAppKeyTemplateByAppKey(template.getAppkeyId());
        if (count > 0) {
            template.setUpdateTime(new Date());
            monitorMapper.updateAppKeyTemplateByAppKey(template);
        } else {
            template.setId(UUIDGenerator.getUUID());
            template.setCreateTime(new Date());
            template.setUpdateTime(new Date());
            monitorMapper.insertAppKeyTemplate(template);
        }

        return true;
    }

    @Override
    @Transactional
    public boolean insertMonitorEarlyWarning(List<MonitorEarlyWarning> warnVos) throws Exception {
        if (CollectionUtils.isEmpty(warnVos)) {
            return true;
        }
        for (MonitorEarlyWarning warn : warnVos) {
            warnMapper.insertMonitorEarlyWarning(warn);

            if (warn.getAppKeyDetail() != null) {
                warnMapper.insertMonitorAppkeyWarnDetail(warn.getAppKeyDetail());
            }
            if (CollectionUtils.isNotEmpty(warn.getDayDetails())) {
                for (MonitorDayWarnDetail daydetail : warn.getDayDetails()) {
                    warnMapper.insertMonitorDayWarnDetail(daydetail);
                }
            }
            if (CollectionUtils.isNotEmpty(warn.getSuccRateDetails())) {
                for (MonitorSuccRateWarnDetail succRatedetail : warn.getSuccRateDetails()) {
                    warnMapper.insertMonitorSuccRateWarnDetail(succRatedetail);
                }
            }
            if (CollectionUtils.isNotEmpty(warn.getRateWarnDetails())) {
                for (MonitorRateWarnDetail RateWarndetail : warn.getRateWarnDetails()) {
                    warnMapper.insertMonitorRateWarnDetail(RateWarndetail);
                }
            }
        }

        return true;
    }

    @Override
    public PageFinder<MonitorEarlyWarning> queryMonitorEarlyWarning(MonitorEarlyWarnQueryVo queryVo, Query query) throws Exception {
        List<MonitorEarlyWarning> list = warnMapper.queryMonitorEarlyWarning(queryVo, new RowBounds(query.getOffset(), query.getPageSize()));
        Integer count = warnMapper.queryMonitorEarlyWarningCount(queryVo);
        PageFinder<MonitorEarlyWarning> pageFinder = new PageFinder<MonitorEarlyWarning>(query.getPage(), query.getPageSize(), count, list);
        return pageFinder;
    }

    @Override
    public List<MonitorAppkeyWarnDetail> queryAppKeyEarlyWarningDetail(MonitorEarlyWarnQueryVo queryVo) throws Exception {
        return warnMapper.queryAppKeyEarlyWarningDetail(queryVo);
    }

    @Override
    public List<MonitorDayWarnDetail> queryApiEarlyWarningDetail(MonitorEarlyWarnQueryVo queryVo) throws Exception {
        return warnMapper.queryApiEarlyWarningDetail(queryVo);
    }

    @Override
    public List<MonitorSuccRateWarnDetail> querySuccRateWarnDetail(MonitorEarlyWarnQueryVo queryVo) throws Exception {
        return warnMapper.querySuccRateWarnDetail(queryVo);
    }

    @Override
    public List<MonitorRateWarnDetail> queryRateWarnDetail(MonitorEarlyWarnQueryVo queryVo) throws Exception {
        return warnMapper.queryRateWarnDetail(queryVo);
    }

    /**
     * 按照appkeyId查询改appkey对应的模板
     * @param MonitorTemplate
     * @return
     */
    public List<MonitorTemplateDetail> getMonitorTemplateListByAppKeyId(String appkeyId) {
        String template_no = "";
        if (monitorMapper.queryAppKeyTemplateByAppKey(appkeyId) > 0) {
            template_no = monitorMapper.queryAppKeyTemplateByAppKeyId(appkeyId);
        } else {
            template_no = monitorMapper.queryDefaultTemplate().get(0);
        }
        return monitorMapper.queryTemplateDetailList(template_no);
    }

    /**
     * 按照appkey查询appkeyId
     * 
     * @param appKey
     * @return
     */
    public String queryAppKeyIdByAppKey(String appKey) {
        return monitorMapper.queryAppKeyIdByAppKey(appKey);
    }

    /**
     * 查询所有已配置的appkeyId和templateNo关系的数据
     * 
     * @return List
     */
    public List<MonitorAppKeyTemplate> queryAppKeyTemplate() {
        return monitorMapper.queryAppKeyTemplate();
    }

    /**
     * 查询默认的模板信息
     * 
     * @return List
     */
    public List<String> queryDefaultTemplate() {
        return monitorMapper.queryDefaultTemplate();
    }

    /**
     * 根据模板号，查询该模板下的api接口配置明细
     * 
     * @return List
     */
    public List<MonitorTemplateDetail> queryTemplateDetailList(String templateNo) {
        return monitorMapper.queryTemplateDetailList(templateNo);
    }
    
	@Override
	public List<MonitorRateWarnDetail> queryApiFrequencyBynow(
			MonitorEarlyWarnQueryVo queryVo) throws Exception {
		return warnMapper.queryApiFrequencyBynow(queryVo);
	}
}
