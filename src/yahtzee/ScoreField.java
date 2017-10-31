enum ScoreField { // Note: You can get the string by doing Field.____.name();
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

		THREEOFKIND 	((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=d.getValue();
			return sum;
		}),
		FOUROFKIND 		((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=d.getValue();
			return sum;
		}),
		FULLHOUSE 		((Die[] dice) -> {return 25;}),
		SMALLSTRAIGHT	((Die[] dice) -> {return 30;}),
		LARGESTRAIGHT 	((Die[] dice) -> {return 40;}),
		CHANCE 			((Die[] dice) -> {
			int sum=0;
			for (Die d : dice) sum+=d.getValue();
			return sum;
		}),
		YAHTZEE 		((Die[] dice) -> {return 50;});

		// Class Info

		ScoreOperation op;
		ScoreField(ScoreOperation op) {
			this.op = op;
		}

		int operate(Die[] dice) {
			return op.operate(dice);
		}

		// ScoreField.ONES.operate(dice);

		// 
		interface ScoreOperation {
			int operate(Die[] dice);
		}
	}