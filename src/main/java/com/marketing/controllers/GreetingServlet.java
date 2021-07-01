package com.marketing.controllers;

import com.marketing.commons.ServletCommon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GreetingServlet", value = "/GreetingServlet")
public class GreetingServlet extends RendererServlet {

    public GreetingServlet() {
        super("/WEB-INF/greeting.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderAndServe(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(404, "Cannot find page");
    }
}
