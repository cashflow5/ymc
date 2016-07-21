package com.yougou.merchant.api.punish;

import java.util.Date;

public interface IPunishOrderForFinanceService {
	/**
	 * settlePunishStock:财务已结算，修改结算信息，并记录用户操作的日志
	 * @author li.n1 
	 * @param registNumStr 登记单号登记单号,以逗号分开的字符串 '234','33'
	 * @since JDK 1.6
	 */
	public boolean settlePunishStock(String registNumStr);
	/**
	 * voidPunishStockSettle:财务作废结算单 ，通过登记单号
	 * @param registNumStr 登记单号登记单号,以逗号分开的字符串 '234','33'
	 * @author li.n1   登记状态（已审核）、结算单号、结算日期清空
	 * @since JDK 1.6
	 */
	public boolean voidPunishStockSettleByRegistNum(String registNumStr);
	/**
	 * settleingPunishStock:财务通知招商，正在结算。状态修改为“结算中” 
	 * @author li.n1 
	 * @param registNumStr 登记单号,以逗号分开的字符串 '234','33'
	 * @return settleNo
	 * @since JDK 1.6
	 */
	public boolean settleingPunishStockByRegistNum(String registNumStr,String settleNo,Date settleDate);
	
}
