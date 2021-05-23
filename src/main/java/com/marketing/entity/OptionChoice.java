package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "option_choice", schema = "gamified_marketing", catalog = "")
public class OptionChoice {
    private Object id;
    private Object optionGroupId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Column(name = "option_group_id", nullable = false)
    public Object getOptionGroupId() {
        return optionGroupId;
    }

    public void setOptionGroupId(Object optionGroupId) {
        this.optionGroupId = optionGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptionChoice that = (OptionChoice) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (optionGroupId != null ? !optionGroupId.equals(that.optionGroupId) : that.optionGroupId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (optionGroupId != null ? optionGroupId.hashCode() : 0);
        return result;
    }
}
