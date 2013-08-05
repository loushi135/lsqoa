 package com.xpsoft.core.util;
 
 import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
 public class DateUtil
 {
   private static final Log logger = LogFactory.getLog(DateUtil.class);
   
   /**
	 * defaultSimpleFormater = "yyyy-MM-dd HH:mm:ss"
	 */
	public static String defaultSimpleFormater = "yyyy-MM-dd HH:mm:ss";// hh:mm:ss
 
   public static Calendar setStartDay(Calendar cal)
   {
     cal.set(11, 0);
     cal.set(12, 0);
     cal.set(13, 0);
     return cal;
   }
 
   public static Calendar setEndDay(Calendar cal) {
     cal.set(11, 23);
     cal.set(12, 59);
     cal.set(13, 59);
     return cal;
   }
 
   public static void copyYearMonthDay(Calendar destCal, Calendar sourceCal)
   {
     destCal.set(1, sourceCal.get(1));
     destCal.set(2, sourceCal.get(2));
     destCal.set(5, sourceCal.get(5));
   }
 
   public static String formatEnDate(Date date)
   {
     SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
 
     return sdf.format(date).replaceAll("上午", "AM").replaceAll("下午", "PM");
   }
 
   public static Date parseDate(String dateString) {
     Date date = null;
     try {
       date = DateUtils.parseDate(dateString, new String[] { "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm", "yyyy-MM-dd","yyyy" });
     } catch (Exception ex) {
       logger.error("Pase the Date(" + dateString + ") occur errors:" + 
         ex.getMessage());
     }
     return date;
   }
	/**
	 * 转换成日期
	 * 
	 * @param dateString
	 * @param formatString
	 * @return
	 */
	public static Date parse(String dateString, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		try {
			return df.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	

	/**
	 * 转换成日期(包括时间)
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parse(String dateString) {
		return parse(dateString, defaultSimpleFormater);
	}
	
	/**
	 * 现在
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date(System.currentTimeMillis());
	}
	
	/**
	 * 格式化日期(使用默认格式)
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, defaultSimpleFormater);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param formatString
	 * @return
	 */
	public static String format(Date date, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		return df.format(date);
	}
	
	public static String shortYear(){
		Integer year = year();
		return year.toString().substring(2);
	}
	/**
	 * 年份
	 * 
	 * @return
	 */
	public static int year() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR);
	}

	/**
	 * 月份
	 * 
	 * @return
	 */
	public static int month() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.MONTH) + 1;
	}

	/**
	 * 日(号)
	 * 
	 * @return
	 */
	public static int day() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 小时(点)
	 * 
	 * @return
	 */
	public static int hour() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.HOUR);
	}

	/**
	 * 分钟
	 * 
	 * @return
	 */
	public static int minute() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.MINUTE);
	}

	/**
	 * 秒
	 * 
	 * @return
	 */
	public static int second() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.SECOND);
	}

	/**
	 * 星期几(礼拜几)
	 * 
	 * @return
	 */
	public static int weekday() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 是上午吗?
	 * 
	 * @return
	 */
	public static boolean isAm() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.AM_PM) == 0;
	}

	/**
	 * 是下午吗?
	 * 
	 * @return
	 */
	public static boolean isPm() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.AM_PM) == 1;
	}

	/**
	 * 某天是上午吗?
	 * 
	 * @return
	 */
	public static boolean someTimeIsAm(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.AM_PM) == 0;
	}

	/**
	 * 某天是下午吗?
	 * 
	 * @return
	 */
	public static boolean someTimeIsPm(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.AM_PM) == 1;
	}
   
   
 }

