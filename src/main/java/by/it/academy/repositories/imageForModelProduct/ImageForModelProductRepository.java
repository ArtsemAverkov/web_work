package by.it.academy.repositories.imageForModelProduct;


import by.it.academy.entities.ImageForModelProduct;
import by.it.academy.entities.product.ModelProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageForModelProductRepository extends JpaRepository<ImageForModelProduct, UUID> {
}
