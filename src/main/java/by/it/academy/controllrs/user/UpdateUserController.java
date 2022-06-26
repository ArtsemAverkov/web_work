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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/user/update")

public class UpdateUserController extends HttpServlet {
    private final Logger logger = Logger.getLogger(UpdateUserController.class);
    private final List<User> users = new ArrayList<>();

    private final UsersRepository<User> usersRepository = new UserDBRepository(users);
    private final UsersService<User> usersService = new UserDBService(usersRepository);


    private static final String USER_UPDATE= "/pages/shop/userShop.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Long id = Long.valueOf(req.getParameter("id"));

        final String newLogin = req.getParameter("newLogin");
        final String newPassword = req.getParameter("newPassword");

        final User user = new User(id);
        final User newUser = new User(newLogin, newPassword);

        usersService.update(user, newUser);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher(USER_UPDATE);
        requestDispatcher.forward(req,resp);

    }
}
