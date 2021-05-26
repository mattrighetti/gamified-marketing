package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "survey_section", schema = "gamified_marketing")
public class SurveySection {
    private long id;
    private Object surveyHeaderId;
    private String name;
    private String title;
    private String subheading;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
