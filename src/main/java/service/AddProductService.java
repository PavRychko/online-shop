package service;

import dao.jdbc.JdbcProductsDao;
import entity.Product;


public class AddProductService {
    JdbcProductsDao jdbcProductsDao = new JdbcProductsDao();

    public Product addProduct(Product product) {
        Product resultProduct = null;
        try {
            resultProduct = jdbcProductsDao.addProduct(product.getName(), product.getPrice());
            if (null != resultProduct) {
                System.out.println("product successfully added!");
            }
        } catch (Exception e) {
            System.out.println("product is not added!\n" + e.getMessage());

        }
        return resultProduct;
    }
}
