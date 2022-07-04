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

@Slf4j
@RestController
@RequestMapping("/product/update")
@RequiredArgsConstructor
public class UpdateProductController {
    private final ProductsService productsService;

    /**
     * this method updates modelProduct by id
     * @param modelProduct get from server
     * @param id get from server
     * @returт successful and unsuccessful update
     */
    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public boolean updateProduct(@RequestBody @Valid ModelProduct modelProduct, UUID id){
        return productsService.updateProduct(modelProduct, id);
    }
}
