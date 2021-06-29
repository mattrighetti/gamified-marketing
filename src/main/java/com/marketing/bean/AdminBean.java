package com.marketing.bean;

import com.marketing.entity.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class AdminBean extends AbstractFacade{

    @EJB
    private QuestionBean questionBean;

    public AdminBean() {
        super(SurveyHeader.class);
    }

    public void createQuestionnaire(Product product, Map<String,String> questions){
        SurveyHeader surveyHeader = new SurveyHeader();
        surveyHeader.setProductId(product);
        surveyHeader.setSurveySections(new HashMap<>());
        surveyHeader.setAnswers(new LinkedList<>());

        List<Question> list = new ArrayList<>();
        for (String key: questions.keySet() ) {

            //check if the question already exists
            //TODO change in lower case without spaces
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

    public void deleteQuestionnaire(int id){
        SurveyHeader surveyHeader = (SurveyHeader) find(id);
        remove(surveyHeader);
    }

    public List<SurveyHeader> getAllQuestionnaires(){
        List<SurveyHeader> questionnaires = getEntityManager().createNamedQuery("SurveyHeader.allPastSurveysOrderedByDate").setParameter("today",(long) new Date().getTime()/1000).getResultList();
        if (questionnaires != null) return questionnaires;
        else return new ArrayList<>();
    }

    public Map<String, List<User>> getSubmittedCanceledUsers(SurveyHeader surveyHeader){
        List<User> users = surveyHeader.getCompiledQuestUsers();
        List<User> submittedUsers = new LinkedList<>();
        List<User> canceledUsers = new LinkedList<>();

        for (User user : users) {
            if(getEntityManager().createNamedQuery("Answer.getAnswerBySurveyHeader").setParameter("user", user).setParameter("surveyHeader", surveyHeader).getResultList().size() > 0)
                submittedUsers.add(user);
            else
                canceledUsers.add(user);
        }
        Map<String,List<User>> compiledUsers = new HashMap<>();
        compiledUsers.put("submitted", submittedUsers);
        compiledUsers.put("canceled", canceledUsers);
        return compiledUsers;
    }

}
