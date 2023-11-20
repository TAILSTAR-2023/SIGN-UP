package code;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Timetable_Catalog extends InheritanceFrame {

    private JButton backButton = new JButton();
    private JTable timetableTable;

    public Timetable_Catalog() {
        super("TIMETABLE LIST", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // 버튼과 테이블 초기화
        setupButton(backButton, 920, 20, "Timetable_List_Screen_Back_Button.png");
        initializeTable();

        // 레이아웃 구성
        setLayout(null);
        JScrollPane scrollPane = new JScrollPane(timetableTable);
        scrollPane.setBounds(100, 120, 1100, 500);
        add(scrollPane);

        // 이벤트 리스너 등록
        backButton.addActionListener(e -> {
            System.out.println("Back Button Clicked!"); // 디버깅 출력문 추가
            dispose();
            new Enrolment().setVisible(true);
        });

        // 배경 이미지 추가
        JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Timetable_List_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);
    }

    private void initializeTable() {
        // 테이블 모델 생성 및 열 이름 설정
        String[] columnNames = {"전공", "학번", "분반", "과목", "강좌", "점수", "시간", "강의실"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        timetableTable = new JTable(tableModel);

        // 테이블 스타일 설정
        timetableTable.setRowHeight(20);
        timetableTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("SUITE-Medium", Font.PLAIN, 13));
                ((DefaultTableCellRenderer) c).setHorizontalAlignment(JLabel.CENTER);
                c.setBackground(isSelected ? Color.decode("#F2EDFF") : Color.decode("#F2EDFF"));
                return c;
            }
        });

        // 데이터베이스에서 테이블 데이터로 채우기
        try {
            DB_connection dbConnection = new DB_connection();
            String query = "SELECT major, num, class, subject, course, score, time, lectureroom FROM signup.timetable_list";
            PreparedStatement preparedStatement = dbConnection.conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String major = resultSet.getString("major");
                String num = resultSet.getString("num");
                String classroom = resultSet.getString("class");
                String subject = resultSet.getString("subject");
                String course = resultSet.getString("course");
                String score = resultSet.getString("score");
                String time = resultSet.getString("time");
                String lectureRoom = resultSet.getString("lectureroom");

                Object[] rowData = {major, num, classroom, subject, course, score, time, lectureRoom};
                tableModel.addRow(rowData);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.toString());
        }
    }

    // 버튼 설정 메소드
    private void setupButton(JButton button, int x, int y, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName)));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBounds(x, y, 500, 65);
        add(button);
    }
}
