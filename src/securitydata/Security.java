package securitydata;

/**
 * Security base class
 * @author Sricharan
 *
 */
public abstract class Security {

	public String instrument;
	
	public abstract Object getParameter(String parameter);
	public abstract void addParameter(String parameter, Object value);
}

