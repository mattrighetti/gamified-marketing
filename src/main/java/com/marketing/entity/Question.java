package com.marketing.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="question")
public class Question  implements Serializable {

    @Id
    private int id;

    @ManyToMany(fetch=FetchType.EAGER)
    private List<Product> product;

    private String question_text;
    private Boolean mandatory;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
