package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "question_option", schema = "gamified_marketing")
public class QuestionOption {
    private long id;
    private Object questionId;
    private Object optionChoiceId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
