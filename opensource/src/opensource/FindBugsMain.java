package opensource;

public class FindBugsMain {

	public static void main(String[] args) {
		String text = "on";
		if(text == "on") {
			System.out.println("2개의 문자열은 동일합니다.");
		}else {
			System.out.println("2개의 문자열은 동일하지 않습니다.");
		}
	}
}
