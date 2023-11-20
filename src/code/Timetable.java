package code;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Timetable extends InheritanceFrame {

    private JButton completeButton = new JButton();

    public Timetable() {
        super("TIMETABLE", Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        setupButton(completeButton, 915, 30, "Timetable_Screen_Complete_Button.png");

        JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/image/Timetable_Screen.png")));
        lb.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        add(lb);

        completeButton.addActionListener(e -> {
            dispose();
            new Student().setVisible(true);
        });
    }

    // 버튼 설정 메소드
    private void setupButton(JButton button, int x, int y, String imageName) {
        button.setIcon(new ImageIcon(getClass().getResource("/image/" + imageName)));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBounds(x, y, 500, 65);
        add(button);
    }
}
