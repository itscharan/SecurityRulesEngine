package rulestore;
import org.joda.time.LocalDate;

/**
 * Rule class which determines if a rule is valid
 * And parses the rule.
 * @author Sricharan
 *
 */
public class Rule implements RuleOperator{
	
	public String ruleid;
	public String instrument;
	public String parameter;
	
	public String name;
	public String line;
	public String expr;
	public String val = null;
	public String valtype;
	public boolean res;
	
	public LocalDate eventDate;
	public float value;
	public boolean isvalid;
	public valuetype type;
	
	
	public Rule(String line){
		this.line = line;	
	}
	
	public void setRuleID(String ruleid){
		this.ruleid = ruleid;
	}
	
	/**
	 * Sets a name for this Rule (Not a unique id)
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Returns name of this rule
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Returns if value is valid.
	 * @return
	 */
	public boolean isvalid(){
		
		if(!isvalid){
			//Do something for a valid rule
		}
		
		return isvalid;
	}
	
	/**
	 * Parses the given rule
	 */
	public void parseRule(){
		//bond nextcoupondate LTHAN WEEK DATE REJECT
		isvalid = true;
		String[] words = this.line.split("\\|"); // | is the delimiter 
		
		this.instrument = words[0];
		this.parameter = words[1];
		this.expr = words[2];
		
		if(valuetype.NUMERIC.toString().equals(words[4]))
			try{
				this.value = Float.parseFloat(words[3]);
				this.type = valuetype.NUMERIC;
			}catch(NumberFormatException ex){
				this.type = valuetype.NONE;
				System.out.println("Numeric value is not in float\n eg: 1.2");
				isvalid = false;
			}
		else if(valuetype.TEXT.toString().equals(words[4])){
			this.val = words[3];
			this.type = valuetype.TEXT;
		}else if(valuetype.DATE.toString().equals(words[4])){
			this.type = valuetype.DATE;
			if(words[3].contains("TODAY")){
				
				String num = words[3].split("TODAY")[1];
				try{
					
					int numb = Integer.parseInt(num); 
					eventDate = new LocalDate();
					eventDate = eventDate.plusDays(numb);
				} catch(NumberFormatException ex){
					System.out.println("Error: integer part in date is not in correct format\n eg: TODAY+10");
					isvalid = false;
				}
			} else{
				this.type = valuetype.NONE;
				System.out.println("Error: Date is not in correct format \n eg: TODAY+1");
				isvalid = false;
			}
				
		}else{
			isvalid = false;
			System.out.println("Error: not a valid rule");
		}
		
		if(result.ACCEPT.toString().equals(words[5])){
			res = true;
		} else if (result.REJECT.toString().equals(words[5])){
			res = false;
		} else {
			isvalid = false;
			System.out.println("Error: rule is not either accept or reject \n eg: ACCEPT or REJECT");
		}
			
		
		if(!isvalid){
			System.out.println("Rule: " + "'" + this.line + "'" + " is not valid");
			System.out.println("eg: BOND|COUPON|GTHAN|100.0|NUMERIC|ACCEPT");
		}
	}
	
}
