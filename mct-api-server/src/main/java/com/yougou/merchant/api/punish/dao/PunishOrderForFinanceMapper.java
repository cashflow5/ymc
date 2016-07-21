package com.yougou.merchant.api.punish.dao;  

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface PunishOrderForFinanceMapper {
	/**
	 * updatePunishStockSettle:修改结算信息（状态/结算实际/结算单号），并记录用户操作的日志 
	 * @author li.n1 
	 * @param settleVo 
	 * @since JDK 1.6
	 */
	public void updatePunishStockSettle(@Param("registNumStr") String registNumStr,@Param("status") String status);
	
	/**
	 * updatePunishStockSettleByRegistNum:根据财务调用，修改状态 
	 * @author li.n1 
	 * @param settleNo
	 * @param status 
	 * @since JDK 1.6
	 */
	public void settleingPunishStockByRegistNum(@Param("registNumStr") String registNumStr,
			@Param("status") String status,
			@Param("settleNo") String settleNo,
			@Param("settleDate") Date settleDate);
	
	/**
	 * getPunishOrderIdByRegistNumStr:根据登记单号查询违规订单或缺货商品主键
	 * @author li.n1 
	 * @param registNumStr 登记单号登记单号,以逗号分开的字符串 '234','33'
	 * @return 
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getPunishOrderIdByRegistNumStr(
			@Param("registNumStr") String registNumStr);
	/**
	 * voidPunishStockSettleByRegistNum:财务作废结算单
	 * @author li.n1 
	 * @param registNumStr 登记单号登记单号,以逗号分开的字符串 '234','33'
	 * @param string 
	 * @since JDK 1.6
	 */
	public void voidPunishStockSettleByRegistNum(@Param("registNumStr") String registNumStr,
			@Param("status") String status);
	
	/**
	 * updatePunishStockSettle:修改违规订单为已结算的状态tbl_sp_supplier_punish_order的is_settle为1 
	 * @author li.n1 
	 * @param settleVo 
	 * @since JDK 1.6
	 */
	public void updatePunishOrderToSettle(@Param("registNumStr") String registNumStr);

}
