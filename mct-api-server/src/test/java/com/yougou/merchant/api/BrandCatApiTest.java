package com.yougou.merchant.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.common.UUIDGenerator;
import com.yougou.merchant.api.supplier.service.IBrandCatApi;
import com.yougou.merchant.api.supplier.vo.BrandCatRelation;
import com.yougou.merchant.api.supplier.vo.BrandVo;
import com.yougou.merchant.api.supplier.vo.CatVo;
import com.yougou.merchant.api.supplier.vo.SupplierVo;

@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class BrandCatApiTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Resource
    private IBrandCatApi brandCatApi;
    
    @Test
    public void insertLimitBrand() throws Exception {
    	System.out.println("bbbbbbbbbbbbbbbbbbbbbbbb");
    }

 
    
}
