package dao.jdbc;

import dao.mapper.ProductRowMapper;
import entity.Product;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class JdbcProductsDao {
    String url = "jdbc:postgresql://192.168.99.100:5432/postgres";
    String user = "postgres";
    String password = "mypwd";
    private static final String CREATE_TABLE_PRODUCTS = """
            CREATE TABLE products 
            (
            id serial primary key not null,
            name varchar(100) not null,
            price int not null,
            creationDate Timestamp
            );""";
    private static final String GET_ALL_PRODUCTS = "Select id, name, price, creationDate from Products;";
    private static final String GET_PRODUCT_BY_ID = "Select id, name, price, creationDate from Products where id = ? ";
    private static final String INSERT_INTO_PRODUCTS = """
            INSERT INTO Products(name, price, creationDate)
            VALUES( ?, ?, ?)""";

    private static final String UPDATE_PRODUCT = "Update products set name = ?, price = ?, creationDate = ? where id = ?";
    private static final String DELETE_FROM_PRODUCTS_BY_ID = "Delete from products where id = ?";


    public void createProductsTable() throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(CREATE_TABLE_PRODUCTS)) {
            statement.executeUpdate();
        }
    }

    public List<Product> getProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(GET_ALL_PRODUCTS)) {
            ResultSet resultSet = statement.executeQuery();
            ProductRowMapper productRowMapper = new ProductRowMapper();
            while (resultSet.next()) {
                Product product = productRowMapper.mapRow(resultSet);
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Product getProductById(int id) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(GET_PRODUCT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            ProductRowMapper productRowMapper = new ProductRowMapper();
            resultSet.next();
            return productRowMapper.mapRow(resultSet);
        }
    }

    public Product addProduct(String name, int price) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_INTO_PRODUCTS)) {
            statement.setString(1, name);
            statement.setInt(2, price);
            statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now().withSecond(0)));
            statement.executeUpdate();
        }
        List<Product> list = getProducts();
        if (list.size() > 0) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    public Product updateProduct(int productId, String newName, int newPrice) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_PRODUCT)) {
            Product productBeforeUpdate = getProductById(productId);
            statement.setInt(4, productId);
            statement.setString(1, newName);
            statement.setInt(2, newPrice);
            statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now().withSecond(0)));
            statement.executeUpdate();
            return productBeforeUpdate;
        }
    }

    public Product deleteProduct(int productId) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(DELETE_FROM_PRODUCTS_BY_ID)) {
            Product productToDelete = getProductById(productId);
            statement.setInt(1, productId);
            statement.executeUpdate();
            return productToDelete;
        }
    }

    public void deleteAllProducts() throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement("Drop table products")) {
            statement.executeUpdate();
        }
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
