package com.marketing.controllers;

import com.marketing.bean.QuestionnaireBean;
import com.marketing.entity.SurveyHeader;
import com.marketing.entity.User;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="InspectQuestionnaireServlet", value="/InspectQuestionnaireServlet")
public class InspectQuestionnaireServlet extends RendererServlet{

    @EJB
    private QuestionnaireBean questionnaireBean;

    public InspectQuestionnaireServlet() {
        super("/WEB-INF/inspectQuest.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        List<SurveyHeader> questionnaires =  questionnaireBean.getAllQuestionnaires();

        HashMap<String, Object> vars = new HashMap<>();
        vars.put("username", session.getAttribute("username"));
        vars.put("questionnaires", questionnaires);
        renderAndServeWithVariables(request, response, vars);
    }


}
