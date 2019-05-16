
public class Chess {
	String strClass, strRace;
	int intLevel, intAtkDmg, intArmour, intHealth, intNum;
	boolean blnRanged;
	
	public void buy() {
		
	}
	
	public void moveToBoard() {
		
	}
	
	public void sell() {
		
	}
	
	public void moveToBench() {
		
	}
	
	public Chess(int intLevel, int intAtkDmg, int intArmour, int intHealth, int intNum, String strClass, String strRace,
			boolean blnRanged) {
		this.intLevel = intLevel;
		this.intAtkDmg = intAtkDmg;
		this.intArmour = intArmour;
		this.intHealth = intHealth;
		this.intNum = intNum;
		this.strClass = strClass;
		this.strRace = strRace;
		this.blnRanged = blnRanged;
	}
}
