package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 供应商更新历史
 * @author zhubin
 * date：2012-1-5 下午5:25:53
 */
public class SupplierUpdateHistory implements Serializable {
	
	private static final long serialVersionUID = 1308370575212810839L;

	//主键ID
	private String id;
	
	private String supplierId;
	
	//操作人
	private String operator;
	
	//操作时间
	private Date operatorTime;
	
	//操作
	private String processing;
	
	//字段
	private String field;
	
	//修改前
	private String updateBefore;
	
	//修改后
	private String updateAfter;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getUpdateBefore() {
		return updateBefore;
	}

	public void setUpdateBefore(String updateBefore) {
		this.updateBefore = updateBefore;
	}

	public String getUpdateAfter() {
		return updateAfter;
	}

	public void setUpdateAfter(String updateAfter) {
		this.updateAfter = updateAfter;
	}



	
}
