package code;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

// 프레임하고 프레임위에 패널, 배경이미지를 넣을때는 이미지 그리는 클래스 만들어서 객체로 전달

public class START extends JFrame {
//	private Image screenImage;
//	private Graphics screenGraphic;
	
	private Image mainScreen = new ImageIcon(Main.class.getResource("../image/Main_Screen.png")).getImage();
	
	private ImageIcon joinBtn = new ImageIcon(Main.class.getResource("../image/Main_Join_Button.png"));
	private ImageIcon loginBtn = new ImageIcon(Main.class.getResource("../image/Main_Login_Button.png"));
	
	private JButton joinbtn = new JButton(joinBtn);
	private JButton loginbtn = new JButton(loginBtn);
	
	public START() {
		setTitle("SIGN UP");
		// setUndecorated(true); // 풀스크린 모드
		setResizable(false); // 창의 크기 변경 못하도록
		// setLocationRelativeTo(null); // 창을 가운데로 띄우기
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayeredPane(null);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(mainScreen, 0, 0, this);
	}

}
