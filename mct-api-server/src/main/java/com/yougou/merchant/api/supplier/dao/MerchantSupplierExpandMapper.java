/*
 * ���� com.yougou.merchant.api.supplier.dao.MerchantSupplierExpandMapper
 * 
 * ����  Thu Jul 16 14:31:54 CST 2015
 * 
 * ��Ȩ����Copyright (C) 2013 YouGou Information Technology Co.,Ltd 
 * All Rights Reserved. 
 * 
 * The software for the YouGou technology development, without the 
 * company's written consent, and any other individuals and 
 * organizations shall not be used, Copying, Modify or distribute 
 * the software.
 * 
 */
package com.yougou.merchant.api.supplier.dao;

import com.yougou.merchant.api.supplier.vo.MerchantSupplierExpand;
import com.yougou.merchant.api.supplier.vo.MerchantSupplierExpandQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantSupplierExpandMapper {
    int countByQuery(MerchantSupplierExpandQuery example);

    int deleteByQuery(MerchantSupplierExpandQuery example);

    int deleteByPrimaryKey(String id);

    int insert(MerchantSupplierExpand record);

    int insertSelective(MerchantSupplierExpand record);

    List<MerchantSupplierExpand> selectByQuery(MerchantSupplierExpandQuery example);

    MerchantSupplierExpand selectByPrimaryKey(String id);

    int updateByQuerySelective(@Param("record") MerchantSupplierExpand record, @Param("example") MerchantSupplierExpandQuery example);

    int updateByQuery(@Param("record") MerchantSupplierExpand record, @Param("example") MerchantSupplierExpandQuery example);

    int updateByPrimaryKeySelective(MerchantSupplierExpand record);

    int updateByPrimaryKey(MerchantSupplierExpand record);
    
    int queryCouponAmountByMerchantCode(@Param("merchantCode")String merchantCode);
}