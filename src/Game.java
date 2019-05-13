import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game implements ActionListener, KeyListener {
	JFrame frame;
	GamePanel panel;
	Timer fps;
	public static int MenuOption = 0, GameState = 0, intCurrLevel1 = 1, intExpLeft1 = 0, intExp1 = 0, intBoard1[],
			intBench1[], intRoll1[], intCurrLevel2 = 1, intExpLeft2 = 0, intExp2 = 0, intBoard2[], intBench2[],
			intRoll2[], intPieces1, intPieces2;

	public Game() {
		panel = new GamePanel();
		panel.setLayout(null);
		frame = new JFrame("Game");
		frame.setPreferredSize(new Dimension(1280, 720));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		panel.addKeyListener(this);
		panel.requestFocusInWindow();
		fps = new Timer(1000 / 60, this);
		fps.start();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (MenuOption == 0) {
				startGame();
			} else if (MenuOption == 1) {

			} else {
				frame.setVisible(false);
				frame.dispose();
			}
		}
		if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (MenuOption + 1 > 2) {
				MenuOption = 2;
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
	}
}
