package com.weisen.www.code.yjf.basic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 陈柏林
 */
public class DateUtils {
    /**
     * 获取yyyy-MM-dd HH:mm:ss 格式的当前时间
     * @return
     */
    public static String getDateForNow(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    /**
     * 将时间转换成yyyy-MM-dd HH:mm:ss 格式的时间
     * @param date
     * @return
     */
    public static String changeDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDate = simpleDateFormat.format(date);
        return newDate;
    }

    /**
     * 转换当前时间到月
     * @param date
     * @return
     */
    public static String changeDateToMonth(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String newDate = simpleDateFormat.format(date);
        return newDate;
    }

    /**
     * 转换当前时间到日
     * @param date
     * @return
     */
    public static String changeDateToDay(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = simpleDateFormat.format(date);
        return newDate;
    }

    /**
     * 转换当前时间的小时
     * @param date
     * @return
     */
    public static String changeDateToHour(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        String newDate = simpleDateFormat.format(date);
        return newDate;
    }

    /**
     * 传入时间比较当前时间的时间戳加上新的长度 与传入时间对比,大于返回1,小于返回-1
     * @param oldDate
     * @param number
     * @return
     */
    public static Integer checkNowByLongToInteger(Date oldDate,Long number){
        Date date = new Date();
        long newTime = date.getTime() + number;
        Date newDate = new Date(newTime);
        if(newDate.getTime() - oldDate.getTime() > 0){
            return 1;
        }else if(newDate.getTime() - oldDate.getTime() < 0){
            return -1;
        }else {
            return 0;
        }
    }

    /**
     *  传入时间比较当前时间的时间戳加上新的长度 与传入时间对比,大于返回true,小于返回false
     * @param oldDate
     * @param number
     * @return
     */
    public static Boolean checkNowByLongToBoolean(Date oldDate,Long number){
        if(new Date().getTime() - oldDate.getTime() > number){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 仅比对日期,慎用
     * @param oldDate
     * @param number
     * @return
     * @throws ParseException
     */
    public static Boolean checkNowByLongToBoolean(String oldDate,Long number) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(new Date().getTime() - parse.getTime() > number){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 仅比对分钟
     * @param oldDate
     * @param number
     * @return
     */
    public static boolean checkNowByMinthToBoolean(String oldDate, Long number) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(new Date().getTime() - parse.getTime() > number){
            return true;
        }else {
            return false;
        }
    }
}
