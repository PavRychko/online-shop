package dao.mapper;

import entity.Product;

import javax.servlet.http.HttpServletRequest;

public class ProductFromRequestMapper {

    public Product getProductFromRequest(HttpServletRequest request) {
        Product product = new Product();
        String parameter = request.getParameter("id");
        product.setName(request.getParameter("name"));
        product.setPrice(Integer.parseInt(request.getParameter("price")));
        if (parameter != null) {
            product.setId(Integer.parseInt(request.getParameter("id")));
        }
        return product;
    }
}
