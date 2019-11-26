package zt;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpClientHelper {
    private static String DEFAULT_ENCODE="UTF-8";//默认字符编码
 
// public static String getContentFromUrl(String url) throws HttpClientException {
// public static String getContentFromUrl(String url) throws Exception {
//  String contentStr="";
//  // Create an instance of HttpClient.
//  HttpClient client = new HttpClient();
//  // Create a method instance.
//  GetMethod method = new GetMethod(url);
//  // Provide custom retry handler is necessary
//  method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
//    new DefaultHttpMethodRetryHandler(3, false));
//  try {
//   // Execute the method.
//   int statusCode = client.executeMethod(method);
//   if (statusCode != HttpStatus.SC_OK) {
//    System.err.println("Method failed: " + method.getStatusLine());
//   }
//   // Read the response body.
//   byte[] responseBody = method.getResponseBody();   
//   // Deal with the response.
//   // Use caution: ensure correct character encoding and is not binary  data
//   contentStr=new String(responseBody,"utf-8");   
//
//  } catch (HttpException e) {
//   System.err.println("Fatal protocol violation: " + e.getMessage());
////   throw new HttpClientException();
//  } 
////  catch (IOException e) {
////   System.err.println("Fatal transport error: " + e.getMessage());
////   throw new HttpClientException();
////  } 
//  finally {
//   // Release the connection.
//   method.releaseConnection();
//  }
//  System.out.println(contentStr);
//  return contentStr;
// }
 
//   public static String postContentToUrl(String content,String url) throws HttpClientException
//   {
//    String contentStr="";
//    HttpClient client = new HttpClient();
//       PostMethod postMethod = new PostMethod(url);       
//       client.setConnectionTimeout(30000);             
//       try
//       {        
//        postMethod.setRequestBody(content);       
//       int statusCode= client.executeMethod(postMethod);       
//       if (statusCode != HttpStatus.SC_OK) {
//   System.err.println("Method failed: " + postMethod.getStatusLine());
//  }
//  byte[] responseBody = postMethod.getResponseBody();   
//  contentStr=new String(responseBody);
//   } catch (HttpException e) {
//  System.err.println("Fatal protocol violation: " + e.getMessage());
//  throw new HttpClientException();
// } catch (IOException e) {
//  System.err.println("Fatal transport error: " + e.getMessage());
//  throw new HttpClientException();
// } finally {
//  // Release the connection.
//  postMethod.releaseConnection();
// }    
//  System.out.println(contentStr);      
//    return contentStr;
//   }
   
  
//   public static String postXmlFileToUrl(String filePath,String url) throws HttpClientException
//   {
//    String contentStr="";
//    HttpClient client = new HttpClient();
//       PostMethod postMethod = new PostMethod(url);       
//       client.setConnectionTimeout(30000);       
//       // Send any XML file as the body of the POST request
//       try
//       {
//       File f = new File(filePath);
////       System.out.println("File Length = " + f.length());
//       RequestEntity entity = new InputStreamRequestEntity(new FileInputStream(f), "text/xml; charset=utf-8");
//       postMethod.setRequestEntity(entity);     
//       int statusCode= client.executeMethod(postMethod);       
//       if (statusCode != HttpStatus.SC_OK) {
//   System.err.println("Method failed: " + postMethod.getStatusLine());
//  }
//  byte[] responseBody = postMethod.getResponseBody();   
//  contentStr=new String(responseBody);
//   } catch (HttpException e) {
//  System.err.println("Fatal protocol violation: " + e.getMessage());
//
// } catch (IOException e) {
//  System.err.println("Fatal transport error: " + e.getMessage());  
//  throw new HttpClientException();
// } finally {
//  // Release the connection.
//  postMethod.releaseConnection();
// }           
//    return contentStr;
//    
//   }
  
//   public static String postFormContentToUrl(String[][] nameValuePair,String url,String encoding) throws HttpClientException
//   {
//    String contentStr="";
//    HttpClient client = new HttpClient();
//       PostMethod postMethod = new PostMethod(url);        
//       client.setConnectionTimeout(30000);       
//       // Send any XML file as the body of the POST request
//       try
//       {
//       if(nameValuePair!=null)
//       {
//        NameValuePair[] nvps=new NameValuePair[nameValuePair.length];
//        for(int i=0;i<nameValuePair.length;i++)
//        {              
//         nvps[i]=new NameValuePair(nameValuePair[i][0],nameValuePair[i][1]);          
//        }        
//        postMethod.setRequestBody(nvps); 
//       }       
//       int statusCode= client.executeMethod(postMethod);       
//       if (statusCode != HttpStatus.SC_OK) {
//   System.err.println("Method failed: " + postMethod.getStatusLine());
//  }
//  byte[] responseBody = postMethod.getResponseBody();   
//  contentStr=new String(responseBody,encoding);
//   } catch (HttpException e) {
//  System.err.println("Fatal protocol violation: " + e.getMessage()); 
//  throw new HttpClientException();
// } catch (IOException e) {
//  System.err.println("Fatal transport error: " + e.getMessage());  
//  throw new HttpClientException();
// } finally {
//  // Release the connection.
//  postMethod.releaseConnection();
// }           
//    return contentStr;
//   }
//   public static String postFormContentToUrl(String[][] nameValuePair,String url) throws HttpClientException{
//    return postFormContentToUrl(nameValuePair,url,DEFAULT_ENCODE);
//   }
 
 public static void main(String[] args) {
     try {
//		HttpClientHelper.getContentFromUrl("http://www.xinhuanet.com");
//		HttpClientHelper.getContentFromUrl("http://tutorials.jenkov.com/java-nio/datagram-channel.html");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//    HttpClientHelper.postContentToUrl("hello","http://localhost:8181/payment");
 }
 
 
 @SuppressWarnings("deprecation")
 private String getResponseString(HttpEntity entity){
	 
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
System.out.println(allen);


res = new String(by, "UTF-8");
         
         input.close();
       
         
     }catch (IOException e) {
         e.printStackTrace();
     }

     return res;

 }
 

}