/**
 * 
 */
package com.yougou.merchant.api.monitor.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 按AppKey统计汇总（天维度）
 * 
 * @author huang.tao
 *
 */
public class AnalyzeAppkeyDay implements Serializable {

	private static final long serialVersionUID = -496392244632161022L;
	
	private String id;
	//appKe
	private String appKey;
	//创建时间
	private Date createTime;
	//统计时间段
	private String timeQuantum;
	//调用次数
	private Integer callCount;
	//有效调用次数
	private Integer validCallCount;
	//成功调用次数
	private Integer sucessCallCount;
	//失败调用次数
	private Integer failCallCount;
	
	//最高频率（次/时）
	private Integer maxFrequency;
	
	//下单量
	private Integer orderQty;
	//下单金额
	private Double orderMoney;
	
	private String appKeyHolder;
	//平均频率
	private Integer avgFrequency;
	//Ip连接数
	private Integer ipNums;
	//成功率
	private Double successRate;
	
	
	//临时字段（排序字段）
	private Integer rankingCall;
	private Integer rankingFrequency;
	private Integer rankingOrderQty;
	private Integer rankingOrderMoney;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTimeQuantum() {
		return timeQuantum;
	}
	public void setTimeQuantum(String timeQuantum) {
		this.timeQuantum = timeQuantum;
	}
	public Integer getCallCount() {
		return callCount;
	}
	public void setCallCount(Integer callCount) {
		this.callCount = callCount;
	}
	public Integer getValidCallCount() {
		return validCallCount;
	}
	public void setValidCallCount(Integer validCallCount) {
		this.validCallCount = validCallCount;
	}
	public Integer getSucessCallCount() {
		return sucessCallCount;
	}
	public void setSucessCallCount(Integer sucessCallCount) {
		this.sucessCallCount = sucessCallCount;
	}
	public Integer getFailCallCount() {
		return failCallCount;
	}
	public void setFailCallCount(Integer failCallCount) {
		this.failCallCount = failCallCount;
	}
	public Integer getMaxFrequency() {
		return maxFrequency;
	}
	public void setMaxFrequency(Integer maxFrequency) {
		this.maxFrequency = maxFrequency;
	}
	public Integer getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}
	public Double getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}
	public String getAppKeyHolder() {
		return appKeyHolder;
	}
	public void setAppKeyHolder(String appKeyHolder) {
		this.appKeyHolder = appKeyHolder;
	}
	public Integer getAvgFrequency() {
		return avgFrequency;
	}
	public void setAvgFrequency(Integer avgFrequency) {
		this.avgFrequency = avgFrequency;
	}
	public Integer getIpNums() {
		return ipNums;
	}
	public void setIpNums(Integer ipNums) {
		this.ipNums = ipNums;
	}
	public Double getSuccessRate() {
		return successRate;
	}
	public void setSuccessRate(Double successRate) {
		this.successRate = successRate;
	}
	public Integer getRankingCall() {
		return rankingCall;
	}
	public void setRankingCall(Integer rankingCall) {
		this.rankingCall = rankingCall;
	}
	public Integer getRankingFrequency() {
		return rankingFrequency;
	}
	public void setRankingFrequency(Integer rankingFrequency) {
		this.rankingFrequency = rankingFrequency;
	}
	public Integer getRankingOrderQty() {
		return rankingOrderQty;
	}
	public void setRankingOrderQty(Integer rankingOrderQty) {
		this.rankingOrderQty = rankingOrderQty;
	}
	public Integer getRankingOrderMoney() {
		return rankingOrderMoney;
	}
	public void setRankingOrderMoney(Integer rankingOrderMoney) {
		this.rankingOrderMoney = rankingOrderMoney;
	}
	
	
}
