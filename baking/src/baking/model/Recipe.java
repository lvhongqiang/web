package baking.model;

/**
 * Recipe entity. @author MyEclipse Persistence Tools
 */

public class Recipe implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer goodsId;
	private Integer inventId;
	private Integer stepId;
	private Integer usage;
	private String unit;
	private Integer showOrder;

	// Constructors

	/** default constructor */
	public Recipe() {
	}

	/** full constructor */
	public Recipe(Integer goodsId, Integer inventId, Integer stepId,
			Integer usage, String unit, Integer showOrder) {
		this.goodsId = goodsId;
		this.inventId = inventId;
		this.stepId = stepId;
		this.usage = usage;
		this.unit = unit;
		this.showOrder = showOrder;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getInventId() {
		return this.inventId;
	}

	public void setInventId(Integer inventId) {
		this.inventId = inventId;
	}

	public Integer getStepId() {
		return this.stepId;
	}

	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}

	public Integer getUsage() {
		return this.usage;
	}

	public void setUsage(Integer usage) {
		this.usage = usage;
	}

	public Integer getShowOrder() {
		return this.showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}