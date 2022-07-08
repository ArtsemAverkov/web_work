package by.it.academy.services.product;



import by.it.academy.entities.product.ModelProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductsService{
    ModelProduct create(ModelProduct modelProduct);
   ModelProduct getProduct (ModelProduct modelProduct);
    boolean updateProduct (ModelProduct modelProduct, UUID id);
    boolean deleteProduct (ModelProduct modelProduct);
    List<ModelProduct> readProducts(Pageable pageable);

    void checkModel(String model);

    UUID saveImageForModelProduct(UUID id, MultipartFile image);
    byte[] getImageForModelProduct(UUID id);
}
