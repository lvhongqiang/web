/**
 * 
 */
package baking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import baking.model.Inventory;
import baking.model.OrdersGoods;
import baking.model.Recipe;

/**
 * @author lvhongqiang
 *
 */
@Service
public class InventoryService extends BaseService {

	/*
	 * 保存库存更改
	 */
	public Boolean saveall(List<Inventory>list){
		try{
			for (Inventory inventory : list) {
				baseDao.saveOrUpdate(inventory);
			}
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Inventory>listall(){
		return baseDao.find("from Inventory order by showOrder");
	}
	
	/**
	 * 减少库存
	 * @return
	 */
	public Map<Integer, Integer> minus(Integer goodsId, Integer num, Boolean reduce){
		String hql="from Recipe where goodsId=?";
		List<Recipe>recipes=baseDao.find(hql, goodsId);
		Map<Integer, Integer> map=new HashMap<Integer, Integer>();
		for (Recipe recipe : recipes) {
			Integer inventory=recipe.getInventId();
			if(map.containsKey(inventory)){
				map.put(inventory, map.get(inventory)+recipe.getUsage().intValue()*num);
			}else {
				map.put(inventory, recipe.getUsage().intValue()*num);
			}
		}
		if(reduce==true){//真减
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
				baseDao.executeHql("update Inventory set num=num-? where id=?",new Object[]{entry.getValue(),entry.getKey()});
			}
		}
		return map;
	}
	/**
	 * 库存消耗
	 * @param orderId
	 * @param reduce 是否减库存，true:减库存
	 * @return
	 */
	public Map<Integer, Integer> totalCosts(Integer orderId, Boolean reduce){
		Map<Integer, Integer> costsMap= new HashMap<Integer, Integer>();
		List<OrdersGoods>oglist=baseDao.find("from OrdersGoods where ordersId=?", orderId);
		for(OrdersGoods og : oglist){
			Map<Integer, Integer> map = minus(og.getGoodsId(), og.getNum(), reduce);
			for (Map.Entry<Integer, Integer> cost : map.entrySet()) {
				Integer id=cost.getKey();
				Integer id_num=cost.getValue();
				if(costsMap.containsKey(id)){
					costsMap.put(id, costsMap.get(id)+id_num);
				}else {
					costsMap.put(id, id_num);
				}
			}
		}
		return costsMap;
	}
	/**
	 * 取消库存消耗
	 * @param orderId
	 * @return
	 */
	public Boolean cancelCosts(Integer orderId){
		try{
			Map<Integer, Integer>totalCosts=totalCosts(orderId,false);
			for (Map.Entry<Integer, Integer> cost : totalCosts.entrySet()) {
				Inventory inventory=inventoryDAO.findById(cost.getKey());
				inventory.setNum(inventory.getNum()+cost.getValue());
				inventoryDAO.update(inventory);
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
