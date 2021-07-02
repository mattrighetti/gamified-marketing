package com.marketing.controllers;

import com.marketing.bean.ProductBean;
import com.marketing.bean.UserBean;
import com.marketing.entity.Product;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends RendererServlet {

    public HomeServlet() {
        super("/WEB-INF/userhome.html");
    }

    @EJB
    private ProductBean productBean;

    @EJB
    private UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        Product product = productBean.getProductOfTheDay();
        HashMap<String, Object> vars = new HashMap<>();
        vars.put("username", session.getAttribute("username"));
        vars.put("isAdmin", session.getAttribute("isAdmin"));
        if (product != null) {
            vars.put("completed", userBean.hasUserCompiledQuestionnaire(username, product.getId()));
            vars.put("available", true);
            vars.put("imageUrl", product.getImage());
            vars.put("productName", product.getName());
            vars.put("productDescription", product.getDescription());
            vars.put("productId", product.getId());
            vars.put("reviews", product.getReviews());
        } else {
            vars.put("available", false);
        }
        renderAndServeWithVariables(request, response, vars);
    }
}
