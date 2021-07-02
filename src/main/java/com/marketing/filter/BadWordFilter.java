package com.marketing.filter;

import com.marketing.bean.ForbiddenWordBean;
import com.marketing.bean.UserBean;
import com.marketing.commons.RedirectAfterCompletion;
import com.marketing.utils.Servlets;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@WebFilter(servletNames = {Servlets.QUESTIONNAIRE})
public class BadWordFilter implements Filter {
    private FilterConfig filterConfig;

    @EJB
    private ForbiddenWordBean forbiddenWordBean;

    @EJB
    private UserBean userBean;

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
        Enumeration<String> params = servletRequest.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            if (checkIfWordIsBanned(servletRequest.getParameter(paramName))) {
                userBean.banUser(getCurrentUsername((HttpServletRequest) servletRequest));
                RedirectAfterCompletion redirect = new RedirectAfterCompletion(Servlets.BANNED, null);
                redirect.run((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean checkIfWordIsBanned(String text) {
        List<String> forbiddenEntered = Arrays.stream(text.split(" "))
                .map(String::toLowerCase)
                .filter(s -> forbiddenWordBean.getForbiddenWords().contains(s))
                .collect(Collectors.toList());
        return !forbiddenEntered.isEmpty();
    }

    private String getCurrentUsername(HttpServletRequest request) {
        return (String) request.getSession(false).getAttribute("username");
    }
}
