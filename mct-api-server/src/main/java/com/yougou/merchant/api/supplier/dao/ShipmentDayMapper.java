package com.yougou.merchant.api.supplier.dao;

import org.apache.ibatis.annotations.Param;


/*
 * 发货日配置
 */
public interface ShipmentDayMapper {
	
	/**
	 * 查询非发货日
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	Integer isNotShipmentDay(@Param("year") Integer year,@Param("month") Integer month,@Param("day") Integer day);
	 
	
}
