package by.it.academy.services.product;



import by.it.academy.entities.product.ModelProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


public interface ProductsService{
    Long create(ModelProduct modelProduct);
    ModelProduct getProduct (Long id);
    boolean updateProduct (ModelProduct modelProduct, Long id);
    void deleteProduct (Long id);
    List<ModelProduct> readProducts(Pageable pageable);

    UUID saveImageForModelProduct(UUID id, MultipartFile image);

    byte[] getImageForModelProduct(UUID id);
}
