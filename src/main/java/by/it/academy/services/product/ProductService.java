package by.it.academy.services.product;

import java.util.List;

public interface ProductService <Product> {
    boolean insert(Product product);
    Product readProduct (Product product);
    boolean updateProduct (Product product, Product newProduct);
    boolean deleteProduct (Product product);
    List<Product> readAllProduct();
    List<Product> readAllProductASCName();
    List<Product> readAllProductASCPrice();
    List<Product> readAllProductDESCName();
    List<Product> readAllProductDESCPrice();
    List<Product> readAllProductBETWEENPrice(Product product);
    List<Product> readAllProductLIKE(Product product);
}
