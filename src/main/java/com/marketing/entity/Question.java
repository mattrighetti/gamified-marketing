package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "question", schema = "gamified_marketing")
public class Question {
    private long id;
    private Object surveySectionId;
    private Object inputTypeId;
    private String name;
    private String subtext;
    private Byte required;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "survey_section_id", nullable = false)
    public Object getSurveySectionId() {
        return surveySectionId;
    }

    public void setSurveySectionId(Object surveySectionId) {
        this.surveySectionId = surveySectionId;
    }

    @Column(name = "input_type_id", nullable = false)
    public Object getInputTypeId() {
        return inputTypeId;
    }

    public void setInputTypeId(Object inputTypeId) {
        this.inputTypeId = inputTypeId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "subtext", nullable = true, length = 255)
    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    @Basic
    @Column(name = "required", nullable = false)
    public Byte getRequired() {
        return required;
    }

    public void setRequired(Byte required) {
        this.required = required;
    }
}
