package by.it.academy.testingTools.common.controllers.product;

import by.it.academy.entities.product.ModelProduct;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.services.product.ProductsService;
import by.it.academy.testingTools.common.extensions.product.InvalidProductParameterResolver;
import by.it.academy.testingTools.common.extensions.product.ValidProductParameterResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

@DisplayName("Testing Product")
public class ProductServiceImplTest {
    @Nested
    @ExtendWith(MockitoExtension.class)
    @ExtendWith(ValidProductParameterResolver.class)
    public class ValidData{
        @Mock
        private ProductRepository productRepository;
        @InjectMocks
        private ProductsService productsService;

        @DisplayName("All Product are Valid")
        @RepeatedTest(3)
        void shouldUpdateProductWhenProductValid(Long id, ModelProduct modelProduct){
            Mockito.when(productRepository.findById(id)).thenReturn(Optional.ofNullable(modelProduct));
            Assertions.assertEquals(Objects.nonNull(modelProduct), productsService.updateProduct(modelProduct, id));
            Mockito.verify(productRepository, Mockito.times(1)).save(modelProduct);

        }
        @RepeatedTest(3)
        void shouldCreateProductWhenProductIsValid(ModelProduct modelProduct){
            Mockito.when(productRepository.save(modelProduct)).thenReturn(modelProduct);
            Assertions.assertEquals(modelProduct.getId(), productsService.create(modelProduct));
            Mockito.verify(productRepository, Mockito.times(1)).save(modelProduct);
        }

        @RepeatedTest(3)
        void shouldDeleteProductWhenProductIsValid(Long id){
            ModelProduct modelProduct = new ModelProduct();
            Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(modelProduct));
            Mockito.verify(productRepository, Mockito.times(1)).deleteById(id);
        }

        @RepeatedTest(3)
        void shouldGetProductWhenProductIsValid(Long id){
            ModelProduct modelProduct = new ModelProduct();
            Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(modelProduct));
            Assertions.assertEquals(modelProduct.getId(), productsService.getProduct(id));
            Mockito.verify(productRepository, Mockito.times(1)).findById(id);
        }
    }

    @Nested
    @ExtendWith(MockitoExtension.class)
    @ExtendWith(InvalidProductParameterResolver.class)
    public class InvalidData{
        @InjectMocks
        private ProductsService productsService;

        @RepeatedTest(3)
        void shouldUpdateProductWheProductIsInvalid (Long id, ModelProduct modelProduct){
            Assertions.assertThrows(EntityNotFoundException.class,
                    () ->productsService.updateProduct(modelProduct, id));
        }
        @RepeatedTest(3)
        void shouldCreateProductWheProductIsInvalid (ModelProduct modelProduct){
            Assertions.assertThrows(EntityNotFoundException.class,
                    () -> productsService.create(modelProduct));
        }
        @RepeatedTest(3)
        void shouldDeleteProductWheProductIsInvalid (Long id){
            Assertions.assertThrows(EntityNotFoundException.class,
                    () -> productsService.deleteProduct(id));
        }
        @RepeatedTest(3)
        void shouldGetProductWheProductIsInvalid (Long id){
            Assertions.assertThrows(EntityNotFoundException.class,
                    () -> productsService.getProduct(id));
        }

    }
}
