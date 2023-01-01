import java.lang.Math;

public class GameGridT {
	
	private int[][] g;
	private int score;
	private boolean end;
	
	public final static int SIZE = 4;
	
	public GameGridT() {
		int[][] temp = new int[4][4];
		
		for(int i = 0; i < 2; i++) {
			double x = Math.random();
			int a = randNum();
			int b = randNum();
			
			while(temp[a][b] != 0) {
				a = randNum();
				b = randNum();
			}
			
			if(x > 0.8 && count(temp, 4) < 1)
				temp[a][b] = 4;
			else
				temp[a][b] = 2;
		}
		
		g = temp;
		score = 0;
		end = false;
	}
	
	public GameGridT(int[][] grid) {
		if(grid.length != SIZE && grid[0].length != SIZE && grid[1].length != SIZE &&
				grid[2].length != SIZE && grid[3].length != SIZE)
			throw new IllegalArgumentException("The grid must be 4 x 4.");
		
		if(count(grid, 0) > 14)
			throw new IllegalArgumentException("There must be at least two nonzero values present in the grid.");
		
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(!(grid[i][j] == 0) && !(log2(grid[i][j]) % 1 == (double)0))
					throw new IllegalArgumentException("Values must either be 0 or a power of 2.");
			}
		}
		
		g = grid;
		score = 0;
		end = false;
	}
	
	public int[][] getGrid() {
		return g;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean getEndStatus() {
		return end;
	}
	
	public void setTile(int row, int col, int tile) {
		if((row < 0 || row >= SIZE) || (col < 0 || col >= SIZE))
			throw new IndexOutOfBoundsException("Both row and col must be between 0 and 3.");
		
		if(tile != 2 || tile != 4)
			throw new IllegalArgumentException("The input tile must be 2 or 4.");
		
		g[row][col] = tile;
	}
	
	public void mergeTiles(int row1, int col1, int row2, int col2) {
		if((row1 < 0 || row1 >= SIZE) || (col1 < 0 || col1 >= SIZE))
			throw new IndexOutOfBoundsException("Both row and col must be between 0 and 3.");
		
		if((row2 < 0 || row2 >= SIZE) || (col2 < 0 || col2 >= SIZE))
			throw new IndexOutOfBoundsException("Both row and col must be between 0 and 3.");
		
		if(g[row1][col1] == g[row2][col2] && g[row1][col1] != 0 && g[row2][col2] != 0) {
			g[row1][col1] = g[row1][col1] * 2;
			g[row2][col2] = 0;
			
			score += g[row1][col1];
		}
	}
	
	public void moveUp() {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				MoveServices.moveTileUp(g, i, j);
			}
		}
		for(int i = 0; i < SIZE; i++) {
			for(int j = 1; j < SIZE; j++) {
				this.mergeTiles(j-1, i, j, i);
			}
		}
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				MoveServices.moveTileUp(g, i, j);
			}
		}
	}
	
	public void moveDown() {
		for(int i = SIZE - 1; i >= 0; i--) {
			for(int j = 0; j < SIZE; j++) {
				MoveServices.moveTileDown(g, i, j);
			}
		}
		for(int i = 0; i < SIZE; i++) {
			for(int j = 2; j >= 0; j--) {
				this.mergeTiles(j+1, i, j, i);
			}
		}
		for(int i = SIZE - 1; i >= 0; i--) {
			for(int j = 0; j < SIZE; j++) {
				MoveServices.moveTileDown(g, i, j);
			}
		}
	}
	
	public void moveLeft() {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				MoveServices.moveTileLeft(g, j, i);
			}
		}
		for(int i = 0; i < SIZE; i++) {
			for(int j = 1; j < SIZE; j++) {
				this.mergeTiles(i, j-1, i, j);
			}
		}
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				MoveServices.moveTileLeft(g, j, i);
			}
		}
	}
	
	public void moveRight() {
		for(int i = SIZE - 1; i >= 0; i--) {
			for(int j = 0; j < SIZE; j++) {
				MoveServices.moveTileRight(g, j, i);
			}
		}
		for(int i = 0; i < SIZE; i++) {
			for(int j = 2; j >= 0; j--) {
				this.mergeTiles(i, j+1, i, j);
			}
		}
		for(int i = SIZE - 1; i >= 0; i--) {
			for(int j = 0; j < SIZE; j++) {
				MoveServices.moveTileRight(g, j, i);
			}
		}
	}
	
	public void endGame() {
		if(count(g, 0) == 0) {
			for(int i = 0; i < SIZE; i++) {
				for(int j = 0; j < SIZE; j++) {
					if(MergeConditions.mergeUp(g, i, j) || MergeConditions.mergeDown(g, i, j)
							|| MergeConditions.mergeLeft(g, i, j) || MergeConditions.mergeRight(g, i, j))
						return;
				}
			}
			end = true;
		}
	}
	
	private int count(int[][] grid, int x) {
		int count = 0;
		
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(grid[i][j] == x)
					count++;
			}
		}
		
		return count;
	}
	
	private int randNum() {
		double x = Math.random();
		
		if(x < 0.25)
			return 0;
		else if(0.25 <= x && x < 0.5)
			return 1;
		else if(0.5 <= x && x < 0.75)
			return 2;
		else
			return 3;
	}
	
	private double log2(int x) {
		return Math.log(x) / Math.log(2);
	}
}
