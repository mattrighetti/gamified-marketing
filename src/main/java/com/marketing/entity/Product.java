package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "product", schema = "gamified_marketing")
public class Product {
    private long id;
    private String name;
    private String image;
    private String thumbnail;
    private Object date;
    private String description;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "image", nullable = false, length = 50)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "thumbnail", nullable = false, length = 50)
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Column(name = "date", nullable = false)
    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
