package code;

import java.awt.Color;
import java.awt.Font;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Enrolment extends InheritanceFrame {
	
	// TODO : 텍스트라벨 생성, 신청 버튼 삽입
	
	private JButton refreshbtn = new JButton();
	private JButton previewbtn = new JButton();
	private JButton submitbtn = new JButton();
	
	private JTextField majortx = new JTextField();
	private JTextField numtx = new JTextField();
	private JTextField classtx = new JTextField();
	private JTextField subjecttx = new JTextField();
	private JTextField coursetx = new JTextField();
	private JTextField scoretx = new JTextField();
	private JTextField timetx = new JTextField();
	private JTextField lectureroomtx = new JTextField();
	
	public Enrolment() {
		super("ENROLMENT", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		JButtonStyle(refreshbtn, 755, 20, "Enrolment_Screen_Refresh_Button.png");
		JButtonStyle(previewbtn, 920, 20, "Enrolment_Screen_Preview_Button.png");
		JButtonStyle(submitbtn, 400, 600, "Enrolment_Application_Button.png");
		
		TextFieldStyle(majortx, 250, 130);
		TextFieldStyle(numtx, 250, 250);
		TextFieldStyle(classtx, 250, 380);
		TextFieldStyle(subjecttx, 250, 510);
		TextFieldStyle(coursetx, 850, 130);
		TextFieldStyle(scoretx, 850, 250);
		TextFieldStyle(timetx, 850, 380);
		TextFieldStyle(lectureroomtx, 850, 510);
		
		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Enrolment_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);
        
        
        previewbtn.addActionListener(e -> {
        	dispose();
        	new Timetable_Preview().setVisible(true);
        });
        
        submitbtn.addActionListener(e -> {
        	// 텍스트필드에 있는 값을 문자열로 변환하여 변수에 저장
        	String major = majortx.getText();
        	String num = numtx.getText();
        	String classroom = classtx.getText();
        	String subject = subjecttx.getText();
        	String course = coursetx.getText();
        	String score = scoretx.getText();
        	String time = timetx.getText();
        	String lectureroom = lectureroomtx.getText();
        	
        	Statement stmt;
        	stmt.execute()
        });
        
	}
	
	// 버튼 설정 메소드
    private void JButtonStyle(JButton button, int x, int y, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName))); // 버튼 이미지 아이콘 설정
        button.setBorderPainted(false); // 버튼 테두리 제거 
        button.setContentAreaFilled(false); // 버튼 내부 영역 투명하게 설정 → 배경색상표시X
        button.setFocusPainted(false); // 포커스 받을 때 테두리 표시되지 않도록 설정
        button.setBounds(x, y, 500, 65); // 버튼 위치 나타내는 x, y좌표와 버튼 크기 설정인 가로 500, 세로 65
        add(button); // 프레임 추가
    }
    
 // 텍스트필드 설정 메소드
    private JTextField TextFieldStyle(JTextField textField, int x, int y) {
        textField.setBounds(x, y, 350, 55); // x, y, width, heigth
        textField.setBackground(Color.decode("#FFFFFF"));
        textField.setFont(new Font("SUITE", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        add(textField);
        return textField;
    }

}
