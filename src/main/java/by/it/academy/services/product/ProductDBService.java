package by.it.academy.services.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.product.ProductRepository;

import java.util.List;

public class ProductDBService implements ProductService <Product>{
    private ProductRepository <Product> repository;

    public ProductDBService(ProductRepository<Product> repository) {
        this.repository = repository;
    }


    @Override
    public boolean insert(Product product) {
        return repository.insert(product);

    }

    @Override
    public Product readProduct(Product product) {
        return repository.readProduct(product);
    }

    @Override
    public boolean updateProduct(Product product, Product newProduct) {
        return repository.updateProduct(product, newProduct);

    }

    @Override
    public boolean deleteProduct(Product product) {
        return repository.deleteProduct(product);

    }

    @Override
    public  List<Product> readAllProduct() {
        return repository.readAllProduct();
    }
}