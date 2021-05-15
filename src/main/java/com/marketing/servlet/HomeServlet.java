package com.marketing.servlet;

import com.marketing.utils.ServletUrls;
import com.marketing.utils.SessionAttribute;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkLoggedIn(request, response);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Welcome</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("Welcome");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void checkLoggedIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect(ServletUrls.LOGIN);
        } else {
            boolean isLoggedIn = (boolean) session.getAttribute(SessionAttribute.IS_LOGGED);
            if (!isLoggedIn) {
                response.sendRedirect(ServletUrls.LOGIN);
            }
        }
    }
}
