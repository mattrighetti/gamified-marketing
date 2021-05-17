package com.marketing.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServletExecutableStrategy {
    void run(HttpServletRequest request, HttpServletResponse response);
}
