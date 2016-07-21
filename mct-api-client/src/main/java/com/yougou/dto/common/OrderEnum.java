package com.yougou.dto.common;

/**
 * 类OrderEnum.java的实现描述：TODO 类实现描述
 * 
 * @author 黄斌
 * @date 2011-12-13 下午3:25:46
 */
public class OrderEnum {

	public enum MethodCode {
		SEND_COUPON_QUALITY("send_coupon_quality", "质检赠送礼品卡"),
		GEN_SUB_ORDER("sub_order", "生成子订单"),
		GEN_SUB_SPLIT_ORDER("sub_split_order", "拆分子订单"),
		GEN_EX_SUB_ORDER("ex_sub_order", "生成手工子订单"),
		GEN_EX_SUB_SPLIT_ORDER("ex_sub_split_order", "拆分手工子订单"),
		PAID_ORDER("turnPaid", "订单支付成功"),
		SAVE_REFUND_ORDER("saveApply", "申请退款"),
		SET_INVALID("setInvalid", "设置商品无效"),
		CANCEL_ORDER("turnCancel", "取消订单"),
		CANCEL_COUPON("batchCancelCoupon", "取消优惠卷"),
		ACTIVE_COUPON("activeCoupon", "激活优惠卷"),
		PAY_OFFLINE("batchCancelCoupon", "财务线下支付"),
		CALL_ALLIANCE("orderAbnormalService", "调用联盟商"),
		CANCEL_SPECIAL_ORDER("cancelSpecialOrder", "客服取消"),
		ACTIVE_ORDER("activeOrder", "激活订单"),
		RENEW_ORDER("activeOrder", "恢复客服取消订单"),
		CANCEL_PRODUCT("cancelProduct", "取消商品"),
		MOUNT_ORDER("mountOrder", "挂起订单"),
		SALE_REFUND_REFUSE("mountOrder", "售后退款拒绝"),
		FINISH_SALE("mountOrder", "售后完成"),
		CHOOSE_HOUSE("updateHouse", "更新仓库"),
		UPDATE_TIME("updateOrderTime", "更新时间戳"),
		CHOOSE_LOGISTIC("updateLogictis", "更新物流公司"),
		UN_LOCK_ORDER("unLockOrder", "解锁订单"),
		LOCK_ORDER("lock_order", "锁定订单"),
		DUBIOUS_ORDER("dubious_order", "置疑订单"),
		SET_NORMAL_ORDER("setNormalOrder", "置为正常订单"),
		TURN_EXCEPTION("turnException", "系统置为异常订单"),
		TURN_SYSTEM_CHECK_SUCCESS("turnSystemCheckSuccess", "系统审核"),
		GIVEING_SORCE("batchSendEmailOrIntegral", "赠送积分"),
		RETURN_SORCE("returnSorce", "返还积分"),
		CONSUME_INTEGRAL("consumeIntegral", "消耗积分"),
		CALL_JOB("rescheduleJobOfException", "手工调度任务"),
		SET_SWITCH("resetKey", "设置调度开关"),
		TURNCHECK_REVIEW("turnCheckReview", "人工确认"),
		TURN_CHECK_NOT_REVIEW("turnCheckNotReview", "拒绝订单"),
		SET_EXCE_ORDER("setExceOrder", "置为异常订单"),
		REFUND_STATUS_BY_ORDERNO("editRefundStatusByOrderNo", "订单退款"),
		CONFIRM_REFUND("turnRefund", "确认退款"),
		CREATE_REMARK("createRemark", "添加备注"),
		STOCKING_ORDER("stockingOrder", "导出备货"),
		EDIT_CONSIGNEEINFO("editConsigneeInfo", "修改收货人信息"),
		EDIT_ORDER_PRODUCT("editConsigneeInfo", "修改商品信息"),
		SAVE_APPLY("saveApply", "申请退款"),
		UPDATE_ORDER("turnUpdateOrder", "修改支付方式"),
		UPDATE_SALETYPE("u_saleType", "修改售后类型"),
		ORDER_OUTSTORE("saveApply", "订单已出库"),
		ORDER_CHECK_UP("saveApply", "订单拣货中"),
		ORDER_PACKED("saveApply", "订单已打包"),
		ORDER_TERMINATED_DELIVERY("terminateddelivery", "订单仓库拦截成功"),
		INSERT_SALE("insertSaleApplyBill", "申请售后"),
		TURN_SALE_REFUND_SUCCESS("turnSaleReundSuccess", "订单售后退款"),
		TURN_SALE_SUPPLY_SUCCESS("turnSaleReundSuccess", "订单售后补款"),
		TURN_SALE_REPAIR_SUCCESS("turnRepairByFinance", "订单售后返修补款"),
		TURN_SALE_REFUND_FAIL("turnSaleReundFail", "订单售后退款拒绝"),
		TURN_SALE_SUPPLY_FAIL("turnSaleReundFail", "订单售后补款拒绝"),
		RETURN_SALE_APPLY("returnSaleApply","售后申请打回"),
		TURN_OTHER_REFUND_SUCCESS("turnRefundOtherSuccess", "订单其他退款成功"),
		TURN_OTHER_REFUND_FAIL("turnRefundOtherFail", "订单其他退款失败"),
		UMOUNT_ORDER("umountOrder", "解挂订单"),
		BATCH_PRESELL_ORDER("batchUpdatePresell", "批量预售转仓库"),
		REJECT_ORDER("rejectOrder", "拒收收货"),
		REJECT_Call("REJECTCall", "拒收回访登记"),
		REJECT_ORDER_QC("umountOrder", "拒收收货质检"),
		BACK_CHANGE_ORDER_QC("backChangeOrderQc", "退换货收货质检"),
		ORDER_BUFFER_STOCK("orderBufferStock", "订单已备货"),
		ORDER_INVOICE_REGISTRATION("orderInvoiceRegistration", "订单发票登记"),
		DELETE_ORDER_INVOICE_REGISTRATION("deleteOrderInvoiceRegistration", "清除订单发票登记"),
		ORDER_INVOICE_PRINT("orderInvoicePrint", "订单发票已打印"),
		MERCHANT_API_OPERATION("deleteOrderInvoiceRegistration", "招商api操作"),
		ADD_LOGISTIC_INFO("addLogisticInfo", "补充物流信息"),
		MODIFY_LOGISTIC_INFO("modifyLogisticInfo","修改物流信息"),
		ORDER_SEND_SMS("order_send_sms", "发送短信"),
		ORDER_APPLY_INTERRUPT_FAIL("order_apply_interrupt_fail","申请拦截招商订单失败"),
		CANCEL_VJIA_COMMAND_ORDER("cancelVjiaCommandOrder", "Vjia指令取消");

		private final String key;
		private final String value;

		MethodCode(String key, String value) {

			this.key = key;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getKey() {
			return this.key;
		}

		public String getName(){
			return this.getValue();
		}
	}

	public enum LogType {
		// 订单操作日志
		ORDER_OPERATE_LOG(1,"订单操作日志"),
		// 订单售后日志
		ORDER_SAL_LOG(2,"订单售后日志"),
		// 订单退款申请日志
		ORDER_REFUND_LOG(3,"订单退款申请日志"),
		// 订单正常流程日志
		ORDER_NORMAL_LOG(4,"订单正常流程日志"),
		// 订单正常流程日志
		ORDER_SYSTEM_LOG(5,"订单系统调度日志"),
		// 订单接口调用日志
		ORDER_CALL_LOG(6,"订单接口调用日志"),
		REJECT_CALL(6,"拒收回访"),
		//暂时做订单操作日志处理
		ORDER_INVOICE_LOG(1,"发票操作日志"),
		ADD_LOGISTIC_INFO(8,"补充物流信息"),
		MODIFY_LOGISTIC_INFO(9,"修改物流信息"),
		APPLY_INTERRUPT_FAIL(10,"申请拦截订单失败"),
		SET_EXCE_ORDER(11,"置为异常订单");
		private final int value;
		private final String name;

		LogType(int value,String name) {
			this.value = value;
			this.name = name;
		}

		public int getValue() {
			return this.value;
		}
		
		public String getName(){
			return this.name;
		}

	}

	/**
	 * 支付方式
	 * 
	 * @author xieqingang
	 * @date 2011-5-10
	 */
	public enum Paymentmethod {
		ONLINE_PAYMENT("1", "在线支付"), CASH_ON_DELIVERY("2", "货到付款"), BANK_TRANSFER("3", "银行转帐"), MAIL_REMITTANCE("4", "邮局汇款"),OFFLINE_TRANSFER("5","线下转账");
		private final String key;
		private final String value;

		Paymentmethod(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getKey() {
			return this.key;
		}
	}

	/**
	 * 送货日期选项
	 * 
	 * @author xieqingang
	 * @date 2011-5-16
	 */
	public enum DeliveryDate {
		WORKDAY_DELIVERY("只工作日送货(双休日、假日不用送)"),
		ALL_CAN_DELIVER("工作日、双休日与假日均可送货"),
		HOLIDAY_DELIVERY("只双休日、假日送货(工作日不用送)"),
		OTHER_TIME_DELIVERY("学校地址/地址白天没人，请尽量安排其他时间送货 (注：特别安排可能会超出预计送货天数)");
		private String name;
		DeliveryDate(String name){
			this.name = name;
		}
		public String getName(){
			return this.name;
		}
		
		public static DeliveryDate getDeliveryDateEnum(String name) {
			for (DeliveryDate deliveryDate : DeliveryDate.values()) {
				if (name.equals(deliveryDate.getName())) {
					return deliveryDate;
				}
			}
			return null;
		}
	}
}
