package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "survey_header", schema = "gamified_marketing", catalog = "")
public class SurveyHeader {
    private Object id;
    private Object productId;
    private String name;
    private String instructions;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Column(name = "product_id", nullable = false)
    public Object getProductId() {
        return productId;
    }

    public void setProductId(Object productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "instructions", nullable = true, length = 255)
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveyHeader that = (SurveyHeader) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (instructions != null ? !instructions.equals(that.instructions) : that.instructions != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (instructions != null ? instructions.hashCode() : 0);
        return result;
    }
}
