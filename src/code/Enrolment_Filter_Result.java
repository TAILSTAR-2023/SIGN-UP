package code;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Enrolment_Filter_Result extends InheritanceFrame {
	
	private JButton backbtn = new JButton();
	
	public Enrolment_Filter_Result() {
		super("ENROLMENT FILTER RESUIT", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null);
		
		JButtonStyle(backbtn, -160, 20, "Enrolment_Filter_Screen_Back_Button.png");
		
		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Enrolment_Filter_Result_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);
        
        backbtn.addActionListener(e -> {
        	dispose();
        	new Enrolment_Filter().setVisible(true);
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
