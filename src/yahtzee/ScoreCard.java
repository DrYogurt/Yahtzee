/*     Imports and MetaData     */
package yahtzee;

import java.util.HashMap;

/*     Class     */
public class ScoreCard {
	/***** Variables *****/
	
	private HashMap<String, Score> fields;

	enum Field { // Note: You can get the string by doing Field.____.name();
		ONES,TWOS,THREES,FOURS,FIVES,SIXES,
		THREEOFKIND,FOUROFKIND,FULLHOUSE,SMALLSTRAIGHT,LARGESTRAIGHT,CHANCE,YAHTZEE
	}

	/***** Constructors *****/

	public ScoreCard() {
		this.fields = new HashMap<String, Score>();
	}

	/***** Methods *****/
	
	// Setters
	public void setField(String field,Score s) {
		this.fields.put(field,s);
	}
	public void setField(String field,int s) {
		this.setField(field, new Score(s));
	}
	
}
