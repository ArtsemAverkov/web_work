package by.it.academy.repositories.product;

import by.it.academy.controllrs.product.ReadAllProduct;
import by.it.academy.entities.Product;
import by.it.academy.repositories.connect.Connect;
import by.it.academy.repositories.connect.ConnectMySQL;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDBRepository implements ProductRepository <Product>{
    Connect connection;
    private final Logger logger = Logger.getLogger(ProductDBRepository.class);
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
            statement.setInt(4,product.getAmount());
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
                if (resultSet.getString("model").equals(product.getModel()) && resultSet.getString("name").equals(product.getName())) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String model= resultSet.getString("model");
                    String price = String.valueOf(resultSet.getInt("price"));
                    int amount = Integer.parseInt(resultSet.getString("amount"));
                    newProduct = new Product(id,name, model, price, amount);
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
            PreparedStatement statement = conn.prepareStatement("UPDATE Product SET name=?, model=?, price=?, amount=? WHERE name=? AND model=? AND price=? AND amount=?");
            statement.setString(1,newProduct.getName());
            statement.setString(2, newProduct.getModel());
            statement.setString(3, newProduct.getPrice());
            statement.setInt(4, newProduct.getAmount());
            statement.setString(5, product.getName());
            statement.setString(6, product.getModel());
            statement.setString(7, product.getPrice());
            statement.setInt(8, product.getAmount());
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
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
               String price = resultSet.getString("price");
               int amount = Integer.parseInt(resultSet.getString("amount"));
             products.add(new Product(id,name, model, price, amount));
               logger.info(products);

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

    @Override
    public List<Product> readAllProductASCName() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = connection.connect()){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT*FROM Product ORDER BY name ASC");
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                String price = resultSet.getString("price");
                int amount = Integer.parseInt(resultSet.getString("amount"));
                products.add(new Product(id,name, model, price, amount));
                logger.info("readAllProductASCName"+products);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> readAllProductASCPrice() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = connection.connect()){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT*FROM Product ORDER BY price ASC");
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                String price = resultSet.getString("price");
                int amount = Integer.parseInt(resultSet.getString("amount"));
                products.add(new Product(id,name, model, price, amount));
                logger.info("readAllProductASCPrice"+products);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }


    @Override
    public List<Product> readAllProductDESCName() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = connection.connect()){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT*FROM Product ORDER BY name DESC");
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                String price = resultSet.getString("price");
                int amount = Integer.parseInt(resultSet.getString("amount"));
                products.add(new Product(id,name, model, price, amount));
               logger.info("readAllProductDESCName"+products);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> readAllProductDESCPrice() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = connection.connect()){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT*FROM Product ORDER BY price DESC");
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                String price = resultSet.getString("price");
                int amount = Integer.parseInt(resultSet.getString("amount"));
                products.add(new Product(id,name, model, price, amount));
                logger.info(" readAllProductDESCPrice"+products);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> readAllProductBETWEENPrice(Product product) {
        List<Product> products = new ArrayList<>();
        String from = product.getName();
        String before = product.getModel();
        String select = "SELECT" + "*" + " FROM " + "Product" + " WHERE " + "price " + "BETWEEN " + from + " AND " + before;

        try (Connection conn = connection.connect()){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                String price = resultSet.getString("price");
                int amount = Integer.parseInt(resultSet.getString("amount"));
                products.add(new Product(id,name, model, price, amount));
                logger.info("readAllProductBETWEENPrice"+products);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> readAllProductLIKE(Product product) {
        List<Product> products = new ArrayList<>();
        String name1 = product.getName();
        String select = "SELECT" + "*" + " FROM " + "Product" + " WHERE " + "name " + "LIKE " + "'" + name1 +"%'" ;

        try (Connection conn = connection.connect()){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                String price = resultSet.getString("price");
                int amount = Integer.parseInt(resultSet.getString("amount"));
                products.add(new Product(id,name, model, price, amount));
                logger.info("readAllProductLIKE"+products);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }
}

