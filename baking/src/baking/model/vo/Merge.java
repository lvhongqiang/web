/**
 * 
 */
package baking.model.vo;

import java.util.List;

import baking.model.OrdersGoods;

/**
 * @author Lv
 * @date 2015年10月16日
 * @version 1.0
 */
public class Merge {
	private List<OrdersGoods>list;
	private Integer mergeId;
	
	public Merge(List<OrdersGoods> list, Integer mergeId) {
		this.list = list;
		this.mergeId = mergeId;
	}
	
	public List<OrdersGoods> getList() {
		return list;
	}
	public void setList(List<OrdersGoods> list) {
		this.list = list;
	}
	public Integer getMergeId() {
		return mergeId;
	}
	public void setMergeId(Integer mergeId) {
		this.mergeId = mergeId;
	}
	
}
