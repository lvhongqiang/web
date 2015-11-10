package baking.test;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Lv
 * @date 2015年11月10日
 * @version 1.0
 */
public class Test1 {

	public static void main(String[] arrs ){
		System.out.println(Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"), Locale.CHINA).getTime());

		Calendar calendar=Calendar.getInstance();
		System.out.println(calendar.getTimeInMillis());
		calendar.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		System.out.println(calendar.getTimeInMillis());
		System.out.println(new Timestamp(Calendar.getInstance().getTimeInMillis()+28800000));
	}
}
