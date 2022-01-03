package dao.mapper;

import entity.Product;

import javax.servlet.http.HttpServletRequest;

public class ProductFromRequestMapper {

    public Product getProductFromRequest(HttpServletRequest req) {
        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setPrice(Integer.parseInt(req.getParameter("price")));
        return product;
    }
}
