package com.marketing.entity;

import javax.persistence.*;

@Entity
@IdClass(QuestionPK.class)
public class Question {
    private Object id;
    private Object product;
    private String questionText;
    private byte mandatory;
    private String answer;

    @Id
    @Column(name = "id")
    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Id
    @Column(name = "product")
    public Object getProduct() {
        return product;
    }

    public void setProduct(Object product) {
        this.product = product;
    }

    @Basic
    @Column(name = "question_text")
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @Basic
    @Column(name = "mandatory")
    public byte getMandatory() {
        return mandatory;
    }

    public void setMandatory(byte mandatory) {
        this.mandatory = mandatory;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (mandatory != question.mandatory) return false;
        if (id != null ? !id.equals(question.id) : question.id != null) return false;
        if (product != null ? !product.equals(question.product) : question.product != null) return false;
        if (questionText != null ? !questionText.equals(question.questionText) : question.questionText != null)
            return false;
        if (answer != null ? !answer.equals(question.answer) : question.answer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (questionText != null ? questionText.hashCode() : 0);
        result = 31 * result + (int) mandatory;
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }
}
