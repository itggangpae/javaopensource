package opensource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONMovie {

	public static void main(String[] args) {

		// 웹의 문자열 가져오기
		String json = "";
		try {
			// 다운로드 받을 주소 생성
			String addr = "http://cyberadam.cafe24.com/movie/list";
			URL url = new URL(addr);
			// 연결 객체 생성
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			// 옵션 설정
			// 최대 30초 동안 연결을 시도
			con.setConnectTimeout(30000);
			// 웹에서 문자열을 읽어올 스트림 생성
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			// 문자열을 일시적으로 저장할 객체 생성
			StringBuilder sb = new StringBuilder();
			// 줄 단위로 읽어서 sb에 추가
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				sb.append(line + "\n");
			}
			// 문자열 변수에 대입
			json = sb.toString();
			// System.out.printf("%s\n", json);

		} catch (Exception e) {
			System.out.printf("다운로드 예외:%s\n", e.getMessage());
			e.printStackTrace();
		}

		try {

			JSONObject data = new JSONObject(json);
			JSONArray movies = data.getJSONArray("list");
			for (int i = 0; i < movies.length(); i = i + 1) {
				JSONObject item = movies.getJSONObject(i);
				System.out.printf("%s:%s\n", item.getString("title"), item.getDouble("rating"));
			}

		} catch (Exception e) {
			System.out.printf("JSON 파싱 예외:%s\n", e.getMessage());
			e.printStackTrace();
		}
	}
}
