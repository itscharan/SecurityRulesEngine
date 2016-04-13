import java.util.List;

import org.joda.time.LocalDate;

import rulestore.Rule;
import rulestore.RuleStore;
import securitydata.Security;



/**
 * A rule engine which runs all rules on the given financial data
 * @author Sricharan
 *
 */
public class RuleEngineImpl implements RuleEngine {
	
	public List<Rule> ruleslist;
	
	public RuleEngineImpl(){
		
	}
	
	/**
	 * 
	 */
	@Override
	public boolean runEngine(Security instrument, RuleStore rulestore) {
		this.ruleslist = rulestore.getRulesList();
		
		if(!(ruleslist.size() >0))
			return true;
		
		for(Rule rule: ruleslist){
			//if(instrument.getParameter("instrument").equals(rule.instrument)){
			
			if(instrument.instrument.equals(rule.instrument)){
				
				System.out.println("Applying rule: " + rule.getName());
			
				if(instrument.getParameter(rule.parameter.toLowerCase()) != null){
					
					switch(rule.type){
					case DATE:
						LocalDate actual = (LocalDate)instrument.getParameter(rule.parameter.toLowerCase());
						LocalDate event = rule.eventDate;
						
						if(actual.compareTo(event) > 0)
							;//DO nothing here and let it continue to next rule
						else{
							System.out.println("Instrument failed as per: " + rule.name);
							return false;
						}
								
						break;
					case NUMERIC:
						//TODO implement relational operator logic to compare value with rule value
							
						break;
					case TEXT:
							if(rule.expr.equals("EQUAL") && rule.val.equals((String)instrument.getParameter(rule.parameter.toLowerCase()))){
								//accepted and passed the rule
							}else if (rule.expr.equals("NEQUAL") && !(rule.val.equals((String)instrument.getParameter(rule.parameter.toLowerCase())))){
								//accepted and passed the rule
							} else{
								System.out.println("Instrument failed as per: " + rule.name);
								return false;
							}
						break;
					case NONE:
					default:
						System.out.println("Error: invalid rule");
						break;
					
					}
					
				} else
					System.out.println(rule.getName() + "failed to apply as no parameter found");
			}
		}
		
		return true;
	}

}
