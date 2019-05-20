import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Game implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	JFrame frame;
	GamePanel panel;
	Timer fps;
	JTextField portoption;
	static BufferedReader reader;
	static Chess[] pieces = new Chess[15];

	public static int MenuOption = 0, GameState = 0, intLevel = 1, intExpLeft = 1, intExp = 0, intBoard[], intBench[],
			intRoll[] = new int[5], intGold = 1, intPieces, intPort = 3000;

	public Game() {
		portoption = new JTextField();
		portoption.setBounds(590, 360, 100, 40);
		portoption.setToolTipText("Port number used for the server 4 integers long");
		portoption.setText(Integer.toString(intPort));
		portoption.setVisible(false);
		panel = new GamePanel();
		panel.setLayout(null);
		frame = new JFrame("Game");
		panel.setPreferredSize(new Dimension(1280, 720));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		panel.addKeyListener(this);
		fps = new Timer(1000 / 60, this);
		fps.start();
		panel.add(portoption);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		panel.requestFocusInWindow();

	}

	public void keyPressed(KeyEvent e) {
		if (GameState == 0) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (MenuOption == 0) {
					startGame();
				} else if (MenuOption == 1) {
					options();
				} else if (MenuOption == 2) {
					help();
				} else {
					frame.setVisible(false);
					frame.dispose();
				}
			}
			if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (MenuOption + 1 > 3) {
					MenuOption = 3;
				} else {
					MenuOption = MenuOption + 1;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
				if (MenuOption - 1 < 0) {
					MenuOption = 0;
				} else {
					MenuOption = MenuOption - 1;
				}
			}
		} else if (GameState == 1) {
			if (e.getKeyCode() == KeyEvent.VK_F) {
				if (intGold >= 5) {
					intGold = intGold - 5;
					intExp = intExp + 4;
					if (4 >= intExpLeft) {
						intExp = 0;
						intExp = intExp + (4 - intExpLeft);
						intLevel = intLevel + 1;
						if (intLevel == 2) {
							intExpLeft = 1;
						} else {
							intExpLeft = intExpLeft * 2;
						}
					} else {
						intExpLeft = intExpLeft - 4;
					}
				} else {
					
				}
			}
		}

	}

	public void startGame() {
		GameState = 1;

	}

	public void roll() {
		GameState = 2;
		int intRand = 0;
		for (int i = 0; i < 5; i++) {
			if (intLevel == 1 || intLevel == 2) {
				intRand = (int) (Math.random() * 5);
			} else if (intLevel == 3 || intLevel == 4) {
				intRand = (int) (Math.random() * 9);
			} else if (intLevel == 5) {
				intRand = (int) (Math.random() * 12);
			} else {
				intRand = (int) (Math.random() * 15);
			}

			intRoll[i] = pieces[intRand].intNum;
		}

	}

	public void options() {
		GameState = 3;
		portoption.setVisible(true);
	}

	public void help() {
		GameState = 4;

	}

	public static void main(String[] args) {
		try {
			reader = new BufferedReader(new FileReader("pieces.csv"));
			for (int i = 0; i < 15; i++) {
				String strCSV = reader.readLine();
				String strLine[] = strCSV.split(",");
				pieces[i] = new Chess(strLine[0], Integer.parseInt(strLine[1]), Integer.parseInt(strLine[2]),
						Integer.parseInt(strLine[3]), Integer.parseInt(strLine[4]), Integer.parseInt(strLine[5]),
						Integer.parseInt(strLine[6]), strLine[7], strLine[8], Boolean.parseBoolean(strLine[9]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Game();

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == fps) {
			panel.repaint();
		}
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if (GameState == 3) {
			if (e.getX() >= 655 && e.getX() <= 833 && e.getY() >= 576 && e.getY() <= 653) {
				if (portoption.getText().length() > 5 || Integer.parseInt(portoption.getText()) > 65535) {
					portoption.setText(Integer.toString(intPort));
				}
				try {
					intPort = Integer.parseInt(portoption.getText());
				} catch (NumberFormatException e1) {
					portoption.setText(Integer.toString(intPort));
				}
			}
			if (e.getX() >= 446 && e.getX() <= 624 && e.getY() >= 576 && e.getY() <= 653) {
				portoption.setVisible(false);
				panel.requestFocusInWindow();
				GameState = 0;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}
}
