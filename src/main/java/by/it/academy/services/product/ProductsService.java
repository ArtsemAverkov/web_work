package by.it.academy.services.product;



import java.util.List;
import java.util.UUID;

public interface ProductsService <ModelProduct>{
    ModelProduct create(ModelProduct modelProduct);
   ModelProduct readProduct (ModelProduct modelProduct);
    boolean updateProduct (ModelProduct modelProduct, UUID id);
    boolean deleteProduct (ModelProduct modelProduct);
    List<ModelProduct> readAllProduct();
}
