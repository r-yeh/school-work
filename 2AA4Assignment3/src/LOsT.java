import java.util.Objects;

public class LOsT implements Measures {
	
	protected String name;
	protected int n_blw;
	protected int n_mrg;
	protected int n_mts;
	protected int n_exc;
	
	public LOsT(String topic, int nblw, int nmrg, int nmts, int nexc) {
		if(nblw < 0 || nmrg < 0 || nmts < 0 || nexc < 0)
			throw new IllegalArgumentException("No integer inputs can be less than 0.");
		name = topic;
		n_blw = nblw;
		n_mrg = nmrg;
		n_mts = nmts;
		n_exc = nexc;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if(!(o instanceof LOsT))
			return false;
		
		LOsT temp = (LOsT) o;
		return this.getName() == temp.getName();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(n_blw, n_mrg, n_mts, n_exc);
	}
	
	public double[] measures() {
		double[] temp = new double[4];
		temp[0] = (double) n_blw;
		temp[1] = (double) n_mrg;
		temp[2] = (double) n_mts;
		temp[3] = (double) n_exc;
		
		if(!Norm.getNLOs())
			return temp;
		else
			return Services.normal(temp);
	}
	
	public double[] measures(IndicatorT ind) {
		throw new UnsupportedOperationException("This signature for measures is not"
				+ "supported for subclass LOsT.");
	}
	
	public double[] measures(AttributeT att) {
		throw new UnsupportedOperationException("This signature for measures is not"
				+ "supported for subclass LOsT.");
	}
}
