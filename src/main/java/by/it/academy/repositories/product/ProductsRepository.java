package by.it.academy.repositories.product;

import by.it.academy.entities.product.ModelProduct;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository<M> {
    boolean createProduct(ModelProduct modelProduct);
    Optional<List<ModelProduct>> readProduct(ModelProduct modelProduct);
    boolean updateProduct(ModelProduct modelProduct, ModelProduct newModelProduct);
    boolean deleteProduct (ModelProduct modelProduct);
    Optional <List<ModelProduct>> readAllProduct();
}
