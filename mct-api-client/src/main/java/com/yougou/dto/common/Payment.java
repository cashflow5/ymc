package com.yougou.dto.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付方式
 * @date 2011-5-16
 */
public enum Payment{

	ONLINE_PAYMENT{
		public String getName(){
			return "在线支付";
		}
	},
	CASH_ON_DELIVERY{
		public String getName(){
			return "货到付款";
		}
	},
	BANK_TRANSFER{
		public String getName(){
			return "银行转帐";
		}
	},
	MAIL_REMITTANCE{
		public String getName(){
			return "邮局汇款";
		}
	},
	OFFLINE_TRANSFER{
		public String getName(){
			return "线下转账";
		}
	};
	
	public abstract String getName();
	
	public static Map<String, String> getPayment() {
		Map<String, String> paymentMethods = new HashMap<String, String>();
		for (Payment payment : Payment.values()) {
			paymentMethods.put(payment.toString(), payment.getName());
		}
		return paymentMethods;
	}
	
	public static Payment getPaymentEnum(String name) {
		for (Payment payment : Payment.values()) {
			if (name.equals(payment.getName())) {
				return payment;
			}
		}
		return null;
	}
}

