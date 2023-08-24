package code;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Login extends JFrame {
	private JPanel main_screen;
	private JButton join_button;
	private JButton login_button;
	private ImageIcon Join_Button = new ImageIcon("./img/Main_Join_Button.png");
	private ImageIcon Login_Button = new ImageIcon("./img/Main_Login_Button.png");
	private ImageIcon background_image = new ImageIcon("./img/Main_Screen.png");
	
	public Login() {
		main_screen = new JPanel();
		setTitle("SIGN - UP");
		setSize(1000, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false); // JPanel 창의 크기 변경 못하도록
		setLocationRelativeTo(null); // JPanel을 가운데로 띄우기
		
		join_button();
		login_button();
		// 메인화면
		// main_screen = new JPanel(new ImageIcon(".img/Main_Screen.jpg").getImage());
		
		// 메인화면 이미지 삽입
		setVisible(true); // 화면에 보이도록
				
	}
	
	public void join_button() {
		// 회원가입 버튼
		join_button = new JButton(Join_Button);
		join_button.setBorderPainted(false); // 버튼 테두리 설정해제
		join_button.setPreferredSize(new Dimension(600, 70)); // 버튼 크기 설정
		main_screen.add(join_button);
	}
	
	public void login_button() {
		// 로그인 버튼
		login_button = new JButton(Login_Button);
		login_button.setBorderPainted(false); // 버튼 테두리 설정해제
		login_button.setPreferredSize(new Dimension(600, 70)); // 버튼 크기 설정
		main_screen.add(join_button);
	}
	
//	public void paint(Graphics g) {
//		g.drawImage(background_image, 0, 0, null);
//	}
	
	public static void main(String[] args) {
		new Login();
	}

}
