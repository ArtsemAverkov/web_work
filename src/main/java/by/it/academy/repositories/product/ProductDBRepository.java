package by.it.academy.repositories.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connect.Connect;
import by.it.academy.repositories.connect.ConnectMySQL;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDBRepository implements ProductRepository <Product> {
    Connect connection;
    private final Logger logger = Logger.getLogger(ProductDBRepository.class);

    public ProductDBRepository(List<Product> connect) {
        this.connection = new ConnectMySQL();
    }


    @Override
    public boolean create(Product product) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Product (name, model, price, amount)VALUES (?,?,?,?)");
            statement.setString(1, product.getName());
            statement.setString(2, product.getModel());
            statement.setString(3, product.getPrice());
            statement.setInt(4, product.getAmount());
            int i = statement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            logger.info(e);
        }
        return false;
    }

    @Override
    public Optional<Product> readProduct(Product product) {
        Optional<Product> newProduct = Optional.of(new Product());
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Product");
            while (resultSet.next()) {
               // if (resultSet.getString("model").equals(product.getModel()) && resultSet.getString("name").equals(product.getName())) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String model = resultSet.getString("model");
                    String price = String.valueOf(resultSet.getInt("price"));
                    int amount = Integer.parseInt(resultSet.getString("amount"));
                    newProduct = Optional.ofNullable(Product.builder()
                            .id(id)
                            .name(name)
                            .model(model)
                            .price(price)
                            .amount(amount)
                            .build());
                    connection.close();
                    return newProduct;
              //  }
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.info(e);
        }
        return newProduct;
    }

    @Override
    public boolean updateProduct(Product product, Product newProduct) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE Product SET name=?, model=?, price=?, amount=? WHERE name=? AND model=? AND price=? AND amount=?");
            statement.setString(1, newProduct.getName());
            statement.setString(2, newProduct.getModel());
            statement.setString(3, newProduct.getPrice());
            statement.setInt(4, newProduct.getAmount());
            statement.setString(5, product.getName());
            statement.setString(6, product.getModel());
            statement.setString(7, product.getPrice());
            statement.setInt(8, product.getAmount());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException | ClassNotFoundException ex) {
            logger.info(ex);
        }
        return false;
    }

    @Override
    public boolean deleteProduct(Product product) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM Product WHERE name=? AND model=?");
            statement.setString(1, product.getName());
            statement.setString(2, product.getModel());
            statement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            logger.info(e);
        }
        return false;
    }

    @Override
    public List<Optional<Product>> readAllProduct() {
        List<Optional<Product>> products = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT*FROM Product");
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                String price = resultSet.getString("price");
                int amount = Integer.parseInt(resultSet.getString("amount"));
                products.add(Optional.ofNullable(Product.builder()
                        .id(id)
                        .name(name)
                        .model(model)
                        .price(price)
                        .amount(amount)
                        .build()));
                logger.info("productRepository readAllProduct" + products);

            }
            connection.close();
            return products;
        } catch (SQLException | ClassNotFoundException e) {
            logger.info(e);
        }
        return products;
    }

    @Override
    public Optional<Product> sortingProductBeforeFrom(Product product) {
        Optional<Product> products = Optional.of(new Product());
        String from = product.getName();
        String before = product.getModel();
        String select = "SELECT" + "*" + " FROM " + "Product" + " WHERE " + "price " + "BETWEEN " + from + " AND " + before;
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                String price = resultSet.getString("price");
                int amount = Integer.parseInt(resultSet.getString("amount"));
                products = (Optional.ofNullable(Product.builder()
                        .id(id)
                        .name(name)
                        .model(model)
                        .price(price)
                        .amount(amount)
                        .build()));
                logger.info("sortingProductBeforeFrom :" + products);
            }
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.info(e);
        }
        return products;
    }

    @Override
    public Optional<Product> searchProduct(Product product) {
        Optional<Product> products = Optional.of(new Product());
        String name1 = product.getName();
        String select = "SELECT" + "*" + " FROM " + "Product" + " WHERE " + "name " + "LIKE " + "'" + name1 + "%'";
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                String price = resultSet.getString("price");
                int amount = Integer.parseInt(resultSet.getString("amount"));
                products = (Optional.ofNullable(Product.builder()
                        .id(id)
                        .name(name)
                        .model(model)
                        .price(price)
                        .amount(amount)
                        .build()));
                logger.info("searchProduct :" + products);
            }
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.info(e);
        }
        return products;
    }


    public Optional<Product> productSorting() {
        Optional<Product> products = Optional.of(new Product());
        String select = null;
//        String requestName = product.getName();
//        String orderBYPriceDESC = "SELECT*FROM Product ORDER BY price DESC";
//        String orderBYNameDESC = "SELECT*FROM Product ORDER BY name DESC";
//        String orderBYPriceASC = "SELECT*FROM Product ORDER BY price ASC";
//        String orderBYNameASC = "SELECT*FROM Product ORDER BY name ASC";
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                String price = resultSet.getString("price");
                int amount = Integer.parseInt(resultSet.getString("amount"));
                products = (Optional.ofNullable(Product.builder()
                        .id(id)
                        .name(name)
                        .model(model)
                        .price(price)
                        .amount(amount)
                        .build()));
                logger.info("productSorting" + products);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            logger.info(e);
        }
        return products;
    }
}


