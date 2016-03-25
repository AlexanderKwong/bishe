package org.kwong.bishe.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 文件生成器
 *
 */
public class FileGenerator {

	/**
	 * 把给定的网址，生成html页面保存
	 * @param sSourceUrl  url
	 * @param sDestDir    保存路径
	 * @param sHtmlFile   保存文件名
	 * @param charcode    编码，需要与原始来源一致才能不乱吗，可以为空，则默认utf-8
	 * @throws IOException
	 */
	public static void convert2Html(String sSourceUrl, String sDestDir,
			String sHtmlFile,String charcode) throws IOException {
		if(charcode==null)
			charcode="UTF-8";
		int HttpResult;
		URL url = new URL(sSourceUrl);
		URLConnection urlconn = url.openConnection();
		urlconn.connect();
		HttpURLConnection httpconn = (HttpURLConnection) urlconn;
		HttpResult = httpconn.getResponseCode();
		if (HttpResult != HttpURLConnection.HTTP_OK) {

		} else {

			InputStreamReader isr = new InputStreamReader(
					httpconn.getInputStream(), charcode);
			BufferedReader in = new BufferedReader(isr);

			String inputLine;
			if (!sDestDir.endsWith("/"))
				sDestDir += "/";
			FileOutputStream fout = new FileOutputStream(sDestDir + sHtmlFile);
            OutputStreamWriter   writer   =   new   OutputStreamWriter(fout,   charcode); 
			while ((inputLine = in.readLine()) != null) {
				writer.write(inputLine);
			}
			in.close();
			writer.close();
			fout.close();
			

		}

	}

	public static void main(String[] args) {
		
		try {
			File f=new File("E:/test.html");
			if(System.currentTimeMillis()-f.lastModified()> 600000)//10分钟
			{			
				convert2Html(
						"http://192.168.1.220:8088/index.do?action=home1",
						"E:/", "test.html","UTF-8");		
				//action=home是静态的
				//action=home1是动态的
			}
			else{
				LoggerUtil.info( "还没到10分钟，不急着生成哦" );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}


