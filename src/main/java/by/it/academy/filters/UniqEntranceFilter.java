package by.it.academy.filters;

import by.it.academy.entities.User;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserApiService;
import by.it.academy.services.user.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebFilter(urlPatterns = "/user/come_in")
public class UniqEntranceFilter implements Filter {
    private final List<User> users = new ArrayList<>();
    private final UserRepository<User> userUserRepository = new UserApiRepository(users);
    private final UserService<User> userUserService = new UserApiService(userUserRepository);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final String login = servletRequest.getParameter("login");
        final String password = servletRequest.getParameter("password");
        final User user = new User(login, password);
        final User read = userUserService.read(user);
        if (Objects.nonNull(read)){
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
