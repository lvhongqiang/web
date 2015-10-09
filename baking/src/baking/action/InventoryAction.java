/**
 * 
 */
package baking.action;

import java.util.List;

import baking.model.Inventory;

/**
 * @author lvhongqiang
 *
 */
public class InventoryAction extends BaseAction {


	private List<Inventory> list;

	public String listall(){
		list=inventoryService.listall();
		return SUCCESS;
	}

	public String save(){
		for (Inventory inventory : list) {
			inventoryService.saveOrUpdate(inventory);
		}
		return SUCCESS;
	}
	
	public List<Inventory> getList() {
		return list;
	}

	public void setList(List<Inventory> list) {
		this.list = list;
	}
}
