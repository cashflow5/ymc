package com.yougou.dto.output;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.yougou.dto.common.OrderEnum.DeliveryDate;
import com.yougou.dto.common.Payment;

/**
 * 订单详情
 * 
 * @author 杨梦清
 * 
 */
public class QueryOrderDetailOutputDto extends OutputDto {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8162266736902922416L;

	/** 页数据 **/
	private List<ItemDetail> items = Collections.emptyList();

	public List<ItemDetail> getItems() {
		return items;
	}

	public void setItems(List<ItemDetail> items) {
		this.items = items;
	}

	private String id;
	
	/** 子订单号 **/
	private String order_sub_no;

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

	/**店铺ID**/
	private String out_shop_id;
	
	/**订单来源**/
	private String order_source_id;
	
	/**
	 * 送货日期
	 */
	private String delivery_date;
	
	
	private DeliveryDate delivery_date_temp;
	
	private String order_source_no;
	
	/**
	 * 下单立减优惠金额
	 */
	private Double buy_reduction_pref_amount;
	
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

	public String getOut_shop_id() {
		return out_shop_id;
	}

	public DeliveryDate getDelivery_date_temp() {
		return delivery_date_temp;
	}

	public void setOut_shop_id(String out_shop_id) {
		this.out_shop_id = out_shop_id;
	}

	

	public void setDelivery_date_temp(DeliveryDate delivery_date_temp) {
		this.delivery_date_temp = delivery_date_temp;
		this.delivery_date = delivery_date_temp.getName();
	}

	public String getOrder_source_id() {
		return order_source_id;
	}

	public void setOrder_source_id(String order_source_id) {
		this.order_source_id = order_source_id;
	}

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
	
	public Integer getOrder_status(Integer baseStatus) {
		if (baseStatus == null) {
			return null;
		} else if (baseStatus.intValue() == 2) {
			return 1;
		} else if (baseStatus.intValue() == 4) {
			return 2;
		} else if (baseStatus.intValue() == 6 || baseStatus.intValue() == 7) {
			return 3;
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
		} else  {// FIXME 补充其他状态类型
			return order_status.toString();
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

	public QueryOrderDetailOutputDto() {
		super();
	}

	public String getOrder_source_no() {
		return order_source_no;
	}

	public void setOrder_source_no(String order_source_no) {
		this.order_source_no = order_source_no;
	}

	public static class ItemDetail implements Serializable {

		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 4423414876912621019L;

		/** 商品编码 */
		private String commodity_no;
		/** 供应商款色编码 **/
		private String supplier_code;
		/** 货品编码 */
		private String prod_no;
		/** 供应商款色编码 */
		private String level_code;
		/** 货品名称 */
		private String prod_name;
		/** 商品图片 */
		private String commodity_image;
		/** 商品数量 */
		private Integer commodity_num;
		/** 商品单价 */
		private Double prod_unit_price;
		/** 商品优惠总价 */
		private Double prod_discount_amount;
		/** 结算总金额 */
		private Double prod_total_amt;
		/**货品规格**/
		private String commodity_specification_str;
		/**货品重量**/
		private Double commodity_weight;
	    /**
	     * 商品类型:0.普通商品  1.赠品  2.换购 3.从商品
	     */
	    private Integer commodity_type;
		
	       /** 款号 */
        private String style_no;
        
        /**
		 * 活动优惠金额
		 */
		private Double active_pref_amount;

		/**
		 * 会员折扣优惠金额
		 */
		private Double member_pref_amount;

		/**
		 * 优惠券优惠金额
		 */
		private Double coupon_pref_amount;
		/**
		 * 支付方式优惠金额
		 */
		private Double payment_pref_amount;

		/**
		 * 下单立减优惠金额
		 */
		private Double buy_reduction_pref_amount;

		/**
		 * 应付运费
		 */
		private Double should_postage;

		/**
		 * 实际运费
		 */
		private Double postage_cost;
	    
		public String getStyle_no() {
            return style_no;
        }

        public void setStyle_no(String style_no) {
            this.style_no = style_no;
        }

        public String getCommodity_specification_str() {
			return commodity_specification_str;
		}

		public void setCommodity_specification_str(String commodity_specification_str) {
			this.commodity_specification_str = commodity_specification_str;
		}

		public Double getCommodity_weight() {
			return commodity_weight;
		}

		public void setCommodity_weight(Double commodity_weight) {
			this.commodity_weight = commodity_weight;
		}

		public String getCommodity_no() {
			return commodity_no;
		}

		public void setCommodity_no(String commodity_no) {
			this.commodity_no = commodity_no;
		}

		public String getSupplier_code() {
			return supplier_code;
		}

		public void setSupplier_code(String supplier_code) {
			this.supplier_code = supplier_code;
		}

		public String getProd_no() {
			return prod_no;
		}

		public void setProd_no(String prod_no) {
			this.prod_no = prod_no;
		}

		public String getLevel_code() {
			return level_code;
		}

		public void setLevel_code(String level_code) {
			this.level_code = level_code;
		}

		public String getProd_name() {
			return prod_name;
		}

		public void setProd_name(String prod_name) {
			this.prod_name = prod_name;
		}

		public String getCommodity_image() {
			return commodity_image;
		}

		public void setCommodity_image(String commodity_image) {
			this.commodity_image = commodity_image;
		}

		public Integer getCommodity_num() {
			return commodity_num;
		}

		public void setCommodity_num(Integer commodity_num) {
			this.commodity_num = commodity_num;
		}

		public Double getProd_unit_price() {
			return prod_unit_price;
		}

		public void setProd_unit_price(Double prod_unit_price) {
			this.prod_unit_price = prod_unit_price;
		}

		public Double getProd_discount_amount() {
			return prod_discount_amount;
		}

		public void setProd_discount_amount(Double prod_discount_amount) {
			this.prod_discount_amount = prod_discount_amount;
		}

		public Double getProd_total_amt() {
			return prod_total_amt;
		}

		public void setProd_total_amt(Double prod_total_amt) {
			this.prod_total_amt = prod_total_amt;
		}

		/**
		 * @return the commodity_type
		 */
		public Integer getCommodity_type() {
			return commodity_type;
		}

		/**
		 * @param commodity_type the commodity_type to set
		 */
		public void setCommodity_type(Integer commodity_type) {
			this.commodity_type = commodity_type;
		}
		
		

		public Double getActive_pref_amount() {
			return active_pref_amount;
		}

		public void setActive_pref_amount(Double active_pref_amount) {
			this.active_pref_amount = active_pref_amount;
		}

		public Double getMember_pref_amount() {
			return member_pref_amount;
		}

		public void setMember_pref_amount(Double member_pref_amount) {
			this.member_pref_amount = member_pref_amount;
		}

		public Double getCoupon_pref_amount() {
			return coupon_pref_amount;
		}

		public void setCoupon_pref_amount(Double coupon_pref_amount) {
			this.coupon_pref_amount = coupon_pref_amount;
		}

		public Double getPayment_pref_amount() {
			return payment_pref_amount;
		}

		public void setPayment_pref_amount(Double payment_pref_amount) {
			this.payment_pref_amount = payment_pref_amount;
		}

		public Double getBuy_reduction_pref_amount() {
			return buy_reduction_pref_amount;
		}

		public void setBuy_reduction_pref_amount(Double buy_reduction_pref_amount) {
			this.buy_reduction_pref_amount = buy_reduction_pref_amount;
		}

		public Double getShould_postage() {
			return should_postage;
		}

		public void setShould_postage(Double should_postage) {
			this.should_postage = should_postage;
		}

		public Double getPostage_cost() {
			return postage_cost;
		}

		public void setPostage_cost(Double postage_cost) {
			this.postage_cost = postage_cost;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ItemDetail [commodity_no=" + commodity_no
					+ ", supplier_code=" + supplier_code + ", prod_no="
					+ prod_no + ", level_code=" + level_code + ", prod_name="
					+ prod_name + ", commodity_image=" + commodity_image
					+ ", commodity_num=" + commodity_num + ", prod_unit_price="
					+ prod_unit_price + ", prod_discount_amount="
					+ prod_discount_amount + ", prod_total_amt="
					+ prod_total_amt + ", commodity_specification_str="
					+ commodity_specification_str + ", commodity_weight="
					+ commodity_weight + ", commodityType=" + commodity_type
					+ "]";
		}
	}

}

