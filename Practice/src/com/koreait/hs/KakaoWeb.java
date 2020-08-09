package com.koreait.hs;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

public class KakaoWeb {
	
	
	public static void main(String[] args) {
		
		try {
		Response response= Jsoup.connect("https://page.kakao.com/main?categoryUid=10&subCategoryUid=1005")
		           .ignoreContentType(true)
		           .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")  
		           .referrer("http://www.naver.com")   
		           .timeout(120000) 
		           .followRedirects(true)
		           .execute();
	
		Document html = response.parse();
		System.out.println(html.html());
		}catch(Exception e) {
			
		}
	}
}
