package by.it.academy.services.product;

import by.it.academy.entities.ImageForModelProduct;
import by.it.academy.entities.product.ModelProduct;
import by.it.academy.repositories.imageForModelProduct.ImageForModelProductRepository;
import by.it.academy.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductAPIService implements ProductsService{
  private final ProductRepository productRepository;
  private final ImageForModelProductRepository imageForModelProductRepository;

    /**
     * this method creates a new product
     * @param modelProduct get from controller
     * @return save product
     */
    @Override
    @Transactional
    public Long create(ModelProduct modelProduct) {
        ModelProduct buildProduct = buildProduct(modelProduct);
        return productRepository.save(buildProduct).getId();
    }

    /**
     * this method counts the product in the database
     * @param id get from controller
     * @return product
     */
    @Override
    @Transactional
    public ModelProduct getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    /**
     * update product by id
     * @param modelProduct get from controller
     * @param id get from controller
     * @return false
     */
    @Override
    @Transactional
    public boolean updateProduct(ModelProduct modelProduct, Long id) {
        ModelProduct product = getProduct(id);
        if (Objects.nonNull(product)){
            ModelProduct buildProducts = buildProduct(modelProduct);
           buildProducts.setId(id);
           productRepository.save(buildProducts);

        }
        return false;
    }

    /**
     * deleted product by id
     * @param id get from controller
     * @return false
     */
    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }

    /**
     * this method receives the set value from the
     * @return the received attribute value
     */
    @Override
    @Transactional
    public List<ModelProduct> readProducts(Pageable pageable) {
        return productRepository.findAll(pageable).toList();
    }

    /**
     * deleted save image for product by id
     * @param id get from controller
     * @param image get from local
     * @return id dor image
     */
    @SneakyThrows
    @Override
    @Transactional
    public UUID saveImageForModelProduct(UUID id, MultipartFile image) {
        return imageForModelProductRepository.save(new ImageForModelProduct(id, image.getBytes())).getId() ;
    }

    /**
     * deleted get image for product by id
     * @param id get from controller
     * @return image from Postman
     */
    @Override
    @Transactional
    public byte[] getImageForModelProduct(UUID id) {
        return imageForModelProductRepository.findById(id).orElseThrow(NoSuchElementException::new).getImage();
    }

    /**
     * this method using build creates a new product
     * @return new product
     */
    private ModelProduct buildProduct (ModelProduct modelProduct) {
        return ModelProduct.builder()
                .id(modelProduct.getId())
                .product(modelProduct.getProduct())
                .model(modelProduct.getModel())
                .operatingSystem(modelProduct.getOperatingSystem())
                .display(modelProduct.getDisplay())
                .camera(modelProduct.getCamera())
                .cpu(modelProduct.getCpu())
                .memory(modelProduct.getMemory())
                .description(modelProduct.getDescription())
                .price(modelProduct.getPrice())
                .amount(modelProduct.getAmount())
                .build();
    }
}
