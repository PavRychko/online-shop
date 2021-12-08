package jdbc.queries;

import java.sql.*;
import java.time.LocalDateTime;

public class Query {
    String url = "jdbc:postgresql://192.168.99.100:5432/postgres";
    String user = "postgres";
    String password = "mypwd";

    private static final String CREATE_TABLE_PRODUCTS = """
            CREATE TABLE Products 
            (
            id serial primary key not null,
            name varchar(100) not null,
            price int not null,
            creationDate Timestamp
            );""";

    private static final String INSERT_INTO_PRODUCTS = """
            INSERT INTO Products(name, price, creationDate)
            VALUES """;

    private static final String GET_ALL_PRODUCTS = "Select * from Products;";


    public void createProducts() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement = connection.createStatement();
            statement.execute(CREATE_TABLE_PRODUCTS);
        }
    }

    public void addProduct(String name, int price) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement = connection.createStatement();
            String insertQuery = INSERT_INTO_PRODUCTS.concat("('" + name + "', " + price + ", '" + Timestamp.valueOf(LocalDateTime.now()) + "');");
            statement.execute(insertQuery);
        }
    }

    public void getProducts() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement = connection.createStatement();
            statement.execute(GET_ALL_PRODUCTS);
        }
    }

    public void deleteProduct(String name) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement = connection.createStatement();
            String deleteQuery = "delete from products where name = '" + name + "';";
            statement.execute(deleteQuery);
        }
    }

    public void changeProduct(String oldName, int oldPrice, String newName, int newPrice) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement = connection.createStatement();
            String updateQuery = "UPDATE products SET name = '" + newName + "', price = " + newPrice + "WHERE name = '" + oldName + "' and price = " + oldPrice + ";";
            statement.execute(updateQuery);
        }
    }

}
