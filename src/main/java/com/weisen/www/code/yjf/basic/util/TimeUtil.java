package com.weisen.www.code.yjf.basic.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;

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
	
}
