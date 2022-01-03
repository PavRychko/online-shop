package servlets;

import dao.mapper.ProductFromRequestMapper;
import entity.Product;
import generators.PageGenerator;
import service.EditProductService;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditProductServlet extends HttpServlet {
    private EditProductService editProductService = new EditProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        try {
            String page = pageGenerator.getPage("editProductForm.html");
            resp.getWriter().write(page);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            ProductFromRequestMapper productFromRequestMapper = new ProductFromRequestMapper();
            Product product = productFromRequestMapper.getProductFromRequest(req);
            editProductService.editProduct(product, );
            resp.sendRedirect("/products");
        } catch (Exception e) {
            resp.getWriter().write(e.getLocalizedMessage());
        }
    }
}
