package com.marketing.controllers;

import com.marketing.bean.QuestionnaireBean;
import com.marketing.entity.SurveyHeader;
import com.marketing.utils.Servlets;
import com.marketing.utils.UrlBuilder;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name="DeleteQuestionnaireServlet", value="/DeleteQuestionnaireServlet")
public class DeleteQuestionnaireServlet extends RendererServlet{

    @EJB
    private QuestionnaireBean questionnaireBean;

    public DeleteQuestionnaireServlet() {
        super("/WEB-INF/inspectQuest.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String surveyId = request.getParameter("surveyId");
        questionnaireBean.deleteQuestionnaire(Integer.valueOf(surveyId));
        response.sendRedirect(UrlBuilder.getUrl(request, Servlets.INSPECTION));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doPost(request,response);
    }

}
