package by.it.academy.filters;

import by.it.academy.entities.User;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserApiService;
import by.it.academy.services.user.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RolesUserFilter implements Filter {
    private final List<User> users = new ArrayList<>();
    private final UserRepository<User> userUserRepository = new UserApiRepository(users);
    private final UserService<User> userUserService = new UserApiService(userUserRepository);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        User user = new User(login, password);
        User read = userUserService.userType(user);
        if ("ADMIN".equalsIgnoreCase(read.getUserType())) {
                RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/pages/shop/adminShop.jsp");
                requestDispatcher.forward(servletRequest, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);

            }
        }


    @Override
    public void destroy() {

    }
}
