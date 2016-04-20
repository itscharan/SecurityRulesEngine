import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import rulestore.RuleStore;
import securitydata.Bond;
import securitydata.Equity;
import securitydata.Security;


@SuppressWarnings("serial")
public class SREServlet extends BaseServlet{
	String rule1;
	
	String instrument;
	String attribute;
	String value;
	boolean result;
	
	public SREServlet(){
		rule1 = null;
		instrument = null;
		attribute = null;
		value = null;
		result = false;
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		
		if (request.getRequestURI().endsWith("favicon.ico")) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		//Start response
		
		prepareResponse("Security Rules Engine", response);
		
		printForm(request, response);
		
		PrintWriter out = response.getWriter();
		
		if(rule1 == null || rule1 == ""){
			out.printf("<t> Type a rule and security data and press validate</t><br>%n%n");
		} else{
			if(result)
				out.printf("<t>Security ACCEPTED </t>");
			else
				out.printf("<t>Security REJECTED </t>");
		}
		// Finishes preparing response
		finishResponse(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		rule1 = request.getParameter("rule1");
		instrument = request.getParameter("instrument");
		attribute = request.getParameter("attribute");
		value = request.getParameter("value");
		
		String action = request.getParameter("action");
		
		Security security = new Bond();//Correct this
		if(action.equals("Validate")){
			if(instrument.equals("bond")){
				security = new Bond();
				System.out.println("bond identified");
					
			} else if(instrument.equals("equity")){
				security = new Equity();
				System.out.println("bond identified");
			}
			
			Object date = new LocalDate();
			if(value.contains("-")){
				final DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MMM-yy");
				date = dtf.parseLocalDate(value);
				System.out.println(date.toString());
			} else{
				date = value;
				System.out.println("issuer identified");
			}
			
			security.addParameter(attribute, date);
			RuleStore rs = new RuleStore();
			rs.addRule("Rule", rule1);
			
			result = rel.runEngine(security, rs);
			
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect(request.getServletPath());
			
		}else {
			rule1 = null;
			instrument = null;
			attribute = null;
			value = null;
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect(request.getServletPath());
		}
	}
	
	private static void printForm(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();

		out.printf("<h1 align=center>Rules Engine !</h1>%n%n");
		out.printf("<form method=\"post\" action=\"%s\">%n",
				request.getServletPath());
		out.printf("<table align=center cellspacing=\"0\" cellpadding=\"2\"%n");
		out.printf("<tr>%n");
		out.printf("\t<td>%n");
		out.printf("\t\t<p>Define Rules</p><input type=\"text\" name=\"rule1\" maxlength=\"50\" size=\"50\">%n");

		out.printf("\t</td>%n");
		out.printf("</tr>%n");
		out.printf("</table>%n");
		
		out.printf("<table align=center cellspacing=\"0\" cellpadding=\"2\"%n");
		out.printf("<tr>%n");
		out.printf("\t<td>%n");
		
		out.printf("\t\t<p>Security data</p><p>Instrument  <input type=\"text\" name=\"instrument\" maxlength=\"50\" size=\"25\">%n");
		out.printf("\t\t<p><p>Attribute  <input type=\"text\" name=\"attribute\" maxlength=\"50\" size=\"25\">%n");
		out.printf("\t\t<p><p>Value  <input type=\"text\" name=\"value\" maxlength=\"50\" size=\"25\">%n");
		out.printf("\t</td>%n");
		out.printf("</tr>%n");
		out.printf("</table>%n");
		
		
		out.printf("<p align=center><input type=\"submit\" name = \"action\" value=\"Validate\"><input type=\"submit\" name = \"action\" value=\"Clear\"></p>\n%n");
		out.printf("</form>\n%n");
	}

}
