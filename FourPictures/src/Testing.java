import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Testing {

	public static void main(String[] args) throws MalformedURLException, IOException {
		JFrame frame = new JFrame();
		BufferedImage img = ImageIO
				.read(new URL(""));
		ImageIcon icon = new ImageIcon(img);
		JLabel tempLabel = new JLabel();
		tempLabel.setIcon(icon);
		frame.add(tempLabel);
		frame.setVisible(true);

	}

}
