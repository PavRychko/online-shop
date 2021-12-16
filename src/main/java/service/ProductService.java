package service;

import dao.jdbc.Query;
import entity.Product;

import java.util.List;

public class ProductService {
    private Query productDaoQuery;

    public List<Product> getAll(){
        return productDaoQuery.getProducts();
    }
}
