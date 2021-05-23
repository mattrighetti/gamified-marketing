package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "question_option", schema = "gamified_marketing", catalog = "")
public class QuestionOption {
    private Object id;
    private Object questionId;
    private Object optionChoiceId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Column(name = "question_id", nullable = false)
    public Object getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Object questionId) {
        this.questionId = questionId;
    }

    @Column(name = "option_choice_id", nullable = false)
    public Object getOptionChoiceId() {
        return optionChoiceId;
    }

    public void setOptionChoiceId(Object optionChoiceId) {
        this.optionChoiceId = optionChoiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionOption that = (QuestionOption) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (questionId != null ? !questionId.equals(that.questionId) : that.questionId != null) return false;
        if (optionChoiceId != null ? !optionChoiceId.equals(that.optionChoiceId) : that.optionChoiceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (questionId != null ? questionId.hashCode() : 0);
        result = 31 * result + (optionChoiceId != null ? optionChoiceId.hashCode() : 0);
        return result;
    }
}
