package services;

import dao.jdbc.JdbcProductsDao;
import entity.Product;

import java.sql.SQLException;

public class EditProductService {
    private final JdbcProductsDao jdbcProductsDao = new JdbcProductsDao();


    public void editProduct(Product product) throws SQLException {
        jdbcProductsDao.updateProduct(product.getId(), product.getName(), product.getPrice());
    }
}
