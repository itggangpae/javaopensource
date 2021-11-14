package opensource;

import java.io.File;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class HTMLMailMain {

	public static void main(String[] args) {
		HtmlEmail htmlEmail = new HtmlEmail();

		// Smtp 서버 연결 설정.
		htmlEmail.setHostName("smtp.naver.com");
		htmlEmail.setSmtpPort(587);
		htmlEmail.setAuthentication("ggangpae11", "cyberadam");
		// 이미지 파일 생성
		File logoFile = new File("red3.jpg");

		String rt = "Failure";

		try {
			// Smtp SSL, TLS 설정
			htmlEmail.setSSLOnConnect(true);
			htmlEmail.setStartTLSEnabled(true);
			htmlEmail.setCharset("utf-8");

			// 받는 사람 설정
			htmlEmail.addTo("ggangpae1@gmail.com", "박문석", "utf-8");
			// 보내는 사람 설정
			htmlEmail.setFrom("ggangpae11@naver.com", "박문석", "utf-8");
			// 제목 설정
			htmlEmail.setSubject("html 메일 보내기");

			// HTML 테그가 포함된 메일 본문을 StringBuffer 객체를 사용하여 처리
			StringBuffer sb = new StringBuffer();
			sb.append("<html><body>");
			sb.append("안녕하세요 박문석 입니다. <br />");
			sb.append("<img src=cid:").append(htmlEmail.embed(logoFile)).append(">");
			sb.append("</body></html>");
			htmlEmail.setHtmlMsg(sb.toString());

			rt = htmlEmail.send();
			System.out.println(rt);
		} catch (EmailException e) {
			System.out.println(e.getMessage());
		}

	}

}
