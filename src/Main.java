import java.awt.Image;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		Frame frame = new Frame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,400);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	
		frame.setIconImage(Frame.getGameImage());
	}
}