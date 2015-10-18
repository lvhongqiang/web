/**
 * 
 */
package baking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baking.model.Goods;
import baking.model.Inventory;
import baking.model.Orders;
import baking.model.OrdersGoods;
import baking.model.Page;
import baking.model.Recipe;
import baking.model.Step;
import baking.model.vo.Merge;
import baking.model.vo.Prepare;
import baking.model.vo.PrepareGroup;
import baking.model.vo.PrepareStep;

/**
 * @author lvhongqiang
 *
 */
@Service
public class OrderService extends BaseService {
	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
	@Autowired private InventoryService inventoryService;
	
	public Page listPage(Integer pageNo,Integer pageSize){
		String hql="from Orders order by createTime desc";
		return baseDao.findPage(hql, "select count(*) "+hql, pageNo, pageSize);
	}
	
	public Orders save(Map<String, Integer> orders){
		try {
			StringBuilder title=new StringBuilder();
			Orders order=new Orders();
			
			ordersDAO.save(order);
			
			Map<Integer, Integer> costsMap=new HashMap<Integer, Integer>();//总库存消耗
			
			for (Map.Entry<String, Integer> entry : orders.entrySet()) {
				Integer goodsId = Integer.parseInt(entry.getKey());
				Integer num = entry.getValue();
				if(num!=null&&num>0){
					Goods goods=goodsDAO.findById(goodsId);
					
					title.append(goods.getShortName()+"x"+num+" ");//组成订单标题

					order.setMoney(order.getMoney()+goods.getPrice()*num);//计算订单价格
					order.setCosts(order.getCosts()+goods.getCost()*num);//计算订单成本
					
					OrdersGoods oGoods=new OrdersGoods(order.getId(), goodsId, num);//保存订单-商品关系
					baseDao.save(oGoods);
					
					//减库存
					inventoryService.minus(goodsId, num, null);
					
				}
			}
			order.setTitle(title.toString());
			ordersDAO.update(order);
			return order;
		} catch (Exception e) {
			log.error("保存订单出错", e);
			return null;
		}

	}
	
	/**
	 * 列出某一订单的详细库存消耗
	 * @param orderId
	 * @return
	 */
	public List<Object[]>costDetial(Integer orderId){
		List<Object[]>result=new ArrayList<Object[]>();
		Map<Integer, Integer> map=inventoryService.totalCosts(orderId);
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			Inventory inventory=inventoryDAO.findById(entry.getKey());
			result.add(new Object[]{inventory,entry.getValue()});
		}
		return result;
	}
	
	/**
	 * 列出某一订单的备料信息
	 * @param orderId
	 * @return
	 */
	public List<PrepareGroup>details(Integer orderId){
		List<PrepareGroup>result=new ArrayList<PrepareGroup>();
		List<OrdersGoods>oglist=baseDao.find("from OrdersGoods where ordersId=?", orderId);
		//找到可以合并的商品，并分组。
		List<Merge> mergeList = merge(oglist);
		
		//对每一组的商品计算备料单
		for (Merge merge : mergeList) {
			PrepareGroup prepareGroup=new PrepareGroup();
			List<OrdersGoods>list=merge.getList();
			prepareGroup.setTitle(getTitle(list));
			
			//列出所有商品的id
			String gids = getGids(list);
			List<Step> stepList=baseDao.find("from Step where goodsId in ("+gids+") order by stepOrder asc");
			
			List<PrepareStep>prepareSteps=new ArrayList<PrepareStep>();
			for (Step step : stepList) {
				PrepareStep prepareStep=new PrepareStep();
				prepareStep.setStep(step);

				
				List<Prepare>prepares=new ArrayList<Prepare>();
				//
				List<OrdersGoods>ogtempList=cloneList(list);
				for (int i=0;i<ogtempList.size();i++) {
					OrdersGoods og = ogtempList.get(i);
					
					Integer count=baseDao.countHql("select count(*) from Recipe where stepId=? and goodsId=?",new Object[]{step.getId(),og.getGoodsId()});
					if(count!=null&&count>0){//如果此商品有当前的步骤step
						pushTheOg(og, prepares, step);
					}
				}
				
				for (Prepare prepare : prepares) {
					List<Recipe> recipeList=getRecipeList(prepare.getOgList(),step);
					prepare.setRecipeList(recipeList);
					prepare.setTitle(getTitle(prepare.getOgList()));
				}
				
				prepareStep.setList(prepares);
				prepareSteps.add(prepareStep);
			}
			prepareGroup.setSteplist(prepareSteps);
			result.add(prepareGroup);
		}
		return result;
	}
	
	/**
	 * 根据商品list计算备料list
	 * @param ogList
	 * @param step
	 * @return
	 */
	private List<Recipe> getRecipeList(List<OrdersGoods> ogList, Step step) {
		List<Recipe> result=new ArrayList<Recipe>();
		for (OrdersGoods og : ogList) {
			List<Recipe> list= baseDao.find("from Recipe where stepId=? and goodsId=?",new Object[]{step.getId(),og.getGoodsId()});
			for (Recipe recipe : list) {
				Integer index=findByid(result, recipe.getInventId());
				if(index>-1){
					Recipe r=result.get(index);
					r.setUsage(r.getUsage()+recipe.getUsage()*og.getNum());
				}else {
					Inventory inventory=inventoryDAO.findById(recipe.getInventId());
					Recipe r=new Recipe();
					r.setInventId(recipe.getInventId());
					r.setInventory(inventory);
					r.setUsage(recipe.getUsage()*og.getNum());
					result.add(r);
				}
			}
		}
		return result;
	}

	private Integer findByid(List<Recipe>list,Integer id){
		for (int i = 0; i < list.size(); i++) {
			if(id!=null&&id.equals(list.get(i).getInventId())){
				return i;
			}
		}
		return -1;
	}
	 
	
	/**
	 * 把当前商品及数量放入prepare列表中
	 * @param og
	 * @param prepares
	 * @param step
	 * @return
	 */
	private boolean pushTheOg(OrdersGoods og,List<Prepare>prepares, Step step){
		Integer max=step.getMaxUnit();

		Prepare p=new Prepare();
		boolean newone=true;//是否一个新的prepare
		if(prepares.size()>0){//list非空，已存在prepare，应先填满已有的prepare
			Prepare preP=prepares.get(prepares.size()-1);
			if(preP.getNum()<step.getMaxUnit()){
				max-=preP.getNum();
				p=preP;
				newone=false;
			}
		}
		
		while(true) {
			if(og.getNum()<=max){//商品数量没有超过step最大值，直接加到列表中。
				p.getOgList().add(og);
				if(newone){
					prepares.add(p);
				}
				break;
			}else {//超过step最大值，分成多个prepare
				p.getOgList().add(new OrdersGoods(og.getOrdersId(), og.getGoodsId(), max));
				og.setNum(og.getNum()-max);//减去一个prepare的量。
				if(newone){
					prepares.add(p);
				}
			}
			p=new Prepare();
			newone=true;
			max=step.getMaxUnit();
		}
		return true;
		
	}
	
	private List<OrdersGoods> cloneList(List<OrdersGoods> list) {
		List<OrdersGoods>result=new ArrayList<OrdersGoods>();
		for (OrdersGoods og : list) {
			result.add(new OrdersGoods(og.getOrdersId(), og.getGoodsId(), og.getNum()));
		}
		return result;
	}

	/**
	 * 根据订单信息生成标题 例如：芝x1 蓝x2
	 * @param list
	 * @return
	 */
	private String getTitle(List<OrdersGoods> list) {
		String title="";
		for (OrdersGoods og : list) {
			Goods goods=goodsDAO.findById(og.getGoodsId());
			title+=goods.getShortName()+"x"+og.getNum()+" ";
		}
		return title;
	}

	/**
	 * 列出所有商品的id
	 * @param list
	 * @return
	 */
	private String getGids(List<OrdersGoods> list) {
		String gids="";
		for (int i=0;i<list.size();i++) {
			OrdersGoods og = list.get(i);
			if(i==0){
				gids+=og.getGoodsId();					
			}else {
				gids+=","+og.getGoodsId();
			}
		}
		return gids;
	}

	/**
	 * 找到可以合并的商品，并分组。
	 * @param oglist
	 * @return
	 */
	private List<Merge> merge(List<OrdersGoods> oglist) {
		List<Merge>merges=new ArrayList<Merge>();
		for(OrdersGoods og : oglist){
			Goods goods = goodsDAO.findById(og.getGoodsId());
			if(goods.getMerge()==null){//不跟别人合并
				List<OrdersGoods>list=new ArrayList<OrdersGoods>();
				list.add(og);
				merges.add(new Merge(list, null));//直接加到列表里
			}else {
				Integer index=findByMerge(goods.getMerge(),merges);
				if(index>-1){
					merges.get(index).getList().add(og);
				}else {
					List<OrdersGoods>list=new ArrayList<OrdersGoods>();
					list.add(og);
					merges.add(new Merge(list,goods.getMerge()));//新建list加到列表里
				}
			}
			
		}
		return merges;
	}
	
	/**
	 * @param mergeId
	 * @param merges
	 * @return
	 */
	private Integer findByMerge(Integer mergeId, List<Merge> merges) {
		for (int i = 0; i < merges.size(); i++) {
			Merge og=merges.get(i);
			if(mergeId!=null&&mergeId.equals(og.getMergeId())){
				return i;
			}
		}
		return -1;
	}

	/**
	 * 删除订单
	 * @param orderId
	 * @return
	 */
	public Boolean delete(Integer orderId){
		try{
			//取消库存消耗
			inventoryService.cancelCosts(orderId);
			
			//删除订单
			Orders orders=ordersDAO.findById(orderId);
			ordersDAO.delete(orders);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
