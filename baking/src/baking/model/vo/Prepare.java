/**
 * 
 */
package baking.model.vo;

import java.util.ArrayList;
import java.util.List;

import baking.model.OrdersGoods;
import baking.model.Recipe;
import baking.model.Step;

/**
 * @author Lv
 * @date 2015年10月16日
 * @version 1.0
 */
public class Prepare {
	
	private String title;
	private List<Recipe>recipeList;
	private List<OrdersGoods>ogList;
	
	public Integer getNum(){
		Integer num=0;
		for (OrdersGoods ordersGoods : ogList) {
			num+=ordersGoods.getNum();
		}
		return num;
	}
	
	public Prepare() {
		recipeList=new ArrayList<Recipe>();
		ogList=new ArrayList<OrdersGoods>();
	}
	public Prepare(String title, List<Recipe> recipeList) {
		this.title = title;
		this.recipeList = recipeList;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Recipe> getRecipeList() {
		return recipeList;
	}
	public void setRecipeList(List<Recipe> recipeList) {
		this.recipeList = recipeList;
	}
	public List<OrdersGoods> getOgList() {
		return ogList;
	}
	public void setOgList(List<OrdersGoods> ogList) {
		this.ogList = ogList;
	}
	
	
}
