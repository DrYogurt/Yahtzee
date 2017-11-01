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
	public HashMap<String,Score> getScore(Die[] dice) {
		HashMap<String,Score> out = new HashMap<String,Score>();
		for (ScoreField s : ScoreField.values()) {
			out.put(s.name(),new Score(s.operate(dice)));			
		}
		return out;
	}
	public String getFormattedScore(Die[] dice) {
		String out="Section             Score\n";
		HashMap<String,Score> in = this.getScore(dice);
		for (String s : in.keySet()) {
			out += String.format("%-20s%d\n",s,in.get(s));
		}
		return out;
	}
}
