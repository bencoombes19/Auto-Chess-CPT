
public class Chess {
	/** name of the piece */
	public String strName;
	/** the class type of the piece */
	public String strClass;
	/** race of the piece */
	public String strRace;
	/** the level/price of the piece */
	public int intLevel;
	/** the attack stat of the piece determines damage done on the field */
	public int intAtkDmg;
	/** how much damage the piece can deflect/tank */
	public int intArmour;
	/** how much damage the piece can withstand before dying */
	public int intHealth;
	/** an identification number for reference */
	public int intNum;
	/** how fast the piece can attack */
	public int intAtkSpd;
	/** ranged/true pieces take less damage while melee/false take more damage */
	public boolean blnRanged;

	/**
	 * when a piece is bought on the roll screen the piece is then added to an array
	 * of Chess that holds the bench
	 */
	public void buy() {

	}

	/** moves a piece from the bench array to the board array */
	public void moveToBoard() {

	}

	/** sells a piece back for the price it was bought for */
	public void sell() {

	}

	/** moves a piece from the board array to the bench array */
	public void moveToBench() {

	}

	// 3 troll increases attack speed of all pieces +20%
	// 3/6 warrior increases armour +5/+10
	// 3 orc increases health +250 health
	// 3 hunter increases attack damage of all pieces +5% and +10
	// 3 knight increases armour +7
	// 2 human increases attack damage +15%
	// 2 shaman increases attack speed +25%
	// 2 elf increases attack speed +20% and attack damage +10%
	// 1 demon increases attack damage +40%
	/** constructs the class */
	public Chess(String strName, int intLevel, int intAtkDmg, int intArmour, int intHealth, int intNum, int intAtkSpd,
			String strClass, String strRace, boolean blnRanged) {
		this.strName = strName;
		this.intLevel = intLevel;
		this.intAtkDmg = intAtkDmg;
		this.intArmour = intArmour;
		this.intHealth = intHealth;
		this.intNum = intNum;
		this.intAtkSpd = intAtkSpd;
		this.strClass = strClass;
		this.strRace = strRace;
		this.blnRanged = blnRanged;
	}
}
