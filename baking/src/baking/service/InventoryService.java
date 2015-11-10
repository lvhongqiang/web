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
	
	public Boolean add(Inventory inventory){
		try {
			baseDao.save(inventory);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Inventory>listall(){
		return baseDao.find("from Inventory order by showOrder asc");
	}
	
	/**
	 * 库存消耗
	 * @return
	 */
	public Map<Integer, Float> costs(Integer goodsId, Integer num){
		String hql="from Recipe where goodsId=?";
		List<Recipe>recipes=baseDao.find(hql, goodsId);
		Map<Integer, Float> map=new HashMap<Integer, Float>();
		for (Recipe recipe : recipes) {
			Integer inventory=recipe.getInventId();
			if(map.containsKey(inventory)){
				map.put(inventory, map.get(inventory)+recipe.getUsage()*num);
			}else {
				map.put(inventory, recipe.getUsage()*num);
			}
		}
		return map;
	}
	/**
	 * 计算库存消耗
	 * @param orderId
	 * @return
	 */
	public Map<Integer, Integer> totalCosts(Integer orderId){
		Map<Integer, Float> costsMap= new HashMap<Integer, Float>();
		List<OrdersGoods>oglist=baseDao.find("from OrdersGoods where ordersId=?", orderId);
		for(OrdersGoods og : oglist){
			Map<Integer, Float> map = costs(og.getGoodsId(), og.getNum());
			for (Map.Entry<Integer, Float> cost : map.entrySet()) {
				Integer id=cost.getKey();
				Float id_num=cost.getValue();
				if(costsMap.containsKey(id)){
					costsMap.put(id, costsMap.get(id)+id_num);
				}else {
					costsMap.put(id, id_num);
				}
			}
		}
		Map<Integer, Integer>resultMap=new HashMap<Integer, Integer>();
		for (Map.Entry<Integer, Float> entry : costsMap.entrySet()) {
			resultMap.put(entry.getKey(), ((Double)Math.ceil(entry.getValue())).intValue());
		}
		return resultMap;
	}
	/**
	 * 减去库存消耗
	 * @param orderId
	 * @return
	 */
	public Boolean reduceCosts(Integer orderId){
		try{
			Map<Integer, Integer>totalCosts=totalCosts(orderId);
			for (Map.Entry<Integer, Integer> cost : totalCosts.entrySet()) {
				Inventory inventory=inventoryDAO.findById(cost.getKey());
				inventory.setNum(inventory.getNum()-cost.getValue());
				inventoryDAO.update(inventory);
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 取消库存消耗
	 * @param orderId
	 * @return
	 */
	public Boolean cancelCosts(Integer orderId){
		try{
			Map<Integer, Integer>totalCosts=totalCosts(orderId);
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
