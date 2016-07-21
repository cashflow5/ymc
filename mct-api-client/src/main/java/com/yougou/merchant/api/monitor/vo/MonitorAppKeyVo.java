package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * appkey全局的配置参数和每个接口的配置明细
 * 
 * @author huang.tao
 * 
 */
public class MonitorAppKeyVo implements Serializable {

    private static final long serialVersionUID = 9004099841868426013L;

    // 模板编码
    private String templateNo;

    // appkey的日调用总次数
    private Integer dataFlow;

    // appkey的日调用总预警次数
    private Integer dataFlowEarlyWarn;

    // appkey的调用成功率
    private Integer successRate;

    // IP无效AppKey发送请求次数
    private Integer invalidAppKeyRequest;

    // 每个api接口的参数配置
    private Map<String, MonitorAppKeyDetailVo> monitorAppKeyDetailVo;

    private Date createTime;
    private Date updateTime;

    public String getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(String templateNo) {
        this.templateNo = templateNo;
    }

    public Integer getDataFlow() {
        return dataFlow;
    }

    public void setDataFlow(Integer dataFlow) {
        this.dataFlow = dataFlow;
    }

    public Integer getDataFlowEarlyWarn() {
        return dataFlowEarlyWarn;
    }

    public void setDataFlowEarlyWarn(Integer dataFlowEarlyWarn) {
        this.dataFlowEarlyWarn = dataFlowEarlyWarn;
    }

    public Integer getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Integer successRate) {
        this.successRate = successRate;
    }

    public Integer getInvalidAppKeyRequest() {
        return invalidAppKeyRequest;
    }

    public void setInvalidAppKeyRequest(Integer invalidAppKeyRequest) {
        this.invalidAppKeyRequest = invalidAppKeyRequest;
    }

    public Map<String, MonitorAppKeyDetailVo> getMonitorAppKeyDetailVo() {
        return monitorAppKeyDetailVo;
    }

    public void setMonitorAppKeyDetailVo(Map<String, MonitorAppKeyDetailVo> monitorAppKeyDetailVo) {
        this.monitorAppKeyDetailVo = monitorAppKeyDetailVo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
