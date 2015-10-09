/**
 * 
 */
package baking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;

import org.springframework.stereotype.Service;

import baking.model.Inventory;
import baking.model.Recipe;

/**
 * @author lvhongqiang
 *
 */
@Service
public class InventoryService extends BaseService {

	public List<Inventory>listall(){
		return baseDao.find("from Inventory order by id");
	}
	
	/**
	 * 减少库存
	 * @return
	 */
	public Map<Integer, Integer> minus(Integer goodsId, Integer num, Boolean drill){
		String hql="from Recipe where goodsId=?";
		List<Recipe>recipes=baseDao.find(hql, goodsId);
		Map<Integer, Integer> map=new HashMap<Integer, Integer>();
		for (Recipe recipe : recipes) {
			Integer inventory=recipe.getInventId();
			if(map.containsKey(inventory)){
				map.put(inventory, map.get(inventory)+recipe.getUsage());
			}else {
				map.put(inventory, recipe.getUsage());
			}
		}
		if(drill==null||drill!=true){//真减
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
				baseDao.executeHql("update Inventory set num=num-? where id=?",new Object[]{entry.getValue(),entry.getKey()});
			}
		}
		return map;
	}
	
	/**
	 * 计算库存消耗，但不减库存
	 * @param orderId
	 * @return
	 */
	public Map<Integer, Integer> totalCosts(Integer orderId){
		Map<Integer, Integer> costsMap= new HashMap<Integer, Integer>();
		/*
		Map<Integer, Integer> map = minus(goodsId, num, true);
		for (Map.Entry<Integer, Integer> cost : map.entrySet()) {
			Integer id=cost.getKey();
			Integer id_num=cost.getValue();
			if(costsMap.containsKey(id)){
				costsMap.put(id, costsMap.get(id)+id_num);
			}else {
				costsMap.put(id, id_num);
			}
		}
		*/
		return costsMap;
	}
}
