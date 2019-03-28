package com.prophet.data.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static void main(String... args) {
		System.out.println(DateUtils.getTomorrowday("20181125", "yyyyMMdd"));
	}

	public static String getNowDate(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);// 设置日期格式
		return df.format(new Date());
	}

	public static String getYesterday(String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date time = cal.getTime();
		return new SimpleDateFormat(format).format(time);
	}

	public static String getTomorrowday(String today, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dt;
		try {
			dt = sdf.parse(today);
		} catch (ParseException e) {
			dt = new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.DATE, 1);
		Date time = cal.getTime();
		return new SimpleDateFormat(format).format(time);
	}

	/*
	 * 将时间戳转换为时间
	 */
	public static String stampToDateTime(String s) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	/*
	 * 将时间戳转换为时间
	 */
	public static String stampToDateTime(long lt) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}
	

	/*
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(long lt) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	
	/*
	 * 将时间归纳到不同时间段
	 */
	public static String changeTimeSlot(long lt) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		if(DateUtils.isInTime("00:00-02:00", res)){
			return "00:00-02:00";
		}else if(DateUtils.isInTime("02:00-04:00", res)) {
			return "02:00-04:00";
		}else if(DateUtils.isInTime("04:00-06:00", res)) {
			return "04:00-06:00";
		}else if(DateUtils.isInTime("06:00-08:00", res)) {
			return "06:00-08:00";
		}else if(DateUtils.isInTime("08:00-10:00", res)) {
			return "08:00-10:00";
		}else if(DateUtils.isInTime("10:00-12:00", res)) {
			return "10:00-12:00";
		}else if(DateUtils.isInTime("12:00-14:00", res)) {
			return "12:00-14:00";
		}else if(DateUtils.isInTime("14:00-16:00", res)) {
			return "14:00-16:00";
		}else if(DateUtils.isInTime("16:00-18:00", res)) {
			return "16:00-18:00";
		}else if(DateUtils.isInTime("18:00-20:00", res)) {
			return "18:00-20:00";
		}else if(DateUtils.isInTime("20:00-22:00", res)) {
			return "20:00-22:00";
		}else if(DateUtils.isInTime("22:00-24:00", res)) {
			return "22:00-24:00";
		}else{
			return "null";
		}

	}

	
	/**
	 * 判断某一时间是否在一个区间内
	 * 
	 * @param sourceTime
	 *            时间区间,半闭合,如[10:00-20:00)
	 * @param curTime
	 *            需要判断的时间 如10:00
	 * @return 
	 * @throws IllegalArgumentException
	 */
	public static boolean isInTime(String sourceTime, String curTime) {
	    if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
	        throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
	    }
	    if (curTime == null || !curTime.contains(":")) {
	        throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
	    }
	    String[] args = sourceTime.split("-");
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    try {
	        long now = sdf.parse(curTime).getTime();
	        long start = sdf.parse(args[0]).getTime();
	        long end = sdf.parse(args[1]).getTime();
	        if (args[1].equals("00:00")) {
	            args[1] = "24:00";
	        }
	        if (end < start) {
	            if (now >= end && now < start) {
	                return false;
	            } else {
	                return true;
	            }
	        } 
	        else {
	            if (now >= start && now < end) {
	                return true;
	            } else {
	                return false;
	            }
	        }
	    } catch (ParseException e) {
	        e.printStackTrace();
	        throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
	    }
	 
	}
	
	
}
