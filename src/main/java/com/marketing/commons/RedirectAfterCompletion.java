package com.marketing.commons;

import com.marketing.utils.UrlBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectAfterCompletion implements ServletExecutableStrategy {
    private final ServletAction action;
    private final String redirectUrl;

    public RedirectAfterCompletion(String redirectUrl, ServletAction action) {
        this.redirectUrl = redirectUrl;
        this.action = action;
    }

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) {
        action.run(request, response);
        String url = UrlBuilder.getUrl(request, redirectUrl);
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}