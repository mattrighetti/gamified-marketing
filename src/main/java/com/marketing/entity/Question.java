package com.marketing.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question", schema = "gamified_marketing")
@NamedQueries(
        @NamedQuery( name = "Question.questionByName", query="select q from Question q where q.name =: name")
)
public class Question {
    private long id;
    private List<SurveySection> surveySectionsId;
    private String name;
    private String subtext;
    private String inputType;
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

    @ManyToMany(mappedBy = "questions")
    public List<SurveySection> getSurveySectionsId() {
        return surveySectionsId;
    }

    public void setSurveySectionsId(List<SurveySection> surveySectionsId) {
        this.surveySectionsId = surveySectionsId;
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
    public OptionGroup getOptionGroup() {
        return optionGroup;
    }

    public void setOptionGroup(OptionGroup optionGroups) {
        this.optionGroup = optionGroups;
    }

    @Column(name = "input_type")
    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }
}
