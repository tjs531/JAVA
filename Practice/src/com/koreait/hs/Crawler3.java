package com.koreait.hs;
import java.io.IOException;
import java.util.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler3 {
	
	public static void main(String[] args) {
	//	List<Cartoon> car_list = new ArrayList<Cartoon>();
	//	car_list = getCartoonList("https://comic.naver.com/webtoon/weekday.nhn");
		//List<Cartoon> finish_car_list = getCartoonList("https://comic.naver.com/webtoon/finish.nhn");
		
		//System.out.println(car_list.get(0).getTitle());
	//	System.out.println("dfs");
		//for(Cartoon c : car_list) {
	//		System.out.println(c.getTitle().toString());
	//	}
	//}
	
	
	
	//public static List<Cartoon> getCartoonList(String url){
		List<String> list = gethref("https://comic.naver.com/webtoon/weekday.nhn");
		
		List<Cartoon> cartoon_list = new ArrayList<Cartoon>();
		
		Cartoon car = new Cartoon();
		
		Connection conn = null;
		
		int idx=0;
				
		for(String u : list) {
			
			conn = Jsoup.connect("https://comic.naver.com" + u);
			Document html;
			Elements comicinfo;
			Elements img_tag;
			Elements detail;
			try {
				html = conn.get();
				comicinfo = html.getElementsByClass("comicinfo");
				img_tag = comicinfo.get(0).getElementsByTag("img");
				//System.out.println(img_tag.toString());
				detail = comicinfo.get(0).getElementsByClass("detail");
				
				//for(int i=0; i<img_tag.size(); i++) {
					car.setImg(img_tag.get(0).toString().split(" ")[1].split("\"")[1]);
					//System.out.println("실제값 : " + idx + " " + img_tag.get(0).toString().split(" ")[1].split("\"")[1]);
					//System.out.println("객체값: " + idx++ + " " + car.getImg());
					
					car.setTitle(detail.get(0).getElementsByTag("h2").get(0).toString().split("<")[1].split("> ")[1]);
					//System.out.println(detail.get(i).getElementsByTag("h2").get(0).toString().split("<")[1].split("> ")[1]);
					String writ = detail.get(0).getElementsByClass("wrt_nm").get(0).toString().split("> ")[1].split("<")[0];
					if(writ.contains("/")) {
						car.setWri_story(writ.split("/")[0]);
						car.setWri_drawing(writ.split("/")[1]);
					}else {
						car.setWri_story(writ);
						car.setWri_drawing(writ);
					}
					String[] st = detail.get(0).getElementsByTag("p").get(0).toString().split("<br>");
					String sto = "";
					for(int z=0; z<st.length;z++) {
						sto += st[z];
						
					}
					sto = sto.split(">")[1].split("<")[0];
					car.setStory(sto);
					
					car.setGenre(detail.get(0).getElementsByClass("genre").get(0).toString().split(">")[1].split("<")[0]);
					
					cartoon_list.add(car);
					
				//}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
		
		System.out.println();
		for(Cartoon c : cartoon_list) {
			System.out.println(c.getTitle());
			System.out.println(c.getWri_story());
			System.out.println(c.getWri_drawing());
			System.out.println(c.getStory());
		}
			
	}
		
		//return cartoon_list;
//	}
	
	
	public static List<String> gethref(String u){
		
		String url = u;
		List<String> list = new ArrayList<String>();
		
		try{
			Connection conn = Jsoup.connect(url);	
			
			Document html = conn.get();
			
			Elements list_area = html.getElementsByClass("list_area");	//class=list_area 부분 Element집합
			
			Elements col = list_area.get(0).getElementsByClass("col "); //class=col 부분 Element 집합
			
			for(Element c : col) {									
				Elements day = c.getElementsByClass("title");		//각 col에서 class=title을 찾아 나눔ㅁ
				for(Element cartton : day) {
					String href = cartton.attr("href").toString();	//각 title의 href주소뽑아서 리스트에 저장ㅇㅇㅇ  
					list.add(href);
				}
			}
			
			
		}catch(Exception e) {
		
		}
		return list;
	}
	
}
