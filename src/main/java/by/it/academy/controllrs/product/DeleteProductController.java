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
@WebServlet(urlPatterns = {"/delete"})
public class DeleteProductController extends HttpServlet {
    private final Logger logger = Logger.getLogger(DeleteProductController.class);
    private final List<ModelProduct> products = new ArrayList<>();
    private final ProductsRepository<ModelProduct> modelProductRepository =
            new ProductAPIRepository(products);
    private  final ProductsService<ModelProduct> modelProductService =
            new ProductAPIService(modelProductRepository);

   private static final String PRODUCT_DELETE = "/pages/product/Delete_Product.jsp";

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String name = req.getParameter("name");
        final String model = req.getParameter("model");
        final Long id = Long.valueOf(req.getParameter("ID"));
        //todo  изменить принимаемый параметр на id
        final ModelProduct modelProduct = new ModelProduct(id);
        logger.info("DeleteProductController" + modelProduct);
        modelProductService.deleteProduct(modelProduct);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_DELETE);
        requestDispatcher.forward(req,resp);
    }


    }

