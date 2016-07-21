package com.yougou.merchant.api.supplier.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.mortbay.util.StringUtil;
import org.springframework.stereotype.Service;

import com.yougou.merchant.api.common.PageFinder;
import com.yougou.merchant.api.common.Query;
import com.yougou.merchant.api.common.UUIDGenerator;
import com.yougou.merchant.api.supplier.dao.MerchantMapper;
import com.yougou.merchant.api.supplier.dao.RejectedAddressMapper;
import com.yougou.merchant.api.supplier.dao.ShipmentDayMapper;
import com.yougou.merchant.api.supplier.service.IMerchantsApi;
import com.yougou.merchant.api.supplier.vo.ExpressTemplate;
import com.yougou.merchant.api.supplier.vo.MerchantBankInfoVo;
import com.yougou.merchant.api.supplier.vo.MerchantMenu;
import com.yougou.merchant.api.supplier.vo.MerchantOperationLog;
import com.yougou.merchant.api.supplier.vo.MerchantRejectedAddressVo;
import com.yougou.merchant.api.supplier.vo.MerchantUser;

/**
 * 
 * 招商系统-商家售后service类
 * 
 * @author mei.jf
 * @date 2013-12-17
 * 
 */
@Service(value="merchantsApi")
public class MerchantsApi implements IMerchantsApi {

    private Logger logger = Logger.getLogger(MerchantsApi.class);

    @Resource
    private MerchantMapper merchantMapper;
    
    @Resource
    private RejectedAddressMapper rejectedAddressMapper;
    
    @Resource
	private ShipmentDayMapper shipmentDayMapper;
    
    /**
     * 查询商家售后退货地址列表
     * 
     * @author wang.m
     * @date 2012-05-11
     */
    public PageFinder<MerchantRejectedAddressVo> getMerchantRejectedAddressList(MerchantRejectedAddressVo vo, Query query) {
        Integer count = rejectedAddressMapper.getMerchantRejectedAddressListCount(vo);
        List<MerchantRejectedAddressVo> list = rejectedAddressMapper.getMerchantRejectedAddressList(vo, new RowBounds(query.getOffset(), query.getPageSize()));
        PageFinder<MerchantRejectedAddressVo> pageFinder = new PageFinder<MerchantRejectedAddressVo>(query.getPage(), query.getPageSize(), count, list);
        return pageFinder;
    }

    @Override
    public PageFinder<MerchantUser> queryMerchantUserList(MerchantUser user, Query query) {
        Integer count = merchantMapper.queryMerchantUserCount(user);
       // List<MerchantUser> list = merchantMapper.queryMerchantUserList(user, new RowBounds(query.getOffset(), query.getPageSize()));
        List<MerchantUser> list = merchantMapper.queryMerchantUserListByQuery(user,query );
        PageFinder<MerchantUser> pageFinder = new PageFinder<MerchantUser>(query.getPage(), query.getPageSize(), count, list);
        return pageFinder;
    }

	@Override
	public PageFinder<MerchantOperationLog> queryMerchantOperLogList(
			String merchantCode, Query query) {
		Integer count = merchantMapper.queryMerchantOperLogCount(merchantCode);
       // List<MerchantOperationLog> list = merchantMapper.queryMerchantOperLog(merchantCode, new RowBounds(query.getOffset(), query.getPageSize()));
		List<MerchantOperationLog> list = merchantMapper.queryMerchantOperLog(merchantCode);
		
        PageFinder<MerchantOperationLog> pageFinder = new PageFinder<MerchantOperationLog>(query.getPage(), query.getPageSize(), count, list);
        return pageFinder;
	}

    /**
     * 保存商家售后退货地址数据
     * 
     * @author wang.m
     * @date 2012-05-11
     */
    public boolean saveMerchantRejectedAddress(HttpServletRequest req, MerchantRejectedAddressVo MerchantRejectedAddressVo) {
        boolean bool = false;
        try {
            MerchantRejectedAddressVo reject = new MerchantRejectedAddressVo();
            // 判断是修改还是保存
            if (null != MerchantRejectedAddressVo) {
                reject.setConsigneeName(MerchantRejectedAddressVo.getConsigneeName());
                reject.setConsigneePhone(MerchantRejectedAddressVo.getConsigneePhone());
                reject.setConsigneeTell(MerchantRejectedAddressVo.getConsigneeTell());
               // SystemmgtUser user = GetSessionUtil.getSystemUser(req);
                String loginUser = "";
//                if (user != null) {
//                    loginUser = user.getUsername();
//                }
                reject.setCreaterPerson(loginUser);
                reject.setCreaterTime(formDate());
                reject.setSupplierName(MerchantRejectedAddressVo.getSupplierName());
                reject.setSupplierCode(MerchantRejectedAddressVo.getSupplierCode());
                reject.setWarehousePostcode(MerchantRejectedAddressVo.getWarehousePostcode());
                reject.setWarehouseArea(MerchantRejectedAddressVo.getWarehouseArea());
                reject.setWarehouseAdress(MerchantRejectedAddressVo.getWarehouseAdress());

                String operationNotes;

                // 如果存在,则修改
                if (StringUtils.isNotBlank(MerchantRejectedAddressVo.getId())) {
                    reject.setId(MerchantRejectedAddressVo.getId());
                    MerchantRejectedAddressVo rejectInfo = rejectedAddressMapper.getMerchantRejectedAddressById(MerchantRejectedAddressVo.getId());
                   // operationNotes = merchantOperationLogService.buildMerchantAfterServiceAddrOperationNotes(rejectInfo, reject);
                    rejectedAddressMapper.updateMerchantRejectedAddress(reject);

                } else {// 保存
                	rejectedAddressMapper.insertMerchantRejectedAddress(reject);
                   // operationNotes = merchantOperationLogService.buildMerchantAfterServiceAddrOperationNotes(null, reject);
                }
                //TODO
                //if (StringUtils.isNotBlank(operationNotes)) {
                    /** 添加商家联系人日志 Modifier by yang.mq **/
                    //MerchantOperationLog operationLog = new MerchantOperationLog();
                   // operationLog.setMerchantCode(reject.getSupplierCode());
                   // operationLog.setOperator(loginUser);
                   // operationLog.setOperated(new Date());
                   // operationLog.setOperationType(OperationType.AFTER_SERVICE);
                  //  operationLog.setOperationNotes(operationNotes);
                   // merchantOperationLogService.saveMerchantOperationLog(operationLog);
                //}

                bool = true;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error("保存商家售后退货地址数据失败!", e);
            bool = false;
        }
        return bool;
    }

    /**
    * 根据id查询商家售后退货地址列表
    *
    * @author mei.jf
    * @date 2013-12-18
    */
    public MerchantRejectedAddressVo getMerchantRejectedAddressById(String id) {
        return rejectedAddressMapper.getMerchantRejectedAddressById(id);
    }

    /**
     * 判断商家退货地址是否已经存在
     * 
     * @throws Exception
     */
    public boolean exictRejectedAddressCount(String supplierName) {
        MerchantRejectedAddressVo vo=new MerchantRejectedAddressVo();
        vo.setSupplierName(supplierName);
        Integer count = rejectedAddressMapper.getMerchantRejectedAddressListCount(vo);
        return count==0?false:true;
    }
    
    /**
     * 转换时间格式 字符串形式
     * 
     */
    private String formDate() {
        Date da = new Date();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sim.format(da);
        return str;
    }

    
	@Override
	public PageFinder<MerchantMenu> queryMerchantMenuList(String menuName,
			Query query) {
		 Integer count = merchantMapper.queryMerchantMenuCount(menuName);
	     List<MerchantMenu> list = merchantMapper.queryMerchantMenuList(menuName, new RowBounds(query.getOffset(), query.getPageSize()));
	     PageFinder<MerchantMenu> pageFinder = new PageFinder<MerchantMenu>(query.getPage(), query.getPageSize(), count, list);
	     return pageFinder;
	}

	@Override
	public MerchantMenu getMerchantMenuById(String id) {
		return merchantMapper.getMerchantMenuById(id);
	}

	@Override
	public boolean insertMerchantMenu(MerchantMenu menu) {
		try {
			merchantMapper.insertYmcMenuResource(menu);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateMerchantMenu(MerchantMenu menu) {
		try {
			merchantMapper.updateYmcMenuResource(menu);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public PageFinder<Map<String, Object>> queryYougouAdminMerchantList(
			String userId, String merchantCode, String merchantName,
			Integer isInputYougouWarehouse, Query query) {
		Integer count = merchantMapper.queryYougouAdminMerchantCount(userId, merchantCode, merchantName, isInputYougouWarehouse);
		List<Map<String, Object>> list = merchantMapper.queryYougouAdminMerchantList(userId, merchantCode, merchantName, isInputYougouWarehouse, new RowBounds(query.getOffset(), query.getPageSize()));
		PageFinder<Map<String, Object>> pageFinder = new PageFinder<Map<String, Object>>(
				query.getPage(), query.getPageSize(), count, list);
		return pageFinder;
	}

	@Override
	public PageFinder<Map<String, Object>> queryMerchantNotHadList(
			String userId, String merchantCode, String merchantName,
			Integer isInputYougouWarehouse, Query query) {
		Integer count = merchantMapper.queryMerchantNotHadCount(userId, merchantCode, merchantName, isInputYougouWarehouse);
	     List<Map<String, Object>> list = merchantMapper.queryMerchantNotHadList(userId, merchantCode, merchantName, isInputYougouWarehouse, new RowBounds(query.getOffset(), query.getPageSize()));
	     PageFinder<Map<String, Object>> pageFinder = new PageFinder<Map<String, Object>>(query.getPage(), query.getPageSize(), count, list);
	     return pageFinder;
	}

	@Override
	public void insertExpressTemplate(ExpressTemplate vo) {
		rejectedAddressMapper.insertExpressTemplate(vo);
	}

	@Override
	public void updateExpressTemplate(ExpressTemplate vo) {
		rejectedAddressMapper.updateExpressTemplate(vo);
	}

	@Override
	public ExpressTemplate getExpressTemplateById(String id) {
		return rejectedAddressMapper.getExpressTemplateById(id);
	}

	@Override
	public boolean saveMerchantUser(MerchantUser vo) throws Exception {
		if (StringUtils.isBlank(vo.getId())) {
			vo.setId(UUIDGenerator.getUUID());
			merchantMapper.insertMerchantUser(vo);
		} else {
			merchantMapper.updateMerchantUser(vo);
		}
		
		return true;
	}
	
	@Override
	public void updateMerchantUser(MerchantUser vo) {
		merchantMapper.updateMerchantUser(vo);
	}

	@Override
	public MerchantUser getMerchantUserById(String id) {
		MerchantUser _vo = new MerchantUser();
		_vo.setId(id);
		
		List<MerchantUser> list = merchantMapper.queryMerchantUserList(_vo);
		
		return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
	}

	@Override
	public void saveMerchantOperationLog(MerchantOperationLog log) {
		merchantMapper.insertMerchantLog(log);
	}
	
	@Override
	public Double getShipmentDayHour(String startDate, String endDate) throws Exception {
		SimpleDateFormat dateSf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateTimeSf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date satrtDateTmp = dateSf.parse(startDate);
		Date satrtDateTimeTmp = dateTimeSf.parse(startDate);
		Date endDateTmp = dateSf.parse(endDate);
		Date endDateTimeTmp = dateTimeSf.parse(endDate);
		
		long differTime = (endDateTimeTmp.getTime() - satrtDateTimeTmp.getTime());
		if( endDateTimeTmp.compareTo(satrtDateTimeTmp) < 0 ){
			return -1D;
		}
		
		if(differTime / (3600000 * 24) > 30){
			return -2D;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(satrtDateTmp);
		Long noShipmentDayCount = new Long(0);
		Double shipmentDayHour = new Double(0);
		Long differHour = new Long(0);
		Long differDay = new Long(0);
		
		Integer isStartShipmentDay = shipmentDayMapper.isNotShipmentDay(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
		
		while (endDateTmp.compareTo(satrtDateTmp) >= 0 ) {
			Integer year = cal.get(Calendar.YEAR);
			Integer month = cal.get(Calendar.MONTH) + 1;
			Integer day = cal.get(Calendar.DAY_OF_MONTH);
			Integer isNotShipmentDay =  shipmentDayMapper.isNotShipmentDay(year, month, day);
			if(isNotShipmentDay > 0){//非发货日
				noShipmentDayCount++;
			}
			differDay++;
			cal.add(Calendar.DAY_OF_MONTH, 1);
			satrtDateTmp = cal.getTime();
		}
		
		if(differDay.intValue() == noShipmentDayCount.intValue()){
			return 0D;
		}
		
		if(noShipmentDayCount.intValue() == 0){
			differHour = 0L;
		} else if(isStartShipmentDay.intValue() == 1){
			differHour = (noShipmentDayCount - isStartShipmentDay ) * 24;
		}
		else{
			differHour = (differDay - noShipmentDayCount ) * 24;
		}
		

		Double litteHour = Double.valueOf(differTime % 3600000) / 3600000d;
		shipmentDayHour = Double.valueOf(differTime / 3600000 - differHour) + litteHour;
		BigDecimal bg = new BigDecimal(shipmentDayHour);
		
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getShipmentCountdownHour(Date startDate,Date endDate) throws Exception{
		if(startDate==null||endDate==null){
			throw new NullPointerException("传入参数时间为null,请检查!");
		}
		
		if( endDate.compareTo(startDate) < 0 ){
			throw new RuntimeException("传入审核时间比当前时间大,请检查!");
		}
		
		long differTime = (endDate.getTime() - startDate.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		Long noShipmentDayCount = new Long(0);
		Double shipmentDayHour = new Double(0);
		Long differHour = new Long(0);
		Long differDay = new Long(0);
		
		Integer isStartShipmentDay = shipmentDayMapper.isNotShipmentDay(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
		
		while (endDate.compareTo(startDate) >= 0 ) {
			Integer year = cal.get(Calendar.YEAR);
			Integer month = cal.get(Calendar.MONTH) + 1;
			Integer day = cal.get(Calendar.DAY_OF_MONTH);
			Integer isNotShipmentDay =  shipmentDayMapper.isNotShipmentDay(year, month, day);
			if(isNotShipmentDay > 0){//非发货日
				noShipmentDayCount++;
			}
			differDay++;
			cal.add(Calendar.DAY_OF_MONTH, 1);
			startDate = cal.getTime();
		}
		
		if(differDay.intValue() == noShipmentDayCount.intValue()){
			return 0D;
		}
		
		if(noShipmentDayCount.intValue() == 0){
			differHour = 0L;
		} else if(isStartShipmentDay.intValue() == 1){
			differHour = (noShipmentDayCount - isStartShipmentDay ) * 24;
		}
		else{
			differHour = (differDay - noShipmentDayCount ) * 24;
		}
		

		Double litteHour = Double.valueOf(differTime % 3600000) / 3600000d;
		shipmentDayHour = Double.valueOf(differTime / 3600000 - differHour) + litteHour;
		BigDecimal bg = new BigDecimal(shipmentDayHour);
		
		return 5*24-bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	@Override
	public MerchantBankInfoVo  queryMerchantBankInfo(String merchantCode) {
		logger.error("queryMerchantBankInfo 入参："+merchantCode);
		try {
			List<Map<String,Object>> bankInfoList=merchantMapper.queryMerchantBankInfo(merchantCode);
			if(bankInfoList!=null &&  bankInfoList.size()>0){
				MerchantBankInfoVo  vo=new MerchantBankInfoVo();
				String billingBank="";    //开票开户行
				String billingAccount=""; //开票银行账号
				String settlementBank=""; //结算开户行
				String settlementAccount="";  //结算银行账号
				//拼装MerchantBankInfoVoVo
				for (int i = 0; i <bankInfoList.size(); i++) {
					Map<String,Object>  bankMap=bankInfoList.get(i);
					if(i==0){
						vo.setId(((String)bankMap.get("id")).trim());
						
						String supplier_code=(String)bankMap.get("supplier_code");
						
						vo.setSupplierCode(StringUtils.isNotBlank(supplier_code)?supplier_code.trim():"");
						
						String supplier=(String)bankMap.get("supplier");
						vo.setSupplier(StringUtils.isNotBlank(supplier)?supplier.trim():"");
						
						String posSourceNo=(String)bankMap.get("pos_source_no");
						vo.setPosSourceNo(StringUtils.isNotBlank(posSourceNo)?posSourceNo.trim():"");
						String is_valid=(String)bankMap.get("is_valid");
						if(StringUtils.isNotBlank(is_valid)){
							vo.setIsValid(Integer.valueOf(is_valid));
						}
						
						String setofbooksCode=(String)bankMap.get("setofbooksCode");
						vo.setSetofbooksCode(StringUtils.isNotBlank(setofbooksCode)?setofbooksCode.trim():"");
						
						String setofbooksName=(String)bankMap.get("set_of_books_name");
						vo.setSetofbooksName(StringUtils.isNotBlank(setofbooksName)?setofbooksName.trim():"");
						
						String supplier_type=(String)bankMap.get("supplier_type");
						vo.setSupplierType(StringUtils.isNotBlank(supplier_type)?supplier_type.trim():"");
					
						String balance_trader_code=(String)bankMap.get("balance_trader_code");
						vo.setBalanceTraderCode(StringUtils.isNotBlank(balance_trader_code)?balance_trader_code.trim():"");
						
						String balance_trader_name=(String)bankMap.get("balance_trader_name");
						vo.setBalanceTraderName(StringUtils.isNotBlank(balance_trader_name)?balance_trader_name.trim():"");
						
						String sub_bank=(String)bankMap.get("sub_bank");
						vo.setContact(StringUtils.isNotBlank(sub_bank)?sub_bank.trim():"");
						
						String bank=(String)bankMap.get("bank");
						settlementBank=StringUtils.isNotBlank(bank)?bank.trim():"";
						
						String account=(String)bankMap.get("account");
						settlementAccount=StringUtils.isNotBlank(account)?account.trim():"";
					}else if(i==1){
						String bank=(String)bankMap.get("bank");
						billingBank=StringUtils.isNotBlank(bank)?bank.trim():"";
						String account=(String)bankMap.get("account");
						billingAccount=StringUtils.isNotBlank(account)?account.trim():"";
					}
				}
				//判断开票银行和结算银行账号是否相同，如果相同合并信息
				if(settlementAccount.equals(billingAccount)  || billingAccount.equals("") ){
					List<String> bankList=new ArrayList<String>();    //开户行
					bankList.add(settlementBank);
					vo.setBankList(bankList);
					
					List<String> accountList=new ArrayList<String>(); //银行账号
					accountList.add(settlementAccount);
					vo.setAccountList(accountList);
				}else{
					List<String> bankList=new ArrayList<String>();    //开户行
					bankList.add(settlementBank);
					bankList.add(billingBank);
					vo.setBankList(bankList);
					
					List<String> accountList=new ArrayList<String>(); //银行账号
					accountList.add(settlementAccount);
					accountList.add(billingAccount);
					vo.setAccountList(accountList);
				}
				return vo;
			}
		} catch (Exception e) {
			logger.error("queryMerchantBankInfo 出现异常：",e);
		}
		return null;
	}
}
