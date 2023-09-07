package code;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;

public class InheritanceFrame extends JFrame {
	
	public InheritanceFrame(String title, int w, int h) {
		setTitle(title); // 프레임 제목 설정
		setSize(w, h); // 프레임 크기 설정
		setResizable(false); // 창의 크기 변경 못하도록 설정
		setLocationRelativeTo(null); // 프레임을 화면 중앙에 배치
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
	}
	
	public ImageIcon getIcon(String path, int w, int h) {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(path).getScaledInstance(w, h, Image.SCALE_SMOOTH));
	}

}

class ImgScreen extends JPanel {
	private Image img;

    public ImgScreen(Image img) {
        this.img = img;
        setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
        setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }
}
