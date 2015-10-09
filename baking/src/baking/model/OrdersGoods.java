package baking.model;

/**
 * OrdersGoods entity. @author MyEclipse Persistence Tools
 */

public class OrdersGoods implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer ordersId;
	private Integer goodsId;
	private Integer num;

	// Constructors

	/** default constructor */
	public OrdersGoods() {
	}

	/** full constructor */
	public OrdersGoods(Integer ordersId, Integer goodsId, Integer num) {
		this.ordersId = ordersId;
		this.goodsId = goodsId;
		this.num = num;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrdersId() {
		return this.ordersId;
	}

	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}