package dao.jdbc;

import entity.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueryITest {

    @Test
    public void getProductsTest(){
        Query query = new Query();
        List<Product> products = query.getProducts();

        assertFalse(products.isEmpty());
        for (Product product : products) {
            assertNotEquals(0, product.getId());
            assertNotNull(product.getName());
            assertNotEquals(0, product.getPrice());
        }
    }
}
