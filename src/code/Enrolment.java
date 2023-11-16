package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Enrolment extends InheritanceFrame {

    private JButton refreshbtn = new JButton();
    private JButton catalogbtn = new JButton();
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

        JButtonStyle(refreshbtn, 870, 20, 150, 65, "Enrolment_Screen_Refresh_Button.png");
        JButtonStyle(catalogbtn, 1050, 20, 150, 65, "Enrolment_Screen_List_Button.png");
        JButtonStyle(submitbtn, 350, 600, 600, 65, "Enrolment_Application_Button.png");

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
        
        catalogbtn.setEnabled(true);

        // refreshbtn 액션 리스너
        refreshbtn.addActionListener(e -> {
            // 텍스트 필드 초기화
            majortx.setText("");
            numtx.setText("");
            classtx.setText("");
            subjecttx.setText("");
            coursetx.setText("");
            scoretx.setText("");
            timetx.setText("");
            lectureroomtx.setText("");
        });

        catalogbtn.addActionListener(e -> {
            System.out.println("Catalog Button Clicked!"); // 디버깅 출력문 추가
            dispose();
            System.out.println("After dispose()"); // 디버깅 출력문 추가

            try {
                Timetable_Catalog timetableList = new Timetable_Catalog();
                System.out.println("Before setDefaultCloseOperation");
                timetableList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                System.out.println("After setDefaultCloseOperation");
                
                System.out.println("Before setVisible(true)");
                timetableList.setVisible(true);
                System.out.println("After setVisible(true)");
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Exception: " + ex.toString());
                JOptionPane.showMessageDialog(this, "오류 발생: " + ex.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            }
        });

        // submitbtn 액션 리스너
        submitbtn.addActionListener(e -> {
            String major = majortx.getText();
            String num = numtx.getText();
            String classroom = classtx.getText();
            String subject = subjecttx.getText();
            String course = coursetx.getText();
            String score = scoretx.getText();
            String time = timetx.getText();
            String lectureroom = lectureroomtx.getText();

            DB_connection s = null; // DB_connection 객체를 선언합니다.

            try {
                // 데이터베이스에 연결
                s = new DB_connection();

                // timetable_list 테이블에서 입력된 값과 일치하는 레코드 찾기
                if (checkIfRecordExists(s, major, num, classroom, subject, course, score, time, lectureroom)) {
                    // timetable 테이블에 값 삽입
                    String insertSql = "INSERT INTO signup.timetable(major, num, class, subject, course, score, time, lectureroom) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement insertPs = s.conn.prepareStatement(insertSql)) {
                        insertPs.setString(1, major);
                        insertPs.setString(2, num);
                        insertPs.setString(3, classroom);
                        insertPs.setString(4, subject);
                        insertPs.setString(5, course);
                        insertPs.setString(6, score);
                        insertPs.setString(7, time);
                        insertPs.setString(8, lectureroom);

                        int affectedRows = insertPs.executeUpdate();

                        if (affectedRows > 0) {
                            JOptionPane.showMessageDialog(this, "수강신청 완료", "알림", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "수강신청 실패", "오류", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "강의 정보 없음", "알림", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "오류 발생: " + e1.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                System.out.println(e1.toString());
            }
        });
    }
    
    // 버튼 설정 메소드
    private void JButtonStyle(JButton button, int x, int y, int w, int h, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName))); // 버튼 이미지 아이콘 설정
        button.setBorderPainted(false); // 버튼 테두리 제거 
        button.setContentAreaFilled(false); // 버튼 내부 영역 투명하게 설정 → 배경색상표시X
        button.setFocusPainted(false); // 포커스 받을 때 테두리 표시되지 않도록 설정
        button.setBounds(x, y, w, h); // 버튼 위치 나타내는 x, y좌표와 버튼 크기 설정인 가로 150, 세로 65
        add(button); // 프레임 추가
    }
    
	// 텍스트필드 설정 메소드
    private JTextField TextFieldStyle(JTextField textField, int x, int y) {
        textField.setBounds(x, y, 350, 55); // x, y, width, height
        textField.setBackground(Color.decode("#DBD0D2"));
        textField.setFont(new Font("SUITE", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        add(textField);
        return textField;
    }

    // timetable_list 테이블에서 입력된 값과 일치하는 레코드가 있는지 확인하는 메소드
    private boolean checkIfRecordExists(DB_connection s, String major, String num, String classroom, String subject, String course, String score, String time, String lectureroom) {
        try {
            String query = "SELECT * FROM signup.timetable_list " +
                    "WHERE major = ? AND num = ? AND class = ? AND subject = ? AND course = ? AND score = ? AND time = ? AND lectureroom = ?";

            PreparedStatement queryPs = s.conn.prepareStatement(query);
            queryPs.setString(1, major);
            queryPs.setString(2, num);
            queryPs.setString(3, classroom);
            queryPs.setString(4, subject);
            queryPs.setString(5, course);
            queryPs.setString(6, score);
            queryPs.setString(7, time);
            queryPs.setString(8, lectureroom);

            ResultSet resultSet = queryPs.executeQuery();

            // 결과 집합이 비어있지 않으면 레코드가 존재함
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // 오류 발생 시 false 반환
        }
    }
    
//    public static void main(String[] args) {
//    	new Enrolment();
//    }
    
}
