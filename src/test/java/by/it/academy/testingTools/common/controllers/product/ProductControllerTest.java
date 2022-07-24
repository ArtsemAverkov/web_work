package by.it.academy.testingTools.common.controllers.product;

import by.it.academy.controllers.product.ProductController;
import by.it.academy.entities.product.ModelProduct;
import by.it.academy.entities.product.Product;
import by.it.academy.services.product.ProductsService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.org.hamcrest.Matchers;

import java.util.Random;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductsService productsService;
    @Autowired
    private Gson gson;

    @Test
    @DisplayName("Test getting a user with a valid request")
    void getUser() throws Exception {
        Random random = new Random();
        long id = random.nextLong();
        ModelProduct modelProduct = new ModelProduct();
        modelProduct.setId(id);
        modelProduct.setProduct(new Product(1, "iPhone"));
        modelProduct.setModel("XS");
        Mockito.when(productsService.getProduct(id)).thenReturn(modelProduct);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.id",
                        Matchers.is(modelProduct.getId())))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.model",
                        Matchers.is(modelProduct.getModel())))
                .andDo(MockMvcResultHandlers.print());
    }
        @Test
        @DisplayName("User record test with valid request")
        void createUser () throws Exception {
            ModelProduct modelProduct = new ModelProduct();
            modelProduct.setProduct(new Product(1, "iPhone"));
            modelProduct.setModel("XS");
            Mockito.when(productsService.create(modelProduct)).thenReturn(modelProduct.getId());

            mockMvc.perform(MockMvcRequestBuilders.post("/product")
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.id",
                            Matchers.is(modelProduct.getId())))
                    .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.model",
                            Matchers.is(modelProduct.getModel())))
                    .andDo(MockMvcResultHandlers.print());

        }
        @Test
        @DisplayName("User deletion test with valid request")
        void deleteUser () throws Exception {
            Random random = new Random();
            long id = random.nextLong();
            ModelProduct modelProduct = new ModelProduct();
            modelProduct.setId(id);
            Mockito.when(productsService.getProduct(id)).thenReturn(modelProduct);

            mockMvc.perform(MockMvcRequestBuilders.delete("/product/{id}", id)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.id",
                            Matchers.is(modelProduct.getId().toString())))
                    .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.model",
                            Matchers.is(modelProduct.getModel())))
                    .andDo(MockMvcResultHandlers.print());

        }
        @Test
        @DisplayName("User update test with valid request")
        void updateUser () throws Exception {
            Random random = new Random();
            long id = random.nextLong();
            ModelProduct modelProduct = new ModelProduct();
            modelProduct.setId(id);
            modelProduct.setProduct(new Product(1, "iPhone"));
            modelProduct.setModel("XS");
            Mockito.when(productsService.updateProduct(modelProduct, id));

            mockMvc.perform(MockMvcRequestBuilders.patch("/user/{id}", id)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.id",
                            Matchers.is(modelProduct.getId().toString())))
                    .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.model",
                            Matchers.is(modelProduct.getModel())))
                    .andDo(MockMvcResultHandlers.print());
        }
    }

