import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Game implements ActionListener, KeyListener {
	JFrame frame;
	GamePanel panel;
	Timer fps;
	JTextField portoption;
	static BufferedReader reader;
	static Chess[] pieces = new Chess[15];

	public static int MenuOption = 0, GameState = 0, intCurrLevel1 = 1, intExpLeft1 = 0, intExp1 = 0, intBoard1[],
			intBench1[], intRoll1[], intCurrLevel2 = 1, intExpLeft2 = 0, intExp2 = 0, intBoard2[], intBench2[],
			intRoll2[], intPieces1, intPieces2;

	public Game() {
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
		panel.requestFocusInWindow();
		fps = new Timer(1000 / 60, this);
		fps.start();
		portoption = new JTextField();
		portoption.setBounds(590, 360, 100, 40);
		portoption.setToolTipText("Port number used for the server 4 integers long");
		panel.add(portoption);
		portoption.setVisible(false);
	}

	public void keyPressed(KeyEvent e) {
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

	}

	public void startGame() {
		GameState = 1;

	}

	public void options() {
		GameState = 2;
		portoption.setVisible(true);
	}

	public void help() {
		GameState = 3;

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

	public static void main(String[] args) {
		new Game();
		try {
			reader = new BufferedReader(new FileReader("pieces.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
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
	}
}
