package xx.action;

import org.springframework.beans.factory.annotation.Autowired;

import xx.service.ArticleService;
import xx.service.BlogService;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	@Autowired protected ArticleService articleService;
	@Autowired protected BlogService blogService;
	
}
