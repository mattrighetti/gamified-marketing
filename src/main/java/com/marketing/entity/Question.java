package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "question", schema = "gamified_marketing")
public class Question {
    private long id;
    private SurveySection surveySectionId;
    private InputType inputType;
    private String name;
    private String subtext;
    private boolean required;
    private OptionGroup optionGroup;

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
    public InputType getInputType() {
        return inputType;
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
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

    @Column(name = "required", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean getRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "option_group")
    @Column(name = "option_group")
    public OptionGroup getOptionGroup() {
        return optionGroup;
    }

    public void setOptionGroup(OptionGroup optionGroups) {
        this.optionGroup = optionGroups;
    }
}
