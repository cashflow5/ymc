/**
 * 
 */
package com.yougou.kaidian.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 日期工具类
 * 
 * @author huang.tao
 *
 */
public class DateUtil {
	
	public static final SimpleDateFormat sdf_mm_dd = new SimpleDateFormat("MM'月'dd'日'");
	public static final SimpleDateFormat _sdf    = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final	SimpleDateFormat _sdf2   = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final	SimpleDateFormat sdfsdf  = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static final	SimpleDateFormat sdfsdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	public static final	SimpleDateFormat _sdf1   = new SimpleDateFormat("yyyy-MM-dd");
	public static final	SimpleDateFormat sdf_mmdd1 = new SimpleDateFormat("yyyyMMdd");
	public static final	SimpleDateFormat sdfsdf1   = new SimpleDateFormat("yyyy/MM/dd");
	
	 //yyyy-MM-dd 
	public static final	 String reg_yyyy_MM_dd = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})" +
            "-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))" +
            "|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
    
    //yyyyMMdd 
	public static final	String reg_yyyyMMdd = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))" +
            "|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))" +
            "|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)";
    
    //yyyy/MM/dd
	public static final	String reg_sdfsdf = "^(\\d{4})/(\\d{2})/(\\d{2})$";
    //yyyy/MM/dd HH:mm
	public static final	String regex4 ="^((\\d){4})/(0(\\d)|1[0-2]{1})/([0-2]{1}(\\d)|3[01]{1})(\\s)(([01]{1}(\\d)|2[0-3]{1}):([0-5]{1}(\\d)))$";  
    //yyyy/MM/dd HH:mm:ss
	public static final	String regex3 = "^(\\d{4})/(0(\\d)|1[0-2]{1})/([0-2]{1}(\\d)|3[01]{1})(\\s)([01]{1}(\\d)|2[0-3]{1}):([0-5]{1}(\\d)):([0-5]{1}(\\d))$";
    //yyyy-MM-dd HH:mm
	public static final	String regex2 = "^(\\d{4})-(0(\\d)|1[0-2]{1})-([0-2]{1}(\\d)|3[01]{1})(\\s)(([01]{1}(\\d)|2[0-3]{1})):([0-5]{1}(\\d))$";
    //yyyy-MM-dd HH:mm:ss
	public static final	String regex1 = "^(\\d{4})-(0(\\d)|1[0-2]{1})-([0-2]{1}(\\d)|3[01]{1})(\\s)(([01]{1}(\\d)|2[0-3]{1})):([0-5]{1}(\\d)):([0-5]{1}(\\d))$";

	
	public static String getFormatByDate(Date date) {
		return _sdf.format(date);
	}
	
	/**
	 * <p>
	 * 获取最近几天的日期字符串集合 <br />
	 * </p><p>
	 * [2013-11-05, 2013-11-06, 2013-11-07]
	 * </p>
	 * @param day
	 * @return MM-dd
	 */
	public static List<String> getDateStrMMddByNeerWeek(int day) {
		List<String> list = new ArrayList<String>();
		for (int i = day - 1; i >= 0; i--) {
			list.add(sdf_mm_dd.format(addDay2Date(-i, new Date())));
		}
		
		return list;
	}
	
	/**
	 * <p>
	 * 获取最近几天的日期字符串集合 <br />
	 * </p><p>
	 * [2013-11-05, 2013-11-06, 2013-11-07]
	 * </p>
	 * @param day
	 * @return yyyy-MM-dd
	 */
	public static List<String> getDateStrByNeerWeek(int day) {
		List<String> list = new ArrayList<String>();
		for (int i = day - 1; i >= 0; i--) {
			list.add(_sdf1.format(addDay2Date(-i, new Date())));	
		}
		
		return list;
	}
	
	/**
	 * 在指定日期上增加天数
	 * 
	 * @param d    天数
	 * @param date 指定日期（默认为当前日期）
	 * @return 增加天数后的日期
	 */
	public static Date addDay2Date(int d, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(null == date ? new Date() : date);
		calendar.add(Calendar.DAY_OF_MONTH, d);
		return calendar.getTime();
	}
	
    public static Date parseDateForCSVImport(String strDate){
    	if(StringUtils.isBlank(strDate)){
    		return null;
    	}else{
    		strDate = strDate.trim();
    	}
    	
	
		try {  
            if(strDate.matches(regex4)){
				return  sdfsdf2.parse(strDate);
            }else  if(strDate.matches(regex3)){
				return  sdfsdf.parse(strDate);
            }else if(strDate.matches(regex2)){
 				return  _sdf2.parse(strDate);
            }else if(strDate.matches(regex1)){
 				return  _sdf.parse(strDate);
            }else{
            	String[] arr = strDate.split(" ");
    			if( 0<arr.length && !StringUtils.isBlank(arr[0]) ){
    				   if( arr[0].matches(reg_yyyy_MM_dd)){
    						try {
    							return  _sdf1.parse(arr[0]);
    						} catch (ParseException e1) {
    							return null;
    						}
    		            }
    		            if(arr[0].matches(reg_yyyyMMdd)){
    		                try {
    							return  sdf_mmdd1.parse(arr[0]);
    						} catch (ParseException e1) {
    							return null;
    						}
    		            }
    		            if(arr[0].matches(reg_sdfsdf)){
    		                try {
    							return  sdfsdf1.parse(arr[0]);
    						} catch (ParseException e1) {
    							return null;
    						}
    		            }
    				
    		            return null;
    				
    			}else{
    				return null;
    			}
    			
            }
           
		} catch (ParseException e) {
			return null;
		}
		
    }
    
//	public static void main(String[] args) {
//		System.out.println(parseDateForCSVImport("2011-12-30 23:32"));
//		System.out.println(parseDateForCSVImport("2011/12/29 23:45"));
//		System.out.println(parseDateForCSVImport("2011-12-29 23:33:23"));
//		System.out.println(parseDateForCSVImport("2011/12/29 23:34:23"));
//	}
}
