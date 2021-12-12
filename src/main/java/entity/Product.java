package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Product {
    int id;
    String name;
    int price;
    Timestamp creationDate;


    public Product(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("id");
        name = resultSet.getString("name");
        price = resultSet.getInt("price");
        creationDate = resultSet.getTimestamp("creationDate");
    }


}
