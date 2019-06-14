import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;

public class Game implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	JFrame frame;
	GamePanel panel;
	Timer fps;
	JTextField portoption, ipaddress, username;
	static JTextField chattext;
	JTextArea chat;
	static JScrollPane scroll;
	static JLabel gold, health, name, level, gold2, health2, name2, level2, roll1, roll2, roll3, roll4, roll5, gold3,
			statusbar;
	static SuperSocketMaster ssm;
	static BufferedReader reader;
	static Chess[] pieces = new Chess[15], pieces2 = new Chess[15], bench, board, roll = new Chess[5];
	public static int MenuOption = 0, GameState = 0, intLevel = 1, intExpLeft = 1, intTotalExp = 1, intExp = 0,
			intBoard[], intBench[], intGold = 1, intPieces, intPort = 3000, intHealth = 100, intGold2, intHealth2 = 100,
			intLevel2, intDamage2, intHealthP2, intArmour2, intDamage, intHealthP, intArmour, intRoundNum = 0,
			intShowScreen = 0, intHelp = 0;
	int[] intPieceNum = new int[15];
	public static String strName = "Player1", strName2 = "Player2", board2[];
	static boolean blnServer, blnroll1 = true, blnroll2 = true, blnroll3 = true, blnroll4 = true, blnroll5 = true,
			blnReady = false, blnReady2 = false, blnRoundStart = false, blnShowLabel = false, blnCalculation = false,
			blnPieces = false, blnMain = false;

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
		portoption.setOpaque(false);
		portoption.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		portoption.setForeground(Color.black);

		ipaddress = new JTextField();
		ipaddress.setBounds(455, 360, 363, 40);
		ipaddress.setToolTipText("Enter IP address of the server");
		ipaddress.setFont(font);
		ipaddress.setVisible(false);
		ipaddress.setOpaque(false);
		ipaddress.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		ipaddress.setForeground(Color.black);

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

		chat = new JTextArea();
		chat.setOpaque(false);
		chat.setBorder(BorderFactory.createLineBorder(new Color(59, 57, 57)));
		chattext = new JTextField();
		chattext.setBounds(1031, 363, 218, 65);
		chattext.addActionListener(this);
		chattext.setVisible(false);
		chattext.setOpaque(false);
		chattext.setBorder(BorderFactory.createLineBorder(new Color(150, 2, 2), 3));
		chattext.setForeground(Color.white);
		scroll = new JScrollPane(chat);
		scroll.setBounds(1031, 30, 218, 326);
		scroll.setVisible(false);
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		scroll.setBorder(BorderFactory.createLineBorder(new Color(59, 57, 57)));
		chat.setEnabled(false);

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

		statusbar = new JLabel();
		statusbar.setFont(font);
		statusbar.setBounds(282, 519, 716, 26);
		statusbar.setForeground(new Color(150, 2, 2));
		statusbar.setHorizontalAlignment(JLabel.CENTER);

		username = new JTextField();
		username.setBounds(455, 360, 363, 40);
		username.setToolTipText("please enter a username between 2 and 9 characters");
		username.setFont(font);
		username.setForeground(Color.black);
		username.setVisible(false);
		username.setEnabled(false);
		username.setOpaque(false);
		username.setBorder(BorderFactory.createLineBorder(Color.black, 3));

		panel.add(chattext);
		panel.add(scroll);
		panel.add(username);
		panel.add(statusbar);
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
					intLevel = 1;
					intGold = 1;
					intExpLeft = 1;
					intHealth = 100;
					intTotalExp = 1;
					intRoundNum = 0;
					board = new Chess[1];
					bench = new Chess[8];
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
			if (e.getKeyCode() == KeyEvent.VK_F && intLevel < 8) {
				if (intGold >= 5) {
					intGold = intGold - 5;
					intExp = intExp + 4;
					if (4 >= intExpLeft) {
						intExp = 0;
						intExp = intExp + (4 - intExpLeft);
						intLevel = intLevel + 1;
						if (intLevel == 2) {
							intTotalExp = 1;
							intExpLeft = intTotalExp;
						} else {
							intTotalExp = intTotalExp * 2;
							intExpLeft = intTotalExp;
						}
					} else {
						intExpLeft = intExpLeft - 4;
						intExp = 0;
					}
				} else {
				}
				for (int i = 0; i < 2; i++) {
					if (intExp >= intExpLeft) {
						intExp = intExp - intExpLeft;
						intLevel = intLevel + 1;
						if (intLevel == 2) {
							intTotalExp = 1;
							intExpLeft = intTotalExp;
						} else {
							intTotalExp = intTotalExp * 2;
							intExpLeft = intTotalExp;
						}
					}
				}
				board = Arrays.copyOf(board, intLevel);
				ssm.sendText("start//" + Integer.toString(intGold) + "," + Integer.toString(intHealth) + ","
						+ Integer.toString(intLevel));
				gold.setText("GOLD: " + Integer.toString(intGold));
				level.setText("LEVEL: " + Integer.toString(intLevel));

			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER && chattext.getText().length() > 0) {
				ssm.sendText(strName + ": " + chattext.getText());
				chat.append(strName + ": " + chattext.getText());
				chattext.setText("");
			}
		}

	}

	public void connection() {
		GameState = 6;
		ipaddress.setVisible(true);
	}

	public void startGame() {
		GameState = 1;
		blnRoundStart = false;
		blnReady = false;
		blnReady2 = false;
		blnPieces = false;
		intDamage = 0;
		intHealthP = 0;
		intArmour = 0;
		intDamage2 = 0;
		intHealthP2 = 0;
		intArmour2 = 0;
		panel.requestFocusInWindow();
		ssm.sendText("start//" + Integer.toString(intGold) + "," + Integer.toString(intHealth) + ","
				+ Integer.toString(intLevel));
		board = Arrays.copyOf(board, intLevel);
		gold.setText("GOLD: " + Integer.toString(intGold));
		level.setText("LEVEL: " + Integer.toString(intLevel));
		health.setText(Integer.toString(intHealth));
	}

	public static void mainGame() {
		System.out.println("mainGame");
		int intNumPieces = 0;
		for (int i = 0; i < board.length; i++) {
			if (board[i] != null) {
				intNumPieces = intNumPieces + 1;
			}
		}
		if (intNumPieces == 1) {
			ssm.sendText("pieces//" + Integer.toString(board[0].intNum));
		} else if (intNumPieces == 2) {
			ssm.sendText("pieces//" + Integer.toString(board[0].intNum) + "," + Integer.toString(board[1].intNum));
		} else if (intNumPieces == 3) {
			ssm.sendText("pieces//" + Integer.toString(board[0].intNum) + "," + Integer.toString(board[1].intNum) + ","
					+ Integer.toString(board[2].intNum));
		} else if (intNumPieces == 4) {
			ssm.sendText("pieces//" + Integer.toString(board[0].intNum) + "," + Integer.toString(board[1].intNum) + ","
					+ Integer.toString(board[2].intNum) + "," + Integer.toString(board[3].intNum));
		} else if (intNumPieces == 5) {
			ssm.sendText("pieces//" + Integer.toString(board[0].intNum) + "," + Integer.toString(board[1].intNum) + ","
					+ Integer.toString(board[2].intNum) + "," + Integer.toString(board[3].intNum) + ","
					+ Integer.toString(board[4].intNum));
		} else if (intNumPieces == 6) {
			ssm.sendText("pieces//" + Integer.toString(board[0].intNum) + "," + Integer.toString(board[1].intNum) + ","
					+ Integer.toString(board[2].intNum) + "," + Integer.toString(board[3].intNum) + ","
					+ Integer.toString(board[4].intNum) + "," + Integer.toString(board[5].intNum));
		} else if (intNumPieces == 7) {
			ssm.sendText("pieces//" + Integer.toString(board[0].intNum) + "," + Integer.toString(board[1].intNum) + ","
					+ Integer.toString(board[2].intNum) + "," + Integer.toString(board[3].intNum) + ","
					+ Integer.toString(board[4].intNum) + "," + Integer.toString(board[5].intNum) + ","
					+ Integer.toString(board[6].intNum));
		} else if (intNumPieces == 8) {
			ssm.sendText("pieces//" + Integer.toString(board[0].intNum) + "," + Integer.toString(board[1].intNum) + ","
					+ Integer.toString(board[2].intNum) + "," + Integer.toString(board[3].intNum) + ","
					+ Integer.toString(board[4].intNum) + "," + Integer.toString(board[5].intNum) + ","
					+ Integer.toString(board[6].intNum) + "," + Integer.toString(board[7].intNum));
		}
		int intTroll = 0, intWarrior = 0, intOrc = 0, intHunter = 0, intKnight = 0, intHuman = 0, intShaman = 0,
				intElf = 0, intDemon = 0;
		Chess round[] = new Chess[0];
		round = board;
		blnRoundStart = true;
		blnReady = false;
		blnReady2 = false;
		for (int i = 0; i < round.length; i++) {
			if (round[i] != null) {
				if (round[i].strRace.equals("troll")) {
					intTroll = intTroll + 1;
				} else if (round[i].strClass.equals("warrior")) {
					intWarrior = intWarrior + 1;
				} else if (round[i].strRace.equals("orc")) {
					intOrc = intOrc + 1;
				} else if (round[i].strClass.equals("hunter")) {
					intHunter = intHunter + 1;
				} else if (round[i].strClass.equals("knight")) {
					intKnight = intKnight + 1;
				} else if (round[i].strRace.equals("human")) {
					intHuman = intHuman + 1;
				} else if (round[i].strClass.equals("shaman")) {
					intShaman = intShaman + 1;
				} else if (round[i].strRace.equals("elf")) {
					intElf = intElf + 1;
				} else if (round[i].strRace.equals("demon")) {
					intDemon = intDemon + 1;
				}
			}
		}
		for (int i = 0; i < round.length; i++) {
			if (round[i] != null) {
				if (intTroll >= 3) {
					round[i].intAtkSpd = (int) (round[i].intAtkSpd * 1.2);
				}
				if (round[i].strClass.equals("warrior") && intWarrior >= 3 && intWarrior < 6) {
					round[i].intArmour = round[i].intArmour + 5;
				}
				if (round[i].strClass.equals("warrior") && intWarrior >= 6) {
					round[i].intArmour = round[i].intArmour + 10;
				}
				if (round[i].strRace.equals("orc") && intOrc >= 3) {
					round[i].intHealth = round[i].intHealth + 250;
				}
				if (intHunter >= 3) {
					round[i].intAtkDmg = (int) (round[i].intAtkDmg * 1.05 + 5);
				}
				if (round[i].strClass.equals("knight") && intKnight >= 3) {
					round[i].intArmour = round[i].intArmour + 7;
				}
				if (round[i].strRace.equals("human") && intHuman >= 2) {
					round[i].intAtkDmg = (int) (round[i].intAtkDmg * 1.15);
				}
				if (round[i].strClass.equals("shaman") && intShaman >= 2) {
					round[i].intAtkSpd = (int) (round[i].intAtkSpd * 1.25);
				}
				if (round[i].strRace.equals("elf") && intHuman >= 2) {
					round[i].intAtkSpd = (int) (round[i].intAtkSpd * 1.2);
					round[i].intAtkDmg = (int) (round[i].intAtkDmg * 1.1);
				}
				if (round[i].strRace.equals("demon") && intDemon == 1) {
					round[i].intAtkDmg = (int) (round[i].intAtkDmg * 1.4);
				}
				intDamage = intDamage + round[i].intAtkDmg * round[i].intAtkSpd;
				intArmour = intArmour + round[i].intArmour;
				intHealthP = intHealthP + round[i].intHealth;
			}
		}
		ssm.sendText("calculation//" + Integer.toString(intDamage) + "," + Integer.toString(intArmour) + ","
				+ Integer.toString(intHealthP));
		blnMain = true;
	}

	public static void endGame() {
		intRoundNum = intRoundNum + 1;
		System.out.println("endGame");
		if (blnServer == true) {
			if (intDamage - intHealthP2 * intArmour2 > intDamage2 - intHealthP * intArmour) {
				intHealth2 = intHealth2 - ((intDamage / 3000) + 1);
				System.out.println(intHealth2 + "," + intDamage + "," + intDamage2);
				health2.setText(Integer.toString(intHealth2));
				statusbar.setText(strName + " won with " + intDamage + " damage to " + intDamage2 + " damage");
				blnShowLabel = true;
				intGold = intGold + 1;

			} else if (intDamage2 - intHealthP * intArmour > intDamage - intHealthP2 * intArmour2) {
				intHealth = intHealth - ((intDamage2 / 3000) + 1);
				System.out.println(intHealth + "," + intDamage2 + "," + intDamage);
				health.setText(Integer.toString(intHealth));
				statusbar.setText(strName2 + " won with " + intDamage2 + " damage to " + intDamage + " damage");
				blnShowLabel = true;
			} else {
				statusbar.setText("Tie round, no damage dealt");
				blnShowLabel = true;
			}

		} else {
			if (intDamage - intHealthP2 * intArmour2 > intDamage2 - intHealthP * intArmour) {
				intHealth2 = intHealth2 - ((intDamage / 3000) + 1);
				System.out.println(intHealth2 + "," + intDamage + "," + intDamage2);
				health2.setText(Integer.toString(intHealth2));
				statusbar.setText(strName + " won with " + intDamage + " damage to " + intDamage2 + " damage");
				blnShowLabel = true;
				intGold = intGold + 1;
			} else if (intDamage2 - intHealthP * intArmour > intDamage - intHealthP2 * intArmour2) {
				intHealth = intHealth - ((intDamage2 / 3000) + 1);
				System.out.println(intHealth + "," + intDamage2 + "," + intDamage);
				health.setText(Integer.toString(intHealth));
				statusbar.setText(strName2 + " won with " + intDamage2 + " damage to " + intDamage + " damage");
				blnShowLabel = true;

			} else {
				statusbar.setText("Tie round, no damage dealt");
				blnShowLabel = true;
			}
		}
		intExp = intExp + 1;
		if (1 == intExpLeft) {
			intLevel = intLevel + 1;
			intExp = 0;
			if (intLevel == 2) {
				intTotalExp = 1;
				intExpLeft = intTotalExp;
			} else {
				intTotalExp = intTotalExp * 2;
				intExpLeft = intTotalExp;
			}
		} else {
			intExpLeft = intExpLeft - 1;
			intExp = 0;
		}
		if (intRoundNum == 1) {
			intGold = intGold + 2;
		} else if (intRoundNum == 2) {
			intGold = intGold + 3;
		} else if (intRoundNum == 4) {
			intGold = intGold + 4;
		} else {
			intGold = intGold + 5;
		}
		if (intGold >= 10 && intGold < 20) {
			intGold = intGold + 1;
		} else if (intGold >= 20 && intGold < 30) {
			intGold = intGold + 2;
		} else if (intGold >= 30 && intGold < 40) {
			intGold = intGold + 3;
		} else if (intGold >= 40 && intGold < 50) {
			intGold = intGold + 4;
		} else if (intGold >= 50) {
			intGold = intGold + 5;
		}
	}

	public static void roll() {
		int intRand = 0;
		blnroll1 = true;
		blnroll2 = true;
		blnroll3 = true;
		blnroll4 = true;
		blnroll5 = true;

		for (int i = 0; i < 5; i++) {
			if (intLevel == 1 || intLevel == 2) {
				System.out.println("roll");
				intRand = (int) (Math.random() * 5);
			} else if (intLevel == 3 || intLevel == 4) {
				intRand = (int) (Math.random() * 9);
			} else if (intLevel == 5) {
				intRand = (int) (Math.random() * 12);
			} else {
				intRand = (int) (Math.random() * 15);
			}
			roll[i] = null;
			roll[i] = pieces[intRand];
		}
		gold3.setText("GOLD: " + Integer.toString(intGold));
		roll1.setText("<html>" + roll[0].strName.toUpperCase() + "<br>" + roll[0].intLevel + " G" + "</html>");
		roll2.setText("<html>" + roll[1].strName.toUpperCase() + "<br>" + roll[0].intLevel + " G" + "</html>");
		roll3.setText("<html>" + roll[2].strName.toUpperCase() + "<br>" + roll[0].intLevel + " G" + "</html>");
		roll4.setText("<html>" + roll[3].strName.toUpperCase() + "<br>" + roll[0].intLevel + " G" + "</html>");
		roll5.setText("<html>" + roll[4].strName.toUpperCase() + "<br>" + roll[0].intLevel + " G" + "</html>");
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

	public void username() {
		GameState = 7;
		username.setVisible(true);
		username.setEnabled(true);
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
			reader = new BufferedReader(new FileReader("pieces2.csv"));
			for (int i = 0; i < 15; i++) {
				String strCSV = reader.readLine();
				String strLine[] = strCSV.split(",");
				pieces2[i] = new Chess(strLine[0], Integer.parseInt(strLine[1]), Integer.parseInt(strLine[2]),
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
			if (blnShowLabel == true) {
				intShowScreen++;
			}
			if (intShowScreen > 50) {
				blnShowLabel = false;
				intShowScreen = 0;
				roll();
			}
			if (blnCalculation == true && blnMain == true) {
				blnMain = false;
				blnCalculation = false;
				endGame();
			}
			if (blnReady == true && blnReady2 == true && blnRoundStart == false) {
				System.out.println("game.maingame");
				mainGame();
			}
			if (GameState == 1) {
				for (int i = 0; i < 15; i++) {
					intPieceNum[i] = 0;
				}
				for (int i = 0; i < 8; i++) {
					if (bench[i] != null) {
						if (bench[i].intNum < 15) {
							intPieceNum[bench[i].intNum] = intPieceNum[bench[i].intNum] + 1;
						}
					}
				}
				for (int i = 0; i < 15; i++) {
					if (intPieceNum[i] >= 3) {
						intPieceNum[i] = 0;
						for (int i2 = 0; i2 < 8; i2++) {
							if (bench[i2] != null) {
								if (bench[i2].intNum == i) {
									bench[i2] = null;
									System.out.println("1");
								}
							}
						}
						for (int i2 = 0; i2 < 8; i2++) {
							if (bench[i2] == null) {
								bench[i2] = pieces2[i];
								System.out.println("2");
								i2 = 8;
							}
						}
					}
				}
				if (intHealth <= 0) {
					GameState = 8;
				} else if (intHealth2 <= 0) {
					GameState = 9;
				}
			}
		}

		if (e.getSource() == chattext && chattext.getText().length() > 0) {
			System.out.println("test");
			ssm.sendText(strName + ": " + chattext.getText());
			chat.append(strName + ": " + chattext.getText() + "\n");
			chattext.setText("");
		}
		if (e.getSource() == ssm) {
			String strText = ssm.readText();
			System.out.println("strText: " + strText);
			if (strText.substring(0, 7).equals("start//")) {
				strText = strText.substring(7, strText.length());
				String strSplit[] = strText.split(",");
				intGold2 = Integer.parseInt(strSplit[0]);
				intHealth2 = Integer.parseInt(strSplit[1]);
				intLevel2 = Integer.parseInt(strSplit[2]);
				gold2.setText("GOLD: " + Integer.toString(intGold2));
				level2.setText("LEVEL: " + Integer.toString(intLevel2));
				health2.setText(Integer.toString(intHealth2));
			} else if (strText.substring(0, 6).equals("name//")) {
				strText = strText.substring(6, strText.length());
				strName2 = strText;
			} else if (strText.equals("ready//")) {
				statusbar.setText(strName2 + " has readied up");
				blnReady2 = true;

			} else if (strText.contains("calculation//") && strText.length() > 13) {
				System.out.println("calculation");
				strText = strText.substring(13, strText.length());
				String strSplit[] = strText.split(",");
				intDamage2 = Integer.parseInt(strSplit[0]);
				intArmour2 = Integer.parseInt(strSplit[1]);
				intHealthP2 = Integer.parseInt(strSplit[2]);
				blnCalculation = true;
			} else if (strText.substring(0, 8).equals("health//")) {
				strText = strText.substring(8, strText.length());
				intHealth2 = Integer.parseInt(strText);
				health2.setText(Integer.toString(intHealth2));
				statusbar.setText(strName + " won with " + intDamage + " damage to " + intDamage2 + " damage");
				blnShowLabel = true;
				intGold = intGold + 1;
			} else if (strText.substring(0, 9).equals("health2//")) {
				strText = strText.substring(9, strText.length());
				intHealth = Integer.parseInt(strText);
				health.setText(Integer.toString(intHealth));
				statusbar.setText(strName2 + " won with " + intDamage2 + " damage to " + intDamage + " damage");
				blnShowLabel = true;
			} else if (strText.substring(0, 8).equals("pieces//")) {
				strText = strText.substring(8, strText.length());
				board2 = strText.split(",");
				blnPieces = true;
			} else if (strText.substring(0, 10).equals("username//")) {
				strText = strText.substring(10, strText.length());
				strName2 = strText;
				name2.setText(strName2);
			} else {
				chat.append(strText + "\n");
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
			for (int i = 0; i < 8; i++) {
				if (e.getX() >= 160 + 120 * i && e.getX() <= 280 + 120 * i && e.getY() >= 554 && e.getY() <= 674
						&& e.getButton() == 1 && blnReady == false) {
					if (bench[i] != null) {
						for (int i2 = 0; i2 < intLevel; i2++) {
							if (board[i2] == null) {
								board[i2] = bench[i];
								bench[i] = null;
								i2 = intLevel + 1;
							}
						}
					}
				}
				if (e.getX() >= 160 + 120 * i && e.getX() <= 280 + 120 * i && e.getY() >= 554 && e.getY() <= 674
						&& e.getButton() == 3 && blnReady == false) {
					if (bench[i] != null) {
						intGold = intGold + bench[i].intLevel;
						gold.setText("GOLD: " + Integer.toString(intGold));
						ssm.sendText("start//" + Integer.toString(intGold) + "," + Integer.toString(intHealth) + ","
								+ Integer.toString(intLevel));
						bench[i] = null;

					}
				}
			}
			for (int i = 0; i < intLevel; i++) {
				if (i < 4) {
					if (e.getX() >= 400 + 120 * i && e.getX() <= 520 + 120 * i && e.getY() >= 270 && e.getY() <= 390
							&& e.getButton() == 1 && blnReady == false) {
						if (board[i] != null) {
							for (int i2 = 0; i2 < 8; i2++) {
								if (bench[i2] == null) {
									bench[i2] = board[i];
									board[i] = null;
									i2 = 8;
								}
							}
						}
					}
				} else {
					if (e.getX() >= -80 + 120 * i && e.getX() <= 40 + 120 * i && e.getY() >= 390 && e.getY() <= 510
							&& e.getButton() == 1 && blnReady == false) {
						if (board[i] != null) {
							for (int i2 = 0; i2 < 8; i2++) {
								if (bench[i2] == null) {
									bench[i2] = board[i];
									board[i] = null;
									i2 = 8;
								}
							}
						}
					}
				}
			}
			if (e.getX() >= 1022 && e.getX() <= 1257 && e.getY() >= 443 && e.getY() <= 510) {
				System.out.println("ready");
				statusbar.setText(strName + " has readied up");
				blnReady = true;
				ssm.sendText("ready//");

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
			if (e.getX() >= 500 && e.getX() <= 775 && e.getY() >= 216 && e.getY() <= 286 && intHelp == 0) {
				intHelp = 1;
			}
			if (e.getX() >= 500 && e.getX() <= 775 && e.getY() >= 339 && e.getY() <= 409 && intHelp == 0) {
				intHelp = 2;
			}
			if (e.getX() >= 500 && e.getX() <= 775 && e.getY() >= 462 && e.getY() <= 532 && intHelp == 0) {
				intHelp = 3;
			}
			if (e.getX() >= 528 && e.getX() <= 752 && e.getY() >= 619 && e.getY() <= 684 && intHelp == 0) {
				GameState = 0;
			}
			if (intHelp == 1 || intHelp == 2 || intHelp == 3) {
				if (e.getX() >= 520 && e.getX() <= 761 && e.getY() >= 638 && e.getY() <= 708) {
					intHelp = 0;
				}
			}
		} else if (GameState == 5) {
			if (e.getX() >= 214 && e.getX() <= 501 && e.getY() >= 287 && e.getY() <= 433) {
				ssm = new SuperSocketMaster(intPort, this);
				ssm.connect();
				System.out.println(ssm.getMyAddress());
				blnServer = true;
				username();
			}
			if (e.getX() >= 782 && e.getX() <= 1069 && e.getY() >= 287 && e.getY() <= 433) {
				blnServer = false;
				connection();
			}
		} else if (GameState == 6) {
			if (e.getX() >= 546 && e.getX() <= 727 && e.getY() >= 476 && e.getY() <= 554) {
				ssm = new SuperSocketMaster(ipaddress.getText(), intPort, this);
				ssm.connect();
				ipaddress.setVisible(false);
				ipaddress.setEnabled(false);
				username();
			}
		} else if (GameState == 7) {
			if (e.getX() >= 546 && e.getX() <= 727 && e.getY() >= 476 && e.getY() <= 554) {
				if (username.getText().length() > 9 || username.getText().length() < 3) {
					username.setText("");
				} else {
					strName = username.getText();
					name.setText(strName);
					ssm.sendText("username//" + strName);
					username.setEnabled(true);
					username.setVisible(false);
					roll();
				}
			}
		} else if (GameState == 8) {
			if (e.getX() >= 546 && e.getX() <= 727 && e.getY() >= 476 && e.getY() <= 554) {
				GameState = 0;
			}
		} else if (GameState == 9) {
			if (e.getX() >= 546 && e.getX() <= 727 && e.getY() >= 476 && e.getY() <= 554) {
				GameState = 0;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}
}
