package com.marketing.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

public class TrimFilter implements Filter {
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
        Enumeration<String> attribs = servletRequest.getAttributeNames();
        while (attribs.hasMoreElements()) {
            String attribName = attribs.nextElement();
            String attribValue = servletRequest.getParameter(attribName);
            servletRequest.setAttribute(attribName, attribValue.trim());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
