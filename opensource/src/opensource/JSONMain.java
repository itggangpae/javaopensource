package opensource;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONMain {

	public static void main(String[] args) {
		//JSON 배열 파싱
		String json = "[100, 500, 300, 200, 400]";
		JSONArray ar = new JSONArray(json);
		for (int i = 0; i < ar.length(); i++) {
			System.out.print(ar.get(i) + "\t");
		}
		System.out.println();
		//JSON 객체 파싱
		json = "{color: \"red\",value: \"#f00\"}";
		JSONObject obj = new JSONObject(json);
		System.out.println("색상:" + obj.getString("color"));
		System.out.println("색상:" + obj.getString("value"));

	}
}
