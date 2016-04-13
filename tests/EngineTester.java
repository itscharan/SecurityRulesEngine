import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Test;

import rulestore.RuleStore;
import securitydata.Bond;
import securitydata.Equity;

/**
 * Security rules engine tester
 * @author Sricharan
 *
 */
public class EngineTester {
	
	@Test
	public void testCouponRuleSuccess(){
		//Data objects
		Bond bond = new Bond();
		LocalDate eventdate = new LocalDate();
		eventdate = eventdate.plusDays(10);
		bond.addParameter("nextcoupondate", eventdate);
		
		RuleStore rs = new RuleStore();
		rs.addRule("Coupon Rule", "bond|nextcoupondate|GTHAN|TODAY+7|DATE|ACCEPT");
		RuleEngineImpl rei = new RuleEngineImpl();
		assertTrue(rei.runEngine(bond, rs));
	}
	
	@Test
	public void testCouponRuleFailure(){
		//Data objects
		Bond bond = new Bond();
		LocalDate eventdate = new LocalDate();
		eventdate = eventdate.plusDays(4);
		bond.addParameter("nextcoupondate", eventdate);
		
		RuleStore rs = new RuleStore();
		rs.addRule("Coupon Rule", "bond|nextcoupondate|GTHAN|TODAY+7|DATE|ACCEPT");//Name and Rule
		RuleEngineImpl rei = new RuleEngineImpl();
		assertFalse(rei.runEngine(bond, rs));
	}
	
	@Test
	public void testIssuerRuleSuccess(){
		//Data objects
		Bond bond = new Bond();
		LocalDate eventdate = new LocalDate();
		eventdate = eventdate.plusDays(4);
		bond.addParameter("nextcoupondate", eventdate);
		bond.addParameter("issuer", "MUNICIPAL");
		
		RuleStore rs = new RuleStore();
		rs.addRule("Issuer Rule", "bond|issuer|EQUAL|MUNICIPAL|TEXT|ACCEPT");// Name and Rule 
		RuleEngineImpl rei = new RuleEngineImpl();
		assertTrue(rei.runEngine(bond, rs));
	}
	

	@Test
	public void testIssuerRuleFailure(){
		//Data objects
		Bond bond = new Bond();
		LocalDate eventdate = new LocalDate();
		eventdate = eventdate.plusDays(4);
		bond.addParameter("nextcoupondate", eventdate);
		bond.addParameter("issuer", "MUNICIPAL"); 
		
		RuleStore rs = new RuleStore();
		rs.addRule("Issuer Rule", "bond|issuer|EQUAL|US-GOV|TEXT|ACCEPT"); //Accepts only bonds issued by US-GOV
		RuleEngineImpl rei = new RuleEngineImpl();
		assertFalse(rei.runEngine(bond, rs)); 
	}
	
	@Test
	public void testInvalidRule(){
		//Data objects
		Bond bond = new Bond();
		LocalDate eventdate = new LocalDate();
		eventdate = eventdate.plusDays(4);
		bond.addParameter("nextcoupondate", eventdate);
		bond.addParameter("issuer", "MUNICIPAL"); 
		
		RuleStore rs = new RuleStore();
		rs.addRule("Issuer Rule", "bond|issuer|EQUAL|US-GOV|NOTHING|ACCEPT"); //Invalid rule so rule so every security is accepted
		RuleEngineImpl rei = new RuleEngineImpl();
		assertTrue(rei.runEngine(bond, rs));
		
	}
	
	@Test
	public void testDividendRuleFailure(){
		//Data objects
		Equity equity = new Equity();
		LocalDate eventdate = new LocalDate();
		eventdate = eventdate.plusDays(4); //Set next dividend date within a week
		equity.addParameter("nextdividenddate", eventdate);
		
		RuleStore rs = new RuleStore();
		rs.addRule("Dividend Rule", "equity|nextdividenddate|GTHAN|TODAY+7|DATE|ACCEPT");
		RuleEngineImpl rei = new RuleEngineImpl();
		assertFalse(rei.runEngine(equity, rs));
	}
	
	@Test
	public void testDividendRuleSuccess(){
		//Data objects
		Equity equity = new Equity();
		LocalDate eventdate = new LocalDate();
		eventdate = eventdate.plusDays(10); //Set next dividend date is more than a week
		equity.addParameter("nextdividenddate", eventdate); 
		
		RuleStore rs = new RuleStore();
		rs.addRule("Dividend Rule", "equity|nextdividenddate|GTHAN|TODAY+7|DATE|ACCEPT"); //Name and Rule
		RuleEngineImpl rei = new RuleEngineImpl();
		assertTrue(rei.runEngine(equity, rs));			
		
	}
	
}
