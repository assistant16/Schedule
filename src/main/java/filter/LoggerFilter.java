package filter;

import javax.servlet.*;
import java.io.IOException;

public class LoggerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("Before filter");
//        filterChain.doFilter(servletRequest,servletResponse);
//        System.out.println("After filter");
    }

    @Override
    public void destroy() {

    }
}
