package com.marketing.filter;

import com.marketing.bean.ProductBean;
import com.marketing.bean.UserBean;
import com.marketing.utils.Servlets;
import com.marketing.utils.SessionAttribute;
import org.slf4j.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @WebFilter(servletNames = { Servlets.QUESTIONNAIRE })
public class QuestionnaireFilter implements Filter {
    private FilterConfig filterConfig;
    
    @EJB
    private ProductBean productBean;
    
    @EJB
    private UserBean userBean;

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
        if (hasUrlBeenTampered(servletRequest) | userHasNotCompletedQuestionnaire(servletRequest)) {
            HttpServletResponse response = (HttpServletResponse) servletResponse; 
            response.sendError(403, "Unauthorized");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public boolean hasUrlBeenTampered(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String productId = request.getParameter("product");
        if (productId != null) {
            long productIdExpected = productBean.getProductOfTheDay().getId();
            return productIdExpected != Long.parseLong(productId);
        }
        return false;
    }

    private boolean userHasNotCompletedQuestionnaire(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String productId = request.getParameter("product");
        String username = (String) request.getSession(false).getAttribute(SessionAttribute.USERNAME);
        if (productId != null) {
            return userBean.hasUserCompiledQuestionnaire(username, Long.parseLong(productId));
        }
        return false;
    }
}
