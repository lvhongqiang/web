/**
 * 
 */
package baking.action;

import java.util.Map;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;

import baking.model.Orders;

/**
 * @author lvhon
 *
 */
public class OrderAction extends BaseAction {
	private Map<String, Integer> orders;
	private Orders order;
	
	public String save(){
		order=orderService.save(orders);
		return SUCCESS;
	}

	public Map<String, Integer> getOrders() {
		return orders;
	}

	public void setOrders(Map<String, Integer> orders) {
		this.orders = orders;
	}

	public Orders getOrder() {
		return order;
	}
}
