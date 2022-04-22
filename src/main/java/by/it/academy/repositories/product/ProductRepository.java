package by.it.academy.repositories.product;

import java.util.List;

public interface ProductRepository<Product> {
    boolean insert(Product product);
    Product readProduct (Product product);
    boolean updateProduct (Product product, Product newProduct);
    boolean deleteProduct (Product product);
    List<Product> readAllProduct();
}