package xx.action.blog;

import java.sql.Timestamp;

import xx.action.BaseAction;
import xx.model.Blog;
import xx.model.Page;
import xx.util.TimeFormat;

public class BlogIndexAction extends BaseAction {

	private Page page;
	private Integer id;
	private Blog blog;
	private Integer p=1;//页码
	private Integer s=10;//每页条数
	
	
	public String execute(){
		page=blogService.list(p,s);
		return SUCCESS;
	}

	public String blog(){
		blog=(Blog)blogService.get(Blog.class, id);
		if(blog.getType()==1){
			return "md";
		}else {
			return "html";
		}
	}

	public TimeFormat TimeFormat(Timestamp time){
		return new TimeFormat(time);
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	public void setP(Integer p) {
		this.p = p;
	}

	public void setS(Integer s) {
		this.s = s;
	}
}
