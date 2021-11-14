package opensource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class KhanHandler extends DefaultHandler {
	// 파싱할 데이터를 저장할 객체
	List<Map<String, String>> list = new ArrayList<>();

	// 파싱에 사용할 임시 변수
	Map<String, String> map = null;
	String content = null;

	public KhanHandler(List<Map<String, String>> list) {
		this.list = list;
	}

	// 문서의 시작
	public void startDocument() {
		//System.out.println("문서 시작");
	}

	// 문서의 종료
	public void endDocument() {
		//System.out.println("문서 종료");
	}

	// 시작 태그 인식했을 때 처리
	public void startElement(String url, String name, String elementName, Attributes attrs) throws SAXException {
		if (elementName.equals("item")) {
			map = new HashMap<>();
		}
	}

	// 시작태그와 끝태그 사이의 내용을 인식 했을 때 처리
	public void characters(char[] str, int start, int len) throws SAXException {
		if (content == null) {
			content = new String(str, start, len);
		} else {
			content = content + new String(str, start, len);
		}
	}

	// 끝 태그를 인식 했을 때 처리
	public void endElement(String url, String localName, String name) {
		if (name.equals("item")) {
			list.add(map);
			map = null;
		}
		if (map != null) {
			if (name.equals("title")) {
				map.put("title", content);
			} else if (name.equals("link")) {
				map.put("link", content);
			} else if (name.equals("description")) {
				map.put("description", content);
			}
		}
		content = null;
	}
}

public class KhanParsing {
	public static void main(String[] args) {
		try {
			// 파싱한 결과 저장할 인스턴스
			List<Map<String, String>> list = new ArrayList<>();
			// 파싱 수행할 객체 만들기
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
			// 파싱 수행을 위임
			parser.parse("http://www.khan.co.kr/rss/rssdata/sk_entertainment.xml", new KhanHandler(list));
			//데이터 출력
			for(Map<String, String> map : list) {
				System.out.println(map.get("title").trim() + ":" + map.get("link").trim());
				System.out.println(map.get("description").trim().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
				System.out.println();
			}
		} catch (Exception e) {
			System.out.printf("XML 파싱 예외:%s\n", e.getMessage());
			e.printStackTrace();
		}
	}
}
