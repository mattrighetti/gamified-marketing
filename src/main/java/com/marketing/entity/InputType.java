package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "input_type", schema = "gamified_marketing")
public class InputType {
    private long id;
    private String type;

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
    @Column(name = "type", nullable = false, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
