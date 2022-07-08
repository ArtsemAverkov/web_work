package by.it.academy.repositories.product;

import by.it.academy.entities.product.ModelProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;


//@Repository мщжно не помечать анотацией а нужно экстензить от другого репозитория
public interface ProductRepository extends JpaRepository <ModelProduct, UUID>{ // можно КРАД или JpaRepository но последний лучше работает погинация и сортировка
    @Query(value = "select count(model) from model_product where model =: model", nativeQuery = true)
    boolean existModelProduct(String model);
   // boolean existModelProductModel(String model);
}
