package opensource;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextMailView extends JFrame {
	private static final long serialVersionUID = 1L;

	public TextMailView(){
		setTitle("메일 보내기");
		setBounds(100,100,400,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(3,2));
		JLabel lblSend = new JLabel("보내는 사람:  ", JLabel.RIGHT);
		JLabel sendEmail = new JLabel("박문석");
		JLabel lblReceive = new JLabel("받는   사람:  ", JLabel.RIGHT);
		JTextField receiveEmail = new JTextField(30);
		JLabel lblTitle = new JLabel("제          목:  ", JLabel.RIGHT);
		JTextField txtTitle = new JTextField(30);
		northPanel.add(lblSend);
		northPanel.add(sendEmail);
		northPanel.add(lblReceive);
		northPanel.add(receiveEmail);
		northPanel.add(lblTitle);
		northPanel.add(txtTitle);
		add("North", northPanel);
				
		JTextArea content = new JTextArea(20,20);
		JScrollPane sp = new JScrollPane(content);
		add(sp);
		
		JPanel southPanel = new JPanel();
		JButton btnSend = new JButton("메일 보내기");
		southPanel.add(btnSend);
		add("South", btnSend);
		
		TextMailEventHandler handler = new TextMailEventHandler(receiveEmail, txtTitle, content);
		btnSend.addActionListener(handler);

		
		setVisible(true);
	}
}
