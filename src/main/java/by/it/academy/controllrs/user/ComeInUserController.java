package by.it.academy.controllrs.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.user.UserDBRepository;
import by.it.academy.repositories.user.UsersRepository;
import by.it.academy.services.user.UserDBService;
import by.it.academy.services.user.UsersService;
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
import java.util.stream.Collectors;


@WebServlet(urlPatterns = {"/user/come_in"})
public class ComeInUserController extends HttpServlet {
    private final Logger logger = Logger.getLogger(ComeInUserController.class);
    private final List<User> users = new ArrayList<>();
    private final UsersRepository<User> usersRepository = new UserDBRepository(users);
    private final UsersService<User> usersService = new UserDBService(usersRepository);
    private static final String USER_LIST_PATH = "/readAllProduct";
    private static final String ADMIN_LIST_PATH = "/pages/shop/adminShop.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        final User user = new User(login, password);
        List<User> users = usersService.read(user);// TODO: 23.06.22  user type = null
        List<User> userList = users.stream()
                .filter(user1 -> Boolean.parseBoolean(user1.getUserType()))
                .collect(Collectors.toList());
        logger.info("ComeInUserController"+ userList);


        if ((Objects.nonNull(req.getSession())) && Objects.isNull(req.getSession().getAttribute("userType"))) ;
        HttpSession session = req.getSession();
        session.setAttribute("userType",userList);

        if ("ADMIN".equals(userList)) {
            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(ADMIN_LIST_PATH);
            requestDispatcher.forward(req, resp);
        } else {
            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(USER_LIST_PATH);
            requestDispatcher.forward(req, resp);

        }
    }
}


