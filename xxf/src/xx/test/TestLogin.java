/**
 * 
 */
package xx.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author lvhongqiang
 *
 */
public class TestLogin {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClient client=HttpClients.createDefault();
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("FileId", "0"));
		params.add(new BasicNameValuePair("Email", "335831@clouddream.net"));
		params.add(new BasicNameValuePair("Password", "0EYvW1"));
		params.add(new BasicNameValuePair("RememberMe", "true"));
		UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params,Consts.UTF_8);
		
		HttpPost httpPost=new HttpPost("http://967453787.wezhan.cn/login");
		httpPost.setEntity(entity);
		
		try {
			HttpResponse response = client.execute(httpPost);
			Header[] headers=response.getAllHeaders();
			for (Header header : headers) {
				System.out.println(header.getName());
				System.out.println(header.getValue());
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
