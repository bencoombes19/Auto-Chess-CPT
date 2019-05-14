
public class Chess {
	String strClass, strRace;
	int intNum, intLevel, intAtkDmg, intArmour, intHealth;
	boolean blnRanged;

	public Chess(int intNum, int intLevel, int intAtkDmg, int intArmour, int intHealth, String strClass, String strRace,
			boolean blnRanged) {
		this.intNum = intNum;
		this.intLevel = intLevel;
		this.intAtkDmg = intAtkDmg;
		this.intArmour = intArmour;
		this.intHealth = intHealth;
		this.strClass = strClass;
		this.strRace = strRace;
		this.blnRanged = blnRanged;
	}
}
