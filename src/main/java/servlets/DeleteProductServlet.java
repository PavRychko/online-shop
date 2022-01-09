package servlets;

import dao.mapper.ProductFromRequestMapper;
import entity.Product;
import generators.PageGenerator;
import services.DeleteProductService;
import services.FindProductByIdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DeleteProductServlet extends HttpServlet {
    private final DeleteProductService deleteProductService = new DeleteProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        Map<String, Object> parameters = new HashMap<>();
        FindProductByIdService findProductByIdService = new FindProductByIdService();
        try {
            Product productToDelete = findProductByIdService.findById(Integer.parseInt(req.getParameter("id")));
            parameters.put("product", productToDelete);
            String page = pageGenerator.getPage("deleteProductForm.html", parameters);
            resp.getWriter().write(page);
        } catch (SQLException e) {
            resp.getWriter().write(e.getLocalizedMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ProductFromRequestMapper productFromRequestMapper = new ProductFromRequestMapper();
            Product product = productFromRequestMapper.getProductFromRequest(req);
            deleteProductService.deleteProduct(product);
            resp.sendRedirect("/products");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getLocalizedMessage());
        }
    }
}
