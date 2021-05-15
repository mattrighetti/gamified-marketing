package com.marketing.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class QuestionPK implements Serializable {
    private Object id;
    private Object product;

    @Column(name = "id")
    @Id
    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Column(name = "product")
    @Id
    public Object getProduct() {
        return product;
    }

    public void setProduct(Object product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionPK that = (QuestionPK) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }
}
