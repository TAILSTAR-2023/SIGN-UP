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

    private JButton backBtn = new JButton();
    private JButton completeBtn = new JButton();

    private JTextField majorTx = new JTextField();
    private JTextField studentNTx = new JTextField();
    private JTextField gradeTx = new JTextField();
    private JTextField sIDTx = new JTextField();

    public Student_Management() {
        super("STUDENT MANAGEMENT", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setLayout(null);

        setupButton(backBtn, 20, 20, 150, 65, "Student_Management_Screen_Back_Button.png");
        setupButton(completeBtn, 920, 20, 500, 65, "Student_Management_Screen_Complete_Button.png");

        setupTextField(majorTx, 230);
        setupTextField(studentNTx, 300);
        setupTextField(gradeTx, 370);
        setupTextField(sIDTx, 440);

        JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Student_Management_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);

        backBtn.addActionListener(e -> {
            dispose();
            new Professor().setVisible(true);
        });

        completeBtn.addActionListener(e -> {
            // 완료: DB 테이블에 값 저장, 메인 화면으로 이동
            String major = majorTx.getText();
            String student = studentNTx.getText();
            String grade = gradeTx.getText();
            String sID = sIDTx.getText();

            DB_connection dbConnection;

            try {
                dbConnection = new DB_connection();
                String sql = "INSERT INTO signup.student_management(major, name, grade, sID) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = dbConnection.conn.prepareStatement(sql);

                ps.setString(1, major);
                ps.setString(2, student);
                ps.setString(3, grade);
                ps.setString(4, sID);

                dbConnection = new DB_connection();

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
    private void setupButton(JButton button, int x, int y, int w, int h, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName)));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBounds(x, y, w, h);
        add(button);
    }

    // 텍스트필드 설정 메소드
    private JTextField setupTextField(JTextField textField, int x) {
        textField.setBounds(550, x, 495, 45);
        textField.setBackground(Color.decode("#C8C9DA"));
        textField.setFont(new Font("SUITE", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        add(textField);
        return textField;
    }
}
