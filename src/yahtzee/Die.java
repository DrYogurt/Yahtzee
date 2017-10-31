package yahtzee;

public class Die {
	/***** Variables *****/

	private int sides,value;

	/***** Constructors *****/

	public Die() {
		this.sides = 6;
		this.roll();
	}

	public Die(int sides, int initialValue) {
		this.sides = sides;
		this.value = initialValue;
	}

	/***** Methods *****/

	public int roll() {
		this.value = (int)(Math.random() * sides + 1);
		return this.value;
	}

	public String toString() {
		return "Sides: "+this.sides+", Value: "+this.value;
	}

	public int getValue() {return this.value;}
}
