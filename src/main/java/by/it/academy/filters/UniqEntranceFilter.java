package by.it.academy.filters;

import by.it.academy.entities.User;

import by.it.academy.repositories.user.UserDBRepository;
import by.it.academy.repositories.user.UsersRepository;
import by.it.academy.services.user.UserDBService;
import by.it.academy.services.user.UsersService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebFilter(urlPatterns = "/user/come_in")
public class UniqEntranceFilter implements Filter {
    private final Logger logger = Logger.getLogger(UniqEntranceFilter.class);
    private final List<User> users = new ArrayList<>();
    private final UsersRepository<User> usersRepository = new UserDBRepository(users);
    private final UsersService<User> usersService = new UserDBService(usersRepository);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        final String login = servletRequest.getParameter("login");
        final String password = servletRequest.getParameter("password");
        final User user = new User(login, password);
        List<User> read = usersService.read(user);
        if (Objects.isNull(read)){
          RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/pages/errors/user_login_error.jsp");
          requestDispatcher.forward(servletRequest, servletResponse);
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
