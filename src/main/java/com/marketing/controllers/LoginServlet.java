package com.marketing.controllers;

import com.marketing.bean.AccessLogBean;
import com.marketing.bean.LoginBean;
import com.marketing.entity.User;
import com.marketing.utils.Servlets;
import com.marketing.utils.SessionAttribute;
import com.marketing.utils.UrlBuilder;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        sendForm(request, response, false, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // Check credentials
        User user = loginBean.login(username, password);
        if (user != null) {
            if (user.getBanned()) {
                sendForm(request, response, true, "This user has been banned for improper use of words, try with a different user.");
                return;
            }
            HttpSession session = request.getSession(true);
            session.setAttribute(SessionAttribute.IS_LOGGED, true);
            session.setAttribute(SessionAttribute.IS_ADMIN, user.getAdmin());
            session.setAttribute(SessionAttribute.USERNAME, user.getUsername());
            response.sendRedirect(UrlBuilder.getUrl(request, Servlets.HOME));
            accessLogBean.logUserAccess(user);
        } else {
            sendForm(request, response, true, "Incorrect data, try again with valid credentials.");
        }
    }

    public void sendForm(HttpServletRequest request, HttpServletResponse response, boolean withError, String errorMessage) throws IOException {
        renderAndServeWithVariables(request, response, new HashMap<String, Object>() {{
            put("formAction", getServletName());
            put("failedAttempt", withError);
            put("alertText", errorMessage);
        }});
    }
}
