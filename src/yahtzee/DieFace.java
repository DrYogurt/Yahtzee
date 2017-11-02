package yahtzee;

public enum DieFace {
    ONE             (1,
    "_ _ _\n" +
   "|     |\n" +
   "|  •  |\n" +
   "|_ _ _|"
    ),
    TWO             (2,
    "_ _ _\n" +
   "| •   |\n" +
   "|     |\n" +
   "|_ _•_|"
    ), 
    THREE           (3,
    "_ _ _\n" +
   "| •   |\n" +
   "|  •  |\n" +
   "|_ _•_|"
    ),
    FOUR            (4,
    "_ _ _\n" +
   "| • • |\n" +
   "|     |\n" +
   "|_•_•_|"
    ),
    FIVE            (5,
    "_ _ _\n" +
   "| • • |\n" +
   "|  •  |\n" +
   "|_•_•_|"
    ),
    SIX             (6,
    "_ _ _\n" +
   "| • • |\n" +
   "| • • |\n" +
   "|_•_•_|"
    );

    private String ascii;
    private int num;
    DieFace(int n,String s) {
        this.num = n;
    	this.ascii = s;
    }

    @Override
    String toString() {
    	return this.ascii;
    }

    int getNum() {
        return this.num;
    }

    static String getAscii(int n) {
        for (DieFace d : DieFace.values()) {
            if (d.getNum() == n) return d.toString();
        }
    }
}