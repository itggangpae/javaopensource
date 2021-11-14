package opensource;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

public class FileMailEventHandler implements ActionListener {
	private JTextField txtReceiveEmail;
	private JTextField txtTitle;
	private JTextArea txtContent;
	private JTextArea txtFiles;

	public FileMailEventHandler(JTextField txtReceiveEmail, JTextField txtTitle, JTextArea txtContent,
			JTextArea txtFiles) {
		super();
		this.txtReceiveEmail = txtReceiveEmail;
		this.txtTitle = txtTitle;
		this.txtContent = txtContent;
		this.txtFiles = txtFiles;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("메일 보내기")) {
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

			MultiPartEmail email = new MultiPartEmail();

			// Smtp 서버 연결 설정.
			email.setHostName("smtp.naver.com");
			email.setSmtpPort(587);
			email.setAuthentication("ggangpae11", "cyberadam");
			String rt = "";
			try {
				// Smtp SSL, TLS 설정
				email.setSSLOnConnect(true);
				email.setStartTLSEnabled(true);
				
				email.setCharset("utf-8");

				if (txtFiles.getText().length() != 0) {
					String[] fileNames = txtFiles.getText().split(",");
					for (String file : fileNames) {
						EmailAttachment attach = new EmailAttachment();
						attach.setName("");
						attach.setPath(file);
						email.attach(attach);
					}
				}

				// 받는 사람 설정
				email.addTo(receiveEmail, "받는 이", "utf-8");
				// 보내는 사람 설정
				email.setFrom("ggangpae11@naver.com", "박문석", "utf-8");

				// 제목 설정
				email.setSubject(title);
				// 본문 설정
				email.setMsg(content);
				rt = email.send();
				System.out.println(rt);
				JOptionPane.showMessageDialog(null, "메일 보내기 성공");
				txtReceiveEmail.setText("");
				txtTitle.setText("");
				txtContent.setText("");

			} catch (Exception exception) {
				JOptionPane.showMessageDialog(null, exception.getMessage(), "메일 보내기 실패", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JFileChooser fc = new JFileChooser();
			fc.setMultiSelectionEnabled(true);
			int result = fc.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File[] f = fc.getSelectedFiles();
				for (File file : f) {
					if (txtFiles.getText().trim().length() == 0) {
						txtFiles.setText(file.getPath());
					} else {
						String origin = txtFiles.getText();
						txtFiles.setText(origin + "," + file.getPath());
					}
				}
			}
		}
	}
}
