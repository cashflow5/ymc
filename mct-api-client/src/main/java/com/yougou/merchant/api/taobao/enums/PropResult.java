package com.yougou.merchant.api.taobao.enums;

/**
 * 属性、属性值结果
 * @author luo.hl
 *
 */
public enum PropResult {
	NOTFOUND(1,"没有找到相关属性"),NOTBIND(2,"属性没有绑定"),HAVEBIND(3,"属性已绑定");
	
	private int type;
	
	private String desc;

	PropResult(int type,String desc) {

		this.type = type;
		this.desc = desc;
	}

	public int getType() {
		return type;
	}
	
	public String getDesc(){
		return this.desc;
	}
}
