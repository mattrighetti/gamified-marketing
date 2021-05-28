package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "question", schema = "gamified_marketing")
public class Question {
    private long id;
    private SurveySection surveySectionId;
    private InputType inputTypeId;
    private String name;
    private String subtext;
    private Byte required;
    private OptionGroup optionGroups;

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
    @Column(name = "survey_section_id", nullable = false)
    public SurveySection getSurveySectionId() {
        return surveySectionId;
    }

    public void setSurveySectionId(SurveySection surveySectionId) {
        this.surveySectionId = surveySectionId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "input_type_id")
    @Column(name = "input_type_id", nullable = false)
    public InputType getInputTypeId() {
        return inputTypeId;
    }

    public void setInputTypeId(InputType inputTypeId) {
        this.inputTypeId = inputTypeId;
    }

    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "subtext")
    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    @Column(name = "required", nullable = false)
    public Byte getRequired() {
        return required;
    }

    public void setRequired(Byte required) {
        this.required = required;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "option_group")
    @Column(name = "option_group")
    public OptionGroup getOptionGroups() {
        return optionGroups;
    }

    public void setOptionGroups(OptionGroup optionGroups) {
        this.optionGroups = optionGroups;
    }
}
