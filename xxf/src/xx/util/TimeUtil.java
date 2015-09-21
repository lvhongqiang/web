package xx.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeUtil {

	/**
	 * 获得特定的日期，以当前日期为标准
	 * @param days 与当前日期相差的天数
	 * @return 2014-02-14 00:00:00.000
	 */
	public static Date getday(Date date,Integer days) {
		if(days==null)return null;
		Calendar nowCalendar=Calendar.getInstance();
		nowCalendar.setTime(date);
		nowCalendar.add(Calendar.DAY_OF_YEAR, days);
		nowCalendar.set(Calendar.HOUR_OF_DAY, 0);
		nowCalendar.set(Calendar.MINUTE, 0);
		nowCalendar.set(Calendar.SECOND, 0);
		nowCalendar.set(Calendar.MILLISECOND, 0);
		return nowCalendar.getTime();
	}
	/**
	 * 获得特定的日期，以当前日期为标准
	 * @param days 与当前日期相差的天数
	 * @return 2014-02-14 23:59:59.000
	 */
	public static Date getdayEnd(Date date,Integer days) {
		if(days==null)return null;
		Calendar nowCalendar=Calendar.getInstance();
		nowCalendar.setTime(date);
		nowCalendar.add(Calendar.DAY_OF_YEAR, days);
		nowCalendar.set(Calendar.HOUR_OF_DAY, 23);
		nowCalendar.set(Calendar.MINUTE, 59);
		nowCalendar.set(Calendar.SECOND, 59);
		nowCalendar.set(Calendar.MILLISECOND, 999);
		return nowCalendar.getTime();
	}
	public static Date getday(Integer days) {
		if(days==null)return null;
		Calendar nowCalendar=Calendar.getInstance();
		return getday(nowCalendar.getTime(),days);
	}
	public static Date getdayEnd(Integer days){
		if(days==null)return null;
		Calendar nowCalendar=Calendar.getInstance();
		return getdayEnd(nowCalendar.getTime(),days);
	}
	/**
	 * 获得特定的日期，以当前日期为标准
	 * @param days 与当前日期相差的天数
	 * @return 2014-02-14 00:00:00
	 */
	public static Date get_day(Integer days) {
		if(days==null)return null;
		return getday(-days);
	}
	/**
	 * 返回当前时间戳
	 * @return
	 */
	public static Timestamp now(){
		Calendar nowCalendar=Calendar.getInstance();
		return new Timestamp(nowCalendar.getTime().getTime());
	}
	/**
	 * 返回两个date的天数差值
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int diffDays(Date begin,Date end){
		return ((Long)((end.getTime()-begin.getTime()+86400000)/86400000)).intValue();
	}
	/**
	 * 格式化日期列表
	 * @param list
	 * @return
	 */
	public static List<String>formatList(List<Date>list){
		List<String>result=new ArrayList<String>();
		for (Date date : list) {
			result.add(format(date));
		}
		return result;
	}
	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	public static String format(Date date){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(date);
	}
}

