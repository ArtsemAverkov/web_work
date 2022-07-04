package by.it.academy.controllrs.product;

import by.it.academy.entities.product.ModelProduct;
import by.it.academy.services.product.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/product/read")
@RequiredArgsConstructor
public class ReadProductController {
    private final ProductsService productsService;

    /**
     * this method searches the user database
     * @param modelProduct get from server
     * @return user
     */
    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ModelProduct readProduct (@RequestBody @Valid ModelProduct modelProduct){
        return (ModelProduct) productsService.readProduct(modelProduct);
    }
}
