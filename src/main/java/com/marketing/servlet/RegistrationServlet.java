package com.marketing.servlet;

import com.marketing.bean.RegistrationBean;

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

    @Override
    public void init() throws ServletException {
        this.registrationBean = new RegistrationBean();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendPage(request, response, false);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (registrationBean.register(username, password)) {
            // TODO forward to a page with a new welcome message
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
        out.println("");
    }

    private void sendPageFooter(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }
}
