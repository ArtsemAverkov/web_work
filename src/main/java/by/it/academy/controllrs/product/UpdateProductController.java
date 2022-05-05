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

@WebServlet(urlPatterns = "/updateProduct")
public class UpdateProductController extends HttpServlet {
    private final Logger logger = Logger.getLogger(UpdateProductController.class);
    private final  List<Product> product = new ArrayList<>();
    private final ProductRepository<Product> productProductRepository =new ProductDBRepository(product);
    private final ProductService<Product> productProductService = new ProductDBService(productProductRepository);
    private static final String PRODUCT_UPDATE = "/pages/product/Update_Product.jsp";

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      final String name = req.getParameter("name");
      final String model = req.getParameter("model");
      final String price = req.getParameter("price");
      final int amount = Integer.parseInt(req.getParameter("amount"));
        final String newName = req.getParameter("newName");
        final String newModel = req.getParameter("newModel");
        final String newPrice = req.getParameter("newPrice");
        final int newAmount = Integer.parseInt(req.getParameter("newAmount"));
      final Product product = new Product(name, model, price, amount);
      final Product newProduct = new Product(newName,newModel,newPrice,newAmount);
      productProductService.updateProduct(product,newProduct);
        logger.info("UpdateProductController" +product);
        logger.info("UpdateProductController" +newProduct);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_UPDATE);
        requestDispatcher.forward(req,resp);



    }
}
