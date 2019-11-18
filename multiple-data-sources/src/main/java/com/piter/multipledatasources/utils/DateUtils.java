/**
 */
package com.piter.multipledatasources.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import strman.Strman;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author cly
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	/**
	 * 一天的毫秒数
	 */
	public static final long ONE_DAY_MS = 24 * 60 * 60 * 1000;

	private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
			"yyyy.MM.dd HH:mm", "yyyy.MM","yyyyMMdd"};
	/**
	 * Date格式yyyy-MM-dd
	 * */
	public static String DATE_PATTERN_yyyy_MM_dd = "yyyy-MM-dd";

	/**
	 * Date格式yyyy-MM
	 * */
	public static String DATE_PATTERN_yyyy_MM = "yyyy-MM";

	/**
	 * Date格式yyyyMMdd
	 * */
	public static String DATE_PATTERN_yyyyMMdd = "yyyyMMdd";

	/**
	 * Date格式yyMMdd
	 * */
	public static String DATE_PATTERN_yyMMdd = "yyMMdd";

	/**
	 * Date格式yyyyMM
	 * */
	public static String DATE_PATTERN_yyyyMM = "yyyyMM";

	/**
	 * Date格式HHmmss
	 * HH-24小时制 mm-分钟
	 * */
	public static String DATE_PATTERN_HHmmss = "HHmmss";

	/**
	 * Date格式HH:mm:ss
	 * HH-24小时制 mm-分钟
	 * */
	public static String DATE_PATTERN_HHmmss_2 = "HH:mm:ss";


	/**
	 * Date格式yyyyMMddHHmmss，HH-24小时制， MM-月份，mm-分钟
	 * 20030519160931
	 * */
	public static String DATE_PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";

	/**
	 * Date格式yyyyMMddHHmmss，HH-24小时制， MM-月份，mm-分钟
	 * 2003-05-19 16:09:31
	 * */
	public static String DATE_PATTERN_yyyyMMddHHmmss_2 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Date格式yyyy-MM-dd HH:mm:ss.SSS，HH-24小时制， MM-月份，mm-分钟，SSS为毫秒
	 */
	public static String DATE_PATTERN_yyyyMMddHHmmssSSS = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	public static String getDateStr() {
		return getDate("yyyyMMdd");
	}

	public static String getTranDate() {
		return getDate("yyyyMMdd");
	}
	public static String getTranTime() {
		return getDate("HHmmss");
	}
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 *
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = System.currentTimeMillis() - date.getTime();
		return t / ONE_DAY_MS;
	}

	/**
	 * 获取过去的小时
	 *
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 *
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 *
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	/**
	 * 获取两个日期之间的天数
	 *
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 获取当前时间前一天的时间
	 * @return
	 */
	public static Date getBeforeDay() {
		Date dNow = new Date();// 当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
		dBefore = calendar.getTime(); // 得到前一天的时间
		return dBefore;
	}

	/**
	 * 获取当前日期前一天的日期
	 * @return
	 * @throws ParseException
	 */
	public static Date getYesterDay() throws ParseException {
		Date dNow = new Date();// 当前时间
		Date dBefore = null;
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
		dBefore = cutDate(calendar.getTime()); // 得到前一天的日期
		return dBefore;
	}

	/**
	 * 把一个时间段按天放到list中
	 *
	 * @param starDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getDateList(Date starDate, Date endDate) {
		List<String> dateList = new ArrayList<String>();
		if (starDate.after(endDate)) {
			dateList.add("-1");
			return dateList;
		}
		Calendar beginDate = Calendar.getInstance();
		Calendar overDate = Calendar.getInstance();
		beginDate.setTime(starDate);
		overDate.setTime(endDate);
		Double dbDay = getDistanceOfTwoDate(starDate, endDate);
		int intDay = dbDay.intValue();
		dateList.add(formatDate(beginDate.getTime(), "yyyy-MM-dd"));
		for (int i = 0; i < intDay; i++) {
			beginDate.add(Calendar.DAY_OF_YEAR, 1);
			dateList.add(formatDate(beginDate.getTime(), "yyyy-MM-dd"));
		}

		return dateList;
	}

	/**
	 * 计算两个日期相差的月数（具体细分到天数的差别）
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */

	public static int getDiffMonths(Date date1, Date date2) {

		int iMonth = 0;

		int flag = 0;

		try {

			Calendar objCalendarDate1 = Calendar.getInstance();

			objCalendarDate1.setTime(date1);

			Calendar objCalendarDate2 = Calendar.getInstance();

			objCalendarDate2.setTime(date2);

			if (objCalendarDate2.equals(objCalendarDate1))

				return 0;

			if (objCalendarDate1.after(objCalendarDate2)) {

				return -1;

			}

			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1

					.get(Calendar.DAY_OF_MONTH))

				flag = 1;

			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1

					.get(Calendar.YEAR))

				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1

						.get(Calendar.YEAR))

						* 12 + objCalendarDate2.get(Calendar.MONTH) - flag)

						- objCalendarDate1.get(Calendar.MONTH);

			else

				iMonth = objCalendarDate2.get(Calendar.MONTH)

						- objCalendarDate1.get(Calendar.MONTH) - flag;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return iMonth;

	}

	/**
	 * 把一个时间段按月放到list中
	 *
	 * @param starDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getMonthList(Date starDate, Date endDate) {
		List<String> dateList = new ArrayList<String>();
		if (starDate.after(endDate)) {
			dateList.add("-1");
			return dateList;
		}
		Calendar beginDate = Calendar.getInstance();
		Calendar overDate = Calendar.getInstance();
		beginDate.setTime(starDate);
		overDate.setTime(endDate);
		int intMonth = getDiffMonths(starDate, endDate);
		dateList.add(formatDate(beginDate.getTime(), "yyyy-MM"));
		for (int i = 0; i < intMonth; i++) {
			beginDate.add(Calendar.MONTH, 1);
			dateList.add(formatDate(beginDate.getTime(), "yyyy-MM"));
		}

		return dateList;
	}

	/**
	 * 截取时间 Date "yyyyMMdd"
	 *
	 * @throws ParseException
	 */
	public static Date cutDate(Date date, String pattern) throws ParseException {
		Date cutDate = parseDate(formatDate(date, pattern));
		return cutDate;
	}

	/**
	 * 截取时间 Date "yyyyMMdd"
	 *
	 * @throws ParseException
	 */
	public static Date cutDate(Date date) throws ParseException {
		Date cutDate = parseDate(formatDate(date));
		return cutDate;
	}

	/**
	 * 获取过去的秒
	 * @param date
	 * @return
	 */
	public static long pastSeconds(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (1000);
	}

	/**
	 * 获取当天23：59：59
	 * @return
	 */
	public static Date todayLastDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 获取指定时间当天23：59：59
	 * @return
	 */
	public static Date getLastDate(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 给时间加上或减去指定毫秒，秒，分，时，天、月或年等，返回变动后的时间
	 *
	 * @param date   要加减前的时间，如果不传，则为当前日期
	 * @param field  时间域，有Calendar.MILLISECOND,Calendar.SECOND,Calendar.MINUTE,<br>
	 *               Calendar.HOUR,Calendar.DATE, Calendar.MONTH,Calendar.YEAR
	 * @param amount 按指定时间域加减的时间数量，正数为加，负数为减。
	 * @return 变动后的时间
	 */
	public static Date add(Date date, int field, int amount) {
		if (date == null) {
			date = new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}


	/**
	 *
	 * @param day 日期
	 * @return 当前日期  年 + 月 + day
	 */
	public static Date getCurrentDay(int day){
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.set(Calendar.DATE, day);
		return calendar.getTime();
	}

	/**
	 * 获取yyyy-MM-dd HH:mm:ss.fff格式的new date()
	 * @return
	 */
	public static Date getMillisDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_yyyyMMddHHmmssSSS);
		String datestr = sdf.format(date);
		try {
			date = (Date) sdf.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 获取yyyy-MM-dd HH:mm:ss.fff格式的new date()
	 * @return
	 */
	public static String getMillisDate(Date date) {
		return formatDate(date, DATE_PATTERN_yyyyMMddHHmmssSSS);
	}
	/**
	 * 获取毫秒差
	 *
	 * @param date1
	 * @return
	 */
	public static long pastMillis(Date date1, Date date2) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_yyyyMMddHHmmssSSS);
		long between = 0;
		try {
			Date beginDate = (Date) sdf.parse(sdf.format(date1));
			Date end = (Date) sdf.parse(sdf.format(date2));
			between = end.getTime() - beginDate.getTime();
		} catch (ParseException e) {
		}
		return between;
	}

	private static final String PATTERN_DATE = "yyyy-MM-dd";
	private static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	private static final String PATTERN_TIME = "HH:mm:ss";

	/**
	 * getStartTime
	 *
	 * @param minusDay
	 * @return
	 */
	public static Date getStartTime(Date date,int minusDay) {
		LocalDate localDate = LocalDate.fromDateFields(date);
		localDate = localDate.minusDays(minusDay);
		return strToDate(Strman.append(dateToStr(localDate.toDate(), PATTERN_DATE), " 00:00:00"), PATTERN_DATE_TIME);
	}

	/**
	 * getEndTime
	 *
	 * @param minusDay
	 * @return
	 */
	public static Date getEndTime(Date date,int minusDay) {
		LocalDate localDate = LocalDate.fromDateFields(date);
		localDate = localDate.minusDays(minusDay);
		return strToDate(Strman.append(dateToStr(localDate.toDate(), PATTERN_DATE), " 23:59:59"), PATTERN_DATE_TIME);
	}

	/**
	 * str to date
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Date strToDate(String str, String pattern){
		return DateTime.parse(str, DateTimeFormat.forPattern(pattern)).toDate();
	}


	/**
	 * date to str
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToStr(Date date, String pattern){
		return new DateTime(date).toString(pattern);
	}

	/**
	 * 日期转时间
	 * @param date
	 * @return
	 */
	public static Date dateToDateTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, new Random().nextInt(86400000));
		return calendar.getTime();
	}

	/**
	 * 时间转日期
	 * @param date
	 * @return
	 */
	public static Date dateTimeToDate(Date date){
		return strToDate(dateToStr(date, PATTERN_DATE), PATTERN_DATE);
	}

	/**
	 * minusMonths n
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date minusMonths(Date date, int n){
		return new DateTime(date).minusMonths(n).toDate();
	}

	/**
	 * 获取当前日期
	 * @return
	 */
	public static Date getCurrentDate(){
		return LocalDate.now(DateTimeZone.getDefault()).toDate();
	}

	/**
	 * 获取下月的日期
	 * @param date
	 * @param n
	 * @return
	 */
	public static java.sql.Date getNextMonth(java.sql.Date date, int n){
		return new java.sql.Date(new DateTime(date).plusMonths(n).toDate().getTime());
	}

	/**
	 * 获取所在月第一天
	 * @param date
	 * @return
	 */
	public static java.sql.Date getMonthOfFirstDay(java.sql.Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return new java.sql.Date(calendar.getTime().getTime());
	}

	/**
	 * 获取下一日期
	 * @param date
	 * @param n
	 * @return
	 */
	public static java.sql.Date getNextDate(java.sql.Date date, int n){
		return new java.sql.Date(new DateTime(date).plusDays(n).toDate().getTime());
	}

	/**
	 * 获取当前日期
	 * @return
	 */
	public static java.sql.Date getCurrentDateWithSqlDate(){
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");
		return new java.sql.Date(DateTime.parse(DateTime.now().toString("yyyyMMdd"), format).toDate().getTime());
	}



	/**
	 * 获取date所在年的第一天日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}

	/**
	 * 获取date所在年最后一天日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
		return calendar.getTime();
	}

	/**
	 * 当天是当年的第几天
	 *
	 * @param date
	 * @return
	 */
	public static int dayOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 从date1到data2有多少天
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long days(Date date1, Date date2) {
		return (date2.getTime() - date1.getTime()) / (60 * 60 * 24 * 1000);
	}

	/**
	 * plusDays n
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date plusDays(Date date, int n){
		return new DateTime(date).plusDays(n).toDate();
	}

	/**
	 * minusDays n
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date minusDays(Date date, int n){
		return new DateTime(date).minusDays(n).toDate();
	}

	/**
	 * 获取从begin到end的每一天
	 * @param begin
	 * @param end
	 * @return
	 */
	public static List<Date> everyDay(Date begin,Date end){
		List<Date> dateList = new ArrayList<>();
		long dateEndTime = end.getTime();
		for(Date dateNow = begin; dateEndTime >= dateNow.getTime(); dateNow = DateUtils.plusDays(dateNow,1)){
			dateList.add(dateNow);
		}
		return dateList;
	}

	public static boolean isDate(String str, String pattern){
		try {
			Date date = strToDate(str, pattern);
			// 避免放行isDate("2019-9-1","yyyy-MM-dd")
			return str.equals(dateToStr(date,pattern));
		} catch (Exception e) {
			return false;
		}
	}

}
