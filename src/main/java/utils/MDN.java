package utils;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MDN {
	/*
	 * C++
	 * <h3><b><a href="/doc/tutorial/">C++ Language
<div id="I_subnav"></div>
 href="
 "
 <h1>
 <table class="C_docPrevNext">
 http://www.cplusplus.com
 E:\ZT\Web笔记\English\C++


	*/

	private String des;
//	private String baseDirectory= "E:\\ZT\\Bootrap\\";
//	private String baseDirectory = "E:\\ZT\\docs\\NIOjenkov\\";
//	private String baseDirectory = "E:\\ZT\\docs\\JavaAPI\\Package java.lang\\";
//	private String baseDirectory = "E:\\ZT\\docs\\JavaAPI\\Package java.nio\\";
//	private String baseDirectory = "E:\\ZT\\docs\\JavaAPI\\Package java.util.concurrent\\";
	private String baseDirectory = "E:\\ZT\\Web笔记\\English\\C++\\";
//	private String baseDirectory = "E:\\ZT\\docs\\JavaAPI\\Package java.net\\";
//	private String baseDirectory= "E:\\ZT\\docs\\The Python Standard Library\\";
//	private String baseDirectory= "E:\\ZT\\docs\\Python\\";
//	E:\\ZT\\Bootrap\\
//	private String  baseUrl = "https://getbootstrap.com";
//	private String  baseUrl = "https://docs.python.org/3.7/tutorial/";
//	private String baseUrl = "https://docs.oracle.com/javase/8/docs/api/";
	private String baseUrl = "http://www.cplusplus.com";
//	private String baseUrl = "https://www.jetbrains.com";
//	private String baseUrl = "http://tutorials.jenkov.com";

//	private String  baseUrl = "https://docs.python.org/3.7/library/";
//private String resFileile = "bs.txt";
	private String resFileile = "Meet IntelliJ IDEA.txt";
	private String urlstart;
	private String urlend;
	private String resstart;
	private String resend;
	{
		urlstart = "href=\"";
		urlend = "\"";

//	resstart = "<main";
//	resend = "</main>";
//		resstart = "<div id=\"mainBody\">";
//		resend = " <div id=\"next";
//		resstart = "<div class=\"header\"";
//		resend = "<div class=\"bottomNav\"><a name=\"navbar.bottom\">";
		resstart = "<h1>";
		resend = "<table class=\"C_docPrevNext\">";
//		resstart = "<h1 data-toc=\"";
//		resend = "<div class=\"last-modified\"";
//	resstart = "<div class=\"documentwrapper\">";
//	resend = "<div class=\"sphinxsidebar\"";
	}

	public static void main(String[] args) throws Exception {

	String artile =  Utils.getResFromWeb("http://tutorials.jenkov.com/java-nio/datagram-channel.html");
	System.out.println(artile);
	
	
		;// new MDN(3).getArtile("http://tutorials.jenkov.com/java-nio/datagram-channel.html");
		
		
//		new MDN();
//		MDN mdn = new MDN(6);
//		mdn.combine(6);
		
//		System.out.println(Utils.getResFromWeb("https://developer.android.com/guide/components/activities/intro-activities"));

//		String resFromWeb = Utils.getResFromWeb("https://www.jetbrains.com/help/idea/install-and-set-up-product.html");
//		System.out.println(resFromWeb);
//		new MDN();
//		String artile = new MDN(3).getArtile("http://getbootstrap.com/docs/4.1/getting-started/theming/");
//		
//		String artile = new MDN(3).getArtile("https://howtodoinjava.com/java7/nio/java-nio-2-0-working-with-buffers/");
//		System.out.println(artile);
//		String absolutePath = new File("sfsf").getAbsolutePath();
//		System.out.println(absolutePath);
//		Utils.write(absolutePath, artile);

//System.out.println(111);
//		new Date();

//		System.out.println("12345".substring(1, 3));
//		
//		System.out.println("xx#ddd".contains("#"));

	}



	public MDN() throws Exception {

//		
//		System.out.println(Utils.getResFromWeb("http://www.cplusplus.com/doc/tutorial/introduction/"));
//		
//		if (true) {
//		return;
//	}
		
		LinkedList<String> urls;
		
		 String res = Utils.getResFromWeb("http://www.cplusplus.com/doc/tutorial/introduction/");
			res = Utils.getArtile(res, "<h3><b><a href=\"/doc/tutorial/\">C++ Language", "<div id=\"I_subnav\"></div>");

			urls = Utils.getUrls(res, "href=\"", "\"");
			
			

			LinkedList<String> linkedList2 = new LinkedList<>();
			for (String string : urls) {

				linkedList2.add(baseUrl + string);
			}

			urls = linkedList2;
		
		
//		LinkedList<String> urls = geturlFjdk();
		
		
//		String baseUrl = "https://www.jetbrains.com/help/idea/2018.2/";
//		String target = "meet-intellij-idea.html";
//
//		urls=new LinkedList<>();
//		urls.add(baseUrl+target);
	
		
		
//		while (true)
//
//		{
//			target = getArtile2(Utils.getResFromWeb(baseUrl + target), "navigation-links__next\" href=\"", "\"");
//			System.out.println(target);
//			if (!target.equals("")) {
//				urls.add(baseUrl+target);
//			}else {
//				break;
//			}
//		}
//		
////		System.out.println("ov");
////		if (true) {
////			return;
////		}
//
//
//
////		urls=
////		urls = Utils.getUrls(Utils.getResFromFile(baseDirectory + resFileile), urlstart, urlend);
////
////		System.out.println(urls.size());
//
////		urls.add
//		for (String string : urls) {
//			System.out.println(string);
//		}
//
////		
//
//		if (true) {
//			return;
//		}

//		res = Utils.getResFromFile(baseDirectory + resFileile);
//
//		LinkedList<String> linkedList;
//
//		linkedList = Utils.getUrls(res, urlstart, urlend);
//
////		String res2 = baseUrl + linkedList.get(j);
//
//		linkedList = FormUrl(linkedList);
//
//		System.out.println(linkedList.size());

//		if (true) {
//			System.out.println(linkedList.size());
//			for (String string : linkedList) {
//				System.out.println(string);
//			}
//			return;
//		}

//		ExecutorService threads = Executors.newFixedThreadPool(21);
		ExecutorService threads = Executors.newFixedThreadPool(5);
		
		
		ThreadPoolExecutor exec2 = (ThreadPoolExecutor) threads;
		exec2.setKeepAliveTime(10, TimeUnit.SECONDS);
		exec2.allowCoreThreadTimeOut(true);
		
//		int period=linkedList.size()/8;
		int period = 5; // five inputs is a runnable task .
		int end = 0;
		int start = 0;
		int cata = 0;

		while (true) {

			// TODO
//			index2+=linkedList.size()/4;
//			index2+=linkedList.size()/6;
			end += period;
			end = end <= urls.size() ? end : urls.size();

			new Runnable(){



				@Override
				public void run() {

				}
			};

			threads.execute(new Runnable(/* index,end2 */) {
				int start, end;
				private LinkedList<String> linkedList;
				int cata;

				@Override
				public void run() {
					String des = "";
					for (int j = start; j < end; j++) {

//						String res2 = baseUrl + linkedList.get(j);
						String[] split = linkedList.get(j).split("/");
						String title = split[split.length - 1];
						des += "<h2>第" + (j + 1) + "章： " + title + "</h2>";
//						System.out.println(j);
						des += Utils.getArtile(Utils.getResFromWeb(linkedList.get(j)), resstart, resend);
					}

					try {
//						TODO
						Utils.write(baseDirectory + cata + ".html", des);
					} catch (Exception e) {
						e.printStackTrace();
					}

					System.out.println("over  " + cata);
				}

				public Runnable setValue(int start2, int end2, LinkedList<String> linkedList, int cata) {
					start = start2;
					end = end2;
					this.linkedList = linkedList;
					this.cata = cata;
					return this;
				}
			}.setValue(start, end, urls, cata));

			start += period;

			if (end == urls.size()) {
				break;
			}
			cata++;

//			index+=
		}

	}

	private LinkedList<String> geturlFjdk() {
		String res, temp;
		LinkedList<String> urls;
		urls = null;

		LinkedList<String> all, all2;
		all = new LinkedList<>();
		all.add("https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html");
//		all.add("https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html");
//		all.add("https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/package-summary.html");
//		all.add("https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/package-summary.html");

//		all.add("https://docs.oracle.com/javase/8/docs/api/java/nio/charset/package-summary.html");
//		all.add("https://docs.oracle.com/javase/8/docs/api/java/nio/charset/spi/package-summary.html");
//		all.add("https://docs.oracle.com/javase/8/docs/api/java/nio/file/package-summary.html");
//		all.add("https://docs.oracle.com/javase/8/docs/api/java/nio/file/attribute/package-summary.html");
//		all.add("https://docs.oracle.com/javase/8/docs/api/java/nio/file/spi/package-summary.html");

		all2 = new LinkedList<>();

		for (String string : all) {
//System.out.println(string);
//			temp = res = Utils.getResFromWeb("https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html");
			String path = string;//
			// "https://docs.oracle.com/javase/8/docs/api/java/lang/package-summary.html";
			
//			temp = res = Utils.getResFromWeb("https://docs.oracle.com/javase/8/docs/api/java/net/package-summary.html");
			;

//			Utils.write(baseDirectory + 0 + ".html",
//					getArtile(res, "<div class=\"header\"", "<div class=\"bottomNav\"><a name=\"navbar.bottom\">"));
			temp = res = Utils.getResFromWeb(path);
			res = Utils.getArtile(res, "<a href=\"#package.description\">Description</a>", "<span>Exception Summary</span>");

			urls = Utils.getUrls(res, "<td class=\"colFirst\"><a href=\"", "\"");
//			for (String string : urls) {
//				System.out.println(string);
//			}
			urls = FormUrl(urls);

			urls.add(0, path);
//			System.out.println(urls.size());
			all2.addAll(urls);
			System.out.println(all2.size() + "!!");
		}
		return all2;
	}

	private LinkedList<String> FormUrl(LinkedList<String> linkedList) {
		LinkedList<String> linkedList2 = new LinkedList<>();
		for (String string : linkedList) {

			String string3;
//			string3 = string.split("../../")[1];
			while (true) {

				if (string.contains("../")) {
					string = string.substring(3, string.length());
				} else {
					break;
				}
			}

			string = baseUrl + string;
			linkedList2.add(string);
		}

		linkedList = linkedList2;
		return linkedList;
	}

	public MDN(int i) {

	}


	private String getArtile2(String res, String resstart, String resend) {
		
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
//						if (res.substring(i-resend.length(),i).equals(resend)) {
//							return des2;
//						}
						
//						exclusive
						if (res.substring(i, i + resend.length()).equals(resend)) {
							return des2;
						}
						
					}
					
					else {
//						if (res.substring(i, i + resstart.length()).equals(resstart)) {
//							write = true;
//						}
						
						if (res.substring(i- resstart.length(), i ).equals(resstart)) {
							write = true;
						}
						
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
	
	
}
