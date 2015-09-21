package xx.service;

import java.sql.Timestamp;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import xx.model.Article;
import xx.util.SavefileUtil;

@Service
public class InputService extends BaseService {

	public Integer saveWeixin(String url){
		try {
			Article article=saveArticleContent(url);
			return article.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**保存文章正文html**/
	private Article saveArticleContent(String url)throws Exception{
		Document doc = Jsoup.connect(url).get();
		Element contentElement=doc.getElementById("js_content");
		//生成article
		Timestamp now=new Timestamp(new Date().getTime());
		String html=contentElement.html();
		String title=doc.title();
		Article article=new Article(title, null, html, 1, now,url);
		baseDao.saveOrUpdate(article);
		//保存图片
		Elements imgs=contentElement.getElementsByTag("img");
		for (int i=0;imgs!=null&&i<imgs.size();i++) {
			Element img=imgs.get(i);
			String datasrc=img.attr("data-src");
			String filePath="article/"+article.getId()+"/images/"+i+".jpg";
			SavefileUtil.saveUrlImg(datasrc, SavefileUtil.realpath(filePath));
			img.attr("data-src", filePath);
		}
		String content=contentElement.html();
		article.setContent(content);
		baseDao.saveOrUpdate(article);
		return article;
	}
	/**保存文章所有html**/
	private Article saveArticleHtml(String url) throws Exception{
		Document doc = Jsoup.connect(url).get();

		//移除js_toobar
		Element js_toobar = doc.getElementById("js_toobar");
		if(js_toobar!=null)js_toobar.remove();
		
		//生成article
		Timestamp now=new Timestamp(new Date().getTime());
		String html=doc.head().toString()+doc.body().toString();
		String title=doc.title();
		Article article=new Article(title, null, html, 1, now,url);
		baseDao.saveOrUpdate(article);
		//保存图片
		Elements imgs=doc.getElementsByTag("img");
		for (int i=0;imgs!=null&&i<imgs.size();i++) {
			Element img=imgs.get(i);
			String datasrc=img.attr("data-src");
			String filePath="article/"+article.getId()+"/images/"+i+".jpg";
			SavefileUtil.saveUrlImg(datasrc, SavefileUtil.realpath(filePath));
			img.attr("data-src", filePath);
		}
		String content=doc.head().toString()+doc.body().toString();
		article.setContent(content);
		baseDao.saveOrUpdate(article);
		return article;
	}
	private String getHtml(String url) throws Exception{
		Document doc = Jsoup.connect(url).get();
		return doc.head().toString()+doc.body().toString();
	}
	
}
