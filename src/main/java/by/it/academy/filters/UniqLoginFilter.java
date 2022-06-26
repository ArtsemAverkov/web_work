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
import java.util.Optional;

@WebFilter(urlPatterns = {"/user/create"})
public class UniqLoginFilter implements Filter {

    private final Logger logger = Logger.getLogger( UniqLoginFilter.class);
    private final List<User> users = new ArrayList<>();
    private final UsersRepository<User> usersRepository = new UserDBRepository(users);
    private final UsersService<User> usersService = new UserDBService(usersRepository);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        final User user = new User(login, password);
        List<User> read = usersService.read(user);

        Optional<List<User>> optionalUserList = Optional.ofNullable(read);
        if (Objects.isNull(optionalUserList)){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/errors/login_error.jsp");
            requestDispatcher.forward(request, response);
        }else {
            chain.doFilter(request,response);
        }
    }
    @Override
    public void destroy() {

    }
}
