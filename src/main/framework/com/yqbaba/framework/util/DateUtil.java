package com.yqbaba.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date dateOnly(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(df.format(date));
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 返回日期格式： yyyy-MM-dd
	 */
	public static Date parse(String str) {
		return parse(str, null);
	}

	public static Date parse(String str, String pattern) {
		if (pattern == null) {
			pattern = "yyyy-MM-dd";
		}
		DateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(str);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date addMonths(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	public static Date addYears(Date date, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}

	public static Date daysAgo(int days) {
		return DateUtil.addDays(new Date(), -days);
	}

	public static int daysBetween(Date early, Date late) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(early);
		long time1 = cal.getTimeInMillis();
		cal.setTime(late);
		long time2 = cal.getTimeInMillis();
		long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(betweenDays));
	}

	public static Date now() {
		return new Date();
	}
}
