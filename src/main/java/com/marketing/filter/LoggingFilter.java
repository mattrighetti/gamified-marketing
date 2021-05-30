package com.marketing.filter;

import com.marketing.utils.Servlets;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter(servletNames = {
        Servlets.LOGIN,
        Servlets.REGISTER,
        Servlets.QUESTIONNAIRE
})
public class LoggingFilter implements Filter {
    private FilterConfig filterConfig;

    @Inject
    private Logger logger;

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
        logger.debug("==============================================");
        Enumeration<String> params = servletRequest.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            logger.debug("Parameter Name - '{}', Value - '{}'", paramName, servletRequest.getParameter(paramName));
        }
        logger.debug("==============================================");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
