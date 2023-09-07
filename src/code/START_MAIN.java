package code;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class START_MAIN extends InheritanceFrame {
    private JButton joinbtn = new JButton(new ImageIcon(getClass().getResource("/image/Main_Join_Button.png")));
    private JButton loginbtn = new JButton(new ImageIcon(getClass().getResource("/image/Main_Login_Button.png")));
    
    public START_MAIN() {
        super("SIGN - UP", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setLayout(null);
        
//        joinbtn.setBorderPainted(false);
//        joinbtn.setContentAreaFilled(false);
//        joinbtn.setFocusPainted(false);
//        joinbtn.setBounds(415, 400, 450, 55);
//        add(joinbtn);
        
        JButtonStyle(joinbtn, 415, 400, "Main_Join_Button.png");
        JButtonStyle(loginbtn, 415, 500, "Main_Login_Button.png");
        
//        loginbtn.setBorderPainted(false);
//        loginbtn.setContentAreaFilled(false);
//        loginbtn.setFocusPainted(false);
//        loginbtn.setBounds(415, 500, 450, 55);
//        
//        add(joinbtn);
//        add(loginbtn);
        
        JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/MainScreen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);
    }
    
    private void JButtonStyle(JButton button, int x, int y, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName)));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBounds(x, y, 450, 55);
        add(button);
    }
    
}

//public class START_MAIN extends InheritanceFrame {
//	
////	private ImageIcon joinbtnPath = new ImageIcon(getClass().getResource("image/Main_Join_Button.png"));
////	private ImageIcon loginbtnPath = new ImageIcon(getClass().getResource("image/Main_Login_Button.png"));
//	private JButton joinbtn = new JButton(new ImageIcon("image/Main_Join_Button.png"));
//	private JButton loginbtn = new JButton(new ImageIcon("image/Main_Login_Button.png"));
//	
//	public START_MAIN() {
//		super("SIGN - UP", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
////		setBackgroundImage("image/Main_Screen.png");
//		
////		joinbtn.setIcon(joinbtnPath);
//		joinbtn.setBorderPainted(false); // 버튼 테두리 설정
//		joinbtn.setContentAreaFilled(false);
//		joinbtn.setFocusPainted(false);
////		joinbtn.setOpaque(false);
////		joinbtn.setBounds(415, 400, 450, 55);
////		add(joinbtn);
//		
////		loginbtn.setIcon(loginbtnPath);
////		loginbtn.setBounds(715, 700, 450, 55);
//		loginbtn.setBorderPainted(false); // 버튼 테두리 설정
//		loginbtn.setContentAreaFilled(false);
//		loginbtn.setFocusPainted(false);
////		loginbtn.setOpaque(false);
//		
//		joinbtn.setBounds(0, 400, 450, 55);
//		add(joinbtn);
//		
//		loginbtn.setBounds(0, 700, 450, 55);
//		add(loginbtn);
//		
//		JLabel lb = new JLabel(getIcon("image/Main_Screen.png", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));
//		lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
//		add(lb);
//		
//		
//	}
//
//}
