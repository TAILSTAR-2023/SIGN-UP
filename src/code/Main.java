package code;

public class Main {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	
	public static void main(String[] args) {
		
		// new start();
//		new START_MAIN();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // START_MAIN 클래스의 인스턴스 생성
                START_MAIN startMain = new START_MAIN();
                // START_MAIN 클래스의 생성자 호출
            }
        });
		
	}

}
