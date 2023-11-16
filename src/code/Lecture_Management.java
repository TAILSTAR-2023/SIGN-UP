package code;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        TextFieldStyle(majortx, 180);
        TextFieldStyle(numtx, 240);
        TextFieldStyle(classtx, 300);
        TextFieldStyle(subjecttx, 360);
        TextFieldStyle(coursetx, 420);
        TextFieldStyle(scoretx, 480);
        TextFieldStyle(timetx, 540);
        TextFieldStyle(lectureroomtx, 600);

        JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Lecture_Management_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);

        // registrationbtn 액션 리스너
        registrationbtn.addActionListener(e -> {
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

                // 수동 커밋 모드로 전환
                s.conn.setAutoCommit(false);

                // 레코드가 이미 존재하는지 확인
                int recordIdToUpdate = findRecordId(s, major, num, classroom, subject, course);

                if (recordIdToUpdate > 0) {
                    // 레코드가 이미 존재하면 업데이트 수행
                    String updateSql = "UPDATE signup.timetable SET major = ?, num = ?, class = ?, subject = ?, course = ?, score = ?, time = ?, lectureroom = ? WHERE id = ?";

                    try (PreparedStatement updatePs = s.conn.prepareStatement(updateSql)) {
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

                        if (updatedRows > 0) {
                            // 커밋
                            s.conn.commit();
                            JOptionPane.showMessageDialog(this, "수정 완료", "알림", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "레코드 업데이트 실패", "오류", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    // 레코드가 존재하지 않으면 새로운 레코드 추가
                    String insertSql = "INSERT INTO signup.timetable(major, num, class, subject, course, score, time, lectureroom) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement insertPs = s.conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
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
                            // 생성된 키(레코드 ID) 가져오기
                            try (ResultSet generatedKeys = insertPs.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    int lastInsertedRecordId = generatedKeys.getInt(1);
                                    // 커밋
                                    s.conn.commit();
                                    JOptionPane.showMessageDialog(this, "저장 완료, 삽입된 레코드 ID: " + lastInsertedRecordId, "알림", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "레코드 삽입 실패", "오류", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "오류 발생: " + e1.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                System.out.println(e1.toString());
            } finally {
                try {
                    // 자동 커밋 모드로 다시 전환
                    if (s != null && s.conn != null) {
                        s.conn.setAutoCommit(true);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            dispose();
            new Professor().setVisible(true);
        });

        // correctionbtn 액션 리스너
        correctionbtn.addActionListener(e -> {
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

                // 수동 커밋 모드로 전환
                s.conn.setAutoCommit(false);

                // 새로운 레코드 추가
                String insertSql = "INSERT INTO signup.timetable_list(major, num, class, subject, course, score, time, lectureroom) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
                        // 커밋
                        s.conn.commit();
                        JOptionPane.showMessageDialog(this, "수정 완료", "알림", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "레코드 삽입 실패", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "오류 발생: " + e1.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                System.out.println(e1.toString());
            } finally {
                try {
                    // 자동 커밋 모드로 다시 전환
                    if (s != null && s.conn != null) {
                        s.conn.setAutoCommit(true);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            dispose();
            new Professor().setVisible(true);
        });
    }

    private void JButtonStyle(JButton button, int x, int y, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName)));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBounds(x, y, 450, 65);
        add(button);
    }

    private JTextField TextFieldStyle(JTextField textField, int x) {
        textField.setBounds(550, x, 450, 55);
        textField.setBackground(Color.decode("#D1D9E4"));
        textField.setFont(new Font("SUITE", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        add(textField);
        return textField;
    }

    // 실제 데이터베이스와 상호작용하여 레코드 ID를 찾는 메소드
    private int findRecordId(DB_connection s, String major, String num, String classroom, String subject, String course) {
        try {
            String query = "SELECT id FROM signup.timetable " +
                    "WHERE major = ? AND num = ? AND class = ? AND subject = ? AND course = ?";

            PreparedStatement queryPs = s.conn.prepareStatement(query);

            // 매개변수로 전달된 값을 설정
            queryPs.setString(1, major);
            queryPs.setString(2, num);
            queryPs.setString(3, classroom);
            queryPs.setString(4, subject);
            queryPs.setString(5, course);

            // SQL 쿼리를 출력
            System.out.println("Query: " + queryPs.toString());

            // SQL 쿼리를 실행하고 결과 집합을 얻음
            ResultSet resultSet = queryPs.executeQuery();

            if (resultSet.next()) {
                int recordId = resultSet.getInt("id");
                // 반환된 레코드 ID 출력
                System.out.println("Record ID: " + recordId);
                return recordId; // 레코드 ID 반환
            } else {
                System.out.println("Record not found");
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
