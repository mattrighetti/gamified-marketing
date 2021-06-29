package com.marketing.controllers;

import com.marketing.bean.AnswerBean;
import com.marketing.entity.Answer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AnswerServlet", value = "/AnswerServlet")
public class AnswerServlet extends RendererServlet {

    @EJB
    private AnswerBean answerBean;

    public AnswerServlet() {
        super("/WEB-INF/answer.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> vars = new HashMap<>();
        String username = request.getParameter("user");
        int surveyHeaderId = Integer.parseInt(request.getParameter("survey"));
        List<Answer> answers = answerBean.getAnswersByQuestionnaire(username, surveyHeaderId);
        Map<String, String> questAnswer = new HashMap<>();
        for (Answer answer : answers) {
            String answerText;
            if (answer.getOptionChoice() == null)
                answerText = answer.getAnswerText();
            else {
                answerText = answer.getOptionChoice().getOptionChoiceName();
            }
            questAnswer.put(answer.getQuestionId().getName(), answerText);
        }
        vars.put("questionsAnswers", questAnswer);
        renderAndServeWithVariables(request, response, vars);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
