package dao.jdbc;

import dao.mapper.ProductRowMapper;
import entity.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Query {
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

    private static final String INSERT_INTO_PRODUCTS = """
            INSERT INTO Products(name, price, creationDate)
            VALUES""";

    private static final String GET_ALL_PRODUCTS = "Select * from Products;";


    public void createProductsTable() throws SQLException {
        executeQuery(CREATE_TABLE_PRODUCTS);
    }

    public Product addProduct(String name, int price) throws SQLException {
        String insertQuery = INSERT_INTO_PRODUCTS.concat("('" + name + "', " + price + ", '" + Timestamp.valueOf(LocalDateTime.now().withNano(0).withSecond(0)) + "');");
        executeQuery(insertQuery);
         List<Product> list  = getProducts();
         if(list.size() > 0){
             return list.get(list.size()-1);
         }
        return null;
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
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void deleteProduct(int productId) throws SQLException {
        String deleteQuery = "delete from products where id = '" + productId + "';";
        executeQuery(deleteQuery);

    }

    public void changeProduct(int productId, String newName, int newPrice) throws SQLException {
        String updateQuery = "UPDATE products SET name = '" + newName + "', price = " + newPrice + "WHERE id = " + productId + ";";
        executeQuery(updateQuery);
    }

    public void deleteAll() throws SQLException {
        executeQuery("DROP TABLE Products;");
    }

    private void executeQuery(String query) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
           statement.executeUpdate();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);


    }

}
