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
@WebFilter(urlPatterns = {"/user/create"})
public class UniqLoginFilter implements Filter {
    private final List<User> users = new ArrayList<>();
    private final UserRepository<User> userUserRepository = new UserApiRepository(users);
    private final UserService<User> userUserService = new UserApiService(userUserRepository);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password);
        User read = userUserService.read(user);
        if (read != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/errors/login_error.jsp");
            requestDispatcher.forward(request, response);
        }else{
           chain.doFilter(request,response);
        }
    }
    @Override
    public void destroy() {

    }
}
