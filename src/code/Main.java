package code;

public class Main {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	try {
					SIGNUP startMain = new SIGNUP();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
		
	}

}
