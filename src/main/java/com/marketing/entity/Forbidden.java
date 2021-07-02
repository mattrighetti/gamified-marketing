package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "forbidden", schema = "gamified_marketing")
public class Forbidden {
    private long id;
    private String word;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "word", nullable = false, length = 50)
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
