package by.it.academy.filters;

import by.it.academy.entities.User;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserApiService;
import by.it.academy.services.user.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@WebFilter(urlPatterns = {"/user/readAllUser","/user/delete","/product/create","/delete","/updateProduct","/user/update"})
public class AuthorisationFilter implements Filter {
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
        User read = userUserService.userType(user);


        HttpServletRequest request1 = (HttpServletRequest) request;
        final HttpSession session = request1.getSession();

        if ((Objects.nonNull(session)) && Objects.nonNull(session.getAttribute("userType"))) {
            Object attribute = session.getAttribute("userType");
            if (attribute == "ADMIN"){
            chain.doFilter(request,response);
        }else {
            RequestDispatcher requestDispatcher = request1.getRequestDispatcher("/pages/errors/User_errors.jsp");
            requestDispatcher.forward(request, response);
        }
    }else{
        chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
