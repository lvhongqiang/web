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

public class TestSpidercnblog extends BaseTest{

	public static void main(String[] args) throws IOException {
		BlogService blogService=ctx.getBean(BlogService.class);
		
		for(int i=4;i<20;i++){
		Document doc = Jsoup.connect("http://www.cnblogs.com/cate/java/#p"+i).get();
		Elements alist=doc.select("#post_list div.post_item_body h3 a");
		for (Element element : alist) {
			try {
				Document article=Jsoup.connect(element.attr("href")).get();
				Elements elements = article.select("a#cb_post_title_url");
				String title=element.text();
				Element elementdes=article.getElementById("cnblogs_post_body");
				String brief=elementdes.text();
				if(brief.length()>500)
					brief=brief.substring(0, 500);
				String content=article.getElementById("cnblogs_post_body").html();
				
				Blog blog=new Blog(title, "",brief, "", content, new Timestamp(new Date().getTime()), null, 2);
				blogService.saveOrUpdate(blog);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		}
		System.out.print("完成！");
	}

}
