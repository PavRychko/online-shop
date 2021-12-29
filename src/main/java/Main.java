import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestServlet;
import servlets.ProductsServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        AllRequestServlet allRequestServlet = new AllRequestServlet();
        ProductsServlet productsServlet = new ProductsServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestServlet), "/*");
        context.addServlet(new ServletHolder(productsServlet), "/products");
        Server server = new Server(8080);
        server.setHandler(context);
        server.start();

    }
}
