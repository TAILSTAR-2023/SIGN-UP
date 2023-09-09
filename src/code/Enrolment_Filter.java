package code;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Enrolment_Filter extends InheritanceFrame {
	
	
	private JButton exitbtn = new JButton();
	private JButton searchbtn = new JButton();
	
	private JTextField majortx = new JTextField();
	private JTextField courseNtx = new JTextField();
	private JTextField subjectNtx = new JTextField();
	private JTextField professorNtx = new JTextField();
	private JTextField gradetx = new JTextField();
	private JTextField divisiontx = new JTextField();
	private JTextField classificationtx = new JTextField();
	private JTextField favoritestx = new JTextField();
	
	public Enrolment_Filter() {
		super("ENROLMENT FILTER", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null);
		
		JButtonStyle(exitbtn, -160, 20, "Enrolment_Filter_Screen_Back_Button.png");
		JButtonStyle(searchbtn, 450, 620, "Enrolment_Filter_Screen_Search_Button.png");
		
		TextFieldStyle(majortx, 140);
		TextFieldStyle(courseNtx, 200);
		TextFieldStyle(subjectNtx, 260);
		TextFieldStyle(professorNtx, 320);
		TextFieldStyle(gradetx, 380);
		TextFieldStyle(divisiontx, 440);
		TextFieldStyle(classificationtx, 500);
		TextFieldStyle(favoritestx, 570);
		
		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Enrolment_Filter_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);
        
        exitbtn.addActionListener(e -> {
        	dispose();
        	new Student().setVisible(true);
        });
        
        searchbtn.addActionListener(e -> {
        	dispose();
        	new Enrolment_Filter_Result().setVisible(true);
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
    private JTextField TextFieldStyle(JTextField textField, int x) {
        textField.setBounds(530, x, 495, 43);
        textField.setBackground(Color.decode("#C8C9DA"));
        textField.setFont(new Font("SUITE", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        add(textField);
        return textField;
    }

}
