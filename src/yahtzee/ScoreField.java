package yahtzee;

import java.util.Arrays;
import java.util.Comparator;

enum ScoreField { // Note: You can get the string by doing Field.____.name();
		// Upper Fields
		/**
		 * @param dice a Die array for calculation	
		 * @return the sum of the ones in the array
		 */
		ONES 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=(d.getValue()==1)?(d.getValue()):(0);
			return sum;
		}),
		/**
		 * @param dice a Die array for calculation	
		 * @return the sum of the twos in the array
		 */
		TWOS 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=(d.getValue()==2)?(d.getValue()):(0);
			return sum;
		}),
		/**
		 * @param dice a Die array for calculation	
		 * @return the sum of the threes in the array
		 */
		THREES 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=(d.getValue()==3)?(d.getValue()):(0);
			return sum;
		}),
		/**
		 * @param dice a Die array for calculation	
		 * @return the sum of the fours in the array
		 */
		FOURS 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=(d.getValue()==4)?(d.getValue()):(0);
			return sum;
		}),
		/**
		 * @param dice a Die array for calculation	
		 * @return the sum of the fives in the array
		 */
		FIVES 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=(d.getValue()==5)?(d.getValue()):(0);
			return sum;
		}),
		/**
		 * @param dice a Die array for calculation	
		 * @return the sum of the sixes in the array
		 */
		SIXES 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=(d.getValue()==6)?(d.getValue()):(0);
			return sum;
		}),
 
		// Lower Field
		/**
		 * @param dice a Die array for calculation	
		 * @return 0 if not 3 of a kind, sum of die array if 3 of a kind
		 */
		THREEOFKIND 	((Die[] dice) -> {
			Arrays.sort(dice, new Comparator<Die>() {
    	    	public int compare(Die a, Die b) {
    	        	return Integer.compare(a.getValue(), b.getValue());
    	    	}
    		});
    		
    		for(int i = 2; i < dice.length; i++) {
    			if(dice[i].getValue() == dice[i - 1].getValue() && dice[i].getValue() == dice[i - 2].getValue()) {
    				int sum=0;
					for (Die d : dice) sum+=d.getValue();
					return sum;
    			}
    		}
    		return 0;
		}),
		/**
		 * @param dice a Die array for calculation	
		 * @return 0 if not 4 of a kind, sum of die array if 4 of a kind
		 */
		FOUROFKIND 		((Die[] dice) -> {
			Arrays.sort(dice, new Comparator<Die>() {
    	    	public int compare(Die a, Die b) {
    	        	return Integer.compare(a.getValue(), b.getValue());
    	    	}
    		});
    		
    		for(int i = 3; i < dice.length; i++) {
    			if(dice[i].getValue() == dice[i - 1].getValue() && dice[i].getValue() == dice[i - 2].getValue() && dice[i].getValue() == dice[i - 3].getValue()) {
    				int sum=0;
					for (Die d : dice) sum+=d.getValue();
					return sum;
    			}
    		}
    		return 0;
		}),
		/**
		 * @param dice a Die array for calculation	
		 * @return 25 if full house, 0 if not
		 */
		FULLHOUSE 		((Die[] dice) -> {
			Arrays.sort(dice, new Comparator<Die>() {
    	    	public int compare(Die a, Die b) {
    	        	return Integer.compare(a.getValue(), b.getValue());
    	    	}
    		});
    		if((dice[0].getValue() == dice[1].getValue()
    		&& dice[2].getValue() == dice[3].getValue()
    		&& dice[2].getValue() == dice[4].getValue())
    		||(dice[0].getValue() == dice[1].getValue()
    		&& dice[0].getValue() == dice[2].getValue()
    		&& dice[3].getValue() == dice[4].getValue()) {
    			return 25;
    		}
    		return 0;
		}),
    	/**
    	 * calculates whether or not a given array is a small straight
		 * @param dice a Die array for calculation	
		 * @return 30 if smaill straight, 0 if not
		 */
		SMALLSTRAIGHT	((Die[] dice) -> {
			Arrays.sort(dice, new Comparator<Die>() {
    	    	public int compare(Die a, Die b) {
    	        	return Integer.compare(a.getValue(), b.getValue());
    	    	}
    		});
			for (int i = 0; i < dice.length -1; i++) {
				if(!(dice[0] == (dice[i] - i))) {
					return 0;
				}
			}
			for (int i = 1; i < dice.length; i++) {
				if(!(dice[0] == (dice[i] - (i - 1))) {
					return 0;
				}
			}
			return 30;
		}),
		/**
    	 * calculates whether or not a given array is a large straight
		 * @param dice a Die array for calculation	
		 * @return 40 if large straight, 0 if not
		 */
		LARGESTRAIGHT 	((Die[] dice) -> {
			Arrays.sort(dice, new Comparator<Die>() {
    	    	public int compare(Die a, Die b) {
    	        	return Integer.compare(a.getValue(), b.getValue());
    	    	}
    		});
			for (int i = 0; i < dice.length; i++) {
				if(!(dice[0] == (dice[i] - (i - 1))) {
					return 0;
				}
			}
			return 40;
		}),
		/**
    	 * finds the score for a chance
		 * @param dice a Die array for calculation	
		 * @return sum of the dice values
		 */
		CHANCE 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=d.getValue();
			return sum;
		}),
		/**
    	 * calculates whetehr or not a given array is a yahtzee
		 * @param dice a Die array for calculation	
		 * @return 50 if Yahtzee, 0 if not
		 */
		YAHTZEE 		((Die[] dice) -> {
			Arrays.sort(dice, new Comparator<Die>() {
    	    	public int compare(Die a, Die b) {
    	        	return Integer.compare(a.getValue(), b.getValue());
    	    	}
    		});
			return (dice[0] == dice[dice.length - 1])?(50):(0);
		});

		// Class Info

		private ScoreOperation op;
		ScoreField(ScoreOperation op) {
			this.op = op;
		}

		// Usage: ScoreField.ONES.operate(dice);
		int operate(Die[] dice) {
			return op.operate(dice);
		}

		// Interface for lambdas
		interface ScoreOperation {
			int operate(Die[] dice);
		}
	}