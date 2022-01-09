package services;

import dao.jdbc.JdbcProductsDao;
import entity.Product;

import java.sql.SQLException;

public class DeleteProductService {
    private final JdbcProductsDao jdbcProductsDao = new JdbcProductsDao();

    public Product deleteProduct(Product product) throws SQLException {
        return jdbcProductsDao.deleteProduct(product.getId());
    }
}
