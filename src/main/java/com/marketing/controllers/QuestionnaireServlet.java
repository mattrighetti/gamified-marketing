package com.marketing.controllers;

import com.marketing.bean.QuestionnaireBean;
import com.marketing.commons.RedirectAfterCompletion;
import com.marketing.utils.Servlets;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "QuestionnaireServlet", value = "/QuestionnaireServlet")
public class QuestionnaireServlet extends RendererServlet {

    @EJB
    private QuestionnaireBean questionnaireBean;

    public QuestionnaireServlet() {
        super("/WEB-INF/survey.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setInitialData(req);
        handleGetRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handlePostRequest(req, resp);
    }

    private void handlePostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        switch (Objects.requireNonNull(getPostAction(request))) {
            case SUBMIT:
                // TODO save answers to database
                break;
            case CANCEL:
                questionnaireBean.cancelQuestionnaire();
                RedirectAfterCompletion redirect = new RedirectAfterCompletion(Servlets.HOME, null);
                redirect.run(request, response);
                break;
            case NEXT:
                questionnaireBean.nextQuestion(request.getParameterMap());
                renderAndServeWithVariables(request, response, questionnaireBean.getVarsForCurrentSection());
                break;
            case PREVIOUS:
                questionnaireBean.previousQuestion(request.getParameterMap());
                renderAndServeWithVariables(request, response, questionnaireBean.getVarsForCurrentSection());
                break;
            default:
                break;
        }
    }

    private void handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        renderAndServeWithVariables(request, response, questionnaireBean.getVarsForCurrentSection());
    }

    private void setInitialData(HttpServletRequest request) {
        if (!questionnaireBean.isDataSet()) {
            int productId = Integer.parseInt(request.getParameter("product"));
            int surveyId = Integer.parseInt(request.getParameter("survey"));
            questionnaireBean.setInitialData(productId, surveyId);
        }
    }

    private PostAction getPostAction(HttpServletRequest request) {
        if (request.getParameter("submit") != null) {
            return PostAction.SUBMIT;
        } else if (request.getParameter("cancel") != null) {
            return PostAction.CANCEL;
        } else if (request.getParameter("next") != null) {
            return PostAction.NEXT;
        } else if (request.getParameter("previous") != null) {
            return PostAction.PREVIOUS;
        } else {
            return null;
        }
    }

    private enum PostAction {
        SUBMIT,
        NEXT,
        PREVIOUS,
        CANCEL
    }
}
