//kakaopage

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.*;

import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class dd {
	public static void main(String[] args) throws UnsupportedEncodingException, IOException{
			
		URL url = new URL("https://api2-page.kakao.com/api/v2/store/day_of_week_top/list?category=10&subcategory=0&page=1&day=1&bm=W");
		InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
		JSONObject object = (JSONObject)JSONValue.parse(isr);
		JSONArray bodyArray = (JSONArray) object.get("list");
		ArrayList<Cartoon> list = new ArrayList<Cartoon>();
		
		for(int i = 0 ; i < bodyArray.size(); i++){

			JSONObject data = (JSONObject) bodyArray.get(i);        

			Cartoon car = new Cartoon();
			car.setTitle(data.get("title").toString());
			car.setAuthor(data.get("author").toString());
			car.setAge_grade(data.get("age_grade").toString());
			
			list.add(car);
		}

		for(Cartoon c : list) {
			System.out.println(c.getTitle());
			System.out.println(c.getAuthor());
			System.out.println(c.getAge_grade());
		}
	}
}
