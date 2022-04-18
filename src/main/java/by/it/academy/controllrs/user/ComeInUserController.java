package by.it.academy.controllrs.user;

import by.it.academy.controllrs.product.UpdateProductController;
import by.it.academy.entities.User;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserApiService;
import by.it.academy.services.user.UserService;
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
@WebServlet(urlPatterns = "/user/come_in")
public class ComeInUserController extends HttpServlet {
    private final Logger logger = Logger.getLogger(ComeInUserController.class);
    private final List<User> users = new ArrayList<>();
    private final UserRepository<User> userUserRepository = new UserApiRepository(users);
    private final UserService<User> userUserService = new UserApiService(userUserRepository);
    private static final String USER_LIST_PATH = "/pages/shop/userShop.jsp";



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final User user = new User(login, password);
        User read = userUserService.read(user);
        logger.info(read);
            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(USER_LIST_PATH);
            requestDispatcher.forward(req, resp);

        }
    }


