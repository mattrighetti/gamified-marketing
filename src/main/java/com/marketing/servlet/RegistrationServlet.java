package com.marketing.servlet;

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

@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    @EJB
    private RegistrationBean registrationBean;

    @EJB
    private AccessLogBean accessLogBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendPage(request, response, false);
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
            sendPage(request, response, true);
        }
    }

    private void sendPage(HttpServletRequest request, HttpServletResponse response, boolean showPreviousValues) throws ServletException, IOException {
        sendPageHeader(response);
        sendRegistrationForm(request, response, showPreviousValues);
        sendPageFooter(response);
    }

    private void sendPageHeader(HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Registration Page</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<center>");
    }

    private void sendRegistrationForm(HttpServletRequest request, HttpServletResponse response, boolean showPreviousValues) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        if (showPreviousValues)
            out.println("Registration failed. Please insert a different username.<BR>");

        out.println("<BR>");
        out.println("<BR><H2>Registration Page</H2>");
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
        out.println("<TD>Email:</TD>");
        out.println("<TD><INPUT TYPE=TEXT NAME=email></TD>");
        out.println("</TR>");
        out.println("<TR>");
        out.println("<TD ALIGN=RIGHT COLSPAN=2>");
        out.println("<INPUT TYPE=SUBMIT VALUE=Register></TD>");
        out.println("</TR>");
        out.println("</TABLE>");
        out.println("</FORM>");
    }

    private void sendPageFooter(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }
}
