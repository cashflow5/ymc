package com.yougou.merchant.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yougou.merchant.api.supplier.service.IBackOrderService;
import com.yougou.merchant.api.supplier.vo.BackOrderDetailVo;
import com.yougou.merchant.api.supplier.vo.BackOrderVo;
import com.yougou.ordercenter.common.DateUtil;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BackOrderServiceTest {
	
	@Resource
	private IBackOrderService backOrderService;
	
	@Test
	public void addBackOrderFromWmsTest() throws Exception{
		BackOrderVo backOrderVo = new BackOrderVo();
		backOrderVo.setId("21111");
		backOrderVo.setBackCode("backcode001");
		backOrderVo.setBackOperator("jack");
		backOrderVo.setBackTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		backOrderVo.setBackWarehHouseCode("商家仓002");
		backOrderVo.setStatus("已确认");
		backOrderVo.setPlanBackTotalCount(12);
		
		List<BackOrderDetailVo> detailList = new ArrayList<BackOrderDetailVo>();
		BackOrderDetailVo backOrderDetailVo = new BackOrderDetailVo();
		backOrderDetailVo.setId("21111");
		backOrderDetailVo.setMainId("11111");
		backOrderDetailVo.setCommodityName("商品名称01");
		backOrderDetailVo.setSpecKey("35码");
		backOrderDetailVo.setOrderSubNo("C_0009");
		backOrderDetailVo.setInsideCode("货品条码001");
		backOrderDetailVo.setSupplierCode("款色001");
		backOrderDetailVo.setProductNo("货品编码001");
		backOrderDetailVo.setType("SDARD_GOODS");
		backOrderDetailVo.setPlanBackCount(10);
		backOrderDetailVo.setUpdateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		BackOrderDetailVo backOrderDetailVo2 = new BackOrderDetailVo();
		backOrderDetailVo2.setId("22222");
		backOrderDetailVo2.setMainId("11111");
		backOrderDetailVo2.setCommodityName("商品名称02");
		backOrderDetailVo2.setSpecKey("34码");
		backOrderDetailVo2.setOrderSubNo("C_0008");
		backOrderDetailVo2.setInsideCode("货品条码002");
		backOrderDetailVo2.setSupplierCode("款色002");
		backOrderDetailVo2.setProductNo("货品编码002");
		backOrderDetailVo2.setType("SDARD_GOODS");
		backOrderDetailVo2.setPlanBackCount(2);
		backOrderDetailVo2.setUpdateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		detailList.add(backOrderDetailVo);
		detailList.add(backOrderDetailVo2);
		
		backOrderVo.setDetailList(detailList);
		backOrderService.addBackOrderFromWms(backOrderVo);
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}
}
