package com.yougou.dto.output;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.yougou.dto.common.OrderEnum.DeliveryDate;
import com.yougou.dto.common.Payment;
import com.yougou.dto.output.QueryOrderDetailOutputDto.ItemDetail;

/**
 * 
 * @author 杨梦清
 * 
 */
public class QueryOrderOutputDto extends PageableOutputDto {

	private static final long serialVersionUID = -4022856061486607800L;

	/** 页数据 **/
	private List<Item> items = Collections.emptyList();
	
	public QueryOrderOutputDto() {
		super();
	}

	public QueryOrderOutputDto(int page_index, int page_size, int total_count) {
		super(page_index, page_size, total_count);
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public static class Item implements Serializable {
		
		private static final long serialVersionUID = 2183113440754268856L;

		/** 子订单id **/
		private String id;
		
		/** 子订单号 **/
		private String order_sub_no;
		
		/** 仓库编号 */
		private String warehouse_code;
		
		/** 原始订单号 **/
		private String original_order_no;
		/**外部订单号**/
		private String out_order_id;
		
		/** 商品总数 */
		private Integer product_total_quantity;
		
		/** 订单留言 **/
		private String message;
		
		/** 支付方式 **/
		private Payment payment;
		
		/** 订单状态 **/
		private Integer order_status;
		
		/** 快递公司编号 */
		private String logistics_code;
		
		/** 快递公司名称 */
		private String logistics_name;
		
		/** 省 */
		private String province;
		
		/** 市 */
		private String city;
		
		/** 区 */
		private String area;
		
		/** 省 */
		private String province_name;
		
		/** 市 */
		private String city_name;
		
		/** 区 */
		private String area_name;
		
		/** 收货人详细地址 */
		private String consignee_address;
		
		/** 收货人姓名 */
		private String consignee_name;
		
		/** 收货人电话 */
		private String constact_phone;
		
		/** 收货人邮箱 */
		private String email;
		
		/** 收货人手机号码 */
		private String mobile_phone;
		
		/** 收货人邮编 */
		private String zipcode;
		
		/** 购买人 */
		private String buyer_name;
		
		/** 会员号 */
		private String member_name;
		
		/** 订单支付金额 下订单时候付款的金额 **/
		private Double order_pay_total_amont;
		
		/** 实际运费 **/
		private Double actual_postage;
		
		/** 总优惠金额 */
		private Double discount_amount;
		/**
		 * 优惠券优惠金额
		 */
		private Double coupon_pref_amount;
		/**
		 * 礼品卡优惠金额
		 */
		private Double coupon_pref_amount5;
		
		/** 创建日期 */
		private Date create_time;
		
		/** 修改日期 */
		private Date modify_time;
		
		/** 订单在线支付时间 **/
		private Date online_pay_time;
		
		/** 发货时间 **/
		private Date ship_time;
		
		/**
		 * 送货日期
		 */
		private String delivery_date;
		
		private DeliveryDate delivery_date_temp;
		
		/**
		 * 下单立减优惠金额
		 */
		private Double buy_reduction_pref_amount;
		/**
	     *  订单来源编码 
	     */
		private String order_source_no;
		
		//==============唯品会JIT新增字段start=============
		/**
		 * 唯品会JIT拣货单号 
		 */
		private String package_no;
		
		/**
		 * 唯品会JIT 送货仓库编码
		 */
		private String jit_warehouse;
		
		/**
		 * 唯品会JIT 要求到货时间
		 */
		private Date arrive_time;
		
		
		//==============唯品会JIT新增字段end=============
		
		public Double getBuy_reduction_pref_amount() {
			return buy_reduction_pref_amount;
		}

		public void setBuy_reduction_pref_amount(Double buy_reduction_pref_amount) {
			this.buy_reduction_pref_amount = buy_reduction_pref_amount;
		}

		public String getDelivery_date() {
			return delivery_date;
		}

		public void setDelivery_date(String delivery_date) {
			
			this.delivery_date = delivery_date;
		}

		public DeliveryDate getDelivery_date_temp() {
			return delivery_date_temp;
		}

		private List<ItemDetail> item_details = Collections.emptyList();
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
		
		public String getOut_order_id() {
			return out_order_id;
		}

		public void setOut_order_id(String out_order_id) {
			this.out_order_id = out_order_id;
		}

		public Double getCoupon_pref_amount() {
			return coupon_pref_amount;
		}

		public void setCoupon_pref_amount(Double coupon_pref_amount) {
			this.coupon_pref_amount = coupon_pref_amount;
		}

		public Double getCoupon_pref_amount5() {
			return coupon_pref_amount5;
		}

		public void setCoupon_pref_amount5(Double coupon_pref_amount5) {
			this.coupon_pref_amount5 = coupon_pref_amount5;
		}

		public String getWarehouse_code() {
			return warehouse_code;
		}

		public void setWarehouse_code(String warehouse_code) {
			this.warehouse_code = warehouse_code;
		}

		public void setDelivery_date_temp(DeliveryDate delivery_date_temp) {
			this.delivery_date_temp = delivery_date_temp;
			this.delivery_date = delivery_date_temp.getName();
		}

		public String getOrder_sub_no() {
			return order_sub_no;
		}
		
		public void setOrder_sub_no(String order_sub_no) {
			this.order_sub_no = order_sub_no;
		}
		
		public String getOriginal_order_no() {
			return original_order_no;
		}

		public void setOriginal_order_no(String original_order_no) {
			this.original_order_no = original_order_no;
		}

		public Integer getProduct_total_quantity() {
			return product_total_quantity;
		}
		
		public void setProduct_total_quantity(Integer product_total_quantity) {
			this.product_total_quantity = product_total_quantity;
		}
		
		public String getMessage() {
			return message;
		}
		
		public void setMessage(String message) {
			this.message = message;
		}
		
		public Payment getPayment() {
			return payment;
		}
		
		public void setPayment(Payment payment) {
			this.payment = payment;
		}
		
		public String getPayment_name() {
			return payment != null ? payment.getName() : null;
		}

		public Integer getOrder_status() {
			return order_status;
		}
		
		public void setOrder_status(Integer order_status) {
			this.order_status = order_status;
		}
		
		public Integer getOrder_status(Integer baseStatus, Integer isException) {
			if (baseStatus == null) {
				return null;
			} else if (isException.intValue() == 10) {
				return 4;
			} else if (baseStatus.intValue() == 2) {
				return 1;
			} else if (baseStatus.intValue() == 4) {
				return 2;
			} else if (baseStatus.intValue() == 6 || baseStatus.intValue() == 7) {
				return 3;
			} else if(baseStatus.intValue() == 1){//如果本身是1， 也会返回1，但1实际是未处理，在getOrder_status_name方法会得到待发货
				return -1;
			} else {
				return baseStatus;
			}
		}
		
		public String getOrder_status_name() {
			if (order_status == null) {
				return null;
			} else if (order_status.intValue() == 1) {
				return "待发货";
			} else if (order_status.intValue() == 2) {
				return "已完成";
			} else if (order_status.intValue() == 3) {
				return "申请拦截";
			} else if (order_status.intValue() == 4) {
				return "缺货";
			} else if (order_status.intValue() == 5) {
				return "已作废";
			} else if (order_status.intValue() == 25) {
				return "已打包";
			} else {
				return "未知状态：" + order_status.intValue();
			}
		}
		
		public String getLogistics_code() {
			return logistics_code;
		}
		
		public void setLogistics_code(String logistics_code) {
			this.logistics_code = logistics_code;
		}
		
		public String getLogistics_name() {
			return logistics_name;
		}
		
		public void setLogistics_name(String logistics_name) {
			this.logistics_name = logistics_name;
		}
		
		public String getProvince() {
			return province;
		}
		
		public void setProvince(String province) {
			this.province = province;
		}
		
		public String getCity() {
			return city;
		}
		
		public void setCity(String city) {
			this.city = city;
		}
		
		public String getArea() {
			return area;
		}
		
		public void setArea(String area) {
			this.area = area;
		}
		
		public String getConsignee_address() {
			return consignee_address;
		}
		
		public void setConsignee_address(String consignee_address) {
			this.consignee_address = consignee_address;
		}
		
		public String getConsignee_name() {
			return consignee_name;
		}
		
		public void setConsignee_name(String consignee_name) {
			this.consignee_name = consignee_name;
		}
		
		public String getConstact_phone() {
			return constact_phone;
		}
		
		public void setConstact_phone(String constact_phone) {
			this.constact_phone = constact_phone;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getMobile_phone() {
			return mobile_phone;
		}
		
		public void setMobile_phone(String mobile_phone) {
			this.mobile_phone = mobile_phone;
		}
		
		public String getZipcode() {
			return zipcode;
		}
		
		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}
		
		public String getBuyer_name() {
			return buyer_name;
		}
		
		public void setBuyer_name(String buyer_name) {
			this.buyer_name = buyer_name;
		}
		
		public String getMember_name() {
			return (null ==member_name ||"".equals(member_name.trim()) )?this.consignee_name:member_name;
		}
		
		public void setMember_name(String member_name) {
			this.member_name = member_name;
		}
		
		public Double getOrder_pay_total_amont() {
			return order_pay_total_amont;
		}
		
		public void setOrder_pay_total_amont(Double order_pay_total_amont) {
			this.order_pay_total_amont = order_pay_total_amont;
		}
		
		public Double getActual_postage() {
			return actual_postage;
		}
		
		public void setActual_postage(Double actual_postage) {
			this.actual_postage = actual_postage;
		}
		
		public Double getDiscount_amount() {
			return discount_amount;
		}
		
		public void setDiscount_amount(Double discount_amount) {
			this.discount_amount = discount_amount;
		}
		
		public Date getCreate_time() {
			return create_time;
		}
		
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}
		
		public Date getModify_time() {
			return modify_time;
		}
		
		public void setModify_time(Date modify_time) {
			this.modify_time = modify_time;
		}
		
		public Date getOnline_pay_time() {
			return online_pay_time;
		}
		
		public void setOnline_pay_time(Date online_pay_time) {
			this.online_pay_time = online_pay_time;
		}
		
		public Date getShip_time() {
			return ship_time;
		}
		
		public void setShip_time(Date ship_time) {
			this.ship_time = ship_time;
		}

		public String getProvince_name() {
			return province_name;
		}

		public void setProvince_name(String province_name) {
			this.province_name = province_name;
		}

		public String getCity_name() {
			return city_name;
		}

		public void setCity_name(String city_name) {
			this.city_name = city_name;
		}

		public String getArea_name() {
			return area_name;
		}

		public void setArea_name(String area_name) {
			this.area_name = area_name;
		}

		public List<ItemDetail> getItem_details() {
			return item_details;
		}

		public void setItem_details(List<ItemDetail> item_details) {
			this.item_details = item_details;
		}

		public String getOrder_source_no() {
			return order_source_no;
		}

		public void setOrder_source_no(String order_source_no) {
			this.order_source_no = order_source_no;
		}	

		public String getJit_warehouse() {
			return jit_warehouse;
		}

		public void setJit_warehouse(String jit_warehouse) {
			this.jit_warehouse = jit_warehouse;
		}

		public String getPackage_no() {
			return package_no;
		}

		public void setPackage_no(String package_no) {
			this.package_no = package_no;
		}

		public Date getArrive_time() {
			return arrive_time;
		}

		public void setArrive_time(Date arrive_time) {
			this.arrive_time = arrive_time;
		}
	}

}

