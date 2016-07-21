package com.yougou.merchant.api.punish;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import com.yougou.merchant.api.common.UUIDGenerator;
import com.yougou.merchant.api.punish.dao.PunishOrderForFinanceMapper;
import com.yougou.merchant.api.supplier.dao.MerchantMapper;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog.OperationType;

@Service("punishOrderForFinanceService")
public class PunishOrderForFinanceServiceImpl implements IPunishOrderForFinanceService{
	@Resource
	private MerchantMapper merchantMapper;
	@Resource
	private PunishOrderForFinanceMapper punishOrderForFinanceMapper;
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(PunishOrderForFinanceServiceImpl.class);
	
	@Override
	public boolean settlePunishStock(String registNumStr) {
		boolean flag = false;
		try {
			//修改为已结算的状态
			punishOrderForFinanceMapper.updatePunishStockSettle(registNumStr,"3");
			//修改违规订单为已结算状态；tbl_sp_supplier_punish_order的is_settle为1 
			punishOrderForFinanceMapper.updatePunishOrderToSettle(registNumStr);
			//根据登记单号查结算Id
			List<Map<String,Object>> mapList = punishOrderForFinanceMapper.getPunishOrderIdByRegistNumStr(registNumStr);
			//记载日志
			MerchantOperationLog operationLog = null;
			for(Map<String,Object> map : mapList){
				operationLog = new MerchantOperationLog();
				operationLog.setId(UUIDGenerator.getUUID());
				operationLog.setOperationNotes("财务已结算");
				operationLog.setMerchantCode(MapUtils.getString(map, "punish_id"));
				operationLog.setOperator("财务");
				operationLog.setOperated(new Date());
				operationLog.setOperationType(OperationType.PUNISHORDER);
				merchantMapper.insertMerchantLog(operationLog);
			}
			flag = true;
		} catch (Exception e) {
			logger.error("财务调用招商，违规订单结算发生错误！",e);
		}
		return flag;
	}
	
	@Override
	public boolean voidPunishStockSettleByRegistNum(String registNumStr) {
		boolean flag = false;
		try{
			punishOrderForFinanceMapper.voidPunishStockSettleByRegistNum(registNumStr,"1");
			flag = true;
		}catch(Exception e){
			logger.error("财务作废结算单 ，通过登记单号发生错误！",e);
		}
		return flag;
	}
	
	@Override
	public boolean settleingPunishStockByRegistNum(String registNumStr,String settleNo,Date settleDate) {
		boolean flag = false;
		try{
			punishOrderForFinanceMapper.settleingPunishStockByRegistNum(registNumStr,"2",settleNo,settleDate);
			flag = true;
		}catch(Exception e){
			logger.error("财务正在结算 ，通知招商发生错误！",e);
		}
		return flag;
	}
}
