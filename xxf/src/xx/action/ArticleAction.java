package xx.action;

import xx.model.Article;

public class ArticleAction extends BaseAction {
	
	private Integer source=0;
	private Integer aid;
	private Article article;
	
	@Override
	public String execute(){
		article=articleService.get(aid);
		return "weixin";
	}
	
	public Article getArticle() {
		return article;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
	
}
