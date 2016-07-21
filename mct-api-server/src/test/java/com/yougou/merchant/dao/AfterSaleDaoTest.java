package com.yougou.merchant.dao;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.dto.input.ReturnQualityQueryInputDto;
import com.yougou.dto.output.ReturnQADetail;
import com.yougou.merchant.api.asm.dao.AfterSaleMapper;
import com.yougou.merchant.api.supplier.vo.SupplierVo;

/**
 * 
 * @ClassName: ContactsDaoTest
 * @author huang.tao
 * @date 2013-12-16 下午6:03:41
 * 
 */
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class AfterSaleDaoTest extends AbstractTransactionalJUnit4SpringContextTests {


}
