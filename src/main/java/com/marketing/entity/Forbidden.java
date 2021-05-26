package com.marketing.entity;

import javax.persistence.*;

@Entity
public class Forbidden {
    private long id;
    private String word;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "word", nullable = false, length = 50)
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
