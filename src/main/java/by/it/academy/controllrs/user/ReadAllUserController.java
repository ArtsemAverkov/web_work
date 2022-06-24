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
import java.util.Optional;

@WebServlet(urlPatterns = "/user/readAllUser")
public class ReadAllUserController extends HttpServlet {
    private final Logger logger = Logger.getLogger(ReadAllUserController.class);

    private final List<User> users = new ArrayList<>();

    private final UsersRepository<User> usersRepository = new UserDBRepository(users);
    private final UsersService<User> usersService = new UserDBService(usersRepository);

    private static final String USER_LIST = "/pages/user/List_Usher.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(USER_LIST);
        Optional<List<User>> user = usersService.readAllUser();
        req.setAttribute("users", user);
        requestDispatcher.forward(req,resp);
    }
}
