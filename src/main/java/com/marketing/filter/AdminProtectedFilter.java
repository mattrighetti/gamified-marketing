package com.marketing.filter;

import com.marketing.utils.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

public class AdminProtectedFilter extends ProtectionFilter {
    @Override
    protected boolean satisfy(HttpServletRequest request) {
        // TODO implement logic for pages that only admins are redirected/forwarded to
        return !(request.getSession(false) == null || request.getSession(false).getAttribute(SessionAttribute.IS_LOGGED) == null);
    }
}
