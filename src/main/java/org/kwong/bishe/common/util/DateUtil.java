package org.kwong.bishe.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 
 * 日期转换函数
 * 
 * @author
 * 
 */
public class DateUtil {

	public static final String Formater_yyyy_MM_dd = "yyyy-MM-dd";
	public static final String Formater_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String Formater_yyyyMMddHHmmss = "yyyyMMddHHmmss";

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		str = str != null ? str.trim() : str;
		return str == null || "".equals(str) ? true : false;
	}

	/**
	 * 
	 * 获取当前的时间戳
	 * 
	 * @return
	 */
	public static String getCurrentTimestamp() {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		return formater.format(today);
	}

	/**
	 * 获取当前时间数字形式
	 * 
	 * @return
	 */
	public static long getTimeInMillis() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.getTimeInMillis();
	}

	/**
	 * 
	 * Date 对象转换成对应格式的 字符串
	 * 
	 * @param date
	 * @param format
	 *            如："yyyy-MM-dd HH:mm:ss"，默认："yyyy-MM-dd HH:mm:ss"
	 * @return
	 * 
	 */
	public static String dateToString(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat formater = new SimpleDateFormat(isNull(format) ? "yyyy-MM-dd HH:mm:ss" : format.trim());
		return formater.format(date);
	}

	/**
	 * 
	 * Date 对象转换成对应格式的 字符串
	 * 
	 * @param date
	 * @param format
	 *            "yyyy-MM-dd HH:mm:ss"
	 * @return
	 * 
	 */
	public static String dateToyMd(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		return formater.format(date);
	}

	/**
	 * 
	 * Date 对象转换成对应格式的 字符串
	 * 
	 * @param date
	 *            默认："yyyy-MM-dd HH:mm:ss"
	 * @return
	 * 
	 */
	public static String dateToString(Date date) {
		if (date == null)
			return "";
		return dateToString(date, null);
	}

	public static String timestampToString(java.sql.Timestamp timestamp) {
		if (timestamp == null)
			return "";
		return timestamp.toString();
	}

	/**
	 * XMLGregorianCalendar 对象转换成对应格式的 字符串
	 * 
	 * @param cal
	 * @return 默认："yyyy-MM-dd HH:mm:ss"
	 * @throws Exception
	 */
	public static String xMLGregorianToString(XMLGregorianCalendar cal) throws Exception {
		GregorianCalendar ca = cal.toGregorianCalendar();
		return dateToString(ca.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * Date 对象转换成对应格式的 字符串
	 * 
	 * @param dateSource
	 * @param format
	 *            如："yyyy-MM-dd HH:mm:ss"，默认："yyyy-MM-dd HH:mm:ss"
	 * @return
	 * 
	 */
	public static Date timeStrToDate(String dateSource, String format) {
		if (isNull(dateSource))
			return null;
		SimpleDateFormat formater = new SimpleDateFormat(isNull(format) ? "yyyy-MM-dd HH:mm:ss" : format.trim());
		try {
			return formater.parse(dateSource);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * Date 对象转换成对应格式的 字符串
	 * 
	 * @param dateSource
	 *            默认："yyyy-MM-dd HH:mm:ss"
	 * @return
	 * 
	 */
	public static Date timeStrToDate(String dateSource) {
		if (isNull(dateSource))
			return null;
		return timeStrToDate(dateSource, null);
	}

	/**
	 * 得到系统日期
	 * 
	 * @return
	 */
	public static String getDate() {
		Calendar calendar = Calendar.getInstance();
		String year = calendar.get(Calendar.YEAR) + "";
		String month = calendar.get(Calendar.MONTH) + 1 + "";
		String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
		if (month.length() == 1)
			month = "0" + month;

		return year + "-" + month + "-" + day;
	}

	/**
	 * 得到系统日期,xx月xx日 xx xx:xx
	 * 
	 * @return
	 */
	public static String getWapDate() {
		Calendar calendar = Calendar.getInstance();
		String month = calendar.get(Calendar.MONTH) + 1 + "";
		String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
		String hour = calendar.get(Calendar.HOUR_OF_DAY) + "";
		String minute = calendar.get(Calendar.MINUTE) + "";

		if (month.length() == 1)
			month = "0" + month;

		return month + "月" + day + "日 " + hour + ":" + minute;
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 * 
	 * @param nowdate
	 * @param delay
	 * @return
	 */
	public static String getNextDay(String nowdate, int delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = timeStrToDate(nowdate);
			long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 得到一个时间延后或前移几天的日期,nowdate为日期,delay为前移或后延的天数
	 * 
	 * @param nowdate
	 * @param delay
	 * @return
	 */
	public static Date getNextDay(Date nowdate, int delay) {
		Date nextDay = new Date();
		try {
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(nowdate);// 把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, delay); // 设置为前一天
			nextDay = calendar.getTime(); // 得到前一天的时间
			return nextDay;
		} catch (Exception e) {
			return nextDay;
		}
	}

	/**
	 * 
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 * 
	 * @param nowdate
	 * @param delay
	 *            小于 0，过去多小天，大于0 未来多小天
	 * @param dateFormat
	 * @return
	 */
	public static String getNextDay(String nowdate, int delay, String dateFormat) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = timeStrToDate(nowdate, dateFormat);
			long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 返回今天的时间段
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String[] getTodayPeriods() {
		String today = dateToString(new Date(), Formater_yyyy_MM_dd);
		return new String[] { today + " 00:00:00", today + " 23:59:59" };
	}

	/**
	 * 返回今天的时间段
	 * 
	 * @return date
	 */
	public static Date[] getTodayPeriodsDate() {
		String today = dateToString(new Date(), Formater_yyyy_MM_dd);
		return new Date[] { timeStrToDate(today + " 00:00:00"), timeStrToDate(today + " 23:59:59") };
	}

	/**
	 * 返回昨天的时间段
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String[] getYesterdayPeriods() {
		String today = dateToString(new Date(), Formater_yyyy_MM_dd);
		String beforeDay = getNextDay(today, -1, Formater_yyyy_MM_dd);
		return new String[] { beforeDay + " 00:00:00", beforeDay + " 23:59:59" };
	}

	/**
	 * 返回一个星期(7天前)的时间段
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String[] getWeekPeriods() {
		String today = dateToString(new Date(), Formater_yyyy_MM_dd);
		String beforeDay = getNextDay(today, -7, Formater_yyyy_MM_dd);
		return new String[] { beforeDay + " 00:00:00", today + " 23:59:59" };
	}

	/**
	 * 返回本周的时间段
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String[] getThisWeekPeriods() {
		Calendar cal = Calendar.getInstance();
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
		cal.add(Calendar.DATE, -day_of_week);
		Date startDate = cal.getTime();
		cal.add(Calendar.DATE, 6);
		Date endDate = cal.getTime();
		return new String[] { dateToString(startDate, "yyyy-MM-dd") + " 00:00:00", dateToString(endDate, "yyyy-MM-dd") + " 23:59:59" };
	}

	/**
	 * 返回上周的时间段
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String[] getLastWeekPeriods() {
		Calendar cal = Calendar.getInstance();
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 3;
		cal.add(Calendar.DATE, -day_of_week);
		Date startDate = cal.getTime();
		cal.add(Calendar.DATE, 6);
		Date endDate = cal.getTime();
		return new String[] { dateToString(startDate, "yyyy-MM-dd") + " 00:00:00", dateToString(endDate, "yyyy-MM-dd") + " 23:59:59" };
	}

	/**
	 * 返回的一个月时间段
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String[] getMonthDayPeriods(int difMonths) {
		difMonths = difMonths == 0 ? 1 : difMonths;
		String today = dateToString(new Date(), Formater_yyyy_MM_dd);
		String beforeDay = getNextDay(today, -7 * 4 * difMonths, Formater_yyyy_MM_dd);
		return new String[] { beforeDay + " 00:00:00", today + " 23:59:59" };
	}

	/**
	 * 返回当前时间相关difMonths个月的时间
	 * 
	 * @param difMonths
	 * @return
	 */
	public static String getMonthlyTime(int difMonths) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, difMonths);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int date = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		return year + "-" + (month < 10 ? "0" + month : month) + "-" + (date < 10 ? "0" + date : date) + " " + (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":" + (second < 10 ? "0" + second : second);
	}

	/**
	 * 返回当前时间相关difMonths个月的时间 date格式
	 * 
	 * @param difMonths
	 * @return
	 */
	public static Date getMonthlyDate(int difMonths) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, difMonths);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int date = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		return timeStrToDate(year + "-" + (month < 10 ? "0" + month : month) + "-" + (date < 10 ? "0" + date : date) + " " + (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":" + (second < 10 ? "0" + second : second));
	}

	/**
	 * 得到系统当前的时间
	 * 
	 * @return
	 */
	public static String getSysDateTime() {
		return dateToString(new Date(), DateUtil.Formater_yyyy_MM_dd_HH_mm_ss);
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	public static void main(String... args) {
		// LoggerUtil.info(getMonthDayPeriods(1)[0]);
		// LoggerUtil.info(getDiffSend(timeStrToDate("20130222115000",
		// Formater_yyyyMMddHHmmss)));
		LoggerUtil.logger.info(getNextDay(12));

	}

	/**
	 * 对比当前时间与要验证的时间相差的分钟数
	 * 
	 * @param checkTime
	 *            要验证的时间
	 * @param minute
	 *            分钟数
	 * @return 要验证的时间少于当前时间 false 要验证的时间大于当前时间 true
	 */
	public static boolean CheckInDate(String checkTime, int minute) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(checkTime));
			cd.add(Calendar.MINUTE, minute);
			Date nowDate = new Date();

			return cd.getTime().getTime() >= nowDate.getTime();

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获得"yyyy年MM月dd日   星期N"格式的时间输出
	 * 
	 * @param date
	 * @return
	 */
	public final static String getStandardFormatTime(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日   EEEE");

		return format.format(date);

	}

	/**
	 * 当前时间与输入时间相差多少天
	 * 
	 * @param date
	 * @return 输入-当前
	 */
	public static long getDiffDays(Date inputDate) {
		long result = (inputDate.getTime() - getCurrentDate().getTime()) / (24 * 60 * 60 * 1000);
		if ((inputDate.getTime() - getCurrentDate().getTime()) % (24 * 60 * 60 * 1000) > 0) {
			result = result + 1;
		}
		return result;
	}

	/**
	 * 当前时间与输入时间相差多少
	 * 
	 * @param inputDate
	 * @return 输入-当前
	 */
	public static long getDiffSend(Date inputDate) {
		// long result=(inputDate.getTime() - getCurrentDate().getTime())/ (24 *
		// 60 * 60 * 1000);
		// if((inputDate.getTime() - getCurrentDate().getTime())% (24 * 60 * 60
		// * 1000)>0){
		// result=result+1;
		// }
		return (inputDate.getTime() - getCurrentDate().getTime());
	}

	/**
	 * 得到一个时间延后或前移几个月的日期
	 * 
	 * @param delaymonth
	 * @return
	 */
	public static Date getNextDay(int delaymonth) {
		Date nextDay = new Date();
		try {
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(getCurrentDate());// 把当前时间赋给日历
			calendar.add(Calendar.MONTH, delaymonth); // 设置为前一天
			nextDay = calendar.getTime(); // 得到前一天的时间
			return nextDay;
		} catch (Exception e) {
			return nextDay;
		}
	}

	/**
	 * 判断date是否在当前时间之前
	 * 
	 * @param date
	 * @return
	 */
	public static boolean before(Date date) {
		return new Date().before(date);
	}

	/**
	 * 手机号码*处理 hsq 2012-5-17
	 * 
	 * @param str
	 * @return
	 */
	public static String PhoneNumber(String str) {
		if (str != null && str.length() == 11) {
			String s = "";
			String s1 = str.substring(0, 3);
			String s2 = str.substring(7);
			s = s1 + "****" + s2;
			return s;
		} else
			return str;

	}

	/**
	 * 返回 d1-d2的相差的分钟数
	 * 
	 * @param d1
	 *            格式：yyyy-MM-dd HH:mm:ss
	 * @param d2
	 *            格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static long getDiffMins(String date1, String date2) {
		long minCount = 0;
		if (StringUtil.isNulOrBlank(date1) == true || StringUtil.isNulOrBlank(date2) == true) {
			return minCount;
		}
		// LoggerUtil.info("date1:"+date1+",date2:"+date2);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse(date1);
			Date d2 = df.parse(date2);
			long diff = d1.getTime() - d2.getTime();
			long days = diff / (1000 * 60 * 60 * 24);
			minCount = days * 24 * 60;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return minCount;
	}

	/**相对当前相差的天、时、分
	 * @param day
	 * @param hour
	 * @param minute
	 * @return 返回相差的时间
	 */
	public static String beforeForNow(int day,int hour,int minute) {
		Calendar calendar = Calendar.getInstance();

		/* HOUR_OF_DAY 指示一天中的小时 */
		// calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)
		// - 1);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - day);
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - hour);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - minute);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//		System.out.println("一个小时前的时间：" + df.format(calendar.getTime()));
//		System.out.println("当前的时间：" + df.format(new Date()));
		return df.format(calendar.getTime());
	}
}
