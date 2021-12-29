package dao.jdbc;

import entity.Product;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueryITest {
    Query query = new Query();


    @BeforeEach
    public void createTableProducts() throws SQLException {
        query.createProductsTable();
    }

    @Test
    public void addProductAndGetProductsTest() throws SQLException {
        Product testProduct = query.addProduct("apple", 200);
        assertEquals("apple", testProduct.getName());
        assertEquals(200, testProduct.getPrice());
        assertNotEquals(0, testProduct.getId());
        assertNotEquals(null, testProduct.getCreationDate());

        List<Product> products = query.getProducts();
        assertFalse(products.isEmpty());
        assertTrue(products.contains(testProduct));
        assertEquals(products.get(products.size() - 1).getName(), testProduct.getName());
        assertEquals(products.get(products.size() - 1).getPrice(), testProduct.getPrice());
    }


    @AfterEach
    public void deleteTableProducts() throws SQLException {
        query.deleteAll();
    }
}
