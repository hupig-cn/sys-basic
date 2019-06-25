package com.weisen.www.code.yjf.basic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

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
	
}
