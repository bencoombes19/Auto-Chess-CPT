import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class GamePanel extends JPanel {
	BufferedImage start1 = null;
	BufferedImage start2 = null;
	BufferedImage options1 = null;
	BufferedImage options2 = null;
	BufferedImage exit1 = null;
	BufferedImage exit2 = null;
	BufferedImage menu = null;

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		try {
			// Importing the images
			start1 = ImageIO.read(new File("start1.png"));
			start2 = ImageIO.read(new File("start2.png"));
			options1 = ImageIO.read(new File("options1.png"));
			options2 = ImageIO.read(new File("options2.png"));
			exit1 = ImageIO.read(new File("exit1.png"));
			exit2 = ImageIO.read(new File("exit2.png"));
			menu = ImageIO.read(new File ("menu.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Start Screen
		if (Game.GameState == 0) {
			g.drawImage(menu,0,0,null);
			g.drawLine(640, 0, 640, 720);
			if (Game.MenuOption == 0) {
				g.drawImage(start1, 540, 440, null);
				g.drawImage(options2, 500, 510, null);
				g.drawImage(exit2, 570, 580, null);
			} else if (Game.MenuOption == 1) { 
				g.drawImage(start2, 540, 440, null);
				g.drawImage(options1, 500, 510, null);
				g.drawImage(exit2, 570, 580, null);
			} else if (Game.MenuOption == 2) {
				g.drawImage(start2, 540, 440, null);
				g.drawImage(options2, 500, 510, null);
				g.drawImage(exit1, 570, 580, null);
			}
		} else if (Game.GameState == 1) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1600, 900);
		}
	}

	public GamePanel() {
		super();
	}
}
