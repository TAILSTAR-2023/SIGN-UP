package code;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Lecture_Management extends InheritanceFrame {
	
	private JButton backbtn = new JButton();
	private JButton completebtn = new JButton();
	
	private JTextField majortx = new JTextField();
	private JTextField courseNtx = new JTextField();
	private JTextField subjectNtx = new JTextField();
	private JTextField professorNtx = new JTextField();
	private JTextField gradetx = new JTextField();
	private JTextField divisiontx = new JTextField();
	private JTextField classificationtx = new JTextField();
	
    public Lecture_Management() {
    	super("LECTURE MANAGEMENT", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
    	setLayout(null);
    	
    	JButtonStyle(backbtn, 755, 20, "Enrolment_Management_Screen_Back_Button.png");
    	JButtonStyle(completebtn, 920, 20, "Lecture_Management_Screen_Complete_Button.png");
    	
    	TextFieldStyle(majortx, 180);			// 학과전공
		TextFieldStyle(courseNtx, 240);			// 학수번호
		TextFieldStyle(subjectNtx, 300);		// 교과목명
		TextFieldStyle(professorNtx, 360);		// 교수명
		TextFieldStyle(gradetx, 420);			// 학년
		TextFieldStyle(divisiontx, 480);		// 분반
		TextFieldStyle(classificationtx, 545);	// 이수구분
		
		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Lecture_Management_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);
        
        backbtn.addActionListener(e -> {
        	dispose();
        	new Professor().setVisible(true);
        });
        
        completebtn.addActionListener(e -> {
        	// 완료 : DB 테이블에 값 저장, 메인화면으로 이동
        	String major = majortx.getText();					// 학과전공
        	String course = courseNtx.getText();				// 학수번호
        	String subject = subjectNtx.getText();				// 교과목명
        	String professor = professorNtx.getText();			// 교수명
        	String grade = gradetx.getText();					// 학년
        	String division = divisiontx.getText();				// 분반
        	String classification = classificationtx.getText();	// 이수구분
        	
        	DB_connection s;
        	
        	try {
        		s = new DB_connection();
        		String sql = "INSERT INTO signup.timetable(major, num, class, subject, course, score, time, lectureroom) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        	PreparedStatement ps = s.conn.prepareStatement(sql);
	        	
	        	ps.setString(1, major);				// 학과전공
	        	ps.setString(2, course);			// 학수번호
	        	ps.setString(3, division);
	        	ps.setString(4, subject);
	        	ps.setString(5, classification);
	        	// TODO : 강의 관리 페이지와 시간표 페이지의 글이 일치하도록 변경
//	        	ps.setString(7, )
	        	// ps.setString(3, subject);			// 교과목명
	        	ps.setString(4, professor);			// 교수명
	        	ps.setString(5, grade);				// 학년
	        	ps.setString(6, division);			// 분반
	        	ps.setString(7, classification);	// 이수구분

	        	// 전공, 번호, 반, 교과목명, 이수구분, 학점, 요일/시간, 강의실
	        	
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
    
}
