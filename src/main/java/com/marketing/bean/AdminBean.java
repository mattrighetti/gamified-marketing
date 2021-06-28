package com.marketing.bean;

import com.marketing.entity.SurveyHeader;
import com.marketing.entity.User;

import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class AdminBean extends AbstractFacade{

    public AdminBean() {
        super(SurveyHeader.class);
    }

    public List<SurveyHeader> getAllQuestionnaires(){
        List<SurveyHeader> questionnaires = getEntityManager().createNamedQuery("SurveyHeader.allPastSurveysOrderedByDate").setParameter("today",(long) new Date().getTime()/1000).getResultList();
        if (questionnaires != null) return questionnaires;
        else return new ArrayList<>();
    }

    public Map<String, List<User>> getSumbittedCanceledUsers(SurveyHeader surveyHeader){
        List<User> users = surveyHeader.getCompiledQuestUsers();
        List<User> submittedUsers = new LinkedList<>();
        List<User> canceledUsers = new LinkedList<>();

        for (User user : users) {
            if(getEntityManager().createNamedQuery("Answer.getAnswerBySurveyHeader").getResultList().size() > 0)
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
