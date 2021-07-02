package com.marketing.controllers;

import com.marketing.bean.AdminBean;
import com.marketing.utils.Servlets;
import com.marketing.utils.UrlBuilder;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteQuestionnaireServlet", value = "/DeleteQuestionnaireServlet")
public class DeleteQuestionnaireServlet extends RendererServlet {

    @EJB
    private AdminBean adminBean;

    public DeleteQuestionnaireServlet() {
        super("/WEB-INF/inspectQuest.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String surveyId = request.getParameter("surveyId");
        adminBean.deleteQuestionnaire(Integer.valueOf(surveyId));
        response.sendRedirect(UrlBuilder.getUrl(request, Servlets.INSPECTION));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

}
