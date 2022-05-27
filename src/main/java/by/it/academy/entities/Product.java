package by.it.academy.entities;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.Objects;
@Data
@Builder
public class Product extends Throwable {
    private  String id;
    private String name;
    private String model;
    private String price;
    private int quantity;
    private int amount;
    private  int from;
    private  int before;

    public Product(String name, String model, String price) {
        this.name = name;
        this.model = model;
        this.price = price;
    }

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String id, String name, String model, String price, int amount) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.price = price;
        this.amount = amount;
    }

    public Product(int from, int before) {
        this.from = from;
        this.before = before;
    }


    public Product(String name, String model, String price, int amount) {
        this.name = name;
        this.model = model;
        this.price = price;
        this.amount = amount;
    }

    public Product(String name, String model) {
        this.name = name;
        this.model = model;
    }
}
