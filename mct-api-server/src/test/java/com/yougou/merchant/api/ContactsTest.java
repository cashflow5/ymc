package com.yougou.merchant.api;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.base.JUnitServiceBase;
import com.yougou.merchant.api.supplier.service.IContactApi;
import com.yougou.merchant.api.supplier.vo.ContactsVo;

//@ContextConfiguration(locations = { "classpath*:applicationContext*.xml" })
//public class ContactsTest extends AbstractTransactionalJUnit4SpringContextTests {
public class ContactsTest extends JUnitServiceBase{
    @Resource
    private IContactApi contactApi;

    @Test
    public void insertContact() {
    	System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbb");
    }
    
}
