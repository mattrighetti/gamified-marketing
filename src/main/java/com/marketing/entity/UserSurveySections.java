package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_survey_sections", schema = "gamified_marketing", catalog = "")
public class UserSurveySections {
    private Object id;
    private Object userId;
    private Object surveySectionId;
    private Object completedOn;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Object getId() {
        return id;
    }

    public void setId(Object id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSurveySections that = (UserSurveySections) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (surveySectionId != null ? !surveySectionId.equals(that.surveySectionId) : that.surveySectionId != null)
            return false;
        if (completedOn != null ? !completedOn.equals(that.completedOn) : that.completedOn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (surveySectionId != null ? surveySectionId.hashCode() : 0);
        result = 31 * result + (completedOn != null ? completedOn.hashCode() : 0);
        return result;
    }
}
