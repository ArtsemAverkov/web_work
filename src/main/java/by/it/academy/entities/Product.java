package by.it.academy.entities;

import java.util.Objects;

public class Product extends Throwable {
    private String name;
    private String model;
    private String price;
    private int quantity;
    private int amount;
    private  int from;
    private  int before;


    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(int from, int before) {
        this.from = from;
        this.before = before;
    }

    public Product(String message, int amount) {
        super(message);
        this.amount = amount;
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



    public Product(String name, String model, String price) {
        this.name = name;
        this.model = model;
        this.price = price;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getBefore() {
        return before;
    }

    public void setBefore(int before) {
        this.before = before;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity && amount == product.amount && from == product.from && before == product.before && Objects.equals(name, product.name) && Objects.equals(model, product.model) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, model, price, quantity, amount, from, before);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", price='" + price + '\'' +
                ", amount=" + amount +
                '}';
    }
}
