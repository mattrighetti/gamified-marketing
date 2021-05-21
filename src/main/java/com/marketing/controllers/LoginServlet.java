package com.marketing.controllers;

import com.marketing.bean.AccessLogBean;
import com.marketing.bean.LoginBean;
import com.marketing.utils.Servlets;
import com.marketing.utils.SessionAttribute;
import com.marketing.utils.UrlBuilder;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends RendererServlet {

    public LoginServlet() {
        super("/WEB-INF/login.html");
    }

    @EJB
    private LoginBean loginBean;

    @EJB
    private AccessLogBean accessLogBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, Object> vars = new HashMap<>();
        vars.put("formAction", "LoginServlet");
        vars.put("failedAttempt", false);
        renderAndServeWithVariables(request, response, vars);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // Check credentials
        if (loginBean.login(username, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute(SessionAttribute.IS_LOGGED, true);
            // TODO change this section, it is better that login returns the actual user so that we can pass it in the session itself
            session.setAttribute(SessionAttribute.IS_ADMIN, false);
            session.setAttribute(SessionAttribute.USERNAME, username);
            response.sendRedirect(UrlBuilder.getUrl(request, Servlets.HOME));
            accessLogBean.logUserAccess(username);
        } else {
            HashMap<String, Object> vars = new HashMap<>();
            vars.put("formAction", "LoginServlet");
            vars.put("failedAttempt", true);
            vars.put("alertText", "Incorrect data, try again with valid credentials.");
            renderAndServeWithVariables(request, response, vars);
        }
    }
}
