package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import jxl.Cell;
//import jxl.Sheet;
//import jxl.Workbook;

public class GetEnglish {

    /*
     * DealHtml deal = new DealHtml();
     *
     * String f2="F:\\dir\\object2.html"; String string = deal.get2(new
     * File("F:\\Java\\JavaTutorials\\tutorial\\java\\generics")); //
     * deal.get(file); System.out.println("oooooooooo"); deal.wrte(f2, string);
     */

    private int search = 1;

    private String des = "", cata, address;

    int BASE = 20000;
    String dirTemp = "";

    private String res;

    private Unit[] units;

    private File parent;

    private String indexh;

    private boolean hasLeft;

    private boolean isIndex = false;

    private char current;

    private boolean isH1;


    /**
     * @param file
     * @return
     * @throws Exception 得到一个html里的资源
     */
    public String get(String file) throws Exception {
        String des = "";
        int index = 0;
        boolean write = false;
        boolean voidImg = false;

        int len2;

        int posSearach = -1;

//		<div id="PageTitle">     <div class="NavBit">   <div class="NavBit">

//		<img src="../../figures/java/concepts-object.gif" width="345" height="210" align="bottom" alt="A circle with an inner circle filled with items, surrounded by gray wedges representing methods that allow access to the inner circle." />

        res = Utils.getResFromFile(file);

        boolean leftw = false;
        boolean over = false;


        while (index < res.length()) {

            char charAt = res.charAt(index);

            if (voidImg) {
                if (charAt == '>') {
                    voidImg = false;
                    index++;
                    continue;
                }
            }

            if (charAt == '<') {


                if (isIndex && !over) {
//					

                    if (!leftw) {

                        if (res.substring(index, index + "<div id=\"LeftBar\"".length())
                                .equals("<div id=\"LeftBar\"")) {

                            leftw = true;
                        }
                    } else {
                        if (res.substring(index, index + "<div id=\"MainFlow\"".length())
                                .equals("<div id=\"MainFlow\"")) {
                            leftw = !leftw;

                            over = true;
                        }

                    }


                }


                try {

                    if (!write) {
                        if (res.substring(index, index + "<div id=\"PageTitle\">".length())
                                .equals("<div id=\"PageTitle\">")) {
                            write = true;
                        }
                    } else {


                        if (res.substring(index, index + "<div id=\"PageContent\"".length())
                                .equals("<div id=\"PageContent\"")) {

                            if (isIndex) {
                                des += address;
                                des += cata;

                            }

                        } else if (res.substring(index, index + "<div class=\"NavBit\">".length())
                                .equals("<div class=\"NavBit\">")) {
//							write!=write;
                            break;
                        } else if (res.substring(index, index + "<img src=".length()).equals("<img src=")) {
//							write!=write;
                            voidImg = true;
                        } else if (posSearach == -1 && res.substring(index, index + "<h1>".length()).equals("<h1>")) {
                            posSearach = index;
                        }

                    }

                } catch (Exception e) {

                }

            }

            if (leftw) {
                cata += charAt;
            }


            if (write) {
                if (!voidImg) {

                    des += charAt;
                    if (posSearach != -1 && index == posSearach + "<h1>".length() - 1) {
                        des += "第" + search + "章: ";
                        search++;
                    }

                }
            }

            index++;
        }


        return des;
    }


    /**
     * new Thread() { public void run() {}; { File file = new
     * File("F:\\Java\\JavaTutorials\\tutorial"); File[] listFiles =
     * file.listFiles(); for (File ff : listFiles) { if (ff.isDirectory()) { String
     * res; // res = "F:\\Java\\JavaTutorials\\tutorial\\java\\generics";
     * res=ff.toString(); DealHtml deal = new DealHtml(); String des="F:\\dir";
     * String s22 = deal.get2(new File(res)); String[] split = res.split("\\\\");
     * deal.wrte(des+"\\\\"+split[split.length-1]+".html", s22);
     * System.out.println("oooooooooo"); } } }; }.start();
     *
     * @param f
     * @return
     * @throws Exception 将一个目录下的所有文件变成一个html,目录中有目录，则递归。
     */

    public String get2(File f) throws Exception {

        int index = 0;

        File[] fs = f.listFiles();

        while (index < 3) {

            for (File file : fs) {
                String string = file.toString();
                if (index == 0) {
                    if (string.endsWith("index.html")) {
                        cata = "";
                        isIndex = true;
                        hasLeft = false;
                        indexh = string;


//						des += get(string);
                        address = "<div>" + string + "</div>";
                        des += get(string);


                        isIndex = !isIndex;
                        if (!cata.equals("")) {
                            addLeft();
                            index++;
                        }


                        break;


                    }
                } else if (index == 1) {
                    if (string.endsWith(".html") && !string.endsWith("index.html")) {
                        des += get(string);
                    }
                } else {
                    if (file.isDirectory()) {
                        get2(file);
                    }
                }

            }

            index++;
        }

        return des;
    }

    /**
     * 将第一个文件中的子文件全部读取出来。
     */
    private void addLeft() {

        int start, end;
        String parse = "<a href=\"";
        for (int i = 0; i < cata.length(); i++) {
            if (cata.charAt(i) == parse.charAt(0)) {


                try {

                    for (int j = 0; j < parse.length(); j++) {
                        if (parse.charAt(j) != cata.charAt(i + j)) {
                            break;
                        }
                        if (j == parse.length() - 1) {
                            start = i + j + 1;

                            end = start + 1;
                            while (cata.charAt(end) != '"') end++;

//							
                            String string = new File(indexh).getParentFile().toString();
                            if (cata.substring(start, end).contains("/")) {
//								System.out.println(cata.substring(start, end));
                                break;
                            }
                            string += "\\" + cata.substring(start, end);
//							System.out.println(string);
                            try {
                                des += get(string);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                    }


                } catch (Exception e) {
                    // : handle exception
                }

            }
        }


    }


    public void change(String res2, final String des) throws Exception {

        res = Utils.getResFromFile(res2);
        dirTemp = des;

        units = getUnits();

        ExecutorService pl = Executors.newCachedThreadPool();

        for (int i = 0; i < units.length; i++) {

            final Unit unit = units[i];

            pl.execute(new Runnable() {

                public void run() {

                    unit.set();
                }
            });
        }

        System.out.println("end");

    }

    public void combine(String string2, String string) throws Exception {

        des = "";
        int index = 0;
//		

        dirTemp = string2;
        while (true) {

            if (!new File(dirTemp + "\\" + index).exists())
                break;

            des += Utils.getResFromFile(dirTemp + "\\" + index);

            index++;
        }

        Utils.write(string, des);

        System.out.println("end...");

    }


    Unit[] getUnits() {

        int len, size, remainder;
        Unit[] tmp;
        Unit u;

        size = res.length() / BASE;
        remainder = res.length() % BASE;
        len = remainder == 0 ? size : size + 1;

        tmp = new Unit[len];

        for (int i = 0; i < tmp.length; i++) {

            tmp[i] = new Unit(i, i * BASE, i == tmp.length - 1 ? tmp.length : i * BASE + BASE);
            ;

        }

        return tmp;

    }

    class Unit {
        int index;
        String des;
        int start, end;
        String path;

        public Unit(int i, int j, int k) {
            index = i;
            start = j;
            end = k;

        }

        void set() {
            des = "";

            while (start < end) {

                char charAt = res.charAt(start);
                if (charAt == '\r') {
                    char left, right;
                    try {

                        left = res.charAt(start - 1);
                        right = res.charAt(start + 2);

//						if ((left >= 65 && left <= 90) || (left >= 97 && left <= 122)) {
                        if ((left >= 97 && left <= 122)) {
//							if ((right >= 65 && right <= 90) || (right >= 97 && right <= 122)) {
                            if ((right >= 97 && right <= 122)) {
                                des += " ";
                                start += 2;
                                continue;
                            }
                        }

                    } catch (Exception e) {
                    }

                }

                des += charAt;
                start++;
            }

            try {
                Utils.write((path = dirTemp + "\\" + index), des);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            System.out.println(index + " over...");

        }

    }

    public void addPart(String string) throws Exception {
        File unit;

        ExecutorService pl = Executors.newCachedThreadPool();

        for (File tmp : new File(string).listFiles()) {
            final File deal = tmp;

            pl.execute(new Runnable() {

                public void run() {
                    String res = null, des;
                    char current;
                    int start, end;

                    String part = "Part III: C", chapter = "Chapter 18. S";
                    int resin, parsein;

                    try {
                        res = Utils.getResFromFile(deal.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    des = "";
                    for (int i = 0; i < res.length(); i++) {
                        current = res.charAt(i);

                        try {
                            boolean b;

                            if (current == part.charAt(0) && res.charAt(i - 1) == '\n' && res.charAt(i - 2) == '\r') {
                                b = false;

                                parsein = 1;
                                resin = i + 1;
                                while (parsein < part.indexOf("I")) {

                                    if (res.charAt(resin) != part.charAt(parsein)) {
                                        b = true;
                                        break;
                                    }

                                    parsein++;
                                    resin++;
                                }

                                if (!b) {
                                    start = resin;

                                    while (parsein < part.indexOf(":") + 1) {

                                        if (res.charAt(resin) == ':') {
                                            end = resin - 1;
                                            if (res.charAt(++resin) == ' ') {
                                                if (res.charAt(++resin) >= 65 && res.charAt(resin) <= 90) {
//													des+="锟斤拷"+start+end+"锟斤拷";

                                                    des += "锟斤拷";

                                                    while (start < end + 1) {
                                                        des += res.charAt(start);
                                                        start++;
                                                    }
                                                    des += "锟斤拷";

                                                    break;

                                                }
                                            }
                                        }

                                        parsein++;
                                        resin++;
                                    }
                                }

                            } else if (current == chapter.charAt(0) && res.charAt(i - 1) == '\n' && res.charAt(i - 2) == '\r') {

//								hapter="Chapter 18. S";

                                b = false;

                                parsein = 1;
                                resin = i + 1;
                                while (parsein < chapter.indexOf(" ") + 1) {

                                    if (res.charAt(resin) != chapter.charAt(parsein)) {
                                        b = true;
                                        break;
                                    }

                                    parsein++;
                                    resin++;
                                }

                                if (!b) {
                                    start = resin;

                                    while (parsein < chapter.indexOf(".") + 1) {

                                        if (res.charAt(resin) == '.') {
                                            end = resin - 1;
                                            if (res.charAt(++resin) == ' ') {
                                                if (res.charAt(++resin) >= 65 && res.charAt(resin) <= 90) {
//													des+="锟斤拷"+start+end+"锟斤拷";

                                                    des += "锟斤拷";

                                                    while (start < end + 1) {
                                                        des += res.charAt(start);
                                                        start++;
                                                    }
                                                    des += "锟斤拷";

                                                    break;

                                                }
                                            }
                                        }

                                        parsein++;
                                        resin++;
                                    }
                                }

                            }

                        } catch (Exception e) {
                        }

                        des += current;
                    }

                    try {
                        Utils.write(deal.toString(), des);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void setPara(String string) throws Exception {
        File unit;

        ExecutorService pl = Executors.newCachedThreadPool();

        for (File tmp : new File(string).listFiles()) {
            final File deal = tmp;

            pl.execute(new Runnable() {

                public void run() {
                    String res = null, des;
                    char current;
                    int start, end;

                    String part = "Part III: C", chapter = "Chapter 18. S";
                    int resin, parsein;

                    try {
                        res = Utils.getResFromFile(deal.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    des = "";
                    for (int i = 0; i < res.length(); i++) {
                        current = res.charAt(i);

                        try {

                            if ((current >= 65 && current <= 90) && res.charAt(i - 1) == '\n' && res.charAt(i - 2) == '\r') {
                                des += "  ";
                            }
                        } catch (Exception e) {
                            // : handle exception
                        }


                        des += current;
                    }

                    try {
                        Utils.write(deal.toString(), des);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public String getSenE(String path) throws Exception {
        String des = "";
        res = "";
        boolean write = false;
        boolean isSentence = false;
        boolean isEnter = false;
        String senE = "\r\n锟斤拷";
        isH1 = false;
        String senE2 = "\r\n.";

        res = Utils.getResFromFile(path);


        for (int i = 0; i < res.length(); i++) {

            current = res.charAt(i);

            try {


                if (isEnter) {
                    isEnter = false;
                    isSentence = false;

                } else {


                    if (!isSentence) {


                        if (i == 0) {
                            isSentence = true;
                            isH1 = true;
                            if (isH1) {
                                des += "<h1>";
//									isH1=false;
                            }
                        } else {

                            if (isSentence = test2(senE, i, true)) {
                                isH1 = true;


                                if (isH1) {
                                    des += "<h1>";
//										isH1=false;
                                }


                            } else {
                                isSentence = test2(senE2, i, true);
                            }


                            if (!isSentence) {
                                isSentence = testSenE(i);
                            }

                        }
                    } else {
                        if (current == '\n') {
                            isEnter = true;
                        } else if (current == '\r') {
                            if (isH1) {
                                des += "</h1>";
                                isH1 = false;
                            }


                            des += "</p>";
                        }
                    }


                }


            } catch (Exception e) {
            }


            if (isSentence) {
                write = true;
            } else
                write = false;


            if (write) {


                des += res.charAt(i);
            }

        }


        return des;
    }

    private boolean testSenE(int i) {


        try {
            if (((current >= 65 && current <= 90) || (current >= 97 && current <= 122)) && res.charAt(i - 1) == '\n' && res.charAt(i - 2) == '\r') {
                return true;
            }
        } catch (Exception e) {
        }


        return false;
    }

    private boolean test2(String wordparse22, int index, boolean b) {
        int in2 = wordparse22.length() - 1;

        while (in2 > -1) {
            if (res.charAt(index + in2 + 1 - wordparse22.length()) != wordparse22.charAt(in2)) {
                return !b;
            }

            in2--;
        }

        return b;
    }


    /**
     * 将一个目录下的所有目录变为单个html
     * 原目录 目标目录 html名字为目录名字
     */
    private void getHtml() {

//		String pathname = "F:\\Java\\JavaTutorials\\tutorial";
        String pathname = "F:\\FF";

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        File[] listFiles = new File(pathname).listFiles();
        for (File file : listFiles) {
            if (file.isDirectory()) {
                final File file2 = file;
                executorService.execute(new Runnable() {

                    public void run() {


                        try {


                            GetEnglish getEnglish = new GetEnglish();

                            String string = file2.toString();
                            String get2 = getEnglish.get2(new File(string));
                            try {
                                String[] split = string.split("\\\\");
                                System.out.println(split[split.length - 1]);
//								if (true) {
//									return;
//								}
                                String string2 = "F:\\Java\\JavaTutorials\\tutorialEnglish\\";
                                new File(string2).mkdirs();
                                System.out.println(string2);
                                Utils.write(string2 + split[split.length - 1] + ".html", get2);
                            } catch (Exception e) {
                                // : handle exception
                            }


                        } catch (Exception e) {
                            // : handle exception
                        }

                        System.out.println(file2.toString());
                    }
                });


            }
        }


    }

    /**
     * @throws Exception 将多个文件合并成一个文件。
     */
    private void combineWords(String res, String des) throws Exception {


        String string2 = "";

        File[] listFiles = new File(res).listFiles();
        for (File file : listFiles) {
            string2 += Utils.getResFromFile(file.toString());
        }

        Utils.write(des, string2);
        System.out.println("ovvv");
    }

    public static void main(String[] args) throws Exception {
//		new GetEnglish().combineWords("E:\\ZT\\html\\combine" , "E:\\ZT\\comb.html");
//		System.out.println("over.");
//		new GetEnglish(). explainWord();
        new GetEnglish().getHtml();
    }
    /**
     * @throws Exception
     *
     */
    /**
     * @throws Exception 将一个文件中的所有单词。查询解释并均匀的写入几个文件中。
     */
    void explainWord() throws Exception {


        int len;
        int index = 2, amount = 202;
        String content222;
        GetEnglish tt2;
        GetEnglish tt = null;
        String content;
        GetWords ws2;


//		if (true) {
//			return;
//		}

        ws2 = new GetWords();
        String des = "";

        ArrayList<String> words = Utils.getWords("C:\\Users\\Administrator\\Desktop\\communism.txt");
        LinkedList<String> linkedList = new LinkedList<>();
        for (String string : words) {
            if (string.length() >= 3) {
                linkedList.add(string);
            }
        }
        words = new ArrayList<>(linkedList);
        System.out.println(words.size());

        index = 0;
        int name = 0;
        while (true) {
            final int start = index;
            int end222 = index + 500;
            if (end222 > words.size()) {
                end222 = words.size();
            }

            final int end2222 = end222;
            final int name2 = name;


            Executor executorService = null;

            final ArrayList<String> words2 = words;

            executorService = Executors.newCachedThreadPool();
            executorService.execute(new Runnable() {

                public void run() {
                    GetWords getWords = new GetWords();
                    String wds = "";


                    int i = start;
                    int j2 = end2222;
                    getWords.setCatalog(i + 1);

                    for (int j = i; j < j2; j++) {
                        wds += getWords.get(words2.get(j));
                    }

                    try {

                        GetEnglish tt2 = new GetEnglish();
                        Utils.write("D:\\res\\" + name2, wds);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("over");
                }
            });

            index += 1000;
            if (index >= words.size()) {
                break;
            }
            name++;
        }
        System.out.println("eeeeeeee");
    }


    /**
     * @param index
     * @param amount
     * @return 将xls文件里面的所有单词都取出来。
     */
    private static LinkedList<String> getWds(int index, int amount) {

//		String string2 = "D:\\English\\1000\\�и�����������(������).xls";
//
//		jxl.Workbook readwb = null;
//		LinkedList<String> wds;
//		wds = new LinkedList<>();
//
//		try {
//			Workbook workbook = Workbook.getWorkbook(new FileInputStream(new File(string2)));
//			Sheet rs = workbook.getSheet(0);
//
//			int rsColumns = rs.getColumns();
//			System.out.println(rsColumns);
//			System.out.println(rs.getRows());
//
//
//			int reach = index + amount;
//			while (true) {
//				Cell c00 = rs.getCell(0, index);
//				String strc00 = c00.getContents();
//				wds.add(strc00);
//
//				index++;
//				if (index == reach) {
//					break;
//				}
//
//			}
//
//			workbook.close();
//
//		} catch (Exception e) {
//			// : handle exception
//		}
//
//		return wds;
        return null;
    }


}
