package com.weisen.www.code.yjf.basic.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

import io.micrometer.core.instrument.util.StringUtils;

public class TimeUtil {

	private static int TIME_ZONE = 0;
	
	static {
		Calendar cal = Calendar.getInstance();
	    int offset = cal.get(Calendar.ZONE_OFFSET);
	    cal.add(Calendar.MILLISECOND, -offset);
	    long timeStampUTC = cal.getTimeInMillis();
	    long timeStamp = System.currentTimeMillis();
	    TIME_ZONE = (int)(timeStamp - timeStampUTC) / (1000 * 3600);
	}
	
	public static Instant now () {
		return Instant.now().plus(TIME_ZONE, ChronoUnit.HOURS);
	}
	
	public static boolean isToday (Instant instant) {
		Instant truncatedInstant = instant.truncatedTo(ChronoUnit.DAYS);
		Instant todayInstant = Instant.now().truncatedTo(ChronoUnit.DAYS);
		return truncatedInstant.equals(todayInstant);
	}
	
	public static boolean isBefore (Instant instant, long offset, TemporalUnit unit) {
		return instant.plus(offset, unit).isAfter(Instant.now());
	}
	
	/**
	 * 返回一个当前时间
	 * @return
	 */
	public static String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return format.format(new Date());
	}
	/**
	 * 返回一个时间节点
	 * @return
	 */
	public static String getDateTimes() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		return format.format(new Date());
	}
	
	/**
	 * 将字符串转换为时间
	 * @param timeStr
	 * @return
	 */
	public static Date formatDate(String timeStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try {
			Date date = format.parse(timeStr);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}


    /**
     * 获取当前时间
     *
     * @param //args
     */
    public static String getNowTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     * 判断当天是否为本月第一天
     *
     * @return
     */
    public static boolean isFirstDayOfMonth() {
        boolean flag = false;
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(calendar.DAY_OF_MONTH);
        if (1 == today) {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取当前月份最后一天
     *
//     * @param date
     * @return
     * @throws ParseException
     */
    public static String getMaxMonthDate() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     *
     * 描述:获取下一个月的第一天.
     *
     * @return
     */
    public static String getPerFirstDayOfMonth() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     *
     * 描述:获取上个月的最后一天.
     *
     * @return
     */
    public static String getLastMaxMonthDate() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     * 获取上一个月
     *
     * @return
     */
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     *
     * 描述:获取下一个月.
     *
     * @return
     */
    public static String getPreMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, 1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        String preMonth = dft.format(cal.getTime());
        return preMonth;
    }

//    // 是否是最后一天
//    public static boolean isLastDayOfMonth() {
//        boolean flag = false;
//        if (StringUtils.isNotBlank(getNowTime()) && StringUtils.isNotBlank(getMaxMonthDate()) && StringUtils.equals(getNowTime(), getMaxMonthDate())) { // getMaxMonthDate().equals(getNowTime())
//            flag = true;
//        }
//        return flag;
//    }

    /**
     * 获取任意时间的下一个月
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    public static String getPreMonth(String repeatDate) {
        String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        int year = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(4, 6);
        int month;
        if ("0".equals(monthsString.substring(0, 1))) {
            month = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year,month,Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     * 获取任意时间的上一个月
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    public static String getLastMonth(String repeatDate) {
        String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        int year = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(4, 6);
        int month;
        if ("0".equals(monthsString.substring(0, 1))) {
            month = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year,month-2,Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     * 获取任意时间的月的最后一天
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    private static String getMaxMonthDate(String repeatDate) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        try {
            if(StringUtils.isNotBlank(repeatDate) && !"null".equals(repeatDate)){
                calendar.setTime(dft.parse(repeatDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     * 获取任意时间的月第一天
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    private static String getMinMonthDate(String repeatDate){
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        try {
            if(StringUtils.isNotBlank(repeatDate) && !"null".equals(repeatDate)){
                calendar.setTime(dft.parse(repeatDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }
    /**
     * 不论是当前时间，还是历史时间  皆是时间点的前天
     * repeatDate  任意时间
     */
    public static String getModify2DaysAgo(String repeatDate) {
        Calendar cal = Calendar.getInstance();
        String daysAgo = "";
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        if (repeatDate == null || "".equals(repeatDate)) {
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 2);

        } else {
            int year = Integer.parseInt(repeatDate.substring(0, 4));
            String monthsString = repeatDate.substring(4, 6);
            int month;
            if ("0".equals(monthsString.substring(0, 1))) {
                month = Integer.parseInt(monthsString.substring(1, 2));
            } else {
                month = Integer.parseInt(monthsString.substring(0, 2));
            }
            String dateString = repeatDate.substring(6, 8);
            int date;
            if ("0".equals(dateString.subSequence(0, 1))) {
                date = Integer.parseInt(dateString.substring(1, 2));
            } else {
                date = Integer.parseInt(dateString.substring(0, 2));
            }
            cal.set(year, month-1, date - 1);
            System.out.println(dft.format(cal.getTime()));
        }
        daysAgo = dft.format(cal.getTime());
        return daysAgo;
    }

    /**
     * 不论是当前时间，还是历史时间  皆是时间点的T-N天
     * repeatDate 任意时间    param 数字 可以表示前几天
     */
    public static String getModifyNumDaysAgo(String repeatDate,int param) {
        Calendar cal = Calendar.getInstance();
        String daysAgo = "";
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        if (repeatDate == null || "".equals(repeatDate)) {
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - param);

        } else {
            int year = Integer.parseInt(repeatDate.substring(0, 4));
            String monthsString = repeatDate.substring(4, 6);
            int month;
            if ("0".equals(monthsString.substring(0, 1))) {
                month = Integer.parseInt(monthsString.substring(1, 2));
            } else {
                month = Integer.parseInt(monthsString.substring(0, 2));
            }
            String dateString = repeatDate.substring(6, 8);
            int date;
            if ("0".equals(dateString.subSequence(0, 1))) {
                date = Integer.parseInt(dateString.substring(1, 2));
            } else {
                date = Integer.parseInt(dateString.substring(0, 2));
            }
            cal.set(year, month-1, date - param+1);
            System.out.println(dft.format(cal.getTime()));
        }
        daysAgo = dft.format(cal.getTime());
        return daysAgo;
    }


}
