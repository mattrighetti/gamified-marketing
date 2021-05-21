package com.marketing.filter;

import com.marketing.utils.SessionAttribute;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(servletNames = "HomeServlet")
public class ProtectedPagesFilter extends ProtectionFilter {
    @Override
    protected boolean satisfy(HttpServletRequest request) {
        return !(request.getSession(false) == null || request.getSession(false).getAttribute(SessionAttribute.IS_LOGGED) == null);
    }
}
