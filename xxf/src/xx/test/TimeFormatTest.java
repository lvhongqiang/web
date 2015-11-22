/**
 * 
 */
package xx.test;

import java.sql.Timestamp;
import java.util.Date;

import xx.util.TimeFormat;

/**
 * @author lvhongqiang
 *
 */
public class TimeFormatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TimeFormat timeFormat=new TimeFormat(new Timestamp(new Date().getTime()));
		System.out.print(timeFormat.getYear());
		System.out.print(timeFormat.getMon());
		System.out.print(timeFormat.getTh());
		System.out.print(timeFormat.getDay());
		
	}

}
