package code;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Professor extends InheritanceFrame {
	
	private JButton joinbtn = new JButton();
	private JButton loginbtn = new JButton();
	private JButton lcmbtn = new JButton(); // 강의관리
	private JButton stmbtn = new JButton(); // 학생관리
	
	public Professor() {
		super("PROFESSOR", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		JButtonStyle(joinbtn, 415, 400, 450, 65, "Professor_Screen_Join_Button.png");
		JButtonStyle(loginbtn, 415, 500, 450, 65, "Professor_Screen_Login_Button.png");
		JButtonStyle(lcmbtn, 1000, 35, 100, 100, "Professor_Screen_Lecture_Management_Button.png");
		JButtonStyle(stmbtn, 1130, 35, 100, 100, "Professor_Screen_Student_Management_Button.png");
		
		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Professor_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);
        
        joinbtn.addActionListener(e -> {
        	dispose();
        	new Professor_Join().setVisible(true);
        });
        
        loginbtn.addActionListener(e -> {
        	dispose();
        	new Professor_Login().setVisible(true);
        });
        
        lcmbtn.addActionListener(e -> {
        	dispose();
        	new Lecture_Management().setVisible(true);
        });
        
        stmbtn.addActionListener(e -> {
        	dispose();
        	new Student_Management().setVisible(true);
        });
        
	}
	
	// 버튼 설정 메소드
    private void JButtonStyle(JButton button, int x, int y, int w, int h, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName))); // 버튼 이미지 아이콘 설정
        button.setBorderPainted(false); // 버튼 테두리 제거 
        button.setContentAreaFilled(false); // 버튼 내부 영역 투명하게 설정 → 배경색상표시X
        button.setFocusPainted(false); // 포커스 받을 때 테두리 표시되지 않도록 설정
        button.setBounds(x, y, w, h); // 버튼 위치 나타내는 x, y좌표와 버튼 크기 설정인 가로 w, 세로 h
        add(button); // 프레임 추가
    }
    
}
