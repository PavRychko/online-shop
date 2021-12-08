import jdbc.queries.Query;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Query query = new Query();
        query.addProduct("apple", 200);
        query.addProduct("pineApple", 300);
        query.addProduct("pomegranate", 10);
    }
}
