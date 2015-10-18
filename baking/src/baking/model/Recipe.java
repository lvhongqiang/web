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
	
	private Inventory inventory;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}