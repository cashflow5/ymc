package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 首尔直发退回单主信息
 * @author zhang.f1
 *
 */
public class BackOrderVo implements Serializable {

	@Override
	public String toString() {
		return "BackOrderVo [id=" + id + ", backCode=" + backCode
				+ ", backWarehHouseCode=" + backWarehHouseCode + ", status="
				+ status + ", receiveStatus=" + receiveStatus + ", backTime="
				+ backTime + ", planBackTotalCount=" + planBackTotalCount
				+ ", alreadyBackTotalCount=" + alreadyBackTotalCount
				+ ", backOperator=" + backOperator + ", detailList="
				+ detailList + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id; //退回发货仓单据ID
	private String backCode; //退回单号
	private String backWarehHouseCode; //原发货仓库编码
	private String status; //退回单状态(确认，已确认),从wms流入招商系统的都是已确认
	private Integer receiveStatus; //收货状态(0 待确认收货，1 已确认收货 2 部分确认收货)
    private String backTime; //退货时间(wms审核时间)
    private Integer planBackTotalCount; //计划退回总数：明细表计划退回汇总
    private Integer alreadyBackTotalCount; //已退回总数：明细表已退回汇总
    private String backOperator; //退回人
    private  List<BackOrderDetailVo> detailList = new ArrayList<BackOrderDetailVo>(); //退回单详情
    
    
    
	public BackOrderVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BackOrderVo(String id, String backCode, String backWarehHouseCode,
			String status, Integer receiveStatus, String backTime,
			Integer planBackTotalCount, Integer alreadyBackTotalCount,
			String backOperator, ArrayList<BackOrderDetailVo> detailList) {
		super();
		this.id = id;
		this.backCode = backCode;
		this.backWarehHouseCode = backWarehHouseCode;
		this.status = status;
		this.receiveStatus = receiveStatus;
		this.backTime = backTime;
		this.planBackTotalCount = planBackTotalCount;
		this.alreadyBackTotalCount = alreadyBackTotalCount;
		this.backOperator = backOperator;
		this.detailList = detailList;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBackCode() {
		return backCode;
	}
	public void setBackCode(String backCode) {
		this.backCode = backCode;
	}
	public String getBackWarehHouseCode() {
		return backWarehHouseCode;
	}
	public void setBackWarehHouseCode(String backWarehHouseCode) {
		this.backWarehHouseCode = backWarehHouseCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(Integer receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	public String getBackTime() {
		return backTime;
	}
	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}
	public Integer getPlanBackTotalCount() {
		return planBackTotalCount;
	}
	public void setPlanBackTotalCount(Integer planBackTotalCount) {
		this.planBackTotalCount = planBackTotalCount;
	}
	public Integer getAlreadyBackTotalCount() {
		return alreadyBackTotalCount;
	}
	public void setAlreadyBackTotalCount(Integer alreadyBackTotalCount) {
		this.alreadyBackTotalCount = alreadyBackTotalCount;
	}
	public String getBackOperator() {
		return backOperator;
	}
	public void setBackOperator(String backOperator) {
		this.backOperator = backOperator;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<BackOrderDetailVo> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<BackOrderDetailVo> detailList) {
		this.detailList = detailList;
	}
}
