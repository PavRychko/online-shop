package entity;


import lombok.Getter;
import lombok.Setter;


import java.sql.Timestamp;

@Getter
@Setter
public class Product {
    private int id;
    private String name;
    private int price;
    private Timestamp creationDate;

}
