/**
 * 
 */
package baking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baking.dao.OrdersDAO;
import baking.model.Goods;
import baking.model.Orders;
import baking.model.OrdersGoods;

/**
 * @author lvhongqiang
 *
 */
@Service
public class OrderService extends BaseService {
	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
	@Autowired private InventoryService inventoryService;
	
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
}
