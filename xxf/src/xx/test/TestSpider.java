package xx.test;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import xx.dao.BlogDao;
import xx.model.Blog;
import xx.service.BlogService;

public class TestSpider extends BaseTest{

	public static void main(String[] args) throws IOException {
		BlogService blogService=ctx.getBean(BlogService.class);
		
		
		Document doc = Jsoup.connect("http://www.csdn.net/headlines.html").get();
		Elements alist=doc.select("#news_list dl dd ul li a");
		for (Element element : alist) {
			try {
				Document article=Jsoup.connect(element.attr("href")).get();
				Elements elements = article.select("div.detail h1.title");
				String title=element.text();
				Element elementdes=article.getElementById("article_description");
				String brief=elementdes.attr("value");
				String content=article.getElementsByClass("news_content").html();
				
				Blog blog=new Blog(title, "",brief, "", content, new Timestamp(new Date().getTime()), null, 2);
				blogService.saveOrUpdate(blog);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
	}

}
