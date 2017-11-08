package yahtzee;

public class Die {
	/***** Variables *****/

	private int sides,value;
	public boolean hold;

	/***** Constructors *****/

	public Die() {
		this.sides = 6;
		this.roll();
		this.hold = false;
	}

	public Die(int sides, int initialValue) {
		this.sides = sides;
		this.value = initialValue;
		this.hold = false;
	}

	/***** Methods *****/

	public int roll() {
		if (!this.hold) this.value = (int)(Math.random() * sides + 1);
		return this.value;
	}

	@Override
	public String toString() {
		return ""+this.value;
	}

	public int getValue() {return this.value;}
}
