package securitydata;
import java.util.HashMap;

/**
 * Bond class which store bond attributes 
 * @author Sricharan
 */
public class Bond extends Security {
	
	private HashMap<String, Object> parameters;
	
	public Bond() {
		this.instrument = "bond"; 
		parameters = new HashMap<String, Object>();
	}

	public String getIstrument(){
		return instrument;
	}
	
	public void addParameter(String parameter, Object value){
		parameters.put(parameter, value);
	}
	
	@Override
	public Object getParameter(String parameter){
		if(parameters.containsKey(parameter))
			return parameters.get(parameter);
		return null;
	}
}
