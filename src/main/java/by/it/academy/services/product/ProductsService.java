package by.it.academy.services.product;



import java.util.List;

public interface ProductsService <ModelProduct>{
    boolean create(ModelProduct modelProduct);
    List<List<ModelProduct>> readProduct (ModelProduct modelProduct);
    boolean updateProduct (ModelProduct modelProduct, ModelProduct newModelProduct);
    boolean deleteProduct (ModelProduct modelProduct);
    List<List<ModelProduct>> readAllProduct();
}
