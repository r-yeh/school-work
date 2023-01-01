import java.util.Arrays;

public class test {
	
	public static void main(String[] args) {
		//int[][] testArray = {{2, 0, 2, 2}, {2, 2, 0, 0}, {0, 0, 0, 0}, {2, 0, 0, 4}};
		
		
		//System.out.println(Arrays.toString(testArray[0]));
		//System.out.println(Arrays.toString(testArray[1]));
		//System.out.println(Arrays.toString(testArray[2]));
		//System.out.println(Arrays.toString(testArray[3]));
		//System.out.println();
		
		//System.out.println(MergeConditions.mergeRight(testArray, 3, 0));
		
		int[][] test = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
		int[][] testArray = {{8, 2, 2, 2}, {2, 0, 4, 0}, {2, 0, 4, 0}, {4, 0, 2, 0}};
		GameGridT grid1 = new GameGridT();
		GameGridT grid2 = new GameGridT(testArray);
		
		test[1][1] = 2;
    	test[1][3] = 4;
    	int[][] expected = {{0, 0, 0, 0}, {0, 0, 2, 4}, {0, 0, 0, 0}, {0, 0, 0, 0}};
    	MoveServices.moveTileLeft(test, 1, 1);
    	
    	System.out.println(Arrays.toString(test[0]));
    	System.out.println(Arrays.toString(test[1]));
    	System.out.println(Arrays.toString(test[2]));
    	System.out.println(Arrays.toString(test[3]));
		
		System.out.print(matchingArrays(test, testArray));
	}
	
	private static boolean matchingArrays(int[][] arr1, int[][] arr2) {
    	for(int i = 0; i < arr1.length; i++) {
    		for(int j = 0; j < arr1.length; j++) {
    			if(arr1[i][j] != arr2[i][j])
    				return false;
    		}
    	}
    	return true;
    }
}
