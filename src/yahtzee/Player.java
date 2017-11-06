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

	public int totalScore() {
		int out=0;
		for (String s : this.scoreCard.keySet()) {
			out += this.scoreCard.get(s).getPoints();
		}
		return out;
	}

	// Setters
	public void setScore(String field,Score s) {
		this.scoreCard.put(field,s);
	}
	public void setScore(String field,int s) {
		this.setScore(field,new Score(s));
	}

	// Getters
	public String getName() {return this.name;}
	public Score getScore(String field) {
		return this.scoreCard.get(field);
	}
	public boolean isFull() {
		for (ScoreField s : ScoreField.values()) {
			if (this.scoreCard.get(s.name()) == null) return false;
		}
		return true;
	}

	public HashMap<String, Score> getScoreCard() {
		return this.scoreCard;
	}
	public String getFormattedScoreCard() {
		String out="Section             Score\n";

		for (String s : this.scoreCard.keySet())
			out += String.format("%-20s%d\n",s,this.scoreCard.get(s));

		return out;
	}

	public HashMap<String,Score> getScoreOf(Die[] dice) {
		HashMap<String,Score> out = new HashMap<String,Score>();
		for (ScoreField s : ScoreField.values()) {
			out.put(s.name(),new Score(s.operate(dice)));			
		}
		return out;
	}
	public String getFormattedScoreOf(Die[] dice) {
		String out="Section             Score Choices\n";
		HashMap<String,Score> in = this.getScoreOf(dice);

		for (String s : in.keySet())
			out += String.format("%-20s%d\n",s,in.get(s));

		return out;
	}
}
