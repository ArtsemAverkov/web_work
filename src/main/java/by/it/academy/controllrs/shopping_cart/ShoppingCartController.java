package by.it.academy.controllrs.shopping_cart;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/shopping_cart")
public class ShoppingCartController extends HttpServlet {
private static final String SHOPPING_CART = "pages/shopping_cart/shopping_cart.jsp";
    private final Logger logger = Logger.getLogger( ShoppingCartController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpServletRequest request1 = req;
        final HttpSession session = request1.getSession();

        if ((Objects.nonNull(session)) && Objects.nonNull(session.getAttribute("productRead"))) {
            Object product = session.getAttribute("productRead");


            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(SHOPPING_CART);
            logger.info("ShoppingCartController" + product);
            req.setAttribute("products", product);
            requestDispatcher.forward(req, resp);


        }
    }
}
