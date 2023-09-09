package code;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Student_Join extends InheritanceFrame {
	
	private JButton doublecheckbtn = new JButton();
	private JButton completebtn = new JButton();
	private JTextField nametx = new JTextField();
	private JTextField idtx = new JTextField();
	private JPasswordField pwtx = new JPasswordField();
	private JPasswordField pwdctx = new JPasswordField();
	private JFormattedTextField teltx = new JFormattedTextField(); // 숫자입력위해
	private JTextField emailtx = new JTextField();
	
	public Student_Join() {
		super("STUDENT JOIN", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null);
		
		JButtonStyle(doublecheckbtn, 980, 405, "Student_Join_Screen_DoubleCheck_Button.png"); // 버튼, x좌표, y좌표, 이미지경로
		JButtonStyle(completebtn, 1100, 35, "Student_Join_Screen_Complete_Button.png");
		TextFieldStyle(nametx, 170);
		TextFieldStyle(idtx, 245);
		TextFieldStyle(pwtx, 325);
		TextFieldStyle(pwdctx, 410);
		TextFieldStyle(teltx, 500);
		TextFieldStyle(emailtx, 580);
			
		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Student_Join_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);
		
	}
	
	// 버튼 설정 메소드
    private void JButtonStyle(JButton button, int x, int y, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName))); // 버튼 이미지 아이콘 설정
        button.setBorderPainted(false); // 버튼 테두리 제거 
        button.setContentAreaFilled(false); // 버튼 내부 영역 투명하게 설정 → 배경색상표시X
        button.setFocusPainted(false); // 포커스 받을 때 테두리 표시되지 않도록 설정
        button.setBounds(x, y, 140, 65); // 버튼 위치 나타내는 x, y좌표와 버튼 크기 설정인 가로 140, 세로 65
        add(button); // 프레임 추가
    }
    
    // 텍스트필드 설정 메소드
    private JTextField TextFieldStyle(JTextField textField, int x) {
        textField.setBounds(500, x, 450, 55);
        textField.setBackground(Color.decode("#DFD4D6"));
        textField.setFont(new Font("SUITE", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        add(textField);
        return textField;
    }

}
