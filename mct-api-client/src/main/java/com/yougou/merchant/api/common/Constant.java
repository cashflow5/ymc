/**
 * 
 */
package com.yougou.merchant.api.common;

/**
 * 
 * 
 * @author huang.tao
 *
 */
public class Constant {
	//仓库编码
	 public final static String  WAREHOUSECODE = "warehouseCode";
	//仓库名称
	 public final static String  WAREHOUSENAME = "warehouseName";
	
    // 单接口日调用超限次数
     public final static String  DATA_FLOW_RATE = "DataFlowRate";
     public final static String  KEY_DATA_FLOW_RATE = "com.yougou.api.monitor.MonitorConfigController.dataFlowRate";
    // 单接口超限频率
     public final static String  FREQUENCY_RATE = "FrequencyRate";
     public final static String  KEY_FREQUENCY_RATE = "com.yougou.api.monitor.MonitorConfigController.frequencyRate";
    // 锁定接口小时数
     public final static String  FREQUENCY_OUTLOCK_TIME = "FrequencyOutLockTime";
     public final static String  KEY_FREQUENCY_OUTLOCK_TIME = "com.yougou.api.monitor.MonitorConfigController.frequencyOutLockTime";
    // 单接口日调用次数
     public final static String  SIMPLEIMPL_ONEDAY_RATE = "SimpleImplOneDayRate";
     public final static String  KEY_SIMPLEIMPL_ONEDAY_RATE = "com.yougou.api.monitor.MonitorConfigController.simpleImplOneDayRate";
    // 单接口频率预警
     public final static String  SIMPLEIMPL_FREQUENCY_RATE = "SimpleImplFrequencyRate";
     public final static String  KEY_SIMPLEIMPL_FREQUENCY_RATE = "com.yougou.api.monitor.MonitorConfigController.simpleImplFrequencyRate";
    // 调用成功率预警
     public final static String  SUCCESS_RATE = "SuccessRate";
     public final static String  KEY_SUCCESS_RATE = "com.yougou.api.monitor.MonitorConfigController.successRate";
    // AppKey日调用次数预警
     public final static String  APPKEY_CALLFREQUENCY_RATE = "AppKeyCallFrequencyRate";
     public final static String  KEY_APPKEY_CALLFREQUENCY_RATE = "com.yougou.api.monitor.MonitorConfigController.appKeyCallFrequencyRate";
    // 无效AppKey发送次数，则该IP被封
     public final static String  INVALID_APPKEY_REQUEST = "InvalidAppKeyRequest";
     public final static String  KEY_INVALID_APPKEY_REQUEST = "com.yougou.api.monitor.MonitorConfigController.invalidAppKeyRequest";
 
     /**
      * 合同状态（1新建 2待审核 3业务审核通过 4业务审核不通过 5财务审核通过 6财务审核不通过 7生效 8已过期）
      */
     public final static char  CONTRACT_STATUS_ADD = '1';
     /**
      *合同状态（1新建 2待审核 3业务审核通过 4业务审核不通过 5财务审核通过 6财务审核不通过 7生效 8已过期） 
      */
     public final static char  CONTRACT_STATUS_NEED_AUDIT = '2';
     /**
      * 合同状态（1新建 2待审核 3业务审核通过 4业务审核不通过 5财务审核通过 6财务审核不通过 7生效 8已过期）
      */
     public final static char  CONTRACT_STATUS_AUDIT_SUCCESS = '3';
     /***
      *合同状态（1新建 2待审核 3业务审核通过 4业务审核不通过 5财务审核通过 6财务审核不通过 7生效 8已过期） 
      */
     public final static char  CONTRACT_STATUS_AUDIT_FAILTURE = '4';
     /**
      *合同状态（1新建 2待审核 3业务审核通过 4业务审核不通过 5财务审核通过 6财务审核不通过 7生效 8已过期） 
      */
     public final static char  CONTRACT_STATUS_FINANCE_SUCCESS = '5';
     /**
      * 合同状态（1新建 2待审核 3业务审核通过 4业务审核不通过 5财务审核通过 6财务审核不通过 7生效 8已过期）
      */
     public final static char  CONTRACT_STATUS_FINANCE_FAILTURE = '6';
     /**
      * 合同状态（1新建 2待审核 3业务审核通过 4业务审核不通过 5财务审核通过 6财务审核不通过 7生效 8已过期）
      */
     public final static char  CONTRACT_STATUS_USED = '7';
     /**
      * 合同状态（1新建 2待审核 3业务审核通过 4业务审核不通过 5财务审核通过 6财务审核不通过 7生效 8已过期）
      */
     public final static char  CONTRACT_STATUS_OVER_DATE = '8';
     
     public static String getStatus(char code){
    	 switch(code){
    	   case CONTRACT_STATUS_ADD :return "新建"; 
    	   case CONTRACT_STATUS_NEED_AUDIT :return "待审核";
    	   case CONTRACT_STATUS_AUDIT_SUCCESS :return "业务审核通过";
    	   case CONTRACT_STATUS_AUDIT_FAILTURE :return "业务审核不通过";
    	   case CONTRACT_STATUS_FINANCE_SUCCESS :return "财务审核通过";
    	   case CONTRACT_STATUS_FINANCE_FAILTURE :return "财务审核不通过";
    	   case CONTRACT_STATUS_USED :return "生效";
    	   case CONTRACT_STATUS_OVER_DATE :return "已过期";
    	 }
    	 return "";
    	 
     }
     
     /* 招商供应商之外的两种merchant_code */
     public final static String MERCHANT_CODE_BELLE = "SPYG";
     
     public final static String MERCHANT_CODE_KOREA = "KOREA";
     /* 招商供应商之海外直发 */
     public final static String DELIVERY_TYPE_OVERSEAS = "2";
     
     
     
}
