package com.marketing.servlet;

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
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @EJB
    private LoginBean loginBean;

    @EJB
    private AccessLogBean accessLogBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        sendLoginForm(response, false);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // Check credentials
        if (loginBean.login(username, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute(SessionAttribute.IS_LOGGED, true);
            session.setAttribute(SessionAttribute.USERNAME, username);
            response.sendRedirect(UrlBuilder.getUrl(request, Servlets.HOME));
            accessLogBean.logUserAccess(username);
        } else {
            sendLoginForm(response, true);
        }
    }

    private void sendLoginForm(HttpServletResponse response, boolean withErrorMessage) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Login</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("<CENTER>");

        if (withErrorMessage)
            out.println("Login failed. Please try again.<BR>");

        out.println("<BR>");
        out.println("<BR><H2>Login Page</H2>");
        out.println("<BR>");
        out.println("<BR><FORM METHOD=POST>");
        out.println("<TABLE>");
        out.println("<TR>");
        out.println("<TD>Username:</TD>");
        out.println("<TD><INPUT TYPE=TEXT NAME=username></TD>");
        out.println("</TR>");
        out.println("<TR>");
        out.println("<TD>Password:</TD>");
        out.println("<TD><INPUT TYPE=PASSWORD NAME=password></TD>");
        out.println("</TR>");
        out.println("<TR>");
        out.println("<TD ALIGN=RIGHT COLSPAN=2>");
        out.println("<INPUT TYPE=SUBMIT VALUE=Login></TD>");
        out.println("</TR>");
        out.println("</TABLE>");
        out.println("</FORM>");
        out.println("</CENTER>");
        out.println("</BODY>");
        out.println("</HTML>");
    }
}
