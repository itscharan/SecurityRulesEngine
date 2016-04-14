import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;


public class Driver {

	public static void main(String[] args){
		
		int port = 8080;
        Server server = new Server(port);

        ServletHandler handler = new ServletHandler();

		handler.addServletWithMapping(SREServlet.class, "/*");
        server.setHandler(handler);
        
		System.out.println("Starting server on port 8080 ...");

		try {
			server.start();
			server.join();

			System.out.println("Exiting...");
		}
		catch (Exception ex) {
			System.out.println("Interrupted while running server.");
			System.exit(-1);
		}
	}
}
