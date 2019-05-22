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
	BufferedImage play = null;
	BufferedImage help = null;
	BufferedImage help1 = null;
	BufferedImage help2 = null;
	BufferedImage options = null;
	BufferedImage axe = null;

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
			menu = ImageIO.read(new File("menu.png"));
			play = ImageIO.read(new File("play.png"));
			help = ImageIO.read(new File("help.png"));
			help1 = ImageIO.read(new File("help1.png"));
			help2 = ImageIO.read(new File("help2.png"));
			options = ImageIO.read(new File("options.png"));
			axe = ImageIO.read(new File("axe.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		// Start Screen
		if (Game.GameState == 0) {
			g.drawImage(menu, 0, 0, null);
			// g.drawLine(640, 0, 640, 800);
			if (Game.MenuOption == 0) {
				g.drawImage(start1, 540, 440, null);
				g.drawImage(options2, 500, 510, null);
				g.drawImage(help2, 560, 580, null);
				g.drawImage(exit2, 565, 650, null);
			} else if (Game.MenuOption == 1) {
				g.drawImage(start2, 540, 440, null);
				g.drawImage(options1, 500, 510, null);
				g.drawImage(help2, 560, 580, null);
				g.drawImage(exit2, 565, 650, null);
			} else if (Game.MenuOption == 2) {
				g.drawImage(start2, 540, 440, null);
				g.drawImage(options2, 500, 510, null);
				g.drawImage(help1, 560, 580, null);
				g.drawImage(exit2, 565, 650, null);
			} else if (Game.MenuOption == 3) {
				g.drawImage(start2, 540, 440, null);
				g.drawImage(options2, 500, 510, null);
				g.drawImage(help2, 560, 580, null);
				g.drawImage(exit1, 565, 650, null);
			}

		} else if (Game.GameState == 1) {
			g.drawImage(play, 0, 0, null);
			g.drawImage(axe, 200, 200, null);
		} else if (Game.GameState == 2) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 1280, 720);
			g.setColor(Color.BLACK);
			g.drawString(Game.roll[0].strName, 100, 300);
			g.drawString(Game.roll[1].strName, 200, 300);
			g.drawString(Game.roll[2].strName, 300, 300);
			g.drawString(Game.roll[3].strName, 400, 300);
			g.drawString(Game.roll[4].strName, 500, 300);
		} else if (Game.GameState == 3) {
			g.drawImage(options, 0, 0, null);

		} else if (Game.GameState == 4) {
			g.drawImage(help, 0, 0, null);
		}
	}

	public GamePanel() {
		super();
	}
}
