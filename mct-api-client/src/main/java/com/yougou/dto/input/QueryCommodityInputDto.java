package com.yougou.dto.input;

/**
 * <p>Title: QueryCommodityInputDto</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年6月12日
 */
public class QueryCommodityInputDto extends PageableInputDto{

	private static final long serialVersionUID = 3239551005430432365L;
	/**
	 * 商品编号
	 */
	private String commodity_no;
	/**
	 * 查询的开始时间（修改时间）
	 */
	private String start_modified;
	/**
	 * 查询的结束时间（修改时间）
	 */
	private String end_modified;
	/**
	 * 商品状态  
	 */
	private Integer status = 1;
	public String getCommodity_no() {
		return commodity_no;
	}
	public void setCommodity_no(String commodity_no) {
		this.commodity_no = commodity_no;
	}
	public String getStart_modified() {
		return start_modified;
	}
	public void setStart_modified(String start_modified) {
		this.start_modified = start_modified;
	}
	public String getEnd_modified() {
		return end_modified;
	}
	public void setEnd_modified(String end_modified) {
		this.end_modified = end_modified;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = (status == null)?1:status;
	}
}
