package com.yougou.kaidian.common.base;

import java.util.UUID;

/**
 * 主键生成器
 * 
 * @author asus
 * 
 */
public class KeyGenerator {

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String key = uuid.toString();
		key = key.replaceAll("-", "");
		return key;
	}
}
