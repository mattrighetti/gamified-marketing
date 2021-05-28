package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "answer", schema = "gamified_marketing")
public class Answer {
    private long id;
    private User userId;
    private QuestionOption questionOptionId;
    private long answerNumeric;
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

    @ManyToOne
    @Column(name = "user_id", nullable = false)
    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @ManyToOne
    @Column(name = "question_option_id", nullable = false)
    public QuestionOption getQuestionOptionId() {
        return questionOptionId;
    }

    public void setQuestionOptionId(QuestionOption questionOptionId) {
        this.questionOptionId = questionOptionId;
    }

    @Column(name = "answer_numeric", nullable = true)
    public long getAnswerNumeric() {
        return answerNumeric;
    }

    public void setAnswerNumeric(long answerNumeric) {
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
