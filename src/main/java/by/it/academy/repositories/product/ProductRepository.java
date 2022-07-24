package by.it.academy.repositories.product;

import by.it.academy.entities.product.ModelProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository <ModelProduct, Long>{

}
