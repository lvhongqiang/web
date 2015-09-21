/**
 * 
 */
package xx.util;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lvhongqiang
 * @email lvhongqiang09@163.com
 * 2015年6月12日
 */
public class TimeFormat {
	private Integer year;
	private String mon;
	private String th;
	private Integer day;
	private static Map<Integer, String>monMap=new HashMap<Integer, String>();
	private static Map<Integer, String>thMap=new HashMap<Integer, String>();
	static{
		monMap.put(1, "Jan");
		monMap.put(2, "Feb");
		monMap.put(3, "Mar");
		monMap.put(4, "Apr");
		monMap.put(5, "May");
		monMap.put(6, "Jun");
		monMap.put(7, "Jul");
		monMap.put(8, "Aug");
		monMap.put(9, "Sep");
		monMap.put(10, "Oct");
		monMap.put(11, "Nov");
		monMap.put(12, "Dec");

		thMap.put(1, "uary");
		thMap.put(2, "ruary");
		thMap.put(3, "ch");
		thMap.put(4, "il");
		thMap.put(5, "");
		thMap.put(6, "e");
		thMap.put(7, "y");
		thMap.put(8, "ust");
		thMap.put(9, "tember");
		thMap.put(10, "ober");
		thMap.put(11, "ember");
		thMap.put(12, "ember");
	}
	/**
	 * @param year
	 */
	public TimeFormat(Timestamp time) {
		this.year=time.getYear();
		this.mon=monMap.get(time.getMonth());
		this.th=thMap.get(time.getMonth());
		this.day=time.getDate();
	}
	public Integer getYear() {
		return year;
	}
	public String getMon() {
		return mon;
	}
	public String getTh() {
		return th;
	}
	public Integer getDay() {
		return day;
	}
	
	
}
