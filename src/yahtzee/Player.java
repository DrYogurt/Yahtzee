/*     Imports and MetaData     */
package yahtzee;

/*     Class     */
public class Player {
	/***** Variables *****/
	
	protected ScoreCard score;
	private String name;

	/***** Constructors *****/

	public Player(String name) {
		this.name = name;
	}

	public Player() {
		this(null);
	}

	/***** Methods *****/

	public Die[] roll(Die[] dice) {
		// TODO: will roll dice
		
	}

	// Getters
	public String getName() {return this.name;}
}
