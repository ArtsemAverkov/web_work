package by.it.academy.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
@WebFilter(urlPatterns = {"/user/readAllUser","/user/delete","/product/create","/delete","/updateProduct","/user/update"})
public class AuthorisationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest requests = (HttpServletRequest) request;
        final HttpSession session = requests.getSession();

        if ((Objects.nonNull(session)) && Objects.nonNull(session.getAttribute("userType"))) {
            Object attribute = session.getAttribute("userType");

            if ("ADMIN".equals(attribute)){
            chain.doFilter(request,response);
        }else {
            RequestDispatcher requestDispatcher = requests.getRequestDispatcher("/pages/errors/User_error.jsp");
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
