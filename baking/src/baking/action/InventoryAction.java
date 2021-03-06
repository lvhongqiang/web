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
	private Boolean ok;
	private Inventory newone;

	public String listall(){
		list=inventoryService.listall();
		return SUCCESS;
	}

	public String save(){
		ok=inventoryService.saveall(list);
		return SUCCESS;
	}
	
	public String add(){
		ok=inventoryService.add(newone);
		return SUCCESS;
	}
	
	public Boolean getOk() {
		return ok;
	}

	public List<Inventory> getList() {
		return list;
	}

	public void setList(List<Inventory> list) {
		this.list = list;
	}

	public Inventory getNewone() {
		return newone;
	}

	public void setNewone(Inventory newone) {
		this.newone = newone;
	}
}
