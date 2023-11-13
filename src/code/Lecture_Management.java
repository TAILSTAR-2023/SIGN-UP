package code;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Lecture_Management extends InheritanceFrame {

	private JButton correctionbtn = new JButton();
	private JButton registrationbtn = new JButton();
	
	private JTextField majortx = new JTextField();
	private JTextField numtx = new JTextField();
	private JTextField classtx = new JTextField();
	private JTextField subjecttx = new JTextField();
	private JTextField coursetx = new JTextField();
	private JTextField scoretx = new JTextField();
	private JTextField timetx = new JTextField();
	private JTextField lectureroomtx = new JTextField();
	
	public Lecture_Management() {
		super("LECTURE MANAGEMENT", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null);
		
		JButtonStyle(correctionbtn, 755, 20, "Enrolment_Management_Screen_Correction_Button.png");
        JButtonStyle(registrationbtn, 920, 20, "Lecture_Management_Screen_Registration_Button.png");

        TextFieldStyle(majortx, 180);            // 학과전공
        TextFieldStyle(numtx, 240);              // 학수번호
        TextFieldStyle(classtx, 300);            // 분반
        TextFieldStyle(subjecttx, 360);          // 교과목명
        TextFieldStyle(coursetx, 420);           // 이수구분
        TextFieldStyle(scoretx, 480);            // 학점
        TextFieldStyle(timetx, 540);             // 요일/시간
        TextFieldStyle(lectureroomtx, 600);      // 강의실

        JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Lecture_Management_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);

        correctionbtn.addActionListener(e -> {
            // 텍스트 필드에서 값 가져오기
            String major = majortx.getText(); // 학과전공
            String num = numtx.getText(); // 학수번호
            String classroom = classtx.getText(); // 분반
            String subject = subjecttx.getText(); // 교과목명
            String course = coursetx.getText(); // 이수구분
            String score = scoretx.getText(); // 학점
            String time = timetx.getText(); // 요일/시간
            String lectureroom = lectureroomtx.getText(); // 강의실

            DB_connection s;

            // 고유 식별자를 통해 업데이트할 레코드 식별 필요, 예: 'id'
            int recordIdToUpdate = findRecordId(major, num, classroom, subject, course);

            if (recordIdToUpdate > 0) {
                // 레코드 찾음, 업데이트 진행
                try {
                    s = new DB_connection();
                    String updateSql = "UPDATE signup.timetable SET major = ?, num = ?, class = ?, subject = ?, course = ?, score = ?, time = ?, lectureroom = ? WHERE id = ?";

                    PreparedStatement updatePs = s.conn.prepareStatement(updateSql);

                    updatePs.setString(1, major);
                    updatePs.setString(2, num);
                    updatePs.setString(3, classroom);
                    updatePs.setString(4, subject);
                    updatePs.setString(5, course);
                    updatePs.setString(6, score);
                    updatePs.setString(7, time);
                    updatePs.setString(8, lectureroom);
                    updatePs.setInt(9, recordIdToUpdate); // 업데이트할 레코드 ID 설정

                    int updatedRows = updatePs.executeUpdate();
                    s = new DB_connection();        	
    				updatePs.executeUpdate();

                    if (updatedRows > 0) {
                        JOptionPane.showMessageDialog(this, "수정완료", "알림", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "레코드 업데이트 실패", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "오류 발생: " + e1.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e1) {
					e1.printStackTrace();
				}
            } else {
                JOptionPane.showMessageDialog(this, "레코드를 찾을 수 없습니다. 업데이트 중단.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }

            dispose();
            new Professor().setVisible(true);
        });

        registrationbtn.addActionListener(e -> {
            // 완료: DB 테이블에 값 저장, 메인 화면으로 이동
            String major = majortx.getText();
            String num = numtx.getText();
            String classroom = classtx.getText();
            String subject = subjecttx.getText();
            String course = coursetx.getText();
            String score = scoretx.getText();
            String time = timetx.getText();
            String lectureroom = lectureroomtx.getText();

            DB_connection s;

            try {
                s = new DB_connection();
                String sql = "INSERT INTO signup.timetable(major, num, class, subject, course, score, time, lectureroom) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = s.conn.prepareStatement(sql);

                ps.setString(1, major);
                ps.setString(2, num);
                ps.setString(3, classroom);
                ps.setString(4, subject);
                ps.setString(5, course);
                ps.setString(6, score);
                ps.setString(7, time);
                ps.setString(8, lectureroom);

                s = new DB_connection();
                ps.executeUpdate();

            } catch (Exception e1) {
                e1.printStackTrace();
                System.out.println(e1.toString());
            }

            // 전공, 번호, 반, 교과목명, 이수구분, 학점, 요일/시간, 강의실

            dispose();
            new Professor().setVisible(true);
        });

    }

    // 버튼 설정 메소드
    private void JButtonStyle(JButton button, int x, int y, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName))); // 버튼 이미지 아이콘 설정
        button.setBorderPainted(false); // 버튼 테두리 제거
        button.setContentAreaFilled(false); // 버튼 내부 영역 투명하게 설정 → 배경색상표시X
        button.setFocusPainted(false); // 포커스 받을 때 테두리 표시되지 않도록 설정
        button.setBounds(x, y, 450, 65); // 버튼 위치 나타내는 x, y좌표와 버튼 크기 설정인 가로 450, 세로 65
        add(button); // 프레임 추가
    }

    // 텍스트필드 설정 메소드
    private JTextField TextFieldStyle(JTextField textField, int x) {
        textField.setBounds(550, x, 450, 55);
        textField.setBackground(Color.decode("#D1D9E4"));
        textField.setFont(new Font("SUITE", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        add(textField);
        return textField;
    }

    // 실제 데이터베이스와 상호작용하여 레코드 ID를 찾는 메소드
    private int findRecordId(String major, String num, String classroom, String subject, String course) {
        DB_connection s;
        
        try {
            s = new DB_connection();
            String query = "SELECT id FROM timetable " +
                    "WHERE major = ? AND num = ? AND class = ? AND subject = ? AND course = ?";
            
            PreparedStatement queryPs = s.conn.prepareStatement(query);
            
            // 매개변수로 전달된 값을 설정
            queryPs.setString(1, major);
            queryPs.setString(2, num);
            queryPs.setString(3, classroom);
            queryPs.setString(4, subject);
            queryPs.setString(5, course);

            // SQL 쿼리를 실행하고 결과 집합을 얻음
            ResultSet resultSet = queryPs.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id"); // 레코드 ID 반환
            } else {
                return -1; // 해당 레코드를 찾지 못한 경우
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1; // 오류 발생 시 -1 반환
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // 기타 예외 발생 시 -1 반환
        }
	}
	
}
