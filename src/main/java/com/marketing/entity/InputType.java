package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "input_type", schema = "gamified_marketing", catalog = "")
public class InputType {
    private Object id;
    private String type;

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
    @Column(name = "type", nullable = false, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InputType inputType = (InputType) o;

        if (id != null ? !id.equals(inputType.id) : inputType.id != null) return false;
        if (type != null ? !type.equals(inputType.type) : inputType.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
