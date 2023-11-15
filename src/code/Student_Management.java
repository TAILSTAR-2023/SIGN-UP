package code;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Student_Management extends InheritanceFrame {
	
	private JButton completebtn = new JButton();
	
	private JTextField majortx = new JTextField();
//	private JTextField courseNtx = new JTextField();
//	private JTextField subjectNtx = new JTextField();
//	private JTextField professorNtx = new JTextField();
	private JTextField studentNtx = new JTextField();
	private JTextField gradetx = new JTextField();
	private JTextField sIDtx = new JTextField();
//	private JTextField classificationtx = new JTextField();
	
	public Student_Management() {
		super("STUDENT MANAGEMENT", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null);
    	
    	JButtonStyle(completebtn, 920, 20, "Student_Management_Screen_Complete_Button.png");
    	
    	TextFieldStyle(majortx, 230);			
//		TextFieldStyle(courseNtx, 240);			
//		TextFieldStyle(subjectNtx, 300);		
//		TextFieldStyle(professorNtx, 360);		
		TextFieldStyle(studentNtx, 300);		
		TextFieldStyle(gradetx, 370);			
		TextFieldStyle(sIDtx, 440);		
//		TextFieldStyle(classificationtx, 605);	
		
		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Student_Management_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);

        completebtn.addActionListener(e -> {
        	// 완료 : DB 테이블에 값 저장, 메인화면으로 이동
        	String major = majortx.getText();
//        	String course = courseNtx.getText();
        	String student = studentNtx.getText();
        	String grade = gradetx.getText();
        	String sID = sIDtx.getText();
        	
        	DB_connection s;
        	
        	try {
        	    s = new DB_connection();
        	    String sql = "INSERT INTO signup.student_management(major, name, grade, sID) VALUES (?, ?, ?, ?)";
        	    PreparedStatement ps = s.conn.prepareStatement(sql);

        	    ps.setString(1, major);
        	    ps.setString(2, student);
        	    ps.setString(3, grade);
        	    ps.setString(4, sID);

        	    s = new DB_connection();    

        	    int affectedRows = ps.executeUpdate();

        	    if (affectedRows > 0) {
        	        System.out.println("저장 완료");
        	        JOptionPane.showMessageDialog(this, "저장 완료", "알림", JOptionPane.INFORMATION_MESSAGE);
        	    } else {
        	        System.out.println("레코드 삽입 실패");
        	        JOptionPane.showMessageDialog(this, "레코드 삽입 실패", "오류", JOptionPane.ERROR_MESSAGE);
        	    }

        	} catch (Exception e1) {
        	    e1.printStackTrace();
        	    System.out.println(e1.toString());
        	    JOptionPane.showMessageDialog(this, "오류 발생: " + e1.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        	}
        	
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
        button.setBounds(x, y, 500, 65); // 버튼 위치 나타내는 x, y좌표와 버튼 크기 설정인 가로 500, 세로 65
        add(button); // 프레임 추가
    }
    
    // 텍스트필드 설정 메소드
    private JTextField TextFieldStyle(JTextField textField, int x) {
        textField.setBounds(550, x, 495, 45);
        textField.setBackground(Color.decode("#C8C9DA"));
        textField.setFont(new Font("SUITE", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        add(textField);
        return textField;
    }

}
