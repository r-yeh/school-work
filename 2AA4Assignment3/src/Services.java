public class Services {
	
	public static double[] normal(double[] v) {
		double total = 0.0;
		double[] temp = new double[v.length];
		
		for(double i : v) {
			total += i;
		}
		
		for(int i = 0; i < v.length; i++) {
			temp[i] = v[i] / total;
		}
		
		return temp;
	}
}
