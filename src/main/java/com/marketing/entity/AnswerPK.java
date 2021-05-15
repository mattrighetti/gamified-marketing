package com.marketing.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class AnswerPK implements Serializable {
    private String user;
    private Object question;

    @Column(name = "user")
    @Id
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Column(name = "question")
    @Id
    public Object getQuestion() {
        return question;
    }

    public void setQuestion(Object question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerPK answerPK = (AnswerPK) o;

        if (user != null ? !user.equals(answerPK.user) : answerPK.user != null) return false;
        if (question != null ? !question.equals(answerPK.question) : answerPK.question != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }
}
