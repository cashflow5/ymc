package com.yougou.merchant.api;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yougou.merchant.api.active.service.IMerchantOfficialActiveService;
import com.yougou.merchant.api.active.vo.QueryActivityVo;

/**
 * 
 * @author luo.q1
 * @time 2016-04-09
 */
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TaobaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private IMerchantOfficialActiveService service;
	

	@Test
	public void testResult() {
		QueryActivityVo vo = new QueryActivityVo();
		vo.setMerchantCode("SP20150123257493");
		vo.setActiveId("6505be1e977a4cd19762901e178305dd");
		vo.setCommodityNo("100254426");
		System.out.println("[merchantCode]:"+vo.getMerchantCode()+"[activeId]"+vo.getActiveId()+"[commodityNo]"+vo.getCommodityNo());
		
		int result = service.queryCouponAmount(vo);
		System.out.println("~~~~~~~~~~run and result is :"+result);
		try {
//			TranslateResult result = taobaoService.translateTBProps2YGProp("20000:101078;13021751:146743520;122216347:132721270;20608:6384766;124128491:28398;122216587:3323086;122216629:15354885;122216563:30161;122216628:115801;122216351:30232;1626698:24574746;122216561:115807;34272:115801;34272:115772;20490:3267935;1627207:3232481;1627207:28320;1627207:28326;20549:30106;20549:670;20549:671;20549:29542;20549:28388;20549:672;20549:28389;122216632:14545464;21541:38487;20603:29454;122276315:28688;122216608:3267959;122216515:29535;32960:30232", "T0",1);
//			System.out.println(result.isError());
//			System.out.println(result.getErrorMessage());
//			System.out.println(result.getTbCategoryCode());
//			System.out.println(result.getTbCategoryName());
//			System.out.println(result.getTbCategoryFullName());
//			List<PropItem>  props = result.getYgProps();
//			for(PropItem item:props){
//				System.out.println("属性："+item.getYgPropNo()+"--"+item.getTbPropNo()+"--"+item.getTbPropName()+"--"+item.getPropResult().getDesc());
//				List<PropValue> values = item.getPropVals();
//				for(PropValue value:values){
//					System.out.println("属性值："+value.getYgPropValueNo()+"--"+value.getTbPropValueNo()+"--"+value.getTbPropValueName()+"--"+value.getPropResult().getDesc());
//				}
//			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
