package com.anl.card.util;

import org.joda.time.DateTimeUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

	public static long OFFSET = 0;

	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";

	public static final String DATE_FORMAT_COMPACT = "yyyyMMdd";

	public static final String DATE_FORMAT_COMPACTFULL = "yyyyMMddHHmmss";

	public static final String DATE_YEAR_MONTH = "yyyyMM";

	public static final String DATE_FORMAT_FULL_MSEL = "yyyyMMddHHmmssSSSS";
	public static final String DATE_FORMAT_FULL_MSE = "yyyyMMddHHmmssSSS";
	public static final String DATE_FORMAT_HOUR = "HH-mm";

	private static void setOffset() {
		DateTimeUtils.setCurrentMillisOffset(OFFSET);
	}

	public static Date getCurrentDate() {
		setOffset();
		Date currentDate = new Date(DateTimeUtils.currentTimeMillis());
		return currentDate;
	}

	/**
	 * 得到当前日期
	 * 
	 * @return String 当前日期 yyyyMMdd HH:mm:ss格式
	 * @author kevin
	 */
	public static String getCurDateTime() {
		DateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FULL);
		return sdf.format(getCurrentDate());
	}

	public static String getCurDateTime(String format) {
		try {
			DateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(getCurrentDate());
		} catch (Exception e) {
			return getCurDateTime(); // 如果无法转化，则返回默认格式的时间。
		}
	}

	/**
	 * 获取当天开始的时间
	 * 
	 * @return
	 */
	public static Date getTodayStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		return cal.getTime();
	}

	public static Date getTodayEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		return cal.getTime();
	}

	public static int getCurrentHour() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public static int getCurrentMinutes() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		return cal.get(Calendar.MINUTE);
	}


	public static Date getFirstDayOfNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		return cal.getTime();
	}

	public static Date afterNDaysDate(int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		cal.add(Calendar.DAY_OF_YEAR, days);
		return cal.getTime();
	}
	public static Date afterNDaysDate(Date date,int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, days);
		return cal.getTime();
	}

	public static Date afterNSecondsDate(int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		cal.add(Calendar.SECOND, seconds);
		return cal.getTime();
	}

	public static Date afterNMonthDate(int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	/**
	 * 
	 * getTimeString 方法描述: 将秒数转换成 1:34:44的格式 逻辑描述:
	 * 
	 * @return
	 * @since Ver 1.00
	 */
	public static String getTimeString(Integer seconds) {
		if (seconds == null) {
			return "00:00";
		}
		String result = "";
		long h = seconds / 3600;// 时
		long m = (seconds - h * 3600) / 60;// 分
		long s = seconds - h * 3600 - m * 60;// 秒
		if (s > 0) {
			if (s >= 10) {
				result = s + "";
			} else {
				result = "0" + s;
			}
		} else if (h == 0 && m == 0) {
			result = "00:00";
		} else {
			result = "00";
		}
		if (h == 0 && m == 0) {
			return "00:" + result;
		}
		if (m > 0) {
			if (m >= 10) {
				result = m + ":" + result;
			} else {
				result = "0" + m + ":" + result;
			}
		} else {
			result = "00:" + result;
		}
		if (h == 0) {
			return result;
		}
		if (h > 0) {
			if (h >= 10) {
				result = h + ":" + result;
			} else {
				result = "0" + h + ":" + result;
			}
		}
		return result;
	}

	/**
	 * 字符串转日期
	 * 
	 * @return Date
	 * @author kevin
	 */
	public static Date parseStringToDate(String thedate, String format) {
		DateFormat sdf = new SimpleDateFormat(format);
		Date dd1 = null;
		try {
			dd1 = sdf.parse(thedate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dd1;
	}

	public static Date afterNSecondsDate(Date date, int time) {
		Date dt = null;
		try {
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(date);
			rightNow.add(Calendar.SECOND, time);
			dt = rightNow.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}

	// 比较两个字符串格式日期大小,带格式的日期
	public static boolean isBefore(String strdat1, String strdat2, String format) {
		try {
			Date dat1 = parseStringToDate(strdat1, format);
			Date dat2 = parseStringToDate(strdat2, format);
			return dat1.before(dat2);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 日期转成字符串
	public static String dateToString(Date date, String format) {
		String str = null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		// df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		str = df.format(date);
		return str;
	}

	// 获取N天之后的时间
	public static String getNDate(Date date, String format, Integer ndate) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, ndate);
		return sdf.format(cal.getTime());
	}

	// 比较两个时间相差了多少个小时
	public static long betweenHours(Date date1, Date date2) {
		return (date1.getTime() - date2.getTime()) / 1000 / 60 / 60;
	}
	
	public static long betweenSecond(Date date1, Date date2) {
		return (date1.getTime()-date2.getTime())/1000;
	}
	
	/**
	 * 得到时间戳
	 * 
	 * @param null
	 * @return String 当前日期时间戳(yyyyMMddHHmmssSSSS)
	 * @author kevin
	 */
	public static String getTimeStamp() {
		try {
			Calendar now = Calendar.getInstance(TimeZone.getDefault());
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FULL_MSEL);
			sdf.setTimeZone(TimeZone.getDefault());
			return (sdf.format(now.getTime()));
		} catch (Exception e) {
			return getCurDateTime(); // 如果无法转化，则返回默认格式的时间。
		}
	}
	
	/**
	 * 得到时间戳
	 * 
	 * @param null
	 * @return String 当前日期时间戳(yyyyMMddHHmmssSSS)
	 * @author kevin
	 */
	public static String getTimeStamp1() {
		try {
			Calendar now = Calendar.getInstance(TimeZone.getDefault());
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FULL_MSE);
			sdf.setTimeZone(TimeZone.getDefault());
			return (sdf.format(now.getTime()));
		} catch (Exception e) {
			return getCurDateTime(); // 如果无法转化，则返回默认格式的时间。
		}
	}

	/*
	 * 活动页面日期格式
	 */
	public static String toDateString(Date date) {
		String reslut = "";
		Date now = DateUtil.getCurrentDate();
		long hours = DateUtil.betweenHours(now, date);
		if (hours == 0) {
			reslut = "刚刚更新";
		} else if (hours < 24) {
			reslut = hours + "小时前";
		} else {
			reslut = DateUtil.dateToString(date, DateUtil.DATE_FORMAT_SHORT);
		}
		return reslut;
	}

	/**
	 * 
	 * @param flashTime 刷新时间
	 * @param waitTime	等待时间
	 * @param nowTime	生成用户对应宝盒的对应时间
	 * @return
	 * @throws Exception
	 */
	public static Date getChangeTime(Date flashTime, Integer waitTime, Date nowTime) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_HOUR);
		String flashTimeStr = dateFormat.format(flashTime);
		String[] flashTimeArr = flashTimeStr.split("-");
		Integer waitTimeHour = waitTime / 60;
		Integer waitTimeMin = waitTime % 60;
		Integer flashTimeHour = Integer.parseInt(flashTimeArr[0]);
		Integer flashTimeMin = Integer.parseInt(flashTimeArr[1]);
		Integer timeMin = waitTimeMin + flashTimeMin;
		Integer timeHour = waitTimeHour + flashTimeHour;
		SimpleDateFormat dateFormat2 = new SimpleDateFormat(DATE_FORMAT_SHORT);
		String[] nowTimeStr = dateFormat2.format(nowTime).split("-");
		timeHour = timeHour + timeMin / 60;
		timeMin = timeMin % 60;
		nowTimeStr[nowTimeStr.length - 1] = String.valueOf(Integer.parseInt(nowTimeStr[nowTimeStr.length - 1])  + timeHour / 24);
											
		timeHour = timeHour % 24;
		String dateStr = "";
		for (int i = 0; i < nowTimeStr.length; i++) {
			if (i == nowTimeStr.length - 1) {
				dateStr = dateStr + nowTimeStr[i];
			} else {
				dateStr = dateStr + nowTimeStr[i] + "-";
			}
		}
		dateStr = dateStr+" "+timeHour+":"+timeMin+":00";
		SimpleDateFormat dateFormat3 = new SimpleDateFormat(DATE_FORMAT_FULL);
		return dateFormat3.parse(dateStr);
	}

	
	public static Integer getTimeDiff(Date changeTime) {
		if(changeTime!=null){
			long time = new Date().getTime();
			Long l = changeTime.getTime()-time;
			if(l<0){
				return 0;
			}
			return l.intValue()/1000;
		}
		return null;
	}
	
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		return cal.getTime();
	}
	
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MINUTE, 59);
		return calendar.getTime();
	}

	public static String getYearMonthDay(Date date,int n){
		String tmp=dateToString(date,DATE_FORMAT_SHORT);
		String[] _temp=tmp.split("-");
		if(n==1){
			return _temp[0]+"年";
		}else  if(n==2){
			return _temp[0]+"年"+_temp[1]+"月";
		}
		return _temp[0]+"年"+_temp[1]+"月"+_temp[2]+"日";
	}
	public static void main(String args[]) throws Exception {
		System.out.println(getYearMonthDay(new Date(),1));
		System.out.println(dateToString(getLastDayOfMonth(afterNMonthDate(11)),DATE_FORMAT_FULL));
		int a=102;
		int b=33;
		System.out.println(b%a);
		long l = System.currentTimeMillis()/1000;
		System.out.println("ll="+l);
		long timeInMillis = Calendar.getInstance().getTimeInMillis();
		Date date=afterNMonthDate(1);
		System.out.println(afterNDaysDate(date,5));
	}

}