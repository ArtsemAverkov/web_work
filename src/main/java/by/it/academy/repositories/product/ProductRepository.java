package by.it.academy.repositories.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository<Product> {
    boolean create(Product product);
    Optional<Product> readProduct (Product product);
    boolean updateProduct (Product product, Product newProduct);
    boolean deleteProduct (Product product);
    List<Optional<Product>>readAllProduct();
    Optional<Product> sortingProductBeforeFrom(Product product);
    Optional<Product> searchProduct(Product product);
    Optional<Product> productSorting();
}
