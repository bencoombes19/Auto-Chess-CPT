
public class Chess {
	String strName, strClass, strRace;
	int intLevel, intAtkDmg, intArmour, intHealth, intNum, intAtkSpd;
	boolean blnRanged;

	public void buy() {

	}

	public void moveToBoard() {

	}

	public void sell() {

	}

	public void moveToBench() {

	}

	// 3 troll increases attack speed of all pieces +20%
	// 3/6 warrior increases armour +5/+10
	// 3 orc increases health +300 health
	// 3 hunter increases attack damage of all pieces +5% and +10
	// 3 knight increases armour +5
	// 2 human increases attack damage +15%
	// 2 shaman increases attack speed +20%
	// 2 elf increases attack speed +20% and attack damage +10%
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
