package xx.service;

import org.springframework.stereotype.Service;

import xx.model.Article;

@Service
public class ArticleService extends BaseService {

	public Article get(Integer aid) {
		return (Article)baseDao.get(Article.class, aid);
	}
}
