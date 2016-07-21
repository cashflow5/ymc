package com.yougou.merchant.api.active.dao;

import java.util.List;
import java.util.Map;

import com.yougou.merchant.api.active.vo.ActivityEnrollMerchantDetailsVo;
import com.yougou.merchant.api.active.vo.ActivityEnrollMerchantVo;

public interface MerchantOfficialActiveMapper {
	 /**
	  * 查询活动报名商家列表
	  * @param supplierName
	  * @param activeId
	  * @param commodityNo
	  * @return
	  */
    public List<ActivityEnrollMerchantVo> queryActivityEnrollMerchant(Map<String,Object> map);
    
    /**
	  * 查询活动报名商家列表数
	  * @param supplierName
	  * @param activeId
	  * @param commodityNo
	  * @return
	  */
   public int queryActivityEnrollMerchantCount(Map<String,Object> map);
   
    
    
    /**
     * 查詢商家報名詳情
     * @param commodityNo
     * @param supplierName
     * @param activeName
     * @param activeId
     * @return
     */
    public List<ActivityEnrollMerchantDetailsVo>  queryActivityEnrollMerchantDetails(Map<String,Object> map);
    
    
    /**
     * 查詢商家報名詳情
     * @param commodityNo
     * @param supplierName
     * @param activeName
     * @param activeId
     * @return
     */
    public int queryActivityEnrollMerchantDetailsCount(Map<String,Object> map);
    
    
    /**
     * 审批商家报名
     * @param supplierCode
     * @param status
     * @return
     */
    public void  merchanteEnrollExamine(Map<String,Object> xamineMap);
    /**
     * 查询商家最高承担金额
     * @param map
     * @return
     */
    public Integer  queryCouponAmount(Map<String,Object> map);
     
    /**
     * 
     * @param xamineMap
     * 
     * @return  有异常的信息
     */
    public List<Map<String, Object>>  checkExamineEnrollInfo(Map xamineMap);
    
    /**
     * 查询官方活动报名的分摊金额
     * @param map
     * @return
     */
    public List<Map<String,Object>>   queryAmountMap(Map<String,List<Map<String, Object>>> map);
    
    
}
