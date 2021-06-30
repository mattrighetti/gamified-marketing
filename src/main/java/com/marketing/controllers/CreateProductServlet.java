package com.marketing.controllers;

import com.marketing.bean.AdminBean;
import com.marketing.bean.ProductBean;
import com.marketing.entity.Product;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CreateProductServlet", value = "/CreateProductServlet")
public class CreateProductServlet extends RendererServlet {

    @EJB
    private ProductBean productBean;
    @EJB
    private AdminBean adminBean;

    public CreateProductServlet() {
        super("/WEB-INF/createProduct.html");
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
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String dateString = request.getParameter("date");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currDate = new Date();

        //Save the questions for the questionnaire
        int id = 1;
        Map<String, String> questions = new HashMap<>();
        while (request.getParameter(Integer.toString(id)) != null) {
            questions.put(Integer.toString(id), request.getParameter(Integer.toString(id)));
            id++;
        }

        //check if the selected date is not already occupied with another product
        if (productBean.getProductByDate(date.getTime() / 1000L) == null) {
            Product product = productBean.addProduct(name, date.getTime() / 1000L, description, image);
            adminBean.createQuestionnaire(product, questions);
            sendForm(request, response, true, "confirmation", "Product " + name + " has been correctly added!");
        } else {
            sendForm(request, response, true, "alertText", "You cannot create a product of the day for the selected day,  a product is already set on that day! please select an other free date.");
        }
    }

    public void sendForm(HttpServletRequest request, HttpServletResponse response, boolean withError, String messageName, String message) throws IOException {
        renderAndServeWithVariables(request, response, new HashMap<String, Object>() {{
            put(messageName, message);
        }});
    }


}
