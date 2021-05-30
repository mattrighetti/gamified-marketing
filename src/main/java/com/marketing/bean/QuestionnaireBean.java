package com.marketing.bean;

import com.marketing.entity.SurveyHeader;
import com.marketing.entity.SurveySection;
import com.marketing.utils.Tuple;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.HashMap;

@Stateful
public class QuestionnaireBean extends AbstractFacade<SurveyHeader> {
    private int productId = 0;
    private int surveyId = 0;
    private boolean isDataSet = false;
    private int currentSection = 1;

    @EJB
    private ProductBean productBean;
    private SurveyHeader survey;

    public QuestionnaireBean() {
        super(SurveyHeader.class);
    }

    public void getProductQuestionnaire() {
        this.survey = (SurveyHeader) getEntityManager()
                .createNamedQuery("SurveyHeader.selectSurveyHeaderWhereProduct")
                .setParameter("productId", productBean.getProduct(productId))
                .setParameter("surveyId",  this.surveyId)
                .getResultList()
                .get(0);
    }

    public void setInitialData(int productId, int surveyId) {
        this.setProductId(productId);
        this.setSurveyId(surveyId);
        this.isDataSet = true;
        getProductQuestionnaire();
    }

    public boolean isDataSet() {
        return isDataSet;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public HashMap<String, Object> getVarsForCurrentSection() {
        HashMap<String, Object> vars = new HashMap<>();
        vars.put("header", getCurrentSurveySection().getName());
        vars.put("subheader", getCurrentSurveySection().getSubheading());
        vars.put("questions", getCurrentSurveySection().getQuestions());
        vars.put("sectionState", new Tuple<>(this.survey.getSurveySections().size(), currentSection));
        return vars;
    }

    public void nextQuestion() {
        this.currentSection += 1;
    }

    public void previousQuestion() {
        this.currentSection -= 1;
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
}