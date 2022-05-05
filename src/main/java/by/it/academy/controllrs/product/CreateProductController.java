package by.it.academy.controllrs.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.product.ProductDBRepository;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.services.product.ProductDBService;
import by.it.academy.services.product.ProductService;
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
    private final List<Product> products = new ArrayList<>();
    private final ProductRepository<Product> productProductRepositoryRepository = new ProductDBRepository( products);
    private final ProductService<Product> productProductServiceService = new ProductDBService(productProductRepositoryRepository);
    private static final String PRODUCT_LIST_PATH = "/pages/product/Create_Product.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String name = req.getParameter("name");
        final String model = req.getParameter("model");
        final String price = req.getParameter("price");
        final int amount = Integer.parseInt(req.getParameter("amount"));
        final Product product = new Product(name,model ,price, amount);
        logger.info("CreateProductController" + product);
        productProductServiceService.insert(product);


        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_LIST_PATH);
        requestDispatcher.forward(req, resp);

    }
}
