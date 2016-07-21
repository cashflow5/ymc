package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 每个接口的配置明细
 * 
 * @author huang.tao
 * 
 */
public class MonitorAppKeyDetailVo implements Serializable {

    private static final long serialVersionUID = 9004099841868426013L;

    // 频率上限
    private Integer frequency;
    // 频率单位 1：次/时 2：次/分钟 3：次/秒
    private Integer frequencyUnit;
    // 日调用次数上限
    private Integer callNum;

    // 锁定控制参数
    // 单接口日调用次数超限次数
    private Integer dataFlowLimit;
    // 单接口频率超限次数
    private Integer frequencyLimit;
    // 如果超限，该接口被锁定的时长（小时）
    private Float lockTime;

    // 预警控制参数
    // 单接口日调用预警次数
    private Integer dataFlowEarlyWarn;
    // 单接口频率预警次数
    private Integer frequencyEarlyWarn;
    private Date createTime;
    private Date updateTime;

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getFrequencyUnit() {
        return frequencyUnit;
    }

    public void setFrequencyUnit(Integer frequencyUnit) {
        this.frequencyUnit = frequencyUnit;
    }

    public Integer getCallNum() {
        return callNum;
    }

    public void setCallNum(Integer callNum) {
        this.callNum = callNum;
    }

    public Integer getDataFlowLimit() {
        return dataFlowLimit;
    }

    public void setDataFlowLimit(Integer dataFlowLimit) {
        this.dataFlowLimit = dataFlowLimit;
    }

    public Integer getFrequencyLimit() {
        return frequencyLimit;
    }

    public void setFrequencyLimit(Integer frequencyLimit) {
        this.frequencyLimit = frequencyLimit;
    }

    public Float getLockTime() {
        return lockTime;
    }

    public void setLockTime(Float lockTime) {
        this.lockTime = lockTime;
    }

    public Integer getDataFlowEarlyWarn() {
        return dataFlowEarlyWarn;
    }

    public void setDataFlowEarlyWarn(Integer dataFlowEarlyWarn) {
        this.dataFlowEarlyWarn = dataFlowEarlyWarn;
    }

    public Integer getFrequencyEarlyWarn() {
        return frequencyEarlyWarn;
    }

    public void setFrequencyEarlyWarn(Integer frequencyEarlyWarn) {
        this.frequencyEarlyWarn = frequencyEarlyWarn;
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
