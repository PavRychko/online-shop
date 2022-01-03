import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AddProductServlet;
import servlets.AllRequestServlet;
import servlets.ProductsServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        AllRequestServlet allRequestServlet = new AllRequestServlet();
        ProductsServlet productsServlet = new ProductsServlet();
        AddProductServlet addProductServlet = new AddProductServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestServlet), "/*");
        context.addServlet(new ServletHolder(productsServlet), "/products");
        context.addServlet(new ServletHolder(addProductServlet), "/products/add");
        Server server = new Server(8080);
        server.setHandler(context);
        server.start();

    }
}
