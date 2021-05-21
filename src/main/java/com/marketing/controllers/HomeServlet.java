package com.marketing.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends RendererServlet {

    public HomeServlet() {
        super("/WEB-INF/userhome.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        HashMap<String, Object> vars = new HashMap<>();
        vars.put("username", session.getAttribute("username"));
        renderAndServeWithVariables(request, response, vars);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
