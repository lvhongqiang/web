package baking.model;

/**
 * Step entity. @author MyEclipse Persistence Tools
 */

public class Step implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer goodsId;
	private String stepName;
	private String detail;
	private Integer stepOrder;

	// Constructors

	/** default constructor */
	public Step() {
	}

	/** full constructor */
	public Step(Integer goodsId, String stepName, String detail,
			Integer stepOrder) {
		this.goodsId = goodsId;
		this.stepName = stepName;
		this.detail = detail;
		this.stepOrder = stepOrder;
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

	public String getStepName() {
		return this.stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getStepOrder() {
		return this.stepOrder;
	}

	public void setStepOrder(Integer stepOrder) {
		this.stepOrder = stepOrder;
	}

}