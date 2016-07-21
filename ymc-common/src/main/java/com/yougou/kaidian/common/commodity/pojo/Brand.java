package com.yougou.kaidian.common.commodity.pojo;

/**
 * 品牌实体类
 *
 * @author zhuangruibo
 * @create 2012-3-30 上午10:08:22 
 * @history
 */
public class Brand implements java.io.Serializable {

	private static final long serialVersionUID = 1862053132766136165L;
	private String id;
	private String brandNo;
	private String brandName;
	private String speelingName;
	private Integer deleteflag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(Integer deleteflag) {
		this.deleteflag = deleteflag;
	}

	public void setSpeelingName(String speelingName) {
		this.speelingName = speelingName;
	}

	public String getSpeelingName() {
		return speelingName;
	}

}
