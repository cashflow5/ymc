package com.yougou.merchant.api;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.dto.input.ReturnQualityQueryInputDto;
import com.yougou.dto.output.ReturnQualityQueryOutputDto;
import com.yougou.yop.api.IMerchantApiAsmService;

@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class AfterSaleTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Resource
    private IMerchantApiAsmService afterSaleApi;

 

}
