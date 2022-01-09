package services;

import dao.jdbc.JdbcProductsDao;
import entity.Product;

import java.sql.SQLException;

public class FindProductByIdService {
    JdbcProductsDao jdbcProductsDao = new JdbcProductsDao();

    public Product findById(int id) throws SQLException {
        return jdbcProductsDao.getProductById(id);
    }
}
