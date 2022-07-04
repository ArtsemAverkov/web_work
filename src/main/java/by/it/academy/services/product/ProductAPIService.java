package by.it.academy.services.product;

import by.it.academy.entities.product.ModelProduct;
import by.it.academy.entities.user.User;
import by.it.academy.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@Scope("prototype")
@RequiredArgsConstructor
public class ProductAPIService implements ProductsService <ModelProduct>{
  private final ProductRepository productRepository;

    /**
     * this method creates a new product
     * @param modelProduct get from controller
     * @return save product
     */
    @Override
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
    public ModelProduct readProduct(ModelProduct modelProduct) {
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
    public boolean deleteProduct(ModelProduct modelProduct) {
        UUID idProduct = buildProduct(modelProduct).getId();
        productRepository.deleteById(idProduct);
        return false;
    }

    /**
     * this method receives the set value from the
     * @return the received attribute value
     */
    @Override
    public List<ModelProduct> readAllProduct() {
        List<ModelProduct> product = new ArrayList<>();
        productRepository.findAll().forEach(product::add);
        return product;
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
                .price(modelProduct.getPrice())
                .amount(modelProduct.getAmount())
                .build();
    }



}
