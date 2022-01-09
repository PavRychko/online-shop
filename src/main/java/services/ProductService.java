package services;

import dao.jdbc.JdbcProductsDao;
import entity.Product;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ProductService {
    private final JdbcProductsDao jdbcProductsDao = new JdbcProductsDao();

    public List<Product> getAll() {
        List<Product> list = null;
        try {
            list = jdbcProductsDao.getProducts();
            if (null == list) {
                jdbcProductsDao.createProductsTable();
                return Collections.emptyList();
            }
        } catch (SQLException e) {
            System.out.println("Something gone wrong in getAll from productService");
            e.printStackTrace();
        }
        return list;
    }
}
