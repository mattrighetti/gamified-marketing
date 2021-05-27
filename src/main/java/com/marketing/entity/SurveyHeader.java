package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "survey_header", schema = "gamified_marketing")
public class SurveyHeader {
    private long id;
    private Product productId;
    private String name;
    private String instructions;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Column(name = "product_id", nullable = false)
    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
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
}
