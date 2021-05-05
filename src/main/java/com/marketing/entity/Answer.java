package com.marketing.entity;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "answer", schema="gamified_marketing")
public class Answer implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Question question;

    @Id
    @ManyToOne(fetch=FetchType.EAGER)
    private User user;

    private String text;

    public Answer() {

    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
