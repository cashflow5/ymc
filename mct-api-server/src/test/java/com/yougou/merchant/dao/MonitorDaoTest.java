package com.yougou.merchant.dao;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.common.UUIDGenerator;
import com.yougou.merchant.api.monitor.dao.MonitorMapper;
import com.yougou.merchant.api.monitor.vo.MonitorAppKeyTemplate;
import com.yougou.merchant.api.monitor.vo.MonitorConfig;
import com.yougou.merchant.api.monitor.vo.MonitorIpBlackList;
import com.yougou.merchant.api.monitor.vo.MonitorLock;
import com.yougou.merchant.api.monitor.vo.MonitorTemplate;
import com.yougou.merchant.api.monitor.vo.MonitorTemplateDetail;


/**
 * 
 * @ClassName: ContactsDaoTest
 * @author huang.tao
 * @date 2013-12-16 下午6:03:41
 * 
 */
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MonitorDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Resource
    private MonitorMapper monitorMapper;

    @Test
    public void insertMonitorConfig() {
        MonitorConfig vo = new MonitorConfig();
        vo.setId(UUIDGenerator.getUUID());
        vo.setConfigName("Test config Name");
        vo.setConfigKey("test.config.key");
        vo.setConfigValue("test.value");
        vo.setCreateTime(new Date());
        vo.setUpdateTime(new Date());
        vo.setDeleteFlag("1");
        vo.setRemark("remark");

        monitorMapper.insertMonitorConfig(vo);
    }

    @Test
    public void updateMonitorConfig() {
        MonitorConfig vo = new MonitorConfig();
        vo.setId("d49a9164a2a34fe098dc4564533ca12c");
        vo.setConfigName("_Test config Name");
        vo.setConfigKey("test.config.key");
        vo.setConfigValue("_test.value");
        // vo.setCreateTime(new Date());
        vo.setUpdateTime(new Date());
        vo.setDeleteFlag("1");
        vo.setRemark("_remark");

        monitorMapper.updateMonitorConfig(vo);
    }

    @Test
    public void getMonitorConfigById() throws ParseException {
        MonitorConfig config = monitorMapper.getMonitorConfigById("d49a9164a2a34fe098dc4564533ca12c");
        assertTrue(null != config);
    }

    @Test
    public void getMonitorConfigByKey() {
        MonitorConfig config = monitorMapper.getMonitorConfigByKey("test.config.key");
        assertTrue(null != config);
    }

    @Test
    public void queryMonitorConfigList() {
        List<MonitorConfig> list = monitorMapper.queryMonitorConfigList();
        assertTrue(CollectionUtils.isNotEmpty(list));
    }

    // =============================================================================
    // ============================== 监控模板
    // ======================================
    // =============================================================================
    @Test
    public void insertTemplate() {
        MonitorTemplate template = new MonitorTemplate();
        template.setId(UUIDGenerator.getUUID());
        template.setCreateTime(new Date());
        template.setUpdateTime(new Date());
        template.setIsDefault(NumberUtils.INTEGER_ONE);
        template.setReferenceType(1);
        template.setTemplateDesc("默认模板");
        template.setTemplateName("默认模板");
        template.setTemplateNo("API002");

        monitorMapper.insertTemplate(template);
    }

    @Test
    public void updateTemplate() {
        MonitorTemplate template = new MonitorTemplate();
        template.setId("123d7d9c0f4642e095b3b475cf835f21");
        template.setCreateTime(new Date());
        template.setUpdateTime(new Date());
        template.setIsDefault(NumberUtils.INTEGER_ONE);
        template.setReferenceType(1);
        template.setTemplateDesc("_默认模板");
        template.setTemplateName("_默认模板");
        template.setTemplateNo("API002");

        monitorMapper.updateTemplate(template);
    }

    @Test
    public void queryMonitorTemplate() {
        MonitorTemplate template = new MonitorTemplate();

        List<MonitorTemplate> list = monitorMapper.queryMonitorTemplate(template, new RowBounds());
        CollectionUtils.isNotEmpty(list);
    }

    @Test
    public void queryMonitorTemplateCount() {
        MonitorTemplate template = new MonitorTemplate();
        Integer count = monitorMapper.queryMonitorTemplateCount(template);
        assertTrue(count > 0);
    }

    @Test
    public void insertTemplateDetail() {
        MonitorTemplateDetail detail = new MonitorTemplateDetail();
        detail.setId(UUIDGenerator.getUUID());
        detail.setApiId("ee501711c4f511e19c4f5cf3fc0c2d70");
        detail.setCallNum(100000);
        detail.setCreateTime(new Date());
        detail.setFrequency(100);
        detail.setFrequencyUnit(2);
        detail.setTemplateNo("API002");
        detail.setUpdateTime(new Date());
        detail.setIsFrequency(1);
        detail.setIsCallNum(1);

        monitorMapper.insertTemplateDetail(detail);
    }

    @Test
    public void updateTemplateDetail() {
        MonitorTemplateDetail detail = new MonitorTemplateDetail();
        detail.setId("378e03b9837b44ddb7ec149b709d161d");
        detail.setApiId("ee501711c4f511e19c4f5cf3fc0c2d70");
        detail.setCallNum(100002);
        // detail.setCreateTime(new Date());
        detail.setFrequency(102);
        detail.setFrequencyUnit(3);
        detail.setTemplateNo("API002");
        detail.setUpdateTime(new Date());
        detail.setIsFrequency(0);
        detail.setIsCallNum(1);

        monitorMapper.updateTemplateDetail(detail);
    }

    @Test
    public void insertAppKeyTemplate() {
        MonitorAppKeyTemplate appKeyTemplate = new MonitorAppKeyTemplate();
        appKeyTemplate.setId(UUIDGenerator.getUUID());
        appKeyTemplate.setAppkeyId("8a809e234100eb9f01410153f08e0004");
        appKeyTemplate.setCreateTime(new Date());
        appKeyTemplate.setUpdateTime(new Date());
        appKeyTemplate.setTemplateNo("API002");

        monitorMapper.insertAppKeyTemplate(appKeyTemplate);
    }

    @Test
    public void updateAppKeyTemplate() {
        MonitorAppKeyTemplate appKeyTemplate = new MonitorAppKeyTemplate();
        appKeyTemplate.setId("72b1e828fbb94283b69aba57f75d6771");
        appKeyTemplate.setAppkeyId("8a80940b41de54ea0141dec89ad80002");
        // appKeyTemplate.setCreateTime(new Date());
        appKeyTemplate.setUpdateTime(new Date());
        appKeyTemplate.setTemplateNo("API002");

        monitorMapper.updateAppKeyTemplate(appKeyTemplate);
    }

    @Test
    public void queryTemplateDetailList() {
        List<MonitorTemplateDetail> list = monitorMapper.queryTemplateDetailList("API001");

        assertTrue(CollectionUtils.isNotEmpty(list));
    }

    @Test
    public void insertIpBlackList() {
        MonitorIpBlackList vo = new MonitorIpBlackList();
        vo.setId(UUIDGenerator.getUUID());
        vo.setDeleteFag(1);
        vo.setCreateTime(new Date());
        vo.setUpdateTime(new Date());
        vo.setIp("10.0.30.164");
        vo.setOperator("huangtao");
        vo.setReason("sgdfgdfg");

        monitorMapper.insertIpBlackList(vo);
    }

    @Test
    public void updateIpBlackList() {
        MonitorIpBlackList vo = new MonitorIpBlackList();
        vo.setId("86a0e52f15ef4919aae363820ed0650f");
        vo.setDeleteFag(0);
        // vo.setCreateTime(new Date());
        vo.setUpdateTime(new Date());
        vo.setIp("10.0.30.164");
        vo.setOperator("_huangtao");
        vo.setReason("sgdfgdfg_");

        monitorMapper.updateIpBlackList(vo);
    }

    @Test
    public void queryMonitorIpBlackList() {
        List<MonitorIpBlackList> list = monitorMapper.queryMonitorIpBlackList("", null, null, new RowBounds());

        Assert.assertTrue(CollectionUtils.isNotEmpty(list));
    }

    @Test
    public void insertMonitorLock() {
        MonitorLock vo = new MonitorLock();
        vo.setId(UUIDGenerator.getUUID());
        vo.setApiId("ee5012a1c4f511e19c4f5cf3fc0c2d70");
        vo.setAppKey("48ba2d35_13c808dcfeb__7eeb");
        vo.setLockHour(8);
        vo.setLockStatus(1);
        vo.setLockTime(new Date());
        vo.setLockType(1);
        vo.setUnlockTime(new Date());
        vo.setUpdateTime(new Date());

        monitorMapper.insertMonitorLock(vo);
    }

    @Test
    public void updateMonitorLock() {
        MonitorLock vo = new MonitorLock();
        vo.setId("aff223218cea40c589cd82a196b61ae5");
        // vo.setApiId("ee5012a1c4f511e19c4f5cf3fc0c2d70");
        // vo.setAppKey("308c6608_13de3f418e5__7fef");
        // vo.setLockHour(8);
        vo.setLockStatus(0);
        // vo.setLockTime(new Date());
        vo.setLockType(0);
        vo.setUnlockTime(new Date());
        vo.setUpdateTime(new Date());

        monitorMapper.updateMonitorLock(vo);
    }

    @Test
    public void queryMonitorLockByObj() {
        MonitorLock lock = new MonitorLock();
        List<MonitorLock> list = monitorMapper.queryMonitorLockByObj(lock, new RowBounds());

        Assert.assertTrue(CollectionUtils.isNotEmpty(list));
    }

    @Test
    public void queryTemplateList() {

        List<MonitorTemplate> list = monitorMapper.queryTemplateList();

        Assert.assertTrue(CollectionUtils.isNotEmpty(list));
    }  
    
    @Test
    public void queryTemplateDetailPage() {
       Query query=new Query();
       List<MonitorTemplateDetail> list = monitorMapper.queryTemplateDetailPage("APIS00020", new RowBounds(query.getOffset(), query.getPageSize()));
       Integer count = monitorMapper.queryTemplateDetailCount("APIS00020");
   }
    
    @Test
    public void queryMonitorTemplateByNoORName() {
       Query query=new Query();
       MonitorTemplate template =new MonitorTemplate();
       template.setTemplateName("%模板%");
       template.setTemplateNo("%模板%");
       List<MonitorTemplate> list = monitorMapper.queryMonitorTemplateByNoORName(template, new RowBounds(query.getOffset(), query.getPageSize()));
       Integer count = monitorMapper.queryMonitorTemplateByNoORNameCount(template);
       Assert.assertTrue(CollectionUtils.isNotEmpty(list));
       Assert.assertTrue(count>0);
   }
    
    
    @Test
    public void queryAppKeyIdByAppKey() {
       String id= monitorMapper.queryAppKeyIdByAppKey("SP20120517996498");
        Assert.assertTrue(StringUtils.isNotEmpty(id));
    }
}
