package by.it.academy.services.product;

import java.util.List;
import java.util.Optional;

public interface ProductService <Product> {
    boolean create(Product product);
    List<Product> readProduct (Product product);
    boolean updateProduct (Product product, Product newProduct);
    boolean deleteProduct (Product product);
    List<Optional<Product>> readAllProduct();
    List<Product> sortingProductBeforeFrom(Product product);
    List<Product> searchProduct(Product product);
    List<Product> productSorting();
}
