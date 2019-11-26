package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Filer {

	public static void main(String[] args) throws Exception {
//		System.out.println(11);
new Filer();
	}
	
	@SuppressWarnings("resource")
	public Filer() throws Exception {
		String res = "E:\\ZT\\test.txt";
		String des = "E:\\ZT\\test2.html";
		
		
		
		String h21 = "<h2>";
		String h22 = "</h2>";
		String enter1 = "<br>";
		String enter2 = "\r\n";
		String line = "";
		
		boolean write =false; 
		
		BufferedReader br = new BufferedReader(new FileReader(res));
		BufferedWriter bw = new BufferedWriter(new FileWriter(des));
		
		
		while ((line = br.readLine()) != null) {
			if (line.startsWith("@:")) {
				write=true;
				line =line.substring(2, line.length());
				line=line+enter1 ;
			}else if(line.startsWith("第")){
				write=true;
				
				line=h21+line+h22 ;
				
			}else{
				write =false;
			}
			if (write) {
				bw.write(line);
//				bw.newLine();
			}
		}
		
		br.close();
		bw.close();
		System.out.println("over");
		
	}

}
