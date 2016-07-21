package com.yougou.yop.api;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MemberTestStart extends AbstractTransactionalJUnit4SpringContextTests {
    @Test
    public void run() {
        System.out.println("Test Started...");
        try {
            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


