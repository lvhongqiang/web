/**
 * 
 */
package baking.action;

import java.util.List;

import baking.model.Goods;
import baking.service.GoodsService;

/**
 * @author lvhongqiang
 *
 */
public class GoodsAction extends BaseAction {
	
	
	private List<Goods>goodsList;
	
	public String listall(){
		goodsList=goodsService.listAll();
		return SUCCESS;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}
}
