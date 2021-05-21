package com.marketing.filter;

import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ProtectionFilter implements Filter {

    protected abstract boolean satisfy(HttpServletRequest request);

    @Inject
    private Logger logger;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (satisfy(request)) {
            logger.debug("User has access to this page");
            filterChain.doFilter(request, response);
        } else {
            logger.debug("User has not access to this page, redirecting to LoginPage");
            response.sendError(403, "Unauthorized");
        }
    }
}
