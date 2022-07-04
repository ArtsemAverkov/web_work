package by.it.academy.controllrs.shop;


import by.it.academy.entities.product.ModelProduct;
import by.it.academy.services.product.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;


@Slf4j
@RestController
@RequestMapping("/byProduct")
@RequiredArgsConstructor
public class ByProductController  {
    private final ProductsService productsService;

    /**
     * purchase of goods
     * @param modelProduct  get from server
     * @param session to set the value of an attribute
     * @return the selected product
     * @see  ModelProduct
     */
    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ModelProduct byProduct (@RequestBody @Valid ModelProduct modelProduct,HttpSession session){
        ModelProduct readProduct = (ModelProduct) productsService.readProduct(modelProduct);


        if (Objects.nonNull(readProduct)){
            ModelProduct build = ModelProduct.builder()
                    .product(readProduct.getProduct())
                    .model(readProduct.getModel())
                    .price(readProduct.getPrice())
                    .amount(readProduct.getAmount() - modelProduct.getAmount())
                    .build();
            boolean updateProduct = productsService.updateProduct(build, readProduct.getId());
            log.info("updateProduct = " + updateProduct);
        }else{
            log.info("product not fount");
        }


        if (Objects.nonNull(session)){
            session.setAttribute("productRead", ModelProduct.builder()
                    .product(readProduct.getProduct())
                    .model(readProduct.getModel())
                    .price(readProduct.getPrice())
                    .amount(modelProduct.getAmount())
                    .build());
        }


        return readProduct;
    }
    }

