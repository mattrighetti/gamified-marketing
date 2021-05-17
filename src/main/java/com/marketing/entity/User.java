package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    private String username;
    private String password;
    private Byte admin;
    private Byte banned;
    private Integer score;

    @Id
    @Column(name = "username", nullable = false, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "admin", nullable = false)
    public Byte getAdmin() {
        return admin;
    }

    public void setAdmin(Byte admin) {
        this.admin = admin;
    }

    @Basic
    @Column(name = "banned", nullable = false)
    public Byte getBanned() {
        return banned;
    }

    public void setBanned(Byte banned) {
        this.banned = banned;
    }

    @Basic
    @Column(name = "score", nullable = false)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (admin != null ? !admin.equals(user.admin) : user.admin != null) return false;
        if (banned != null ? !banned.equals(user.banned) : user.banned != null) return false;
        if (score != null ? !score.equals(user.score) : user.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        result = 31 * result + (banned != null ? banned.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }
}
