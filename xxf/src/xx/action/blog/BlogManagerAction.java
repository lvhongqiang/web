/**
 * 
 */
package xx.action.blog;

import xx.action.BaseAction;
import xx.model.Blog;
import xx.model.Page;
import xx.util.TimeUtil;

/**
 * @author lvhongqiang
 * @email lvhongqiang09@163.com
 * 2015年6月4日
 */
public class BlogManagerAction extends BaseAction {
	private Integer id;
	private Blog blog;
	private Page page;
	private Integer p=1;//页码
	private Integer s=10;//每页条数
	public String list(){
		page=blogService.list(p,s);
		return SUCCESS;
	}
	public String add_md() {
		blog=new Blog();
		blog.setCreateTime(TimeUtil.now());
		blog.setType(1);
		return SUCCESS;
	}
	public String add_html() {
		blog=new Blog();
		blog.setCreateTime(TimeUtil.now());
		blog.setType(2);
		return SUCCESS;
	}
	public String update(){
		blog=(Blog)blogService.get(Blog.class, id);
		if(blog.getType()==1){//markdown
			return "md";
		}else if (blog.getType()==2) {//hmtl
			return "html";
		}
		return SUCCESS;
	} 
	
	public String save(){
		blogService.saveOrUpdate(blog);
		return "list";
	}
	public String del(){
		blog=(Blog)blogService.get(Blog.class, id);
		blogService.delete(blog);
		return "list";
	}
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setP(Integer p) {
		this.p = p;
	}
	public void setS(Integer s) {
		this.s = s;
	}
}
