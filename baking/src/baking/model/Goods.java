package baking.model;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String shortName;
	private Integer numPerServing;
	private String unit;
	private Long price;
	private Long cost;
	private Integer merge;

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** full constructor */
	public Goods(String name, Integer numPerServing, String unit, Long price) {
		this.name = name;
		this.numPerServing = numPerServing;
		this.unit = unit;
		this.price = price;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumPerServing() {
		return this.numPerServing;
	}

	public void setNumPerServing(Integer numPerServing) {
		this.numPerServing = numPerServing;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Long getPrice() {
		return this.price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Integer getMerge() {
		return merge;
	}

	public void setMerge(Integer merge) {
		this.merge = merge;
	}

}