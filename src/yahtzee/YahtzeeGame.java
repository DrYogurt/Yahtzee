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

	public YahtzeeGame() {
		// Default Constructor
	}

	/***** Methods *****/

	public void start(Player[] playrs) {
		this.players = playrs;
		this.turnCount = 1;

		this.dice = new Die[YahtzeeGame.DICENUM];
		for (int i=0;i<this.dice.length;i++) this.dice[i] = new Die();
	}

	public void startList(Player... playrs) {
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
				for (int i=1;i<=this.dice.length;i++) {
					System.out.println(i+": "+this.dice[i-1].roll()+"\n"+DieFace.getAscii(this.dice[i]));
					this.dice[i-1].hold = false;
				}

				// Player Options
				System.out.println("Your options are:");
				System.out.println(p.getFormattedScoreOf(dice));
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
				for (char c : in.toCharArray()) { // <- may not be the best way to do it, costly function
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

	public void over() {
		System.out.println("Game over! Here is the final scoreboard.");
		System.out.println(this.getFormattedScores());
		System.out.println("The winner was....... "+this.getWinner().getName());
	}

	public String getFormattedScores() {
		// Header
		String out = String.format("%-15s", "Section");
		for (Player p : this.players) out += String.format("%-10s",p.getName());


		// Actual table
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

	public Player getWinner() {
		Player highest = new Player(); // Throwaway player object
		for (Player p : this.players) {
			if (p.totalScore() > highest.totalScore()) highest = p;
		}
		return highest;
	}

	// Getters
	public Player[] getPlayers() {return this.players;}
	public Die[] getDice() {return this.dice;}
	public int getTurn() {return this.turnCount;}
	public int getPlayerNum() {return this.players.length;}
	public int getDiceNum() {return this.dice.length;}

	// Setters
	// N/A
}
