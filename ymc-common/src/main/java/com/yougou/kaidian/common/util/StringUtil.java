/**
 * 
 */
package com.yougou.kaidian.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 字符串工具类
 * 
 * @author huang.tao
 *
 */
public class StringUtil {
	
	/**
	 * 使用分隔连接数组
	 * ["9998890", "9998891", "9998892"] ==> "9998890,9998891,9998892"
	 * 
	 * @param arry
	 * @param separate
	 * @return
	 */
	public static String join(String[] arry, String separate) {
		if (null == arry) return null;
		
		StringBuilder sb = new StringBuilder();
		for (String string : arry) {
			sb.append(string).append(separate);
		}
		
		if (sb.length() > 1)
			return sb.substring(0, sb.length() - 1);
		
		return sb.toString();
	}
	
	public static String listToString(List<String> list, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			if (i < list.size() - 1) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}
	
	public static String setToString(Set<String> set, String separator) {
		StringBuilder sb = new StringBuilder();
		int i=0;
		for(String str:set){
			sb.append(str);
			if (i < set.size() - 1) {
				sb.append(separator);
			}
			i++;
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String[] arry = new String[] {"9998890", "9998891", "9998892"};
		
		System.out.println(join(arry, ","));
		
		List<String> _list = new ArrayList<String>();
		_list.add("11-01");
		_list.add("11-02");
		System.out.println(listToString(_list, ","));
	} 
}
