package com.yougou.dto.output;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>退换货质检信息</p>
 * 
 * @author huang.tao
 *
 */
public class ReturnQA implements Serializable {
		
		private static final long serialVersionUID = -6836905521368246771L;
		
		/** 退换货质检Id */
		private String return_id;
		
		/** 优购订单号 */
		private String order_sub_no;
		
		/** 外部订单号 */
		private String out_order_id;
		
		/** 发货快递公司 */
		private String logistics_name;
		
		/** 快递单号 */
		private String express_code;
		
		/** 发货仓库 */
		private String warehouse_name;
		
		private String return_logistics_name;
		private String return_express_code;
		private Double express_fee;
		
		private Date qa_date;
		private String qa_person;
		private String qa_remark;
		
		private List<ReturnQADetail> return_details = Collections.emptyList();

		public String getReturn_id() {
			return return_id;
		}

		public void setReturn_id(String return_id) {
			this.return_id = return_id;
		}

		public String getOrder_sub_no() {
			return order_sub_no;
		}

		public void setOrder_sub_no(String order_sub_no) {
			this.order_sub_no = order_sub_no;
		}

		public String getOut_order_id() {
			return out_order_id;
		}

		public void setOut_order_id(String out_order_id) {
			this.out_order_id = out_order_id;
		}

		public String getLogistics_name() {
			return logistics_name;
		}

		public void setLogistics_name(String logistics_name) {
			this.logistics_name = logistics_name;
		}

		public String getExpress_code() {
			return express_code;
		}

		public void setExpress_code(String express_code) {
			this.express_code = express_code;
		}
		
		public String getWarehouse_name() {
			return warehouse_name;
		}

		public void setWarehouse_name(String warehouse_name) {
			this.warehouse_name = warehouse_name;
		}

		public String getReturn_logistics_name() {
			return return_logistics_name;
		}

		public void setReturn_logistics_name(String return_logistics_name) {
			this.return_logistics_name = return_logistics_name;
		}

		public String getReturn_express_code() {
			return return_express_code;
		}

		public void setReturn_express_code(String return_express_code) {
			this.return_express_code = return_express_code;
		}

		public Double getExpress_fee() {
			return express_fee;
		}

		public void setExpress_fee(Double express_fee) {
			this.express_fee = express_fee;
		}
		public Date getQa_date() {
			return qa_date;
		}
		public void setQa_date(Date qa_date) {
			this.qa_date = qa_date;
		}
		public String getQa_person() {
			return qa_person;
		}
		public void setQa_person(String qa_person) {
			this.qa_person = qa_person;
		}
		public String getQa_remark() {
			return qa_remark;
		}
		public void setQa_remark(String qa_remark) {
			this.qa_remark = qa_remark;
		}
		public List<ReturnQADetail> getReturn_details() {
			return return_details;
		}

		public void setReturn_details(List<ReturnQADetail> return_details) {
			this.return_details = return_details;
		}

	}
