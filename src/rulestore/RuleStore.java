package rulestore;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores all valid rules
 * @author Sricharan
 *
 */
public class RuleStore {
	
	private List<Rule> rules;
	
	public RuleStore(){
		rules = new ArrayList<Rule>();
	}
	
	public void addRule(String name, String line){
		
		Rule rule = new Rule(line);
		rule.setName(name);
		rule.parseRule();
		if(rule.isvalid())
			rules.add(rule);
	}
	
	public List<Rule> getRulesList(){
		return rules;
	}
	
	public void showRules(){
		for(Rule rule: rules)
			System.out.println(rule.getName() +": " + rule.line);
	}

}
