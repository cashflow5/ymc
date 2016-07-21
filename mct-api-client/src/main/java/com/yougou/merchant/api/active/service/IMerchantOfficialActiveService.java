package com.yougou.merchant.api.active.service;

import java.util.List;
import java.util.Map;

import com.yougou.merchant.api.active.vo.ActivityEnrollMerchantDetailsVo;
import com.yougou.merchant.api.active.vo.ActivityEnrollMerchantVo;
import com.yougou.merchant.api.active.vo.MerchanteEnrollxamineVo;
import com.yougou.merchant.api.active.vo.QueryActivityVo;
import com.yougou.merchant.api.common.Query;
/**
 * 商家活动接口类
 * 
 * @author zhang.wj
 *
 */
public interface IMerchantOfficialActiveService {
	 /**
	  * 查询活动报名商家列表
	  * @param supplierName
	  * @param activeId
	  * @param commodityNo
	  * @return
	  */
     public List<ActivityEnrollMerchantVo> queryActivityEnrollMerchant(QueryActivityVo vo ,Query  query);
   
     
     /**
	  * 查询活动报名商家列表数
	  * @param supplierName
	  * @param activeId
	  * @param commodityNo
	  * @return
	  */
     public int queryActivityEnrollMerchantCount(QueryActivityVo vo );
     /**
      * 查詢商家報名詳情
      * @param QueryActivityVo
      * @param query  
      * @return
      */
     public List<ActivityEnrollMerchantDetailsVo>  queryActivityEnrollMerchantDetails(QueryActivityVo vo ,Query  query) ;
     
     /**
      * 查詢商家報名詳情数
      * @param commodityNo
      * @param supplierName
      * @param activeName
      * @param activeId
      * @return
      */
     public int  queryActivityEnrollMerchantDetailsCount(QueryActivityVo vo);
     
     /**
      * 审批商家报名
      * @param 
      * @param 
      * @return  
      */
     public MerchanteEnrollxamineVo  merchanteEnrollExamine(List<String>  examineList,String status,String auditRemark,String activeId);
     
     
     
     /**
      *  查询商家最高承担金额
      * @param merchantCode 供应商编码(必输)
      * @param activeId 活动编码(选输)
      * @param commodityNo 商品编码(选输)
      * @return 商家最高承担金额
      */
     public int  queryCouponAmount(QueryActivityVo vo);
     
     
     /**
      * 查询官方活动报名的分摊金额
      * @param map
      * @return
      */
     public List<Map<String,Object>>  queryAmountMap(Map<String,Object> map);
     

}
