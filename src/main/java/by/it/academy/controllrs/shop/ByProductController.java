package by.it.academy.controllrs.shop;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = "/readProductAdd")
public class ByProductController extends HttpServlet {
    private final Logger logger = Logger.getLogger(ByProductController.class);
    private final List<ModelProduct> products = new ArrayList<>();
//    private final ProductRepository<Product> productProductRepositoryRepository = new ProductDBRepository(products);
//    private final ProductService<Product> productProductServiceService = new ProductDBService(productProductRepositoryRepository);

private final ProductsRepository<ModelProduct> modelProductRepository
        = new ProductAPIRepository(products);
    private  final ProductsService<ModelProduct> modelProductService =
            new ProductAPIService(modelProductRepository);
    private static final String PRODUCT_LIST_PATH = "/pages/product/List_product.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String name = req.getParameter("name");
        final String model = req.getParameter("model");
        final String price= req.getParameter("price");
        final int amount = Integer.parseInt(req.getParameter("amount"));
        ModelProduct modelProduct = new ModelProduct(new Product(name),
                model, Integer.parseInt(price), Integer.parseInt(price));

        ModelProduct product = (ModelProduct) modelProductService.readProduct(modelProduct);
        ModelProduct modelProducts = new ModelProduct(product.getProduct(), product.getModel(), product.getPrice(), product.getAmount());
            if ((Objects.nonNull(req.getSession())) && Objects.isNull(req.getSession().getAttribute("productRead"))) ;
            HttpSession session = req.getSession();
            session.setAttribute("productRead", modelProducts);

            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_LIST_PATH);
            requestDispatcher.forward(req, resp);

        }
    }

