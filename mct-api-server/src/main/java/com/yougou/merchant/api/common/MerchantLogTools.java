/**
 * 
 */
package com.yougou.merchant.api.common;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.yougou.merchant.api.supplier.vo.ContactsVo;
import com.yougou.merchant.api.supplier.vo.ContractVo;
import com.yougou.merchant.api.supplier.vo.MerchantUser;
import com.yougou.merchant.api.supplier.vo.RejectedAddressVo;
import com.yougou.merchant.api.supplier.vo.SupplierVo;

/**
 * 商家信息操作日志工具类
 * 
 * @author huang.tao
 *
 */
public class MerchantLogTools {
	
	private static final Map<String, String> BASIC_DATA_TRANSLATABLE_FIELDS = new HashMap<String, String>();
	
	private static final Map<String, String> ACCOUNT_TRANSLATABLE_FIELDS = new HashMap<String, String>();
	
	private static final Map<String, String> CONTACT_TRANSLATABLE_FIELDS = new HashMap<String, String>();
	
	private static final Map<String, String> CONTRACT_TRANSLATABLE_FIELDS = new HashMap<String, String>();
	
	private static final Map<String, String> AFTER_SERVICE_TRANSLATABLE_FIELDS = new HashMap<String, String>();
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	static {
		// 商家资料
		BASIC_DATA_TRANSLATABLE_FIELDS.put("supplier", "供应商名称");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("supplierType", "供应商类型");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("isInputYougouWarehouse", "仓库类型");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("setOfBooksName", "成本账套名称");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("taxRate", "税率");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("shipmentType", "验收差异处理方式");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("couponsAllocationProportion", "优惠券分摊比例");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("contact", "银行开户名");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("businessLocal", "营业执照所在地");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("account", "公司银行帐号");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("businessValidity", "营业执照有效期");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("subBank", "开户行支行名称");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("tallageNo", "税务登记证号");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("bankLocal", "开户行银行所在地");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("institutional", "组织机构代码");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("businessLicense", "营业执照号");
		BASIC_DATA_TRANSLATABLE_FIELDS.put("taxpayer", "纳税人识别号");
		// 商家帐户
		ACCOUNT_TRANSLATABLE_FIELDS.put("userName", "帐户名称");
		ACCOUNT_TRANSLATABLE_FIELDS.put("loginName", "登录名称");
		ACCOUNT_TRANSLATABLE_FIELDS.put("password", "登录密码");
		ACCOUNT_TRANSLATABLE_FIELDS.put("status", "是否启用");
		ACCOUNT_TRANSLATABLE_FIELDS.put("isAdministrator", "是否商家管理员");
		ACCOUNT_TRANSLATABLE_FIELDS.put("deleteFlag", "是否删除");
		ACCOUNT_TRANSLATABLE_FIELDS.put("isYougouAdmin", "是否优购管理员");
		// 联系人
		CONTACT_TRANSLATABLE_FIELDS.put("contact", "姓名");
		CONTACT_TRANSLATABLE_FIELDS.put("type", "类型");
		CONTACT_TRANSLATABLE_FIELDS.put("telePhone", "电话号码");
		CONTACT_TRANSLATABLE_FIELDS.put("mobilePhone", "手机号码");
		CONTACT_TRANSLATABLE_FIELDS.put("fax", "传真号码");
		CONTACT_TRANSLATABLE_FIELDS.put("email", "电子邮箱");
		CONTACT_TRANSLATABLE_FIELDS.put("address", "地址");
		// 合同
		CONTRACT_TRANSLATABLE_FIELDS.put("contractNo", "合同编号");
		CONTRACT_TRANSLATABLE_FIELDS.put("effectiveDate", "有效日期(起)");
		CONTRACT_TRANSLATABLE_FIELDS.put("failureDate", "有效日期(止)");
		CONTRACT_TRANSLATABLE_FIELDS.put("clearingForm", "结算方式");
		// 售后
		AFTER_SERVICE_TRANSLATABLE_FIELDS.put("supplierName", "商家名称");
		AFTER_SERVICE_TRANSLATABLE_FIELDS.put("consigneeName", "收货人姓名");
		AFTER_SERVICE_TRANSLATABLE_FIELDS.put("consigneePhone", "收货人手机");
		AFTER_SERVICE_TRANSLATABLE_FIELDS.put("consigneeTell", "收货人电话");
		AFTER_SERVICE_TRANSLATABLE_FIELDS.put("warehousePostcode", "收货人邮编");
		AFTER_SERVICE_TRANSLATABLE_FIELDS.put("warehouseArea", "收货人地区");
		AFTER_SERVICE_TRANSLATABLE_FIELDS.put("warehouseAdress", "收货人地址");
	}
	
	public static String buildMerchantBasicDataOperationNotes(SupplierVo source, SupplierVo target) throws Exception {
		if (target == null) {
			throw new NullPointerException("target");
		}
		if (source == null) {
			return "新建商家联系人";
		}
		
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : BASIC_DATA_TRANSLATABLE_FIELDS.entrySet()) {
			Object o1 = PropertyUtils.getProperty(source, entry.getKey());
			Object o2 = PropertyUtils.getProperty(target, entry.getKey());
			if (!ObjectUtils.equals(o1, o2)) {
				if (StringUtils.equals("isInputYougouWarehouse", entry.getKey())) {
					CooperationModel[] cooperationModels = CooperationModel.values();
					o1 = cooperationModels[(Integer) o1].getDescription();
					o2 = cooperationModels[(Integer) o2].getDescription();
				} else if (StringUtils.equals("shipmentType", entry.getKey())) {
					o1 = ObjectUtils.equals(NumberUtils.INTEGER_ONE, o1) ? "销退" : "验退";
					o2 = ObjectUtils.equals(NumberUtils.INTEGER_ONE, o2) ? "销退" : "验退";
				}
				sb.append(MessageFormat.format("将“{0}”由【{1}】修改为【{2}】{3}", entry.getValue(), o1, o2, LINE_SEPARATOR));
			}
		}
		return sb.toString();
	}
	
	public static String buildMerchantAccountOperationNotes(MerchantUser source, MerchantUser target) throws Exception {
		if (target == null) {
			throw new NullPointerException("target");
		}
		if (source == null) {
			return "新建商家帐号";
		}
		
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : ACCOUNT_TRANSLATABLE_FIELDS.entrySet()) {
			Object o1 = PropertyUtils.getProperty(source, entry.getKey());
			Object o2 = PropertyUtils.getProperty(target, entry.getKey());
			if (!ObjectUtils.equals(o1, o2)) {
				if (StringUtils.equals("status", entry.getKey())) {
					o1 = ObjectUtils.equals(NumberUtils.INTEGER_ONE, o1) ? "启用" : "锁定";
					o2 = ObjectUtils.equals(NumberUtils.INTEGER_ONE, o2) ? "启用" : "锁定";
				} else if (StringUtils.equals("isAdministrator", entry.getKey()) || StringUtils.equals("deleteFlag", entry.getKey()) || StringUtils.equals("isYougouAdmin", entry.getKey())) {
					o1 = ObjectUtils.equals(NumberUtils.INTEGER_ONE, o1) ? "是" : "不是";
					o2 = ObjectUtils.equals(NumberUtils.INTEGER_ONE, o2) ? "是" : "不是";
				}
				sb.append(MessageFormat.format("将“{0}”由【{1}】修改为【{2}】{3}", entry.getValue(), o1, o2, LINE_SEPARATOR));
			}
		}
		return sb.toString();
	}

	public static String buildContactOperationNotes(ContactsVo source, ContactsVo target) throws Exception {
		if (target == null) {
			throw new NullPointerException("target");
		}
		if (source == null) {
			return "新建商家联系人";
		}
		
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : CONTACT_TRANSLATABLE_FIELDS.entrySet()) {
			Object o1 = PropertyUtils.getProperty(source, entry.getKey());
			Object o2 = PropertyUtils.getProperty(target, entry.getKey());
			if (!ObjectUtils.equals(o1, o2)) {
				if (StringUtils.equals("type", entry.getKey())) {
					o1 = ObjectUtils.equals(NumberUtils.INTEGER_ONE, o1) ? "业务" : ObjectUtils.equals(2, o1) ? "售后" : ObjectUtils.equals(3, o1) ? "仓储" : ObjectUtils.equals(4, o1) ? "财务" : ObjectUtils.equals(5, o1) ? "技术" : "未知";
					o2 = ObjectUtils.equals(NumberUtils.INTEGER_ONE, o2) ? "业务" : ObjectUtils.equals(2, o2) ? "售后" : ObjectUtils.equals(3, o2) ? "仓储" : ObjectUtils.equals(4, o2) ? "财务" : ObjectUtils.equals(5, o2) ? "技术" : "未知";
				}
				sb.append(MessageFormat.format("将“{0}”由【{1}】修改为【{2}】{3}", entry.getValue(), o1, o2, LINE_SEPARATOR));
			}
		}
		return sb.toString();
	}

	public static String builContractOperationNotes(ContractVo source, ContractVo target) throws Exception {
		if (target == null) {
			throw new NullPointerException("target");
		}
		if (source == null) {
			return "新建商家合同";
		}
		
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : CONTRACT_TRANSLATABLE_FIELDS.entrySet()) {
			Object o1 = PropertyUtils.getProperty(source, entry.getKey());
			Object o2 = PropertyUtils.getProperty(target, entry.getKey());
			if (!ObjectUtils.equals(o1, o2)) {
				if (StringUtils.equals("clearingForm", entry.getKey())) {
					o1 = ObjectUtils.equals(NumberUtils.INTEGER_ONE, o1) ? "底价结算" : ObjectUtils.equals(2, o1) ? "扣点结算" : ObjectUtils.equals(3, o1) ? "配折结算" : ObjectUtils.equals(4, o1) ? "促销结算" : "未知";
					o2 = ObjectUtils.equals(NumberUtils.INTEGER_ONE, o2) ? "底价结算" : ObjectUtils.equals(2, o2) ? "扣点结算" : ObjectUtils.equals(3, o2) ? "配折结算" : ObjectUtils.equals(4, o2) ? "促销结算" : "未知";
				}
				sb.append(MessageFormat.format("将“{0}”由【{1}】修改为【{2}】{3}", entry.getValue(), o1, o2, LINE_SEPARATOR));
			}
		}
		return sb.toString();
	}

	public static String buildRejectAddressOperationNotes(RejectedAddressVo source, RejectedAddressVo target) throws Exception {
		if (target == null) {
			throw new NullPointerException("target");
		}
		if (source == null) {
			return "新建商家售后退货地址";
		}
		
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : AFTER_SERVICE_TRANSLATABLE_FIELDS.entrySet()) {
			Object o1 = PropertyUtils.getProperty(source, entry.getKey());
			Object o2 = PropertyUtils.getProperty(target, entry.getKey());
			if (!ObjectUtils.equals(o1, o2)) {
				sb.append(MessageFormat.format("将“{0}”由【{1}】修改为【{2}】{3}", entry.getValue(), o1, o2, LINE_SEPARATOR));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 合作模式
	 */
	public static enum CooperationModel {
		/*
		 * 提示：严禁调整枚举常量的先后顺序(因数据库记录的是枚举常量的顺序值)
		 */
		NON_ENTER_YOUGOU_WAREHOUSE_MERCHANT_DELIVERY("不入优购仓库，商家发货", true),
		ENTER_YOUGOU_WAREHOUSE_YOUGOU_DELIVERY("入优购仓库，优购发货"),
		NON_ENTER_YOUGOU_WAREHOUSE_YOUGOU_DELIVERY("不入优购仓库，优购发货");
		
		private String description;
		private boolean checked;

		private CooperationModel(String description) {
			this(description, false);
		}

		private CooperationModel(String description, boolean checked) {
			this.description = description;
			this.checked = checked;
		}
		
		public String getDescription() {
			return description;
		}

		public boolean isChecked() {
			return checked;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
