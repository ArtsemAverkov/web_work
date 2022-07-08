package by.it.academy.controllers.product;

import by.it.academy.entities.product.ModelProduct;
import by.it.academy.services.product.ProductsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    public final ProductsService productsService;

    /**
     * this method creates a new product
     * @param modelProduct get from server
     * @return the UUID id of the created product
     */
    @RequestMapping("/insert")
    @PostMapping(name = "product/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ModelProduct createProduct(@RequestBody @Valid ModelProduct modelProduct){
        return productsService.create(modelProduct);
    }

    /**
     * this method removes the product from the database
     * @param modelProduct get from server
     * @return the  boolean of the deleted product
     */
    @RequestMapping("/delete")
    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public boolean deleteProduct(@RequestBody @Valid ModelProduct modelProduct){
        return productsService.deleteProduct(modelProduct);

    }

    /**
     * this method returns a collection of all products in the database
     * @return collection of all products
     */
    @RequestMapping("/readProducts")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private final List<ModelProduct> readProducts(@PageableDefault(page = 0)
                                                        @SortDefault(sort = "name") Pageable pageable){
        return productsService.readProducts(pageable);
    }

    /**
     * this method searches the user database
     * @param modelProduct get from server
     * @return product
     */
    @RequestMapping("/getProduct")
    @GetMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ModelProduct getProduct (@RequestBody @Valid ModelProduct modelProduct){
        return productsService.getProduct(modelProduct);
    }

    /**
     * this method updates modelProduct by id
     * @param modelProduct get from server
     * @param id get from server
     * @returт successful and unsuccessful update
     */
    @RequestMapping("/update")
    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public boolean updateProduct(@RequestBody @Valid ModelProduct modelProduct, UUID id){
        return productsService.updateProduct(modelProduct, id);
    }


    @PostMapping("{modelProductId}/imageForModelProduct")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UUID  saveImageForModelProduct(@PathVariable UUID modelProductId,
                                          @RequestBody @NonNull MultipartFile image){
        return productsService.saveImageForModelProduct(modelProductId, image);
    }

    @GetMapping(value = "{modelProductId}/imageForModelProduct", produces = MediaType.IMAGE_PNG_VALUE)
    public  byte[] getImageForModelProduct(@PathVariable UUID modelProductId){
        return productsService.getImageForModelProduct(modelProductId);
    }
    @RequestMapping(method = RequestMethod.HEAD)
    public void checkModelProductExist(@RequestParam String model){
    productsService.checkModel(model);
    }

}
