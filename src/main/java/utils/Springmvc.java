package utils;

public class Springmvc {

	
	
	
	public static void main(String[] args) throws Exception {
		
		
		String file = "E:\\ZT\\baidudisk\\ENSMVC\\NOTE22.txt";
		String dfile = "E:\\ZT\\baidudisk\\ENSMVC\\NOTE222.txt";
		
		String res = "";
		String des = "";
		char n = '\n';
		char r= '\r';
		char cu= '\r';
		char[] head;
		char dot = '.';
		char space = ' ';
		String name = "";
		String name2[];
		
	
		
		
		head=new char[10];
		
//		isDigit(cu, head);
		
		for (int i = 1; i < 10; i++) {
			head[i-1]=String.valueOf(i).charAt(0);
		}
		for (char string : head) {
			System.out.println(string);
		}
		
		res=Utils.getResFromFile(file);
		System.out.println(res.length());
for (int i = 0; i < res.length(); i++) {
	char c = res.charAt(i);
	try {
		char charAt;
		charAt = res.charAt(i-1);
		
		if (charAt==r&&c==n) {
//			System.out.println("rn");
			char th = res.charAt(i+1);
			boolean b =false;
			
		b=isDigit(th, head);
			
			if (b) {
//				System.out.println("en.");
				if (res.charAt(i+2)==dot) {
					name=""	;
					for (int j = i+1; j < res.length(); j++) {
						char c2 = res.charAt(j);
						
						if (c2==space) {
							break;
						}else
							name+=c2;
					}
					System.out.println(name);
					if (name.charAt(name.length()-1)==dot) {
						 th = name.charAt(name.length()-2);
						 
						 
						if (isDigit(th, head)) {
							name2=name.split("\\.");
							
							
							name="";
							for (String string : name2) {
								name+=string;
							}
							
							name="第"+name+"章:";
							des+=c;
							des+=name;
							continue;
							
						}
					}
					
				}
			}
		}
	} catch (Exception e) {
	}
	des+=c;
}

Utils.write(dfile, des);
		
	}

	private static boolean isDigit(char cu, char[] head) {
		
		boolean b = false;
		for (char string : head) {
			if (string==cu) {
				b = true;
				return b;
			}
		}
		return b ;
	}

}
