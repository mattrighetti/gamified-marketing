package com.marketing.controllers;

import com.marketing.commons.ServletCommon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BannedServlet", value = "/BannedServlet")
public class BannedServlet extends RendererServlet {

    public BannedServlet() {
        super("/WEB-INF/banned.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletCommon.invalidateSession.run(req, resp);
        renderAndServe(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(404, "Cannot find page");
    }
}
