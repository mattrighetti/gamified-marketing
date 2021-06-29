package com.marketing.controllers;

import com.marketing.bean.AdminBean;
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

@WebServlet(name = "InspectQuestionnaireServlet", value = "/InspectQuestionnaireServlet")
public class InspectQuestionnaireServlet extends RendererServlet {

    @EJB
    private AdminBean adminBean;

    public InspectQuestionnaireServlet() {
        super("/WEB-INF/inspectQuest.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Map<Long, Map<String, List<User>>> usersMap = new HashMap<>();
        List<SurveyHeader> questionnaires = adminBean.getAllQuestionnaires();
        for (SurveyHeader q : questionnaires) {
            usersMap.put(q.getId(), adminBean.getSubmittedCanceledUsers(q));
        }
        HashMap<String, Object> vars = new HashMap<>();
        vars.put("username", session.getAttribute("username"));
        vars.put("questionnaires", questionnaires);
        vars.put("usersMap", usersMap);
        renderAndServeWithVariables(request, response, vars);
    }


}
