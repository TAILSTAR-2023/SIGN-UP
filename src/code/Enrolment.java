package code;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Enrolment extends InheritanceFrame {

    private String loggedInUserId;

    private JButton backbtn = new JButton();
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

    // Enrolment 클래스의 생성자
    public Enrolment() {
        // 상위 클래스(InheritanceFrame)의 생성자를 호출합니다.
        super("ENROLMENT", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // 버튼 및 텍스트 필드 초기화 및 스타일링
        JButtonStyle(backbtn, 40, 20, 150, 65, "Enrolment_Screen_Back_Button.png");
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

        // 배경 이미지 설정
        JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Enrolment_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);

        // 카탈로그 버튼 활성화
        catalogbtn.setEnabled(true);

        // 뒤로 가기 버튼 액션 리스너
        backbtn.addActionListener(e -> {
            dispose();
            new Student().setVisible(true);
        });

        // 새로고침 버튼 액션 리스너
        refreshbtn.addActionListener(e -> {
            majortx.setText("");
            numtx.setText("");
            classtx.setText("");
            subjecttx.setText("");
            coursetx.setText("");
            scoretx.setText("");
            timetx.setText("");
            lectureroomtx.setText("");
        });

        // 카탈로그 버튼 액션 리스너
        catalogbtn.addActionListener(e -> {
            System.out.println("Catalog Button Clicked!");
            dispose();

            try {
                // Timetable_Catalog 창 열기
                Timetable_Catalog timetableList = new Timetable_Catalog();
                timetableList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                timetableList.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "오류 발생: " + ex.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 제출 버튼 액션 리스너
        submitbtn.addActionListener(e -> {
            String major = majortx.getText();
            String num = numtx.getText();
            String classroom = classtx.getText();
            String subject = subjecttx.getText();
            String course = coursetx.getText();
            String score = scoretx.getText();
            String time = timetx.getText();
            String lectureroom = lectureroomtx.getText();

            DB_connection s = null;

            try {
                s = new DB_connection();

                if (checkIfRecordExists(s, major, num, classroom, subject, course, score, time, lectureroom)) {
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
                            int choice = showConfirmationDialog();
                            handleConfirmationChoice(choice);
                        } else {
                            JOptionPane.showMessageDialog(Enrolment.this, "수강신청 실패", "오류", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(Enrolment.this, "강의 정보 없음", "알림", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Enrolment.this, "오류 발생: " + e1.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                System.out.println(e1.toString());
            }
        });
    }

    private void JButtonStyle(JButton button, int x, int y, int w, int h, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName)));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBounds(x, y, w, h);
        add(button);
    }

    private JTextField TextFieldStyle(JTextField textField, int x, int y) {
        textField.setBounds(x, y, 350, 55);
        textField.setBackground(Color.decode("#DBD0D2"));
        textField.setFont(new Font("SUITE", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        add(textField);
        return textField;
    }

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

            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private int showConfirmationDialog() {
        Object[] options = {"계속 진행", "시간표"};
        return JOptionPane.showOptionDialog(
                Enrolment.this,
                "수강신청이 완료되었습니다. 더 수강신청을 진행하시겠습니까?",
                "알림",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
    }

    private void handleConfirmationChoice(int choice) {
        switch (choice) {
            case 0:
                majortx.setText("");
                numtx.setText("");
                classtx.setText("");
                subjecttx.setText("");
                coursetx.setText("");
                scoretx.setText("");
                timetx.setText("");
                lectureroomtx.setText("");
                break;
            case 1:
                showTimetable();
                break;
            default:
                break;
        }
    }

    private String getUserName() {
        try {
            DB_connection dbConnection = new DB_connection();
            return dbConnection.getLoggedInUserId();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "오류 발생: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // 더 이상 사용하지 않는 메서드이므로 주석 처리
    // private String getLoggedInUserId() {
//         return loggedInUserId;
    // }


    private String getLoggedInUserId() {
        loggedInUserId = getUserName(); // 이 부분을 추가하여 loggedInUserId 값을 설정
        System.out.println(loggedInUserId);
        return loggedInUserId;
    }

    private Vector<Vector<Object>> getTimetableData(String userName) throws SQLException {
        DB_connection s = null;

        try {
            s = new DB_connection();
            String query = "SELECT * FROM signup.timetable WHERE major = ?";

            try (PreparedStatement queryPs = s.conn.prepareStatement(query)) {
                queryPs.setString(1, userName);

                ResultSet resultSet = queryPs.executeQuery();

                Vector<Vector<Object>> data = new Vector<>();

                while (resultSet.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(resultSet.getString("major"));
                    row.add(resultSet.getString("num"));
                    row.add(resultSet.getString("class"));
                    row.add(resultSet.getString("subject"));
                    row.add(resultSet.getString("course"));
                    row.add(resultSet.getString("score"));
                    row.add(resultSet.getString("time"));
                    row.add(resultSet.getString("lectureroom"));
                    data.add(row);
                }

                return data;
            }
        } finally {
            if (s != null) {
                s.closeConnection();
            }
        }
    }

    private void displayTimetable(Vector<Vector<Object>> timetableData) {
        JFrame timetableFrame = new JFrame();
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable timetableTable = new JTable(tableModel);

        tableModel.addColumn("학과전공");
        tableModel.addColumn("학수번호");
        tableModel.addColumn("분반");
        tableModel.addColumn("교과목명");
        tableModel.addColumn("이수구분");
        tableModel.addColumn("학점");
        tableModel.addColumn("요일/시간");
        tableModel.addColumn("강의실");

        for (Vector<Object> row : timetableData) {
            tableModel.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(timetableTable);
        timetableFrame.add(scrollPane);

        // 시간표 창의 제목을 로그인된 아이디로 설정
        timetableFrame.setTitle("시간표 - " + getLoggedInUserId());

        timetableFrame.setSize(600, 400);
        timetableFrame.setLocationRelativeTo(null);
        timetableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        timetableFrame.setVisible(true);
    }

    private void showTimetable() {
        try {
            String userId = getLoggedInUserId();
            Vector<Vector<Object>> timetableData = getTimetableData(userId);
            displayTimetable(timetableData);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "오류 발생: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

}
