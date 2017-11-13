package yahtzee;

import java.util.Arrays;
import java.util.Comparator;

enum ScoreField { // Note: You can get the string by doing Field.____.name();
		// Upper Fields
		ONES 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=(d.getValue()==1)?(d.getValue()):(0);
			return sum;
		}),
		TWOS 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=(d.getValue()==2)?(d.getValue()):(0);
			return sum;
		}),
		THREES 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=(d.getValue()==3)?(d.getValue()):(0);
			return sum;
		}),
		FOURS 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=(d.getValue()==4)?(d.getValue()):(0);
			return sum;
		}),
		FIVES 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=(d.getValue()==5)?(d.getValue()):(0);
			return sum;
		}),
		SIXES 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=(d.getValue()==6)?(d.getValue()):(0);
			return sum;
		}),
 
		// Lower Field
		THREEOFKIND 	((Die[] dice) -> {
			// TODO: for all of these, Arrays.sort(dice) then make it check them for legitimacy
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
    		&& dice[3].getValue() == dice[4].getValue())) {
    			return 25;
    		}
    		return 0;
		}),
		SMALLSTRAIGHT	((Die[] dice) -> {
			Arrays.sort(dice, new Comparator<Die>() {
    	    	public int compare(Die a, Die b) {
    	        	return Integer.compare(a.getValue(), b.getValue());
    	    	}
    		});
			outer: for (int i = 1; i < dice.length -1; i++) {
				if(!(dice[0].getValue() == (dice[i].getValue() - i))) {
					for (int j = 2; j < dice.length; j++) {
						if(!(dice[0].getValue() == (dice[j].getValue() - (j - 1)))) {
							return 0;
						}
					}
					break outer;
				}
			}
			return 30;
		}),
		LARGESTRAIGHT 	((Die[] dice) -> {
			Arrays.sort(dice, new Comparator<Die>() {
    	    	public int compare(Die a, Die b) {
    	        	return Integer.compare(a.getValue(), b.getValue());
    	    	}
    		});
			for (int i = 1; i < dice.length; i++) {
				if(dice[0].getValue() != (dice[i].getValue() - (i - 1))) {
					return 0;
				}
			}
			return 40;
		}),
		CHANCE 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=d.getValue();
			return sum;
		}),
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
			Die[] dye = new Die[dice.length];
			for (int i = 0; i < dice.length; i++) dye[i] = new Die(6, dice[i].getValue());
			return op.operate(dye);
		}

		// Interface for lambdas
		interface ScoreOperation {
			int operate(Die[] dice);
		}
	}