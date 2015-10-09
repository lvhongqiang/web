package baking.model;

/**
 * Inventory entity. @author MyEclipse Persistence Tools
 */

public class Inventory implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer type;
	private Integer num;
	private String unit;

	// Constructors

	/** default constructor */
	public Inventory() {
	}

	/** full constructor */
	public Inventory(String name, Integer type, Integer num, String unit) {
		this.name = name;
		this.type = type;
		this.num = num;
		this.unit = unit;
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

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getUnit() {
		return this.unit;
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
		Inventory other = (Inventory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}