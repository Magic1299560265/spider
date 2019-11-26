package utils;

import java.io.File;
import java.util.LinkedList;


public class Mybatis {

	private String des;
	private int chapter;
	private int section;
	private String ad;
	private LinkedList<String> linkedList;

	public Mybatis() throws Exception {
		ad = "http://www.mybatis.org/mybatis-3/index.html";
		String base = "";
		des = "";
		linkedList = new LinkedList<String>();
		chapter = 1;
		section = 1;
		des += add(ad);
	
		base=ad.substring(0,ad.lastIndexOf("/")+1);
//		if (true) {
//			return;
//		}
		int test = 0;
		for (String string : linkedList) {
			des += add(base + "/" + string);
			System.out.println(string+"  "+(++test));
		}

		Utils.write("C:\\Users\\Administrator\\Desktop\\Mybatis\\Mybatis2.html", des);
		System.out.println("over.");

	}

	private String add(String ad) {
		
		int chapter3 = chapter++;
		
		String res = "";
		String des = "";
		String start = "<div id=\"bodyColumn\"";
		String add = "<div";
		String substract = "</div>";
		int count = 0;
		boolean close = false;
		boolean b = false;
		boolean write = false;
		
		String start2 = "<li><a href=\"";
		String add2 = "\"";
		String add3 = "<li class=\"nav-header";
		String value = "";
		boolean c = false;
		boolean close2 = false;
		boolean b2 = false;
		boolean write2 = false;
		boolean close3 = false;
		
		
		
		String chapter2 = "<h2>";
		String section2 = "<h3>";
		
		res = Utils.getResFromWeb(ad);

		for (int i = 0; i < res.length(); i++) { 
			
			
			if (!close3) {
				if (ad.equals(this.ad)) {
					close=true;
					if (b2) {
						if (res.substring(i, i + add2.length()).equals(add2)) {
							write2=false;
							b2=false;
							close2=false;
							c=true;
							linkedList.add(value);
							continue;
						}
					}
					
					if (c) {
						if (res.substring(i, i + add3.length()).equals(add3)) {
							close3=true;
							close=false;
							close2=true;
						}
					}
					
					if (!close2) {
						
						try {

							if (res.substring(i - start2.length(), i).equals(start2)) {
								write2=true;
								close2=true;
								b2=true;
								c=false;
								value="";
							}
							
						
						} catch (Exception e) {
						}
						
					}
					if (write2) {
						value+=res.charAt(i);
					}
					
				}
			}
			

			if (b) {
				if (res.substring(i, i + add.length()).equals(add)) {
					count++;

				} else if (res.substring(i - substract.length(), i).equals(substract)) {
					count--;
					if (count == 0) {
						return des;
					}
				}
			}

			if (!close) {
				if (res.substring(i, i + start.length()).equals(start)) {
					write = true;
					close = true;
					b = true;
					count++;
				}
			}

			
			boolean d = false;
			boolean f = false;
			if (write) {
				
				if (!d) {
					if (res.substring(i - chapter2.length(), i).equals(chapter2)) {
						des+="第"+chapter3+"章";
						des+=": ";
						
						section=1;
						d=true;
					}
				}
				
				if (res.substring(i - section2.length(), i).equals(section2)) {
					des+="第"+chapter3+"章";
					des+="第"+section+"节";
					des+=": ";
					section++;
					f=true;
				}
				
			}
			
			
			if (write) {
				des += res.charAt(i);
			}
		}

		return des;

	}

	public static void main(String[] args) throws Exception {
		System.out.println(111);
		new Mybatis();
		
		System.out.println("over...");
//		String string = "C:\\Users\\Administrator\\Desktop\\Mybatis";
//		new File(string).mkdirs();
		
	}

}
