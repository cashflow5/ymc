/**
 * 
 */
package com.yougou.merchant.api.supplier.service;

import com.yougou.merchant.api.supplier.vo.MerchantOperationLog;
import com.yougou.merchant.api.supplier.vo.SupplierUpdateHistory;
import com.yougou.merchant.api.supplier.vo.SupplierVo;

/**
 * 采购调用接口
 * 
 * @author huang.tao
 *
 */
public interface ISupplierPurchaseService {
	/**
	 * 添加供应商信息
	 * 
	 * @param vo
	 */
	void insertSupplier(SupplierVo vo) throws Exception;
	
	/**
	 * 修改供应商信息 <br />
	 * 必填项：
	 * <b>1.updateDate</b><br />
	 * <b>2.updateTimestamp</b><br />
	 * <b>3.updateUser</b><br />
	 * ===========================
	 * @param vo
	 * @throws Exception
	 */
	void updateSupplier(SupplierVo vo) throws Exception;
	
	/**
	 * 添加供应商操作日志
	 * 
	 * @param log
	 */
	void saveMerchantOperationLog(MerchantOperationLog log) throws Exception;
	
	/**
	 * 通过Id删除商家信息<br />
	 * 删除包括{
	 * 	1、删除供应商联系人（tbl_sp_supplier_contact）<br />
     *  2、供应商登录帐号逻辑删除（tbl_merchant_user）<br />
     *  3、供应商对象逻辑删除
	 * }
	 * @param id
	 * @param operator 操作人
	 * @throws Exception
	 */
	void deleteSupplierById(String id, String operator) throws Exception;
	
	/**
	 * 通过merchantCode删除商家信息<br />
	 * 删除包括{
	 * 	1、删除供应商联系人（tbl_sp_supplier_contact）<br />
     *  2、供应商登录帐号逻辑删除（tbl_merchant_user）<br />
     *  3、供应商对象逻辑删除
	 * }
	 * @param supplierCode
	 * @param operator 操作人
	 * @throws Exception
	 */
	void deleteSupplierBySupplierCode(String supplierCode, String operator) throws Exception;
	
	/**
	 * 供应商修改日志
	 * 
	 * @param vo
	 * @throws Exception
	 */
	void insertSupplierUpdateHistory(SupplierUpdateHistory vo) throws Exception;
}
