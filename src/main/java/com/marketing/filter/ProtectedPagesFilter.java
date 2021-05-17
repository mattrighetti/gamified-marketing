package com.marketing.filter;

import com.marketing.utils.Servlets;
import com.marketing.utils.SessionAttribute;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(servletNames = "HomeServlet")
public class ProtectedPagesFilter implements Filter {

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
        // Check if user is logged, otherwise redirect to Login/Register servlet
        logger.debug("Running ProtectedPageFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionAttribute.IS_LOGGED) == null) {
            logger.debug("User has not access to this page, redirecting to LoginPage");
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher('/' + Servlets.LOGIN);
            dispatcher.forward(request, response);
        } else {
            logger.debug("User has access to this page");
            filterChain.doFilter(request, response);
        }
    }
}
