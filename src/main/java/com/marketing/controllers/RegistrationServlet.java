package com.marketing.controllers;

import com.marketing.bean.AccessLogBean;
import com.marketing.bean.RegistrationBean;
import com.marketing.commons.RedirectAfterCompletion;
import com.marketing.utils.Servlets;
import com.marketing.utils.SessionAttribute;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends RendererServlet {

    public RegistrationServlet() {
        super("/WEB-INF/login.html");
    }

    @EJB
    private RegistrationBean registrationBean;

    @EJB
    private AccessLogBean accessLogBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> vars = new HashMap<>();
        vars.put("formAction", "RegistrationServlet");
        vars.put("failedAttempt", false);
        renderAndServeWithVariables(request, response, vars);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        if (registrationBean.register(username, password, email)) {
            RedirectAfterCompletion action = new RedirectAfterCompletion(Servlets.HOME, (req, res) -> {
                HttpSession session = req.getSession(true);
                session.setAttribute(SessionAttribute.IS_LOGGED, true);
                session.setAttribute(SessionAttribute.USERNAME, username);
                accessLogBean.logUserAccess(username);
            });
            action.run(request, response);
        } else {
            HashMap<String, Object> vars = new HashMap<>();
            vars.put("formAction", "RegistrationServlet");
            vars.put("failedAttempt", true);
            vars.put("alertText", String.format("Username %s has already been taken, try with another one.", username));
            renderAndServeWithVariables(request, response, vars);
        }
    }
}
