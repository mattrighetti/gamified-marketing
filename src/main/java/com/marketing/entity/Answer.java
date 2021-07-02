package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "answer", schema = "gamified_marketing")
@NamedQuery(name = "Answer.getAnswerBySurveyHeader", query =
        "select a from Answer a where a.surveyHeaderId =: surveyHeader and a.userId =:user order by a.questionId.id")
public class Answer {
    private long id;
    private User userId;
    private Question questionId;
    private SurveyHeader surveyHeaderId;
    private OptionChoice optionChoice;
    private String answerText;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @Column(name = "user_id", nullable = false)
    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @ManyToOne
    @JoinColumn(name = "question_id")
    @Column(name = "question_id", nullable = false)
    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "answer_text", nullable = true, length = 255)
    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    @ManyToOne
    @JoinColumn(name = "option_choice_id")
    public OptionChoice getOptionChoice() {
        return optionChoice;
    }

    public void setOptionChoice(OptionChoice optionChoice) {
        this.optionChoice = optionChoice;
    }

    @ManyToOne
    @JoinColumn(name = "survey_header_id")
    public SurveyHeader getSurveyHeaderId() {
        return surveyHeaderId;
    }

    public void setSurveyHeaderId(SurveyHeader surveyHeaderId) {
        this.surveyHeaderId = surveyHeaderId;
    }
}
