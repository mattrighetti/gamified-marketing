package com.marketing.bean;

import com.marketing.entity.*;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AnswerBean extends AbstractFacade{

    public AnswerBean() {  super(Answer.class); }

    public void createAnswer(Question question,String answer, User user){
        Answer newAnswer = new Answer();
        newAnswer.setUserId(user);
        newAnswer.setQuestionId(question);
        if (question.getOptionGroup() == null){
            //if the answer is a text
            newAnswer.setAnswerText(answer);
        } else {
            //if the answer is a multiple choice
            newAnswer.setAnswerText(null);
            OptionChoice optionChoice = getEntityManager().find(OptionChoice.class,Integer.valueOf(answer));
            if(optionChoice != null) {
                newAnswer.setOptionChoice(optionChoice);
            } else {
                System.out.println("Error: the multiple choice does not match any option choice");
            }
        }
        create(newAnswer);
    }

}
