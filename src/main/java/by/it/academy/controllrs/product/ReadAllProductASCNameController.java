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

@WebServlet(urlPatterns = {"/ReadAllProductASPName"})
public class ReadAllProductASCNameController extends HttpServlet {
    private final Logger logger = Logger.getLogger(ReadAllProduct.class);
    private final List<Product> products = new ArrayList<>();
    private final ProductRepository<Product> productProductRepositoryRepository = new ProductDBRepository( products);
    private final ProductService<Product> productProductServiceService = new ProductDBService(productProductRepositoryRepository);
    public static final String PRODUCT_PAGE ="/pages/product/List_product.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_PAGE);
        List<Product> product = productProductServiceService.readAllProductASCName();
        logger.info("ReadAllProductASCNameController" + product);
        req.setAttribute("products", product);
        requestDispatcher.forward(req,resp);
    }
}
