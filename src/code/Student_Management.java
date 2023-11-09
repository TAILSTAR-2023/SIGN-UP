package code;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Student_Management extends InheritanceFrame {
	
	private JButton backbtn = new JButton();
	private JButton completebtn = new JButton();
	
	private JTextField majortx = new JTextField();
	private JTextField courseNtx = new JTextField();
	private JTextField subjectNtx = new JTextField();
	private JTextField professorNtx = new JTextField();
	private JTextField studentNtx = new JTextField();
	private JTextField gradetx = new JTextField();
	private JTextField divisiontx = new JTextField();
	private JTextField classificationtx = new JTextField();
	
	public Student_Management() {
		super("STUDENT MANAGEMENT", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null);
    	
		JButtonStyle(backbtn, 755, 20, "Student_Management_Screen_Back_Button.png");
    	JButtonStyle(completebtn, 920, 20, "Student_Management_Screen_Complete_Button.png");
    	
    	TextFieldStyle(majortx, 180);			// 학과전공
		TextFieldStyle(courseNtx, 240);			// 학수번호
		TextFieldStyle(subjectNtx, 300);		// 교과목명
		TextFieldStyle(professorNtx, 360);		// 교수명
		TextFieldStyle(studentNtx, 420);		// 학생명
		TextFieldStyle(gradetx, 480);			// 학년
		TextFieldStyle(divisiontx, 540);		// 분반
		TextFieldStyle(classificationtx, 605);	// 이수구분
		
		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Student_Management_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);
        
        backbtn.addActionListener(e -> {
        	dispose();
        	new Professor().setVisible(true);
        });
        
        completebtn.addActionListener(e -> {
        	// 완료 : DB 테이블에 값 저장, 메인화면으로 이동
        	String major = majortx.getText();
        	String course = courseNtx.getText();
        	String subject = subjectNtx.getText();
        	String professor = professorNtx.getText();
        	String student = studentNtx.getText();
        	String grade = gradetx.getText();
        	String division = divisiontx.getText();
        	String classification = classificationtx.getText();
        	
        	DB_connection s;
        	
        	try {
        		s = new DB_connection();
        		String sql = "INSERT INTO signup.student_management(major, subnum, subject, professor, sname, grade, class, course) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        	PreparedStatement ps = s.conn.prepareStatement(sql);
	        	
	        	ps.setString(1, major);
	        	ps.setString(2, course);
	        	ps.setString(3, subject);
	        	ps.setString(4, professor);
	        	ps.setString(5, student);
	        	ps.setString(6, grade);
	        	ps.setString(7, division);
	        	ps.setString(8, classification);
	        	
				s = new DB_connection();        	
				ps.executeUpdate();
				
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println(e1.toString());
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
        textField.setBounds(550, x, 495, 43);
        textField.setBackground(Color.decode("#C8C9DA"));
        textField.setFont(new Font("SUITE", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        add(textField);
        return textField;
    }

}
