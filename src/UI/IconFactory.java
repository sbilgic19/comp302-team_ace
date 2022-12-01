package UI;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

// Singleton Class
public class IconFactory {
	
	private static IconFactory iconFactory = null;
	
	public static IconFactory getInstance() { 
		if (IconFactory.iconFactory == null) {
			iconFactory = new IconFactory();
		}
		return iconFactory;
	}
	 
	public ImageIcon generateIcon(String filename, int width, int height) {
		IconFactory iconFactory = getInstance();
		URL url = iconFactory.generateURL(filename);
		ImageIcon imageIcon = new ImageIcon(url);
		Image image = imageIcon.getImage();
		if (width != 0 && height != 0) {
			image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		}
		return new ImageIcon(image);
	}
	
	private URL generateURL(String filename) {
		return getClass().getResource(filename);
	}
}