package com.marketing.bean;

import com.marketing.entity.*;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AnswerBean extends AbstractFacade<Answer> {

    public AnswerBean() {
        super(Answer.class);
    }

    public void createAnswer(SurveyHeader surveyHeader, Question question, String answer, User user) {
        Answer newAnswer = new Answer();
        newAnswer.setUserId(user);
        newAnswer.setQuestionId(question);
        newAnswer.setSurveyHeaderId(surveyHeader);
        if (question.getOptionGroup() == null) {
            //if the answer is a text
            newAnswer.setAnswerText(answer);
        } else {
            //if the answer is a multiple choice
            newAnswer.setAnswerText(null);
            OptionChoice optionChoice = getEntityManager().find(OptionChoice.class, Integer.valueOf(answer));
            if (optionChoice != null) {
                newAnswer.setOptionChoice(optionChoice);
            } else {
                // TODO handle this scenario
                System.out.println("Error: the multiple choice does not match any option choice");
            }
        }
        create(newAnswer);
    }

    public List<Answer> getAnswersByQuestionnaire(String username, int surveyHeaderId) {
        User user = getEntityManager()
                .createNamedQuery("User.selectUserWithUsername", User.class)
                .setParameter("username", username)
                .getResultList()
                .get(0);
        SurveyHeader surveyHeader = getEntityManager().find(SurveyHeader.class, surveyHeaderId);

        List<Answer> answers = getEntityManager()
                .createNamedQuery("Answer.getAnswerBySurveyHeader", Answer.class)
                .setParameter("user", user).setParameter("surveyHeader", surveyHeader)
                .getResultList();
        if (answers != null)
            return answers;
        else
            return new ArrayList<>();
    }
}
