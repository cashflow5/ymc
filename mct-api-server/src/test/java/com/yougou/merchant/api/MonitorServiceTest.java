package com.yougou.merchant.api;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yougou.merchant.api.monitor.service.IApiMonitorService;
import com.yougou.merchant.api.monitor.vo.MonitorAppKeyVo;

@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class MonitorServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Resource
    private IApiMonitorService apiMonitorService;

    @Test
    public void updateRedisCasheForMonitorTemplate() throws Exception {
//        List<String> list=apiMonitorService.updateRedisCasheForMonitorTemplate();
//		assertTrue(CollectionUtils.isNotEmpty(list));
    }

    @Test
    public void queryMonitorAppKeyVo() throws Exception {
//        MonitorAppKeyVo vo=apiMonitorService.queryMonitorAppKeyVo("6242ebb2_1407b95bc46__7fb7");
//        assertTrue(!vo.getMonitorAppKeyDetailVo().isEmpty());
    }

}
