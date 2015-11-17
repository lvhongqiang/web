package xx.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Blog entity. @author MyEclipse Persistence Tools
 */

public class Blog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String pic;
	private String brief;
	private String markdown;
	private String html;
	private Timestamp createTime;
	private Integer menuId;
	private Integer type;

	// Constructors

	/** default constructor */
	public Blog() {
	}
	public Blog(Integer id, String title, String pic, String brief,
			Date createTime, Integer menuId, Integer type) {
		this.id=id;
		this.title = title;
		this.pic = pic;
		this.brief=brief;
		this.createTime = new Timestamp(createTime.getTime());
		this.menuId = menuId;
		this.type = type;
	}
	
	/** full constructor */
	public Blog(String title, String pic, String brief,String markdown, String html,
			Timestamp createTime, Integer menuId, Integer type) {
		this.title = title;
		this.pic = pic;
		this.brief=brief;
		this.markdown = markdown;
		this.html = html;
		this.createTime = createTime;
		this.menuId = menuId;
		this.type = type;
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

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getMarkdown() {
		return this.markdown;
	}

	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * @return the brief
	 */
	public String getBrief() {
		return brief;
	}

	/**
	 * @param brief the brief to set
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}

}