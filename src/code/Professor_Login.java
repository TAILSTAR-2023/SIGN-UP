package code;

import java.awt.Color;
import java.awt.Font;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Professor_Login extends InheritanceFrame {
	
	private JButton backBtn = new JButton();
	private JButton loginBtn = new JButton();
	
	private JTextField idTx = new JTextField();
	private JPasswordField pwTx = new JPasswordField();
	
	public Professor_Login() {
		super("PROFESSOR LOGIN", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null);
		
		// 버튼 설정
		setupButton(backBtn, 10, 35, 150, 65, "Professor_Join_Screen_Back_Button.png");
		setupButton(loginBtn, 400, 530, 450, 65, "Professor_LogIn_Screen_Login_Button.png");
		
		// 텍스트 필드 설정
		setupTextField(idTx, 285);
		setupTextField(pwTx, 410);
		
		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Professor_Login_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);
        
        backBtn.addActionListener(e -> {
        	dispose();
        	new Professor().setVisible(true);
        });
        
        loginBtn.addActionListener(e -> {
        	String id = idTx.getText();
            String pw = new String(pwTx.getPassword());

            DB_connection dbConnection;
            try {
                dbConnection = new DB_connection();

                // 아이디와 비밀번호를 검증하기 위한 SQL 쿼리
                String sql = "SELECT id, pw FROM signup.professor_join WHERE id = ?";

                PreparedStatement ps = dbConnection.conn.prepareStatement(sql);
                ps.setString(1, id);

                ResultSet resultSet = ps.executeQuery();

                if (resultSet.next()) {
                    String storedPw = resultSet.getString("pw");

                    // DB에 저장된 비밀번호와 입력한 비밀번호 비교
                    if (pw.equals(storedPw)) {
                        JOptionPane.showMessageDialog(this, "로그인 성공", "알림", JOptionPane.INFORMATION_MESSAGE);
                        // 로그인 성공한 경우, 다음 작업을 수행하거나 메인 화면으로 이동
                        dispose();
                        new Professor().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "회원가입 정보가 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println(ex.toString());
            }
        	
        });
	}
	
	// 버튼 설정 메소드
    private void setupButton(JButton button, int x, int y, int w, int h, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName))); // 버튼 이미지 아이콘 설정
        button.setBorderPainted(false); // 버튼 테두리 제거 
        button.setContentAreaFilled(false); // 버튼 내부 영역 투명하게 설정 → 배경색상표시X
        button.setFocusPainted(false); // 포커스 받을 때 테두리 표시되지 않도록 설정
        button.setBounds(x, y, w, h); // 버튼 위치 나타내는 x, y좌표와 버튼 크기 설정인 가로 w, 세로 h
        add(button); // 프레임 추가
    }
    
    // 텍스트필드 설정 메소드
    private JTextField setupTextField(JTextField textField, int x) {
        textField.setBounds(500, x, 450, 55);
        textField.setBackground(Color.decode("#D1D9E4"));
        textField.setFont(new Font("SUITE", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        add(textField);
        return textField;
    }

}
