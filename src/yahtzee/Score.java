/*     Imports and MetaData     */
package yahtzee;

/*     Class     */
public class Score {
	/***** Variables *****/
	
	private final int points;

	/***** Constructors *****/
	
	public Score(int points) {
		this.points = points;
	}

	public Score() {
		this(0);
	}

	/***** Methods *****/

	public int getPoints() {
		return this.points;
	}

	@Override
	public String toString() {
		return ""+this.points;
	}
}
