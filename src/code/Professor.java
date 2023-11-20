package code;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Professor extends InheritanceFrame {

    public static boolean loggedIn = false; // 로그인 여부를 저장하는 변수

    private JButton joinBtn = new JButton();
    private JButton loginBtn = new JButton();
    private JButton lectureManagementBtn = new JButton(); // 강의관리
    private JButton studentManagementBtn = new JButton(); // 학생관리

    public Professor() {
        super("PROFESSOR", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // 버튼 설정
        setupButton(joinBtn, 415, 400, 450, 65, "Professor_Screen_Join_Button.png");
        setupButton(loginBtn, 415, 500, 450, 65, "Professor_Screen_Login_Button.png");
        setupButton(lectureManagementBtn, 1000, 35, 100, 100, "Professor_Screen_Lecture_Management_Button.png");
        setupButton(studentManagementBtn, 1130, 35, 100, 100, "Professor_Screen_Student_Management_Button.png");

        JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Professor_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);

        joinBtn.addActionListener(e -> {
            dispose();
            new Professor_Join().setVisible(true);
        });

        loginBtn.addActionListener(e -> {
            loggedIn = true; // 로그인이 완료되면 loggedIn 변수를 true로 설정
            System.out.println("로그인 버튼 실행");

            dispose();
            new Professor_Login().setVisible(true);
        });

        lectureManagementBtn.addActionListener(e -> {
            if (loggedIn) {
                // 로그인된 상태이므로 강의 관리 페이지 실행
                dispose();
                new Lecture_Management().setVisible(true);
                System.out.println("강의관리 창 열림");
            } else {
                // 로그인되지 않은 상태이므로 메시지를 표시
                JOptionPane.showMessageDialog(this, "로그인을 먼저 해주세요.", "안내", JOptionPane.WARNING_MESSAGE);
                System.out.println("강의관리 창 안열림");
            }
        });

        studentManagementBtn.addActionListener(e -> {
            if (loggedIn) {
                dispose();
                new Student_Management().setVisible(true);
            } else {
                // 로그인되지 않은 상태이므로 메시지를 표시
                JOptionPane.showMessageDialog(this, "로그인을 먼저 해주세요.", "안내", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    // 버튼 설정 메소드
    private void setupButton(JButton button, int x, int y, int w, int h, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName))); // 버튼 이미지 아이콘 설정
        button.setBorderPainted(false); // 버튼 테두리 제거
        button.setContentAreaFilled(false); // 버튼 내부 영역 투명하게 설정 → 배경색상표시X
        button.setFocusPainted(false); // 포커스 받을 때 테두리 표시되지 않도록 설정
        button.setBounds(x, y, w, h); // 버튼 위치 나타내는 x, y좌표와 버튼 크기 설정인 가로 w, 세로 h
        add(button); // 프레임 추가
    }
}
