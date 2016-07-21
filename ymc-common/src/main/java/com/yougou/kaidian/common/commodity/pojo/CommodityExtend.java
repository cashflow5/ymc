package com.yougou.kaidian.common.commodity.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 商品实体扩展类
 *
 * @author lijun
 * @create 2012-3-30 上午10:07:47 
 * @history
 */

public class CommodityExtend {
	
	private String id;
	private String commodityNo;	
	private String sensitiveWord;
	
	public CommodityExtend(){}
	
	public CommodityExtend(String commodityNo, String sensitiveWord) {
		this.commodityNo = commodityNo;
		this.sensitiveWord = sensitiveWord;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public String getSensitiveWord() {
		return sensitiveWord;
	}
	public void setSensitiveWord(String sensitiveWord) {
		this.sensitiveWord = sensitiveWord;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
