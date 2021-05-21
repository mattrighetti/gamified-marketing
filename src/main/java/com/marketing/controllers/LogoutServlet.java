package com.marketing.controllers;

import com.marketing.commons.RedirectAfterCompletion;
import com.marketing.commons.ServletCommon;
import com.marketing.utils.Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        RedirectAfterCompletion action = new RedirectAfterCompletion(Servlets.LOGIN, ServletCommon.invalidateSession);
        action.run(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
