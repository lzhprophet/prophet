package com.prophet.web.framework.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * <P>
 * 日期工具类
 * </P>
 * 
 * @author lvzhh
 */
public class DateUtil {

	/** 日期格式(yyyy-MM-dd) */
	public static final String yyyy_MM_dd_EN = "yyyy-MM-dd";
	/** 日期格式(yyyy/MM/dd) */
	public static final String yyyy_MM_dd_decline = "yyyy/MM/dd";
	/** 日期格式(yyyyMMdd) */
	public static final String yyyyMMdd_EN = "yyyyMMdd";
	/** 日期格式(yyyy-MM) */
	public static final String yyyy_MM_EN = "yyyy-MM";
	/** 日期格式(yyyyMM) */
	public static final String yyyyMM_EN = "yyyyMM";
	/** 日期格式(yyyy-MM-dd HH:mm:ss) */
	public static final String yyyy_MM_dd_HH_mm_ss_EN = "yyyy-MM-dd HH:mm:ss";
	/** 日期格式(yyyy-MM-dd HH:mm:ss.S) */
	public static final String yyyy_MM_dd_HH_mm_ss_S_EN = "yyyy-MM-dd HH:mm:ss.S";
	/** 日期格式(yyyyMMddHHmmss) */
	public static final String yyyyMMddHHmmss_EN = "yyyyMMddHHmmss";

	/**
	 * 获取当前日期时间yyyy-MM-dd HH:mm:ss的形式
	 * 
	 * @return
	 */
	public static String getCurDateTime() {
		DateFormat df = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss_EN);
		return df.format(new Date());
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date, String format) {
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		String str = dateformat.format(date);
		return str;
	}

	public static BigDecimal minusDate(String dateFormat, String startDate, String endDate) {
		// "yyyy-MM-dd HH:mm:ss"
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		Date start = null;
		Date end = null;
		BigDecimal dayHour = new BigDecimal(60 * 60 * 1000);
		BigDecimal result = new BigDecimal(0);
		if (ObjectUtil.isObjectEmpty(startDate) || ObjectUtil.isObjectEmpty(endDate))
			return null;

		try {
			start = df.parse(startDate);
			end = df.parse(endDate);
			result = new BigDecimal(end.getTime() - start.getTime()).divide(dayHour, 1, BigDecimal.ROUND_HALF_DOWN);

		} catch (ParseException e) {
			result = new BigDecimal(-999);
		}
		return result;
	}

}
