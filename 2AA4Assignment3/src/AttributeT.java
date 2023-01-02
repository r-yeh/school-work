import java.util.HashSet;

public class AttributeT {
	
	protected String name;
	protected HashSet<IndicatorT> s = new HashSet<IndicatorT>();
	
	public AttributeT(String n, IndicatorT[] indicators){
		name = n;
		
		for(IndicatorT i : indicators) {
			s.add(i);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public IndicatorT[] getIndicators() {
		IndicatorT[] temp = new IndicatorT[s.size()];
		int count = 0;
		
		for(IndicatorT i : s) {
			temp[count] = i;
			count++;
		}
		
		return temp;
	}
}
