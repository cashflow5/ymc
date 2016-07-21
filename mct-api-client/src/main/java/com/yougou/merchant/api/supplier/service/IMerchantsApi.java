package com.yougou.merchant.api.supplier.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.supplier.vo.ExpressTemplate;
import com.yougou.merchant.api.supplier.vo.MerchantBankInfoVo;
import com.yougou.merchant.api.supplier.vo.MerchantMenu;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog;
import com.yougou.merchant.api.supplier.vo.MerchantRejectedAddressVo;
import com.yougou.merchant.api.supplier.vo.MerchantUser;

/**
 * 
 * 招商系统-招商基本信息service接口类
 * 
 * @author mei.jf
 * @date 2013-12-16
 * 
 */
public interface IMerchantsApi {

    /**
     * 查询商家售后退货地址列表
     * 
     * @author wang.m
     * @date 2012-05-11
     */
    public PageFinder<MerchantRejectedAddressVo> getMerchantRejectedAddressList(MerchantRejectedAddressVo vo, Query query);

    /**
     * 查询商家账号列表 （分页展示）
     * 
     * @param user
     * @param query
     * @return
     */
    public PageFinder<MerchantUser> queryMerchantUserList(MerchantUser user, Query query);
    
    /**
     * 保存商家账户信息
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    boolean saveMerchantUser(MerchantUser vo) throws Exception;
    
    void updateMerchantUser(MerchantUser vo);
    
    MerchantUser getMerchantUserById(String id);
    
    /**
     * 查询商家操作日志列表
     * 
     * @param merchantCode
     * @param query
     * @return
     */
    public PageFinder<MerchantOperationLog> queryMerchantOperLogList(String merchantCode, Query query);
    
    void saveMerchantOperationLog(MerchantOperationLog log);
    
    /**
     * 保存商家售后退货地址数据
     *
     * @author wang.m
     * @date 2012-05-11
     */
    public boolean saveMerchantRejectedAddress(HttpServletRequest req, MerchantRejectedAddressVo merchantRejectedAddress);

   /**
    * 根据id查询商家售后退货地址列表
    *
    * @author mei.jf
    * @date 2013-12-18
    */
   public MerchantRejectedAddressVo getMerchantRejectedAddressById(String id);
   
   /**
    * 判断商家退货地址是否已经存在
    *
    * @throws Exception
    */
   public boolean exictRejectedAddressCount(String supplierName);
     
   
   /**
    * 商家中心菜单资源管理
    * @param menuName
    * @param query
    * @return
    */
   public PageFinder<MerchantMenu> queryMerchantMenuList(String menuName, Query query);
   public MerchantMenu getMerchantMenuById(String id);
   public boolean insertMerchantMenu(MerchantMenu menu);
   public boolean updateMerchantMenu(MerchantMenu menu);
   
   /**
    * 查询优购管理员 所拥有的商家列表
    * 
    * @param userId （优购管理员userId）
    * @param merchantCode
    * @param merchantName
    * @param isInputYougouWarehouse
    * @param query
    * @return
    */
   public PageFinder<Map<String, Object>> queryYougouAdminMerchantList(String userId, String merchantCode, String merchantName, Integer isInputYougouWarehouse, Query query);
   /**
    * 查询优购管理员 没有拥有的商家列表
    * 
    * @param userId （优购管理员userId）
    * @param merchantCode
    * @param merchantName
    * @param isInputYougouWarehouse
    * @param query
    * @return
    */
   public PageFinder<Map<String, Object>> queryMerchantNotHadList(String userId, String merchantCode, String merchantName, Integer isInputYougouWarehouse, Query query);
   
   /**
    * 快递公司模板
    * 
    * @param vo
    */
   void insertExpressTemplate(ExpressTemplate vo);
   void updateExpressTemplate(ExpressTemplate vo);
   ExpressTemplate getExpressTemplateById(String id);
   
	/**
	 * 计算出时间段内商家发货日时长
	 * @param startDate 开始时间 yyyy-MM-dd hh:mm:ss
	 * @param endDate 结束时间 yyyy-MM-dd hh:mm:ss
	 * @return 时长,0表时当天为非发货日，-1 表于开始时间不能大于结束时间，-2时间段不能大于30天
	 * @throws Exception
	 */
	public Double getShipmentDayHour(String startDate,String endDate) throws Exception;
	/**
	 * 计算出当前时间到某个时间发货有效时间
	 * @param startDate 开始时间 yyyy-MM-dd hh:mm:ss
	 * @return 时长，-1 表于开始时间不能大于结束时间
	 * @throws Exception
	 */
	public Double getShipmentCountdownHour(Date startDate,Date endDate) throws Exception;
	
	
	/**
	 * 
	 * 查询商结算银行和开票银行，如两条银行信息一样则合并为一条数据，如果不一样，第一条信息为结算银行第二条为开票银行
	 * @return  
	 */
	public MerchantBankInfoVo  queryMerchantBankInfo(String merchantCode);
}
