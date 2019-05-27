import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;

public class Game implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	JFrame frame;
	GamePanel panel;
	Timer fps;
	JTextField portoption, ipaddress;
	static JLabel gold, health, name, level, gold2, health2, name2, level2, roll1, roll2, roll3, roll4, roll5, gold3;
	SuperSocketMaster ssm;
	static BufferedReader reader;
	static Chess[] pieces = new Chess[15];
	public static int MenuOption = 0, GameState = 0, intLevel = 1, intExpLeft = 1, intExp = 0, intBoard[], intBench[],
			intGold = 5, intPieces, intPort = 3000, intHealth = 100, intGold2, intHealth2 = 100, intLevel2;
	public String strName = "Player1", strName2 = "Player2";
	static boolean blnServer, blnroll1 = true, blnroll2 = true, blnroll3 = true, blnroll4 = true, blnroll5 = true;;
	static Chess[] roll = new Chess[5];
	static Chess[] board = new Chess[1];
	static Chess[] bench = new Chess[8];

	public Game() {
		try {
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("pixelart1.otf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);

		} catch (FontFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Font font = new Font("My Font Regular", Font.PLAIN, 24);
		Font font2 = new Font("My Font Regular", Font.PLAIN, 32);

		portoption = new JTextField();
		portoption.setBounds(590, 360, 100, 40);
		portoption.setToolTipText("Port number used for the server 4 integers long");
		portoption.setText(Integer.toString(intPort));
		portoption.setFont(font);
		portoption.setVisible(false);
		portoption.setEnabled(false);

		ipaddress = new JTextField();
		ipaddress.setBounds(455, 360, 363, 40);
		ipaddress.setToolTipText("Enter IP address of the server");
		ipaddress.setFont(font);
		ipaddress.setVisible(false);

		panel = new GamePanel();
		panel.setLayout(null);
		frame = new JFrame("Game");
		panel.setPreferredSize(new Dimension(1280, 720));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

		fps = new Timer(1000 / 60, this);
		fps.start();

		health = new JLabel(Integer.toString(intHealth));
		health.setFont(font);
		health.setBounds(23, 85, 229, 100);
		health.setForeground(Color.BLACK);
		health.setHorizontalAlignment(JLabel.CENTER);

		gold = new JLabel("GOLD: " + Integer.toString(intGold));
		gold.setFont(font);
		gold.setBounds(23, 135, 229, 100);
		gold.setForeground(new Color(150, 2, 2));
		gold.setHorizontalAlignment(JLabel.CENTER);

		level = new JLabel("LEVEL: " + Integer.toString(intLevel));
		level.setFont(font);
		level.setBounds(23, 185, 229, 100);
		level.setForeground(new Color(150, 2, 2));
		level.setHorizontalAlignment(JLabel.CENTER);

		name = new JLabel(strName);
		name.setFont(font2);
		name.setBounds(23, 12, 229, 100);
		name.setForeground(new Color(150, 2, 2));
		name.setHorizontalAlignment(JLabel.CENTER);

		health2 = new JLabel(Integer.toString(intHealth2));
		health2.setFont(font);
		health2.setBounds(23, 325, 229, 100);
		health2.setForeground(Color.BLACK);
		health2.setHorizontalAlignment(JLabel.CENTER);

		gold2 = new JLabel("GOLD: " + Integer.toString(intGold2));
		gold2.setFont(font);
		gold2.setBounds(23, 375, 229, 100);
		gold2.setForeground(new Color(150, 2, 2));
		gold2.setHorizontalAlignment(JLabel.CENTER);

		level2 = new JLabel("LEVEL: " + Integer.toString(intLevel2));
		level2.setFont(font);
		level2.setBounds(23, 425, 229, 100);
		level2.setForeground(new Color(150, 2, 2));
		level2.setHorizontalAlignment(JLabel.CENTER);

		name2 = new JLabel(strName2);
		name2.setFont(font2);
		name2.setBounds(23, 252, 229, 100);
		name2.setForeground(new Color(150, 2, 2));
		name2.setHorizontalAlignment(JLabel.CENTER);

		roll1 = new JLabel();
		roll1.setFont(font);
		roll1.setBounds(60, 221, 200, 52);
		roll1.setForeground(Color.BLACK);
		roll1.setHorizontalAlignment(JLabel.CENTER);

		roll2 = new JLabel();
		roll2.setFont(font);
		roll2.setBounds(303, 221, 200, 52);
		roll2.setForeground(Color.BLACK);
		roll2.setHorizontalAlignment(JLabel.CENTER);

		roll3 = new JLabel();
		roll3.setFont(font);
		roll3.setBounds(546, 221, 200, 52);
		roll3.setForeground(Color.BLACK);
		roll3.setHorizontalAlignment(JLabel.CENTER);

		roll4 = new JLabel();
		roll4.setFont(font);
		roll4.setBounds(789, 221, 200, 52);
		roll4.setForeground(Color.BLACK);
		roll4.setHorizontalAlignment(JLabel.CENTER);

		roll5 = new JLabel();
		roll5.setFont(font);
		roll5.setBounds(1032, 221, 200, 52);
		roll5.setForeground(Color.BLACK);
		roll5.setHorizontalAlignment(JLabel.CENTER);

		gold3 = new JLabel("GOLD: " + Integer.toString(intGold));
		gold3.setFont(font2);
		gold3.setBounds(547, 87, 200, 69);
		gold3.setForeground(Color.BLACK);
		gold3.setHorizontalAlignment(JLabel.CENTER);
		gold3.setVerticalAlignment(JLabel.CENTER);

		panel.add(gold3);
		panel.add(roll1);
		panel.add(roll2);
		panel.add(roll3);
		panel.add(roll4);
		panel.add(roll5);
		panel.add(level);
		panel.add(health);
		panel.add(name);
		panel.add(gold);
		panel.add(level2);
		panel.add(health2);
		panel.add(name2);
		panel.add(gold2);
		panel.addKeyListener(this);
		panel.add(portoption);
		panel.add(ipaddress);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		panel.requestFocusInWindow();

	}

	public void keyPressed(KeyEvent e) {
		if (GameState == 0) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (MenuOption == 0) {
					GameState = 5;
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

	public void connection() {
		GameState = 6;
		ipaddress.setVisible(true);
	}

	public void startGame() {
		GameState = 1;
		if (blnServer == true) {
			ssm.sendText("start//" + Integer.toString(intGold) + "," + Integer.toString(intHealth) + ","
					+ Integer.toString(intLevel));
			board = Arrays.copyOf(board, intLevel);
			gold.setText("GOLD: " + Integer.toString(intGold));
			level.setText("LEVEL: " + Integer.toString(intLevel));
			health.setText(Integer.toString(intHealth));

		} else {
			ssm.sendText("start//" + Integer.toString(intGold) + "," + Integer.toString(intHealth) + ","
					+ Integer.toString(intLevel));

		}

	}

	public void roll() {
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

			roll[i] = pieces[intRand];
		}
		roll1.setText(roll[0].strName.toUpperCase());
		roll2.setText(roll[1].strName.toUpperCase());
		roll3.setText(roll[2].strName.toUpperCase());
		roll4.setText(roll[3].strName.toUpperCase());
		roll5.setText(roll[4].strName.toUpperCase());
		GameState = 2;

	}

	public void options() {
		GameState = 3;
		portoption.setVisible(true);
		portoption.setEnabled(true);
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
		if (e.getSource() == ssm) {
			String strText = ssm.readText();
			if (strText.substring(0, 6).equals("start//")) {
				strText = strText.substring(7, strText.length());
				String strSplit[] = strText.split(",");
				intGold2 = Integer.parseInt(strSplit[0]);
				intHealth2 = Integer.parseInt(strSplit[1]);
				intLevel2 = Integer.parseInt(strSplit[2]);
			} else if (strText.substring(0, 5).equals("name//")) {
				strText = strText.substring(6, strText.length());
				strName2 = strText;
			}

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
		if (GameState == 1) {
			if (e.getX() >= 160 && e.getX() <= 280 && e.getY() >= 554 && e.getY() <= 674) {
				if(bench[0] != null) {
					for(int i = 0; i < intLevel; i++) {
						if(board[i] == null) {
							board[i] = bench[0];
							bench[0] = null;
							i = intLevel + 1;
						}
					}
				}
				
			}
		} else if (GameState == 2) {
			if (e.getX() >= 53 && e.getX() <= 267 && e.getY() >= 431 && e.getY() <= 523 && blnroll1 == true) {
				for (int i = 0; i < bench.length; i++) {
					if (bench[i] == null && intGold >= roll[0].intLevel) {
						bench[i] = roll[0];
						intGold = intGold - roll[0].intLevel;
						blnroll1 = false;
						System.out.println(bench[i].strName);
						i = 10;

					}
				}
			}
			if (e.getX() >= 295 && e.getX() <= 509 && e.getY() >= 431 && e.getY() <= 523 && blnroll2 == true) {
				for (int i = 0; i < bench.length; i++) {
					if (bench[i] == null && intGold >= roll[1].intLevel) {
						bench[i] = roll[1];
						intGold = intGold - roll[1].intLevel;
						blnroll2 = false;
						System.out.println(bench[i].strName);
						i = 10;

					}
				}
			}
			if (e.getX() >= 542 && e.getX() <= 756 && e.getY() >= 431 && e.getY() <= 523 && blnroll3 == true) {
				for (int i = 0; i < bench.length; i++) {
					if (bench[i] == null && intGold >= roll[2].intLevel) {
						bench[i] = roll[2];
						intGold = intGold - roll[2].intLevel;
						blnroll3 = false;
						System.out.println(bench[i].strName);
						i = 10;

					}
				}
			}
			if (e.getX() >= 784 && e.getX() <= 998 && e.getY() >= 431 && e.getY() <= 523 && blnroll4 == true) {
				for (int i = 0; i < bench.length; i++) {
					if (bench[i] == null && intGold >= roll[3].intLevel) {
						bench[i] = roll[3];
						intGold = intGold - roll[3].intLevel;
						blnroll4 = false;
						System.out.println(bench[i].strName);
						i = 10;

					}
				}
			}
			if (e.getX() >= 1027 && e.getX() <= 1241 && e.getY() >= 431 && e.getY() <= 523 && blnroll5 == true) {
				for (int i = 0; i < bench.length; i++) {
					if (bench[i] == null && intGold >= roll[4].intLevel) {
						bench[i] = roll[4];
						intGold = intGold - roll[4].intLevel;
						blnroll5 = false;
						System.out.println(bench[i].strName);
						i = 10;

					}
				}
			}
			gold3.setText("GOLD: " + Integer.toString(intGold));
			if (e.getX() >= 540 && e.getX() <= 754 && e.getY() >= 561 && e.getY() <= 654) {
				startGame();
			}
		} else if (GameState == 3) {
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
		} else if (GameState == 4) {

		} else if (GameState == 5) {
			if (e.getX() >= 214 && e.getX() <= 501 && e.getY() >= 287 && e.getY() <= 433) {
				ssm = new SuperSocketMaster(intPort, this);
				blnServer = true;
				roll();
			}
			if (e.getX() >= 782 && e.getX() <= 1069 && e.getY() >= 287 && e.getY() <= 433) {
				blnServer = false;
				connection();
			}
		} else if (GameState == 6) {
			if (e.getX() >= 546 && e.getX() <= 727 && e.getY() >= 476 && e.getY() <= 554) {
				ssm = new SuperSocketMaster(ipaddress.getText(), intPort, this);
				ipaddress.setVisible(false);
				ipaddress.setEnabled(false);
				roll();
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}
}
