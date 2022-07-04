package by.it.academy.controllrs.product;

import by.it.academy.entities.product.ModelProduct;
import by.it.academy.services.product.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j //логер
@RestController // рес подход когда нету вью нету страниц
@RequestMapping("/product/create")
@RequiredArgsConstructor // конструктор на все final поля
public class CreateProductController {
    private final ProductsService productsService;

    /**
     * this method creates a new product
     * @param modelProduct get from server
     * @return the UUID id of the created product
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createProduct(@RequestBody @Valid ModelProduct modelProduct){
        return (UUID) productsService.create(modelProduct);
    }

}
