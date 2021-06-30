package com.marketing.filter;

import com.marketing.utils.Servlets;
import com.marketing.utils.SessionAttribute;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(servletNames = {
        Servlets.DELETE_QUESTIONNAIRE,
        Servlets.INSPECT_QUESTIONNAIRE,
        Servlets.CREATE_PRODUCT
})
public class AdminProtectedFilter extends ProtectionFilter {
    @Override
    protected boolean satisfy(HttpServletRequest request) {
        return !(request.getSession(false) == null || request.getSession(false).getAttribute(SessionAttribute.IS_ADMIN) == null);
    }
}
