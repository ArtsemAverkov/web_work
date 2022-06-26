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

@WebServlet(urlPatterns = "/updateProduct")
public class UpdateProductController extends HttpServlet {
    private final Logger logger = Logger.getLogger(UpdateProductController.class);
    private final  List<ModelProduct> product = new ArrayList<>();

    private final ProductsRepository<ModelProduct> modelProductRepository
            = new ProductAPIRepository(product);
    private  final ProductsService<ModelProduct> modelProductService =
            new ProductAPIService(modelProductRepository);private static final String PRODUCT_UPDATE = "/pages/product/Update_Product.jsp";

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Long id = Long.valueOf(req.getParameter("ID"));

        final String newName = req.getParameter("newName");
        final String newModel = req.getParameter("newModel");
        final int newPrice = Integer.parseInt(req.getParameter("newPrice"));
        final int newAmount = Integer.parseInt(req.getParameter("newAmount"));
        ModelProduct modelProduct = new ModelProduct(id);
        ModelProduct newModelProduct = new ModelProduct(new Product(newName), newModel, newPrice, newAmount);
        modelProductService.updateProduct(modelProduct,newModelProduct);

        logger.info("UpdateProductController" +modelProduct);
        logger.info("UpdateProductController" +newModelProduct);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_UPDATE);
        requestDispatcher.forward(req,resp);



    }
}
