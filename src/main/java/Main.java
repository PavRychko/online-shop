import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

public class Main {
    public static void main(String[] args) throws Exception {
        AllRequestServlet allRequestServlet = new AllRequestServlet();
        ProductsServlet productsServlet = new ProductsServlet();
        AddProductServlet addProductServlet = new AddProductServlet();
        EditProductServlet editProductServlet = new EditProductServlet();
        DeleteProductServlet deleteProductServlet = new DeleteProductServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestServlet), "/*");
        context.addServlet(new ServletHolder(productsServlet), "/products");
        context.addServlet(new ServletHolder(addProductServlet), "/products/add");
        context.addServlet(new ServletHolder(editProductServlet), "/products/edit");
        context.addServlet(new ServletHolder(deleteProductServlet), "/products/delete");
        Server server = new Server(8080);
        server.setHandler(context);
        server.start();

    }
}
