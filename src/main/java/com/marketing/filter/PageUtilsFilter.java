package com.marketing.filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Filter that applies common html tags automatically to each response
 *
 * This can also be used to wrap responses with navigation bars and page footers
 */
public class PageUtilsFilter implements Filter {
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
        PrintWriter out = servletResponse.getWriter();
        out.println("<html>");
        out.println("<body>");
        filterChain.doFilter(servletRequest, servletResponse);
        out.println("</body>");
        out.println("</html>");
    }
}
