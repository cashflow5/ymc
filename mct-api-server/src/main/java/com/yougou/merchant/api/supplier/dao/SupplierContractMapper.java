/*
 * 类名 com.yougou.merchant.api.supplier.dao.SupplierContractMapper
 * 
 * 日期  Tue Jun 23 13:25:28 CST 2015
 * 
 * 版权声明Copyright (C) 2013 YouGou Information Technology Co.,Ltd 
 * All Rights Reserved. 
 * 
 * The software for the YouGou technology development, without the 
 * company's written consent, and any other individuals and 
 * organizations shall not be used, Copying, Modify or distribute 
 * the software.
 * 
 */
package com.yougou.merchant.api.supplier.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.supplier.vo.MerchantBrandDeducRate;
import com.yougou.merchant.api.supplier.vo.SupplierContract;

public interface SupplierContractMapper {
	SupplierContract selectSupplierContractByContractNo(@Param("contractNo") String contractNo);
	
	SupplierContract selectSupplierContractByContractId(@Param("contractId") String contractId);
	
	void updateSupplierContract(SupplierContract supplierContract);
	
	int queryMerchantBrandDeductRateCount(@Param("merchantCodelist") List<String> merchantCode, @Param("updateTimeStart") String updateTimeStart, @Param("updateTimeEnd") String updateTimeEnd);
	
	List<MerchantBrandDeducRate> queryMerchantBrandDeductRateList(@Param("merchantCodelist") List<String> merchantCode, @Param("updateTimeStart") String updateTimeStart, @Param("updateTimeEnd") String updateTimeEnd, @Param("query") Query query);
}