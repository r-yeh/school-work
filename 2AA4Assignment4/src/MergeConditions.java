public class MergeConditions {

	public static boolean mergeUp(int[][] grid, int row, int col) {
		if(grid[row][col] == 0)
			return false;
		
		for(int i = row - 1; i >= 0; i--) {
			if(grid[i][col] == grid[row][col]) {
				for(int j = i + 1; j < row; j++) {
					if(grid[j][col] != 0)
						return false;
				}
				return true;
			}
		}
		return false;
	}
	
	public static boolean mergeDown(int[][] grid, int row, int col) {
		if(grid[row][col] == 0)
			return false;
		
		for(int i = row + 1; i <= grid.length - 1; i ++) {
			if(grid[i][col] == grid[row][col]) {
				for(int j = i - 1; j > row; j--) {
					if(grid[j][col] != 0)
						return false;
				}
				return true;
			}
		}
		return false;
	}
	
	public static boolean mergeLeft(int[][] grid, int row, int col) {
		if(grid[row][col] == 0)
			return false;
		
		for(int i = col - 1; i >= 0; i--) {
			if(grid[row][i] == grid[row][col]) {
				for(int j = i + 1; j < col; j++) {
					if(grid[row][j] != 0)
						return false;
				}
				return true;
			}
		}
		return false;
	}
	
	public static boolean mergeRight(int[][] grid, int row, int col) {
		if(grid[row][col] == 0)
			return false;
		
		for(int i = col + 1; i <= grid[row].length - 1; i++) {
			if(grid[row][i] == grid[row][col]) {
				for(int j = i - 1; j > col; j--) {
					if(grid[row][j] != 0)
						return false;
				}
				return true;
			}
		}
		return false;
	}
}
