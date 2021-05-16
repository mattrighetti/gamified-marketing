package com.marketing.filter;

import com.marketing.utils.ServletUrls;
import com.marketing.utils.SessionAttribute;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(servletNames = "/HomeServlet")
public class ProtectedPagesFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Check if user is logged, otherwise redirect to Login/Register servlet
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionAttribute.IS_LOGGED) == null) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ServletUrls.LOGIN);
            dispatcher.forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
