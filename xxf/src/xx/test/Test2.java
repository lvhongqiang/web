/**
 * 
 */
package xx.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.cglib.transform.impl.AddDelegateTransformer;

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
		
		//添加文章
		HttpPost addPost=add();
		HttpResponse addresponse= client.execute(addPost);
		
		response.
		
	}
	
	/**添加文章
	 * 
	 * @return
	 */
	public static HttpPost add() {
		List<NameValuePair>params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("Id", "0"));
		params.add(new BasicNameValuePair("LanguageId", "2"));
		params.add(new BasicNameValuePair("Title", "标题2_httpclient"));
		params.add(new BasicNameValuePair("Short", "简介"));
		params.add(new BasicNameValuePair("Full", "内容"));
		params.add(new BasicNameValuePair("CreatedOn", "2015-11-03+04:29:39"));
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
		return httpPost;
	}

}
