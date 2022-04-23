package by.it.academy.repositories.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connect.Connect;
import by.it.academy.repositories.connect.ConnectMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDBRepository implements ProductRepository <Product>{
    Connect connection;

    public ProductDBRepository(List<Product> connect) {
        this.connection = new ConnectMySQL();
    }



    @Override
    public boolean insert(Product product) {
        try(Connection conn = connection.connect()){
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Product (name, model, price, amount)VALUES (?,?,?,?)");
            statement.setString(1, product.getName());
            statement.setString(2, product.getModel());
            statement.setString(3, product.getPrice());
            statement.setString(4,product.getAmount());
            int i = statement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product readProduct(Product product) {
        Product newProduct;
        try(Connection conn = connection.connect()){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Product");
            while (resultSet.next()){
                if (resultSet.getString("name").equals(product.getName()) && resultSet.getString("model").equals(product.getModel())) {
                    String name = resultSet.getString("name");
                    String model= resultSet.getString("model");
                    String price = String.valueOf(resultSet.getInt("price"));
                    String amount = resultSet.getString("amount");
                    newProduct = new Product(name, model, price, amount);
                    connection.close();
                    return newProduct;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateProduct(Product product, Product newProduct) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE Product SET name=?, model=?, price=?, amount? WHERE name=? AND model=? AND price=? AND amount=?");
            statement.setString(1,newProduct.getName());
            statement.setString(2, newProduct.getModel());
            statement.setString(3, newProduct.getPrice());
            statement.setString(4, newProduct.getAmount());
            statement.setString(5, product.getName());
            statement.setString(6, product.getModel());
            statement.setString(7, product.getPrice());
            statement.setString(8, product.getAmount());
            int i = statement.executeUpdate();

            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }
        @Override
    public boolean deleteProduct(Product product) {
        try(Connection conn = connection.connect()){
            PreparedStatement statement = conn.prepareStatement("DELETE FROM Product WHERE name=? AND model=?");
            statement.setString(1, product.getName());
            statement.setString(2, product.getModel());
            statement.executeUpdate();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            return false;
    }

    @Override
    public List<Product> readAllProduct() {
        List<Product> products = new ArrayList<>();
        try(Connection conn = connection.connect()){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT*FROM Product");
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
               String price = resultSet.getString("price");
             products.add(new Product(name, model, price));
                System.out.println(products);

            }
            connection.close();
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }
}
