import java.util.Arrays;
import java.lang.Math;

public class test {
	private static boolean testArrayEquality(double[] calc, double[] expected, double epsilon) {
		for(int i = 0; i < calc.length; i++) {
			double temp = Math.abs(calc[i] - expected[i]);

			if(!(temp / expected[i] < epsilon))
				return false;
		}

		return true;
	}
	public static void main(String[] args) {
		CourseT test = new CourseT("test", new IndicatorT[] {IndicatorT.engInSoc});
		System.out.println(Arrays.toString(test.measures(IndicatorT.engInSoc)));
	}
}
