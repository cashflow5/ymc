package com.yougou.merchant.api;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.supplier.service.ISupplierCommodityService;
import com.yougou.merchant.api.supplier.service.ISupplierService;
import com.yougou.merchant.api.supplier.vo.CatVo;
import com.yougou.merchant.api.supplier.vo.Commodity;
import com.yougou.merchant.api.supplier.vo.CommodityQueryVo;
import com.yougou.merchant.api.supplier.vo.SupplierVo;

@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class SupplierCommodityTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Resource
    private ISupplierCommodityService commodityService;
    
    @Resource
    private ISupplierService supplierService;
    
    @Test
    public void getBrandNosByMerchantCode() {
        List<String> list = commodityService.getBrandNosByMerchantCode("SP20130821678648");

        assertTrue(CollectionUtils.isNotEmpty(list));
    }

    @Test
    public void getCategoryNosByMerchantCode() {
        List<String> list = commodityService.getCategoryNosByMerchantCode("SP20130821678648");
        assertTrue(CollectionUtils.isNotEmpty(list));
    }

    @Test
    public void getCategoryBySupplyId() {
        List<CatVo> list = commodityService.getCategoryBySupplyId("8a8a8a173b97e026013b98163c3e05fe");
        assertTrue(CollectionUtils.isNotEmpty(list));
    }
    
    @Test
    public void getMerchantByCatNo() {
        List<Map<String, String>> list = commodityService.getMerchantByCatNo("CE");
        assertTrue(CollectionUtils.isNotEmpty(list));
    }
    
    @Test
    public void getSupplierByName() throws Exception {
    	SupplierVo vo = supplierService.getSupplierByName("燕子测试招商专用供应商");
    	
    	assertTrue(vo != null);
    }
    
    @Test
    public void querySupplierByVo() throws Exception {
    	List<SupplierVo> vos = supplierService.querySupplierByVo(new SupplierVo());
    	
    	assertTrue(CollectionUtils.isNotEmpty(vos));
    }
    
    @Test
    public void getCommodityList() {
    	try {
        	Query query=new Query();
        	CommodityQueryVo commodityQueryVo=new CommodityQueryVo();
        	commodityQueryVo.setMerchantCode("SP20130821678648");
        	List<String> cate=new ArrayList<String>();
        	cate.add("16-11-16");
        	cate.add("13-12-15");
        	commodityQueryVo.setCategory(cate);
        	
        	PageFinder<Commodity> commoditys=commodityService.getCommodityList(commodityQueryVo,query,false);
        	if(commoditys!=null){
        		System.out.println(commoditys.getData().size());
        	}
        	
        	List<String> cate2=new ArrayList<String>();
        	cate2.add("16");
        	cate2.add("13");
        	commodityQueryVo.setCategory(cate2);
        	PageFinder<Commodity> commoditys2=commodityService.getCommodityList(commodityQueryVo,query,true);
        	if(commoditys2!=null){
        		System.out.println(commoditys2.getData().size());
        	}
        	
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
}
