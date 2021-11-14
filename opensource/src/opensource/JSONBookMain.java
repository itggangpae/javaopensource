package opensource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

class JsonThread extends Thread {
	public void run() {
		// 도서이름을 입력받기 위한 스캐너 생성
		Scanner sc = new Scanner(System.in);
		while (true) {
			//도서이름 입력받기
			System.out.print("조회할 도서이름(종료는 그냥 엔터):");
			String bookname = sc.nextLine();
			if(bookname.trim().length() == 0) {
				break;
			}
			try {
				//도서제목이 한글인 경우를 대비해서 인코딩
				bookname = URLEncoder.encode(bookname, "utf8");
				//다운로드 받은 문자열을 임시로 저장할 변수
				StringBuilder sBuffer = new StringBuilder();
				//다운로드 받은 문자열 전체를 저장할 변수
				String json = "";
				//파싱한 결과를 출력하기 위한 인스턴스
				ArrayList<Map<String, Object>> data = new ArrayList<>();
				try {
					//다운로드 받을 url 만들기
					String urlAddr = "https://dapi.kakao.com/v3/search/book?target=title&query=" + bookname;
					URL url = new URL(urlAddr);
					//url에 연결하는 객체 만들기
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					//연결 객체가 제대로 만들어져 있다면
					if (conn != null) {
						//다운로드 옵션 설정
						conn.setConnectTimeout(20000);
						conn.setUseCaches(false);
						//헤더 설정
						conn.addRequestProperty("Authorization", "KakaoAK 06fab290c9f4eb6f130c09796d57bc30");
						//서버에서 정상 응답을 하면	
						if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
							//서버에서 문자열을 읽어올 스트림을 생성
							InputStreamReader isr = new InputStreamReader(conn.getInputStream());
							BufferedReader br = new BufferedReader(isr);
							//스트림에서 한 줄 씩 읽어서 sBuffer에 추가
							while (true) {
								String line = br.readLine();
								if (line == null) {
									break;
								}
								sBuffer.append(line);
							}
							//전부 읽었으므로 서버와 연결 해제
							br.close();
							conn.disconnect();
						}
					}
					//읽어온 문자열을 json에 추가
					json = sBuffer.toString();
					//System.out.println(json);
				} catch (Exception e) {
					System.out.println("가져오기 실패:" + e.getMessage());
				}
				
				//JSON 파싱
				try {
					//처음에는 객체 형태로 가져오므로 객체 형태로 파싱
					JSONObject obj = new JSONObject(json);
					//documents 키에 해당하는 데이터를 배열로 가져오기
					JSONArray documents = obj.getJSONArray("documents");
					//배열의 데이터를 하나씩 읽으면서 수행
					for (int i = 0; i < documents.length(); i++) {
						//하나의 객체 가져오기
						JSONObject book = documents.getJSONObject(i);
						//하나의 객체를 저장하기 위한 Map 생성
						Map<String, Object> map = new HashMap<>();
						
						//제목과 저자는 바로 문자열로 가져오기
						map.put("title", book.getString("title"));
						map.put("price", book.getInt("price"));
						
						//저자는 다시 배열이므로 배열을 순회해서 list에 추가
						JSONArray authors = book.getJSONArray("authors");
						List<String>authorList = new ArrayList<>();
						for (int j = 0; j < authors.length(); j++) {
							authorList.add(authors.getString(j));
						}
						map.put("author", authorList);
						
						//하나의 객체를 전체 리스트에 저장
						data.add(map);
					}
				} catch (Exception e) {
					System.out.println("파싱 실패:" + e.getMessage());
				}
				
				//데이터 출력
				System.out.printf("%40s%10s%30s\n", "제목", "가격", "저자");
				for(Map<String, Object> temp : data) {
					System.out.printf("%40s%10d", temp.get("title"), temp.get("price"));
					@SuppressWarnings("unchecked")
					List <String> list = (List<String>)temp.get("author");
					for(String imsi : list) {
						System.out.printf("%20s ", imsi);
					}
					System.out.println();
				}
			
			} catch (UnsupportedEncodingException e) {
				System.out.println("인코딩 에러");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
			
		sc.close();
	}
}

public class JSONBookMain {
	public static void main(String[] args) {
		JsonThread th = new JsonThread();
		th.start();
	}
}
