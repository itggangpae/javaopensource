package opensource;

import org.mindrot.jbcrypt.BCrypt;

public class JbcryptMain {

	public static void main(String[] args) {
		String encryptString= BCrypt.hashpw("cyberadam", BCrypt.gensalt());
		System.out.printf("암호화된 문자열:%s\n", encryptString);
		Boolean result = BCrypt.checkpw("cyberadam", encryptString);
		if(result) {
			System.out.printf("두 개의 문자열은 일치합니다.\n");
		}else {
			System.out.printf("두 개의 문자열은 일치하지 않습니다.\n");
		}
	}
}
