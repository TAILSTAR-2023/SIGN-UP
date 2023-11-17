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

public class Student_Login extends InheritanceFrame {

    private JButton backbtn = new JButton();
    private JButton loginbtn = new JButton();

    private JTextField idtx = new JTextField();
    private JPasswordField pwtx = new JPasswordField();

    private String loggedInUserId;  // 추가: 로그인한 사용자의 아이디를 저장하기 위한 변수

    public Student_Login() {
        super("STUDENT LOGIN", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setLayout(null);

        JButtonStyle(backbtn, 20, 35, 150, 65, "Student_Login_Screen_Back_Button.png");
        JButtonStyle(loginbtn, 400, 530, 450, 65, "Student_LogIn_Screen_Login_Button.png");

        TextFieldStyle(idtx, 285);
        TextFieldStyle(pwtx, 410);

        JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Student_Login_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);

        backbtn.addActionListener(e -> {
            dispose();
            new Student().setVisible(true);
        });

        loginbtn.addActionListener(e -> {
            String id = idtx.getText();
            String pw = new String(pwtx.getPassword());

            DB_connection dbConnection;
            try {
                dbConnection = new DB_connection();

                // 아이디와 비밀번호를 검증하기 위한 SQL 쿼리
                String sql = "SELECT id, pw FROM signup.student_join WHERE id = ?";

                PreparedStatement ps = dbConnection.conn.prepareStatement(sql);
                ps.setString(1, id);

                ResultSet resultSet = ps.executeQuery();

                if (resultSet.next()) {
                    String storedPw = resultSet.getString("pw");

                    // DB에 저장된 비밀번호와 입력한 비밀번호 비교
                    if (pw.equals(storedPw)) {
                        // 로그인 성공 시 세션을 유지하기 위해 loggedInUserId에 사용자 아이디 저장
                        loggedInUserId = id;
                        JOptionPane.showMessageDialog(this, "로그인 성공", "알림", JOptionPane.INFORMATION_MESSAGE);
                        // 로그인 성공한 경우, 다음 작업을 수행하거나 메인 화면으로 이동
                        dispose();
                        new Student().setVisible(true);
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
    private void JButtonStyle(JButton button, int x, int y, int w, int h, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName)));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBounds(x, y, w, h);
        add(button);
    }

    // 텍스트필드 설정 메소드
    private JTextField TextFieldStyle(JTextField textField, int x) {
        textField.setBounds(500, x, 450, 55);
        textField.setBackground(Color.decode("#DFD4D6"));
        textField.setFont(new Font("SUITE", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        add(textField);
        return textField;
    }

    // 추가: 로그인한 사용자의 아이디를 반환하는 메소드
    public String getLoggedInUserId() {
        return loggedInUserId;
    }
}
