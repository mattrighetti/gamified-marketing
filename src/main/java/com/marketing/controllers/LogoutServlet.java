package com.marketing.controllers;

import com.marketing.commons.RedirectAfterCompletion;
import com.marketing.commons.ServletCommon;
import com.marketing.utils.Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
