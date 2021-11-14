package opensource;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.mail.SimpleEmail;

public class TextMailEventHandler implements ActionListener {
	private JTextField txtReceiveEmail;
	private JTextField txtTitle;
	private JTextArea txtContent;

	public TextMailEventHandler(JTextField txtReceiveEmail, JTextField txtTitle, JTextArea txtContent) {
		super();
		this.txtReceiveEmail = txtReceiveEmail;
		this.txtTitle = txtTitle;
		this.txtContent = txtContent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String receiveEmail = txtReceiveEmail.getText().trim();
		if (receiveEmail.length() == 0) {
			JOptionPane.showMessageDialog(null, "받는 분의 이메일 주소가 없습니다.");
			return;
		}
		String title = txtTitle.getText().trim();
		if (title.length() == 0) {
			title = "무제";
		}
		String content = txtContent.getText().trim();
		if (content.length() == 0) {
			content = "냉무";
		}

		SimpleEmail simpleEmail = new SimpleEmail();

		// Smtp 서버 연결 설정.
		simpleEmail.setHostName("smtp.naver.com");
		simpleEmail.setSmtpPort(587);
		simpleEmail.setAuthentication("ggangpae11", "cyberadam");
		String rt = "";
		try {
			// Smtp SSL, TLS 설정
			simpleEmail.setSSLOnConnect(true);
			simpleEmail.setStartTLSEnabled(true);
			simpleEmail.setCharset("utf-8");

			// 받는 사람 설정
			simpleEmail.addTo(receiveEmail, "받는 이", "utf-8");
			// 보내는 사람 설정
			simpleEmail.setFrom("ggangpae11@naver.com", "박문석", "utf-8");
			// 제목 설정
			simpleEmail.setSubject(title);

			// 본문 설정
			simpleEmail.setMsg(content);
			rt = simpleEmail.send();
			System.out.println(rt);
			JOptionPane.showMessageDialog(null, "메일 보내기 성공");
			txtReceiveEmail.setText("");
			txtTitle.setText("");
			txtContent.setText("");

		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, exception.getMessage(), "메일 보내기 실패", JOptionPane.ERROR_MESSAGE);
			;
		}

	}

}
