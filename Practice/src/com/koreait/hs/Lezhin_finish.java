import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Lezin {
	public static void main(String[] args) throws UnsupportedEncodingException, IOException{
			
		//레진코믹스 연재작
		URL url = new URL("https://www.lezhin.com/api/v2/inventory_groups/home_scheduled_k?platform=web&store=web&_=1597027210408");
		
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		huc.setRequestMethod("GET");
		huc.setRequestProperty("x-lz-locale", "ko-KR");
		huc.connect();
		
		InputStreamReader isr = new InputStreamReader(huc.getInputStream(), "UTF-8");
		JSONObject object = (JSONObject)JSONValue.parse(isr);
		
		ArrayList<Cartoon> list = new ArrayList<Cartoon>();
		
		
		JSONObject gata = (JSONObject) object.get("data");

		JSONArray bodyArray = (JSONArray) gata.get("inventoryList");
		
		//1: 일 ~ 7: 토 , 8: 완결
		for(int i = 1 ; i < bodyArray.size(); i++){
			
			JSONArray items = (JSONArray)(((JSONObject)bodyArray.get(i)).get("items"));
  
			//요일마다 작품
			for(int z =0; z<items.size(); z++) {
				JSONObject data = (JSONObject) items.get(z);

				Cartoon car = new Cartoon();
				car.setTitle(data.get("title").toString());
				
				JSONArray authors = (JSONArray) data.get("authors");

				//글 작가 이름
				String au_name = ((JSONObject)authors.get(0)).get("name").toString();
				
				//글 작가 저장
				car.setWri_story(au_name);
				
				try {
					//그림작가 이름
					String dra_name = ((JSONObject)authors.get(1)).get("name").toString();
					if(dra_name.equals("레진코믹스")) {
						car.setWri_drawing(au_name);
					}else {
						car.setWri_drawing(dra_name);
					}
				}catch(Exception e){
					car.setWri_drawing(au_name);
				}
				
				//장르
				JSONArray genres = (JSONArray)data.get("tags");
				
				for(int g=0; g<genres.size(); g++) {
					String n = genres.get(g).toString();
					car.setGenre(n);
				}
				
				//웹툰 상세페이지 이동링크
				String link = "https://www.lezhin.com" + data.get("targetUrl").toString();
				
				car.setLink(link);
				
				//링크 이용해서 상세페이지로 가서 스토리 뽑아오기
				String story= getStory(link);
				car.setStory(story);
				
				//썸네일
				JSONArray im = (JSONArray) data.get("mediaList");
				String img = "https://cdn.lezhin.com/v2" + ((JSONObject)im.get(0)).get("url").toString();
				car.setImg(img);
				
				list.add(car);
			}
		}

    //리스트 출력
		for(Cartoon c : list) {
			System.out.println(c.getTitle());
			System.out.println(c.getWri_story());
			System.out.println(c.getWri_drawing());
			System.out.println(c.getImg());
			System.out.println(c.getGenre());
			System.out.println(c.getLink());
			System.out.println(c.getStory());
		}
	}
	
  
  //웹툰 상세페이지로 이동해서 스토리 뽑아오는 메소드
	public static String getStory(String url) throws IOException {
		Connection conn = Jsoup.connect(url);
		
		Document html = conn.get();
		Element comicinfo = html.getElementsByClass("comicInfoExtend__synopsis").first();
		String story = comicinfo.getElementsByTag("p").first().text();
		
		return story;
	}
}
