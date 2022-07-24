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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProduct(@RequestBody @Valid ModelProduct modelProduct){
        return productsService.create(modelProduct);
    }

    /**
     * this method removes the product from the database
     * @param id get from server

     */

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id){
        productsService.deleteProduct(id);
    }

    /**
     * this method returns a collection of all products in the database
     * @return collection of all products
     */

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ModelProduct> readProducts(@PageableDefault(page = 0)
                                                        @SortDefault(sort = "name") Pageable pageable){
        return productsService.readProducts(pageable);
    }

    /**
     * this method searches the user database
     * @param id get from server
     * @return product
     */

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ModelProduct getProduct (@PathVariable Long id){
        return productsService.getProduct(id);
    }

    /**
     * this method updates modelProduct by id
     * @param modelProduct get from server
     * @param id get from server
     * @returт successful and unsuccessful update
     */

    @PatchMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean updateProduct(@PathVariable Long id, @RequestBody @Valid ModelProduct modelProduct){
        return productsService.updateProduct(modelProduct, id);
    }


    /**
     * this method save image for modelProduct by id
     * @param modelProductId get from server
     * @param image get from local
     * @returт successful and unsuccessful save
     */
    @PostMapping("{modelProductId}/imageForModelProduct")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UUID saveImageForModelProduct(@PathVariable UUID modelProductId,
                                          @RequestBody @NonNull MultipartFile image){
        return productsService.saveImageForModelProduct(modelProductId, image);
    }


    /**
     * this method updates modelProduct by id
     * @param modelProductId get from server
     * @returт successful and unsuccessful getImage
     */
    @GetMapping(value = "{modelProductId}/imageForModelProduct", produces = MediaType.IMAGE_PNG_VALUE)
    public  byte[] getImageForModelProduct(@PathVariable UUID modelProductId){
        return productsService.getImageForModelProduct(modelProductId);
    }

}
