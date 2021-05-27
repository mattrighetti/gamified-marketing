package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "question_option", schema = "gamified_marketing")
public class QuestionOption {
    private long id;
    private Question questionId;
    private OptionChoice optionChoiceId;

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
    @Column(name = "question_id", nullable = false)
    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }

    @ManyToOne
    @Column(name = "option_choice_id", nullable = false)
    public OptionChoice getOptionChoiceId() {
        return optionChoiceId;
    }

    public void setOptionChoiceId(OptionChoice optionChoiceId) {
        this.optionChoiceId = optionChoiceId;
    }
}
