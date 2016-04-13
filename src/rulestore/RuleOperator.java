package rulestore;

/**
 * Rule operator interface
 * @author Sricharan
 *
 */
public interface RuleOperator {
	
	public enum relationaloperator{
		EQUAL,
		NEQUAL,
		LTHAN, 
		GTHAN,
		LEQUAL,
		GEQUAL
	}
	public enum valuetype{NUMERIC, TEXT, DATE, NONE}
	public enum result{ ACCEPT, REJECT}
	public void parseRule();
}