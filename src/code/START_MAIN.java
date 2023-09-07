package code;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class START_MAIN extends InheritanceFrame {
    private JButton joinbtn = new JButton(new ImageIcon(getClass().getResource("/image/Main_Join_Button.png")));
    private JButton loginbtn = new JButton(new ImageIcon(getClass().getResource("/image/Main_Login_Button.png")));
    
    public START_MAIN() {
        super("SIGN - UP", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setLayout(null);
        
        JButtonStyle(joinbtn, 415, 400, "Main_Join_Button.png"); // 버튼, x좌표, y좌표, 이미지경로
        JButtonStyle(loginbtn, 415, 500, "Main_Login_Button.png"); // 버튼, x좌표, y좌표, 이미지경로
        
        JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/MainScreen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);
    }
    
    // 버튼 설정 메소드
    private void JButtonStyle(JButton button, int x, int y, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName))); // 버튼 이미지 아이콘 설정
        button.setBorderPainted(false); // 버튼 테두리 제거 
        button.setContentAreaFilled(false); // 버튼 내부 영역 투명하게 설정 → 배경색상표시X
        button.setFocusPainted(false); // 포커스 받을 때 테두리 표시되지 않도록 설정
        button.setBounds(x, y, 450, 55); // 버튼 위치 나타내는 x, y좌표와 버튼 크기 설정인 가로 450, 세로 55
        add(button); // 프레임 추가
    }
    
}