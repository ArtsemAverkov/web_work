package by.it.academy.controllrs.product;

import by.it.academy.entities.product.ModelProduct;
import by.it.academy.repositories.product.ProductAPIRepository;
import by.it.academy.repositories.product.ProductsRepository;
import by.it.academy.services.product.ProductAPIService;
import by.it.academy.services.product.ProductsService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/readAllProduct"})
public class ReadAllProduct extends HttpServlet {
    private final Logger logger = Logger.getLogger(ReadAllProduct.class);
    private final List<ModelProduct> products = new ArrayList<>();

    private final ProductsRepository<ModelProduct> modelProductRepository
            = new ProductAPIRepository(products);
    private  final ProductsService<ModelProduct> modelProductService =
            new ProductAPIService(modelProductRepository);

    public static final String PRODUCT_PAGE ="pages/product/List_product.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_PAGE);
        List<List<ModelProduct>> products = modelProductService.readAllProduct();
        List<ModelProduct> product = products.stream()
                .flatMap(l -> l.stream())
                .collect(Collectors.toList());
        product.forEach(System.out::println);

        logger.info("ReadAllProduct :" + product);
         req.setAttribute("products", product);
        requestDispatcher.forward(req,resp);

    }
}
