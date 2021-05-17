package com.marketing.utils;

import javax.servlet.http.HttpServletRequest;

public class UrlBuilder {
    public static String getUrl(HttpServletRequest request, String... components) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(request.getContextPath());
        for (String component: components) {
            stringBuilder.append('/');
            stringBuilder.append(component);
        }
        return stringBuilder.toString();
    }
}
