package com.marketing.commons;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardAfterCompletion implements ServletExecutableStrategy {
    private final ServletAction action;
    private final String forwardServlet;

    public ForwardAfterCompletion(ServletAction action, String forwardServlet) {
        this.action = action;
        this.forwardServlet = forwardServlet;
    }

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) {
        action.run(request, response);
        try {
            request.getRequestDispatcher(forwardServlet).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
