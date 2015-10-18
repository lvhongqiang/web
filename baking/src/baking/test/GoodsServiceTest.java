/**
 * 
 */
package baking.test;

import baking.service.OrderService;

/**
 * @author lvhongqiang
 *
 */
public class GoodsServiceTest extends BaseTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OrderService service=ctx.getBean(OrderService.class);
		service.details(4);

	}

}
