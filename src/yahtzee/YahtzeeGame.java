/*     Imports and MetaData     */
package yahtzee;

/*     Class     */
public class YahtzeeGame {

	/***** Variables *****/

	public static final int // Settings
		REROLLS = 2,
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
		
		System.out.println("New game started!");
	}

	public void startList(Player... playrs) {
		this.start(playrs);
	}
	
	public void turn() {
		for (Player p : this.players) {
			// Player p's move
			System.out.println("Starting player "+p.getName()+"'s turn.");
			
			// Resetting things
			int rerolls = YahtzeeGame.REROLLS;String in;boolean heldany;
			for (Die d : this.dice) d.hold = false;

			System.out.println("Rolling dice...");
			rer: while (rerolls >= 0) {
				System.out.println("Your dice were: ");
				
				// Rolling Dice
				for (int i=0;i<this.dice.length;i++) {
					if (!this.dice[i].hold) this.dice[i].roll();
					System.out.println((i+1)+": "+this.dice[i]);
					if (this.dice[i].hold) System.out.println("You held this dice's value of "+this.dice[i]);
					System.out.println(DieFace.getAscii(this.dice[i]));
					this.dice[i].hold = false;
				}

				System.out.println("Your options are:");
				System.out.println(p.getFormattedScoreOf(dice));
				do {
					// Player Input
					System.out.println("You have "+rerolls+" rerolls left.");
					System.out.println("Please enter\na) A score field to accept that score (that you have not filled already) or \nb) The dice you want to keep (numbers, separated by commas)");
					in = TextIO.getln();

					// Checking for score field, break
					for (ScoreField s : ScoreField.values()) {
						if (in.toUpperCase().equals(s.name()) && p.getScore(s.name()) == null) {
							System.out.println("You chose to accept the score "+s.name()+", getting you "+s.operate(dice)+" points.");
							rerolls = 1000;
							break rer;
						}
					}
					// Checking through which to hold
					heldany = false;
					for (char c : in.toCharArray()) { // <- may not be the best way to do it, costly function
						if (Character.isDigit(c)) {
							this.dice[Integer.parseInt(""+c)-1].hold = true;
							heldany = true;
						}
					}
				} while (!heldany);

				System.out.println("Rerolling dice...");
				rerolls--;
			}

			if (rerolls <= 0) {
				System.out.println("You have no more rerolls.\nPlease choose one of the following:");
				System.out.println(p.getFormattedScoreOf(dice));
				boi: while (true) {
					in = TextIO.getln();
					// Checking for score field
					for (ScoreField s : ScoreField.values()) {
						if (in.toUpperCase().equals(s.name()) && p.getScore(s.name()) == null) {
							System.out.println("You chose to accept the score "+s.name()+", getting you "+s.operate(dice)+" points.");
							break boi;
						}
					}
					System.out.println("Please choose a score field you have not chosen before.");
				}
			}

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
		for(ScoreField f : ScoreField.values()) {
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
