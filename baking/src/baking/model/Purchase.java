package baking.model;

import java.sql.Timestamp;

/**
 * Purchase entity. @author MyEclipse Persistence Tools
 */

public class Purchase implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer inventId;
	private Integer num;
	private Timestamp createTime;
	private Long cost;
	private String remark;

	// Constructors

	/** default constructor */
	public Purchase() {
	}

	/** full constructor */
	public Purchase(Integer inventId, Integer num, Timestamp createTime,
			Long cost, String remark) {
		this.inventId = inventId;
		this.num = num;
		this.createTime = createTime;
		this.cost = cost;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInventId() {
		return this.inventId;
	}

	public void setInventId(Integer inventId) {
		this.inventId = inventId;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getCost() {
		return this.cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}