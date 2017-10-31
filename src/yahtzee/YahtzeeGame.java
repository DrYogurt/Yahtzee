/*     Imports and MetaData     */
package yahtzee;

/*     Class     */
public class YahtzeeGame {

	/***** Variables *****/

	private Player[] players;
	private Die[] dice;
	private int rerolls;
	private int turnCount;

	enum Field { // Note: You can get the string by doing Field.____.name();
		ONES,TWOS,THREES,FOURS,FIVES,SIXES,
		THREEOFKIND,FOUROFKIND,FULLHOUSE,SMALLSTRAIGHT,LARGESTRAIGHT,CHANCE,YAHTZEE
	}

	/***** Constructors *****/

	// N/A

	/***** Methods *****/

	public void start(Player[] playrs) {
		this.players = playrs;
		this.turnCount=0;
	}

	public void start(Player... playrs) {
		this.start(playrs);
	}
	
	public void turn() {
		for (Player p : this.players) {
			p.move();
		}
		turnCount++;
	}

	// Getters
	public int getTurn() {return this.turnCount;}
	public int getPlayerNum() {return this.players.length;}
	public int getDiceNum() {return this.dice.length;}

	// Setters
	// N/A
}
