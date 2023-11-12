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

public class Timetable_List extends InheritanceFrame {
	
	private JButton listbtn = new JButton();
	
	private JTable timetableTable;
	
	public Timetable_List() throws Exception {
		super("TIMETABLE LIST", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		JButtonStyle(listbtn, 920, 20, "Timetable_List_Screen_Back_Button.png");
		
		 // 테이블 모델을 생성하고 열 이름을 설정합니다.
        String[] columnNames = {"전공", "학번", "분반", "과목", "강좌", "점수", "시간", "강의실"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        timetableTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(timetableTable);
        scrollPane.setBounds(100, 120, 1100, 500);
        add(scrollPane);
        
        // 행 높이를 설정
        int rowHeight = 20;
        timetableTable.setRowHeight(rowHeight);
                
        // JTable의 TableCellRenderer를 설정
        timetableTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // 글꼴 설정
                c.setFont(new Font("SUITE-Medium", Font.PLAIN, 13));
                
                // 셀 내용 가운데 정렬
                ((DefaultTableCellRenderer) c).setHorizontalAlignment(JLabel.CENTER);
                
                // 셀 배경색 설정
                if (isSelected) {
//                    c.setBackground(table.getSelectionBackground());
                    c.setBackground(Color.decode("#F2EDFF"));
                } else {
                    c.setBackground(Color.decode("#F2EDFF"));
                }
                
                return c;
            }
        });

        DB_connection s;
        
        // 데이터베이스에서 테이블을 데이터로 채웁니다.
        try {
            s = new DB_connection();

            String query = "SELECT major, num, class, subject, course, score, time, lectureroom FROM signup.timetable";
            PreparedStatement preparedStatement = s.conn.prepareStatement(query);
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

            s = new DB_connection();
            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
		
		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Timetable_List_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);
        
        listbtn.addActionListener(e -> {
        	dispose();
        	new Enrolment().setVisible(true);
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
