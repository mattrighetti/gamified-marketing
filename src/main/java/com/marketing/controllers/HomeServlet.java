package com.marketing.controllers;

import com.marketing.bean.ProductBean;
import com.marketing.entity.Product;

import javax.ejb.EJB;
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

    @EJB
    private ProductBean productBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Product product = productBean.getProductOfTheDay();
        HashMap<String, Object> vars = new HashMap<>();
        vars.put("username", session.getAttribute("username"));
        vars.put("isAdmin", session.getAttribute("isAdmin"));
        if (product != null) {
            vars.put("imageUrl", product.getImage());
            vars.put("productName", product.getName());
            vars.put("productDescription", product.getDescription());
            vars.put("productId", product.getId());
            vars.put("reviews", product.getReviews());
        } else {
            // TODO handle case when product is not found
        }
        renderAndServeWithVariables(request, response, vars);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
