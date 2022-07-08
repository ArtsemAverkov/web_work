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
@Scope("prototype")
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
    public ModelProduct create(ModelProduct modelProduct) {
        ModelProduct buildProduct = buildProduct(modelProduct);
        return productRepository.save(buildProduct);
    }

    /**
     * this method counts the product in the database
     * @param modelProduct get from controller
     * @return product
     */
    @Override
    @Transactional
    public ModelProduct getProduct(ModelProduct modelProduct) {
        UUID id = buildProduct(modelProduct).getId();
        return productRepository.findById(id).get();
    }

    /**
     * update product by id
     * @param modelProduct get from controller
     * @param id get from controller
     * @return false
     */
    @Override
    @Transactional
    public boolean updateProduct(ModelProduct modelProduct, UUID id) {
        UUID idProduct = buildProduct(modelProduct).getId();
        ModelProduct product = productRepository.findById(idProduct).get();
        if (Objects.nonNull(product)){
            product.setProduct(modelProduct.getProduct());
            product.setModel(modelProduct.getModel());
            product.setPrice(modelProduct.getPrice());
            product.setAmount(modelProduct.getAmount());
            productRepository.save(product);
        }else {
            log.info("Product not fount");
        }
        return false;
    }

    /**
     * deleted product by id
     * @param modelProduct get from controller
     * @return false
     */
    @Override
    @Transactional
    public boolean deleteProduct(ModelProduct modelProduct) {
        UUID idProduct = buildProduct(modelProduct).getId();
        productRepository.deleteById(idProduct);
        return true;
    }

    /**
     * this method receives the set value from the
     * @return the received attribute value
     */
    @Override
    @Transactional
    public List<ModelProduct> readProducts(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    @Override
    @Transactional
    public void checkModel(String model) {
        boolean productModel = productRepository.existModelProduct(model);
        if (productModel){
            log.warn("ModelProduct is exist");
        }else {
            log.info("login does not exist");
        }

    }
    @SneakyThrows
    @Override
    @Transactional
    public UUID saveImageForModelProduct(UUID id, MultipartFile image) {
        return imageForModelProductRepository.save(new ImageForModelProduct(id, image.getBytes())).getId() ;
    }

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
