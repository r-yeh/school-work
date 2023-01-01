public class HighScore {
	
	private static int highScore;
	
	public static void setHighScore() {
		highScore = 0;
	}
	
	public static int getHighScore() {
		return highScore;
	}
	
	public static void updateHighScore(GameGridT grid) {
		if(grid.getScore() > highScore)
			highScore = grid.getScore();
	}
}
