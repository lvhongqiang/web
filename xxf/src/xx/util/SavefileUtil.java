package xx.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class SavefileUtil {
	

	/**
	 * 保存网络图片	
	 * @param imgUrl
	 * @param filePath
	 * @throws Exception
	 */
	public static void saveUrlImg(String imgUrl,String filePath) throws Exception{
		try {
			URL url = new URL(imgUrl);
			URLConnection uc = url.openConnection();
			InputStream is = uc.getInputStream();
			File file = new File(filePath);
			if(!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileOutputStream out = new FileOutputStream(file);
			int i = 0;
			while ((i = is.read()) != -1) {
				out.write(i);
			}
			is.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 保存文件
	 * @param file
	 * @param contentType
	 * @param savepath
	 * @param rename 是否按时间重命名新文件
	 * @return
	 * @throws Exception
	 */
	public static String savefile(File file,String filename,String savepath,boolean rename) throws Exception{
		String type=filename.substring(filename.lastIndexOf(".")+1, filename.length());
		String realpath = ServletActionContext.getServletContext().getRealPath(savepath);
		System.out.println("realpath: "+realpath);
		String newfilename=filename;
		if(rename)
			newfilename=timeString()+"."+type;
		if (file != null) {            
			File savefile = new File(new File(realpath), newfilename);            
			if (!savefile.getParentFile().exists())               
				savefile.getParentFile().mkdirs();            
			FileUtils.copyFile(file, savefile);                
			}
		return savepath+newfilename;
	}
	/**
	 * 生成时间字符串
	 * @return
	 */
	public static String timeString(){
		SimpleDateFormat formate=new java.text.SimpleDateFormat("yyyyMMddHHmmssSSSS"); 
		return formate.format(Calendar.getInstance().getTime());
	}
	/**
	 * 返回服务器实际路径
	 * @param path
	 * @return
	 */
	public static String realpath(String path){
		return ServletActionContext.getServletContext().getRealPath(path);
	}
}
