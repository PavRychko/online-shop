package service;

import dao.jdbc.Query;
import entity.Product;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ProductService {
    private Query productDaoQuery = new Query();

    public List<Product> getAll()  {
        List<Product> list = null;
        try {
            list = productDaoQuery.getProducts();
            if (null == list) {
                productDaoQuery.createProductsTable();
                return Collections.emptyList();
            }
        }catch (SQLException e){
            System.out.println("Something gone wrong in getAll from productService");
            e.printStackTrace();
        }
        return list;
    }
}
