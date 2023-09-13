package code;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Enrolment_Cart extends InheritanceFrame {
	
	private JButton enrolmentbtn = new JButton();
	private JButton exitbtn = new JButton();
	
	public Enrolment_Cart() {
		super ("ENROLMENT CART", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		JButtonStyle(enrolmentbtn, 755, 20, "Enrolment_Cart_Screen_enrolment_Button.png");
		JButtonStyle(exitbtn, 920, 20, "Enrolment_Cart_Screen_exit_Button.png");
		
		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Enrolment_Cart_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);
        
        enrolmentbtn.addActionListener(e -> {
        	dispose();
        	new Enrolment().setVisible(true);
        });
        
        exitbtn.addActionListener(e -> {
        	dispose();
        	new Student().setVisible(true);
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

}
