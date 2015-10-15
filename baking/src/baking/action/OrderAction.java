/**
 * 
 */
package baking.action;

import java.util.List;
import java.util.Map;

import baking.model.Orders;
import baking.model.Page;

/**
 * @author lvhon
 *
 */
public class OrderAction extends BaseAction {
	private Map<String, Integer> orders;
	private Orders order;
	private Page page;
	private Integer p=1;
	private Integer s=10;
	private Integer orderId;
	private List<Object[]> costDetail;
	private List<Object[]>details;
	
	public String save(){
		order=orderService.save(orders);
		return SUCCESS;
	}
	public String list(){
		page=orderService.listPage(p, s);
		return SUCCESS;
	}
	public String costs(){
		costDetail=orderService.costDetial(orderId);
		
		return SUCCESS;
	}
	public String detail(){
		details=orderService.details(orderId);
		return SUCCESS;
	}
	public String del(){
		orderService.delete(orderId);
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
	public Integer getP() {
		return p;
	}
	public void setP(Integer p) {
		this.p = p;
	}
	public Integer getS() {
		return s;
	}
	public void setS(Integer s) {
		this.s = s;
	}
	public Page getPage() {
		return page;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public List<Object[]> getCostDetail() {
		return costDetail;
	}
}
