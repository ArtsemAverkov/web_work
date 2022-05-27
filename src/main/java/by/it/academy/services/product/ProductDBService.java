package by.it.academy.services.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.product.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductDBService implements ProductService <Product>{
    private ProductRepository <Product> repository;

    public ProductDBService(ProductRepository<Product> repository) {
        this.repository = repository;
    }


    @Override
    public boolean create(Product product) {
        return repository.create(product);

    }

    @Override
    public   List<Product> readProduct (Product product) {
        return repository.readProduct(product)
                .stream()
                .sorted()
                .collect(Collectors.toList());
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
    public  List<Optional<Product>> readAllProduct() {
        List<Optional<Product>> collect = repository.readAllProduct()
                .stream()
                .sorted()
                .collect(Collectors.toList());
        return collect;
    }


    @Override
    public List<Product> sortingProductBeforeFrom (Product product) {
        return repository.sortingProductBeforeFrom(product)
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> searchProduct (Product product) {
        return repository.searchProduct(product)
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> productSorting() {
        return repository.productSorting()
                .stream()
                .collect(Collectors.toList());
    }

}
