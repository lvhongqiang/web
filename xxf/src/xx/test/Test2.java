/**
 * 
 */
package xx.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author Lv
 * @date 2015年11月3日
 * @version 1.0
 */
public class Test2 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient client=HttpClients.createDefault();
		
		//登陆信息
		List<NameValuePair>params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("Email", "335831@clouddream.net"));
		params.add(new BasicNameValuePair("Password", "0EYvW1"));
		params.add(new BasicNameValuePair("RememberMe", "true"));
		params.add(new BasicNameValuePair("FileId", "0"));
		UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params, Consts.UTF_8);
		HttpPost httpPost=new HttpPost("http://967453787.wezhan.cn/login");
		httpPost.setEntity(entity);
		
		//登陆
		HttpResponse response= client.execute(httpPost);
		System.out.println(EntityUtils.toString(response.getEntity()));
		
		/*登陆成功
		<html><head><title>Object moved</title></head><body>
			<h2>Object moved to <a href="/Admin/SiteAdmin/Index?yibu_php_sessionId=">here</a>.</h2>
		</body></html>
		 */
		
//		System.out.println(response);
		
		//添加文章
//		HttpPost addPost=add();
//		HttpResponse addresponse= client.execute(addPost);
//		System.out.println(addresponse);
		
		//修改文章
		HttpPost updatePost=update();
		HttpResponse updateresponse= client.execute(updatePost);
		System.out.println(updateresponse);
		
				
		
		//上传图片
//		HttpPost uploadPost=upload();		
//		HttpResponse uploadresponse= client.execute(uploadPost);
//		System.out.println(EntityUtils.toString(uploadresponse.getEntity()));
		
		
		
	}
	
	public static HttpPost upload(){
		MultipartEntityBuilder entity=MultipartEntityBuilder.create();
		entity.addTextBody("localUrl", "IMG_0001.JPG");
		entity.addBinaryBody("imgFile", new File("E:\\照片\\Ipad\\IMG_0001.JPG"));
		HttpPost httpPost=new HttpPost("http://967453787.wezhan.cn/picture/SingleUpload?categoryId=0&dir=image");
		httpPost.setEntity(entity.build());
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0");
		return httpPost;
	}
	

	/**修改文章
	 * 
	 * @return
	 */
	public static HttpPost update() {
		List<NameValuePair>params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("Id", "277160652"));
		params.add(new BasicNameValuePair("LanguageId", "2"));
		params.add(new BasicNameValuePair("Title", "标题bbbbba_httpclient"));
		params.add(new BasicNameValuePair("Short", "简介"));
		params.add(new BasicNameValuePair("Full", "<style>h1{font-size:20pt;}</style><script src=\"//code.jquery.com/jquery-2.1.4.min.js\"></script><script type=\"text/javascript\">"
				+ "//$(\"html\").html($(\"#realhtml\").html());</script><div id=\"realhtml\" style='z-index=1000;background:white;position:fixed;top:0;left:0;height:100%;width:100%;'>"
				+ "<link rel=\"stylesheet\" href=\"http://967453787.wezhan.cn/abc\" type=\"text/css\" />"
				+ "<h1>    <a>hello</a> world23</h1></div>"));
		params.add(new BasicNameValuePair("CreatedOn", "2015-11-14 04:29:39"));
		params.add(new BasicNameValuePair("selCategoryId", "27480"));
		params.add(new BasicNameValuePair("StartDate", "2015-11-03"));
		params.add(new BasicNameValuePair("MetaKeywords", "1"));
		params.add(new BasicNameValuePair("SeName", "a"));
		params.add(new BasicNameValuePair("AllowComments", "True"));
		params.add(new BasicNameValuePair("SubjectToAcl", "False"));
		params.add(new BasicNameValuePair("Locales[0].LanguageId", "1"));
		params.add(new BasicNameValuePair("Locales[0].Title", ""));
		params.add(new BasicNameValuePair("Locales[0].Short", ""));
		params.add(new BasicNameValuePair("Locales[0].Full", ""));
		params.add(new BasicNameValuePair("Locales[0].MetaKeywords", ""));
		params.add(new BasicNameValuePair("Locales[0].SeName", ""));
		params.add(new BasicNameValuePair("editorValue", ""));
		params.add(new BasicNameValuePair("editorValue", ""));
		params.add(new BasicNameValuePair("editorValue", "内容"));
		params.add(new BasicNameValuePair("editorValue", "简介"));
		UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params, Consts.UTF_8);
		HttpPost httpPost=new HttpPost("http://967453787.wezhan.cn/Admin/News/Edit/277160652");
		httpPost.setEntity(entity);
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0");
//		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
//		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
//		httpPost.setHeader("Referer", "http://967453787.wezhan.cn/Admin/News/Create?categoryId=0");
//		httpPost.setHeader("Origin", "http://967453787.wezhan.cn");
		return httpPost;
	}

	
	/**添加文章
	 * 
	 * @return
	 */
	public static HttpPost add() {
		List<NameValuePair>params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("Id", "0"));
		params.add(new BasicNameValuePair("LanguageId", "2"));
		params.add(new BasicNameValuePair("Title", "标题bbbbba_httpclient"));
		params.add(new BasicNameValuePair("Short", "简介"));
		params.add(new BasicNameValuePair("Full", "<style>h1{font-size:20pt;}</style><script src=\"//code.jquery.com/jquery-2.1.4.min.js\"></script><script type=\"text/javascript\">$(\"#realhtml\").unwrap();</script><div id=\"realhtml\"><h1>    hello world23</h1></div>"));
		params.add(new BasicNameValuePair("CreatedOn", "2015-11-14 04:29:39"));
		params.add(new BasicNameValuePair("selCategoryId", "27480"));
		params.add(new BasicNameValuePair("StartDate", "2015-11-03"));
		params.add(new BasicNameValuePair("MetaKeywords", ""));
		params.add(new BasicNameValuePair("SeName", ""));
		params.add(new BasicNameValuePair("AllowComments", "True"));
		params.add(new BasicNameValuePair("SubjectToAcl", "False"));
		params.add(new BasicNameValuePair("Locales[0].LanguageId", "1"));
		params.add(new BasicNameValuePair("Locales[0].Title", ""));
		params.add(new BasicNameValuePair("Locales[0].Short", ""));
		params.add(new BasicNameValuePair("Locales[0].Full", ""));
		params.add(new BasicNameValuePair("Locales[0].MetaKeywords", ""));
		params.add(new BasicNameValuePair("Locales[0].SeName", ""));
		params.add(new BasicNameValuePair("editorValue", ""));
		params.add(new BasicNameValuePair("editorValue", ""));
		params.add(new BasicNameValuePair("editorValue", "内容"));
		params.add(new BasicNameValuePair("editorValue", "简介"));
		UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params, Consts.UTF_8);
		HttpPost httpPost=new HttpPost("http://967453787.wezhan.cn/Admin/News/Create?categoryId=0");
		httpPost.setEntity(entity);
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0");
//		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
//		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
//		httpPost.setHeader("Referer", "http://967453787.wezhan.cn/Admin/News/Create?categoryId=0");
//		httpPost.setHeader("Origin", "http://967453787.wezhan.cn");
		return httpPost;
	}

}
