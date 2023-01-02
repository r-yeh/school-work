import java.util.HashSet;

public class ProgramT extends HashSet<CourseT> implements Measures {
	
	public double[] measures() {
		throw new UnsupportedOperationException("This signature for measures is not"
				+ "supported for ProgramT.");
	}
	
	public double[] measures(IndicatorT ind) {
		throw new UnsupportedOperationException("This signature for measures is not"
				+ "supported for ProgramT.");
	}
	
	public double[] measures(AttributeT att) {
		double[] temp = {0.0, 0.0, 0.0, 0.0};
		
		for(CourseT c : this) {
			temp = sumMeas(temp, c.measures(att));
		}
		
		return Services.normal(temp);
	}
	
	private double[] sumMeas(double[] a, double[] b) {
		double[] temp = new double[4];
		
		for(int i = 0; i < 4; i++) {
			temp[i] = a[i] + b[i];
		}
		
		return temp;
	}
}
