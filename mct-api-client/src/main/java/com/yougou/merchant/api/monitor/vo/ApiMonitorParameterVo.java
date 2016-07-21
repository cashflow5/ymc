package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;

public class ApiMonitorParameterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    //单接口日调用超限次数
    private String dataFlowRate;

    //单接口超限频率
    private String frequencyRate;

    //锁定接口小时数
    private String frequencyOutLockTime;

    //单接口日调用次数预警
    private String simpleImplOneDayRate;

    //单接口频率预警
    private String simpleImplFrequencyRate;

    //调用成功率预警
    private String successRate;

    //AppKey日调用次数预警
    private String appKeyCallFrequencyRate;

    //无效AppKey发送IP
    private String invalidAppKeyRequest;

    public String getDataFlowRate() {
        return dataFlowRate;
    }

    public void setDataFlowRate(String dataFlowRate) {
        this.dataFlowRate = dataFlowRate;
    }

    public String getFrequencyRate() {
        return frequencyRate;
    }

    public void setFrequencyRate(String frequencyRate) {
        this.frequencyRate = frequencyRate;
    }

    public String getFrequencyOutLockTime() {
        return frequencyOutLockTime;
    }

    public void setFrequencyOutLockTime(String frequencyOutLockTime) {
        this.frequencyOutLockTime = frequencyOutLockTime;
    }

    public String getSimpleImplOneDayRate() {
        return simpleImplOneDayRate;
    }

    public void setSimpleImplOneDayRate(String simpleImplOneDayRate) {
        this.simpleImplOneDayRate = simpleImplOneDayRate;
    }

    public String getSimpleImplFrequencyRate() {
        return simpleImplFrequencyRate;
    }

    public void setSimpleImplFrequencyRate(String simpleImplFrequencyRate) {
        this.simpleImplFrequencyRate = simpleImplFrequencyRate;
    }

    public String getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(String successRate) {
        this.successRate = successRate;
    }

    public String getAppKeyCallFrequencyRate() {
        return appKeyCallFrequencyRate;
    }

    public void setAppKeyCallFrequencyRate(String appKeyCallFrequencyRate) {
        this.appKeyCallFrequencyRate = appKeyCallFrequencyRate;
    }

    public String getInvalidAppKeyRequest() {
        return invalidAppKeyRequest;
    }

    public void setInvalidAppKeyRequest(String invalidAppKeyRequest) {
        this.invalidAppKeyRequest = invalidAppKeyRequest;
    }
}
