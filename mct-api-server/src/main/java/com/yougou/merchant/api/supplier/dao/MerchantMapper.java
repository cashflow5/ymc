package com.yougou.merchant.api.supplier.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.supplier.vo.MerchantBankInfoVo;
import com.yougou.merchant.api.supplier.vo.MerchantMenu;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog;
import com.yougou.merchant.api.supplier.vo.MerchantUser;
import com.yougou.merchant.api.supplier.vo.SupplierUpdateHistory;
import com.yougou.merchant.api.supplier.vo.UserAuthority;

/**
 * 招商用户信息数据库操作
 * 
 * @author mei.jf
 *
 */
public interface MerchantMapper {
    
	List<MerchantUser> queryMerchantUserListByQuery(@Param("user")MerchantUser user,@Param("query")Query query);
	
	List<MerchantUser> queryMerchantUserList(MerchantUser user);
	
	Integer queryMerchantUserCount(MerchantUser user);
	
	/**
	 * 增加商家售后退货地址
	 * 
	 * @param vo
	 */
	void insertMerchantUser(MerchantUser vo);
	
	 /**
     * 更新商家售后退货地址
     * 
     * @param vo
     */
	void updateMerchantUser(MerchantUser vo);
	
	/**
	 * 插入商家操作日志
	 * 
	 * @param log
	 */
	void insertMerchantLog(MerchantOperationLog log);
	
	/**
	 * 查询商家操作日志
	 * 
	 * @param merchantCode
	 * rowbound --没有用到
	 * @return
	 */
	//List<MerchantOperationLog> queryMerchantOperLog(String merchantCode, RowBounds rowbound);
	List<MerchantOperationLog> queryMerchantOperLog(String merchantCode);
	Integer queryMerchantOperLogCount(String merchantCode);
	
	/**
	 * 商家中心菜单表维护
	 * 
	 * @param menu
	 */
	void insertYmcMenuResource(MerchantMenu menu);
	void updateYmcMenuResource(MerchantMenu menu);
	List<MerchantMenu> queryMerchantMenuList(@Param("menuName") String menuName, RowBounds rowbound);
	Integer queryMerchantMenuCount(@Param("menuName") String menuName);
	MerchantMenu getMerchantMenuById(String id);
	void deleteYmcMenuResourceById(String id);
	
	/**
	 * 用户菜单授权
	 * 
	 * @param userauthority
	 */
	void insertUserAuthority(UserAuthority userauthority);
	void deleteUserAuthorityByUserId(String userId);
	List<UserAuthority> queryUserAuthoritysByUserId(String userId);
	
	
	/**
	 * 查询优购管理员所拥有商家
	 * 
	 * @param userId (当前用户Id)
	 * @param merchantCode
	 * @param merchantName
	 * @param isInputYougouWarehouse
	 * @param rowbound
	 * @return Map[] {tbl_merchant_user_supplier.id, supplier, supplier_code, is_input_yougou_warehouse,delete_flag,is_valid}
	 */
	List<Map<String, Object>> queryYougouAdminMerchantList(@Param("userId")String userId, @Param("merchantCode")String merchantCode, @Param("merchantName")String merchantName, @Param("isInputYougouWarehouse")Integer isInputYougouWarehouse, RowBounds rowbound);
	Integer queryYougouAdminMerchantCount(@Param("userId")String userId, @Param("merchantCode")String merchantCode, @Param("merchantName")String merchantName, @Param("isInputYougouWarehouse")Integer isInputYougouWarehouse);
	
	/**
	 * 查询优购管理员不拥有的商家
	 * 
	 * @param userId (当前用户Id)
	 * @param merchantCode
	 * @param merchantName
	 * @param isInputYougouWarehouse
	 * @param rowbound
	 * @return Map[] {supplier, supplier_code, is_input_yougou_warehouse,delete_flag,is_valid}
	 */
	List<Map<String, Object>> queryMerchantNotHadList(@Param("userId")String userId, @Param("merchantCode")String merchantCode, @Param("merchantName")String merchantName, @Param("isInputYougouWarehouse")Integer isInputYougouWarehouse, RowBounds rowbound);
	Integer queryMerchantNotHadCount(@Param("userId")String userId, @Param("merchantCode")String merchantCode, @Param("merchantName")String merchantName, @Param("isInputYougouWarehouse")Integer isInputYougouWarehouse);
	void insertYougouAdminMerchant(@Param("id")String id, @Param("userId")String userId, @Param("merchantCode")String merchantCode);
	void deleteYougouAdminMerchant(String id);
	
	//添加修改日志
	void insertUpdateHistory(SupplierUpdateHistory vo);
	
	List<Map<String,Object>> queryMerchantBankInfo(String merchantCode) ;
}
