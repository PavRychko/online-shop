package service;

import dao.jdbc.Query;
import entity.Product;


public class AddProductService {
    Query query = new Query();

    public void addProduct(Product product) {
        try {
            Product resultProduct = query.addProduct(product.getName(), product.getPrice());
            if (null != resultProduct) {
                System.out.println("product successfully added!");
            }
        } catch (Exception e) {
            System.out.println("product is not added!\n" + e.getMessage());
        }
    }
}
