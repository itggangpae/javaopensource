package opensource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BobaeHTMLParsing {
	public static void main(String[] args) {
		StringBuilder sBuffer = new StringBuilder();
		String html = "";
		try {
			// 데이터를 다운로드 받을 주소 생성
			String urlAddr = "https://www.bobaedream.co.kr/cyber/CyberCar.php?gubun=K&page=1";

			URL url = new URL(urlAddr);
			// 주소와 연결
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			if (conn != null) {
				conn.setConnectTimeout(20000);
				conn.setUseCaches(false);

				InputStreamReader isr = new InputStreamReader(conn.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				// 줄 단위로 읽어서 sBuffer에 저장
				while (true) {
					String line = br.readLine();
					if (line == null) {
						break;
					}
					sBuffer.append(line);
				}
				br.close();
				conn.disconnect();
			}
			// 전부 읽었으면 String으로 변환
			html = sBuffer.toString();
		} catch (Exception e) {
			System.out.println("다운로드 중 에러 발생");
		}
		// html 문자열을 메모리에 펼침
		Document doc = Jsoup.parse(html);
		// 펼쳐진 데이터에서 클래스 이름이 carinfo 인 데이터 찾아오기
		Elements root = doc.getElementsByClass("mode-cell title");
		//System.out.println(root);

		// 클래스가 price 인 데이터 찾아오기
		Elements prices = doc.getElementsByClass("price");
		//System.out.println(prices);

		// Parsing한 결과를 저장할 리스트 생성
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		for (int i = 1; i < root.size(); i++) {
			Element element = root.get(i);
			// carinfo 클래스 안에 있는 요소 중에서 클래스가 title 인 데이터 전부 찾아오기
			Elements titles = element.getElementsByTag("a");
			// price를 순회하면서 em 태그의 데이터 찾아오기
			Element price = prices.get(i+1);
			try {
				Elements strong = price.getElementsByTag("strong");

				// 하나의 데이터를 저장할 맵을 생성
				map = new LinkedHashMap<String, String>();
				// title 클래스의 첫번째 데이터의 문자열을 title이라는 키에 저장
				map.put("title", titles.get(0).text());
				map.put("price", strong.get(0).text());
			}catch(Exception e) {
				map.put("price", "계약");
			}

			list.add(map);
		}

		for (Map<String, String> temp : list) {
			System.out.println(temp);
		}
	}
}
