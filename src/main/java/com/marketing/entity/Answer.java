package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "answer", schema = "gamified_marketing")
public class Answer {
    private long id;
    private long userId;
    private long questionOptionId;
    private Object answerNumeric;
    private String answerText;
    private Byte answerYn;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "question_option_id", nullable = false)
    public long getQuestionOptionId() {
        return questionOptionId;
    }

    public void setQuestionOptionId(long questionOptionId) {
        this.questionOptionId = questionOptionId;
    }

    @Column(name = "answer_numeric", nullable = true)
    public Object getAnswerNumeric() {
        return answerNumeric;
    }

    public void setAnswerNumeric(Object answerNumeric) {
        this.answerNumeric = answerNumeric;
    }

    @Basic
    @Column(name = "answer_text", nullable = true, length = 255)
    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    @Basic
    @Column(name = "answer_yn", nullable = true)
    public Byte getAnswerYn() {
        return answerYn;
    }

    public void setAnswerYn(Byte answerYn) {
        this.answerYn = answerYn;
    }
}
