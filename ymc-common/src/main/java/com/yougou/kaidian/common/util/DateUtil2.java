package com.yougou.kaidian.common.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * <p>Title: 时间操作工具</p>
 * <p>Description: SOC基础技术平台</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: </p>
 * @author sbruan
 * @version 1.0
 */
public class DateUtil2 {
    public String formatDate(java.util.Date date) {
        return formatDateByFormat(date, "yyyy-MM-dd");
    }

    public static String formatDateByFormat(java.util.Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static java.util.Date parseDate(java.sql.Date date) {
        return date;
    }

    public static java.sql.Date parseSqlDate(java.util.Date date) {
        if (date != null) {
            return new java.sql.Date(date.getTime());
        } else {
            return null;
        }
    }

    public static String format(java.util.Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                java.text.DateFormat df = new java.text.SimpleDateFormat(format);
                result = df.format(date);
            }
        } catch (Exception e) {
        }
        return result;
    }

    public static String format(java.util.Date date) {
        return format(date, "yyyy/MM/dd");
    }

    public static String format1(java.util.Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static int getYear(java.util.Date date) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        return c.get(java.util.Calendar.YEAR);
    }

    public static int getMonth(java.util.Date date) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        return c.get(java.util.Calendar.MONTH) + 1;
    }

    public static int getDay(java.util.Date date) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        return c.get(java.util.Calendar.DAY_OF_MONTH);
    }

    public static int getHour(java.util.Date date) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        return c.get(java.util.Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(java.util.Date date) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        return c.get(java.util.Calendar.MINUTE);
    }

    public static int getSecond(java.util.Date date) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        return c.get(java.util.Calendar.SECOND);
    }

    public static long getMillis(java.util.Date date) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    public static int getWeek(java.util.Date date) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(java.util.Calendar.DAY_OF_WEEK);
        dayOfWeek = dayOfWeek - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        return dayOfWeek;
    }

    public static String getDate(java.util.Date date) {
        return format(date, "yyyy/MM/dd");
    }
    
    public static String getFormatedDate(java.util.Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String getDate(java.util.Date date,String formatStr) {
        return format(date, formatStr);
    }


    public static String getTime(java.util.Date date) {
        return format(date, "HH:mm:ss");
    }

    public static String getDateTime(java.util.Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    
    /**
     * 日期相加
     * @param date Date
     * @param day int
     * @return Date
     */
    public static java.util.Date addDate(java.util.Date date, int day) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }

    /**
     * 日期相减
     * @param date Date
     * @param date1 Date
     * @return int
     */
    public static int diffDate(java.util.Date date, java.util.Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
    }
    
    /**
     * 距离今天有多少天
     * @param String yyyy-MM-dd
     * @return int
     */
    public static int getDiffDateFromToday(String date) {
    	
    	Date originalDate ;
    	try {
			 originalDate = getdate1(date+" 00:00:00");
			 if ( null==date ){
		    	return 0;// 无效
		     }
			 int diff = diffDate(originalDate,getCurrentDateTimeFromZero());
			 return diff;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;// 无效
		}
      
    }
    /**
     * 
     * @param date eg:2015-08-11
     * @return
     */
    public static int getDiffDateFromToday(Date date){
    	try {
			 int diff = diffDate(date,getCurrentDateTimeFromZero());
			 return diff;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;// 无效
		}
    }
    
    /**
     * 日期相减(返回秒值)
     * @param date Date
     * @param date1 Date
     * @return int
     * @author 
     */
    public static Long diffDateTime(java.util.Date date, java.util.Date date1) {
        return (Long) ((getMillis(date) - getMillis(date1))/1000);
    }

    public static java.util.Date getdate(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }

    public static java.util.Date getdate1(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);
    }
    
    public static java.util.Date getMaxTimeByStringDate(String date) throws Exception {
    	String maxTime = date + " 23:59:59";
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return sdf.parse(maxTime);
    }
    /**
     * 获得当前时间
     * @return
     */
    public static Date getCurrentDateTime()
    {
    	Date date=new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    	
        String result = getDateTime(date);
    	try {
			return sdf.parse(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
    	
    }
    
    /**
     * 获得当前时间00:00:00
     * @return
     */
    public static Date getCurrentDateTimeFromZero()
    {
    	Date date = new Date();
    	GregorianCalendar gc = new GregorianCalendar(); 
    	gc.setTime(date);
    	Date date2 = new Date( date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60 * 1000
    					- gc.get(gc.MINUTE) * 60 * 1000 - gc.get(gc.SECOND) * 1000);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    	
        String result = getDateTime( date2 );
    	try {
			return sdf.parse(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
    	
    }
    
    public static Date getCurrentDate()
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String result = getFormatedDate( Calendar.getInstance().getTime() );
		try {
			return sdf.parse(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    public static String getCurrentDateTimeToStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(getCurrentDateTime());
	}
    public static String getCurrentDateTimeToStr2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(getCurrentDateTime());
	}

    public static Long getWmsupdateDateTime() {
		Calendar cl=Calendar.getInstance();
		
		return cl.getTimeInMillis();
	}
    
    public static Integer getLeftSeconds(String date)throws Exception{
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date condition = sdf.parse(date);
    	long n = condition.getTime();
    	long s = sdf.parse(getCurrentDateTimeToStr2()).getTime();
//    	System.out.println("开始时间:"+date+"-->"+(int)((s-n)/1000));
    	return (int)((s-n)/1000);
    }
    
    /**
     * 获得时间戳
     * @return
     * @throws Exception
     */
    public static String getTime(){
    	Date date=new Date();
    	return String.valueOf(date.getTime());
    }
    /**
     * 时间字符串转换成数字格式，精确到秒
     * @param strDate
     * @return
     * @throws Exception
     */
    public static int getToSecondsByStrDate(String strDate)throws Exception{
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date condition = sdf.parse(strDate);
    	return (int)(condition.getTime()/1000);
    }
    
    /**
     * 拿到N天前的当前时刻(周末不记时)
     * @return String
     * 
     */
    public static String getEdgeDateTimeFromNow(long day){
    	Calendar c = generateEdgeDateTimeFromNow(day);
    	return getDateTime(c.getTime());
    }
    
    /**
     * 拿到N天前的当前时刻(周末不记时)
     * @return String
     * 
     */
    public static Date generateEdgeDateTimeFromNow(Date date,long day){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	int weekOfDay = c.get(Calendar.DAY_OF_WEEK);
    	long daysBefore =  day;
    	if(weekOfDay==2 || weekOfDay==3 ){//周一 或 周二
    		daysBefore = daysBefore + 2;
    	}
        c.setTimeInMillis(getMillis(date) - daysBefore * 24 * 3600 * 1000);
    	return c.getTime();
    }
    
    /**
     * 拿到N天前的当前时刻(周末不记时)
     * @return String
     * 
     */
    public static Calendar generateEdgeDateTimeFromNow(long day){
    	Date date = new Date();
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	int weekOfDay = c.get(Calendar.DAY_OF_WEEK);
    	long daysBefore =  day;
    	if(weekOfDay==2 || weekOfDay==3 ){//周一 或 周二
    		daysBefore = daysBefore + 2;
    	}
        c.setTimeInMillis(getMillis(date) - daysBefore * 24 * 3600 * 1000);
    	return c;
    }
    
    /*********************************/
    /**
     * 获取创建时间距离规定期限（3天不包括周日）还剩余的时间（毫秒数）
     * @param args
     */
    public static int getReplyLeftTime(Date createTime,long day){
    	Date date = new Date();
    	if( null!=createTime ){
    		Calendar curDate = Calendar.getInstance();
    		curDate.setTime(date);
    		Calendar startDate = Calendar.getInstance();
    		startDate.setTime(createTime);
    		long left = 0L;
    		long daysBefore =  day;
    		// 1.考虑 到期的所有情况
    		Date edgeDate = generateEdgeDateTimeFromNow(date,day);
//    		System.out.println("edgeDate: "+sdf.format(edgeDate.getTime()));////
    		if( edgeDate.compareTo(createTime) >=0){
    			return 0;
    		}
    		//2.考虑 不需要涉及周末的简单情况
    		int weekOfCurDate = curDate.get(Calendar.DAY_OF_WEEK);
//    		System.out.println("startDate: "+sdf.format(startDate.getTime()));////
//    		System.out.println("curDate: "+sdf.format(curDate.getTime()));////
    		
    		Calendar weekendStartDate = getSaturdayDateOfTheWeek(createTime);// 周末的初始时刻（以createTime为坐标,同周的周末）
//    		System.out.println("weekendStartDate: "+sdf.format(weekendStartDate.getTime()));////
    		
    		Calendar weekendEndDate =  getSaturdayDateOfTheWeek(createTime);
    		weekendEndDate.add(Calendar.DATE, 2);// 周末的结束时刻（以createTime为坐标）
//    		System.out.println("weekendEndDate: "+sdf.format(weekendEndDate.getTime()));////
    		if( startDate.before(weekendStartDate) && curDate.before(weekendStartDate) ){
    			left = startDate.getTimeInMillis() + daysBefore * 24 * 3600 * 1000  - curDate.getTimeInMillis();
    		}else{
	    		// 3.需要考虑周末的情况
	    		//  3.1  跨周末两整天
	    		if( startDate.before(weekendStartDate) && curDate.after(weekendEndDate ) ){
	    			daysBefore = day +2;
	    			left =  startDate.getTimeInMillis() + daysBefore * 24 * 3600 * 1000  - curDate.getTimeInMillis();	
	    		}else{
		    		//  3.2 起始日在周末 
		    		if(  startDate.before(weekendEndDate) && startDate.after(weekendStartDate) ){
		    			if(weekOfCurDate==7 || weekOfCurDate ==1){
		    				left = day * 24 * 3600 * 1000 + 1000;
		    			}else{
		    				//从周一00:00:00算起始日
		    				left = weekendEndDate.getTimeInMillis() + daysBefore * 24 * 3600 * 1000  - curDate.getTimeInMillis();
		    			}
		    		}else{
		    		
		    		//  3.3 起始日不在周末
		    			// 3.3.1当前时刻在周末
		    			if( weekOfCurDate==7 || weekOfCurDate==1){
		    				// 拿到周六的日期
		    				left = startDate.getTimeInMillis() + daysBefore * 24 * 3600 * 1000  -  weekendStartDate.getTimeInMillis();
		    			}else{
		    				// 3.3.2当前时刻 不在周末 
		    				System.out.println(" can not come into here! if come into here,it proves bug here.");// 跨周末的情况已处理
		    			}
		    		}
	    		}
    		}
    		
    		if(left>0){
    			return (int)left;
    		}else{
    			return 0;
    		}
    		
    	}else{
    		return -1;
    	}
    }
    
    //拿到当前周的周一的：00:00:00 时刻的日期
    public static Calendar getMondayDateOfTheWeek(Date date){
    	Calendar tempDate = Calendar.getInstance();
    	tempDate.setTime(date);
    	Calendar result = Calendar.getInstance();
    	int day_of_week = tempDate.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
               day_of_week = 7;
        tempDate.add(Calendar.WEEK_OF_MONTH, 0); //idx 参数，0为当前，1为下周 -1为上周以此类推      
        tempDate.add(Calendar.DATE, -day_of_week + 1);
        result.set(tempDate.get(Calendar.YEAR), tempDate.get(Calendar.MONTH), tempDate.get(Calendar.DATE), 0, 0, 0);           
    	return result;
    }
    
    //拿到当前周的周六的：00:00:00 时刻的日期
    public static Calendar getSaturdayDateOfTheWeek(Date date){
    	Calendar tempDate = Calendar.getInstance();
    	tempDate.setTime(date);
    	Calendar result = Calendar.getInstance();
    	int day_of_week = tempDate.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
               day_of_week = 7;
        tempDate.add(Calendar.WEEK_OF_MONTH, 0); //idx 参数，0为当前，1为下周 -1为上周以此类推      
        tempDate.add(Calendar.DATE, -day_of_week + 6);
        result.set(tempDate.get(Calendar.YEAR), tempDate.get(Calendar.MONTH), tempDate.get(Calendar.DATE), 0, 0, 0);  
        tempDate = null;
        return result;
    }
    
    // a integer( seconds ) to xx:xx:xx
    public static String[] secToTime(int time) {
    	time = time/1000;
        String[] timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return new String[]{"00","00","00"};
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = new String[]{"00",unitFormat(minute) , unitFormat(second)};
            } else {
                hour = minute / 60;
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr =new String[]{ unitFormat(hour) , unitFormat(minute) , unitFormat(second)};
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }
    
    /*********************************/
    // 判断是否周末
    public static boolean isWeekDay(Date date){
    	if( null!=date ){
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(date);
    		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    		if( dayOfWeek==1 || dayOfWeek==7 ){
    			return true;
    		}
    	}
    	return false;
    }
    // 赔付管理：图片上传的路径 加上 年份 月份 日 三层目录
    public static String initPicPathForCompensate(){
    	String date = getDate(new Date(), "yyyyMMdd");
		String datePath ="2015";
		String monthPath = "12";
		String dayPath = "30";
		if(StringUtils.isNotEmpty(date) && date.length()>=8){
			datePath = date.substring(0,4);
			monthPath = date.substring(4,6);
			dayPath =  date.substring(6,8);
		}
    	return datePath+ monthPath+ dayPath; 
    }
    
//    public static void main(String[] args) {
//        try {
//        	Date date = new Date();
//        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        	Calendar createDate = Calendar.getInstance();
//        	createDate.setTime(date);
//        	createDate.add(Calendar.DATE, -2);
//        	createDate.add(Calendar.MINUTE, -2);
//        	System.out.println("path:"+initPicPathForCompensate());
////        	System.out.println("left:"+ DateUtil2.getReplyLeftTime(createDate.getTime(),2) );
////        	System.out.println("left:"+ (24 * 3600 * 1000 ) );
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}
