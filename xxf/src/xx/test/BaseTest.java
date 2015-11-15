/**
 * 
 */
package xx.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lvhongqiang
 *
 */
public class BaseTest {
	protected static ApplicationContext ctx;
	static {
		ctx = new ClassPathXmlApplicationContext(new String[]{
				"applicationContext.xml",
				"spring-struts.xml"});
	}
}
