/*
 * 类名 com.yougou.merchant.api.supplier.dao.MerchantContractUpdateHistoryMapper
 * 
 * 日期  Thu Jul 16 14:31:54 CST 2015
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

import com.yougou.merchant.api.supplier.vo.MerchantContractUpdateHistory;
import com.yougou.merchant.api.supplier.vo.MerchantContractUpdateHistoryQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantContractUpdateHistoryMapper {
    int countByQuery(MerchantContractUpdateHistoryQuery example);

    int deleteByQuery(MerchantContractUpdateHistoryQuery example);

    int deleteByPrimaryKey(String id);

    int insert(MerchantContractUpdateHistory record);

    int insertSelective(MerchantContractUpdateHistory record);

    List<MerchantContractUpdateHistory> selectByQuery(MerchantContractUpdateHistoryQuery example);

    MerchantContractUpdateHistory selectByPrimaryKey(String id);

    int updateByQuerySelective(@Param("record") MerchantContractUpdateHistory record, @Param("example") MerchantContractUpdateHistoryQuery example);

    int updateByQuery(@Param("record") MerchantContractUpdateHistory record, @Param("example") MerchantContractUpdateHistoryQuery example);

    int updateByPrimaryKeySelective(MerchantContractUpdateHistory record);

    int updateByPrimaryKey(MerchantContractUpdateHistory record);
}