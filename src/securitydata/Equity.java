package securitydata;
import java.util.HashMap;

/**
 * Equity class which store equity attributes 
 * @author Sricharan
 */
public class Equity extends Security {

	private HashMap<String, Object> parameters;
	
	public Equity() {
		this.instrument = "equity"; 
		parameters = new HashMap<String, Object>();
	}

	public String getIstrument(){
		return instrument;
	}
	
	public void addParameter(String parameter, Object value){
		parameters.put(parameter, value);
	}
	
	public Object getParameter(String parameter){
		if(parameters.containsKey(parameter))
			return parameters.get(parameter);
		return null;
	}
}
