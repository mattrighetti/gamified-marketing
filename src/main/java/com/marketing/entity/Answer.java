package com.marketing.entity;

import javax.persistence.*;

@Entity
public class Answer {
    private Object id;
    private Object userId;
    private Object questionOptionId;
    private Object answerNumeric;
    private String answerText;
    private Byte answerYn;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    @Column(name = "question_option_id", nullable = false)
    public Object getQuestionOptionId() {
        return questionOptionId;
    }

    public void setQuestionOptionId(Object questionOptionId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (id != null ? !id.equals(answer.id) : answer.id != null) return false;
        if (userId != null ? !userId.equals(answer.userId) : answer.userId != null) return false;
        if (questionOptionId != null ? !questionOptionId.equals(answer.questionOptionId) : answer.questionOptionId != null)
            return false;
        if (answerNumeric != null ? !answerNumeric.equals(answer.answerNumeric) : answer.answerNumeric != null)
            return false;
        if (answerText != null ? !answerText.equals(answer.answerText) : answer.answerText != null) return false;
        if (answerYn != null ? !answerYn.equals(answer.answerYn) : answer.answerYn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (questionOptionId != null ? questionOptionId.hashCode() : 0);
        result = 31 * result + (answerNumeric != null ? answerNumeric.hashCode() : 0);
        result = 31 * result + (answerText != null ? answerText.hashCode() : 0);
        result = 31 * result + (answerYn != null ? answerYn.hashCode() : 0);
        return result;
    }
}
