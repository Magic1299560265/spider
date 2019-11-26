package test;

import java.nio.charset.Charset;

import utils.Utils;

public class testtt {
	public static void main(String[] args) {

//		String string = "123456";
//		
//		System.out.println(string.substring(1, 3));


		String resFromWeb;
//		resFromWeb = Utils.getResFromWeb("http://www.docjar.com/html/api/sun/net/www/protocol/http/HttpURLConnection.java.html");
//		resFromWeb = Utils.getResFromWeb("http://tutorials.jenkov.com/java-nio/datagram-channel.html");
		resFromWeb = Utils.getResFromWeb("http://localhost:8080/servlet3/jenkov.html");
		System.out.println(resFromWeb);

		Charset charset;

	}

}
