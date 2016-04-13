package securitydata;

/**
 * Security base class
 * @author Sricharan
 *
 */
public abstract class Security {

	public String instrument;
	
	public Security(){
		
	}
	
	public abstract Object getParameter(String parameter);
}

