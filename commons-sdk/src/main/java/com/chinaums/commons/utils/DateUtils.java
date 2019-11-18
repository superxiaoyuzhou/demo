package com.chinaums.commons.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import strman.Strman;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 日期工具类
 * 备注：joda：LocalDate 、LocalTime 、Instant 、DateTime 、DateTimeZone 、Duration 、Period、Interval、DateTimeFormat
 *              、Years、Months、Days、Hours、Minutes、Seconds
 *
 * @author xionglei
 * @create 2018-08-14 21:49
 */

public class DateUtils {

    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_TIME = "HH:mm:ss";

    /**
     * 获取当前日期
     * @return
     */
    public static Date getCurrentDate(){
        return getCurrentDate(DateTimeZone.getDefault());
    }

    /**
     * 获取当前日期
     * @param dateTimeZone
     * @return
     */
    public static Date getCurrentDate(DateTimeZone dateTimeZone){
        return LocalDate.now(dateTimeZone).toDate();
    }

    /**
     * 获取当前日期 yyyy-MM-dd
     * @return
     */
    public static String getCurrentDateStr(){
        return getCurrentDateStr(PATTERN_DATE);
    }

    /**
     * 获取当前日期
     * @param pattern
     * @return
     */
    public static String getCurrentDateStr(String pattern){
        return getCurrentDateStr(pattern, DateTimeZone.getDefault());
    }

    /**
     * 获取当前日期
     * @param pattern
     * @param dateTimeZone
     * @return
     */
    public static String getCurrentDateStr(String pattern, DateTimeZone dateTimeZone){
        return new DateTime(getCurrentDate(dateTimeZone)).toString(pattern);
    }

    /**
     * 获取当前日期时间
     * @return
     */
    public static Date getCurrentDateTime(){
        return getCurrentDateTime(DateTimeZone.getDefault());
    }

    /**
     * 获取当前日期时间
     * @param dateTimeZone
     * @return
     */
    public static Date getCurrentDateTime(DateTimeZone dateTimeZone){
        return DateTime.now(dateTimeZone).toDate();
    }

    /**
     * 获取当前日期时间
     * @return
     */
    public static String getCurrentDateTimeStr(){
        return getCurrentDateTimeStr(PATTERN_DATE_TIME);
    }

    /**
     * 获取当前日期时间
     * @param pattern
     * @return
     */
    public static String getCurrentDateTimeStr(String pattern){
        return getCurrentDateTimeStr(pattern, DateTimeZone.getDefault());
    }

    /**
     * 获取当前日期时间
     * @param pattern
     * @param dateTimeZone
     * @return
     */
    public static String getCurrentDateTimeStr(String pattern, DateTimeZone dateTimeZone){
        return DateTime.now(dateTimeZone).toString(pattern);
    }

    /**
     * 获取时区
     * @param id eg: Asia/Shanghai
     * @return
     */
    public static DateTimeZone getDateTimeZone(String id){
        return DateTimeZone.forID(id);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime(){
        return getCurrentTime(DateTimeZone.getDefault());
    }

    /**
     * 获取当前时间
     * @param dateTimeZone
     * @return
     */
    public static String getCurrentTime(DateTimeZone dateTimeZone){
        return LocalTime.now(dateTimeZone).toString(PATTERN_TIME);
    }

    /**
     * str to date
     * @param str
     * @param pattern
     * @return
     */
    public static Date strToDate(String str, String pattern){
        return DateTimeFormat.forPattern(pattern).parseLocalDateTime(str).toDate();
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
     * 时间转日期
     * @param date
     * @return
     */
    public static Date dateTimeToDate(Date date){
        return strToDate(dateToStr(date, PATTERN_DATE), PATTERN_DATE);
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
     * plusMonths n
     * @param date
     * @param n
     * @return
     */
    public static Date plusMonths(Date date, int n){
        return new DateTime(date).plusMonths(n).toDate();
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
     * 获取date所在月第一天日期
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取date所在月最后一天日期
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * getStartTime
     *
     * @param minusDay
     * @return
     */
    public static Date getStartTime(int minusDay) {
        final LocalDate localDate = LocalDate.now(DateTimeZone.getDefault());
        localDate.minusDays(minusDay);
        final Date date = localDate.toDate();
        return strToDate(Strman.append(dateToStr(date, PATTERN_DATE), " 00:00:00"), PATTERN_DATE_TIME);
    }

    /**
     * getEndTime
     *
     * @param minusDay
     * @return
     */
    public static Date getEndTime(int minusDay) {
        final LocalDate localDate = LocalDate.now(DateTimeZone.getDefault());
        localDate.minusDays(minusDay);
        final Date date = localDate.toDate();
        return strToDate(Strman.append(dateToStr(date, PATTERN_DATE), " 23:59:59"), PATTERN_DATE_TIME);
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
     * 比较两个日期的大小
     * @author LiuLi
     * @date 2018/12/13 10:59
     * @return true, date2时间大；false, otherwise
     */
    public static boolean after(Date date1,Date date2){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        return c2.after(c1);
    }

    /**
     * 转换时间为时间段显示
     *
     * @param start 开始时间
     * @param end 结束时间
     * @param split 分割符
     * @author LiuLi
     * @date 2018/12/19 11:31
     * @return 1970.01.01 - 2000.01.01
     */
    public static String convertToDisplayRangeTime(Date start, Date end, String split) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        if(start != null) {
            sb.append(format.format(start));
            sb.append(split);
            if(end != null) {
                sb.append(format.format(end));
            }
        } else {
            return "";
        }
        return sb.toString();
    }

    /**
     * 转换currentTime与timestamp差值为N秒前、N分钱、N小时前、昨天等
     *
     * @param currentTime 当前时间(毫秒)
     * @param timestamp 基准时间(毫秒)
     * @author LiuLi
     * @date 2018/12/18 9:59
     * @return N秒前/N分钱/N小时前/昨天/前天等
     */
    public static String convertToDisplayTime(long currentTime, long timestamp){
        long time = (currentTime - timestamp) / 1000;

        if(time >= 0 && time < 10) {
          return "刚刚";
        } else if (time < 60 && time >= 10) {
            return time + "秒前";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if(time >= 3600 * 24 && time < 3600 * 24 * 2) {
            return "昨天";
        } else if(time >= 3600 * 24 * 2 && time < 3600 * 24 * 3) {
            return "前天";
        } else {
            return new SimpleDateFormat("yyyy.MM.dd").format(timestamp);
        }
    }

    /**
     * 转换currentTime与timestamp差值为已等待N秒、已等待N分钟
     *
     * @param currentTime 当期时间(毫秒)
     * @param timestamp 基准时间(毫秒)
     * @author LiuLi
     * @date 2018/12/18 10:20
     * @return 已等待N秒/已等待N分钟
     */
    public static String convertToDisplayWaitTime(long currentTime, long timestamp) {
        long time = (currentTime - timestamp) / 1000;

        if(time >= 0 && time < 10) {
            return "刚刚";
        } else if (time < 60 && time >= 10) {
            return "已等待" + time + "秒";
        } else if (time >= 60 && time < 3600) {
            return "已等待" + time / 60 + "分钟";
        } else if (time >= 3600 && time < 3600 * 24) {
            return "已等待" + time / 3600 + "小时";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return "已等待" + time / 3600 / 24 + "天";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return "已等待" + time / 3600 / 24 / 30 + "个月";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return "已等待" + time / 3600 / 24 / 30 / 12 + "年";
        } else {
            return "刚刚";
        }
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
     * 校验字符串是否是目标格式日期
     * @param str
     * @param pattern
     * @return
     */
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
