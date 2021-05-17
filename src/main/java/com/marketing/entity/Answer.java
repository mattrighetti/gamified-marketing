package com.marketing.entity;

import javax.persistence.*;

@Entity
@IdClass(AnswerPK.class)
@Table(name = "answer")
public class Answer {
    private String user;
    private Object question;
    private String text;

    @Id
    @Column(name = "user", nullable = false, length = 50)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Id
    @Column(name = "question", nullable = false)
    public Object getQuestion() {
        return question;
    }

    public void setQuestion(Object question) {
        this.question = question;
    }

    @Basic
    @Column(name = "text", nullable = false, length = 100)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (user != null ? !user.equals(answer.user) : answer.user != null) return false;
        if (question != null ? !question.equals(answer.question) : answer.question != null) return false;
        if (text != null ? !text.equals(answer.text) : answer.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
