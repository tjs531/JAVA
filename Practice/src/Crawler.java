import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Crawler {

/*	public static void main(String[] args) {
		
		String url = "https://comic.naver.com/webtoon/weekday.nhn";
		Document doc = null;
		List<String> list = new ArrayList();
		String str;
		
		try {
			doc = Jsoup.connect(url).get();
			//System.out.println(doc);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("div.list_area");
		
		//제목 가져오
		Iterator<Element> ie1 = element.select("a.title").iterator();
		
		while(ie1.hasNext()) {
			str = ie1.next().text();
			list.add(str);
		}
		
		for(int i=0; i< list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	}*/
}
