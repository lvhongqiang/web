package baking.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */

public class Orders implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private Timestamp createTime;
	private Timestamp postTime;
	private Long money;
	private Long costs;
	private Integer finished;
	private String remark;
	private Integer costsReduced;
	private Integer deleted;

	// Constructors

	/** default constructor */
	public Orders() {
		createTime=postTime=new Timestamp(new Date().getTime());
		finished=0;
		money=0l;
		costs=0l;
	}

	/** full constructor */
	public Orders(String title, Timestamp createTime, Timestamp postTime,
			Long money, Integer finished, String remark) {
		this.title = title;
		this.createTime = createTime;
		this.postTime = postTime;
		this.money = money;
		this.finished = finished;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getPostTime() {
		return this.postTime;
	}

	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}

	public Long getMoney() {
		return this.money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public Integer getFinished() {
		return this.finished;
	}

	public void setFinished(Integer finished) {
		this.finished = finished;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCosts() {
		return costs;
	}

	public void setCosts(Long costs) {
		this.costs = costs;
	}

	public Integer getCostsReduced() {
		return costsReduced;
	}

	public void setCostsReduced(Integer costsReduced) {
		this.costsReduced = costsReduced;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}