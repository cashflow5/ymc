 
package com.yougou.merchant.api.supplier.vo;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.yougou.merchant.api.common.UUIDGenerator;

public class MerchantContractUpdateHistory {
    private String id;

    private String operator;

    private Date operationTime;

    private String processing;

    private String updateField;

    private String updateBefore;

    private String updateAfter;

    private String contractNo;

    private String remark;

    private String type;

    private String supplierId;
    
    public MerchantContractUpdateHistory(){
    	this.id = UUIDGenerator.getUUID();
    	this.operationTime = new Date();
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getProcessing() {
        return processing;
    }

    public void setProcessing(String processing) {
        this.processing = processing == null ? null : processing.trim();
    }

    public String getUpdateField() {
        return updateField;
    }

    public void setUpdateField(String updateField) {
        this.updateField = updateField == null ? null : updateField.trim();
    }

    public String getUpdateBefore() {
        return updateBefore;
    }

    public void setUpdateBefore(String updateBefore) {
        this.updateBefore = updateBefore == null ? null : updateBefore.trim();
    }

    public String getUpdateAfter() {
        return updateAfter;
    }

    public void setUpdateAfter(String updateAfter) {
        this.updateAfter = updateAfter == null ? null : updateAfter.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
    

    public String getSupplierId() {
		return supplierId;
	}


	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}


	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
    
    /**
	 * 操作类型
	 * 
	 * @author yang.mq
	 *
	 */
	public static enum ProcessingType {
		CREATE_CONTRACT("创建合同"),BUSINESS_AUDIT("业务审核合同"),BUSINESS_AUDIT_MERCHANT("业务审核商家"),RENEW_MERCHANT("商家续签创建合同"),
		UPDATE_RENEW_MERCHANT("商家续签修改合同"),
		FINANCE_AUDIT("财务审核合同"),FINANCE_AUDIT_MERCHANT("财务审核商家"),CONTRACT_EXPIRE("合同时间到期"),CONTRACT_EFFECTIVE("合同生效"),
		BUSINESS_EFFECTIVE("业务已生效"),SUBMIT_FINANCE("提交财务审核"),CREATE_SUBMIT("创建并提交财务审核"),
		USER_STARTUP("商家启用"),USER_STOP("商家停用"),RECALL_AUDIT("撤销审核");
		private String description;

		private ProcessingType(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}
}