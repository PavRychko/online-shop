package servlets;


import generators.PageGenerator;
import services.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> parameters = new HashMap<>();
        PageGenerator pageGenerator = PageGenerator.instance();
        ProductService productService = new ProductService();
        parameters.put("products", productService.getAll());
        try {
            String page = pageGenerator.getPage("productsTable.html", parameters);
            resp.getWriter().write(page);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
