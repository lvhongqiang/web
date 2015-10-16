/**
 * 
 */
package baking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baking.model.Goods;
import baking.model.Inventory;
import baking.model.Orders;
import baking.model.OrdersGoods;
import baking.model.Page;
import baking.model.Step;
import baking.model.vo.Merge;

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
	public List<Object[]>details(Integer orderId){
		List<Object[]>result=new ArrayList<Object[]>();
		

		List<OrdersGoods>oglist=baseDao.find("from OrdersGoods where ordersId=?", orderId);
		//找到可以合并的商品，并分组。
		List<Merge> mergeList = merge(oglist);
		
		//对每一组的商品计算备料单
		for (Merge merge : mergeList) {
			List<OrdersGoods>list=merge.getList();
			
			//列出所有商品的id
			String gids = getGids(list);
			
			List<Step> stepList=baseDao.find("from Step where goodsId in ("+gids+") order by stepOrder asc");
			for (Step step : stepList) {
				
				
			}
			
		}
		

		
		for(OrdersGoods og : oglist){
			
		}
		
		return result;
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
