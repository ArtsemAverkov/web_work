package by.it.academy.services.product;



import by.it.academy.entities.product.ModelProduct;

import java.util.List;
import java.util.Optional;

public interface ProductsService <ModelProduct>{
    boolean create(ModelProduct modelProduct);
    List<List<ModelProduct>> readProduct (ModelProduct modelProduct);
    boolean updateProduct (ModelProduct modelProduct, ModelProduct newModelProduct);
    boolean deleteProduct (ModelProduct modelProduct);
    List<List<ModelProduct>> readAllProduct();
}
