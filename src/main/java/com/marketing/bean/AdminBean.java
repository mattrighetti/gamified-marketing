package com.marketing.bean;

import com.marketing.entity.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class AdminBean extends AbstractFacade<SurveyHeader> {

    @EJB
    private QuestionBean questionBean;

    @EJB
    private SurveySectionBean surveySectionBean;

    @EJB
    private UnownedEntitiesBean unownedEntitiesBean;

    public AdminBean() {
        super(SurveyHeader.class);
    }

    public void createQuestionnaire(Product product, Map<String, String> questions, boolean withStats) {
        SurveyHeader surveyHeader = createSurveyHeader(product, "Quality Control", "Answer to all the required fields to gain points");
        List<Question> questionList = createQuestionsList(questions);
        SurveySection surveySection = createSection("Marketing section", "Quality", questionList);

        Map<Integer, SurveySection> surveySectionMap = new HashMap<>();
        surveySectionMap.put(1, surveySection);

        surveyHeader.setSurveySections(surveySectionMap);
        surveyHeader.setAnswers(new LinkedList<>());

        create(surveyHeader);
        getEntityManager().flush();
        if (withStats) {
            surveySectionMap.put(2, surveySectionBean.getSurveyById(2));
            surveyHeader.setSurveySections(surveySectionMap);
            edit(surveyHeader);
            getEntityManager().flush();
        }
    }

    public void deleteQuestionnaire(int id) {
        SurveyHeader surveyHeader = (SurveyHeader) find(id);
        remove(surveyHeader);
        getEntityManager().flush();
        unownedEntitiesBean.removeUnownedEntries();
    }

    public List<SurveyHeader> getAllQuestionnaires() {
        List<SurveyHeader> questionnaires = getEntityManager()
                .createNamedQuery("SurveyHeader.allPastSurveysOrderedByDate", SurveyHeader.class)
                .setParameter("today", new Date().getTime() / 1000L)
                .getResultList();
        if (questionnaires != null)
            return questionnaires;
        else
            return new ArrayList<>();
    }

    public Map<String, List<User>> getSubmittedCanceledUsers(SurveyHeader surveyHeader) {
        List<User> users = surveyHeader.getCompiledQuestUsers();
        List<User> submittedUsers = new LinkedList<>();
        List<User> canceledUsers = new LinkedList<>();

        for (User user : users) {
            if (getEntityManager()
                    .createNamedQuery("Answer.getAnswerBySurveyHeader")
                    .setParameter("user", user)
                    .setParameter("surveyHeader", surveyHeader)
                    .getResultList()
                    .size() > 0)
                submittedUsers.add(user);
            else
                canceledUsers.add(user);
        }
        Map<String, List<User>> compiledUsers = new HashMap<>();
        compiledUsers.put("submitted", submittedUsers);
        compiledUsers.put("canceled", canceledUsers);
        return compiledUsers;
    }

    private List<Question> createQuestionsList(Map<String, String> questions) {
        List<Question> list = new ArrayList<>();
        for (String key : questions.keySet()) {
            //check if the question already exists
            //TODO change in lower case without spaces
            Question question = questionBean.getQuestionByName(questions.get(key));
            //if the question doesn't exist, create a new one
            if (question == null) {
                question = new Question();
                question.setName(questions.get(key));
                question.setOptionGroup(null);
                question.setRequired(true);
                question.setInputType("text");
            }
            list.add(question);
        }
        return list;
    }

    private SurveySection createSection(String title, String name, List<Question> questions) {
        SurveySection surveySection = new SurveySection();
        surveySection.setTitle(title);
        surveySection.setName(name);
        surveySection.setQuestions(questions);
        return surveySection;
    }

    private SurveyHeader createSurveyHeader(Product product, String name, String instructions) {
        SurveyHeader surveyHeader = new SurveyHeader();
        surveyHeader.setProductId(product);
        surveyHeader.setName(name);
        surveyHeader.setInstructions(instructions);
        surveyHeader.setCompiledQuestUsers(new LinkedList<>());
        return surveyHeader;
    }
}
