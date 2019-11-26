package test;

import utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class IdeHelp {
    String base = "https://www.jetbrains.com/help/idea/2018.2/";
    String[] thread = {"meet-intellij-idea.html", "working-with-source-code.html",

            "", "", "",};
//	String 


    ArrayList<String> urls;

    ExecutorService threads;
    private static String baseDirc = "E:\\ZT\\idehelp\\";
    ;

    // 20*5  100  1310


    public IdeHelp() {


        try {
//            urls = Utils.getWords("E:\\ZT\\idehelp2.txt");
            urls = Utils.getWords("E:\\ZT\\idehelp.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        threads = Executors.newFixedThreadPool(5);
        ((ThreadPoolExecutor) threads).setKeepAliveTime(10, TimeUnit.SECONDS);
        ((ThreadPoolExecutor) threads).allowCoreThreadTimeOut(true);

        int index = 0;
//        final int num = 2;
        final int num = 20;
        for (int i = 0; i < urls.size(); i += num) {
            index++;

            index=38;
            i=740;

            threads.execute(new Runnable() {
                private String file = "";
                private int i;
                private int index;

                public Runnable set(int i, int index) {
                    this.i = i;
                    this.index = index;
                    return this;
                }

                @Override
                public void run() {
                    for (int j = i; j < i + num; j++) {

                        if (j == urls.size()) break;
                        String url = null;
                        try {

                            url = urls.get(j);
                            String res = Utils.getResFromWeb(base + url);
                            res = Utils.getArtile(res, "<article", "</article>");
                            file += "<h1>" + url + "</h1>";
                            file += res;
                        } catch (Exception e) {
                            System.out.println(url);
                            e.printStackTrace();

                        }


                    }

                    try {
                        Utils.write(baseDirc + index + ".html", file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println("over:" + index);

                }
            }.set(i, index));

       i=8000;

        }


    }

//
//    ThreadPoolExecutor exec2 = (ThreadPoolExecutor) threads;
//		exec2.setKeepAliveTime(10, TimeUnit.SECONDS);
//		exec2.allowCoreThreadTimeOut(true);


    public static void main(String[] args) throws Exception {
        System.out.println(2311);
//        new IdeHelp();

//        Utils.combine(67,baseDirc);
    }

    public void setThread(String[] thread) {
        String base, init, url = null, end = null;//		base = 1;

        base = "https://www.jetbrains.com/help/idea/2018.2/";

        init = "meet-intellij-idea.html";

        init = "extract-constant.html";
        init = "project-module-dependencies-diagram.html";
        init = "run-debug-configuration.html";
        init = "running-applications.html";
        init = "configuring-testing-libraries.html";
        init = "dependencies-analysis.html";
        init = "dsm-analysis.html";
        init = "settings-code-style-json.html";
        init = "apply-ejb-3-0-style.html";
        init = "ejb-editor-general-tab-common.html";
        init = "diagram-toolbar-and-context-menu.html";
        init = "symbols.html";


//		end = "working-with-source-code.html";


        System.out.println(init);
        url = Utils.getUrl(Utils.getResFromWeb(base + init), null, null);
        System.out.println(url);
        for (; ; ) {

            if (url == null) {
                System.out.println(url);
                String s = null;
                s.toString();
                break;
            }

            url = Utils.getUrl(Utils.getResFromWeb(base + url), null, null);
            System.out.println(url);


//			if (url.equals(end)) {
//				break;
//			}
        }

        if (true) {
            return;
        }

    }
}
