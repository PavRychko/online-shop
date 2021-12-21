package servlets;

import entity.Product;
import generators.PageGenerator;
import service.AddProductService;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class AddProductServlet extends HttpServlet {
    AddProductService addProductService = new AddProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        try {
            String page = pageGenerator.getPage("addProductForm.html");
            resp.getWriter().write(page);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Product product = getProductFromRequest(req);
            addProductService.addProduct(product);
            resp.sendRedirect("productsTable.html");
        } catch (Exception e) {
            resp.getWriter().write(e.getLocalizedMessage());
        }
    }

    private Product getProductFromRequest(HttpServletRequest req) {
        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setPrice(Integer.parseInt(req.getParameter("price")));
        return product;
    }
}



