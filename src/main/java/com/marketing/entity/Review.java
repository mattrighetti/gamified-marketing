package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "review", schema = "gamified_marketing")
public class Review {
    private long id;
    private Product productId;
    private User userId;
    private String reviewText;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne()
    @JoinColumn(name = "product_id")
    @Column(name = "product_id", nullable = false)
    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    @ManyToOne
    @Column(name = "user_id", nullable = false)
    @JoinColumn(name = "user_id")
    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "review_text")
    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

}
