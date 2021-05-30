package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(
                name = "User.selectUserWithUsername",
                query = "SELECT u FROM User u WHERE u.username = :username"
        ),
        @NamedQuery(
                name = "User.selectUserWithUsernameOrEmail",
                query = "SELECT u FROM User u WHERE u.username = :username OR u.email = :email"
        )
})
public class User {
    private long id;
    private String username;
    private String password;
    private String email;
    private boolean admin;
    private boolean banned;
    private Object score;

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
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "admin", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Basic
    @Column(name = "banned", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean getBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Column(name = "score", nullable = false)
    public Object getScore() {
        return score;
    }

    public void setScore(Object score) {
        this.score = score;
    }
}
