package by.it.academy.controllrs.product;

import by.it.academy.entities.product.ModelProduct;
import by.it.academy.entities.product.Product;
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
@WebServlet(urlPatterns = "/product/create")
public class CreateProductController extends HttpServlet {
    private final Logger logger = Logger.getLogger(CreateProductController.class);
    private final List<ModelProduct> products = new ArrayList<>();

   private final ProductsRepository<ModelProduct> modelProductRepository
        = new ProductAPIRepository(products);
   private  final ProductsService<ModelProduct> modelProductService =
           new ProductAPIService(modelProductRepository);

    private static final String PRODUCT_LIST_PATH = "/pages/product/Create_Product.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String name = req.getParameter("name");
        final String model = req.getParameter("model");
        final int price = Integer.parseInt(req.getParameter("price"));
        final int amount = Integer.parseInt(req.getParameter("amount"));
        final ModelProduct modelProduct = new ModelProduct(model ,price, amount, new Product(name));

        logger.info("CreateProductController" + modelProduct);
        modelProductService.create(modelProduct);


        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_LIST_PATH);
        requestDispatcher.forward(req, resp);

    }
}
