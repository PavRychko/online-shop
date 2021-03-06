package servlets;

import dao.mapper.ProductFromRequestMapper;
import entity.Product;
import generators.PageGenerator;
import services.EditProductService;
import services.FindProductByIdService;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EditProductServlet extends HttpServlet {
    private final EditProductService editProductService = new EditProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        Map<String, Object> parameters = new HashMap<>();
        FindProductByIdService findProductByIdService = new FindProductByIdService();
        try {
            Product productToEdit = findProductByIdService.findById(Integer.parseInt(req.getParameter("id")));
            parameters.put("product", productToEdit);
            String page = pageGenerator.getPage("editProductForm.html", parameters);
            resp.getWriter().write(page);
        } catch (SQLException e) {
            resp.getWriter().write(e.getLocalizedMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            ProductFromRequestMapper productFromRequestMapper = new ProductFromRequestMapper();
            Product product = productFromRequestMapper.getProductFromRequest(req);
            editProductService.editProduct(product);
            resp.sendRedirect("/products");
        } catch (Exception e) {
            resp.getWriter().write(e.getLocalizedMessage());
        }
    }
}
