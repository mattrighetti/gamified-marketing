package com.marketing.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.io.Serializable;

@Entity
@Table(name = "user", schema="gamified_marketing")
public class User implements Serializable {

    @Id
    private String username;
    private String password;
    private Boolean admin;
    private Boolean banned;
    private Integer score;

    public User(){

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
