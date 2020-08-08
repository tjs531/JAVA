package com.koreait.hs;
import java.util.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.koreait.hs.*;

public class Crawler2 {
	
	public static void main(String[] args) {
		String url = "https://comic.naver.com/webtoon/weekday.nhn";
		
		List<Cartoon> car_list = new ArrayList<Cartoon>();
		car_list = getCartoonList(url);
		//List<Cartoon> finish_car_list = getCartoonList("https://comic.naver.com/webtoon/finish.nhn");
		
		//System.out.println(car_list.get(0).getTitle());
		//for(Cartoon c : car_list) {
	//		System.out.println(c.getTitle().toString());
	//	}
	}
	
	
	
	public static List<Cartoon> getCartoonList(String url){
		List<String> list = gethref(url);
		
		List<Cartoon> cartoon_list = new ArrayList<Cartoon>();
		List<String> img = new ArrayList<String>();
		List<String> title = new ArrayList<String>();
		List<String> writer = new ArrayList<String>();
		List<String> story = new ArrayList<String>();
		List<String> genre = new ArrayList<String>();
		List<String> age = new ArrayList<String>();
		
		Cartoon car = new Cartoon();
		
		Connection conn = null;
		int idx = 0;
		
				
		System.out.println("test");
		for(String u : list) {
			//System.out.println(s);
			
			try {
				//System.out.println("test");
				conn = Jsoup.connect("https://comic.naver.com" + u);
				Document html = conn.get();
				
				Elements comicinfo = html.getElementsByClass("comicinfo");
				
				Elements img_tag = comicinfo.get(0).getElementsByTag("img");
				Elements detail = comicinfo.get(0).getElementsByClass("detail");
				
				//System.out.println("test");
				
				for(Element i : img_tag) {
					String im = i.toString().split(" ")[1].split("\"")[1];
					img.add(im);
				}
				
				for(Element t : detail) {
					String tit = t.getElementsByTag("h2").get(0).toString().split("<")[1].split("> ")[1];
					title.add(tit);
					System.out.println(idx++ + " " + tit);
				}
				
				for(Element w : detail) {
					String writ = w.getElementsByClass("wrt_nm").get(0).toString().split("> ")[1].split("<")[0];
					writer.add(writ);

				}
				
				for(Element s : detail) {
					String[] st = s.getElementsByTag("p").get(0).toString().split("<br>");
					String sto = "";
					for(int i=0; i<st.length;i++) {
						sto += st[i];
						
					}
					sto = sto.split(">")[1].split("<")[0];
					story.add(sto);
				}
				
				for(Element g : detail) {
					String ge = g.getElementsByClass("genre").get(0).toString().split(">")[1].split("<")[0];
					//System.out.println(ge);
					genre.add(ge);

				}
				
				for(Element a : detail) {
					String ag = a.getElementsByClass("age").get(0).toString().split(">")[1].split("<")[0];
					//System.out.println(ag);
					age.add(ag);
					
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		for(int i = 0; i<title.size(); i++){
			car.setImg(img.get(i).toString());
			car.setTitle(title.get(i).toString());

			//System.out.println(img.get(i).toString());
			if(writer.get(i).contains("/")) {

				car.setWri_story(writer.get(i).split("/")[0].toString());
				car.setWri_drawing(writer.get(i).split("/")[1].toString());
			}else {

				car.setWri_drawing(writer.get(i).toString());
			}
			car.setStory(story.get(i).toString());
			car.setGenre(genre.get(i).toString());
			car.setAge(age.get(i).toString());
			cartoon_list.add(car);
			
			//System.out.println(cartoon_list.get(i).getTitle());
		}
		return cartoon_list;
	}
	
	
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
