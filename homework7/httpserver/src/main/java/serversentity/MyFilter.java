package serversentity;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestUri =  httpServletRequest.getRequestURI();
        if("/products".equals(requestUri) || "/".equals(requestUri))
            chain.doFilter(request,response);
        else
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
