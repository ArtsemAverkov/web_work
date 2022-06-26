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

@WebServlet(urlPatterns = "/user/delete")
public class DeleteUserController extends HttpServlet {
    private final Logger logger = Logger.getLogger(DeleteUserController.class);
    private final List<User> users = new ArrayList<>();

    private final UsersRepository<User> usersRepository = new UserDBRepository(users);
    private final UsersService<User> usersService = new UserDBService(usersRepository);

    private static final String USER_DELETE = "/pages/user/Delete_User.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Long id = Long.valueOf(req.getParameter("id"));
        final User user = new User(id);
        logger.info("DeleteUserController :" + user);
       usersService.delete(user);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher(USER_DELETE);
        requestDispatcher.forward(req,resp);
    }
}
