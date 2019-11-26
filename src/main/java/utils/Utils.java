package utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * slflsfls<br>
 *     s;f;kskfe
 */
public class Utils {
	/**
	 * @param file
	 * @param content
	 * @throws Exception 将String写入指定的文件中。
	 */
	public static void write(String file, String content) throws Exception {

		// 指锟斤拷要写锟斤拷锟侥硷拷锟侥伙拷锟斤拷锟斤拷锟斤拷纸锟斤拷锟�
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
//		byte[] bb = content.getBytes();
		byte[] bb = content.getBytes("UTF-8");
//		byte[] bb = content.getBytes("UTF-8");
		out.write(bb, 0, bb.length);// 写锟诫到锟斤拷锟斤拷锟�

		out.close();

	}

	/**
	 * @param file
	 * @return
	 * @throws Exception 文件变成String
	 * 
	 */
	public static String getResFromFile(String file) throws Exception {
		int len = 0;
		String res;
		// 指锟斤拷要锟斤拷取锟侥硷拷锟侥伙拷锟斤拷锟斤拷锟斤拷锟街斤拷锟斤拷
		BufferedInputStream in;
		byte[] bb;// 锟斤拷锟斤拷锟芥储每锟轿讹拷取锟斤拷锟斤拷锟街斤拷锟斤拷锟斤拷
		in = new BufferedInputStream(new FileInputStream(file));
		// 指锟斤拷要写锟斤拷锟侥硷拷锟侥伙拷锟斤拷锟斤拷锟斤拷纸锟斤拷锟�
//       BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("F:\\dir\\index2.html"));  

		bb = new byte[1024 * 100];
		int n;// 每锟轿讹拷取锟斤拷锟斤拷锟街斤拷锟斤拷锟斤拷某锟斤拷锟�
		while ((n = in.read(bb)) != -1) {
//			System.out.println("10m");
			len += n;
//           out.Utils.write(bb, 0, n);// 写锟诫到锟斤拷锟斤拷锟�  
		}
		in.close();
		bb = new byte[len];
		in = new BufferedInputStream(new FileInputStream(file));

		in.read(bb);

//       out.close();// 锟截憋拷锟斤拷  
		in.close();

		res = new String(bb);

		InputStreamReader ipr;
		ipr = new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8"));

		char[] cbuf;
		cbuf = new char[1024 * 100];
		;
		len = 0;
		while ((n = ipr.read(cbuf)) != -1) {
//			System.out.println("10m");
			len += n;
//           out.Utils.write(bb, 0, n);// 写锟诫到锟斤拷锟斤拷锟�  
		}

		ipr.close();

		ipr = new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8"));
		cbuf = new char[len];
		ipr.read(cbuf);
		ipr.close();

		res = new String(cbuf);

		return res;
	}

	/**
	 * @param
	 * @return 锟斤拷锟斤拷页锟斤拷锟絊tring
	 */
	public static String getResFromWeb(String url) {
		

	     //创建一个Http请求对象
	     final HttpGet request = new HttpGet(url);
	     
	     
//	     new BasicHttpParams() {
////	    	BasicHttpParams(); 
//	    	 
//	    	 
//	     };
	     
	     
	     HttpParams params = new BasicHttpParams();
	     //设置连接超时或响应超时
	     //HttpConnectionParams.setConnectionTimeout(params, 3000);
	     //HttpConnectionParams.setSoTimeout(params, 5000);
	     //创建一个网络访问对象
	     final HttpClient httpClient = new DefaultHttpClient(params);

	     
//	     HttpHost proxy = new HttpHost("127.0.0.1",9666);
	        RequestConfig requestConfig = RequestConfig.custom()
//	                .setProxy(proxy)
	                .setConnectTimeout(10000)
	                .setSocketTimeout(10000)
	                .setConnectionRequestTimeout(10000)
//	                .settim
	                .build();
	        request.setConfig(requestConfig);
	        //设置请求头消息
//	        request.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
//	        urlCon.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	     FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
	         public String call() throws Exception {
	             try {
	                 //执行请求参数项
	                 HttpResponse response = httpClient.execute(request);
	                 //判断是否请求成功
	                 if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	                     //获取响应信息
	                     //noinspection UnnecessaryLocalVariable
	                     String content = getResponseString(response.getEntity());
	                     return content;
	                 } else {
	                     //网连接失败，使用Toast显示提示信息
//	                     Toast.makeText(context, "网络访问失败，请检查您机器的联网设备!", Toast.LENGTH_LONG).show();
	                 }
	             } catch (IOException e) {
	                 e.printStackTrace();
	             } finally {
	                 //释放网络连接资源
	                 httpClient.getConnectionManager().shutdown();
	             }
	             return null;
	         }
	     });
	     new Thread(task).start();
	     try {
	         return task.get();
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	     return null;
	 
	}

	
	public static String getUrl(String res, String urlstart, String urlend) {
		
		boolean write = false ,startParse = false;
		String url = "";
		
		
		
		
		for (int i = 0; i < res.length(); i++) {
			
			try {
				if (res.substring(i, i + "navigation-links__next".length()).equals("navigation-links__next")) {
					startParse=true;
				}
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
			
			if (startParse) {
				if (res.substring(i - "href=\"".length(), i).equals("href=\"")) {
					write=true;
				}
			}
			
			
			
			if (write) {
				
				if (res.charAt(i)=='"') {
					return url;
				}
				
				url+=res.charAt(i);
			}
			
			
		}
		
		return null;
		
		
	}
	
	
	
	
	public static LinkedList<String> getUrls(String res, String urlstart, String urlend) {

		boolean write = false;
		String ele = "";

		LinkedList<String> linkedList;
		linkedList = new LinkedList<>();

		for (int i = 0; i < res.length(); i++) {

			try {

				if (write) {
					if (res.substring(i, i + urlend.length()).equals(urlend)) {
						write = false;

						boolean has = false;
						for (String string : linkedList) {
							if (string.equals(ele)) {
								has = true;
								break;
							}
						}
						if (!has) {
							linkedList.add(ele);
						}
						ele = "";
					}
				}

				if (res.substring(i - urlstart.length(), i).equals(urlstart)) {
					write = true;
				}

				if (write) {
					ele += res.charAt(i);
				}

			} catch (Exception e) {
			}
		}

//		LinkedList<String> linkedList2;
//		linkedList2 = new LinkedList<>();
//		
//		for (String string : linkedList) {
//			if (!string.contains("#")) {
//				linkedList2.add(string);
//			}
//		}
//		linkedList=linkedList2;

		return linkedList;
	}

	 private static String getResponseString(HttpEntity entity){
		 
		    InputStream input = null;

			byte[] by;
			by = new byte[1024 * 1024 * 5];

			int len;
			byte[] tmps;
	        
	    
	    	LinkedList<Object> ls;
			int allen;
		 
	     String res = null;
	     
		try {
	         if ((entity.getContentEncoding() != null)
	                 && entity.getContentEncoding().getValue().contains("gzip")) {
					input = new GZIPInputStream(new ByteArrayInputStream(EntityUtils.toByteArray(entity)));;
	         } else {
	        	 input=entity.getContent();
	         }
	         
	    
			
			ls = new LinkedList<Object>();

			
			allen = 0;
			while ((len = input.read(by)) != -1) {
				allen += len;
				tmps = new byte[len];
				System.arraycopy(by, 0, tmps, 0, len);
				ls.add(tmps);

			}

			by = new byte[allen];
			int start = 0;
			for (Object object : ls) {
				tmps = (byte[]) object;
				System.arraycopy(tmps, 0, by, start, tmps.length);
				start = start + tmps.length;
			}
//	System.out.println(allen);


	res = new String(by, "UTF-8");
	         
	         input.close();
	       
	         
	     }catch (IOException e) {
	         e.printStackTrace();
	     }

	     return res;
	 }

	/**
	 * @param filename
	 * @return
	 * @throws Exception
	 * get url from file
	 */
	public static ArrayList<String> getWords(String filename) throws Exception {
		LinkedList<String> linkedList = new LinkedList<>();

		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = br.readLine()) != null) {
			linkedList.add(line);
		}

		br.close();

		ArrayList<String> arrayList = new ArrayList<>(linkedList);

		return arrayList;

	}

	public static String getArtile(String res, String resstart, String resend) {

//		res = Utils.getResFromWeb(res);
//		System.out.println(res);
//
//		if (true) {
//			return res;
//
//		}

		String des2 = "";

		boolean write = false;

		try {
			for (int i = 0; i < res.length(); i++) {

				try {

					if (write) {
//						TODO judge resend whether is included

						// inclusive
//						</article>
						if (res.substring(i-resend.length(),i).equals(resend)) {
							return des2;
						}

//						exclusive
//						if (res.substring(i, i + resend.length()).equals(resend)) {
//							return des2;
//						}

					}

					else {

//							<article class="article"
						if (res.substring(i, i + resstart.length()).equals(resstart)) {
							write = true;
						}

//						if (res.substring(i- resstart.length(), i ).equals(resstart)) {
//							write = true;
//						}

					}

					if (write) {

						des2 += res.charAt(i);
					}

				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			des2 = "";
		}

		return des2;

	}

	public static void combine(int amount, String baseDirectory) throws Exception {
//		Utils.write("E:\\ZT\\MDN\\"+ cata2+".html", des);
		String res = "";
//		TODO

		for (int i = 1; i < amount; i++) {
			res += Utils.getResFromFile(baseDirectory + i + ".html");
		}
//		Utils.write("E:\\ZT\\MDN\\"+ "all"+".html", res);
		Utils.write(baseDirectory + "all" + ".html", res);
		System.out.println("combine OVER ");
	}
}
