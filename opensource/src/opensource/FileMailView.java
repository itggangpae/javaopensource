package opensource;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FileMailView extends JFrame{
	private static final long serialVersionUID = 1L;

	public FileMailView(){
		setTitle("메일 보내기");
		setBounds(100,100,400,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(5,2));
		JLabel lblSend = new JLabel("보내는 사람:  ", JLabel.RIGHT);
		JLabel sendEmail = new JLabel("박문석");
		
		JLabel lblReceive = new JLabel("받는     사람:  ", JLabel.RIGHT);
		JTextField receiveEmail = new JTextField(30);
		
		JLabel lblTitle = new JLabel("제             목:  ", JLabel.RIGHT);
		JTextField txtTitle = new JTextField(30);
		
		JLabel lblFile = new JLabel("파   일    첨  부:  ", JLabel.RIGHT);
		JButton btnFile = new JButton("첨부파일");

		JTextArea txtFiles = new JTextArea(2, 30);
		JScrollPane sp1 = new JScrollPane(txtFiles);
		
		
		northPanel.add(lblSend);
		northPanel.add(sendEmail);
		northPanel.add(lblReceive);
		northPanel.add(receiveEmail);
		northPanel.add(lblTitle);
		northPanel.add(txtTitle);
		northPanel.add(lblFile);
		northPanel.add(btnFile);
		northPanel.add(new JLabel());
		northPanel.add(sp1);
		
		add("North", northPanel);
		
		JTextArea content = new JTextArea(20,20);
		JScrollPane sp = new JScrollPane(content);
		add(sp);
		
		JPanel southPanel = new JPanel();
		JButton btnSend = new JButton("메일 보내기");
		southPanel.add(btnSend);
		add("South", btnSend);
		
		FileMailEventHandler handler = new FileMailEventHandler(receiveEmail, txtTitle, content, txtFiles);
		btnSend.addActionListener(handler);
		btnFile.addActionListener(handler);
		setVisible(true);
	}

}
