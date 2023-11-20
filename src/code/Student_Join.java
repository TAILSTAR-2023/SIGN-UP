package code;

import java.awt.Color;
import java.awt.Font;

import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Student_Join extends InheritanceFrame {

    private JButton backBtn = new JButton();
    private JButton completeBtn = new JButton();

    private JTextField nameTx = new JTextField();
    private JTextField idTx = new JTextField();
    private JPasswordField pwTx = new JPasswordField();
    private JPasswordField pwCkTx = new JPasswordField();
    private JFormattedTextField telTx = new JFormattedTextField(); // 숫자 입력을 위해
    private JTextField emailTx = new JTextField();

    public Student_Join() {
        super("STUDENT JOIN", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setLayout(null);

        setupButton(backBtn, 20, 35, 150, 65, "Student_Join_Screen_Back_Button.png");
        setupButton(completeBtn, 950, 35, 450, 65, "Student_Join_Screen_Complete_Button.png");

        setupTextField(nameTx, 170);
        setupTextField(idTx, 245);
        setupTextField(pwTx, 325);
        setupTextField(pwCkTx, 410);
        setupTextField(telTx, 500);
        setupTextField(emailTx, 580);

        JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Student_Join_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);

        backBtn.addActionListener(e -> {
            dispose();
            new Student().setVisible(true);
        });

        completeBtn.addActionListener(e -> {
            // 완료: DB 테이블에 값 저장, 메인 화면으로 이동
            String name = nameTx.getText();
            String id = idTx.getText();
            String pw = new String(pwTx.getPassword());
            String pwCk = new String(pwCkTx.getPassword());
            String tel = telTx.getText();
            String email = emailTx.getText();

            if (!pw.equals(pwCk)) {
                JOptionPane.showMessageDialog(this, "입력한 비밀번호와 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                return; // 비밀번호 확인이 일치하지 않으면 회원가입 중단
            }

            try {
                DB_connection dbConnection = new DB_connection();
                String sql = "INSERT INTO signup.student_join(name, id, pw, pwck, tel, email) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = dbConnection.conn.prepareStatement(sql);

                ps.setString(1, name);
                ps.setString(2, id);
                ps.setString(3, pw);
                ps.setString(4, pwCk);
                ps.setString(5, tel);
                ps.setString(6, email);

                ps.executeUpdate();

                // 회원가입이 성공했을 때 메시지 창 표시
                JOptionPane.showMessageDialog(this, "회원가입 성공", "알림", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println(ex.toString());
            }

            dispose();
            new Student().setVisible(true);
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
        textField.setBackground(Color.decode("#DFD4D6"));
        textField.setFont(new Font("SUITE", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        add(textField);
        return textField;
    }
}
