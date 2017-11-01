/*     Imports and MetaData     */
package yahtzee;

/*     Class     */
public class YahtzeeGame {

	/***** Variables *****/

	public static final int REROLLS = 3;
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
			
			int rerolls = 0;
			rer: do {
				System.out.println("Your dice were: ");
				
				// Rolling Dice
				for (int i=0;i<this.dice.length;i++)
					System.out.println(i+": "+this.dice[i].roll()/*+DieFace.getAscii(this.dice[i])*/);

				// Player Options
				System.out.println("Your options are:");
				System.out.println(p.getFormattedScore(dice));
				System.out.println("You can choose to keep some dice when rerolling by typing out the dice numbers, separated by commas, or "+
					"you can type in the name of a field (e.g. ONES) to choose that score");

				for (ScoreField.)
				for (int i)
			} while (rerolls < REROLLS);
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
