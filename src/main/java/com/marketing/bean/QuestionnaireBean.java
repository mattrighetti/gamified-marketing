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
    private String username;
    private boolean isDataSet = false;
    private int currentSection = 1;
    private Map<String,Map<String,String>> temporaryAnswers;

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
                .setParameter("productId", productBean.getProduct(productId))
                .setParameter("surveyId",  this.surveyId)
                .getResultList()
                .get(0);
        edit(survey);
    }

    public User getCurrentUser(){
        return (User) getEntityManager().createNamedQuery("User.selectUserWithUsername")
                .setParameter("username", this.username)
                .getResultList()
                .get(0);
    }

    public void setInitialData(int productId, int surveyId, String username) {
        this.setProductId(productId);
        this.setSurveyId(surveyId);
        this.setUsername(username);
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
        this.storeAnswers(answers);
        this.currentSection += 1;
    }

    public void previousQuestion(Map<String, String[]> answers) {
        this.storeAnswers(answers);
        this.currentSection -= 1;
    }

    @Remove
    public void cancelQuestionnaire(){
        // The persistence context is terminated when the client calls this method
        getEntityManager().persist(survey);
        getEntityManager().flush();
    }
    @Remove
    public void submitQuestionnaire(Map<String, String[]> answers){
        this.storeAnswers(answers);
        Map<Integer,SurveySection> sections = survey.getSurveySections();
        for (Integer sectionKey : sections.keySet()) {
            for (Question question : sections.get(sectionKey).getQuestions()) {
                if (temporaryAnswers.get(sectionKey.toString()) != null && temporaryAnswers.get(sectionKey.toString()).get(Long.toString(question.getId())) != null ){
                    answerBean.createAnswer(survey, question, temporaryAnswers.get(sectionKey.toString()).get(Long.toString(question.getId())), getCurrentUser());
                }
            }
        }
        this.edit(survey);
        getEntityManager().flush();
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
        surveyHeader.setAnswers(new LinkedList<>());

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
            //TODO add the statistical part
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    void storeAnswers(Map<String, String[]> answers){
        Map<String,String> newParams = new HashMap<>();
        for (String key: answers.keySet() ) {
            newParams.put(key,new String(answers.get(key)[0]));
        }
        this.temporaryAnswers.put(Integer.toString(currentSection), newParams);
    }
}