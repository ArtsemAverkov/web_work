package by.it.academy.services.product;

import by.it.academy.entities.User;
import by.it.academy.entities.product.ModelProduct;
import by.it.academy.repositories.product.ProductsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ProductAPIService implements ProductsService <ModelProduct>{
    private ProductsRepository<ModelProduct> repository;

    public ProductAPIService(ProductsRepository<ModelProduct> repository) {
        this.repository = repository;
    }

    @Override
    public boolean create(ModelProduct modelProduct) {
        return repository.createProduct(modelProduct);
    }

    @Override
    public  List<List<ModelProduct>> readProduct(ModelProduct modelProduct) {
        return repository.readProduct(modelProduct)
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateProduct(ModelProduct modelProduct, ModelProduct newModelProduct) {
        return repository.updateProduct(modelProduct, newModelProduct);
    }

    @Override
    public boolean deleteProduct(ModelProduct modelProduct) {
        return repository.deleteProduct(modelProduct);
    }

    @Override
    public List<List<ModelProduct>> readAllProduct() {
        return  repository.readAllProduct()
                .stream()
                .sorted()
                .collect(Collectors.toList());

    }
}
