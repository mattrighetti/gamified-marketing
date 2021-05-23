package com.marketing.entity;

import javax.persistence.*;

@Entity
public class Question {
    private Object id;
    private Object surveySectionId;
    private Object inputTypeId;
    private String name;
    private String subtext;
    private Byte required;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Object getId() {
        return id;
    }

    public void setId(Object id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != null ? !id.equals(question.id) : question.id != null) return false;
        if (surveySectionId != null ? !surveySectionId.equals(question.surveySectionId) : question.surveySectionId != null)
            return false;
        if (inputTypeId != null ? !inputTypeId.equals(question.inputTypeId) : question.inputTypeId != null)
            return false;
        if (name != null ? !name.equals(question.name) : question.name != null) return false;
        if (subtext != null ? !subtext.equals(question.subtext) : question.subtext != null) return false;
        if (required != null ? !required.equals(question.required) : question.required != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (surveySectionId != null ? surveySectionId.hashCode() : 0);
        result = 31 * result + (inputTypeId != null ? inputTypeId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (subtext != null ? subtext.hashCode() : 0);
        result = 31 * result + (required != null ? required.hashCode() : 0);
        return result;
    }
}
