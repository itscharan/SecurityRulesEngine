import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class BaseServlet extends HttpServlet{
	
	
	//Port
    public static final int PORT = 8080;
    public static RuleEngineImpl rel = new RuleEngineImpl();
	
	/**
	 * Prepares the HTTP response by setting the content type and adding header
	 * HTML code.
	 *
	 * @param title - web page title
	 * @param response - HTTP response
	 * @throws IOException
	 * @see #finishResponse(HttpServletRequest, HttpServletResponse)
	 */
	public static void prepareResponse(String title, HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<center>");
		out.printf("<br><br><br></br></br></br>");
		out.printf("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\"");
		out.printf("\"http://www.w3.org/TR/html4/strict.dtd\">%n%n");
		out.printf("<html>%n%n");
		out.printf("<head>%n");
		out.printf("\t<title>%s</title>%n", title);
		out.printf("\t<meta http-equiv=\"Content-Type\" ");
		out.printf("content=\"text/html;charset=utf-8\">%n");
		out.printf("</head>%n%n");
		out.printf("<body>%n%n");
//		out.printf("<div id=\"content\" style=\"height:400px;width:600px;float:left;\">");
		
	}
	
	
	/**
	 * Finishes the HTTP response by adding footer HTML code and setting the
	 * response code.
	 *
	 * @param request - HTTP request
	 * @param response - HTTP response
	 * @throws IOException
	 * @see #prepareResponse(String, HttpServletResponse)
	 */
	public static void finishResponse(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		
		out.printf("<p style=\"font-size: 10pt; font-style: italic; text-align: center;");
		out.printf("border-top: 1px solid #eeeeee; margin-bottom: 1ex;\">");
		out.printf("Page <a href=\"%s\">%s</a> generated on %s. ",
				request.getRequestURL(), request.getRequestURL(),
				StringUtilities.getDate("yyyy-MM-dd hh:mm a"));

		out.printf("</p>%n%n");
		out.printf("</div>");
		out.printf("</body>%n");
		out.printf("</html>%n");

		out.flush();

		response.setStatus(HttpServletResponse.SC_OK);
		response.flushBuffer();
	}
	
}
