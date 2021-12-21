package service;

import dao.jdbc.Query;
import entity.Product;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ProductService {
    private Query productDaoQuery;

    public List<Product> getAll() throws SQLException {
        List<Product> list =productDaoQuery.getProducts();
        if(null == list){
            productDaoQuery.createProductsTable();
            return Collections.emptyList();
        }
        return list;
    }
}
