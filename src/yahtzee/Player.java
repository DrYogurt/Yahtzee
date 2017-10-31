/*     Imports and MetaData     */
package yahtzee;

import java.util.HashMap;

/*     Class     */
public class Player {
	/***** Variables *****/

	private String name;
	private HashMap<String, Score> scoreCard;

	/***** Constructors *****/

	public Player(String name) {
		this.name = name;
		this.scoreCard = new HashMap<String, Score>();
	}

	public Player() {
		this(null);
	}

	/***** Methods *****/

	// Setters
	public void setScore(String field,Score s) {
		this.scoreCard.put(field,s);
	}
	public void setScore(String field,int s) {
		this.setScore(field,new Score(s));
	}

	// Getters
	public String getName() {return this.name;}
	public String getFormattedScore(Die[] dice) {
		// TODO: take in rolled dice, outputs the correct 
	}
}
