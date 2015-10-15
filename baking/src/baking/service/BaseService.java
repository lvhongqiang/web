package baking.service;

import org.springframework.beans.factory.annotation.Autowired;

import baking.dao.BaseDao;
import baking.dao.GoodsDAO;
import baking.dao.InventoryDAO;
import baking.dao.OrdersDAO;
import baking.dao.RecipeDAO;
import baking.dao.StepDAO;

public class BaseService {
	@Autowired protected BaseDao baseDao;
	@Autowired protected GoodsDAO goodsDAO;
	@Autowired protected OrdersDAO ordersDAO;
	@Autowired protected InventoryDAO inventoryDAO;
	@Autowired protected StepDAO stepDAO;
	@Autowired protected RecipeDAO recipeDAO;
	
	public Object get(Class clazz, Integer id){
		return baseDao.get(clazz, id);
	}
	public void saveOrUpdate(Object obj){
		baseDao.saveOrUpdate(obj);
	}
	public void delete(Object obj){
		baseDao.delete(obj);
	}
}
