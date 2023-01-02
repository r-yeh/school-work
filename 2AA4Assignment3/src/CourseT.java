import java.util.HashMap;
import java.util.HashSet;

public class CourseT implements Measures {

	protected String name;
	protected HashMap<IndicatorT, HashSet<LOsT>> m = new HashMap<>();
	
	public CourseT(String courseName, IndicatorT[] indicators) {
		name = courseName;
		
		for(IndicatorT i : indicators) {
			m.put(i, new HashSet<LOsT>());
		}
	}
	
	public String getName() {
		return name;
	}
	
	public IndicatorT[] getIndicators() {
		IndicatorT[] temp = new IndicatorT[m.size()];
		int count = 0;
		
		for(IndicatorT i : m.keySet()) {
			temp[count] = i;
			count++;
		}
		
		return temp;
	}
	
	public LOsT[] getLOs(IndicatorT indicator) {
		HashSet<LOsT> temp = m.get(indicator);
		LOsT[] out = new LOsT[temp.size()];
		int count = 0;
		
		for(LOsT i : temp) {
			out[count] = i;
			count++;
		}
		
		return out;
	}
	
	public void addLO(IndicatorT indicator, LOsT outcome) {
		if(m.containsKey(indicator)) {
			m.get(indicator).add(outcome);
		}
	}
	
	public void delLO(IndicatorT indicator, LOsT outcome) {
		if(m.containsKey(indicator)) {
			m.get(indicator).remove(outcome);
		}
	}
	
	public boolean member(IndicatorT indicator, LOsT[] outcomes) {
		if(m.containsKey(indicator)) {
			HashSet<LOsT> temp = m.get(indicator);
			
			for(LOsT i : outcomes) {
				if(!(temp.contains(i)))
					return false;
			}
			return true;
		}
		
		return false;
	}
	
	public double[] measures() {
		throw new UnsupportedOperationException("This signature for measures is not"
				+ "supported for CourseT.");
	}
	
	public double[] measures(IndicatorT ind) {
		if(m.containsKey(ind)) {
			HashSet<LOsT> temp = m.get(ind);
			double[] measureInd = {0.0, 0.0, 0.0, 0.0};
		
			for(LOsT i : temp) {
				measureInd = this.sumMeas(measureInd, i.measures());
			}
		
			if(Norm.getNInd())
				return Services.normal(measureInd);
			else
				return measureInd;
		}
		return new double[] {0.0, 0.0, 0.0, 0.0};
	}
	
	public double[] measures(AttributeT att) {
		IndicatorT[] temp = att.getIndicators();
		double[] measureInd = {0.0, 0.0, 0.0, 0.0};
		
		for(IndicatorT i : temp) {
			measureInd = this.sumMeas(measureInd, this.measures(i));
		}
		
		if(Norm.getNAtt())
			return Services.normal(measureInd);
		else
			return measureInd;
	}
	
	protected double[] sumMeas(double[] a, double[] b) {
		double[] temp = new double[4];
		
		for(int i = 0; i < 4; i++) {
			temp[i] = a[i] + b[i];
		}
		
		return temp;
	}
}
