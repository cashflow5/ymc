package com.yougou.merchant.api.supplier.service;

import com.yougou.merchant.api.supplier.vo.MerchantContractUpdateHistory;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog;
import com.yougou.merchant.api.supplier.vo.YmcResult;

/**
 * 
 * @author le.sm
 * 
 * @description
 *【商家后台】账号启用时，通过接口发消息通知WMS自动匹配商家仓库（给货品的接口和调用WMS的接口）
      【商家后台】账号启用或停用时，分配或撤销相应的权限（向货品系统提供接口）

 */
public interface IMerchantOperatorApi {

	/**
	 * 启用账号时候，将主账号分配基本权限，如果主账号已经有权限，不做分配操作，只启动该商家所有账号
	 * 通知WMS 自动分配仓库
	 * @param user
	 */
	YmcResult  startUpAccout(String merchantCode,String operator);
	
	/**
	 * 关闭该商家所有账号
	 * 通知WMS关闭仓库
	 * @param user
	 */
	YmcResult  stopAccout(String merchantCode,String operator);
	
	/**
	 * 提供给商品系统写商家操作日志 
	 * operationType:
	 *  BASIC_DATA("商家资料"), 
		ACCOUNT("商家帐户"), 
		CONTRACT("合同"), 
		CONTACT("联系人"),
	 */
	YmcResult  addMerchantLog(MerchantOperationLog log);
	/**
	 * 合同操作日志接口
	 * @param log
	 * @return
	 */
	YmcResult  addContractLog(MerchantContractUpdateHistory log);
}
