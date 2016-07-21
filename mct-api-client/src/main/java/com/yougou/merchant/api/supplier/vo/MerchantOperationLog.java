/**
 * 
 */
package com.yougou.merchant.api.supplier.vo;

import java.io.Serializable;
import java.util.Date;

import com.yougou.merchant.api.common.UUIDGenerator;

/**
 * 商家/合同操作日志
 * 
 * @author huang.tao
 *
 */
public class MerchantOperationLog implements Serializable {
	
	private static final long serialVersionUID = -4013231138724357917L;
	
	private String id;
	private String operator;// 操作人
	private Date operated;// 操作时间
	private String operationType;// 操作类型
	private String operationNotes;// 操作备注
	private String merchantCode;// 商家编码
	private String type;// 日志类型：1 商家日志 2 合同日志
	private String contractNo;// 合同编号
	private String supplierId;//商家Id
	private String userId;//商家账号用户Id
	private String remark;//填写的备注
	
	public MerchantOperationLog(){
		this.id = UUIDGenerator.getUUID();
		this.operated = new Date();
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getOperator() {
		return operator;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}


	public Date getOperated() {
		return operated;
	}


	public void setOperated(Date operated) {
		this.operated = operated;
	}


	public String getOperationType() {
		return operationType;
	}


	public void setOperationType(OperationType operationType) {
		this.operationType = operationType.name();
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getContractNo() {
		return contractNo;
	}


	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getOperationNotes() {
		return operationNotes;
	}


	public void setOperationNotes(String operationNotes) {
		this.operationNotes = operationNotes;
	}


	public String getMerchantCode() {
		return merchantCode;
	}


	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}


	public String getSupplierId() {
		return supplierId;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}


	/**
	 * 操作类型
	 * 
	 * @author luo.q
	 *
	 */
	public static enum OperationType {
		BASIC_DATA("商家资料"), 
		ACCOUNT("商家帐户"), 
		CONTRACT("合同"), 
		CONTACT("联系人"),
		AFTER_SERVICE("售后"),
		API("API"),
		AppKey_ADD("添加AppKey"),
		APPKEY_AUTH("授权AppKey"), 
		APPKEY_BINDING("绑定AppKey"),
		APPKEY_UNBUND("解绑AppKey"),
		APPKEY_ENABLED("启用AppKey"), 
		APPKEY_DISABLE("禁用AppKey"),
		AUTH_ADD("添加资源"),
		AUTH_UPDATE("修改资源"),
		AUTH_DELETE("删除资源"),
		PUNISHORDER("违规订单"),
		UPDATE_NATURAL("更新资质及品牌授权"),
		ADD_MERCHANT("添加供应商"),
		UPDATE_MERCHANT("修改供应商"),
		FINANCE_AUDIT_MERCHANT("财务审核供应商"),
		BUSINESS_AUDIT_MERCHANT("业务审核供应商"),
		VALID_MERCHANT("启用供应商"),
		ENVALID_MERCHANT("停用供应商"),
		CATEGORY_AUTH("品类授权"),
		PUNISH_RULE("编辑处罚规则"),
		ADD_CONTRACT("添加供应商合同"),
		UPDATE_CONTRACT("修改供应商合同"),
		RENEW_MERCHANT("商家续签创建合同"),
		UPDATE_RENEW_MERCHANT("商家续签修改合同"),
		SHOP("旗舰店")
		;
		public void setDescription(String description) {
			this.description = description;
		}

		private String description;

		private OperationType(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}


	public String getUserId() {
		return userId;
	}

	public String getRemark() {
		return remark;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


}
