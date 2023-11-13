package code;

import javax.swing.JButton;
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
		
		
	}
	
}
