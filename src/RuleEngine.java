import rulestore.RuleStore;
import securitydata.Security;

/**
 * Rule engine interface
 * @author Sricharan
 *
 */
public interface RuleEngine {
	
	public boolean runEngine(Security instrument, RuleStore rulestore);
}
