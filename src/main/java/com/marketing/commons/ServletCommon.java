package com.marketing.commons;

import javax.servlet.http.HttpSession;

public class ServletCommon {
    public static final ServletAction invalidateSession = (request, response) -> {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    };
}
