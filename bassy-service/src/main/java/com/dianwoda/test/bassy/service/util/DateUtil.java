package com.dianwoda.test.bassy.service.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

	private DateUtil() {
	}

	public static final String DAY_FORMAT = "yyyyMMdd";

	public static final String MONTH_FORMAT = "yyyyMM";

	public static final String YYYY_MM_DD = "yyyy/MM/dd";

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	public static final String YYYY_MM_DD_CN = "yyyy年MM月dd日";

	public static final String YYYY_MM_CN = "yyyy年MM月";

	public static final String YYYY_MM_DD_HH_DD_SS = "yyyy-MM-dd HH:mm:ss"; // HH是24小时制，hh是12小时制

	public static final String YYYYMMDDHHDDSS = "yyyyMMddHHmmss"; // HH是24小时制，hh是12小时制

	public static final String YYYYMMDDHHDDSS_S = "yyyyMMddHHmmssS"; // HH是24小时制，hh是12小时制(精确到毫秒)

	public static final String YYYY_MM_DD_HH_DD = "yyyy-MM-dd HH:mm"; // HH是24小时制，hh是12小时制

	public static final String YY_MM = "yy-MM"; // HH是24小时制，hh是12小时制

	public static final String MM_DD = "MM-dd";

	public static final String HH_MI = "HH:mm";

	public static final String MM_DD_HH_MI = "MM-dd HH:mm";

	/**
	 * 取得系统当前日期
	 */
	public static Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 把日期型转换成"yyyy/MM/dd/"字符串形式。
	 * 
	 * @param date
	 * @return 日期字符串
	 */
	public static String toLocaleString(Date date) {
		return toLocaleString(date, null);
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getNowTime() {
		return new SimpleDateFormat(YYYY_MM_DD_HH_DD_SS).format(new Date(System
				.currentTimeMillis()));
	}

	/**
	 * 通过时间戳获取时间
	 * 
	 * @return
	 */
	public static String getDateTimeFromTm(long timestamp) {
		return new SimpleDateFormat(YYYY_MM_DD_HH_DD_SS).format(new Date(
				timestamp));
	}

	/**
	 * 通过string获取时间戳
	 * 
	 * @return
	 */
	public static long getTmFromString(String dateString) {
		Date date = null;
		try {
			date = new SimpleDateFormat(YYYY_MM_DD_HH_DD_SS).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date != null) {
			return date.getTime();
		}
		return 0;
	}

	/**
	 * 通过string获取天 string
	 * 
	 * @return
	 */
	public static String getDateStringFromString(String dateString) {
		Date date = null;
		if (StringUtils.isNotBlank(dateString)) {
			try {
				date = new SimpleDateFormat(YYYY_MM_DD_HH_DD_SS)
						.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (date != null) {
				return toLocaleString(date, null);
			}
		}
		return null;
	}

	/**
	 * 通过string获取天 string
	 * 
	 * @return
	 */
	public static String getDateStringFromStringFormat(String dateString,
			String dateFormat) {
		Date date = null;
		if (StringUtils.isNotBlank(dateString)) {
			try {
				date = new SimpleDateFormat(YYYY_MM_DD_HH_DD_SS)
						.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (date != null) {
				return toLocaleString(date, dateFormat);
			}
		}
		return null;
	}

	/**
	 * 通过string获取分钟级时间戳
	 * 
	 * @return
	 */
	public static long getMinuteTmFromString(String dateString) {
		Date date = null;
		try {
			date = new SimpleDateFormat(YYYY_MM_DD_HH_DD).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date != null) {
			return date.getTime();
		}
		return 0;
	}

	/**
	 * 把日期型转换成字符串形式。
	 * 
	 * @param date
	 *            日期
	 * @param dateFormat
	 *            日期格式，例如"yyyy/MM/dd"、"yyyy年MM月dd"
	 * @return 日期字符串
	 */
	public static String toLocaleString(Date date, String dateFormat) {
		if (date == null) {
			return "";
		}

		if (StringUtils.isBlank(dateFormat)) {
			return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(date);
		}

		return new SimpleDateFormat(dateFormat).format(date);
	}

	/**
	 * @author Zhu
	 * @date 2015-5-30下午12:27:10
	 * @description
	 * @param date1
	 * @param date2
	 * @param unit
	 * @return
	 */
	public static long sub(Date date1, Date date2, int unit) {
		if (date1 == null || date2 == null) {
			return 0;
		}
		long milliseconds = date1.getTime() - date2.getTime();
		int divisor = -1;
		switch (unit) {
		case Calendar.MILLISECOND:
			divisor = 1;
			break;
		case Calendar.SECOND:
			divisor = 1000;
			break;
		case Calendar.MINUTE:
			divisor = 1000 * 60;
			break;
		case Calendar.HOUR_OF_DAY:
			divisor = 1000 * 60 * 60;
			break;
		case Calendar.DATE:
			divisor = 1000 * 60 * 60 * 24;
		default:
			break;
		}
		if (divisor == -1) {
			return -1;
		}
		return milliseconds / divisor;

	}

	/**
	 * @author Zhu
	 * @date 2015-6-8下午6:50:38
	 * @description
	 * @return
	 */
	public static Date getCurrentDate() {
		return getDateStart(getCurrentTime());
	}

	/**
	 * 字符串转时间类型
	 * 
	 * @param date
	 *            字符串时间
	 * @param format
	 *            对应格式
	 * @return
	 */
	public static Date parse(String date, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			return df.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date parse(long timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp);
		return calendar.getTime();
	}

	public static Date beforeDateMin(Date date,Integer min) {
		return parse(beforeMinToDate(date,min));
	}

	public static Date afterDateMin(Date date,Integer min) {
		return parse(afterMinToDate(date,min));
	}

	/**
	 * 获取日期月份，1,2,3....11,12
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getMonth(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		return month + 1;
	}

	/**
	 * 获取年份
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getYear(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取天数
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getDay(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @author Zhu
	 * @date 2015-7-22上午11:49:45
	 * @description
	 * @return
	 */
	public static Date getDateStart(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取结束营业时间
	 * 
	 * @return
	 */
	public static Date getDateEnd(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * @author Zhu
	 * @date 2015-8-5下午7:29:19
	 * @description
	 * @return
	 */
	public static Date getMonthStart(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * @author Zhu
	 * @date 2015-8-5下午7:29:28
	 * @description
	 * @return
	 */
	public static Date getMonthEnd(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * @author Zhu
	 * @date 2015-7-21下午8:56:25
	 * @description
	 * @param date
	 * @param step
	 * @return
	 */
	public static Date add(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 获取一个永久的时间，9999-12-31 23:59:59
	 * 
	 * @return
	 */
	public static Date getForeverTime() {
		return parse("9999-12-31 23:59:59", YYYY_MM_DD_HH_DD_SS);
	}

	/**
	 * @Description: 获得指定日期的前一天
	 * @author:Zhao, Xu-Guang
	 * @date:2015年11月4日上午11:18:22
	 */
	public static String getSpecifiedDayBefore(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT)
					.parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT)
				.format(c.getTime());
		return dayBefore;
	}

	public static boolean isLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * @Description: 获取当月第一天
	 * @author:Zhao, Xu-Guang
	 * @date:2015年11月23日下午9:36:46
	 */
	public static Date getMonthStart(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * @Description: 获取当月最后一天
	 * @author:Zhao, Xu-Guang
	 * @date:2015年11月23日下午9:33:33
	 */
	public static Date getMonthEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * @Description: 根据日期获得相应的星期几
	 * @author:Zhao, Xu-Guang
	 * @date:2015年12月17日下午7:13:55
	 */
	public static String getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int num = cal.get(Calendar.DAY_OF_WEEK);
		String[] str = { "", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		return str[num];
	}

	public static Date getWeekStart(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getWeekEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取下一个月
	 * 
	 * @param date
	 * @return
	 * 
	 * @date 2016年3月9日
	 */
	public static Integer getNextMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		return cal.get(Calendar.MONTH);
	}

	/**
	 * 判断当前日期是否在指定时间段内, (startTime, endTime]
	 * 
	 * @param date
	 * @param startTime
	 *            开始时间段，如：06:01:03
	 * @param endTime
	 *            结束时间段，如：20:00:00
	 * @return
	 */
	public static boolean isBetweenTime(Date date, String startTime,
			String endTime) {
		String[] startTimeArray = startTime.split(":");
		String[] endTimeArray = endTime.split(":");

		Calendar rangeStart = Calendar.getInstance();
		rangeStart.setTime(date);
		rangeStart
				.set(Calendar.HOUR_OF_DAY, Integer.valueOf(startTimeArray[0]));
		rangeStart.set(Calendar.MINUTE, Integer.valueOf(startTimeArray[1]));
		rangeStart.set(Calendar.SECOND, Integer.valueOf(startTimeArray[2]));

		Calendar rangeEnd = Calendar.getInstance();
		rangeEnd.setTime(date);
		rangeEnd.set(Calendar.HOUR_OF_DAY, Integer.valueOf(endTimeArray[0]));
		rangeEnd.set(Calendar.MINUTE, Integer.valueOf(endTimeArray[1]));
		rangeEnd.set(Calendar.SECOND, Integer.valueOf(endTimeArray[2]));

		Calendar objectDate = Calendar.getInstance();
		objectDate.setTime(date);
		return objectDate.after(rangeStart) && !objectDate.after(rangeEnd);
	}

	/**
	 * <p>
	 * Checks if two date objects are on the same day ignoring time.
	 * </p>
	 * 
	 * <p>
	 * 28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true. 28 Mar 2002
	 * 13:45 and 12 Mar 2002 13:45 would return false.
	 * </p>
	 * 
	 * @param date1
	 *            the first date, not altered, not null
	 * @param date2
	 *            the second date, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 * @since 2.1
	 */
	public static boolean isSameDay(final Date date1, final Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		final Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		final Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	/**
	 * <p>
	 * Checks if two calendar objects are on the same day ignoring time.
	 * </p>
	 * 
	 * <p>
	 * 28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true. 28 Mar 2002
	 * 13:45 and 12 Mar 2002 13:45 would return false.
	 * </p>
	 * 
	 * @param cal1
	 *            the first calendar, not altered, not null
	 * @param cal2
	 *            the second calendar, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either calendar is <code>null</code>
	 * @since 2.1
	 */
	public static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
				&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
					.get(Calendar.DAY_OF_YEAR) == cal2
				.get(Calendar.DAY_OF_YEAR));
	}

	/**
	 * 获取周区间，起止日期，星期一是第一天
	 * 
	 * @param date
	 * @return 星期的时间区间
	 * 
	 * @date 2016年3月9日
	 */
	public static Date[] getWeekInterval(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);

		// 开始日期
		Calendar bgn = Calendar.getInstance();
		bgn.setFirstDayOfWeek(Calendar.MONDAY);
		bgn.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		bgn.set(Calendar.WEEK_OF_YEAR, cal.get(Calendar.WEEK_OF_YEAR));
		bgn.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		bgn.set(Calendar.HOUR_OF_DAY, 0);
		bgn.set(Calendar.MINUTE, 0);
		bgn.set(Calendar.SECOND, 0);
		bgn.set(Calendar.MILLISECOND, 0);

		// 截止日期
		Calendar end = Calendar.getInstance();
		end.setFirstDayOfWeek(Calendar.MONDAY);
		end.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		end.set(Calendar.WEEK_OF_YEAR, cal.get(Calendar.WEEK_OF_YEAR));
		end.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		end.set(Calendar.MILLISECOND, 999);

		return new Date[] { bgn.getTime(), end.getTime() };
	}

	public static Date max(Date... dates) {
		if (ArrayUtils.isEmpty(dates)) {
			return null;
		}
		Date max = dates[0];
		for (int i = 1; i < dates.length; i++) {
			Date d = dates[i];
			if (d != null
					&& d.compareTo(max == null ? DateUtils.addDays(d, -1) : max) > 0) {
				max = d;
			}
		}
		return max;
	}

	public static Date min(Date... dates) {
		if (ArrayUtils.isEmpty(dates)) {
			return null;
		}
		Date min = dates[0];
		for (int i = 1; i < dates.length; i++) {
			Date d = dates[i];
			if (d != null
					&& d.compareTo(min == null ? DateUtils.addDays(d, 1) : min) < 0) {
				min = d;
			}
		}
		return min;
	}

	/**
	 * 获取指定时间之前某个时间 单位min
	 * @param min
	 * @param date
	 * @return 毫秒
	 */
	public static long beforeMinToDate(Date date,Integer min) {
		long time = date.getTime();
		long beforeMills = min * 60 * 1000;
		return time - beforeMills;
	}

	/**
	 * 获取指定时间之前某个时间 单位min
	 * @param min
	 * @param date
	 * @return 毫秒
	 */
	public static long afterMinToDate(Date date,Integer min) {
		long time = date.getTime();
		long beforeMills = min * 60 * 1000;
		return time + beforeMills;
	}

	/**
	 * 判断某个时间d是否在[begin, end]之间，任何一个为null则false
	 * 
	 * @param d
	 * @param begin
	 * @param end
	 * @return
	 */
	public static boolean between(Date d, Date begin, Date end) {
		return d != null && begin != null && end != null
				&& d.compareTo(begin) >= 0 && d.compareTo(end) <= 0;
	}

	/**
	 * 获取两个日期之间的工作日（除周末）
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int workDays(Date begin, Date end) {
		int workDays = 0;
		while(begin.before(end)){
			if(!getWeek(begin).equals("星期六") && !getWeek(begin).equals("星期日")){
				workDays++;
			}
			begin = add(begin,Calendar.DATE,1);
		}
		return workDays;
	}

	public static Date utcToCst(String UTCStr, String format) throws ParseException {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		date = sdf.parse(UTCStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
		return calendar.getTime();
	}

	public static void main(String[] args) throws ParseException{
		// Date[] dates = getWeekInterval(DateUtil.parse("2016-03-15",
		// DEFAULT_DATE_FORMAT));
		// for (Date date : dates) {
		// System.out.println(date);
		// }
		Date d = new Date();
		System.out.println(d);
		System.out.println(DateUtil.getWeekStart(d));
		System.out.println(DateUtil.getWeekEnd(d));
		System.out.println(workDays(new SimpleDateFormat(YYYY_MM_DD_HH_DD_SS).parse("2018-10-22 00:00:00"),new SimpleDateFormat(YYYY_MM_DD_HH_DD_SS).parse("2018-10-29 23:59:59")));
		// Date[] ds = new Date[]{DateUtils.addSeconds(getCurrentTime(), -100),
		// DateUtils.addSeconds(getCurrentTime(), 10),
		// DateUtils.addSeconds(getCurrentTime(), -10),
		// DateUtils.addSeconds(getCurrentTime(), 600)};
		// System.err.println(max(ds));
		// System.err.println(min(ds));
		// System.err.println(max(new Date[]{getCurrentTime(), null}));
		// System.err.println(min(new Date[]{null, getCurrentTime()}));
	}
}
