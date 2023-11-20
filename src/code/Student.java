package code;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Student extends InheritanceFrame {

    public static boolean loggedIn = false; // 로그인 여부를 저장하는 변수

    private JButton joinBtn = new JButton();
    private JButton loginBtn = new JButton();
    private JButton enrolmentBtn = new JButton();
    private JButton listBtn = new JButton();

    public Student() {
        super("STUDENT", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        setupButton(joinBtn, 415, 400, 450, 65, "Student_Screen_Join_Button.png");
        setupButton(loginBtn, 415, 500, 450, 65, "Student_Screen_Login_Button.png");
        setupButton(enrolmentBtn, 1000, 35, 100, 100, "Student_Screen_Timetable_Button.png");
        setupButton(listBtn, 1130, 35, 100, 100, "Student_Screen_Timetable_List_Button.png");

        JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Student_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);

        joinBtn.addActionListener(e -> {
            dispose();
            new Student_Join().setVisible(true);
        });

        loginBtn.addActionListener(e -> {
            loggedIn = true; // 로그인이 완료되면 loggedIn 변수를 true로 설정
            System.out.println("로그인 버튼 실행");

            dispose();
            new Student_Login().setVisible(true);
        });

        enrolmentBtn.addActionListener(e -> {
            if (loggedIn) {
                // 로그인된 상태이므로 수강신청 페이지 실행
                dispose();
                new Enrolment().setVisible(true);
                System.out.println("수강신청 창 열림");
            } else {
                // 로그인되지 않은 상태이므로 메시지를 표시
                JOptionPane.showMessageDialog(this, "로그인을 먼저 해주세요.", "안내", JOptionPane.WARNING_MESSAGE);
                System.out.println("수강신청 창 안열림");
            }
        });

        listBtn.addActionListener(e -> {
            if (loggedIn) {
                dispose();
                try {
                    new Timetable_Catalog().setVisible(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                System.out.println("시간표 목록 창 열림");
            } else {
                // 로그인되지 않은 상태이므로 메시지를 표시
                JOptionPane.showMessageDialog(this, "로그인을 먼저 해주세요.", "안내", JOptionPane.WARNING_MESSAGE);
                System.out.println("시간표 목록 창 안열림");
            }
        });
    }

    // 버튼 설정 메소드
    private void setupButton(JButton button, int x, int y, int w, int h, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName)));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBounds(x, y, w, h);
        add(button);
    }
}
