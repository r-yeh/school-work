public class MoveServices {

	public static void moveTileUp(int[][] grid, int row, int col) {
		if(grid[row][col] != 0) {
			while(row > 0 && grid[row-1][col] == 0) {
				int substitute = grid[row][col];
				grid[row][col] = grid[row-1][col];
				grid[row-1][col] = substitute;
			
				row--;
			}
		}
	}
	
	public static void moveTileDown(int[][] grid, int row, int col) {
		if(grid[row][col] != 0) {
			while(row < grid.length - 1 && grid[row+1][col] == 0) {
				int substitute = grid[row][col];
				grid[row][col] = grid[row+1][col];
				grid[row+1][col] = substitute;
				
				row++;
			}
		}
	}
	
	public static void moveTileLeft(int[][] grid, int row, int col) {
		if(grid[row][col] != 0) {
			while(col > 0 && grid[row][col-1] == 0) {
				int substitute = grid[row][col];
				grid[row][col] = grid[row][col-1];
				grid[row][col-1] = substitute;
				
				col--;
			}
		}
	}
	
	public static void moveTileRight(int[][] grid, int row, int col) {
		if(grid[row][col] != 0) {
			while(col < grid[row].length - 1 && grid[row][col+1] == 0) {
				int substitute = grid[row][col];
				grid[row][col] = grid[row][col+1];
				grid[row][col+1] = substitute;
				
				col++;
			}
		}
	}
}
