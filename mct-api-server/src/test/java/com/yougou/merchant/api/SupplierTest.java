package com.yougou.merchant.api;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yougou.merchant.api.common.Constant;
import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.supplier.service.ISupplierService;
import com.yougou.merchant.api.supplier.vo.MerchantBrandDeducRate;
import com.yougou.merchant.api.supplier.vo.RejectedAddressVo;
import com.yougou.merchant.api.supplier.vo.SupplierQueryVo;
import com.yougou.merchant.api.supplier.vo.SupplierVo;

/**
 * 
 * @ClassName: SupplierTest
 * @Description: 供应商Test
 * @author zhuang.rb
 * @date 2012-9-7 下午6:03:41
 * 
 */
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SupplierTest{

	@Resource
	private ISupplierService supplierService;

	/*public SupplierTest() {
		// 启用直接对保护类型属性变量进行注入的机制
		this.setPopulateProtectedVariables(true);
	}

	*//**
	 * 设置bean xml路径，可以添加多个
	 *//*
	protected String[] getConfigLocations() {
		return new String[] { "classpath*:applicationContext*.xml"};
	}*/

	@Test
	public void testBeanInjection() {
		try {
			List<SupplierVo> lstSupplier = supplierService.getAllSupplier();
			for (SupplierVo vo : lstSupplier) {
				System.out.println("供应商名称：" + vo.getSupplier());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testSupplierByMerchant() {
		try {
			SupplierVo vo=supplierService.getSupplierByMerchantCode("SP20130821678648");
			System.out.println("供应商：" + supplierService.getSupplierByMerchantCode("SP20130821678648").getSupplier());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	@Test
	public void testSupplierSide() {
		try {
			System.out.println("供应商配送方：" + supplierService.getSupplierDistributionSide("SP20110831511483"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	@Test
	public void testSupplierRejectedAddress() {
		try {
			RejectedAddressVo vo = supplierService.getSupplierRejectedAddress("SP20110831511483");
			System.out.println("供应商名称：" + vo.getSupplierName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	@Test
	public void testContainYougouRejectedAddress() {
		try {
			RejectedAddressVo vo = supplierService.getContainYougouRejectedAddress("");
			System.out.println("售后地址：" + vo.getWarehouseArea()+vo.getWarehouseAdress());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	@Test
	public void testTradeCurrencyByMerchantCode() {
		try {
			System.out.println("供应商币种：" + supplierService.getTradeCurrencyByMerchantCode("SP20130701857574").getDesc());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	@Test
	public void testUpdateSupplierHousewareCode() {
		try {
			System.out.println("更新仓库：" + supplierService.updateMerchantWarehouseCode("SP20130701857574", "12345678", "张三"));;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void querySupplierByVo() {
		try {
			SupplierVo vo = new SupplierVo();
			vo.setSupplierCode("SP20130821678648");
			List<SupplierVo> list = supplierService.querySupplierByVo(vo);
			
			assertTrue(list.size() > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getSupplierByName() {
		try {
			SupplierVo vo = supplierService.getSupplierByName("燕子测试招商专用供应商");
			System.out.println(vo.getIsInvoice());
			assertTrue(vo != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void querySupplierListByPage() {
		SupplierQueryVo vo = new SupplierQueryVo();
		vo.setIsValid(1);
		//vo.setIsInputYougouWarehouse(2);
		List<String> brands = new ArrayList<String>();
		brands.add("Hfjt");
		//brands.add("YfrI");
		//brands.add("sL1n");
		vo.setBrandNos(brands);
		try {
			PageFinder<SupplierVo> vos = supplierService.querySupplierListByPage(vo, new Query());
			
			assertTrue(vos != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getWarehouseByMerchantCode() throws Exception {
		Map<String, String> map = supplierService.getWarehouseByMerchantCode("SP20130821678648");
		
		if (MapUtils.isNotEmpty(map)) {
			System.out.println("仓库编码: " + map.get(Constant.WAREHOUSECODE));
			System.out.println("仓库名称: " + map.get(Constant.WAREHOUSENAME));
		}
	}
	
	@Test
	public void queryMerchantBrandDeductRateTest() throws Exception {
		Query query = new Query();
		query.setPage(1);
		query.setPageSize(3);
		List<String> merchantCode = new ArrayList<String>();
		merchantCode.add("SP20151210234768");
		merchantCode.add("SP20151214362820");
		String updateTimeStart = "2015-01-01 00:00:00";
		String updateTimeEnd = "2016-01-01 00:00:00";
		PageFinder<MerchantBrandDeducRate> pageFinder = 
				supplierService.queryMerchantBrandDeductRate(merchantCode, updateTimeStart, updateTimeEnd, query);
		assertTrue(pageFinder.getData().size() == 3);
	}
	
}
