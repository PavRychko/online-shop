package service;

import dao.jdbc.JdbcProductsDao;
import entity.Product;

import java.sql.SQLException;

public class EditProductService {
    private final JdbcProductsDao jdbcProductsDao = new JdbcProductsDao();


    public void editProduct(Product product, int id) throws SQLException {
        jdbcProductsDao.updateProduct(id, product.getName(), product.getPrice());
    }
}
