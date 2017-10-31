/*     Imports and MetaData     */
package yahtzee;

/*     Class     */
public class YahtzeeGame {

	/***** Variables *****/

	private Player[] players;
	private Die[] dice;
	private int rerolls;
	private int turnCount;

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
			// Player p's move
			
			/*
			TODO:
				rolls dice for player,
				outputs calculated options - System.out.println(p.getFormattedScore(dice));
				asks player to choose score or hold/reroll dice
				repeat until 2 rerolls or player chooses score
			 */
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
