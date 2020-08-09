package com.koreait.hs;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DaumWeb {
	
	public static void main(String[] args) {
		gethref();
	}
	
	
	public static List<String> gethref(){
		List<String> list = new ArrayList<String>();
		String[] day = {"mon", "tue", "wed", "thu", "fri", "sat", "sum"};
		String[] url = new String[7];
		
		for(int i=0; i<url.length; i++) {
			url[i] = "http://webtoon.daum.net/#day="+day[i] + "&tab=day" ;
		}
		
		try{
			//for(int i=0; i<url.length; i++) {				//링크   
				/*Connection conn = Jsoup.connect(url[0]);
				
				Document html = conn
					      .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
					      .referrer("http://www.google.com")
					      .get();*/
			
				Response response= Jsoup.connect(url[0])
			           .ignoreContentType(true)
			           .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")  
			           .referrer("http://www.google.com")   
			           .timeout(12000) 
			           .followRedirects(true)
			           .execute();

			Document html = response.parse();
				System.out.println(html.toString());
				//Elements list_area = html.select("li");
				//System.out.println(list_area.toString());
				//class=list_area 부분 Element집합
				//for(Element l : list_area) {
				//	String img = l.getElementsByTag("img").attr("src");
				//	String title = l.getElementsByClass("tit_wt").text();
					//System.out.println(img);
				//}
				
				//System.out.println(list_area.get(0).text());
				//Elements thumb = list_area.first().getElementsByClass("thumb"); //class=thumb 부분 Element 집합
				
				//for(Element t : thumb) {
				//	String href = t.getElementsByTag("a").attr("href");	//각 a태그 href 속성값을 리스트에 저장ㅇㅇㅇ  
				//	list.add(href);
				//}
			//}
		} catch(Exception e) {
		
		}
		return list;
	}
}