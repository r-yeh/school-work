public class UserInterface {
	
	public static void printWelcome() {
		System.out.println("-----------------");
		System.out.println();
		System.out.println(" Welcome to 2048! ");
		System.out.println();
		System.out.println("-----------------");
	}

	public static void printGrid(GameGridT grid) {
		int[][] temp = grid.getGrid();
		int size = grid.SIZE;
		
		System.out.println("-----------------");
		
		for(int i = 0; i < size; i++) {
			int[] row = temp[i];
			System.out.print("|");
			
			for(int j = 0; j < size; j++) {
				if(row[j] == 0) {
					System.out.print("   |");
				}
				else {
					System.out.print(" " + row[j] + " |");
				}
			}
			System.out.print("\n");
			System.out.println("-----------------");
		}
	}
	
	public static void printScore(GameGridT grid) {
		int score = grid.getScore();
		
		System.out.println("Your Score: " + score);
	}
	
	public static void printHighScore() {
		int score = HighScore.getHighScore();
		
		System.out.println("High Score: " + score);
	}
	
	public static void printCongratulations() { // add to MIS
		System.out.println("-------------------");
		System.out.println("   You got 2048!   ");
		System.out.println("  				   ");
		System.out.println("     Continue?     ");
		System.out.println("-------------------");
	}
	
	public static void printGameOver() {
		System.out.println("------------------");
		System.out.println("    Game Over!    ");
		System.out.println("  				  ");
		System.out.println("    Try Again?    ");
		System.out.println("------------------");
	}
}
