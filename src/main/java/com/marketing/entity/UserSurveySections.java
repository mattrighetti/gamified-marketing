package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_survey_sections", schema = "gamified_marketing")
public class UserSurveySections {
    private long id;
    private Object userId;
    private Object surveySectionId;
    private Object completedOn;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    @Column(name = "survey_section_id", nullable = false)
    public Object getSurveySectionId() {
        return surveySectionId;
    }

    public void setSurveySectionId(Object surveySectionId) {
        this.surveySectionId = surveySectionId;
    }

    @Column(name = "completed_on", nullable = false)
    public Object getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(Object completedOn) {
        this.completedOn = completedOn;
    }
}
