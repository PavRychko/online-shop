package dao.jdbc;

import entity.Product;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcProductsDaoITest {
    JdbcProductsDao jdbcProductsDao = new JdbcProductsDao();

    // if you don`t have table Products in your database, uncomment code below
//    @BeforeAll
//    public void createTableProducts() throws SQLException {
//        jdbcProductsDao.createProductsTable();
//    }

    @Test
    public void addProductAndGetProductsTest() throws SQLException {
        Product testProduct = jdbcProductsDao.addProduct("apple", 200);
        assertEquals("apple", testProduct.getName());
        assertEquals(200, testProduct.getPrice());
        assertNotEquals(0, testProduct.getId());
        assertNotEquals(null, testProduct.getCreationDate());

        List<Product> products = jdbcProductsDao.getProducts();
        assertFalse(products.isEmpty());
        for (Product product : products) {
            assertNotEquals(0, product.getId());
            assertNotNull(product.getName());
            assertNotEquals(0, product.getPrice());
            assertNotNull(product.getCreationDate());
        }
        assertEquals(products.get(products.size() - 1).getName(), testProduct.getName());
        assertEquals(products.get(products.size() - 1).getPrice(), testProduct.getPrice());
    }

    @Test
    public void updateProductTest() throws SQLException {
        jdbcProductsDao.addProduct("tea", 20);
        List<Product> products = jdbcProductsDao.getProducts();
        assertFalse(products.isEmpty());
        int productId = products.size() - 1;

        Product oldProduct = jdbcProductsDao.updateProduct(productId, "nut", 1000);
        assertNotNull(oldProduct);

        Product newProduct = jdbcProductsDao.getProductById(productId);
        assertNotNull(newProduct);

        assertEquals(oldProduct.getId(), newProduct.getId());
        assertNotEquals(oldProduct.getName(), newProduct.getName());
        assertNotEquals(oldProduct.getPrice(), newProduct.getPrice());
        assertEquals(oldProduct.getCreationDate(), newProduct.getCreationDate());
    }

    @Test
    public void deleteProductByIdTest() throws SQLException {
        jdbcProductsDao.addProduct("nut", 20);
        List<Product> products = jdbcProductsDao.getProducts();
        assertFalse(products.isEmpty());

        Product oldProduct = jdbcProductsDao.deleteProduct(products.get(products.size() - 1).getId());
        assertNotNull(oldProduct);
        assertEquals("nut", oldProduct.getName());
        assertEquals(20, oldProduct.getPrice());

        products = jdbcProductsDao.getProducts();
        assertFalse(products.isEmpty());
        assertNotEquals(products.get(products.size() - 1).getName(), oldProduct.getName());
        assertNotEquals(products.get(products.size() - 1).getPrice(), oldProduct.getPrice());
    }

    // if you don`t have table Products in your database, uncomment code below
//    @AfterAll
//    public void deleteTableProducts() throws SQLException {
//        jdbcProductsDao.deleteAllProducts();
//    }
}
