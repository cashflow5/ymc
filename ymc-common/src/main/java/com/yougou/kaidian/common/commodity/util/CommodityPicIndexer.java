package com.yougou.kaidian.common.commodity.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author yang.mq
 *
 */
public class CommodityPicIndexer {

	private static final Map<Pattern, Integer> CAT_NAMES_SHEETS_MAP = new HashMap<Pattern, Integer>();
	
	private static final Map<Pattern, Integer> CAT_NAMES_NUMBERS_MAP = new HashMap<Pattern, Integer>();
	
	static {
		// 运动鞋类用上传的第6张放大镜图片来生成其他图片
		CAT_NAMES_SHEETS_MAP.put(Pattern.compile("运动_运动鞋_.*", Pattern.CASE_INSENSITIVE), 6);
		
		// 男鞋、女鞋、户外休闲鞋，用上传的第2张放大镜图片来生成其他图片
		CAT_NAMES_SHEETS_MAP.put(Pattern.compile("(男鞋|女鞋)_.*_.*", Pattern.CASE_INSENSITIVE), 2);
		CAT_NAMES_SHEETS_MAP.put(Pattern.compile("户外休闲_(户外鞋|休闲鞋)_.*", Pattern.CASE_INSENSITIVE), 2);
		
		//运动鞋至少6张
		CAT_NAMES_NUMBERS_MAP.put(Pattern.compile("运动_运动鞋_.*", Pattern.CASE_INSENSITIVE), 6);
	}

	/**
	 * 索引分类选取第几张图片为小图生成模板
	 * 
	 * @param catNames
	 * @return int
	 */
	public static int indexSheets(String... catNames) {
		String keyword = StringUtils.join(catNames, "_");
		for (Map.Entry<Pattern, Integer> entry : CAT_NAMES_SHEETS_MAP.entrySet()) {
			if (entry.getKey().matcher(keyword).matches()) {
				return entry.getValue().intValue();
			}
		}
		return 1;
	}
	
	/**
	 * 索引分类放大镜图片最低上传数量
	 * 
	 * @param catNames
	 * @return int
	 */
	public static int indexNumbers(int defaultValue,String... catNames) {
		String keyword = StringUtils.join(catNames, "_");
		for (Map.Entry<Pattern, Integer> entry : CAT_NAMES_NUMBERS_MAP.entrySet()) {
			if (entry.getKey().matcher(keyword).matches()) {
				return entry.getValue().intValue();
			}
		}
		return defaultValue;
	}
}
