package com.marketing.controllers;

import com.marketing.bean.QuestionnaireBean;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="InspectQuestionnaireServlet", value="/InspectQuestionnaireServlet")
public class InspectQuestionnaireServlet extends RendererServlet{

    @EJB
    private QuestionnaireBean questionnaireBean;

    public InspectQuestionnaireServlet(String htmlPage) {
        super("/WEB-INF/inspectQuest.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

    }


}
