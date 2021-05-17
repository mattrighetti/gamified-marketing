package com.marketing.filter;

import com.marketing.utils.Servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(servletNames = { Servlets.REGISTER })
public class BadWordFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // TODO extract data from request
        String data = servletRequest.getParameter("data");
        checkIfWordIsBanned(data);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void checkIfWordIsBanned(String word) {
        // TODO regex logic to remove bad words
    }
}
