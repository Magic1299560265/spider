package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

public class GetWords {

	private String pEnd = "</p>";
	private String wordparse1;
	private String wordparse2;
	private String pronparse1;
	private String pronparse2;
	private int catalog = 1;

	public void setCatalog(int catalog) {
		this.catalog = catalog;
	}

	private String cataParse;
	private String res;
	private String wordGroupParse;
	private String wordparse2end;

	
	public static void main(String[] args) throws Exception {
		GetWords getWords = new  GetWords();
		String deString="";
		ArrayList<String> words = Utils.getWords("C:\\Users\\Administrator\\Desktop\\communism.txt");
		for (int i = 0; i < words.size(); i++) {
			deString+=getWords.get(words.get(i));
		}
		
//		System.out.println(deString);
		Utils.write("C:\\\\Users\\\\Administrator\\\\Desktop\\\\communism2.html", deString);
	}

	/**
	 * @param word
	 * @return
	 * 锟斤拷取锟斤拷页锟斤拷实玫锟斤拷姆锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
	 */
	public String get(String word) {

		
		 boolean sen2Area = false;
		 boolean sen2Areaparse1tag = false;
		 boolean sen2Areaparse2tag = false;
		
		
		String ret = "<p class=\"addition\"";
		
		
		res = "";
		String des = "";

		int index = 0;
		char current = '0';
		boolean write = false;

		wordparse1 = "<h2 class=\"wordbook-js\">";
		wordparse2 = "</ul>";
		wordparse2end = "</div></div>";

		pronparse1 = "<a href=";
		pronparse2 = "></a>";
		
		boolean wordArea = false;
		boolean proArea = false;

		int count = 0;

		String sen1parse1 = "<div class=\"examples\">";
		String sen1parse2 = "</div>";
		String sen2parse1 = "<li>";// "<p><span id=\"tran_"
		String sen2parse2 = "<p class=\"example-via\">";

		 String sen2Areaparse1="<div id=\"bilingual";
		 String sen2Areaparse2="</ul>";

		
		
		
		boolean closeSent = false;
		boolean closeSentcntl = false;

		String closeSentendparse = "<a class=\"more-example\"";
		String storeSent = "";

		boolean isWordGroup = false;
		wordGroupParse = "<div id=\"wordGroup2\"";
		boolean wg1c = false, wg2s = false, wgcc = false;
		int wordGroupCount = 0;

		cataParse = "<span class=\"keyword\">";
		boolean cataParsestart = false;
		boolean senArea = false, isSen2 = false, banSen1 = false;

		boolean diamond = false;
		boolean diamond2 = false;
		char diml = '<';
		char dimr = '>';

//		String path = "http://dict.youdao.com/w/battery" + word;
		String path = "http://dict.youdao.com/w/" + word;

		res = Utils.getResFromWeb(path);

		boolean wordparse1close = false;
		boolean pronparse1start = false;

		boolean wordparse2tag = false;
		boolean pronparse2start = false;
		boolean sec1over = false;

		boolean tagall1 = false;
		boolean tagall12 = false;
		while (index < res.length()) {

			current = res.charAt(index);

			try {

				if (!sec1over) {
					if (pronparse2start) {
						proArea = test(pronparse2, index - 1, false);
						if (!proArea) {
							pronparse2start = false;
							pronparse1start = true;
							wordparse2tag = true;
						}
					}

					if (pronparse1start) {
						proArea = test(pronparse1, index, true);
						if (proArea) {
							wordparse2tag = false;
							pronparse1start = false;
							pronparse2start = true;
						}
					}

					if (cataParsestart) {
						cataParsestart = test2(cataParse, index - 1, false);
						if (!cataParsestart) {
							des += "第" + catalog + "章 :";
							catalog++;
						}

					}

					if (wordparse2tag) {
						wordArea = test2(wordparse2, index - 1, false);
						if (!wordArea) {
							des += wordparse2end;
							wordparse2tag = false;
							sec1over = true;
							
						}
					}

					if (!wordparse1close) {
						wordArea = test(wordparse1, index, true);

						if (wordArea) {

							wordparse1close = true;
							wordparse2tag = true;
							pronparse1start = true;
							cataParsestart = true;
						}
					}

					if (wordArea && !proArea) {
						write = true;
					} else {
						write = false;
					}

					if (sec1over) {
						diamond = false;
						diamond2 = false;
						closeSentcntl = true;
					}
				}

				if (sec1over) {

					if (!closeSent) {

						if (tagall12) {

							if (isSen2) {
								senArea = test(sen2parse2, index, false);
							} else {
								senArea = test2(sen1parse2, index - 1, false);

							}

							if (!senArea) {

								tagall12 = false;
								tagall1 = false;
								closeSentcntl = true;

								count++;
								if (count == 4) {
									closeSent = true;
								}
							}

						}

						if (!closeSent) {

							if (!tagall1) {
								if (banSen1) {

								} else {
									senArea = test(sen1parse1, index, true);
									if (senArea) {
										isSen2 = false;
									}
								}

								if (senArea) {

								} else {
									
//									senArea = test(sen2parse1, index, true);
//									if (senArea) {
//										isSen2 = true;
//										if (!banSen1) {
//											banSen1 = true;
//										}
//									}
									
									if (sen2Areaparse2tag) {
										sen2Area=test2(sen2Areaparse2, index-1, false);
										if (sen2Area==false) {
											closeSent=true;
										}
										
									}else {
										
									}
									
									
									
									if (sen2Areaparse1tag) {
										
									}else {
										sen2Area = test(sen2Areaparse1, index, true);
										if (sen2Area) {
//										isSen2 = true;
											sen2Areaparse1tag=true;
											sen2Areaparse2tag=true;
											
											
										if (!banSen1) {
											banSen1 = true;
										}
									}
									}
									
									
									
									if (sen2Area==true) {
										
										
										senArea = test(sen2parse1, index, true);
										if (senArea) {
										isSen2 = true;
									} else {

									}
									
								}
								

							}
								if (senArea) {
									tagall1 = true;
									tagall12 = true;
									closeSentcntl = false;

									diamond = false;
									diamond2 = false;

								}

							if (closeSentcntl) {
								closeSent = test(closeSentendparse, index, true);
							}

						}

						if (senArea) {

							if (isSen2) {

								if (diamond2) {
									if (res.charAt(index - 1) == dimr) {
										diamond = false;
										diamond2 = false;
									}
								}

								if (!diamond) {
									if (res.charAt(index) == diml
											&& !((res.substring(index, index + "<p".length()).equals("<p"))
													|| (res.substring(index, index + pEnd.length()).equals(pEnd)))) {
										diamond = true;
										diamond2 = true;
									}
								}
							} else {
								diamond = false;
							}

						}

						if (senArea && !diamond) {
//							write = true;
							storeSent+=current;
						} else {
							write = false;
						}

					

					}

				

					
				}
					if (!senArea) {

						if (!wgcc) {
							if (wg2s) {
								isWordGroup = test("</div>", index, false);
								if (isWordGroup) {
									isWordGroup = test("<div", index, false);
								}
								if (!isWordGroup) {
									wgcc = true;
									index=res.length()-1;
								}
							}

							if (wg1c ) {
								if (test2("</p>", index - 1, true)) {
									wordGroupCount++;
									if (wordGroupCount == 4) {
										wgcc = true;
										index=res.length()-1;
										isWordGroup = false;
									}
								}
							}

							if (!wg1c) {
								isWordGroup = test(wordGroupParse, index, true);
								if (isWordGroup) {
									
									closeSent=true;
									
									wg1c = true;
									wg2s = true;
									
									diamond = false;
									diamond2 = false;
									
								}
							}


						}

//		TODO

						if (isWordGroup) {

							if (diamond2) {
								if (res.charAt(index - 1) == dimr) {
									diamond = false;
									diamond2 = false;
								}
							}

							if (!diamond) {
								if (res.charAt(index) == diml
										&& !((res.substring(index, index + "<p".length()).equals("<p"))
												|| (res.substring(index, index + pEnd.length()).equals(pEnd)))) {
									diamond = true;
									diamond2 = true;
								}
							}

						} else {
							diamond = false;
						}

						if (isWordGroup && !diamond) {
							write = true;
						} else {
							write = false;
						}

						if (write!=true&&diamond==false) {
							if (test(ret, index, true)) {
								index=res.length()-1;
							}
						}
						
					}
				
				
				}	
			} catch (Exception e) {
			}

		
			
			if (write&&!senArea) {
				des += current;
			}

			index++;
			if (index==res.length()) {
				des+=storeSent;
			}

		}

		return des;
	}

	

	/**
	 * @param wordparse22
	 * @param index
	 * @param b
	 * @return
	 * 锟斤拷锟斤拷锟角癱har锟斤拷锟斤拷,锟斤拷锟斤拷指锟斤拷bool<锟接从猴拷锟斤拷前锟叫讹拷>
	 */
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
	 * @param string
	 * @param index
	 * @param b
	 * @return
	 * 锟斤拷锟斤拷锟角癱har锟斤拷锟斤拷,锟斤拷锟斤拷指锟斤拷bool<锟斤拷前锟斤拷锟斤拷锟叫讹拷>
	 */
	private boolean test(String string, int index, boolean b) {

		int in2 = 0;

		while (in2 < string.length()) {
			if (res.charAt(index + in2) != string.charAt(in2)) {
				return !b;
			}

			in2++;
		}

		return b;
	}

	

	
	/**
	 * @param string
	 * @return
	 * @throws Exception
	 * 锟斤拷取英锟侥撅拷锟斤拷
	 */
	public String get46(String string) throws Exception {

		String des = "";
		String line = "";
		String enter = "\r\n";
		char cu = '0';
		BufferedReader br;
		boolean have = false;
		int count = 0;

		br = new BufferedReader(new FileReader(string));
		while ((line = br.readLine()) != null) {
			have = false;
			for (int i = 0; i < line.length(); i++) {

				cu = line.charAt(i);
//				|| 

				if (i == 0) {
					if ((cu >= 65 && cu <= 90)) {
						continue;
					}
					if (cu == ' ' && line.length() <= 2) {
						continue;
					}
				}

				if ((cu >= 97 && cu <= 122)) {
					des += cu;
					have = true;
				} else {

//					if (i==0) {
//						break;
//					}
//					

					if (have) {
						des += enter;
						System.out.println(++count);

						break;
					}
				}

				if (i == line.length() - 1 && have == true) {
					des += enter;
					System.out.println(++count);
				}

			}

		}

		br.close();

		return des;
	}



}
