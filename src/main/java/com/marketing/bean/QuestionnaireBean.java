package com.marketing.bean;

import com.marketing.entity.*;
import com.marketing.utils.Tuple;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import java.util.*;

@Stateful(mappedName = "QuestionnaireBean")
public class QuestionnaireBean extends AbstractFacade<SurveyHeader> {
    private int productId;
    private String username;
    private boolean isDataSet = false;
    private int currentSection = 1;
    private final Map<String, Map<String, String>> temporaryAnswers;

    @EJB
    private UserBean userBean;

    @EJB
    private ProductBean productBean;

    @EJB
    private QuestionBean questionBean;

    @EJB
    private AnswerBean answerBean;

    private SurveyHeader survey;


    public QuestionnaireBean() {
        super(SurveyHeader.class);
        temporaryAnswers = new HashMap<>();
    }

    public void getProductQuestionnaire() {
        this.survey = (SurveyHeader) getEntityManager()
                .createNamedQuery("SurveyHeader.selectSurveyHeaderWhereProduct")
                .setParameter("productId", productBean.getProduct(this.productId))
                .getResultList()
                .get(0);
        edit(survey);
    }

    public User getCurrentUser() {
        return userBean.getUser(this.username);
    }

    public void setInitialData(int productId, String username) {
        this.setProductId(productId);
        this.setUsername(username);
        this.isDataSet = true;
        getProductQuestionnaire();
        survey.setCompiledQuestUsers(new LinkedList<>());
    }

    public boolean isDataSet() {
        return isDataSet;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public HashMap<String, Object> getVarsForCurrentSection() {
        HashMap<String, Object> vars = new HashMap<>();
        vars.put("header", getCurrentSurveySection().getName());
        vars.put("subheader", getCurrentSurveySection().getSubheading());
        vars.put("questions", getCurrentSurveySection().getQuestions());
        if (temporaryAnswers.containsKey(Integer.toString(currentSection))) {
            vars.put("temporaryAnswers", temporaryAnswers.get(Integer.toString(currentSection)));
        }
        vars.put("sectionState", new Tuple<>(this.survey.getSurveySections().size(), currentSection));
        return vars;
    }

    public void nextQuestion(Map<String, String[]> answers) {
        this.storeAnswers(answers);
        this.currentSection += 1;
    }

    public void previousQuestion(Map<String, String[]> answers) {
        this.storeAnswers(answers);
        this.currentSection -= 1;
    }

    @Remove
    public void cancelQuestionnaire() {
        // The persistence context is terminated when the client calls this method
        survey.addCompiledQuestUsers(this.getCurrentUser());
        getEntityManager().merge(survey);
        getEntityManager().flush();
        isDataSet = false;
    }

    @Remove
    public void submitQuestionnaire(Map<String, String[]> answers) {
        this.storeAnswers(answers);
        //add user in the list of who has compiled the questionnaire
        survey.addCompiledQuestUsers(this.getCurrentUser());
        getEntityManager().merge(survey);
        getEntityManager().flush();

        Map<Integer, SurveySection> sections = survey.getSurveySections();
        for (Integer sectionKey : sections.keySet()) {
            for (Question question : sections.get(sectionKey).getQuestions()) {
                if (temporaryAnswers.get(sectionKey.toString()) != null && temporaryAnswers.get(sectionKey.toString()).get(Long.toString(question.getId())) != null) {
                    answerBean.createAnswer(survey, question, temporaryAnswers.get(sectionKey.toString()).get(Long.toString(question.getId())), getCurrentUser());
                }
            }
        }
        this.edit(survey);
        getEntityManager().flush();
        isDataSet=false;
    }

    public boolean hasPreviousSection() {
        return survey.getSurveySections().size() - currentSection >= 0;
    }

    public boolean isLastSection() {
        return survey.getSurveySections().size() - currentSection == 0;
    }

    public SurveySection getCurrentSurveySection() {
        return this.survey.getSurveySections().get(currentSection);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    void storeAnswers(Map<String, String[]> answers) {
        Map<String, String> newParams = new HashMap<>();
        for (String key : answers.keySet()) {
            newParams.put(key, answers.get(key)[0]);
        }
        this.temporaryAnswers.put(Integer.toString(currentSection), newParams);
    }

}