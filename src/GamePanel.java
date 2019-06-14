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
	BufferedImage roll = null;
	BufferedImage connection = null;
	BufferedImage client = null;
	BufferedImage username = null;
	BufferedImage helpmenu = null;
	BufferedImage controlshelp = null;
	BufferedImage connectionhelp = null;
	BufferedImage mechanicshelp = null;
	BufferedImage win = null;
	BufferedImage lose = null;
	BufferedImage pieceimgs[] = new BufferedImage[30];

	public void paintComponent(Graphics g) throws NullPointerException {
		try {
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("pixelart1.otf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);

		} catch (FontFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Font font2 = new Font("My Font Regular", Font.TRUETYPE_FONT, 36);
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
			roll = ImageIO.read(new File("roll.png"));
			connection = ImageIO.read(new File("connection.png"));
			client = ImageIO.read(new File("client.png"));
			username = ImageIO.read(new File("username.png"));
			helpmenu = ImageIO.read(new File("helpmenu.png"));
			controlshelp = ImageIO.read(new File("controlshelp.png"));
			connectionhelp = ImageIO.read(new File("connectionhelp.png"));
			mechanicshelp = ImageIO.read(new File("mechanicshelp.png"));
			win = ImageIO.read(new File("win.png"));
			lose = ImageIO.read(new File("lose.png"));
			for (int i = 0; i < 30; i++) {
				if (i < 15) {
					pieceimgs[i] = ImageIO.read(new File(Game.pieces[i].strName + ".png"));
				} else {
					pieceimgs[i] = ImageIO.read(new File(Game.pieces2[i - 15].strName + "2.png"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (Game.GameState == 1) {
			Game.health.setVisible(true);
			Game.name.setVisible(true);
			Game.gold.setVisible(true);
			Game.level.setVisible(true);
			Game.health2.setVisible(true);
			Game.name2.setVisible(true);
			Game.gold2.setVisible(true);
			Game.level2.setVisible(true);
			Game.statusbar.setVisible(true);
			Game.scroll.setVisible(true);
			Game.chattext.setVisible(true);
			Game.roll1.setVisible(false);
			Game.roll2.setVisible(false);
			Game.roll3.setVisible(false);
			Game.roll4.setVisible(false);
			Game.roll5.setVisible(false);
			Game.gold3.setVisible(false);

		} else if (Game.GameState == 2) {
			if (Game.blnroll1 == true) {
				Game.roll1.setVisible(true);
			} else {
				Game.roll1.setVisible(false);
			}
			if (Game.blnroll2 == true) {
				Game.roll2.setVisible(true);
			} else {
				Game.roll2.setVisible(false);
			}
			if (Game.blnroll3 == true) {
				Game.roll3.setVisible(true);
			} else {
				Game.roll3.setVisible(false);
			}
			if (Game.blnroll4 == true) {
				Game.roll4.setVisible(true);
			} else {
				Game.roll4.setVisible(false);
			}
			if (Game.blnroll5 == true) {
				Game.roll5.setVisible(true);
			} else {
				Game.roll5.setVisible(false);
			}

			Game.gold3.setVisible(true);
			Game.health.setVisible(false);
			Game.name.setVisible(false);
			Game.gold.setVisible(false);
			Game.level.setVisible(false);
			Game.health2.setVisible(false);
			Game.name2.setVisible(false);
			Game.gold2.setVisible(false);
			Game.level2.setVisible(false);
			Game.statusbar.setVisible(false);
			Game.scroll.setVisible(false);
			Game.chattext.setVisible(false);
		} else {
			Game.health.setVisible(false);
			Game.name.setVisible(false);
			Game.gold.setVisible(false);
			Game.level.setVisible(false);
			Game.health2.setVisible(false);
			Game.name2.setVisible(false);
			Game.gold2.setVisible(false);
			Game.level2.setVisible(false);
			Game.statusbar.setVisible(false);
			Game.roll1.setVisible(false);
			Game.roll2.setVisible(false);
			Game.roll3.setVisible(false);
			Game.roll4.setVisible(false);
			Game.roll5.setVisible(false);
			Game.gold3.setVisible(false);
			Game.scroll.setVisible(false);
			Game.chattext.setVisible(false);
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
			int intWidth = (int) (Game.intHealth * 2.29);
			int intWidth2 = (int) (Game.intHealth2 * 2.29);
			g.drawImage(play, 0, 0, null);
			g.setColor(new Color(150, 2, 2));
			g.fillRect(23, 120, intWidth, 22);
			g.fillRect(23, 360, intWidth2, 22);
			for (int i = 0; i < 8; i++) {
				if (Game.bench[i] != null) {
					g.drawImage(pieceimgs[Game.bench[i].intNum], 160 + 120 * i, 555, null);
				}
			}
			for (int i = 0; i < Game.board.length; i++) {
				if (Game.board[i] != null) {
					if (i < 4) {
						g.drawImage(pieceimgs[Game.board[i].intNum], 400 + 120 * i, 270, null);
					} else {
						g.drawImage(pieceimgs[Game.board[i].intNum], -80 + 120 * i, 390, null);
					}
				}
			}
			if (Game.blnRoundStart == true && Game.blnPieces == true) {
				for (int i = 0; i < Game.board2.length; i++) {
					if (i < 4) {
						g.drawImage(pieceimgs[Integer.parseInt(Game.board2[i])], 760 - 120 * i, 150, null);
					} else {
						g.drawImage(pieceimgs[Integer.parseInt(Game.board2[i])], 1240 - 120 * i, 30, null);
					}
				}

			}
		} else if (Game.GameState == 2) {
			g.drawImage(roll, 0, 0, null);
			if (Game.blnroll1 == true) {
				g.drawImage(pieceimgs[Game.roll[0].intNum], 103, 274, null);
			}
			if (Game.blnroll2 == true) {
				g.drawImage(pieceimgs[Game.roll[1].intNum], 346, 274, null);
			}
			if (Game.blnroll3 == true) {
				g.drawImage(pieceimgs[Game.roll[2].intNum], 589, 274, null);
			}
			if (Game.blnroll4 == true) {
				g.drawImage(pieceimgs[Game.roll[3].intNum], 832, 274, null);
			}
			if (Game.blnroll5 == true) {
				g.drawImage(pieceimgs[Game.roll[4].intNum], 1075, 274, null);
			}
		} else if (Game.GameState == 3) {
			g.drawImage(options, 0, 0, null);
		} else if (Game.GameState == 4) {
			if (Game.intHelp == 0) {
				g.drawImage(helpmenu, 0, 0, null);
			} else if (Game.intHelp == 1) {
				g.drawImage(controlshelp, 0, 0, null);
			} else if (Game.intHelp == 2) {
				g.drawImage(connectionhelp, 0, 0, null);
			} else if (Game.intHelp == 3) {
				g.drawImage(mechanicshelp, 0, 0, null);
			}
		} else if (Game.GameState == 5) {
			g.drawImage(connection, 0, 0, null);
		} else if (Game.GameState == 6) {
			g.drawImage(client, 0, 0, null);
		} else if (Game.GameState == 7) {
			g.drawImage(username, 0, 0, null);
		} else if (Game.GameState == 8) {
			g.drawImage(lose, 0, 0, null);
		} else if (Game.GameState == 9) {
			g.drawImage(win, 0, 0, null);
		}
	}

	public GamePanel() {
		super();
	}
}
