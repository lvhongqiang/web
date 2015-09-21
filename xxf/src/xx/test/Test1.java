package xx.test;

import java.sql.Timestamp;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import xx.model.Article;

public class Test1 {

	public static void main(String[] arrs) throws Exception{
		Test1 test1=new Test1();
		test1.getArticle("http://mp.weixin.qq.com/s?__biz=MzA5OTYwNDUzNA==&mid=200200556&idx=2&sn=c884da2cb10b042f4e52f01434589950&scene=2&from=timeline&isappinstalled=0#rd");
		
	}
	
	private Article getArticle(String url) throws Exception{

		Document doc = Jsoup.connect(url).get();
		Element contentElement=doc.getElementById("js_content");
		String contentHtml=contentElement.html();
		
		
		
//		Document doc = Jsoup.connect(url).get();
		Element js_toobar = doc.getElementById("js_toobar");
		if(js_toobar!=null)js_toobar.remove();
		//生成article
		Timestamp now=new Timestamp(new Date().getTime());
		String html=doc.head().toString()+doc.body().toString();;
		String content=doc.body().text();
		String title=doc.title();
		Article article=new Article(title, content, html, 1, now,url);
		return article;
	}
}
