package com.marketing.controllers;

import com.marketing.bean.QuestionnaireBean;
import com.marketing.entity.SurveyHeader;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "QuestionnaireServlet", value = "/QuestionnaireServlet")
public class QuestionnaireServlet extends RendererServlet {

    @EJB
    private QuestionnaireBean questionnaireBean;

    public QuestionnaireServlet() {
        super("/WEB-INF/survey.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("product"));
        int surveyId = Integer.parseInt(req.getParameter("survey"));
        SurveyHeader surveyHeader = questionnaireBean.getProductQuestionnaire(productId);
        renderAndServeWithVariables(req, resp, new HashMap<String, Object>() {{
            put("header", surveyHeader.getName());
            put("subheader", surveyHeader.getInstructions());
        }});
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(404, "Cannot find POST endpoint to " + getServletName());
    }
}
