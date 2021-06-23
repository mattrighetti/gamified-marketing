package com.marketing.bean;

import com.marketing.entity.*;
import com.marketing.utils.Tuple;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import java.util.*;

@Stateful
public class QuestionnaireBean extends AbstractFacade<SurveyHeader> {
    private int productId = 0;
    private int surveyId = 0;
    private boolean isDataSet = false;
    private int currentSection = 1;
    private Map<String,Map<String,String>> temporaryAnswers;

    @EJB
    private ProductBean productBean;
    @EJB
    private QuestionBean questionBean;
    private SurveyHeader survey;


    public QuestionnaireBean() {
        super(SurveyHeader.class);
        temporaryAnswers = new HashMap<>();
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
        if (temporaryAnswers.containsKey(Integer.toString(currentSection))){
            vars.put("temporaryAnswers", temporaryAnswers.get(Integer.toString(currentSection)));
        }
        vars.put("sectionState", new Tuple<>(this.survey.getSurveySections().size(), currentSection));
        return vars;
    }

    public void nextQuestion(Map<String, String[]> answers) {
        Map<String,String> newParams = new HashMap<>();
        for (String key: answers.keySet() ) {
            newParams.put(key,new String(answers.get(key)[0]));
        }
        this.temporaryAnswers.put(Integer.toString(currentSection), newParams);
        this.currentSection += 1;
    }

    public void previousQuestion(Map<String, String[]> answers) {
        Map<String,String> newParams = new HashMap<>();
        for (String key: answers.keySet() ) {
            newParams.put(key,new String(answers.get(key)[0]));
        }
        this.temporaryAnswers.put(Integer.toString(currentSection), newParams);
        this.currentSection -= 1;
    }

    @Remove
    public void cancelQuestionnaire(){
        // The persistence context is terminated when the client calls this method
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

    public void createQuestionnaire(Product product, Map<String,String> questions){
        SurveyHeader surveyHeader = new SurveyHeader();
        surveyHeader.setProductId(product);
        surveyHeader.setSurveySections(new HashMap<>());

        List<Question> list = new ArrayList<>();
        for (String key: questions.keySet() ) {

            //check if the question already exists
            Question question = questionBean.getQuestionByName(questions.get(key));
            //if the question doesn't exist, create a new one
            if(question == null){
                question = new Question();
                question.setName(questions.get(key));
                question.setOptionGroup(null);
                question.setRequired(true);
                getEntityManager().persist(question);
            }
            list.add(question);
        }
        SurveySection surveySection = new SurveySection();
        surveySection.setTitle("Marketing section");
        surveySection.setName("Quality");
        surveySection.setQuestions(list);
        getEntityManager().persist(surveySection);

        //Add the marketing section (marked with 1)
        surveyHeader.addSurveySection(1,surveySection);
        //Add the statistical Section: the section with id 2 is assumed to be the default "statistical section"
        SurveySection statSection = getEntityManager().find(SurveySection.class,2);
        if(statSection != null) {
            //surveyHeader.addSurveySection(2, statSection);
        }
        create(surveyHeader);
        getEntityManager().flush();
    }

    public List<SurveyHeader> getAllQuestionnaires(){
        List<SurveyHeader> questionnaires = getEntityManager().createNamedQuery("SurveyHeader.allPastSurveysOrderedByDate").setParameter("today",(long) new Date().getTime()/1000).getResultList();
        if (questionnaires != null) return questionnaires;
        else return new ArrayList<>();

    }

    public void deleteQuestionnaire(int id){
        remove(find(id));
    }

}