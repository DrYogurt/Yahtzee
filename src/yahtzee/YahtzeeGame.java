/*     Imports and MetaData     */
package yahtzee;

/*     Class     */
public class YahtzeeGame {

	/***** Variables *****/

	public static final int // Settings
		REROLLS = 3,
		DICENUM = 5;
	private Player[] players;
	private Die[] dice;
	private int rerolls;
	private int turnCount;

	/***** Constructors *****/

	// N/A

	/***** Methods *****/

	public void start(Player[] playrs) {
		this.players = playrs;
		this.turnCount = 0;

		this.dice = new Die[YahtzeeGame.DICENUM];
		for (int i=0;i<this.dice.length;i++) this.dice[i] = new Die();
	}

	public void start(Player... playrs) {
		this.start(playrs);
	}
	
	public void turn() {
		for (Player p : this.players) {
			// Player p's move
			System.out.println("Starting player "+p.getName()+"'s turn.");
			
			int rerolls = 0;String in;
			System.out.println("Rolling dice...");
			rer: do {
				System.out.println("Your dice were: ");
				
				// Rolling Dice
				for (int i=0;i<this.dice.length;i++) {
					System.out.println((i+1)+": "+this.dice[i].roll()+"\n"+DieFace.getAscii(this.dice[i]));
					this.dice[i].hold = false;
				}

				// Player Options
				System.out.println("Your options are:");
				System.out.println(p.getFormattedScore(dice));
				System.out.println("You can choose to keep some dice when rerolling by typing out the dice numbers, separated by commas, or "+
					"you can type in the name of a field (e.g. ONES) to choose that score");

				in = TextIO.getln();
				// Checking for score field, break
				for (ScoreField s : ScoreField.values()) {
					if (in.toUpperCase().equals(s.name())) {
						System.out.println("You chose to accept the score "+s.name()+", getting you "+s.operate(dice)+"points");
						break rer;
					}
				}
				// Checking through which to hold
				for (char c : in.toCharArray()) {
					if (Character.isDigit(c)) {
						this.dice[Integer.parseInt(""+c)-1].hold = true;
					}
				}

				System.out.println("Rerolling dice...");
			} while (rerolls < YahtzeeGame.REROLLS);

			System.out.print("Player "+p.getName()+"'s turn is over. ");
		}
		
		turnCount++;
	}

	public String getFormattedScores() {
		String out = String.format("%-15s", "Section") + String.format("%-10s", "Player 1") + String.format("%-10s",
				"Player 2");
		for(ScoreFiled f : ScoreField.values()) {
			out += "\n" + String.format("%-15s", f.toString());
			for(Player p : this.players) {
				out += String.format("%-10s", p.getScoreCard().get(f.name()));
			}
		}

		//Take in score cards
		//Place into String
		return out;
	}

	// Getters
	public int getTurn() {return this.turnCount;}
	public int getPlayerNum() {return this.players.length;}
	public int getDiceNum() {return this.dice.length;}

	// Setters
	// N/A
}
