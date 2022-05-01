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

    @Override
    public List<Product> readAllProductASCName() {
        return repository.readAllProductASCName();
    }

    @Override
    public List<Product> readAllProductASCPrice() {
        return repository.readAllProductASCPrice();
    }

    @Override
    public List<Product> readAllProductDESCName() {
        return repository.readAllProductDESCName();
    }

    @Override
    public List<Product> readAllProductDESCPrice() {
        return repository.readAllProductDESCPrice();
    }

    @Override
    public List<Product> readAllProductBETWEENPrice(Product product) {
        return repository.readAllProductBETWEENPrice(product);
    }

    @Override
    public List<Product> readAllProductLIKE(Product product) {
        return repository.readAllProductLIKE(product);
    }
}
