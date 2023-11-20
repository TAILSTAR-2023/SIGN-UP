package code;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class SIGNUP extends InheritanceFrame {
    private JButton professorBtn = new JButton();
    private JButton studentBtn = new JButton();
    
    public SIGNUP() {
        super("SIGN - UP", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setLayout(null);
        
        setupButton(professorBtn, 415, 400, "Main_Professor_Button.png"); // 버튼, x좌표, y좌표, 이미지경로
        setupButton(studentBtn, 415, 500, "Main_Student_Button.png"); // 버튼, x좌표, y좌표, 이미지경로
        
        JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Main_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);
        
        professorBtn.addActionListener(e -> {
            dispose();
            new Professor().setVisible(true);
        });
        
        studentBtn.addActionListener(e -> {
            dispose();
            new Student().setVisible(true);
        });

    }
    
    // 버튼 설정 메소드
    private void setupButton(JButton button, int x, int y, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName))); // 버튼 이미지 아이콘 설정
        button.setBorderPainted(false); // 버튼 테두리 제거 
        button.setContentAreaFilled(false); // 버튼 내부 영역 투명하게 설정 → 배경색상표시X
        button.setFocusPainted(false); // 포커스 받을 때 테두리 표시되지 않도록 설정
        button.setBounds(x, y, 450, 65); // 버튼 위치 나타내는 x, y좌표와 버튼 크기 설정인 가로 450, 세로 65
        add(button); // 프레임 추가
    }
}
