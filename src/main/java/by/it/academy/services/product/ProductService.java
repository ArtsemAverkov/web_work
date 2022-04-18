package by.it.academy.services.product;

import java.util.List;

public interface ProductService <Product> {
    boolean insert(Product product);
    Product readProduct (Product product);
    boolean updateProduct (Product product, Product newProduct);
    boolean deleteProduct (Product product);
    List<Product> readAllProduct();
}
