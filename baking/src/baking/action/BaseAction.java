package baking.action;

import org.springframework.beans.factory.annotation.Autowired;

import baking.service.GoodsService;
import baking.service.InventoryService;
import baking.service.OrderService;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	@Autowired protected OrderService orderService;
	@Autowired protected GoodsService goodsService;
	@Autowired protected InventoryService inventoryService;

	
}
