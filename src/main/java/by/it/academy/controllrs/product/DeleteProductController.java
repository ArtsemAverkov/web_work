package by.it.academy.controllrs.product;

import by.it.academy.entities.product.ModelProduct;
import by.it.academy.services.product.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;

@Slf4j
@RestController
@RequestMapping("/product/delete")
@RequiredArgsConstructor
public class DeleteProductController {
    private final ProductsService productsService;

    /**
     * this method removes the product from the database
     * @param modelProduct get from server
     * @return the  boolean of the deleted product
     */
    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public boolean deleteProduct(@RequestBody @Valid ModelProduct modelProduct){
        return productsService.deleteProduct(modelProduct);

    }
}

