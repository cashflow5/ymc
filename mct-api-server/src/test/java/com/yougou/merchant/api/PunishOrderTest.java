package com.yougou.merchant.api;  

import static org.junit.Assert.*;
import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.yougou.merchant.api.punish.IPunishOrderForFinanceService;


@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class PunishOrderTest {
	
	@Resource
	private IPunishOrderForFinanceService service; 
	
	@Test
	public void testGetPunishOrderIdByRegistNum(){
		String settleId = "8a8094fd4bbeb363014bbeb8a87b0002";
		//List<Map<String, Object>>  list = service.getPunishOrderIdBySettleId(settleId);
		//for(Map<String, Object> map : list){
		//	System.out.println(map.get("punish_id"));
		//}
		//assertNotNull(list);
	}
	
	//select po.id as punish_id from tbl_sp_supplier_punish_order po inner join tbl_sp_supplier_punish_settle 
	//ps on po.settle_id = ps.id  where ps.regist_num in ('DJ201502257769','DJ201501232599')
	
	@Transactional
	@Rollback(value=true)
	@Test
	public void testSettlePunishStock(){
		//登记单号
		String str = "'DJ201502257769','DJ201501232599'";
		boolean flag = service.settlePunishStock(str);
		System.out.println("===");
		assertTrue(flag);
	}
	
	@Transactional
	@Rollback(value=true)
	@Test
	public void testVoidPunishStockSettleByRegistNum(){
		String str = "'DJ201502257769','DJ201501232599'";
		boolean flag = service.voidPunishStockSettleByRegistNum(str);
		System.out.println("===");
		assertTrue(flag);
	}
	
	@Transactional
	@Rollback(value=true)
	@Test
	public void testSettleingPunishStock(){
		String str = "'DJ201502257769','DJ201501232599'";
		boolean flag = service.settleingPunishStockByRegistNum(str,"jiesuandanhao",new Date());
		System.out.println("===");
		assertTrue(flag);
	}
}
