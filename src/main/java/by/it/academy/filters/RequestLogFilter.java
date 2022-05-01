package by.it.academy.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebFilter(urlPatterns = {"/user/*","/product/*"})
public class RequestLogFilter implements Filter {
  private final Logger log = Logger.getLogger(RequestLogFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
       final HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Map<String, ArrayList<String>> headers = getHeader(httpServletRequest);
        log.info(httpServletRequest.getRequestURI() + ": " + headers);
        chain.doFilter(httpServletRequest, httpServletResponse);



    }

    @Override
    public void destroy() {

    }
    private Map<String, ArrayList<String>> getHeader(HttpServletRequest httpServletRequest){
        Collections.list(httpServletRequest.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h ->Collections.list(httpServletRequest.getHeaders((String) h))));
        return null;
    }

}
