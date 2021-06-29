package com.marketing.bean;

import com.marketing.entity.Question;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class QuestionBean extends AbstractFacade<Question> {

    public QuestionBean() {
        super(Question.class);
    }

    public Question getQuestionByName(String name) {
        List<Question> questions = getEntityManager()
                .createNamedQuery("Question.questionByName", Question.class)
                .setParameter("name", name)
                .getResultList();
        if (questions.size() > 0)
            return (Question) questions.get(0);
        return null;

    }
}
