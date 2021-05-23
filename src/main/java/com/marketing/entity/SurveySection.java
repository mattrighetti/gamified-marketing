package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "survey_section", schema = "gamified_marketing", catalog = "")
public class SurveySection {
    private Object id;
    private Object surveyHeaderId;
    private String name;
    private String title;
    private String subheading;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Column(name = "survey_header_id", nullable = false)
    public Object getSurveyHeaderId() {
        return surveyHeaderId;
    }

    public void setSurveyHeaderId(Object surveyHeaderId) {
        this.surveyHeaderId = surveyHeaderId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "subheading", nullable = true, length = 70)
    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveySection that = (SurveySection) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (surveyHeaderId != null ? !surveyHeaderId.equals(that.surveyHeaderId) : that.surveyHeaderId != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (subheading != null ? !subheading.equals(that.subheading) : that.subheading != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (surveyHeaderId != null ? surveyHeaderId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (subheading != null ? subheading.hashCode() : 0);
        return result;
    }
}
