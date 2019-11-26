package test.c;

import java.util.ArrayList;

public class testtt {

	private static ArrayList<String> words;

	public static void main(String[] args) throws Exception {

		String reg = "../";
String string = "../../../java/util/PropertyPermission.html";
System.out.println(string.contains(reg));
System.out.println(string.substring(3, string.length()));
String[] split = string.split(reg);
System.out.println(split.length);
//System.out.println(split[0]);
//for (String string2 : split) {
//	System.out.println(string2);
//}

	}
}

