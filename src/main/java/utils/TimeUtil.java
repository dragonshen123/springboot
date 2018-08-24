package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

public class TimeUtil {
	public final static String FormatDate = "yyyy-MM-dd";
	public final static String FormatDateTime = "yyyy-MM-dd HH:mm:ss"; // 默认
	public final static String FormatTime = "HH:mm:ss";

	private final static SimpleDateFormat dateformat = new SimpleDateFormat(
			"yyyy-MM-dd");// 默认
	private final static SimpleDateFormat datetimeformat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat timeformat = new SimpleDateFormat(
			"HH:mm:ss");

	public Date date = null;

	/**
	 * 无惨构造函数
	 */
	public TimeUtil() {
		date = new Date();
	}

	/**
	 * 设置工具类默认时间
	 */
	public TimeUtil(Date date) {
		this.date = date;
	}

	// ****************************String转换为Date*************************************************
	/**
	 * 按照指定的格式将时间字符串转为时间
	 * 
	 * @param dateStr
	 *            String 时间字符串
	 * @param formatStr
	 *            String 时间格式
	 * @return Date
	 * */
	public static Date stringToDate(String dateStr, String formatStr) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat(formatStr);
			return sf.parse(dateStr);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将String转化成date 格式为yyyy-MM-dd hh:mm:ss
	 * 
	 * @param dateStr
	 *            时间字符串
	 * @throws ParseException
	 * */
	public static Date stringToDate(String dateStr) {
		try {
			return datetimeformat.parse(dateStr);
		} catch (java.text.ParseException e) {
			 e.printStackTrace();
			return null;
		}
	}

	// ****************************Date转换为String****************************
	/**
	 * 转换成时间格式的字符串 格式为yyyy-MM-dd hh:mm:ss
	 * 
	 * @param date
	 *            Date 转换的时间
	 * @return String
	 */
	public static String dateToString(Date date) {
		try {
			return datetimeformat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 转换成给定时间格式的字符串
	 * 
	 * @param date
	 *            Date 需要转换的时间
	 * @param formatStr
	 *            String 转换的格式
	 * @return String
	 */
	public static String dateToString(Date date, String formatStr) {
		try {
			return new SimpleDateFormat(formatStr).format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据指定格式formatStr获取系统默认时间字符串
	 * 
	 * @param formatStr
	 *            String 转换的格式
	 * @return String
	 */
	public static String dateToString(String formatStr) {
		try {
			return new SimpleDateFormat(formatStr).format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 按照默认的格式（yyyy-MM-dd HH:mm:ss）得到当前系统时间字符串
	 * 
	 * @param formatStr
	 *            String 转换的格式
	 * @return String
	 */
	public static String dateToString() {
		try {
			return new SimpleDateFormat(TimeUtil.FormatDateTime)
					.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ****************************时间格式的String转换为String****************************
	/**
	 * 按照指定的格式将字符串转为时间对象按照指定的格式formatStr将dateStr时间字符串转为时间字符串，
	 * 
	 * @param dateStr
	 *            String 时间字符串
	 * @param formatStr
	 *            String 给定的格式
	 * @return String
	 */
	public static String stringToString(String dateStr, String formatStr) {
		Date date = null;
		try {
			date = datetimeformat.parse(dateStr);
			return dateToString(date, formatStr);
		} catch (java.text.ParseException e) {

			 e.printStackTrace();
			return null;
		}

	}

	/**
	 * 按照默认的格式（yyyy-MM-dd HH:mm:ss）将时间字符串转为时间字符串
	 * 
	 * @param dateString
	 *            String 时间字符串
	 * @param format
	 *            String 给定的格式
	 * @return String
	 */
	public static String stringToString(String dateString) {
		Date date = null;
		try {
			date = datetimeformat.parse(dateString);
			return dateToString(date, TimeUtil.FormatDateTime);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	// ****************************Date转换为给定格式的Date****************************
	/**
	 * 时间转时间按照指定格式formatStr将时间对象（date）转为时间对象
	 * 
	 * @param date
	 *            Date 需要转换的时间
	 * @param format
	 *            String 转换的格式
	 * @return Date 转换后的时间
	 * @throws ParseException
	 * */
	public static Date dateToDate(Date date, String formatStr) {
		try {
			String dateStr = dateformat.format(date);
			return stringToDate(dateStr, formatStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static Date dateToDate(Date date) {
		try {
			String dateStr = dateformat.format(date);
			return stringToDate(dateStr, FormatDateTime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	// ****************************获取时间的年，月，周，日，时，分，秒，毫秒****************************
	/**
	 * 获得当前的年份
	 * 
	 * @return
	 */
	public int getYear() {
		return date.getYear();
	}

	/**
	 * 获得当前的月份
	 * 
	 * @return
	 */

	public int getMonth() {
		return date.getMonth();
	}

	/**
	 * 获得当前日期是本月的第几周
	 * 
	 * @return int
	 */
	public int getWeekOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 获得当前日期是星期几
	 * 
	 * @return int
	 */
	public int getWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获得当前的日期天
	 * 
	 * @return
	 */
	public int getDay() {

		return date.getDay();
	}

	/**
	 * 获得当前的日期小时
	 * 
	 * @return
	 */
	public int getHour() {
		return date.getHours();
	}

	/**
	 * 获得当前的日期分钟
	 * 
	 * @return
	 */
	public int getMinute() {
		return date.getMinutes();
	}

	/**
	 * 获得当前的日期秒
	 * 
	 * @return
	 */
	public int getSecond() {

		return date.getSeconds();
	}

	/**
	 * 获得当前的日期豪秒
	 * 
	 * @return
	 */
	public long getMillisSecond() {
		return date.getTime();
	}

	public static void main(String[] args) {

		List<KeyValueForDate> list = TimeUtil.getKeyValueForDate("2015-08-24",
				"2016-06-10");
		System.out.println("开始日期--------------结束日期");
		for (KeyValueForDate date : list) {
			System.out.println(date.getStartDate() + "-----"
					+ date.getEndDate());
		}

		/*
		 * List<String> list = TimeUtil.getBwteenDay("2015-08-24","2016-06-10");
		 * for (int i = 0; i < list.size(); i++) {
		 * System.out.println(list.get(i)); }
		 */
		/*
		 * 
		 * List<KeyValueForDate> list =
		 * TimeUtil.getBetweenWeek("2015-08-24","2016-06-10");
		 * System.out.println("开始日期--------------结束日期"); for(KeyValueForDate
		 * date : list){
		 * System.out.println(date.getStartDate()+"-----"+date.getEndDate
		 * ()+":"+date.getFlag()); }
		 */
		/*
		 * List<KeyValueForDate> list =
		 * TimeUtil.getBetweenYear("2018-08-24","2019-06-10");
		 * System.out.println("开始日期--------------结束日期"); for(KeyValueForDate
		 * date : list){
		 * System.out.println(date.getStartDate()+"-----"+date.getEndDate
		 * ()+":"+date.getFlag()); }
		 */
	}

	/**
	 * 将时间划分为时间区间(按照年份)
	 * 
	 * @return
	 */
	public static List<KeyValueForDate> getBetweenYear(String startDateStr,
			String endDateStr) {
		try {
			Date d1 = null;
			try {
				d1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);// 定义起始日期
			} catch (Exception e) {
				d1 = new Date();
			}
			Date d2 = null;
			try {
				d2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);// 定义结束日期
			} catch (Exception e) {
				d2 = new Date();
			}
			Calendar startDate = Calendar.getInstance();// 定义日期实例
			startDate.setTime(d1);// 设置日期起始时间

			Calendar endDate = Calendar.getInstance();// 定义日期实例
			endDate.setTime(d2);// 设置日期结束时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<KeyValueForDate> list = new ArrayList<KeyValueForDate>();
			int flag = 0;
			while (startDate.get(Calendar.YEAR) <= endDate.get(Calendar.YEAR)) {
				KeyValueForDate keyValueForDate = new KeyValueForDate();
				if (flag == 0) {
					flag++;
					keyValueForDate
							.setStartDate(sdf.format(startDate.getTime()));
				} else {
					Calendar currCal = Calendar.getInstance();
					currCal.clear();
					currCal.set(Calendar.YEAR, startDate.get(Calendar.YEAR));
					keyValueForDate.setStartDate(sdf.format(currCal.getTime()));

				}

				startDate.add(Calendar.YEAR, flag);
				if (startDate.get(Calendar.YEAR) <= endDate.get(Calendar.YEAR)) {
					Calendar currCal = Calendar.getInstance();
					currCal.clear();
					currCal.set(Calendar.YEAR, startDate.get(Calendar.YEAR));
					keyValueForDate.setEndDate(sdf.format(currCal.getTime()));
				} else {
					keyValueForDate.setEndDate(sdf.format(endDate.getTime()));
				}

				list.add(keyValueForDate);

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将时间划分为时间区间(按照周份)
	 * 
	 * @return
	 */
	public static List<KeyValueForDate> getBetweenWeek(String startDateStr,
			String endDateStr) {
		try {
			Date d1 = null;
			try {
				d1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);// 定义起始日期
			} catch (Exception e) {
				d1 = new Date();
			}
			Date d2 = null;
			try {
				d2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);// 定义结束日期
			} catch (Exception e) {
				d2 = new Date();
			}
			Calendar startDate = Calendar.getInstance();// 定义日期实例
			startDate.setTime(d1);// 设置日期起始时间
			Calendar endDate = Calendar.getInstance();// 定义日期实例
			endDate.setTime(d2);// 设置日期结束时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<KeyValueForDate> list = new ArrayList<KeyValueForDate>();
			while (startDate.getTime().before(endDate.getTime())) {
				KeyValueForDate keyValueForDate = new KeyValueForDate();
				keyValueForDate.setStartDate(sdf.format(startDate.getTime()));
				startDate.add(Calendar.WEEK_OF_YEAR, 1);
				keyValueForDate.setEndDate(sdf.format(startDate.getTime()));
				// keyValueForDate.setFlag(startDate.get(Calendar.WEEK_OF_YEAR));
				list.add(keyValueForDate);

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 将时间划分为时间区间(按照日份)
	 * 
	 * @return
	 */
	public static List<String> getBwteenDay(String startDateStr,
			String endDateStr) {
		try {
			Date d1 = null;
			try {
				d1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);// 定义起始日期
			} catch (Exception e) {
				d1 = new Date();
			}
			Date d2 = null;
			try {
				d2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);// 定义结束日期
			} catch (Exception e) {
				d2 = new Date();
			}
			Calendar startDate = Calendar.getInstance();// 定义日期实例
			startDate.setTime(d1);// 设置日期起始时间

			Calendar endDate = Calendar.getInstance();// 定义日期实例
			endDate.setTime(d2);// 设置日期结束时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<String> list = new ArrayList<String>();
			while (startDate.getTime().before(endDate.getTime())) {
				String dateStrTmp = sdf.format(startDate.getTime());
				list.add(dateStrTmp);
				startDate.add(Calendar.DATE, 1);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 将时间划分为时间区间(按照月份)
	 * 
	 * @return
	 */
	public static List<KeyValueForDate> getKeyValueForDate(String startDate,
			String endDate) {
		List<KeyValueForDate> list = null;

		try {
			list = new ArrayList<KeyValueForDate>();

			String firstDay = "";
			String lastDay = "";
			Date d1 = null;
			try {
				d1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);// 定义起始日期
			} catch (Exception e) {
				d1 = new Date();
			}
			Date d2 = null;
			try {
				d2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);// 定义结束日期
			} catch (Exception e) {
				d2 = new Date();
			}
			Calendar dd = Calendar.getInstance();// 定义日期实例
			dd.setTime(d1);// 设置日期起始时间
			Calendar cale = Calendar.getInstance();

			Calendar c = Calendar.getInstance();
			c.setTime(d2);

			int startDay = d1.getDate();
			int endDay = d2.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			KeyValueForDate keyValueForDate = null;

			while (dd.get(Calendar.YEAR) < c.get(Calendar.YEAR)
					|| (dd.get(Calendar.YEAR) == c.get(Calendar.YEAR) && dd
							.get(Calendar.MONTH) <= c.get(Calendar.MONTH))) {// 判断是否到结束日期
				keyValueForDate = new KeyValueForDate();
				cale.setTime(dd.getTime());
				keyValueForDate.setFlag(1);
				if (dd.getTime().equals(d1)) {
					cale.set(Calendar.DAY_OF_MONTH,
							dd.getActualMaximum(Calendar.DAY_OF_MONTH));
					lastDay = sdf.format(cale.getTime());
					keyValueForDate.setStartDate(sdf.format(d1));
					keyValueForDate.setEndDate(lastDay);

				} else if (dd.get(Calendar.MONTH) == d2.getMonth()
						&& dd.get(Calendar.YEAR) == c.get(Calendar.YEAR)) {
					cale.set(Calendar.DAY_OF_MONTH, 1);// 取第一天
					firstDay = sdf.format(cale.getTime());

					keyValueForDate.setStartDate(firstDay);
					keyValueForDate.setEndDate(sdf.format(d2));

				} else {
					cale.set(Calendar.DAY_OF_MONTH, 1);// 取第一天
					firstDay = sdf.format(cale.getTime());

					cale.set(Calendar.DAY_OF_MONTH,
							dd.getActualMaximum(Calendar.DAY_OF_MONTH));
					lastDay = sdf.format(cale.getTime());

					keyValueForDate.setStartDate(firstDay);
					keyValueForDate.setEndDate(lastDay);

				}
				list.add(keyValueForDate);
				dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1

			}

			if (endDay < startDay) {
				keyValueForDate = new KeyValueForDate();

				cale.setTime(d2);
				cale.set(Calendar.DAY_OF_MONTH, 1);// 取第一天
				firstDay = sdf.format(cale.getTime());
				keyValueForDate.setStartDate(firstDay);
				keyValueForDate.setEndDate(sdf.format(d2));
				list.add(keyValueForDate);
			}
		} catch (Exception e) {
			return null;
		}

		return list;
	}

	// 获取统计图下标
	public static Map<String, List<Double>> getTotalFlag(
			List<Map<String, Object>> list, String begining, String ending,
			Integer between, String dateStrLinumer,String count,String countValue) {
		// 日的统计 统计数量 总价值
		Map<String, List<Double>> total = new HashedMap();
		if (between == 0) {
			List<String> listDateStr = TimeUtil.getBwteenDay(begining, ending);
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < listDateStr.size(); j++) {
					String timeStr = TimeUtil.stringToString(
							list.get(i).get(dateStrLinumer).toString(),
							"yyyy-MM-dd");
					if (timeStr.equals(listDateStr.get(j))) {
						List<Double> listTmp = total.get(listDateStr.get(j));
						if (listTmp != null && listTmp.size() > 0) {
							listTmp.set(0, listTmp.get(0)
									+ (Double) list.get(i).get(count));
							listTmp.set(1, listTmp.get(1)
									+ (Double) list.get(i).get(countValue));
						} else {
							listTmp = new ArrayList<Double>();
							listTmp.add((Double) list.get(i).get(count));
							listTmp.add((Double) list.get(i).get(countValue));
						}
						total.put(listDateStr.get(j), listTmp);
					}
				}
			}
		}
		// 周的统计
		if (between == 1) {
			List<KeyValueForDate> listKeyValueForDate = TimeUtil
					.getBetweenWeek(begining, ending);
			for (int i = 0; i < list.size(); i++) {
				Date dateTmp = TimeUtil.stringToDate(
						list.get(i).get(dateStrLinumer).toString(),
						"yyyy-MM-dd");
				for (int j = 0; j < listKeyValueForDate.size(); j++) {
					Date startDate = TimeUtil.stringToDate(listKeyValueForDate
							.get(j).getStartDate(), "yyyy-MM-dd");
					Date endDate = TimeUtil.stringToDate(listKeyValueForDate
							.get(j).getEndDate(), "yyyy-MM-dd");
					if (startDate.getTime() <= dateTmp.getTime()
							&& dateTmp.getTime() < endDate.getTime()) {
						List<Double> listTmp = total.get(listKeyValueForDate
								.get(j).getStartDate()
								+ "--"
								+ listKeyValueForDate.get(j).getEndDate());
						if (listTmp != null && listTmp.size() > 0) {
							listTmp.set(0, listTmp.get(0)
									+ (Double) list.get(i).get(count));
							listTmp.set(1, listTmp.get(1)
									+ (Double) list.get(i).get(countValue));
						} else {
							listTmp = new ArrayList<Double>();
							listTmp.add((Double) list.get(i).get(count));
							listTmp.add((Double) list.get(i).get(countValue));
						}
						total.put(listKeyValueForDate.get(j).getStartDate()
								+ "--"
								+ listKeyValueForDate.get(j).getEndDate(),
								listTmp);
					}
				}
			}
		}

		// 月的统计
		if (between == 2) {
			List<KeyValueForDate> listKeyValueForDate = TimeUtil
					.getKeyValueForDate(begining, ending);
			for (int i = 0; i < list.size(); i++) {
				Date dateTmp = TimeUtil.stringToDate(
						list.get(i).get(dateStrLinumer).toString(),
						"yyyy-MM-dd");
				for (int j = 0; j < listKeyValueForDate.size(); j++) {
					Date startDate = TimeUtil.stringToDate(listKeyValueForDate
							.get(j).getStartDate(), "yyyy-MM-dd");
					Date endDate = TimeUtil.stringToDate(listKeyValueForDate
							.get(j).getEndDate(), "yyyy-MM-dd");

					if (startDate.getTime() <= dateTmp.getTime()
							&& dateTmp.getTime() <= endDate.getTime()) {
						List<Double> listTmp = total.get(listKeyValueForDate
								.get(j).getStartDate()
								+ "--"
								+ listKeyValueForDate.get(j).getEndDate());
						if (listTmp != null && listTmp.size() > 0) {
							listTmp.set(0, listTmp.get(0)
									+ (Double) list.get(i).get(count));
							listTmp.set(1, listTmp.get(1)
									+ (Double) list.get(i).get(countValue));
						} else {
							listTmp = new ArrayList<Double>();
							listTmp.add((Double) list.get(i).get(count));
							listTmp.add((Double) list.get(i).get(countValue));
						}
						total.put(listKeyValueForDate.get(j).getStartDate()
								+ "--"
								+ listKeyValueForDate.get(j).getEndDate(),
								listTmp);
						break;
					}
				}
			}

		}
		// 年的统计
		if (between == 3) {
			List<KeyValueForDate> listKeyValueForDate = TimeUtil
					.getBetweenYear(begining, ending);
			for (int i = 0; i < list.size(); i++) {
				Date dateTmp = TimeUtil.stringToDate(
						list.get(i).get(dateStrLinumer).toString(),
						"yyyy-MM-dd");
				for (int j = 0; j < listKeyValueForDate.size(); j++) {
					Date startDate = TimeUtil.stringToDate(listKeyValueForDate
							.get(j).getStartDate(), "yyyy-MM-dd");
					Date endDate = TimeUtil.stringToDate(listKeyValueForDate
							.get(j).getEndDate(), "yyyy-MM-dd");
					if (startDate.getTime() <= dateTmp.getTime()
							&& dateTmp.getTime() < endDate.getTime()) {
						List<Double> listTmp = total.get(listKeyValueForDate
								.get(j).getStartDate()
								+ "--"
								+ listKeyValueForDate.get(j).getEndDate());
						if (listTmp != null && listTmp.size() > 0) {
							listTmp.set(0, listTmp.get(0)
									+ (Double) list.get(i).get(count));
							listTmp.set(1, listTmp.get(1)
									+ (Double) list.get(i).get(countValue));
						} else {
							listTmp = new ArrayList<Double>();
							listTmp.add((Double) list.get(i).get(count));
							listTmp.add((Double) list.get(i).get(countValue));
						}
						total.put(listKeyValueForDate.get(j).getStartDate()
								+ "--"
								+ listKeyValueForDate.get(j).getEndDate(),
								listTmp);
					}
				}
			}
		}
		return total;
	}
}
