package opensource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HaniHTMLParsing {

	public static void main(String[] args) {
		//hani.co.kr의 html 받아오기
		String html = "";
		try {
			String addr = "https://www.hani.co.kr/";
			URL url = new URL(addr);
			HttpURLConnection con = 
				(HttpURLConnection)
					url.openConnection();
			con.setConnectTimeout(30000);
			
			BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(
						con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				sb.append(line + "\n");
			}
			
			html = sb.toString();
			br.close();
			con.disconnect();
			
			//System.out.printf("%s\n", html);
			
			
		}catch(Exception e) {
			System.out.printf(
				"다운로드 예외:%s\n", e.getMessage());
		}
		
		//HTML 파싱을 수행하는 부분
		try {
			//HTML 문자열을 파싱할 수 있도록 DOM으로 생성
			Document document = 
				Jsoup.parse(html);
			//태그로 찾아오기
			/*
			Elements elements = 
				document.getElementsByTag("a");
			for(int i=0; i<elements.size(); i=i+1) {
				Element element = elements.get(i);
				System.out.printf("%s\n",
					element.text());
			}
			*/
			
			Elements elements = 
				document.select(
				"#main-top > div.main-top > div.main-top-article > h4 > a");
			for(int i=0; i<elements.size(); i=i+1) {
				Element element = 
					elements.get(i);
				//태그 안의 문자열을 찾아오기
				System.out.printf("%s\n",
					element.text());
				//태그 내의 속성 값을 찾아오기
				System.out.printf("%s\n", 
					element.attr("href"));
			}
			
		}catch(Exception e) {
			System.out.printf(
				"HTML 파싱 예외:%s\n", e.getMessage());
		}

	}

}
