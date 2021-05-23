package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "option_group", schema = "gamified_marketing", catalog = "")
public class OptionGroup {
    private Object id;
    private String optionGroupName;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Basic
    @Column(name = "option_group_name", nullable = true, length = 50)
    public String getOptionGroupName() {
        return optionGroupName;
    }

    public void setOptionGroupName(String optionGroupName) {
        this.optionGroupName = optionGroupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptionGroup that = (OptionGroup) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (optionGroupName != null ? !optionGroupName.equals(that.optionGroupName) : that.optionGroupName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (optionGroupName != null ? optionGroupName.hashCode() : 0);
        return result;
    }
}
